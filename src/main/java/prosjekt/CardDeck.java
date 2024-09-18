package prosjekt;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class CardDeck {

    private List<Card> deck = new ArrayList<>();
    private List<String> color = new ArrayList<String>(Arrays.asList("Red", "Blue", "Yellow", "Green"));

    // lager et kortstokk av en liste med kort man har fra før
    public CardDeck(List<Card> cardDeck) {
        this.deck = cardDeck;

    }

    // lager en helt ny full kortstokk
    public CardDeck() {

        for (int j = 0; j < color.size(); j++) {
            for (int i = 0; i < 13; i++) {
                Card card = new Card(color.get(j), i);
                deck.add(card);
                deck.add(card);
            }
        }

        Card actionCard1 = new Card(13);
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

    public List<Card> getList() {
        List<Card> tmpDeck = new ArrayList<>();
        tmpDeck.addAll(deck);
        return tmpDeck; // dette gjøres for at lista ikke skal kunne endres på fra utsiden
    }

    public List<String> getColors() {
        return color;
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public Card removeCard(int n) {
        if (n >= this.getDeckSize()) {
            // ikke ha throws
            throw new IllegalArgumentException("det finnes ingen kort med denne indeksen");
        }
        return deck.remove(n);

    }

    public Card removeLastCard() {
        Card kort = deck.get(deck.size() - 1);
        deck.remove(kort);
        return kort;

    }

    public void shuffle() {
        Collections.shuffle(deck, new Random());
    }

    public void handOut(Player player) {
        for (int n = 0; n < 7; n++) {
            player.addCard(deck.get(deck.size() - 1));
            deck.remove(deck.size() - 1);
        }
    }
    

    @Override
    public String toString() {
        return "" + deck + "";
    }

    public static void main(String[] args) {
        CardDeck deck = new CardDeck();
        System.out.println(deck);
        deck.shuffle();
        Player player = new Player("Anne");
        deck.handOut(player);
        System.out.println(player.getHand());

    }
}
