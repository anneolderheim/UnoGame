package prosjekt;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;

public class Player {

    private String name;
    private List<Card> hand = new ArrayList<>();


    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void playCard(Game game, Card card) {
        game.addOnTable(card);
        hand.remove(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getHandSize() {
        return hand.size();
    }

    public static void main(String[] args) {
        Card card = new Card('R', 9);
        

    }
}
