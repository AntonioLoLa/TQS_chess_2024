package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RookTest {

    private Board board;
    private Rook whiteRook;
    private Rook blackRook;

    @BeforeEach
    void setUp() {
        // Initialize a board and create two bishops, one for each color.
        board = new Board();
        whiteRook = new Rook(Color.WHITE);
        blackRook = new Rook(Color.BLACK);
    }

    // **Black Box Tests**
    //Equivalence partitions:
    	// - Valid: Straight horizontal movement
    	// 	- Limit and boundary values
    	//			((4,4),(4,7))
    	// - Valid: Straight vertical movement
    	// 	- Limit and boundary values
    	//			((2,2),(5,2))
    	// - Valid: Capture enemy piece
    	// 	- Limit and boundary values
    	//			((4,4), (4,7)) where in (4,7) there is an enemy piece
    	// - Invalid: Capture same color piece
    	//	- Limit and boundary values
    	//			((4,4), (4,7)) where in (4,7) there is a friendly piece
    	// - Invalid: Move out-of-bounds
    	//	- Limit and boundary values
    	//			((1,0),(9,0))
    	//			((4,4),(8,-1))
    	//			((4,4),(0,9))
    	//			((1,0),(-1,8))
    	// - Invalid: Move through pieces (attempt to move through blocked path)
		//	- Limit and boundary values:
		//			((4,4),(4,7)) where in (4,5) there is a piece
    	// - Invalid: Move diagonally
    	//	- Limit and boundary values:
    	//			((4,4),(6,6))
    
    @Test
    void testRookGetName() {
        assertEquals("W.Rook", whiteRook.getName());
        assertEquals("B.Rook", blackRook.getName());
    }
    
    @Test
    void testWhiteRookCanMoveStraightHorizontally() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteRook);
        Square destination = board.getSquare(4, 7); // Move straight right

        assertTrue(whiteRook.validMovement(destination, board));
    }

    @Test
    void testWhiteRookCanMoveStraightVertically() {
        Square origin = board.getSquare(2, 2);
        origin.setPiece(whiteRook);
        Square destination = board.getSquare(5, 2); // Move straight down

        assertTrue(whiteRook.validMovement(destination, board));
    }

    @Test
    void testWhiteRookCanCaptureBlackRook() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteRook);
        Square enemySquare = board.getSquare(4, 7);
        enemySquare.setPiece(blackRook); // Place a black rook to capture

        assertTrue(whiteRook.validMovement(enemySquare, board));
    }

    @Test
    void testWhiteRookCannotCaptureSameColorPiece() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteRook);
        Square friendlySquare = board.getSquare(4, 7);
        friendlySquare.setPiece(new Rook(Color.WHITE)); // Another white rook

        assertFalse(whiteRook.validMovement(friendlySquare, board));
    }
    
    @Test
    void testRookCannotMoveOutOfBound() {
    	Square start = board.getSquare(1, 0);
        start.setPiece(new Rook(Color.WHITE));
        Square outOfBoundsDestinationRow = new Square(9, 0);
        Square outOfBoundsDestinationRow2 = new Square(8, -1);
        Square outOfBoundsDestinationColumn = new Square(0, 9);
        Square outOfBoundsDestinationColumn2 = new Square(-1, 8);
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow2, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn2, board));
        start.setPiece(new Rook(null));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow, board));
    }
    
    @Test
    void testRookCannotMoveThroughPieces() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteRook);
        Square blockingSquare = board.getSquare(4, 5);
        blockingSquare.setPiece(new Pawn(Color.BLACK)); // Blocked by a pawn
        Square destination = board.getSquare(4, 7); // Attempting to move straight

        assertFalse(whiteRook.validMovement(destination, board));
    }

    @Test
    void testRookCannotMoveDiagonally() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteRook);
        Square destination = board.getSquare(6, 6); // Diagonal move, invalid

        assertFalse(whiteRook.validMovement(destination, board));
    }

    // **White Box Tests** - More tests to ensure path coverage
      
    @Test
    void testKingCannotMoveToInvalidPosition() {
        Square start = board.getSquare(1, 0);
        start.setPiece(blackRook);
        Square invalid = board.getSquare(-1, 0); // Out of bounds
        assertFalse(start.getPiece().validMovement(invalid, board));
    }


}
