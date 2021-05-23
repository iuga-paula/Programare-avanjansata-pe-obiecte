package Gestiuni;

import Bcrypt.BCrypt;
import Localuri.*;
import Meniu.*;
import Repo.*;
import Utilizatori.*;
import CSV.*;


import java.text.SimpleDateFormat;
import java.util.*;

import static Bcrypt.BCrypt.checkpw;

public class GestiunePlatforma {

    private static GestiunePlatforma instance;
    private Map<String, Utilizator> users = new Hashtable<String, Utilizator>(); //hasthtable pt userii inregistrati: user[username] = obiectul user care ct hashul de parola;
    private TreeSet<Local> localuri = new TreeSet<>();
    private String usernameUtlogat;
    private String parolaUtlogat;
    private Set<Sofer> soferi = new HashSet<>();

    private Scanner scanner = new Scanner(System.in);
    private Audit audit;
    private CitireDate citireDate;
    private Boolean bd = true;



    private GestiunePlatforma() {
        System.out.println("Bine ati venit la platforma GoodFood GoodLife!");

        audit = Audit.getInstance("D:\\documente\\An2_sem2\\ProgrAvansOb\\Proiect\\Logging.csv");//creez serviciul de audit
        citireDate = CitireDate.getInstance();

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

        audit.scrieFisierLoggin();

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

        System.out.println("Utilizatori.Utilizator normal? Da sau nu");

        String optiune = scanner.next();

        if (Objects.equals(optiune.toLowerCase(), "nu")) {

            System.out.println("Intorduceti cod autorizatie:");
            mn.setCodAutorizatie(scanner.next());
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
        audit.scrieFisierLoggin();

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
        audit.scrieFisierLoggin();

        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            System.out.println(usernameUtlogat + ", ne revedem curand!");
        }

        usernameUtlogat = null;
        parolaUtlogat = null;

    }

