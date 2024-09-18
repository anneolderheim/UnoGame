package prosjekt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

        private Player player;
        private Card card1;
        private Card card2;
        private Card card3;
        private CardDeck cardDeck;

        @BeforeEach
        public void beforeEach() {
                player = new Player("Per");
                card1 = new Card("Red", 9);
                card2 = new Card("Blue", 7);
                card3 = new Card(14);
                cardDeck = new CardDeck();
        }

        @Test
        @DisplayName("Tester konstruktør")
        public void testConstructor() {
                Assertions.assertEquals("Per", player.getName());
                Assertions.assertEquals(0, player.getHandSize());
        }

        @Test
        @DisplayName("tester at spiller kan motta kort på hånda")
        public void testAddCard() {
                player.addCard(card1);
                player.addCard(card2);
                Assertions.assertEquals(2, player.getHandSize());
                // Assertions.assertEquals(Arrays.asList("Red9, Blue7"), player.getHand());
        }

        @Test
        @DisplayName("tester at spiller kan fjerne kort på hånda - legge ut kort")
        public void testRemoveCard() {
                player.addCard(card1);
                player.addCard(card2);
                player.removeCardFromHand(card1);
                Assertions.assertEquals(1, player.getHandSize());
                // Assertions.assertEquals(Arrays.asList("Blue7"), player.getHand());
                Assertions.assertThrows(IllegalArgumentException.class, () -> {
                        player.removeCardFromHand(card3);
                });
        }

        @Test
	@DisplayName("tester at spiller kan trekke kort fra kortstokken")
	public void testDrawCards() {
                player.drawTwo(cardDeck);
                Assertions.assertEquals(2, player.getHandSize());
                player.drawThree(cardDeck);
                Assertions.assertEquals(5, player.getHandSize());
                player.drawFour(cardDeck);
                Assertions.assertEquals(9, player.getHandSize());
        }
}
