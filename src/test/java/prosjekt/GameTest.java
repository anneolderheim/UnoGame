package prosjekt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game newGame;
    private Game restartGame;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    @BeforeEach
    public void beforeEach() {
        newGame = new Game();
        // restartGame = new Game(players, onTable, cardDeck, 3,1, "Red" );
        player1 = new Player("Mari");
        player2 = new Player("Vilde");
        player3 = new Player("Andrine");
        player4 = new Player("");

    }

    @Test
    @DisplayName("tester opprettelse av helt nytt spill")
    public void testNewGameConstructor() {
        // et spill skal alltid starte med en full kortstokk
        Assertions.assertEquals(108, newGame.getCardDeck().getDeckSize());
        // et spill opprettes uten spillere
        Assertions.assertEquals(0, newGame.getPlayers().size());
        // et nytt spill skal alltid ha spillretning med klokken
        Assertions.assertEquals(1, newGame.getNextTurn());

    }

    @Test
    @DisplayName("tester gjenoppretting av tildigere spill")
    public void testRestartGameConstructor() {
        // Assertions.assertEquals();
    }

    // et spill som er helt nytt eller som gjennopptas har ulike konstruktører, men
    // like metoder. Det holder derfor å kun teste på NewGame

    @Test
    @DisplayName("tester å legge til spiller")
    public void testAddPlayer() {
        newGame.addPlayer(player1);
        newGame.addPlayer(player2);
        newGame.addPlayer(player3);
        newGame.addPlayer(player4);

        Assertions.assertEquals(4, newGame.getPlayers().size());
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Player player5 = new Player("Erik");
            newGame.addPlayer(player5);
        }, "Maks fire spillere");
    }

    @Test
    @DisplayName("tester metode som legger til et navn på spillere om navnet er en tom streng")
    public void testSetPlayerNamesIfEmpty() {
        newGame.addPlayer(player1);
        newGame.addPlayer(player2);
        newGame.addPlayer(player3);
        newGame.addPlayer(player4);
        // bekrefter at spiller 4 ikke har et navn
        Assertions.assertEquals("", newGame.getPlayers().get(3).getName());
        newGame.setPlayerNamesIfEmpty();
        // tester om metoden har gitt navn til spiller 4
        Assertions.assertEquals("Spiller 4", newGame.getPlayers().get(3).getName());

    }

    @Test
    @DisplayName("tester sjekk om et kort er spesialkort med verdi 13 og 14")
    public void testIsSpecialCard() {
        Card card = new Card(14);
        Card card2 = new Card("Yellow", 8);
        Assertions.assertTrue(newGame.isSpecialCard(card));
        Assertions.assertFalse(newGame.isSpecialCard(card2));

    }

    @Test
    @DisplayName("tester metode for å starte et helt nytt spill")
    // denne metoden fungerer på både nytt og gjennopptatt spill, men brukes kun på
    // et nytt. (vil fungere på begge, men det vil endre tilstandet til et spill -
    // og det ønsker man ikke for et gjennopptatt spill)
    public void testStartNewGame() {
        // Assertions.assertEquals();
    }

    @Test
    @DisplayName("tester å dele ut til alle spillere")
    public void testHandOut() {
        newGame.addPlayer(player1);
        newGame.addPlayer(player2);
        newGame.addPlayer(player3);
        newGame.addPlayer(player4);
        newGame.handOut();
        Assertions.assertEquals(7, player1.getHandSize());
        Assertions.assertEquals(80, newGame.getCardDeck().getDeckSize());
    }

    @Test
    @DisplayName("tester å legge et gyldig kort på bordet fra kortstokk")
    public void testHandOutOnTable() {
        // Assertions.assertEquals();
    }

    @Test
    @DisplayName("tester metode som utfører ulike operasjoner med hensyn på hvilket kort som spilles")
    public void testPlayCard() {
        // Assertions.assertEquals();
    }

    @Test
    @DisplayName("tester metode som sjekker om et kort er gyldig")
    public void testValidCard() {
        // Assertions.assertEquals();
    }

    @Test
    @DisplayName("tester at spilleren må trekke to, tre og fire kort")
    public void testDrawCards() {
        //drawThree metoden gjør også at man går rett til neste spiller - man sier pass
        newGame.drawThree(player1);
        Assertions.assertEquals(3, player1.getHandSize());
        Assertions.assertEquals(1, newGame.getNextTurn());
        newGame.drawFour(player1);
        Assertions.assertEquals(7, player1.getHandSize());
        newGame.drawTwo(player1);
        Assertions.assertEquals(9, player1.getHandSize());
    }

    @Test
    @DisplayName("tester metode for å legge en kort på bordet")
    public void testAddOnTable() {
        Card card = new Card(14);
        newGame.addOnTable(card);
        Assertions.assertEquals(14, newGame.getTopOnTable().getValue());
    }

    @Test
    @DisplayName("tester metoder som styrer hvem sin tur det er og reversere spillernes rekkefølge")
    public void testNextPlayerTurnAndReverse() {
        newGame.nextPlayerTurn();
        Assertions.assertEquals(1, newGame.getTurn());
        newGame.reverse();
        Assertions.assertEquals(-1, newGame.getNextTurn());
        newGame.nextPlayerTurn();
        Assertions.assertEquals(0, newGame.getTurn());
    }

    @Test
    @DisplayName("tester metode som sjekker om noen av spillerne har bare et kort igjen")
    public void testUno() {
        // Assertions.assertEquals();
    }

    @Test
    @DisplayName("tester metode som sjekker om det er en vinner ")
    public void testIsThereAWinner() {
        // Assertions.assertEquals();
    }

    @Test
    @DisplayName("tester om kortstokken det trekkes fra er tom")
    public void testIsDeckEmpty() {
        // Assertions.assertEquals();
    }

    @Test
    @DisplayName("tester metode som sorterer spillerne etter antall kort")
    public void testSortPlayers() {
        newGame.addPlayer(player1);
        newGame.addPlayer(player2);
        newGame.addPlayer(player3);
        newGame.addPlayer(player4);
        newGame.drawFour(player1);
        newGame.drawThree(player3);
        newGame.drawTwo(player4);
        newGame.sortPlayers();
        Assertions.assertEquals(player2, newGame.getPlayers().get(0));
        Assertions.assertEquals(player1, newGame.getPlayers().get(3));

    }

    @Test
    @DisplayName("tester metode som viser resultat")
    public void testResultText() {
        // Assertions.assertEquals();
    }

}
