package Pers;

import Pers.Gen;

import java.util.Objects;

public class Persoana {
    private String nume;
    private String prenume;

    private int varsta;
    private long cod_numeric;
    Gen gen;

    public Persoana(String nume, String prenume, int varsta, long cod_numeric, Gen gen){
        this.nume = nume;
        this.prenume = prenume;
        this.cod_numeric = cod_numeric;
        this.varsta = varsta;
        this.gen = gen;

    }

    public Persoana(){
        //new Persoana("mihai","",0,0, Gen.M); // face un ob local
       this("mihai","",0,0, Gen.M);

    }

    public String getNume() {
        return nume;
    }

    public Gen getGen() {
        return gen;
    }

    public long getCod_numeric() {
        return cod_numeric;
    }

    public String getPrenume() {
        return prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setCod_numeric(long cod_numeric) {
        this.cod_numeric = cod_numeric;
    }

    public void setGen(Gen gen) {
        this.gen = gen;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", varsta=" + varsta +
                ", cod_numeric=" + cod_numeric +
                ", gen=" + gen +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persoana persoana = (Persoana) o;
        return varsta == persoana.varsta && cod_numeric == persoana.cod_numeric && Objects.equals(this.nume,persoana.nume) && Objects.equals(this.prenume,persoana.prenume) && gen == persoana.gen;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, varsta, cod_numeric, gen);
    }

    public static void main(String[] args) {
        Persoana p1 = new Persoana();
        Persoana p = p1;
        Persoana p2 = new Persoana("Popa", "Antonia Ileana", 20, 20293293, Gen.F);
        System.out.println(p1.getNume());
        System.out.println(p1);
        System.out.println(p1.hashCode());
        System.out.println(p2);
        System.out.println(p2.hashCode());

        System.out.println(p1.equals(p));
        System.out.println(p1.equals(p2));


    }



}
