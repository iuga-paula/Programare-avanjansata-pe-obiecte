import java.util.Objects;

public class Bauturi extends  Produs{

    private Boolean alcool;
    private Boolean carbogazificate;

    public  Bauturi(Boolean alcool, Boolean carbogazificate, String denumire, Double pret){
        super(denumire,pret);
        this.alcool = alcool;
        this.carbogazificate = carbogazificate;

    }

    public Bauturi(){
        this(false, false, "", 0.00);


    }

    @Override
    public Double getPret() {
        return pret;
    }

    @Override
    public void setPret(Double pret) {
        this.pret = pret;
    }

    public Boolean getAlcool() {
        return alcool;
    }

    public void setAlcool(Boolean alcool) {
        this.alcool = alcool;
    }

    public Boolean getCarbogazificate() {
        return carbogazificate;
    }

    public void setCarbogazificate(Boolean carbogazificate) {
        this.carbogazificate = carbogazificate;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder(this.denumire);

        if(alcool)
        {
            str.append(" cu alcool");
        }
        else {
            str.append(" fara alcool");
        }

        if(carbogazificate){
            str.append(" carbogazificata");
        }
        else{
            str.append(" necarbogazoasa");
        }

        return str + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bauturi)) return false;
        if (!super.equals(o)) return false;
        Bauturi bauturi = (Bauturi) o;
        return Objects.equals(alcool, bauturi.alcool) && Objects.equals(carbogazificate, bauturi.carbogazificate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), alcool, carbogazificate);
    }
}

