package prosjekt;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    
    private CardDeck cardDeck;
    //private List<Card> deck = new ArrayList<Card>(Arrays.asList(R0, R0, R1, R1, R2, R2, R3, R3, R4, R4, R5, R5, R6, R6, R7, R7, R8, R8, R9, R9, R Skip, R Skip, R Snu, R Snu, R Trekk to, R Trekk to, B0, B0, B1, B1, B2, B2, B3, B3, B4, B4, B5, B5, B6, B6, B7, B7, B8, B8, B9, B9, B Skip, B Skip, B Snu, B Snu, B Trekk to, B Trekk to, G0, G0, G1, G1, G2, G2, G3, G3, G4, G4, G5, G5, G6, G6, G7, G7, G8, G8, G9, G9, G Skip, G Skip, G Snu, G Snu, G Trekk to, G Trekk to, Y0, Y0, Y1, Y1, Y2, Y2, Y3, Y3, Y4, Y4, Y5, Y5, Y6, Y6, Y7, Y7, Y8, Y8, Y9, Y9, Y Skip, Y Skip, Y Snu, Y Snu, Y Trekk to, Y Trekk to, Bytt farge, Bytt farge, Bytt farge og trekk fire, Bytt farge og trekk fire));


    @BeforeEach
	public void beforeEach() {
		cardDeck = new CardDeck();
	}


    @Test
	@DisplayName("Sjekk at et kortstokk-objekt blir opprettet med riktige verdier")
	public void testConstructor() {
		Assertions.assertEquals(108, cardDeck.getDeckSize());
		//sjekke at kortstokken er i riktig rekkefølge
		//Assertions.assertEquals(cardDeck.getList(),deck);
	}

	@Test
	@DisplayName("Sjekk at shuffle-metoden stokker kortstokken")
	public void testShuffle() {
		cardDeck.shuffle();
		//Assertions.assertNotEquals(cardDeck.getList(), deck);
	}

	@Test
	@DisplayName("Sjekk at getCard henter ut riktig kort")
	public void testGetCard() {

		Assertions.assertEquals("R2", cardDeck.getCard(5));
		Assertions.assertEquals("Bytt farge og trekk fire", cardDeck.getCard(107));
	}

	@Test
	@DisplayName("Sjekk at removeCard-metoden fjerner kort")
	public void testReomoveCard() {
		cardDeck.removeCard(107);
		Assertions.assertEquals(107, cardDeck.getDeckSize());
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			cardDeck.removeCard(107);
		}, "Skal ikke kunne fjerne et kort med høyere indeks enn antall kort i kortstokken");



	}

	@Test
	@DisplayName("Sjekk at HandOut-metoden deler ut riktig antall kort")
	public void testHandOut() {
		Player player = new Player("Anne");
		cardDeck.handOut(player);
		Assertions.assertEquals(101, cardDeck.getDeckSize());
		Assertions.assertEquals(7, player.getHandSize());

	}
	


}
