import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Pb2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number1: ");
        int n1 = scanner.nextInt();

        System.out.print("Enter number2: ");
        int n2 = scanner.nextInt();

        System.out.print("Enter number3: ");
        int n3 = scanner.nextInt();

        Vector vec = new Vector(3);
        vec.addElement(n1);
        vec.addElement(n2);
        vec.addElement(n3);

        boolean ok = false;

        Collections.sort(vec);
        for(int i = 0; i < vec.size() - 1; i++){
            if(vec.get(i) == vec.get(i+1)){
                if(ok == true)
                {
                    System.out.print( " = " + vec.get(i + 1));
                }
                else {
                    System.out.print(vec.get(i) + " = " + vec.get(i + 1));
                    ok = true;
                }


            }
            else{
                if(ok == true){
                    System.out.print(" < " + vec.get(i + 1));

                }
                else {
                    System.out.print(vec.get(i) + " < " + vec.get(i + 1));
                    ok = true;
                }

            }

        }


    }
}
