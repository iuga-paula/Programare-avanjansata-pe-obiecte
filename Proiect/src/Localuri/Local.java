package Localuri;

import ComparaProduse.ComparaProduseAlfabetic;
import ComparaProduse.ComparaProduseCresc;
import ComparaProduse.ComparaProduseDesc;
import Meniu.Bauturi;
import Meniu.Desert;
import Meniu.Produs;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Local implements Comparable<Local> {

    private  String denumire;
    private Time timpExecutieComanda;
    private  Time timpLivrare;
    private static long nrAngajati;
    private  Adresa adresa;//agregare => adresa exista si dupa distrugerea obiectului de tip local
    private Set<Produs> produse;//fiecare local are o lista de produse => un  set (nu poate contine acelasi produs de mai multe ori)
    private Scanner scanner = new Scanner(System.in);


    public  Local(Adresa adresa, String denumire, Time timpExecutieComanda, Time timpLivrare){
        this.timpExecutieComanda = timpExecutieComanda;
        this.denumire = denumire;
        this.timpLivrare = timpLivrare;

        this.adresa = adresa;
        produse = new HashSet<Produs>();

    }

    public Local(){
        this(new Adresa(),"", new Time(0), new Time(0));
        //this.timpExecutieComanda = new Time(0); //new Time(0, 50, 0); - deprecated

    }

    static {
        nrAngajati = 0;
    }

    public Local(Local l) {
        this.denumire = l.denumire;
        this.adresa = l.adresa;
        this.produse = l.produse;
        this.timpLivrare = l.timpLivrare;
        this.timpExecutieComanda = l.timpExecutieComanda;
        this.scanner = l.scanner;

    }

    static void addAngajat(){
        nrAngajati++;
    }

    public void addProdus(Produs produs){
        produse.add(produs);
    }

    public void stergeProdus(Produs produs){
        if(!produse.remove(produs)){
            System.out.println("Nu exista acest produs in localul " + denumire + "!");

        }

    }

    public void oferaDiscount(Produs produs, Double discount) throws CloneNotSupportedException {
        if(produse.contains(produs)){
            produse.remove(produs);

            Produs produs1 = (Produs) produs.clone();
            Double pret = produs.getPret() - discount*1/produs.getPret();
            produs1.setPret(pret);
            produse.add(produs1);

        }
        else{
            System.out.println("Localul nu ofera acest produs!");
        }
    }

    public void afiseazaProduse(){
        System.out.println("Setati filtru pt algerea produsului: \n 1. alfabetic, 2. pret crescator, 3. pret descrescator");
        int optiune = scanner.nextInt();

        Produs[] listaProduse = new Produs[produse.size()];
        produse.toArray(listaProduse); //fac array din produse pt a le putea sorta

        switch (optiune){
           case 1:
               Arrays.sort(listaProduse, new ComparaProduseAlfabetic());
               System.out.println("--Afisare alfabetic--");
               break;
           case 2:
                Arrays.sort(listaProduse, new ComparaProduseCresc());
                System.out.println("--Afisare pret crescator--");
                break;

           case 3:
                Arrays.sort(listaProduse, new ComparaProduseDesc());
                System.out.println("--Afisare pret descrescator--");
                break;

           default:
                System.out.println("--Fara filtru---");
                break;

        }

        System.out.println("Produsele localului " + denumire + " sunt:");
       for(Produs p : listaProduse){
           if(p instanceof Desert){
               System.out.println("*Meniu.Desert* ");
           }
           else if(p instanceof Bauturi){
               System.out.println("*Meniu.Bauturi* ");
           }
           else{
               System.out.println("*Fel Principal* ");

           }
           System.out.print(p);
           System.out.println("-" + p.getPret() + "lei");
       }

    }

    public static void setNrAngajati(long nrAngajati) {
        Local.nrAngajati = nrAngajati;
    }

    public static long getNrAngajati() {
        return nrAngajati;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDenumire() {
        return denumire;
    }

    public Time getTimpExecutieComanda() {
        return timpExecutieComanda;
    }

    public void setTimpExecutieComanda(Time timpExecutieComanda) {
        this.timpExecutieComanda = timpExecutieComanda;
    }

    public Time getTimpLivrare() {
        return timpLivrare;
    }

    public void setTimpLivrare(Time timpLivrare) {
        this.timpLivrare = timpLivrare;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Set<Produs> getProduse() {
        return produse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Local)) return false;
        Local local = (Local) o;
        return Objects.equals(denumire, local.denumire) && Objects.equals(timpExecutieComanda, local.timpExecutieComanda) && Objects.equals(timpLivrare, local.timpLivrare) && Objects.equals(adresa, local.adresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denumire, timpExecutieComanda, timpLivrare, adresa);
    }

    @Override
    public String toString() {
        return "Localul cu denumirea: " + denumire +
                ", are un timp de executie comanda " + TimeUnit.MILLISECONDS.toMinutes(timpExecutieComanda.getTime()) + " minute " +
                " si un timp de livrare " + TimeUnit.MILLISECONDS.toMinutes(timpLivrare.getTime()) + "minute\n" +
                "localul " + adresa;
    }

    @Override
    public int compareTo(Local o) {
        return this.getDenumire().compareTo(o.getDenumire());
    }
}
