import java.sql.Time;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Local {

    private  String denumire;
    private Time timpExecutieComanda;
    private  Time timpLivrare;
    private static long nrAngajati;
    private  Adresa adresa;//agregare => adresa exista si dupa distrugerea obiectului de tip local
    private Set<Produs> produse;//fiecare local are o lista de produse => un  set (nu poate contine acelasi produs de mai multe ori)


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

    public void oferaDiscount(Produs produs, Double discount){
        if(produse.contains(produs)){
            produse.remove(produs);
            Double pretInitial = produs.getPret();
            Double pret = produs.getPret() - discount*1/produs.getPret();
            produs.setPret(pret);
            produse.add(produs);
            produs.setPret(pretInitial);
        }
        else{
            System.out.println("Localul nu ofera acest produs!");
        }
    }

    public void afiseazaProduse(){
        System.out.println("Produsele localului" + denumire + " sunt:");
       for(Produs p : produse){
           if(p instanceof Desert){
               System.out.println("*Desert* ");
           }
           else if(p instanceof  Bauturi){
               System.out.println("*Bauturi* ");
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
}
