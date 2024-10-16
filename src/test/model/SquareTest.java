package test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.model.Color;
import main.model.Square;
import main.model.Queen;

class SquareTest {

	@Test
    void testSquare() {
        Square testSquare = new Square(3,4);
        assertEquals(3, testSquare.getRow());
        assertEquals(4, testSquare.getColumn());
    }

	@Test
	void testIsOccupied() {
        Square testSquare = new Square(3,4);
        assertFalse(testSquare.isOccupied());
        Queen q = new Queen(Color.WHITE);
        testSquare.setPiece(q);
        assertTrue(testSquare.isOccupied());        
	}

}
