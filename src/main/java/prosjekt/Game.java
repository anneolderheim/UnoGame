package prosjekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;

public class Game {

    private List<Card> onTable = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private int NextTurn = 1;
    private int turn;
    private CardDeck cardDeck = new CardDeck();
    private int topCardValue;
    private char topCardColor;

    public int getTurn() {
        return turn;
    }

    public Player getCurrentPlayer() {
        return players.get(turn);
    }

    public void start() {
        cardDeck.shuffle();
        handOut();
        handOutOnTable();

    }

    public void handOut() {
        for (Player player : players) {
            cardDeck.handOut(player);
        }

    }

    public void handOutOnTable() {
        onTable.add(cardDeck.removeLastCard());
    }

    public Card getTopOnTable() {
        return onTable.get(onTable.size() - 1);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void drawTwo(Player player) {
        player.addCard(cardDeck.removeLastCard());
        player.addCard(cardDeck.removeLastCard());

    }

    public void drawThree(Player player) {
        System.out.println("trekker tre");
        drawTwo(player);
        player.addCard(cardDeck.removeLastCard());
        nextPlayerTurn();

    }

    public void drawFour(Player player) {
        drawTwo(player);
        drawTwo(player);
    }

    public void addOnTable(Card card) {
        onTable.add(card);
    }

    public void changeColor() {

    }

    public Player currentPlayer() {
        return players.get(turn);
    }

    public void nextPlayerTurn() {
        turn += NextTurn;
        if (turn == 4) {
            turn = 0;
        }
        else if (turn == -1) {
            turn = 3;
        }
        System.out.println(turn);
    }

    public void reverse() {
        NextTurn *= -1;
    }

    public void playCard(Card card) {
        System.out.println("legg på kort");
        addOnTable(card);
        players.get(turn).getHand().remove(card);

        if (card.getValue() == 10) {
            nextPlayerTurn();
        }

        if (card.getValue() == 11) {
            reverse();
        }

        if (card.getValue() == 12) {
            nextPlayerTurn();
            this.drawTwo(getCurrentPlayer());
        }

        if (card.getValue() == 13) {
            System.out.println("bytt farge");
        }

        if (card.getValue() == 14) {
            nextPlayerTurn();
            this.drawFour(getCurrentPlayer());
        }

        nextPlayerTurn();
    }

    public boolean validCard(Card card) {
        if (onTable.size() != 0) {
            Card topp = onTable.get(onTable.size() - 1);
            System.out.println(topp);
            System.out.println(topp.getColor());
            System.out.println(card.getColor());
            if (card.getValue() == 13 || card.getValue() == 14) {
                return true;
            } 
            if (card.getColor().equals(topp.getColor())) {
                return true;
            } 
            else if (card.getValue() == (topp.getValue())) {
                return true;
            }

            else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Game game = new Game();
        CardDeck cardeck = new CardDeck();
        Player player1 = new Player("A");
        Player player2 = new Player("B");
        Player player3 = new Player("C");
        Player player4 = new Player("D");
        game.drawThree(player1);
        System.out.println(player1.getHand());

    }
}
