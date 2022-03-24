package prosjekt;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


public class Game {

    private List<Card> onTable = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private int nextTurn = 1;
    private int turn;
    private CardDeck cardDeck = new CardDeck();
    private String onTopColor;


    public Game(ArrayList<Player> players,List<Card> onTable, CardDeck cardDeck, int nextTurn,int turn) {
        this.players = players;
        this.onTable = onTable;
        this.cardDeck = cardDeck;
    }

    public Game() {

    }

   

    public int getTurn() {
        return turn;
    }

    public void setOnTopColor(String color) {
        onTopColor = color;
    }

    public String getOnTopColor() {
    return onTopColor;        
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public List<Card> getOnTable() {
        return onTable;
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
        System.out.println(getTopOnTable());
        if (getTopOnTable().getValue() > 9) {
            handOutOnTable();
        }
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
    //begerensning: maks 16 kort
    public void drawTwo(Player player) {
        if (player.getHandSize()< 15) {
        player.addCard(cardDeck.removeLastCard());
        player.addCard(cardDeck.removeLastCard());
        }

    }

    public void drawThree(Player player) {
        if (player.getHandSize()< 14) {
        drawTwo(player);
        player.addCard(cardDeck.removeLastCard());
        }
        nextPlayerTurn();
    }

    public void drawFour(Player player) {
        if (player.getHandSize()< 13) {
        drawTwo(player);
        drawTwo(player); }
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
        turn += nextTurn;
        if (turn == 4) {
            turn = 0;
        }
        else if (turn == -1) {
            turn = 3;
        }
        
    }

    public void reverse() {
        nextTurn *= -1;
    }

    public void playCard(Card card) {
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

    public boolean isThereAWinner() {
        for (Player player: players) {
            if (player.getHandSize() == 0) {
                return true;
            }
        }

        return false;
    }

    public void isOnTableEmpty() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        Card tempCard = getTopOnTable();

        if (cardDeck.getDeckSize() <= 2) {
            tempDeck.addAll(onTable.subList(0, onTable.size()-2)); 
            Collections.shuffle(tempDeck, new Random());
            for (Card card: tempDeck) {
                cardDeck.addCard(card);
            }
            onTable.removeAll(onTable);
            addOnTable(tempCard);
        }
    }

    public List<Player> gameOver() {
        Collections.sort(getPlayers(), new CardsOnHandComparator());
        return getPlayers();
    }

    public List<Player> uno() {
        List<Player> playersWithUno = new ArrayList<>();
        for (Player player: players) {
            if (player.getHandSize() == 1) {
                playersWithUno.add(player);
            }
        }

        return playersWithUno;
    }

    public String resultString() {
        Date today = Calendar.getInstance().getTime();
        String result = today + """
                
                -----------------------------------------------
                Rangering   Navn                  Antall kort
                """;
        for (int i = 0; i<players.size();i++) {
                result += String.format("%s\t%.2s\t%d\t",
                        i + ".plass", 
                        players.get(i).toString(),
                        players.get(i).getHandSize());
            }
        
        result += String.format(
                """
                        -----------------------------------------------
                                                          
                        """
                );
        return result;
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
        game.start();
        System.out.println(game.getPlayers().get(1).getHand());
        
    
}

    public int getNextTurn() {
        return nextTurn;
    } }
