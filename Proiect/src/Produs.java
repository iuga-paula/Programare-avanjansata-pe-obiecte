import java.util.Objects;

public abstract class Produs {

    private Integer Id;
    private static Integer idCounter = 0;
    protected String denumire;
    protected Double pret;

    public  Produs(String denumire, Double pret){

        this.denumire = denumire;
        this.pret = pret;
        this.Id = idCounter;
    }

    public Produs(){

        this("", 0.00);

    }

  {

        idCounter++;

    }

    public Integer getId() {
        return Id;
    }

    public abstract Double  getPret();
    public  abstract void setPret(Double pret);



    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "Id=" + Id +
                ", denumire='" + denumire + '\'' +
                ", pret=" + pret +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produs)) return false;
        Produs produs = (Produs) o;
        return Id.equals(produs.Id) && Objects.equals(denumire, produs.denumire) && Objects.equals(pret, produs.pret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, denumire, pret);
    }

}
