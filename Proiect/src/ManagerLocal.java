public class ManagerLocal extends Utilizator{

    Local local;//compozitie => localul nu mai exista dupa distrugerea obiectului de tip manager;
    String codAutorizatie;


    public ManagerLocal(Local l, String codAutorizatie, String nume, String prenume, String email){
        local = new Local(l);
        this.codAutorizatie = codAutorizatie;
        this.email = email;
        this.nume = nume;
        this.prenume = prenume;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public String getCodAutorizatie() {
        return codAutorizatie;
    }

    public void setCodAutorizatie(String codAutorizatie) {
        this.codAutorizatie = codAutorizatie;
    }

    @Override
    public String toString() {
        return "ManagerLocal{" +
                "local=" + local +
                ", codAutorizatie='" + codAutorizatie + '\'' +
                '}';
    }
}
