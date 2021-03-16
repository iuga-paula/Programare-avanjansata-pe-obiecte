import java.util.Arrays;

public class Main {
    public static void main(String[] args) {



        FelPrincipal fp = new FelPrincipal("cartofi natur", "", "Limba de vita cu sos", 35.50);
        System.out.println(fp.getPret());
        System.out.println(fp);
        System.out.println(fp.getId());

        FelPrincipal fp2 = new FelPrincipal();
        fp2.introduDateFelPrincipal();
        System.out.println(fp2);
        System.out.println(fp2.getId());


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
