package prosjekt;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

	private Card card;
    private Card card1;
	private Card failCard;
	private Card failCard2;
	private Card failCard3;



	@BeforeEach
	public void beforeEach() {
		card = new Card("Blue", 10);
        card1 = new Card(13);
	}


    //hvor nødvendige er tester av klasser som jeg selv kontrollerer helt?
	@Test
	@DisplayName("Sjekk at et kort-objekt har riktige verdier")
	public void testConstructor() {
		Assertions.assertEquals("Blue", card.getColor());
		Assertions.assertEquals(10, card.getValue());
		Assertions.assertEquals(13,card1.getValue());
		Assertions.assertNull(card1.getColor());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			failCard = new Card(10);

		}, "Skal ikke kunne lage et kort med verdi 10 uten farge");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			failCard2 = new Card("Yellow", 14);

		}, "Kort med verdi 14 skal ikke ha farge");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			failCard3 = new Card("Orange", 7);

		}, "Ugyldig farge");
	}

}