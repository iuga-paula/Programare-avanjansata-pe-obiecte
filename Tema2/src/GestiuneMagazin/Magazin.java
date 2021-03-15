package GestiuneMagazin;

import java.util.Arrays;
import java.util.Objects;

public class Magazin {
    private String nume;
    //private Produs produse[] = new Produs [3];
    private Produs[] produse;

    private  int nrProduse;

    public Magazin(){
        this.nume = "";
        this.nrProduse = 0;
        this.produse = new Produs[3];
    }

    public  Magazin(String nume){
        this.nume = nume;
        this.nrProduse = 0;
        produse = new Produs[3];

    }
    public  void addProdus(Produs produs){
        if(nrProduse >= 3){
            System.out.println("Nu mai puteti adauga produse in Magazinul " + this.nume);

        }
        else{
            produse[nrProduse] = produs;
            nrProduse++;

        }

    }

    public Double getTotalMagazin(){
        Double total = 0.00;
        for ( Produs p:
             this.produse) {
            total += p.getTotalProdus();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Magazinul " + nume  +
                "cu  " + nrProduse + " produse : \n" + Arrays.toString(produse);
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Magazin)) return false;
        Magazin magazin = (Magazin) o;
        return nrProduse == magazin.nrProduse && Objects.equals(nume, magazin.nume) && Arrays.equals(produse, magazin.produse);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nume, nrProduse);
        result = 31 * result + Arrays.hashCode(produse);
        return result;
    }
}
