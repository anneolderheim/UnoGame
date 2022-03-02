package prosjekt;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
public class CardDeck {

    private List<Card> deck = new ArrayList<>();
    private List<Character> color = new ArrayList<Character>(Arrays.asList('R', 'B', 'G', 'Y'));


    public CardDeck() {

        for (int j = 0; j<color.size(); j++) {
        for (int i = 0; i < 13; i++) {
            Card card = new Card(color.get(j), i);
            deck.add(card);
            deck.add(card);
          }
        }

        Card actionCard1= new Card(13);
        Card actionCard2 = new Card(14);
        deck.add(actionCard1);
        deck.add(actionCard1);
        deck.add(actionCard2);
        deck.add(actionCard2);

    }

    public int getDeckSize() {
        return deck.size();
    }

    public Card getCard(int n) {
        return deck.get(n);

    }
    public Card removeCard(int n) {
        if (n >= this.getDeckSize()) {
            throw new IllegalArgumentException("det finnes ingen kort med denne indeksen");
        }
        return deck.remove(n);

    }

    public List<Card> getList() {
        return deck; //dette er en usikker løsning. Kan endre lista utenifra
    }

    public void shuffle() {
            Collections.shuffle(deck, new Random());
    }

    public void handOut(Player player) {
            for (int n = 0; n < 7; n++) {
                player.addCard(deck.get(deck.size()-1));
                deck.remove(deck.size()-1);
            }
    }
 
    public static void main(String[] args) {
        CardDeck deck = new CardDeck();
        System.out.println(deck.getDeckSize());
        System.out.println(deck.getList());
        System.out.println(deck.getCard(107));
    
    }
}