    public  void schimbaAdresa(Adresa adresa){
        audit.scrieFisierLoggin();

        ValidatorAdesa validatorAdesa = new ValidatorAdesa();


        if(usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {

                System.out.println("*Schimbare adresa*");
                if(validatorAdesa.Validare(adresa)){
                ((UtilizatorNormal) users.get(usernameUtlogat)).setAdresa(adresa);
                System.out.println(((UtilizatorNormal) users.get(usernameUtlogat)).getAdresa());

            }
                else{
                    System.out.print(" Introduceti alta adresa.");
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
        audit.scrieFisierLoggin();

        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof ManagerLocal) {

                System.out.println("*Adaugare local*");
                if(localuri.contains(local))
                {
                    System.out.println("Acest local exista deja!");
                    return;
                }
                else {
                    System.out.println("Local valid");
                }
                localuri.add(local);
                if(bd) {
                    //ii adaug si adresa in baza de date
                    Adresa_Repo.insert(local.getAdresa());
                    Integer id = Adresa_Repo.getIdAdresa(local.getAdresa());
                    Localuri_Repo.insert(local, id);//adaug localul in baza de date
                    Integer idLocal = Localuri_Repo.getId(local.getDenumire());
                    //ii adaug toate produsele in baza de date si apoi legaturile cu ele din tabelel asociative
                    Set<Produs> produse = local.getProduse();
                    for (Produs p:produse){
                        //adaug produsul si in baza de date pt local;
                        if (p instanceof Bauturi) {
                            Bauturi_Repo.insert((Bauturi) p);
                            Integer idProdus = Bauturi_Repo.getIdBautura((Bauturi) p);
                            if(idProdus != null)
                                Bauturi_Repo.adaugaBaturaLocal(idLocal, idProdus);
                        } else if (p instanceof Desert) {
                            Desert_Repo.insert((Desert) p);
                            Integer idProdus = Desert_Repo.getIdDesert((Desert) p);
                            if(idProdus != null)
                                Desert_Repo.adaugaDesertLocal(idLocal, idProdus);

                        } else if (p instanceof FelPrincipal) {
                            Felurip_Repo.insert((FelPrincipal) p);
                            Integer idProdus = Felurip_Repo.getIdFelp((FelPrincipal) p);
                            if(idProdus != null)
                                Felurip_Repo.adaugaFelpLocal(idLocal, idProdus);

                        }


                    }
                }

            } else {
                System.out.println("Nu aveti permisiunea pentru a adauga local!");
            }

        }
        else{
            System.out.println("Nu sunteti logat!");
        }
    }

    public void adaugaLocalManager(Local local) {
        audit.scrieFisierLoggin();

        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof ManagerLocal) {
                System.out.println("*Adaugati-va un local*");
                ((ManagerLocal) users.get(usernameUtlogat)).setLocal(local);
                if(localuri.contains(local))
                {
                    System.out.println("Acest local exista deja!");
                    return;
                }
                else System.out.println("Local valid");
                localuri.add(local);
                    if(bd) {
                        //ii adaug si adresa in baza de date
                        Adresa_Repo.insert(local.getAdresa());
                        Integer id = Adresa_Repo.getIdAdresa(local.getAdresa());
                        Localuri_Repo.insert(local, id);//adaug localul in baza de date
                        Integer idLocal = Localuri_Repo.getId(local.getDenumire());
                        //ii adaug toate produsele in baza de date si apoi legaturile cu ele din tabelel asociative
                        Set<Produs> produse = local.getProduse();
                        for (Produs p:produse){
                            //adaug produsul si in baza de date pt local;
                            if (p instanceof Bauturi) {
                                Bauturi_Repo.insert((Bauturi) p);
                                Integer idProdus = Bauturi_Repo.getIdBautura((Bauturi) p);
                                if(idProdus != null)
                                    Bauturi_Repo.adaugaBaturaLocal(idLocal, idProdus);
                            } else if (p instanceof Desert) {
                                Desert_Repo.insert((Desert) p);
                                Integer idProdus = Desert_Repo.getIdDesert((Desert) p);
                                if(idProdus != null)
                                    Desert_Repo.adaugaDesertLocal(idLocal, idProdus);

                            } else if (p instanceof FelPrincipal) {
                                Felurip_Repo.insert((FelPrincipal) p);
                                Integer idProdus = Felurip_Repo.getIdFelp((FelPrincipal) p);
                                if(idProdus != null)
                                    Felurip_Repo.adaugaFelpLocal(idLocal, idProdus);

                            }


                        }

                }
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }

    public void veziListaLocaluri(){
        audit.scrieFisierLoggin();

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
        audit.scrieFisierLoggin();

        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (localuri.contains(local)) {
                System.out.println("*Produse local " + local.getDenumire() + "*");
                local.afiseazaProduse();
            } else {
                System.out.println("Nu exista acest local!");
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }


    }

    public void afiseazaDateCont(){
        audit.scrieFisierLoggin();

        if(usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            System.out.println(users.get(usernameUtlogat));
        }

    }

    public void oferaDiscount(Produs p, Local local, Double discount) throws CloneNotSupportedException {
        audit.scrieFisierLoggin();

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
        audit.scrieFisierLoggin();

        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof ManagerLocal) {
                for(Local local1:localuri) {
                    if (local1.equals(local)) {
                        local1.addProdus(p);
                        if(bd) {
                            //adaug produsul si in baza de date pt local;
                            Integer idLocal = Localuri_Repo.getId(local.getDenumire());
                            if (p instanceof Bauturi) {
                                Integer idProdus = Bauturi_Repo.getIdBautura((Bauturi) p);
                                Bauturi_Repo.adaugaBaturaLocal(idLocal, idProdus);
                            } else if (p instanceof Desert) {
                                Integer idProdus = Desert_Repo.getIdDesert((Desert) p);
                                Desert_Repo.adaugaDesertLocal(idLocal, idProdus);

                            } else if (p instanceof FelPrincipal) {
                                Integer idProdus = Felurip_Repo.getIdFelp((FelPrincipal) p);
                                Felurip_Repo.adaugaFelpLocal(idLocal, idProdus);

                            }
                        }


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
        audit.scrieFisierLoggin();

        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof ManagerLocal) {
                for(Local local1:localuri) {
                    if (local1.equals(local)) {
                        local1.stergeProdus(p);
                        if(bd) {
                            //sterg produsul de la local si din baza de date
                            Integer idLocal = Localuri_Repo.getId(local.getDenumire());
                            if (p instanceof Bauturi) {
                                Integer idProdus = Bauturi_Repo.getIdBautura((Bauturi) p);
                                Bauturi_Repo.stergeBaturaLocal(idLocal, idProdus);
                            } else if (p instanceof Desert) {
                                Integer idProdus = Desert_Repo.getIdDesert((Desert) p);
                                Desert_Repo.stergeDesertLocal(idLocal, idProdus);

                            } else if (p instanceof FelPrincipal) {
                                Integer idProdus = Felurip_Repo.getIdFelp((FelPrincipal) p);
                                Felurip_Repo.stergeFelpLocal(idLocal, idProdus);

                            }
                        }
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
        audit.scrieFisierLoggin();

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
        audit.scrieFisierLoggin();

        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {
                System.out.println("*Repeta comanda cu nr de ordine " + nr + "*");
                ((UtilizatorNormal) users.get(usernameUtlogat)).repetaComanda(nr);
                finalizeazaComanda();
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }


    }

    public  void adaugaLocalPreferinte(Local l){
        audit.scrieFisierLoggin();

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

    public  void stergeLocalPreferinte(Local l){
        audit.scrieFisierLoggin();

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

    public  void veziPreferinte(){
        audit.scrieFisierLoggin();

        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {
                ((UtilizatorNormal) users.get(usernameUtlogat)).afiseazaPreferinte();

            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }



    public void afiseazaComandaInCurs(){
        audit.scrieFisierLoggin();

        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {
                System.out.println("*Localuri.Comanda in curs*");
                System.out.println(((UtilizatorNormal) users.get(usernameUtlogat)) .getComandaInCurs());
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }

    public void comandaDeLaLocalul(String denumire){
        audit.scrieFisierLoggin();

        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {
                boolean ok = false;
                for (Local l : localuri) {
                    if (Objects.equals(l.getDenumire().toLowerCase(), denumire.toLowerCase())) {
                        ok = true;
                        ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().setLocal(l);
                        break;
                    }
                }

                if(!ok){
                    System.out.println("Nu exista acest local!");
                }
            }
        }
        else{
            System.out.println("Nu sunteti logat!");
        }

    }


    public void cautaProdus(String numeProdus){
        audit.scrieFisierLoggin();

        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if (users.get(usernameUtlogat) instanceof UtilizatorNormal) {
                boolean ok = false;
                System.out.println("*Produsul " + numeProdus + "*");
                for(Local local : localuri){
                    for(Produs produs : local.getProduse()){
                        if(Objects.equals(produs.getDenumire().toLowerCase(), numeProdus.toLowerCase())){
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
        audit.scrieFisierLoggin();

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
        audit.scrieFisierLoggin();

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
        audit.scrieFisierLoggin();

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
        audit.scrieFisierLoggin();

        if (usernameUtlogat != null && !usernameUtlogat.isEmpty()) {
            if(users.get(usernameUtlogat) instanceof UtilizatorNormal){
                System.out.println("*Finalizare comanda*");


                System.out.println("Cum doriti sa primiti comanda? Introduceti 1 pentru preluare personala si 2 pentru transport.");
                int optiune = scanner.nextInt();


                //pt data
                Date date = new Date();
                java.sql.Date new_date =new java.sql.Date(date.getTime());

                switch (optiune) {
                    case 1 -> {//cu ridicare personala
                        ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().finalizeaza2();
                       Double total = ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().getTotal();
                       Double discount = ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().getDiscount();
                       Integer idLocal = Localuri_Repo.getId(((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().getLocal().getDenumire());

                        ((UtilizatorNormal) users.get(usernameUtlogat)).adaugaComandaIstoric();
                        if(bd)
                            ManagerBd.adaugaComanda(users.get(usernameUtlogat).getUsername(), new_date, total, discount, idLocal,null);

                    }
                    case 2 -> {
                        int size = soferi.size();
                        int item = new Random().nextInt(size);//aleg sofer random din multimea de soferi si il asignez la comanda;
                        int i = 0;
                        for (Sofer sofer : soferi) {
                            if (i == item) {//cu livrare
                                ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().finalizeaza(sofer);
                                ((UtilizatorNormal) users.get(usernameUtlogat)).adaugaComandaIstoric();

                                Double total = ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().getTotal();
                                Double discount = ((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().getDiscount();
                                Integer idLocal = Localuri_Repo.getId(((UtilizatorNormal) users.get(usernameUtlogat)).getComandaInCurs().getLocal().getDenumire());
                                Integer idSofer = Soferi_Repo.getidSofer(sofer.getNume(), sofer.getPrenume());
                                if(bd)
                                    ManagerBd.adaugaComanda(users.get(usernameUtlogat).getUsername(), new_date, total, discount, idLocal,idSofer);
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


    if(bd) {//din baza de date
        localuri.addAll(ManagerBd.adaugaLocaluriBD());
    }
    else {
        this.localuri.addAll(citireDate.citesteDate()); //citire cu CSV
    }
    }

    public void addSoferi(){
        if(bd) {//din baza de date
            soferi.addAll(Soferi_Repo.get_soferi());
        }
        else {
            soferi.add(new Sofer("Ilie" ,"Casian Iulian", "part-time",1500.000,"0772207605"));
            soferi.add(new Sofer("Antonescu","Marius", "full-time", 1700.000,"0773467605"));
        }

    }


    public  void afiseazaSoferi(){
        for(Sofer s:soferi){
            System.out.println(s);
        }
    }
}
