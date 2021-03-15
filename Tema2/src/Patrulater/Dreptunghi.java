package Patrulater;

public class Dreptunghi extends Paralelogram{

    private Double latime;
    private Double lungime;

    public  Dreptunghi(){
        super();
        nume = "dreptunghi";
        latime = lungime = 0.00;

    }

    public Dreptunghi(Double latime, Double lungime){
        super(latime, lungime);
        this.latime = latime;
        this.lungime = lungime;

        nume = "dreptunghi";
    }

    @Override
    public void Aria() {
        super.Aria();
    }

}
