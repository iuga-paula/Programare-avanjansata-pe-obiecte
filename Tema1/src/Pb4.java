import java.util.Scanner;

public class Pb4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float suma = 0;
        float nr = 0;
        int x = 0;

        while(x != -1)
        {
            x = scanner.nextInt();
            if(x != -1){
                suma += x;
                nr ++;

            }
        }
        if(nr == 0){
            System.out.println("You didn't enter a number");
        }
        else {
            System.out.println(suma / nr);
        }
    }
}
