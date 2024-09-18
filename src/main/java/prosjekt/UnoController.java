package prosjekt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class UnoController {

    @FXML
    private TextArea feedback, onTable, resultText;

    @FXML
    private TextField playerOne, playerTwo, playerThree, playerFour;

    @FXML
    private GridPane pickColor, grid1, grid2, grid3, grid4;

    @FXML
    private ScrollPane player1Cards, player2Cards, player3Cards, player4Cards;

    @FXML
    private Button pass, startButton, lastGame, saveGameButton;

    @FXML
    private Label pickColorLabel, player1name, player2name, player3name, player4name;

    @FXML
    private Pane gameStarters, whilePlaying;

    Game game;
    private List<ScrollPane> scrollPanes = new ArrayList<>();
    private List<GridPane> grids = new ArrayList<>();

    public void setGame(Game game) {
        this.game = game;
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

        gameStarters.setVisible(true);
        whilePlaying.setVisible(false);

        pass.setOnAction((event) -> {
            game.drawThree(game.getCurrentPlayer());
            update();
        });

        saveGameButton.setOnAction((event) -> {
            SaveGame();
        });

    }

    @FXML
    public void onStartNewGame() {

        Game game = new Game();
        setGame(game);

        //oppretter nye spillere
        Player player1 = new Player(playerOne.getText());
        Player player2 = new Player(playerTwo.getText());
        Player player3 = new Player(playerThree.getText());
        Player player4 = new Player(playerFour.getText());
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        //autofyller spillernavn om brukeren ikke har skrevet inn navn
        game.setPlayerNamesIfEmpty();
        game.startNewGame();
        startGame();

    }

    @FXML
    public void onPlayLastGame() {
        try {
        StateHandler stateHandler = new StateHandler();
        //gjenoppretter spill fra fil
        Game lastGame = stateHandler.readState("UnoGame.txt");
        setGame(lastGame);
        }
        catch (FileNotFoundException e) {
        feedback.setText("Ingen tidligere spill lagret");
        }

        startGame();

    }

    public void startGame() {

        player1name.setVisible(true);
        player2name.setVisible(true);
        player3name.setVisible(true);
        player4name.setVisible(true);

        player1name.setText(game.getPlayers().get(0).getName());
        player2name.setText(game.getPlayers().get(1).getName());
        player3name.setText(game.getPlayers().get(2).getName());
        player4name.setText(game.getPlayers().get(3).getName());

        update();
        changeColorOnTable(game.getOnTopColor());
        gameStarters.setVisible(false);
        whilePlaying.setVisible(true);
        feedback.setVisible(true);
        saveGameButton.setVisible(true);
    }

    @FXML
    //oppdaterer brukergrensesnittet
    public void update() {

        //lager kort-knapper
        for (int j = 0; j < game.getPlayers().size(); j++) {
            //hver spiller har sin egen grid med kort
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

        if (!game.isSpecialCard(game.getTopOnTable())) {
            game.setOnTopColor(game.getTopOnTable().getColor());
        }

        changeColorOnTable(game.getOnTopColor());
        onTable.clear();
        onTable.appendText(game.getTopOnTable().cardString());

        //sjekker om noen spillere har et kort igjen - UNO
        if (game.uno() != null) {
            String uno = "";
            for (Player player : game.uno()) {
                uno += "" + player + " har UNO \n";
            }
            feedback.setText(uno);
        }

        //sjekker om det er en vinner og om spillet skal avsluttes
        if (game.isThereAWinner()) {
            handleGameOver(game.sortPlayers());
        }

        //sjekker om kortstokken er tom og bunken på bordet må snus
        game.isDeckEmpty();

       
    }
    //lager en knapp for hvert kort
    private Button createCardButton(Card card) {
        Button button = new Button(card.cardString());
        button.setOnAction((event) -> {

            //spesialkort skal føre til at man kan velge farge
            if (game.isSpecialCard(card)) {
                game.playCard(card);
                pickColorLabel.setVisible(true);
                pickColor.setVisible(true);
                for (int i = 0; i < game.getColors().size(); i++) {
                    pickColor.add(createSelectColorButton(game.getColors().get(i)), i % 1, i / 1);
                }
            } 
            
            else if (game.validCard(card)) {
                game.playCard(card);
                update();
            }

            else {
                showErrorMessage("Velg et nytt kort");
            }

        });

        //styler knappene
        button.wrapTextProperty().setValue(true);
        button.setStyle("-fx-text-alignment: center;");
        button.setCursor(Cursor.HAND);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        
        if(Objects.isNull(card.getColor())) {

        }

        else if (card.getColor().equals("Red")) {
            button.setStyle("-fx-background-color: #ff0000; ");

        } else if (card.getColor().equals("Green")) {
            button.setStyle("-fx-background-color: #00ff00; ");

        } else if (card.getColor().equals("Blue")) {
            button.setStyle("-fx-background-color: #0000ff; ");

        } else if (card.getColor().equals("Yellow")) {
            button.setStyle("-fx-background-color: #ffff00; ");

        }

        if (game.isSpecialCard(card) ) {
            button.setStyle("-fx-font-size: 10px;");

        }

        return button;
    }


    //lager knapper for å kunne velge ny farge
    private Button createSelectColorButton(String color) {
        Button button = new Button(color);
        button.setOnAction((event) -> {
            changeColorOnTable(color);
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

    //endrer fargen på kortet som ligger på bordet
    private void changeColorOnTable(String color) {

        if (color == "Red") {
            onTable.setStyle("-fx-background-color: #ff0000; ");

        } else if (color == "Green") {
            onTable.setStyle("-fx-background-color: #00ff00; ");

        } else if (color == "Blue") {
            onTable.setStyle("-fx-background-color: #0000ff; ");

        } else if (color == "Yellow") {
            onTable.setStyle("-fx-background-color: #ffff00; ");

        }
        game.setOnTopColor(color);
    }
    
    //feilmelding om man prøver å legge på et ugyldig kort
    public void showErrorMessage(String s) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Ikke gyldig trekk");
        alert.setHeaderText(
                "Kortet må ha samme farge eller samme \n tall som kortet som ligger på bordet, eller være et spesialkort");
        alert.setContentText(s);
        alert.showAndWait();
    }

    @FXML
    //lagrer tilstanden til spillet til fil
    void SaveGame() {
        StateHandler stateHandler = new StateHandler();
        try {
            stateHandler.writeState(game);
            feedback.setText("Spillet er lagret!");
        }

        catch (IOException e) {
            feedback.setText("Spillet ble ikke lagret");
        }

        finally {
            handleGameOver(game.sortPlayers());
        }
    }



    public void handleGameOver(List<Player> players) {
        //disabler alle kort-knappene når spillet er ferdig
        for (int i = 0; i < grids.size(); i++) {
            for (Node button : grids.get(i).getChildren()) {
                button.setDisable(true);
            }
        }

        feedback.setText(game.resultText());

        whilePlaying.setVisible(false);
        gameStarters.setVisible(true);
    }

}
