package prosjekt;



import java.util.Comparator;

public class  CardsOnHandComparator implements Comparator<Player>{

    @Override
    public int compare(Player o1, Player o2) {
        return o1.getHandSize()-o2.getHandSize();
    }
    
}
