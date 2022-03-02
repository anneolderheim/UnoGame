package prosjekt;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

	private Card card;
    private Card card1;

	@BeforeEach
	public void beforeEach() {
		card = new Card('B', 10);
        card1 = new Card(13);
	}


    //hvor nødvendige er tester av klasser som jeg selv kontrollerer helt?
	@Test
	@DisplayName("Sjekk at et kort-objekt har riktige verdier")
	public void testConstructor() {
		Assertions.assertEquals('B', card.getColor());
		Assertions.assertEquals(10, card.getValue());
		Assertions.assertEquals(13,card1.getValue());
        // hva skjer når card1.getValue?
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Card failCard = new Card(10);
		}, "Skal ikke kunne lage et kort med verdi 10 uten farge");
	}

}