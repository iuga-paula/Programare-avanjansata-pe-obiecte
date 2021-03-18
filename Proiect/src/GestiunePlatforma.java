import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class GestiunePlatforma {

    private static GestiunePlatforma instance;

    private GestiunePlatforma()   {
        System.out.println("Bine ati venit la platforma GoodFood GoodLife!");
    }
    private Map<String, Utilizator> users = new Hashtable<String, Utilizator>(); //hasthtable pt userii inregistrati: user[username] = obiectul user care ct hashul de parola;

    public static GestiunePlatforma getInstance() {
        if(instance == null)    {
            instance = new GestiunePlatforma();
        }
        return instance;
    }

    private  Boolean valideazaParola(String parola){
        if(parola.matches(".*[A-Z]+.*") && parola.matches(".*[a-z]+.*") && parola.matches(".*[0-9]+.*"))
            return  true;
        return  false;
    }

    public void inegistrare(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("*Inregistrare utilizator*");
        System.out.println("Intorduceti username");
        String username = scanner.next();
        //caut daca useruname ul e luat deja pt ca e cheie in hash
        if(users.containsKey(username)){
            System.out.println("Username ul deja exista! Repetati operatiunea.");
            return;

        }


        System.out.println("Introduceti parola( cel putin 4 caractere, sa contina cel putin litera mare, litera mica si un numar: ");
        String parola = scanner.next();
        if(!valideazaParola(parola)){
            System.out.println("Parola nu respecta constangerile! Repetati operatiunea.");
            return;
        }
        System.out.println("Introduceti confirmare parola:");
        String confirmare = scanner.next();

        if(!Objects.equals(parola, confirmare)){
            System.out.println("Confrimarea nu e la fel ca parola! Repetati operatiunea.");
            return;

        }

        System.out.println("Felicitari! Acum introduceti datele de contact");
        System.out.println("Introduceti mail:");
        String mail = scanner.next();

        System.out.println("Introduceti ");






    }
}
