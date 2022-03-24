package prosjekt;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> hand = new ArrayList<>();


    public Player(String name) {
        this.name = name;
    }


    public void addCard(Card card) {
        hand.add(card);
    }

    

    public List<Card> getHand() {
        return hand;
    }

    public int getHandSize() {
        return hand.size();
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        

    }
}
