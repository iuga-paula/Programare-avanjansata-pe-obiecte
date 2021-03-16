import java.util.Objects;
import java.util.Scanner;

public class Desert extends Produs{

    private Boolean vegan;

    public Desert(Boolean vegan, String denumire, Double pret){
        super(denumire, pret);
        this.vegan = vegan;

    }

    public  Desert(){
        this(false, "", 0.00);
    }

    public void introduDateDesert(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti denumire");
        String denumire = scanner.nextLine();
        this.setDenumire(denumire);

        System.out.println("Este un produs vegan? 1 pentru da, 2 pentru nu");
        Integer nr = scanner.nextInt();
        switch (nr){
            case 1:
                this.vegan = true;
                break;
            case 2:
                break;
            default:
                System.out.println("Optinue gresita!!");

        }
    }

    public Boolean getVegan() {
        return vegan;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
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
        if (!(o instanceof Desert)) return false;
        if (!super.equals(o)) return false;
        Desert desert = (Desert) o;
        return Objects.equals(vegan, desert.vegan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vegan);
    }

    @Override
    public String toString() {
        if (vegan == false)
            return denumire + " - normal";


        return denumire + "- vegan";
    }


}
