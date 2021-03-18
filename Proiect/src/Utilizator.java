import java.util.concurrent.TimeUnit;

public class Utilizator {
    private Integer Id;
    private String username;
    private String parola;
    private String rol;
    protected  String email;
    protected  String nume;
    protected  String prenume;

    private static Integer idCounter;

    static {
        idCounter = 0;
    }

    {
        idCounter ++;
    }

    public Utilizator(String username, String parola, String rol, String email, String nume, String prenume){

        this.username = username;
        this.parola = parola;
        this.rol = rol;
        this.email = email;
        this.nume = nume;
        this.prenume = prenume;

        Id = idCounter;

    }

    public Utilizator(){
        this("","", "user normal", "", "", "");
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getRol() {
        return rol;
    }

    protected void setRol(String rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                ", rol='" + rol + '\'' +
                ", email='" + email + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                '}';
    }


}
