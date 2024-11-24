package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KnightTest {

    private Board board;
    private Knight whiteKnight;
    private Knight blackKnight;

    @BeforeEach
    void setUp() {
        // Initialize a board and create two bishops, one for each color.
        board = new Board();
        whiteKnight = new Knight(Color.WHITE);
        blackKnight = new Knight(Color.BLACK);
    }

    // **Black Box Tests**
    //Equivalence partitions:
    	// - Valid: Valid L shaped movement RowDelta 2 ColDelta 1
    	//	 - Limit and boundary values:
    	//			((2,2),(4,3))
    	// - Valid: Valid L shaped movement RowDelta 1 ColDelta 2
    	//	 - Limit and boundary values:
		//			((2,2),(3,4))
    	// - Invalid: Straight movement
    	//	 - Limit and boundary values:
    	//			((4,4),(4,5))
    	// - Invalid: Non-L-shaped movement
    	//	 - Limit and boundary values:
    	//			((2,2), (5,5))
    	//			((2,2), (4,4))
		//			((2,2), (3,3))
    	// - Invalid: Destination row is negative
    	//	 - Limit and boundary values:
    	//			((1,0),(-1,2))
		// - Invalid: Destination row is out-of-bounds
		//	 - Limit and boundary values:
		//			((1,0),(n,2)) where n is the number of rows (8)
		// - Invalid: Destination column is negative
		//	 - Limit and boundary values:
		//			((1,0),(2,-1))
		// - Invalid: Destination column is out-of-bounds
		//	 - Limit and boundary values:
		//			((1,0),(2,n)) where n is the number of rows (8)
    	
    @Test
    void testKnightGetName() {
        assertEquals("W.Knight", whiteKnight.getName());
        assertEquals("B.Knight", blackKnight.getName());
    }

    @Test
    void testBlackBoxValidLShapedMoveRowDelta2ColDelta1() {
        Square origin = board.getSquare(2, 2);
        origin.setPiece(whiteKnight);
        Square destination = board.getSquare(4, 3); // L movement (rowDelta == 2, colDelta == 1)

        assertTrue(whiteKnight.validMovement(destination, board));
    }

    @Test
    void testBlackBoxValidLShapedMoveRowDelta1ColDelta2() {
        Square origin = board.getSquare(2, 2);
        origin.setPiece(whiteKnight);
        Square destination = board.getSquare(3, 4); // L movement (rowDelta == 1, colDelta == 2)

        assertTrue(whiteKnight.validMovement(destination, board));
    }

    @Test
    void testBlackBoxInvalidStraightMove() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteKnight);
        Square destination = board.getSquare(4, 5); // Straight move

        assertFalse(whiteKnight.validMovement(destination, board));
    }

    @Test
    void testBlackBoxInvalidNonLShapedMove() {
        Square origin = board.getSquare(2, 2);
        origin.setPiece(whiteKnight);
        Square destination = board.getSquare(5, 5); // Not an L move (rowDelta == 3, colDelta == 3)

        assertFalse(whiteKnight.validMovement(destination, board));
    }
    
    @Test
    void testBlackBoxInvalidNonLShapedMove2() {
        Square origin = board.getSquare(2, 2);
        origin.setPiece(whiteKnight);
        Square destination = board.getSquare(4, 4); // Not an L move (rowDelta == 3, colDelta == 3)

        assertFalse(whiteKnight.validMovement(destination, board));
    }
    
    @Test
    void testBlackBoxInvalidNonLShapedMove3() {
        Square origin = board.getSquare(2, 2);
        origin.setPiece(whiteKnight);
        Square destination = board.getSquare(3, 3); // Not an L move (rowDelta == 3, colDelta == 3)

        assertFalse(whiteKnight.validMovement(destination, board));
    }

    @Test
    void testBlackBoxCannotMoveOutOfBoundsRowNegative() {
        Square origin = board.getSquare(1, 0);
        origin.setPiece(whiteKnight);
        Square outOfBoundsSquare = new Square(-1, 2); // Row is out of bounds (negative)

        assertFalse(whiteKnight.validMovement(outOfBoundsSquare, board));
    }

    @Test
    void testBlackBoxCannotMoveOutOfBoundsRowExceedsBoard() {
        Square origin = board.getSquare(1, 0);
        origin.setPiece(whiteKnight);
        Square outOfBoundsSquare = new Square(board.getSizeRows(), 2); // Row exceeds board size

        assertFalse(whiteKnight.validMovement(outOfBoundsSquare, board));
    }

    @Test
    void testBlackBoxCannotMoveOutOfBoundsColumnNegative() {
        Square origin = board.getSquare(1, 0);
        origin.setPiece(whiteKnight);
        Square outOfBoundsSquare = new Square(2, -1); // Column is out of bounds (negative)

        assertFalse(whiteKnight.validMovement(outOfBoundsSquare, board));
    }

    @Test
    void testBlackBoxCannotMoveOutOfBoundsColumnExceedsBoard() {
        Square origin = board.getSquare(1, 0);
        origin.setPiece(whiteKnight);
        Square outOfBoundsSquare = new Square(2, board.getSizeCols()); // Column exceeds board size

        assertFalse(whiteKnight.validMovement(outOfBoundsSquare, board));
    }

  
    // **White Box Tests** - More tests to ensure 100% path coverage
    
    @Test
    void testNullDestination() {
        Square origin = board.getSquare(3, 3);
        origin.setPiece(whiteKnight);

        assertFalse(whiteKnight.validMovement(null, board));
    }


    @Test
    void testWhiteBoxCanCaptureBlackKnight() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteKnight);
        Square enemySquare = board.getSquare(6, 5);
        enemySquare.setPiece(blackKnight); 

        assertTrue(whiteKnight.validMovement(enemySquare, board));
    }

    @Test
    void testWhiteBoxCannotCaptureSameColorPiece() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteKnight);
        Square friendlySquare = board.getSquare(6, 5);
        friendlySquare.setPiece(new Knight(Color.WHITE));

        assertFalse(whiteKnight.validMovement(friendlySquare, board));
    }

    @Test
    void testInvalidLShapeMove() {
        Square origin = board.getSquare(2, 2);
        origin.setPiece(whiteKnight);
        Square invalidMoveSquare = board.getSquare(5, 5); // Not an L move

        assertFalse(whiteKnight.validMovement(invalidMoveSquare, board));
    }

    @Test
    void testCaptureNullColorInvariantCheck() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteKnight);

        // Temporarily set knight's color to null
        whiteKnight.setColor(null);
        
        Square destination = board.getSquare(6, 5);
        assertFalse(whiteKnight.validMovement(destination, board));

        // Restore knight's color for further tests
        whiteKnight.setColor(Color.WHITE);
    }
}
