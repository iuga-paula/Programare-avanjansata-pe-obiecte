package Patrulater;

public class Main {
    public static void main(String[] args) {
        Patrat p = new Patrat(10.00);
        System.out.println(p.getNume());
        p.Aria();

        Dreptunghi d = new Dreptunghi(3.10,4.10);
        System.out.println(d.getNume());
        d.Aria();

        Romb r = new Romb(2.00,12.01);
        System.out.println(r.getNume());
        r.Aria();

        Paralelogram paralelogram = new Paralelogram(6.7,7.8);
        System.out.println(paralelogram.getNume());
        paralelogram.Aria();


    }
}
