package Localuri;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Sofer {

    private  String nume;
    private  String prenume;
    private String tipContract;
    private  Double salariu;
    private String nrTelefon;

    public Sofer(String nume, String prenume, String tipContract, Double salariu, String nrTelefon){
        this.nume = nume;
        this.prenume = prenume;
        this.tipContract = tipContract;
        this.salariu = salariu;
        this.nrTelefon = nrTelefon;

    }

    public Sofer(){
        this("", "", "full-time", 1500.00, "0700000000");

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

    public String getTipContract() {
        return tipContract;
    }

    public void setTipContract(String tipContract) {
        this.tipContract = tipContract;
    }

    public Double getSalariu() {
        return salariu;
    }

    public void setSalariu(Double salariu) {
        this.salariu = salariu;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public  void maresteSalariu(Double procent){
        System.out.println("Se mareste salariul soferului " + this.nume + " de " + this.salariu + " cu " + procent + " la suta");
        this.salariu = this.salariu * procent/100 + this.salariu;
        System.out.println("Salariul nou este: " + this.salariu);

    }

    public void introduDateSofer(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Dati numele soferului:\n");
        String nume = scanner.nextLine();
        if(nume.matches("^[ A-Za-z]+$")){
            this.nume = nume.trim();

            System.out.print("Dati prenumele soferului:\n");
            String prenume = scanner.nextLine();
            if(prenume.matches("^[ A-Za-z]+$")) {
                this.prenume = prenume.trim();

                System.out.print("Dati nr telefon: \n");
                String nr = scanner.next();
                if(nr.matches("[0-9]{10}")) {

                    this.nrTelefon = nrTelefon;

                    System.out.print("Dati tipul contactului: full-time, part-time sau practica \n");
                    String tip = scanner.next();
                    tip = tip.trim();
                    if (Arrays.asList(new String[]{"full-time", "part-time"}).contains(tip)) {
                        this.tipContract = tip;
                        System.out.print("Dati salariul: prag minim 1500.00 lei pentru full-time \n");
                        Double salariu = scanner.nextDouble();


                        if (tip == "full-time" && salariu < 1500.00) {
                            System.out.println("Prag minim depasit! Repetati opreatiunea \n");
                        } else {
                            this.salariu = salariu;
                        }
                    } else {
                        System.out.println("Tip contract gresit! Repetati operatiunea!");
                    }
                }
                else{
                    System.out.println("Nr de telefon gresit! Repetati operatiunea!");
                }



            }
            else{
                System.out.println("prenume incorect!  Repetati operatiunea!");
                return;

            }

        }
        else{
            System.out.println("nume incorect!  Repetati operatiunea!");
            return;
        }


    }

    @Override
    public String toString() {
        return "Localuri.Sofer{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", tipContract='" + tipContract + '\'' +
                ", salariu=" + salariu +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sofer)) return false;
        Sofer sofer = (Sofer) o;
        return Objects.equals(nume, sofer.nume) && Objects.equals(prenume, sofer.prenume) && Objects.equals(tipContract, sofer.tipContract) && Objects.equals(salariu, sofer.salariu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, tipContract, salariu);
    }


}
