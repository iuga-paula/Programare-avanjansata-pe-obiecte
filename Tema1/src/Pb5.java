public class Pb5 {
    public static long putere(int p, int nr){
        int rez = 1;
        while(p != 0){
            rez *= nr;
            p--;
        }
        return rez;
    }

    public static void afisare(){

        System.out.println(putere(4,2));

    }
    public static void main(String[] args) {
    afisare();
    }
}
