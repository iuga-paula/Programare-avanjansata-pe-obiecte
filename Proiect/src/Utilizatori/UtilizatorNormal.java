package Utilizatori;

import Localuri.Adresa;
import Localuri.Comanda;
import Localuri.Local;

import java.util.*;

public class UtilizatorNormal extends Utilizator{

    private  Adresa adresa;//agregare => adresa exista si dupa distrugerea obiectului de tip utlizator normal;
    private Comanda comandaInCurs;//compozitie => comanda nu mai exista dupa distrugerea userului;

    List<Comanda> istoricComenzi;
    Set<Local> preferinte;//un local nu poate aparea de mai multe ori in preferinte

    public  UtilizatorNormal(Comanda c, Adresa a, String nume, String prenume, String email){
        this.adresa = a;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;

        this.comandaInCurs = new Comanda(c);


        this.setRol("utilizator normal");
        istoricComenzi = new ArrayList<Comanda>(); //ArrayList implementeaza List care extinde collection
        preferinte = new HashSet<Local>();



    }

    public UtilizatorNormal(){
        comandaInCurs = new Comanda();
        istoricComenzi = new ArrayList<Comanda>(); //ArrayList implementeaza List care extinde collection
        preferinte = new HashSet<Local>();

    }



    public void afiseazaPreferinte(){

        System.out.println("Lista cu preferintele este: ");
        if(preferinte == null || preferinte.size() == 0){
            System.out.println("Inca nu ati adauga preferinte.");
            return;
        }
        for(Local l :preferinte){
            System.out.println(l);
        }

    }

    public void afiseazaIstoricComenzi(){
        System.out.println("Istoricul comenzilor este:");

        if(istoricComenzi != null) {
            for (int i = 0; i < istoricComenzi.size(); i++) {
                System.out.println("Localuri.Comanda cu nr: " + (i + 1));
                System.out.println(istoricComenzi.get(i));
            }
        }
        else{
            System.out.println("Nu ati comandat nimic inca.");
        }
    }

    public  void repetaComanda(Integer nr){
        if(nr > istoricComenzi.size()){
            System.out.println("Nu exista aceasta comanda");
        }
        else{
            this.comandaInCurs = istoricComenzi.get(nr-1);
            //comandaInCurs.finalizeaza(un ob sofer);

        }

    }

    public void adaugaPreferinta(Local local){
        preferinte.add(local);
    }


    public void stergePreferinta(Local local){
        Boolean ok = false;
        if(preferinte.contains(local)){
            preferinte.remove(local);
        }
        else{
            System.out.println("Nu exista acest local in preferinte!");
        }



    }

    public Comanda getComandaInCurs() {
        return comandaInCurs;
    }

    public void setComandaInCurs(Comanda comandaInCurs) {
        this.comandaInCurs = comandaInCurs;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public void adaugaComandaIstoric(){
        this.istoricComenzi.add(this.comandaInCurs);
    }

    //adauga comanda mare in manager


}
