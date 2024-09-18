package prosjekt;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getHandSize() {
        return hand.size();
    }

    public void setName(String name){
        this.name = name;
    }
    
 
    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCardFromHand(Card card) {
        if (hand.contains(card)) {
            hand.remove(card);
        }

        else {
            throw new IllegalArgumentException("Spiller har ikke dette kortet");
        }
    }

      // begerensning: maks 16 kort
      public void drawTwo(CardDeck deck) {
        if (getHandSize() < 15) {
            addCard(deck.removeLastCard());
            addCard(deck.removeLastCard());
        }

    }

    public void drawThree(CardDeck deck) {
        if (getHandSize() < 14) {
            drawTwo(deck);
            addCard(deck.removeLastCard());
        }
    }

    public void drawFour(CardDeck deck) {
        if (getHandSize() < 13) {
            drawTwo(deck);
            drawTwo(deck);
        }
    }

    public static void main(String[] args) {
        Player player = new Player("Anne");
        Card card1 = new Card("Red", 9);
        Card card2 = new Card("Blue",7);
        player.addCard(card1);
        player.addCard(card2);
        System.out.println(player.getHand());
    }
}
