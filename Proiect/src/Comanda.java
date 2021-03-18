import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Comanda {


    private Integer IdComanda;
    private Double total;
    private Double discount;

    private  Local local;//compozitie
    private  Sofer sofer;//agregare
    private List<Produs> produse;


    private static Integer idCounter = 0;

    {
        idCounter++;
    }

    public Comanda(Double discount, Local local) {

        this.total = 0.00;
        this.discount = discount;
        this.local = local;
        IdComanda = idCounter;

        produse = new ArrayList<Produs>();
    }

    public Comanda() {
        this(0.00, new Local());
    }

    public Comanda(Comanda c) {
        this.total = c.total;
        this.discount = c.discount;
        this.local = c.local;
        this.IdComanda = c.IdComanda;
        this.produse = c.produse;
        this.sofer = c.sofer;
        this.local = c.local;

    }


    public  void finalizeaza(Sofer sofer) {
        if (total != 0.00) {
            this.sofer = sofer;

            System.out.println("*Comanda cu nr. " + IdComanda + " de la " + local.getDenumire() + " plasata*");
            total = total - 1 / total * discount;
            System.out.println("Total: " + total);
            System.out.println("Timp estimat livrare: " + (TimeUnit.MILLISECONDS.toMinutes((local.getTimpExecutieComanda()).getTime()) + TimeUnit.MILLISECONDS.toMinutes((local.getTimpExecutieComanda()).getTime())) + " minute");
            System.out.println("Sofer: " + sofer.getNume() + " " + sofer.getPrenume() + " Telefon:" + sofer.getNrTelefon());
            System.out.println("Produse: ");
            for (Produs p : produse) {
                System.out.println(p);
            }

            System.out.println("***********************************");
        }
        else {
            System.out.println("Nu aveti produse in cos!");
        }
    }

    public  void finalizeaza2() {//cu ridicare personala
        if (total != 0.00) {
            System.out.println("*Comanda cu nr. " + IdComanda + " de la " + local.getDenumire() + " plasata*");
            total = total - 1 / total * discount;
            System.out.println("Total: " + total);
            System.out.println("Timp estimat livrare: " + (TimeUnit.MILLISECONDS.toMinutes((local.getTimpExecutieComanda()).getTime()) + TimeUnit.MILLISECONDS.toMinutes((local.getTimpExecutieComanda()).getTime())) + " minute");
            System.out.println("Produse: ");
            for (Produs p : produse) {
                System.out.println(p);
            }

            System.out.println("***********************************");
        }
        else{
            System.out.println("Nu aveti produse in cos!!");
        }

    }


    public void adaugaProdus(Produs p){
        if(!(local.getProduse()).contains(p)){
            System.out.println("Produs de la alt local! Adaugati un local nou");
        }
        else{
            produse.add(p);
            total += p.getPret();
        }

    }

    public void stergeToateProdusele(){
        produse.clear();
    }

    public void stergeProdus(Produs p){
        if(!(local.getProduse()).contains(p)){
            System.out.println("Produsul nu exista in comanda curenta");
        }
        else{
            produse.remove(p);
            total -= p.getPret();
        }
    }

    public Integer getIdComanda() {
        return IdComanda;
    }

    public void setIdComanda(Integer idComanda) {
        IdComanda = idComanda;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
        //stergem si produsele;
        this.stergeToateProdusele();
    }

    public Sofer getSofer() {
        return sofer;
    }

    public void setSofer(Sofer sofer) {
        this.sofer = sofer;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }
}
