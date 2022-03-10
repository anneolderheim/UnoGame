package prosjekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class UnoController {

    @FXML
    private TextArea feedback, onTable;

    @FXML
    private TextField playerOne, playerTwo, playerThree, playerFour;

    @FXML
    private GridPane pickColor, grid1, grid2, grid3, grid4;

    @FXML
    private ScrollPane player1Cards, player2Cards, player3Cards, player4Cards;

    @FXML
    private Button drawThree, startButton;

    @FXML
    private Label pickColorLabel, player1name, player2name, player3name, player4name;

    @FXML
    private Group playerInputFieldsGroup;

    Game game = new Game();
    private List<ScrollPane> scrollPanes = new ArrayList<>();
    private List<GridPane> grids = new ArrayList<>();

    private List<String> colors = new ArrayList<String>(Arrays.asList("Red", "Blue", "Green", "Yellow"));

    @FXML
    public void onStart() {

        Player player1 = new Player(playerOne.getText());
        Player player2 = new Player(playerTwo.getText());
        Player player3 = new Player(playerThree.getText());
        Player player4 = new Player(playerFour.getText());
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);

        player1name.setVisible(true);
        player2name.setVisible(true);
        player3name.setVisible(true);
        player4name.setVisible(true);

        player1name.setText(playerOne.getText());
        player2name.setText(playerTwo.getText());
        player3name.setText(playerThree.getText());
        player4name.setText(playerFour.getText());

        game.start();
        update();

        (onTable).setVisible(true);
        (drawThree).setVisible(true);

        playerInputFieldsGroup.setVisible(false);


        // start-knappen må forsvinne
        //(startButton).setVisible(false);;

    }

    @FXML
    void drawThree() {
        game.drawThree(game.currentPlayer());
    }

    @FXML
    public void initialize() {
        scrollPanes.add(player1Cards);
        scrollPanes.add(player2Cards);
        scrollPanes.add(player3Cards);
        scrollPanes.add(player4Cards);

        grids.add(grid1);
        grids.add(grid2);
        grids.add(grid3);
        grids.add(grid4);

        (onTable).setVisible(false);
        (drawThree).setVisible(false);

        drawThree.setOnAction((event) -> {
            game.drawThree(game.getCurrentPlayer());
            update();
        });


    }

    @FXML
    public void update() {

        feedback.setText("Det er spiller " + (game.getTurn() +1) + " sin tur");

        for (int j = 0; j < game.getPlayers().size(); j++) {
            grids.get(j).getChildren().clear();
            Player player = game.getPlayers().get(j);
            for (int i = 0; i < player.getHandSize(); i++) {
                Card card = player.getHand().get(i);
                grids.get(j).add(createCardButton(card), i % 4, i / 4);

            }

        }

        // disable knapper hvis det ikke er din tur

        for (int i = 0; i < grids.size(); i++) {
            if (i == game.getTurn()) {
                for (Node button : grids.get(i).getChildren()) {
                    button.setDisable(false);
                }
            }

            else if (i != game.getTurn()) {
                for (Node button : grids.get(i).getChildren()) {
                    button.setDisable(true);
                }

            }
        }

        pickColorLabel.setVisible(false);
        pickColor.getChildren().clear();
        pickColor.setVisible(false);

        // for (int j = 0; j < game.getPlayers().size(); j++) {
        // Player player = game.getPlayers().get(j);
        // TilePane grid = new TilePane();
        // grid.setPrefColumns(4);
        // for (int i = 0; i < player.getHandSize() ; i++) {
        // Card card = player.getHand().get(i);
        // grid.getChildren().add(createCardButton(card));
        // //(createCardButton(card), i%4, i/4);

        // }
        // scrollPanes.get(j).setContent(grid);
        // }
        if (game.getTopOnTable().getColor() == "Red") {
            onTable.setStyle("-fx-background-color: #ff0000; ");

        } else if (game.getTopOnTable().getColor() == "Green") {
            onTable.setStyle("-fx-background-color: #00ff00; ");

        } else if (game.getTopOnTable().getColor() == "Blue") {
            onTable.setStyle("-fx-background-color: #0000ff; ");

        } else if (game.getTopOnTable().getColor() == "Yellow") {
            onTable.setStyle("-fx-background-color: #ffff00; ");

        }
        onTable.clear();
        onTable.appendText(game.getTopOnTable().toString());

    }

    private Button createCardButton(Card card) {
        Button button = new Button(card.toString());
        button.setOnAction((event) -> {

            if (card.getValue() == 13 || card.getValue() == 14) {
                game.playCard(card);
                pickColorLabel.setVisible(true);
                pickColor.setVisible(true);
                for (int i = 0; i < colors.size(); i++) {
                    pickColor.add(createSelectColorButton(colors.get(i)), i % 1, i / 1);
                }
                if (card.getValue() == 14) {
                    game.drawFour(game.getCurrentPlayer());
                }
                
            }
            else if (game.validCard(card)) {
                game.playCard(card);
                update();

            }

        });

        button.wrapTextProperty().setValue(true);
        button.setStyle("-fx-text-alignment: center;");
        button.setCursor(Cursor.HAND);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        if (card.getColor() == "Red") {
            button.setStyle("-fx-background-color: #ff0000; ");

        } else if (card.getColor() == "Green") {
            button.setStyle("-fx-background-color: #00ff00; ");

        } else if (card.getColor() == "Blue") {
            button.setStyle("-fx-background-color: #0000ff; ");

        } else if (card.getColor() == "Yellow") {
            button.setStyle("-fx-background-color: #ffff00; ");

        }
        return button;
    }

    private Button createSelectColorButton(String color) {
        Button button = new Button(color);
        button.setOnAction((event) -> {
            changeColor(color);
            game.getTopOnTable().setColor(color);
            update();
        });

        button.wrapTextProperty().setValue(true);
        button.setStyle("-fx-text-alignment: center;");
        button.setCursor(Cursor.HAND);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        if (color == "Red") {
            button.setStyle("-fx-background-color: #ff0000; ");

        } else if (color == "Green") {
            button.setStyle("-fx-background-color: #00ff00; ");

        } else if (color == "Blue") {
            button.setStyle("-fx-background-color: #0000ff; ");

        } else if (color == "Yellow") {
            button.setStyle("-fx-background-color: #ffff00; ");

        }
        return button;
    }

    private void changeColor(String color) {
        

        if (color == "Red") {
            onTable.setStyle("-fx-background-color: #ff0000; ");

        } else if (color == "Green") {
            onTable.setStyle("-fx-background-color: #00ff00; ");

        } else if (color == "Blue") {
            onTable.setStyle("-fx-background-color: #0000ff; ");

        } else if (color == "Yellow") {
            onTable.setStyle("-fx-background-color: #ffff00; ");

        }
        update();

    }

}
