package prosjekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    

    private List<Card> onTable = new ArrayList<>();
    private List<Integer> turn = new ArrayList<Integer>(Arrays.asList(1,2,3,4));


    public void drawTwo(Player player, CardDeck deck) {
        player.addCard(deck.getCard(deck.getDeckSize()-1));
        deck.removeCard(deck.getDeckSize()-1);
        player.addCard(deck.getCard(deck.getDeckSize()-1));
        deck.removeCard(deck.getDeckSize()-1);
    }

    public void drawFour(Player player, CardDeck deck) {
        drawTwo(player, deck);
        drawTwo(player, deck);
    }

    public void addOnTable(Card card) {
        onTable.add(card);
    }

    public void 

    public void reverse() {

    }

    public boolean validCard(Card card) {
        Card topp = onTable.get(onTable.size()-1);
        if (card.getValue() == 13 || card.getValue() == 14) {
            return true;
        }
        else if (card.getColor().equals(topp.getColor()) {
            return true;
        }
        else if (card.getValue().equals(topp.getValue()) {
            return true;
        }

        else {
            return false;
        }
    }
} 
