import java.sql.Time;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        //String strada, Integer nr, Integer bloc, String scara, Integer apartament, String localitate, String judet, Integer sector, Integer codPostal
        ValidatorAdesa validatorAdesa = new ValidatorAdesa();
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
        SalonGolescu.addProdus(b);
        SalonGolescu.addProdus(fp);
        SalonGolescu.addProdus(fp2);

        SalonGolescu.afiseazaProduse();

        SalonGolescu.stergeProdus(b);
        SalonGolescu.stergeProdus(b);
        SalonGolescu.oferaDiscount(fp, 40.00);
        //System.out.println(fp.getPret());
        SalonGolescu.afiseazaProduse();



        System.out.println("----------------------");

        Sofer sofer1 = new Sofer();
        System.out.println(sofer1);

        sofer1.introduDateSofer();
        System.out.println(sofer1);
        sofer1.maresteSalariu(10.00);

        Sofer sofer2 = new Sofer("Ilie", "Casian Iulian", "part-time", 1500.00);


        String[] str = " ana are     ".split(" "); //face si siruri vide
        System.out.println(Arrays.toString(str));
        String[] str2 = (" ana are     ".trim()).split(" ");//fara siruri vide
        System.out.println(Arrays.toString(str2));
        String s = "ana ";
        if(s.matches("^[ A-Za-z]+$")){
            System.out.println("da");
        }

    }
}
