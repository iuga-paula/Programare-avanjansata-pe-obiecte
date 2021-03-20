import java.sql.Time;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
       GestiunePlatforma gestiunePlatforma = GestiunePlatforma.getInstance();
       GestiunePlatforma  gestiunePlatforma1 = GestiunePlatforma.getInstance();


        gestiunePlatforma.inegistrare();
        gestiunePlatforma.inegistrare();
        gestiunePlatforma.logare();
        Local SalonGolescu = new Local(new Adresa(), "Salon Golescu", new Time(36_000_000), new Time(1_000_000));
        gestiunePlatforma.adaugaLocalManager(SalonGolescu);
        gestiunePlatforma.adaugaLocalPlaforma(SalonGolescu);

        //gestiunePlatforma.logare();

        gestiunePlatforma.delogare();
        gestiunePlatforma.afiseazaDateCont();

        gestiunePlatforma.logare();
        gestiunePlatforma.afiseazaDateCont();
        gestiunePlatforma.schimbaAdresa(new Adresa("Bul. Tineretului", 10, 5 , "A", (Integer) null, "Bucuresti", "Bucuresti", 6, "302900"));


        /*ValidatorAdesa validatorAdesa = new ValidatorAdesa();
        Adresa a1 = new Adresa("Bul. Tineretului", 10, 5 , "A", (Integer) null, "Bucuresti", "Bucuresti", 6, "302900");
        validatorAdesa.Validare(a1);

        validatorAdesa.Validare(new Adresa("Bul. Dristor", 10, null, null, null, "Bucuresti", "Bucuresti", 3, "302900"));
        Local PuertoCafe = new Local(new Adresa("Bul. Dristor", 10, null, null, null, "Bucuresti", "Bucuresti", 3, "302900"), "Puerto Cafe", new Time(1_800_000), new Time(900_000));
        System.out.println(PuertoCafe);
        System.out.println(PuertoCafe.getNrAngajati());


        Local SalonGolescu = new Local(new Adresa(), "Salon Golescu", new Time(36_000_000), new Time(1_000_000));
        SalonGolescu.setAdresa(a1);
        SalonGolescu.setNrAngajati(120);
        SalonGolescu.addAngajat();
        System.out.println(SalonGolescu);
        System.out.println(SalonGolescu.getNrAngajati());


        System.out.println();
        FelPrincipal fp = new FelPrincipal("cartofi natur", "", "Limba de vita cu sos", 35.50);
        System.out.println(fp.getPret());
        System.out.println(fp);
        System.out.println(fp.getId());

        FelPrincipal fp3 = new FelPrincipal("ardei iuti", "", "sarmale", 15.50);

        FelPrincipal fp2 = new FelPrincipal();
       fp2.introduDateFelPrincipal();
       fp2.setPret(25.99);
        System.out.println(fp2);
        System.out.println(fp2.getId());

        Desert d = new Desert(false, "Lava cake", 12.00);
        System.out.println(d);

        Bauturi b = new Bauturi(true, true, "Aperol", 10.00);

        SalonGolescu.addProdus(d);
        SalonGolescu.addProdus(d);
        SalonGolescu.addProdus(fp);
        SalonGolescu.addProdus(fp2);
        SalonGolescu.addProdus(fp3);

        SalonGolescu.afiseazaProduse();


        SalonGolescu.stergeProdus(b);
       SalonGolescu.oferaDiscount(fp, 40.00);
       System.out.println(fp.getPret());
        SalonGolescu.afiseazaProduse();

        PuertoCafe.addProdus(b);

        System.out.println("-----------------------------");
        ManagerLocal managerLocal = new ManagerLocal(SalonGolescu, "0102938493hgi", "Antonescu", "Marian Mihai", "mm@mial.com" );
        System.out.println(managerLocal.getLocal());
        System.out.println(managerLocal.getLocal().getNrAngajati()); // a ramas 121



        System.out.println("----------------------");

        Sofer sofer1 = new Sofer();
        System.out.println(sofer1);

       // sofer1.introduDateSofer();
        System.out.println(sofer1);
        sofer1.maresteSalariu(10.00);

        Sofer sofer2 = new Sofer("Ilie", "Casian Iulian", "part-time", 1500.00, "0772207605");


        Comanda comanda = new Comanda(10.00,SalonGolescu);
        comanda.adaugaProdus(fp);
        comanda.adaugaProdus(b);
        System.out.println(SalonGolescu.getProduse().contains(fp));
        System.out.println(SalonGolescu.getProduse().contains(fp2));
        fp3.setPret(10.00);//daca modific ceva la un produs nu se modifica si din lista localurilor deci noul fp3 nu mai e  in lista Salonului
        System.out.println(SalonGolescu.getProduse().contains(fp3));


        FelPrincipal fp4 = new FelPrincipal("", "", "tochitura", 45.50);
        SalonGolescu.addProdus(fp4);
        SalonGolescu.oferaDiscount(fp4,10.00);
        System.out.println(SalonGolescu.getProduse().contains(fp4));
        comanda.finalizeaza(sofer2);

        System.out.println();
        String[] str = " ana are     ".split(" "); //face si siruri vide
        System.out.println(Arrays.toString(str));
        String[] str2 = (" ana are     ".trim()).split(" ");//fara siruri vide
        System.out.println(Arrays.toString(str2));
        String s = "ana ";
        if(s.matches("^[ A-Za-z]+$")){
            System.out.println("da");
        }*/

    }
}
