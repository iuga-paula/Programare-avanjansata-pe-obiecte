package Patrulater;

public abstract class Patrulater {

   protected String nume;

    public  Patrulater(){
        nume = "";
    }

    public Patrulater(String nume){
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public abstract void Aria();
}
