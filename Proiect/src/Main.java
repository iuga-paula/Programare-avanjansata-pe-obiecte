import Gestiuni.GestiunePlatforma;
import Localuri.Adresa;
import Localuri.Local;
import Meniu.Bauturi;
import Meniu.FelPrincipal;

import java.sql.*;

public class Main {
    public static void main(String[] args){

       try{

       GestiunePlatforma gestiunePlatforma = GestiunePlatforma.getInstance();
       gestiunePlatforma.addLocaluri();
       gestiunePlatforma.addSoferi();

        gestiunePlatforma.inegistrare();
           System.out.println();
        gestiunePlatforma.inegistrare();
           System.out.println();
        gestiunePlatforma.logare(); //cu manager hotel

        FelPrincipal fp1 = new FelPrincipal("", "", "Paste cu fructe de mare", 26.50);
        FelPrincipal fp2 = new FelPrincipal("", "ruccola", "Zucchini cu pui", 30.50);
        Bauturi b1 = new Bauturi(true, true, "Aperol", 10.00);
        Bauturi b2 = new Bauturi(true, true, "Sampanie", 15.00);
        Bauturi b3 = new Bauturi(true, true, "Vin rosu", 13.00);
        Local AltShift = new Local(new Adresa("Bul. Unirii", 28, null, null, null, "Bucuresti", "Bucuresti", 1, "312900"), "AltShift Restaurant", new Time(1_800_000), new Time(900_000));
        AltShift.addProdus(fp1);
        AltShift.addProdus(fp2);
        AltShift.addProdus(b1);
        AltShift.addProdus(b2);
        AltShift.oferaDiscount(b2,10.00);
        //AltShift.afiseazaProduse();
           System.out.println();
        gestiunePlatforma.oferaDiscount(b2, AltShift, 10.00);

           System.out.println();
        gestiunePlatforma.adaugaLocalManager(AltShift);
           System.out.println();
        gestiunePlatforma.adaugaLocalPlaforma(AltShift);
           System.out.println();
        gestiunePlatforma.veziProduseLocal(AltShift);

        //gestiunePlatforma.logare();

        gestiunePlatforma.delogare();
        System.out.println();
        gestiunePlatforma.afiseazaDateCont();




        gestiunePlatforma.logare();
           System.out.println();
        gestiunePlatforma.afiseazaDateCont();
           System.out.println();
        gestiunePlatforma.schimbaAdresa(new Adresa("Bul. Tineretului", 10, 5 , "A", (Integer) null, "Bucuresti", "Bucuresti", 6, "302900"));
           System.out.println();
        gestiunePlatforma.schimbaAdresa(new Adresa("Bul. Tineretului", 10, 5 , "A", 123, "Bucuresti", "Bucuresti", 6, "302900"));
           System.out.println();
        gestiunePlatforma.afiseazaDateCont();
           System.out.println();
        gestiunePlatforma.veziListaLocaluri();
           System.out.println();
        gestiunePlatforma.veziProduseLocal(AltShift);
           System.out.println();
        gestiunePlatforma.cautaProdus("Paste cu fructe de mare");
           System.out.println();
        gestiunePlatforma.veziProduseLocal(AltShift);
           System.out.println();
        gestiunePlatforma.comandaDeLaLocalul("AltShift");
           System.out.println();
        gestiunePlatforma.comandaDeLaLocalul("AltShift Restaurant");
           System.out.println();
        gestiunePlatforma.adaugaProdusLaComanda(fp1);
        gestiunePlatforma.adaugaProdusLaComanda(fp2);
        gestiunePlatforma.adaugaProdusLaComanda(b3);
        gestiunePlatforma.adaugaProdusLaComanda(b1);
        gestiunePlatforma.stergeProdusDinComanda(b3);
        gestiunePlatforma.stergeProdusDinComanda(fp2);
        //gestiunePlatforma.stergeToateProdusele();
           System.out.println();
        gestiunePlatforma.finalizeazaComanda();
           System.out.println();
        gestiunePlatforma.veziIstoric();
           System.out.println();
        gestiunePlatforma.adaugaLocalPreferinte(AltShift);
           System.out.println();
        gestiunePlatforma.veziPreferinte();
           System.out.println();
        gestiunePlatforma.stergeLocalPreferinte(AltShift);
           System.out.println();
        gestiunePlatforma.veziPreferinte();
           System.out.println();
        gestiunePlatforma.repetaComanda(1);


        /*Localuri.ValidatorAdesa validatorAdesa = new Localuri.ValidatorAdesa();
        Localuri.Adresa a1 = new Localuri.Adresa("Bul. Tineretului", 10, 5 , "A", (Integer) null, "Bucuresti", "Bucuresti", 6, "302900");
        validatorAdesa.Validare(a1);

        validatorAdesa.Validare(new Localuri.Adresa("Bul. Dristor", 10, null, null, null, "Bucuresti", "Bucuresti", 3, "302900"));
        Localuri.Local PuertoCafe = new Localuri.Local(new Localuri.Adresa("Bul. Dristor", 10, null, null, null, "Bucuresti", "Bucuresti", 3, "302900"), "Puerto Cafe", new Time(1_800_000), new Time(900_000));
        System.out.println(PuertoCafe);
        System.out.println(PuertoCafe.getNrAngajati());


        Localuri.Local SalonGolescu = new Localuri.Local(new Localuri.Adresa(), "Salon Golescu", new Time(36_000_000), new Time(1_000_000));
        SalonGolescu.setAdresa(a1);
        SalonGolescu.setNrAngajati(120);
        SalonGolescu.addAngajat();
        System.out.println(SalonGolescu);
        System.out.println(SalonGolescu.getNrAngajati());


        System.out.println();
        Meniu.FelPrincipal fp = new Meniu.FelPrincipal("cartofi natur", "", "Limba de vita cu sos", 35.50);
        System.out.println(fp.getPret());
        System.out.println(fp);
        System.out.println(fp.getId());

        Meniu.FelPrincipal fp3 = new Meniu.FelPrincipal("ardei iuti", "", "sarmale", 15.50);

        Meniu.FelPrincipal fp2 = new Meniu.FelPrincipal();
       fp2.introduDateFelPrincipal();
       fp2.setPret(25.99);
        System.out.println(fp2);
        System.out.println(fp2.getId());

        Meniu.Desert d = new Meniu.Desert(false, "Lava cake", 12.00);
        System.out.println(d);

        Meniu.Bauturi b = new Meniu.Bauturi(true, true, "Aperol", 10.00);

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
        Utilizatori.ManagerLocal managerLocal = new Utilizatori.ManagerLocal(SalonGolescu, "0102938493hgi", "Antonescu", "Marian Mihai", "mm@mial.com" );
        System.out.println(managerLocal.getLocal());
        System.out.println(managerLocal.getLocal().getNrAngajati()); // a ramas 121



        System.out.println("----------------------");

        Localuri.Sofer sofer1 = new Localuri.Sofer();
        System.out.println(sofer1);

       // sofer1.introduDateSofer();
        System.out.println(sofer1);
        sofer1.maresteSalariu(10.00);

        Localuri.Sofer sofer2 = new Localuri.Sofer("Ilie", "Casian Iulian", "part-time", 1500.00, "0772207605");


        Localuri.Comanda comanda = new Localuri.Comanda(10.00,SalonGolescu);
        comanda.adaugaProdus(fp);
        comanda.adaugaProdus(b);
        System.out.println(SalonGolescu.getProduse().contains(fp));
        System.out.println(SalonGolescu.getProduse().contains(fp2));
        fp3.setPret(10.00);//daca modific ceva la un produs nu se modifica si din lista localurilor deci noul fp3 nu mai e  in lista Salonului
        System.out.println(SalonGolescu.getProduse().contains(fp3));


        Meniu.FelPrincipal fp4 = new Meniu.FelPrincipal("", "", "tochitura", 45.50);
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

           /*String insertPersonSql = "INSERT INTO Test(elem) VALUES(?)";
           Connection connection = DatabaseConfiguration.getDatabaseConnection();
           if(!connection.isClosed())
           {
               System.out.println("Conexiune ok");
           }

           ArrayList<String> elements = new ArrayList<String>();
           elements.add("telefon");
           elements.add("penar");

           try {
               for (String element : elements){


                   PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
                   preparedStatement.setString(1, element);
                   preparedStatement.executeUpdate();
                   System.out.println(element);

               }
           } catch (SQLException e)    {
               e.printStackTrace();
           }*/

       }

       catch (Exception exception){
           //System.out.println("Clona nesuportata la linia: " + exception.getStackTrace()[0].getLineNumber());
           exception.printStackTrace();
       }

    }
}
