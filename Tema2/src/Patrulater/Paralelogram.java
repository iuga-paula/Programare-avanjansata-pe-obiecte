package Patrulater;

public class Paralelogram  extends Patrulater{

    private Double lungime;
    private Double inaltime;

    public  Paralelogram(){
        super();
        nume = "paralelogram";
        lungime = inaltime = 0.00;
    }
    public Paralelogram(Double lungime, Double inaltime){
        super();
        nume = "paralelogram";
        this.lungime = lungime;
        this.inaltime = inaltime;


    }

    @Override
    public void Aria() {
        System.out.println(lungime*inaltime);
    }
}
