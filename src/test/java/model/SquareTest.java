package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SquareTest {
	
	// **Black Box Tests**
    // Equivalence partitions (IsOccupied Method): 
    	// -Invalid: There isn't a piece in the square
    	//	 - Limit and boundary values: 
		//			(3,4) where (3,4) is free
		// - Valid: There is a piece in the square
		//	 - Limit and boundary values: 
		//			(3,4) where (3,4) has a queen
	
	@Test
	void testIsOccupied() {
        Square testSquare = new Square(3,4);
        assertFalse(testSquare.isOccupied());
        Queen q = new Queen(Color.WHITE);
        testSquare.setPiece(q);
        assertTrue(testSquare.isOccupied());      
        testSquare.setPiece(null);
        assertFalse(testSquare.isOccupied());
    }
	
    // **White Box Tests** - More tests to ensure 100% path coverage
	
	@Test
    void testSquare() {
        Square testSquare = new Square(3,4);
        assertEquals(3, testSquare.getRow());
        assertEquals(4, testSquare.getColumn());
        assertNull(testSquare.getPiece());
    }

}
