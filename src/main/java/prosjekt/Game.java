package prosjekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {
    

    private List<Card> onTable = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private CardDeck cardDeck = new CardDeck();


    public void start() {
        cardDeck.shuffle();
        handOut();
        addOnTable();

    }

    public void handOut() {
        for (Player player: players) {
            cardDeck.handOut(player);
        }
       

    }

    public void addOnTable() {
        onTable.add(cardDeck.removeLastCard());
    }

    public Card getTopOnTable() {
        return onTable.get(onTable.size()-1);
    }



    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void drawTwo(Player player, CardDeck deck) {
        player.addCard(deck.removeLastCard());
        player.addCard(deck.removeLastCard());
    
    }

    public void drawFour(Player player, CardDeck deck) {
        drawTwo(player, deck);
        drawTwo(player, deck);
    }

    public void addOnTable(Card card) {
        onTable.add(card);
    }

    //tar inn player-index til den som har sin tur
    public int nextPlayer(int i) {
       
    }

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

    public static void main(String[] args) {
        Game game = new Game();
        game.reverse();
        System.out.println(game.nextPlayer(1));
    }
} 
