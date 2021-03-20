import Bcrypt.BCrypt;

import java.sql.Time;
import java.util.*;
import java.util.function.DoubleToIntFunction;

import static Bcrypt.BCrypt.checkpw;

public class GestiunePlatforma {

    private static GestiunePlatforma instance;
    private Map<String, Utilizator> users = new Hashtable<String, Utilizator>(); //hasthtable pt userii inregistrati: user[username] = obiectul user care ct hashul de parola;
    private Set<Local> localuri = new HashSet<Local>();
    private String usernameUtlogat;
    private String parolaUtlogat;
    private Set<Sofer> soferi = new HashSet<Sofer>() ;

    private Scanner scanner = new Scanner(System.in);

    private GestiunePlatforma() {
        System.out.println("Bine ati venit la platforma GoodFood GoodLife!");
    }


    public static GestiunePlatforma getInstance() {
        if (instance == null) {
            instance = new GestiunePlatforma();
        }
        return instance;
    }

    private Boolean valideazaParola(String parola) {
        return parola.matches(".*[A-Z]+.*") && parola.matches(".*[a-z]+.*") && parola.matches(".*[0-9]+.*");
    }

    private Boolean valideazaEmail(String email) {
        return email.matches("^(.+)@(.+)$");

    }

    public void inegistrare() {


        System.out.println("*Inregistrare utilizator*");
        System.out.println("Intorduceti username");
        String username = scanner.next();
        //caut daca useruname ul e luat deja pt ca e cheie in hash
        if (users.containsKey(username)) {
            System.out.println("Username ul deja exista! Repetati operatiunea.");
            return;

        }


        System.out.println("Introduceti parola( cel putin 4 caractere, sa contina cel putin litera mare, litera mica si un numar: ");
        String parola = scanner.next();
        if (!valideazaParola(parola)) {
            System.out.println("Parola nu respecta constangerile! Repetati operatiunea.");
            return;
        }
        System.out.println("Introduceti confirmare parola:");
        String confirmare = scanner.next();

        if (!Objects.equals(parola, confirmare)) {
            System.out.println("Confirmarea nu coincide cu parola! Repetati operatiunea.");
            return;

        }

        System.out.println("Felicitari! Acum introduceti datele de contact");
        UtilizatorNormal ut = new UtilizatorNormal();
        ManagerLocal mn = new ManagerLocal();


        System.out.println("Introduceti mail:");
        String mail = scanner.next();
        if (!valideazaEmail(mail)) {
            System.out.println("Mail incorect! Repetati opreatiunea.");
            return;
        }

        System.out.println("Introduceti nume:");
        scanner.nextLine();
        String nume = scanner.nextLine();


        System.out.println("Introduceti prenume:");
        String prenume = scanner.nextLine();

        System.out.println("Utilizator normal? Da sau nu");

        String optiune = scanner.next();

        if (Objects.equals(optiune.toLowerCase(), "nu")) {

            mn.setEmail(mail);
            mn.setNume(nume);
            mn.setPrenume(prenume);
            mn.setUsername(username);

            String hashed = BCrypt.hashpw(parola, BCrypt.gensalt());
            mn.setParola(hashed);

            mn.setRol("manager local");

            users.put(username, mn);

            System.out.println("*Felicitari! Acum va puteti inregistra localul pe FoodDelivery.*");
            System.out.println("Date manager local:");
            System.out.println(mn);

        } else if (Objects.equals(optiune.toLowerCase(), "da")) {


            ut.setEmail(mail);
            ut.setNume(nume);
            ut.setPrenume(prenume);
            ut.setUsername(username);

            String hashed = BCrypt.hashpw(parola, BCrypt.gensalt());
            ut.setParola(hashed);

            ut.setRol("utilizator normal");

            users.put(username, ut);

            System.out.println("*Felicitari! Acum puteti comanda de la FoodDelivery.*");
            System.out.println("Date utilizator:");
            System.out.println(ut);


        } else
            System.out.println("Optiune invalida!");


    }

    public void logare() {
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            System.out.println("Sunteti deja logat. Pentru logare cu alt utilizator, delogati-va.");
        }
        System.out.println("*Log in*");
        System.out.println("Introduceti username:");
        String username = scanner.next();
        scanner.nextLine();
        if (!users.containsKey(username)) {
            System.out.println("Nu exista acest user! Inregistrati-va.");
            return;
        }

