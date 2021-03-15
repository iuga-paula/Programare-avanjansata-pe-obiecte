package Patrulater;

public class Romb extends Paralelogram{

     private  double latura;
     private double inaltime;

    public  Romb(){
        super();
        nume = "romb";
        latura = inaltime = 0.00;

    }

    public Romb(Double latura, Double inaltime){
        super(latura, inaltime);
        nume = "romb";
        this.latura = latura;
        this.inaltime = inaltime;
    }

    @Override
    public void Aria() {
        super.Aria();
    }
}
