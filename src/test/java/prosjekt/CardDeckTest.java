package prosjekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    
    private CardDeck cardDeck;
	private CardDeck oldCardDeck;
	private List<Card> deck = new ArrayList<Card>(Arrays.asList());

    @BeforeEach
	public void beforeEach() {
		cardDeck = new CardDeck();
		oldCardDeck = new CardDeck();
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
		Card card = new Card("Red", 2);
		System.out.println(""+card + cardDeck.getCard(5));
		Assertions.assertEquals(card.getValue(), cardDeck.getCard(5).getValue());
		Assertions.assertEquals(card.getColor(), cardDeck.getCard(5).getColor());
		Assertions.assertEquals("Bytt farge +4", cardDeck.getCard(107).toString());
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
		Player player = new Player("Mari");
		cardDeck.handOut(player);
		Assertions.assertEquals(101, cardDeck.getDeckSize());
		Assertions.assertEquals(7, player.getHandSize());

	}
	


}
