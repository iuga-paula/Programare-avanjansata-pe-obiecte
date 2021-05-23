package ComparaProduse;

import Meniu.Produs;

import java.util.Comparator;


public class ComparaProduseCresc implements Comparator<Produs> {

    @Override
    public int compare(Produs o1, Produs o2) {
        return o1.getPret().compareTo(o2.getPret());
    }
}
