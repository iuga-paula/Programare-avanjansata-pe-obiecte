public class Pb3 {
    public static boolean verifica_prim(int nr){

        boolean ok = false;
        for (int i = 2; i <= nr / 2; ++i) {
            if (nr % i == 0) {
                ok = true;
                break;
            }
        }

        if (!ok){
            return true;

        }
        return false;

    }

    public  static void afis(){
        for(int i = 2; i < 50; i++){

            if(verifica_prim(i)){
                System.out.print(i + " ");
            }

        }
    }
    public static void main(String[] args) {
        System.out.println(verifica_prim(230));
        afis();
    }
}
