package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class PlayerTest {
	
	// **White Box Tests**: Check getNAme to ensure 100% statement coverage

	@Test
    void testPlayerColorWhite() {
        Player whitePlayer = new Player(Color.WHITE);
        assertEquals(Color.WHITE, whitePlayer.getColor());
    }

    @Test
    void testPlayerColorBlack() {
        Player blackPlayer = new Player(Color.BLACK);
        assertEquals(Color.BLACK, blackPlayer.getColor());
    }
    

    @Test
    void testColorIsNotNull() {
        Player player = new Player(Color.WHITE);
        assertNotNull(player.getColor());
    }

}
