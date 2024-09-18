package prosjekt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {

    private List<Card> onTable = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private int nextTurn = 1;
    private int turn;
    private CardDeck cardDeck = new CardDeck();
    private String onTopColor;

    public Game(ArrayList<Player> players, List<Card> onTable, CardDeck cardDeck, int nextTurn, int turn, String onTopColor) {
        this.players = players;
        this.onTable = onTable;
        this.cardDeck = cardDeck;
        this.nextTurn = nextTurn;
        this.turn = turn;
        this.onTopColor = onTopColor;
    }

    public Game() {} 

    public int getTurn() {
        return turn;
    }

    public int getNextTurn() {
        return nextTurn;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public List<Card> getOnTable() {
        return onTable;
    }

    public Card getTopOnTable() {
        return onTable.get(onTable.size() - 1);
    }

    public String getOnTopColor() {
        return onTopColor;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public Player getCurrentPlayer() {
        return players.get(turn);
    }

    public List<String> getColors() {
        return cardDeck.getColors();
    }

    public void setOnTopColor(String color) {
        onTopColor = color;
    }

    public void addPlayer(Player player) {
        if (players.size() <= 4) {
        players.add(player);}

        else {
            //riktig?
            throw new IllegalStateException();
        }
    }

    public void setPlayerNamesIfEmpty() {
        for (int i= 0; i<getPlayers().size(); i++) {
            if (getPlayers().get(i).getName() == "") {
                getPlayers().get(i).setName("Spiller " + (i+1));
            }
        }
    }

    public boolean isSpecialCard(Card card) {
        if (card.getValue() > 12) {
            return true;
        }

        else {
            return false;
        }
    }

    public void startNewGame() {
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
        if (getTopOnTable().getValue() > 9) {
            handOutOnTable();
        }
    }

    public void playCard(Card card) {
        addOnTable(card);
        players.get(turn).removeCardFromHand(card);;

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

        if (card.getValue() == 14) {
            nextPlayerTurn();
            this.drawFour(getCurrentPlayer());
        }

        nextPlayerTurn();
    }

    public boolean validCard(Card card) {
        if (onTable.size() != 0) {
            Card topp = onTable.get(onTable.size() - 1);

            if (card.getValue() == 13 || card.getValue() == 14) {
                return true;
            }
            if (card.getColor().equals(getOnTopColor())) {
                return true;
            } else if (card.getValue() == (topp.getValue())) {
                return true;
            }

            else {
                return false;
            }
        }
        return true;
    }

    // begerensning: maks 16 kort
    public void drawTwo(Player player) {
       player.drawTwo(getCardDeck());

    }

    public void drawThree(Player player) {
        player.drawThree(getCardDeck());
        nextPlayerTurn();
    }

    public void drawFour(Player player) {
        player.drawFour(getCardDeck());

    }

    public void addOnTable(Card card) {
        onTable.add(card);
    }

    public void nextPlayerTurn() {
        turn += nextTurn;
        if (turn == 4) {
            turn = 0;
        } else if (turn == -1) {
            turn = 3;
        }

    }

    public void reverse() {
        nextTurn *= -1;
    }

    public List<Player> uno() {
        List<Player> playersWithUno = new ArrayList<>();
        for (Player player : players) {
            if (player.getHandSize() == 1) {
                playersWithUno.add(player);
            }
        }

        return playersWithUno;
    }

    public boolean isThereAWinner() {
        for (Player player : players) {
            if (player.getHandSize() == 0) {
                return true;
            }
        }

        return false;
    }

    public void isDeckEmpty() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        Card tempCard = getTopOnTable();

        if (cardDeck.getDeckSize() <= 2) {
            tempDeck.addAll(onTable.subList(0, onTable.size() - 2));
            Collections.shuffle(tempDeck, new Random());
            for (Card card : tempDeck) {
                cardDeck.addCard(card);
            }
            onTable.removeAll(onTable);
            addOnTable(tempCard);
        }
    }

    public List<Player> sortPlayers() {
        Collections.sort(getPlayers(), new CardsOnHandComparator());
        return getPlayers();
    }

    public String resultText() {
        return "Spillet er over!\n1. plass: " + players.get(0).getName() + "\n2. plass: " + players.get(1).getName()
        + "\n3. plass: " + players.get(2).getName() + "\n4. plass: " + players.get(3).getName();
    }

    public static void main(String[] args) {
        Game game = new Game();

        Player player1 = new Player("A");
        Player player2 = new Player("B");
        Player player3 = new Player("C");
        Player player4 = new Player("D");
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        game.startNewGame();
        System.out.println(game.getPlayers().get(1).getHand());

    }

}
