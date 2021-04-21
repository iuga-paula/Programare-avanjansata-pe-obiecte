import java.util.Comparator;

public class ComparaProduseAlfabetic implements Comparator<Produs> {

@Override
public int compare(Produs o1, Produs o2) {

    return o1.getDenumire().compareTo(o2.getDenumire());

}

}
