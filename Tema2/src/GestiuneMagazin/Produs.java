package GestiuneMagazin;

public class Produs {
    private String nume;
    private Double pret;
    private Integer cantitate;

    public Produs(String  nume, Double pret, int cantitate){
        this.nume = nume;
        this.pret = pret;
        this.cantitate = cantitate;



    }

    @Override
    public String toString() {
        return "Produsul " + nume + " " + pret + "" + cantitate;

    }

    public Double getTotalProdus() {
        return pret*cantitate;
    }


}
