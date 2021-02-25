import java.util.Scanner;

public class Pb1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number:");

        int n = scanner.nextInt();


        for (int i = 0; i < n; i++) {
            if(i % 2 != 0 && i % 5 == 0){//if(assertThat(i % 5).isEqualTo(0) && !assertThat(i % 2).isEqualTo(0)){
                System.out.print(i + " ");
            }
        }
    }
}
