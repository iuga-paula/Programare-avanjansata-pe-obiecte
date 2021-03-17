import java.util.Objects;
import java.util.Scanner;

public class FelPrincipal extends Produs {

    private String garnitura;
    private String salata;

    public FelPrincipal(String garnitura, String salata, String denumire, Double pret) {
        super(denumire, pret);
        this.garnitura = garnitura;
        this.salata = salata;
    }

    public FelPrincipal() {
        this("", "", "", 0.00);

    }


    public void introduDateFelPrincipal() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Dati numele felului principal:\n");
        String nume = scanner.nextLine();
        if (nume.matches("^[ A-Za-z]+$")) {
            this.setDenumire(nume.trim());

            System.out.println("Are garnitura? Introduceti 1 pentru da, 2 pentru nu");
            Integer nr = scanner.nextInt();
            switch (nr) {
                case 1:
                    System.out.println("Dati numele garniturii");
                    scanner.nextLine();
                    String garnitura = scanner.nextLine();
                    this.setGarnitura(garnitura.trim());
                    break;


                case 2:
                    break;
                default:
                    System.out.println("Optiune gresita!!!");
            }


            System.out.println("Are salata? Introduceti 1 pentru da, 2 pentru nu");
            Integer nr2 = scanner.nextInt();
            switch (nr2) {
                case 1:
                    System.out.println("Dati numele salatei");
                    scanner.nextLine();
                    String salata = scanner.nextLine();
                    this.setSalata(salata.trim());
                    break;

                case 2:
                    break;
                default:
                    System.out.println("Optiune gresita!!!");


            }
        }
        else{
            System.out.println("Denumire gresit introdusa! Repetati operatiunea");
        }
    }

    @Override
    public Double getPret() {
        return pret;
    }

    @Override
    public void setPret(Double pret) {
        this.pret = pret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FelPrincipal)) return false;
        if (!super.equals(o)) return false;
        FelPrincipal that = (FelPrincipal) o;
        return Objects.equals(garnitura, that.garnitura) && Objects.equals(salata, that.salata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), garnitura, salata);
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder(denumire);

        if (Objects.equals(this.garnitura, "")) {

            str.append(" fara garnitura");

        } else {
            str.append(" cu " + garnitura);
        }

        if (Objects.equals(this.salata, "")) {

            str.append(" fara salata");

        } else {
            str.append(" cu " + salata);
        }
        return str + "";
    }


    public String getGarnitura() {
        return garnitura;
    }

    public void setGarnitura(String garnitura) {
        this.garnitura = garnitura;
    }

    public void setSalata(String salata) {
        this.salata = salata;
    }

    public String getSalata() {
        return salata;
    }
}
