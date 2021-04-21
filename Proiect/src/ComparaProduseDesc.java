import java.util.Comparator;

public class ComparaProduseDesc implements Comparator<Produs> {

    @Override
    public int compare(Produs o1, Produs o2) {
        return (int) (o2.getPret()- o1.getPret());
    }
}

