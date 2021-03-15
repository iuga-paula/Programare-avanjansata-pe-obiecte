import java.util.Random;
import java.util.Scanner;

public class TablouBidimens {

    public static void print(int a[][], int n, int m){
        int s = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) {

                s += a[i][j];
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Sum of all components: " + s);



    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of lines:");
        int n = scanner.nextInt();
        System.out.print("Enter number of columns:");
        int m = scanner.nextInt();

        int a[][] = new int[n][m];
        Random rand = new Random();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                a[i][j] = rand.nextInt(100); //gen nr de la 0 la 100

            }
        }

        print(a,n,m);




    }
}

