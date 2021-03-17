import java.util.Objects;

public class Adresa {

    private  String strada;
    private Integer nr;
    private Integer bloc;
    private String scara;
    private Integer apartament;
    private String localitate;
    private String judet;
    private Integer sector;
    private String codPostal;

    public Adresa(String strada, Integer nr, Integer bloc, String scara, Integer apartament, String localitate, String judet, Integer sector, String codPostal){
        this.strada = strada;
        this.nr = nr;
        this.bloc = bloc;
        this.scara = scara;
        this.apartament = apartament;
        this.localitate = localitate;
        this.judet = judet;
        this.sector = sector;
        this.codPostal = codPostal;

    }

    public Adresa(){

    }

    public Integer getBloc() {
        return bloc;
    }

    public void setBloc(Integer bloc) {
        this.bloc = bloc;
    }

    public Integer getApartament() {
        return apartament;
    }

    public void setApartament(Integer apartament) {
        this.apartament = apartament;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getLocalitate() {
        return localitate;
    }


    public void setLocalitate(String localitate) {
        this.localitate = localitate;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getScara() {
        return scara;
    }

    public void setScara(String scara) {
        this.scara = scara;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public Integer getNr() {
        return nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    public Integer getSector() {
        return sector;
    }

    public void setSector(Integer sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "Se gaseste la adresa{" +
                "strada='" + strada + '\'' +
                ", nr=" + nr +
                ", bloc=" + bloc +
                ", scara='" + scara + '\'' +
                ", apartament=" + apartament +
                ", localitate='" + localitate + '\'' +
                ", judet='" + judet + '\'' +
                ", sector='" + sector + '\'' +
                ", codPostal=" + codPostal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adresa)) return false;
        Adresa adresa = (Adresa) o;
        return Objects.equals(strada, adresa.strada) && Objects.equals(nr, adresa.nr) && Objects.equals(bloc, adresa.bloc) && Objects.equals(scara, adresa.scara) && Objects.equals(apartament, adresa.apartament) && Objects.equals(localitate, adresa.localitate) && Objects.equals(judet, adresa.judet) && Objects.equals(sector, adresa.sector) && Objects.equals(codPostal, adresa.codPostal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strada, nr, bloc, scara, apartament, localitate, judet, sector, codPostal);
    }
}

