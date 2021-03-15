package GestiuneMagazin;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Produs produs1 = new Produs("Zahar", 2.00, 10);
        Produs produs2 = new Produs("Ulei",  3.24, 50);
        Produs produs3 = new Produs("Skyr", 1.5, 20);
        Produs produs4 = new Produs("Miere", 20.00, 10);
        Produs p[] = new Produs[3];
        p[0] = produs1;
        p[1] = produs2;

        System.out.println(Arrays.toString(p)); //apeleaza roString al fiecarui produs

        Magazin m1 = new Magazin();
        m1.setNume("La doi pasi");
        m1.addProdus(produs1);
        m1.addProdus(produs2);

        System.out.println(m1);

        System.out.println("------------");

        Magazin m2 = new Magazin("La Georgel");
        m2.addProdus(produs3);
        m2.addProdus(produs4);
        m2.addProdus(produs2);
        m2.addProdus(produs1);

        System.out.println(m2);






        //Integer VAR;
        //System.out.println(VAR); - err var might not be initialized
        //int var;
        //System.out.println(var); - tot asa
        /*Integer a [] = new Integer[3];
        a[0] = 1000;
        a[1]=200;
        for(int i = 0; i <3;i++){
            System.out.println(a[i]);//a[3] e null
        }

        for ( Integer elem:a) {
            System.out.println(elem);//tot cu null

        }*/


    }
}
