package prosjekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResultHandler {

    public Game readResult(String filename) throws FileNotFoundException {

        int lineNumber = 0;
        List<Card> tmpCardDeck = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();
        List<Card> onTable = new ArrayList<>();
        int turn = 0;
        int nextTurn = 0;

        File myObj = new File(filename);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String[] data = myReader.nextLine().split(";");
            if (lineNumber < 4) {
                Player player = new Player(data[1]);
                players.add(player);
                String[] cardHand = data[2].split(",");
                for (int i = 0; i < cardHand.length; i++) {
                    String[] cards = cardHand[i].split(":");
                    System.out.println(cards[0]);
                    Card card;
                    if (!cards[0].equals("null")) {
                        card = new Card(cards[0], Integer.parseInt(cards[1]));
                    } else {
                        card = new Card(Integer.parseInt(cards[1]));
                    }
                    player.addCard(card);
                }
            } 
            
            
            else if (lineNumber == 5) {
                String[] cardsOnTable = data[1].split(",");
                for (int i = 0; i < cardsOnTable.length; i++) {
                    String[] cards = cardsOnTable[i].split(":");
                    Card card;
                    if (!cards[0].equals("null")) {
                        card = new Card(cards[0], Integer.parseInt(cards[1]));
                    } else {
                        card = new Card(Integer.parseInt(cards[1]));
                    }
                    onTable.add(card);
                }
            }

            else if (lineNumber == 6) {
                System.out.println("DATA: " + data[1]);
                String[] cardsOnTable = data[1].split(",");
                for (int i = 0; i < cardsOnTable.length; i++) {
                    String[] cards = cardsOnTable[i].split(":");
                    Card card;
                    System.out.println("kort: " + cards);
                    if (!cards[0].equals("null")) {
                        card = new Card(cards[0], Integer.parseInt(cards[1]));
                    } else {
                        card = new Card(Integer.parseInt(cards[1]));
                    }

                    tmpCardDeck.add(card);
                }
            } 
            
            else if (lineNumber == 7) {
                turn = Integer.parseInt(data[1]);

            } 
            
            else if (lineNumber == 8) {
                nextTurn = Integer.parseInt(data[1]);

            }

            lineNumber++;
        }
        CardDeck cardDeck = new CardDeck(tmpCardDeck);
        Game game = new Game(players, onTable, cardDeck, nextTurn, turn);
        return game;
    }

    public void writeResult(Game game) throws IOException {
        File file = new File("test.txt");

        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < game.getPlayers().size(); i++) {
            fw.write(i + ";" + game.getPlayers().get(i).getName() + ";");
            for (int j = 0; j < game.getPlayers().get(i).getHandSize(); j++) {
                fw.write(game.getPlayers().get(i).getHand().get(j).getColor() + ":"
                        + game.getPlayers().get(i).getHand().get(j).getValue());
                if (j != game.getPlayers().get(i).getHandSize() - 1) {
                    fw.write(",");
                }
            }
            fw.write("\n");
        }
        fw.write("Cards on table;");
        for (int k = 0; k < game.getOnTable().size(); k++) {
            fw.write(game.getOnTable().get(k).getColor() + ":" + game.getOnTable().get(k).getValue());
            if (k != game.getOnTable().size() - 1) {
                fw.write(",");
            }
        }
        fw.write("\n");

        fw.write("Carddeck;");
        for (int l = 0; l < game.getCardDeck().getList().size(); l++) {
            fw.write(
                    game.getCardDeck().getList().get(l).getColor()
                            +
                            ":"
                            + game.getCardDeck().getList().get(l).getValue());
            if (l != game.getCardDeck().getList().size() - 1) {
                fw.write(",");
            }

        }
        fw.write("\n");
        fw.write("turn;" + game.getTurn() + "\n");
        fw.write("Next turn;" + game.getNextTurn() + "\n");

        fw.close();

    }

    public static void main(String[] args) throws IOException {
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
        ResultHandler print = new ResultHandler();
        print.writeResult(game);
        Game game1 = print.readResult("test.txt");

        System.out.println(game1.getPlayers());
        System.out.println(game1.getCardDeck().getList());

    }

}
