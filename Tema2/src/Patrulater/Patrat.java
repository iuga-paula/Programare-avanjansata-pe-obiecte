package Patrulater;

public class Patrat extends Dreptunghi{
    private  Double latura;

    public  Patrat(){
        super();
        nume = "patrat";
        latura = 0.00;

    }

    public Patrat(Double latura){
        super(latura, latura);
        this.latura = latura;
        nume = "patrat";
    }

    @Override
    public void Aria() {
        super.Aria();
    }
}
