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

    @FXML
    private TextArea feedback, onTable;

    @FXML
    private TextField playerOne, playerTwo, playerThree, playerFour;

    @FXML
    private GridPane player1Cards,player2Cards,player3Cards,player4Cards;

    Game game = new Game();
    private List<GridPane> grids = new ArrayList<>();


    @FXML
    public void onStart() {
        feedback.setText("Spillet har startet!");
        Player player1 = new Player(playerOne.getText());
        Player player2 = new Player(playerTwo.getText());
        Player player3 = new Player(playerThree.getText());
        Player player4 = new Player(playerFour.getText());
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        game.start();
        (onTable).setVisible(true);
        update();

    }

    @FXML
    public void initialize() {
        grids.add(player1Cards);
        grids.add(player2Cards);
        grids.add(player3Cards);
        grids.add(player4Cards);
        (onTable).setVisible(false);



    }

    @FXML
    public void update() {
    
        

        for (int j = 0; j<game.getPlayers().size(); j++) {
            Player player = game.getPlayers().get(j);
            for (int i = 0; i < player.getHand().size(); i++) {
                Card card = player.getHand().get(i);
                System.out.println(card);
                grids.get(j).add(createCardButton(card), i % 4, i / 3);

            }
        }

        onTable.appendText(game.getTopOnTable().toString());


    }


    private Button createCardButton(Card card) {
        Button button = new Button(card.toString());
        button.setOnAction((event) -> {
            //playCardSelect(card);
        });
        return button;
    }

}


