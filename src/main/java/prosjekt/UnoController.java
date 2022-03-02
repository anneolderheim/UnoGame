package prosjekt;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class UnoController {


    @FXML private TextArea feedback, player2Cards,player3Cards,player4Cards;

    @FXML private TextField playerOne,playerTwo,playerThree,playerFour;

    @FXML private GridPane player1Cards;

    @FXML
    public void onStart() {
        feedback.setText("Spillet har startet!");

    }

    private List<Player> players = new ArrayList<>();

    @FXML
    public void initialize() {
        Player player1 = new Player(playerOne);
        Player player2 = new Player(playerTwo);
        Player player3 = new Player(playerThree);
        Player player4 = new Player(playerFour);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        Game game = new Game();
        CardDeck cardDeck = new CardDeck();
        for (Player player: players) {
            cardDeck.handOut(player);
            for (int i = 0; i<getHand(player).size();i++) {
                Card card = getHand(player).get(i);
                player1Cards.add(createCardButton(card), i%4, i/3);
            }
        }

        
  
}

private List<Card> getHand(Player player) {
    return player.getHand();

}

private Button createCardButton(Card card) {
    Button button = new Button(card.toString());
    button.setOnAction((event) -> {
        playCardSelect(card);
    });
    return button;
}

@FXML
public void updateHand() {
    for (Player player: players) {
        player.getHand();
    }

}