        System.out.println("introduceti parola:");
        String parola = scanner.next();
        if (checkpw(parola, users.get(username).getParola())) {

            usernameUtlogat = username;
            parolaUtlogat = users.get(username).getParola();

            if (users.get(username).getRol() == "utilizator normal")
                System.out.println("Buna " + users.get(username).getPrenume() + "! De ce iti e pofta azi?");
            else
                System.out.println("Buna " + users.get(username).getPrenume() + "! Cum iti manageriezi afacearea azi?");
        } else {
            System.out.println("Parola gresita! Repetati operatiunea.");
        }


    }


    public void delogare() {
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            System.out.println(usernameUtlogat + ", ne revedem curand!");
        }

        usernameUtlogat = null;
        parolaUtlogat = null;

    }

    public  void schimbaAdresa(Adresa adresa){
        ValidatorAdesa validatorAdesa = new ValidatorAdesa();


        if(usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {

                System.out.println("*Schimbare adresa*");
                if(validatorAdesa.Validare(adresa)){
                ((UtilizatorNormal) users.get(usernameUtlogat)).setAdresa(adresa);
                System.out.println(((UtilizatorNormal) users.get(usernameUtlogat)).getAdresa());

            }
                else{
                    System.out.println("Adresa invalida! Introduceti alta adresa.");
                }
            }
            else {
                System.out.println("Nu puteti modifica adresa pt un manager de local!");

                   }
        }
        else{
            System.out.println("Nu sunteti logat.");
        }


    }

    public void adaugaLocalPlaforma(Local local) {
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof ManagerLocal) {

                System.out.println("*Adaugare Local*");
                if(localuri.contains(local))
                {
                    System.out.println("Acest local exista deja!");
                    return;
                }
                localuri.add(local);

            } else {
                System.out.println("Nu aveti permisiunea pentru a adauga local!");
            }

        }
        else{
            System.out.println("Nu sunteti logat!");
        }
    }

    public void adaugaLocalManager(Local local) {
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof ManagerLocal) {
                System.out.println("*Adaugati-va un local*");
                ((ManagerLocal) users.get(usernameUtlogat)).setLocal(local);
                localuri.add(local);
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }

    public void veziListaLocaluri(){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            System.out.println("*Lista Localuri*");

            for (Local l: localuri) {
                System.out.println(l);

            }

        }
        else{
            System.out.println("Nu sunteti logat!");

        }


    }

    public void veziProduseLocal(Local local){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (localuri.contains(local)) {
                System.out.println("*Produse local " + local.getDenumire() + "*");
                for(Produs p : local.getProduse()){
                    System.out.println(p);
                }
            } else {
                System.out.println("Nu exista acest local!");
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }


    }

    public void afiseazaDateCont(){
        if(usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            System.out.println(users.get(usernameUtlogat));
        }

    }

    public void oferaDiscout(Produs p, Local local, Double discount){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof ManagerLocal) {
                for(Local local1:localuri) {
                    if (local1.equals(local)) {
                        local1.oferaDiscount(p, discount);

                        break;
                    }


                }
            } else {
                System.out.println("Nu aveti aceasta permisiune!");
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }

    public void adaugaProdus(Produs p, Local local){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof ManagerLocal) {
                for(Local local1:localuri) {
                    if (local1.equals(local)) {
                        local1.addProdus(p);

                        break;
                    }


                }
            } else {
                System.out.println("Nu aveti aceasta permisiune!");
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }

    public void stergeProdus(Produs p, Local local){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof ManagerLocal) {
                for(Local local1:localuri) {
                    if (local1.equals(local)) {
                        local1.stergeProdus(p);

                        break;
                    }


                }
            } else {
                System.out.println("Nu aveti aceasta permisiune!");
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }


    public void veziIstoric(){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {
                System.out.println("*Istoric Comenzi*");
            }
                ((UtilizatorNormal) users.get(usernameUtlogat)).afiseazaIstoricComenzi();
            }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }

    public void repetaComanda(Integer nr){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {
                System.out.println("*Repeta comanda cu nr de ordine " + nr + "*");
                ((UtilizatorNormal) users.get(usernameUtlogat)).repetaComanda(nr);
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }


    }

    public  void adaugaLocalPreferinte(Local l){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {
                if(localuri.contains(l)) {
                    System.out.println("*Adauga local " + l.getDenumire() + " la preferinte*");
                    ((UtilizatorNormal) users.get(usernameUtlogat)).adaugaPreferinta(l);
                }
                else{
                    System.out.println("Nu exista acest local");
                }
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }

    public  void StergeLocalPreferinte(Local l){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {
                if(localuri.contains(l)) {
                    System.out.println("*Sterge local " + l.getDenumire() + " de la preferinte*");
                    ((UtilizatorNormal) users.get(usernameUtlogat)).stergePreferinta(l);
                }
                else{
                    System.out.println("Nu exista acest local");
                }
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }

    public void AfiseazaProduse(Local l){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {
                if(localuri.contains(l)) {
                    System.out.println("*Produsele localului " + l.getDenumire() + "*");
                    l.afiseazaProduse();
                }
                else{
                    System.out.println("Nu exista acest local");
                }
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }

    public void afiseazaComandaInCurs(){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {
                System.out.println("*Comanda in curs*");
                System.out.println(((UtilizatorNormal) users.get(usernameUtlogat)) .getComandaInCurs());
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }

    public void comandaDeLaLocalul(String denumire){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {
                for (Local l : localuri) {
                    if (Objects.equals(l.getDenumire(), denumire)) {
                        ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().setLocal(l);
                        break;
                    }
                }
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }


    public void cautaProdus(String numeProdus){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {
                boolean ok = false;
                System.out.println("*Produsul " + numeProdus + "*");
                for(Local local : localuri){
                    for(Produs produs : local.getProduse()){
                        if(Objects.equals(produs.getDenumire(), numeProdus)){
                            ok =  true;
                            System.out.println("in " + local.getDenumire() + " pret: " + produs.getPret());

                        }
                    }
                }

                if(!ok){
                    System.out.println("Nu am gasit acest produs in niciun local.");
                }

            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }
    }

    public  void adaugaProdusLaComanda(Produs p) {
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if(users.get(usernameUtlogat) instanceof UtilizatorNormal){
                System.out.println("*Adauga " + p.getDenumire() + " la comanda " +  ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().getIdComanda() + " *");
                ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().adaugaProdus(p);

            }


        } else {
            System.out.println("Nu sunteti logat!");
        }
    }

    public  void stergeProdusDinComanda(Produs p) {
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if(users.get(usernameUtlogat) instanceof UtilizatorNormal){
                System.out.println("*Sterge " + p.getDenumire() + " din comanda " +  ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().getIdComanda() + " *");
                ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().stergeProdus(p);

            }


        } else {
            System.out.println("Nu sunteti logat!");
        }

    }

    public  void stergeToateProdusele(){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if(users.get(usernameUtlogat) instanceof UtilizatorNormal){
                System.out.println("*Sterge toate produsele din comanda " +  ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().getIdComanda() + " *");
                ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().stergeToateProdusele();

            }


        } else {
            System.out.println("Nu sunteti logat!");
        }

    }

    public void finalizeazaComanda(){
        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if(users.get(usernameUtlogat) instanceof UtilizatorNormal){
                System.out.println("*Finalizare comanda*");


                System.out.println("Cum doriti sa primiti comanda? Introduceti 1 pentru preluare personala si 2 pentru transport.");
                int optiune = scanner.nextInt();
                //aleg sofer random din multimea de soferi si il asignez la comanda;
                switch (optiune) {
                    case 1 -> ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().finalizeaza2();
                    case 2 -> {
                        int size = soferi.size();
                        int item = new Random().nextInt(size);
                        int i = 0;
                        for (Sofer sofer : soferi) {
                            if (i == item) {
                                ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().finalizeaza(sofer);
                                break;
                            }

                            i++;
                        }
                    }
                    default -> System.out.println("Optiune gresita!");
                }

            }


        } else {
            System.out.println("Nu sunteti logat!");
        }
    }

    public  void addLocaluri(){
        FelPrincipal fp1 = new FelPrincipal("", "de muraturi", "Tochitura", 45.50);
        FelPrincipal fp2 = new FelPrincipal("orez", "", "Curry de pui", 25.50);
        FelPrincipal fp3 = new FelPrincipal("cartofi copti", "de cruditati", "Somon", 45.90);
        FelPrincipal fp4 = new FelPrincipal("orez", "", "Paella cu fructe de mare", 35.00);
        FelPrincipal fp5 = new FelPrincipal("legume la gratar", "", "Caracacatita la gratar", 50.50);
        FelPrincipal fp6 = new FelPrincipal("orez", "", "Paella vegetariana", 30.70);
        FelPrincipal fp7 = new FelPrincipal("", "", "Frigarui de pui", 20.00);
        FelPrincipal fp8 = new FelPrincipal("mamaliga", "de varza", "Figatei de pui", 15.50);

        Desert d1 = new Desert(false, "Lava cake", 12.00);
        Desert d2 = new Desert(true, "Salam de biscuiti", 10.00);
        Desert d3 = new Desert(true, "Clatite cu fructe", 9.50);
        Desert d4 = new Desert(false, "CheeseCake", 9.50);

        Bauturi b1 = new Bauturi(true, true, "Aperol", 10.00);
        Bauturi b2 = new Bauturi(true, true, "Sampanie rose", 18.00);
        Bauturi b3 = new Bauturi(true, false, "Vin rosu", 15.00);
        Bauturi b4 = new Bauturi(true, false, "Dorna", 5.00);
        Bauturi b7 = new Bauturi(false, false, "Dorna", 5.00);
        Bauturi b5 = new Bauturi(true, false, "Vin alb", 15.00);
        Bauturi b6 = new Bauturi(false, false, "Cappy", 10.00);


        Local PuertoCafe = new Local(new Adresa("Bul. Dristor", 10, null, null, null, "Bucuresti", "Bucuresti", 3, "302900"), "Puerto Cafe", new Time(1_800_000), new Time(900_000));
        Local SalonGolescu = new Local(new Adresa("Bul. Tineretului", 15, null, null, null, "Bucuresti", "Bucuresti", 6, "302988"), "Salon Golescu", new Time(36_000_000), new Time(1_000_000));
        Local CasaDori = new Local(new Adresa("Str. Alexandru Vlahuta", 13, 2, "A", 1, "Bucuresti", "Bucuresti", 3, "306700"), "Puerto Cafe", new Time(1_800_000), new Time(900_000));
        Local CremeriaEmille = new Local(new Adresa("Str. Centrului", 34, 3, "C", 1, "Bucuresti", "Bucuresti", 3, "305677"), "Puerto Cafe", new Time(1_800_000), new Time(900_000));

        PuertoCafe.addProdus(fp2);
        PuertoCafe.addProdus(fp3);
        PuertoCafe.addProdus(fp6);
        PuertoCafe.addProdus(fp5);
        PuertoCafe.addProdus(d1);
        PuertoCafe.addProdus(d2);
        PuertoCafe.addProdus(b1);
        PuertoCafe.addProdus(b2);
        PuertoCafe.addProdus(b3);
        PuertoCafe.addProdus(b4);
        PuertoCafe.addProdus(b5);
        PuertoCafe.addProdus(b6);
        PuertoCafe.addProdus(b7);

        CremeriaEmille.addProdus(d1);
        CremeriaEmille.addProdus(d2);
        CremeriaEmille.addProdus(d3);
        CremeriaEmille.addProdus(d4);
        CremeriaEmille.addProdus(b4);
        CremeriaEmille.addProdus(b7);
        CremeriaEmille.addProdus(b7);

        CasaDori.addProdus(fp1);
        CasaDori.addProdus(fp7);
        CasaDori.addProdus(fp8);
        CasaDori.addProdus(b3);
        CasaDori.addProdus(b4);
        CasaDori.addProdus(b7);

        SalonGolescu.addProdus(fp4);
        SalonGolescu.addProdus(fp5);
        SalonGolescu.addProdus(d4);


    }

    public void addSoferi(){
        soferi.add(new Sofer("Ilie", "Casian Iulian", "part-time", 1500.00, "0772207605"));
        soferi.add(new Sofer("Antonescu", "Marius", "full-time", 1700.00, "0773467605"));
        soferi.add(new Sofer("Popa", "Gabriel", "part-time", 1600.50, "0774205909"));
        soferi.add(new Sofer("Dusescu", "Adrian", "full-time", 1678.30, "0772807699"));
        soferi.add(new Sofer("Ieftimie", "Antonio", "practica", 1000.00, "0787207005"));
        soferi.add(new Sofer("Dumitru", "Iustin", "part-time", 1300.00, "0772206020"));
        soferi.add(new Sofer("Georgescu", "Alex Andrei", "full-time", 1600.00, "0772707115"));
        soferi.add(new Sofer("Mantarosie", "Iulian", "full-time", 1698.90, "0777709105"));


    }

    public  void afiseazaSoferi(){
        for(Sofer s:soferi){
            System.out.println(s);
        }
    }
}
