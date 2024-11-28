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
    
    //For constructor, check colors
    @Test
    void testRookGetColors() {
    	// Verify that the rook's color.
        assertEquals(Color.WHITE, whiteRook.getColor());
        assertEquals(Color.BLACK, blackRook.getColor());
    }
    @Test
    void testRookGetPositionInBoard() {
    	// Verify that the rook's initial position.
        assertEquals(whiteRook.getName(), board.getSquare(0, 0).getPiece().getName());
        assertEquals(whiteRook.getName(), board.getSquare(0, 7).getPiece().getName());
        assertEquals(blackRook.getName(), board.getSquare(7, 0).getPiece().getName());
        assertEquals(blackRook.getName(), board.getSquare(7, 7).getPiece().getName());
    }
    
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
        
        // Test invalid movement with a Rook that has no color.
        Square start = board.getSquare(4, 4);
        start.setPiece(new Rook(null));
        assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(friendlySquare, board))
                .getMessage().contains("Rook's state invariant violated: color cannot be null."),
                "Error message should indicate that color cannot be nulls.");
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
    void testKingCannotMoveToInvalidPosition() {
        Square start = board.getSquare(1, 0);
        start.setPiece(blackRook);
        Square invalid = board.getSquare(-1, 0); // Out of bounds
        assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(invalid, board))
                .getMessage().contains("Destination square cannot be null."),
                "Error message should indicate that destination cannot be null.");
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
    void testRookCannotMoveOutOfBound() {
    	Square start = board.getSquare(1, 0);
        start.setPiece(new Rook(Color.WHITE));
        
        Square outOfBoundsDestinationRow = new Square(9, 0); // Row >= 8
        Square outOfBoundsDestinationRow2 = new Square(-1, 7); // Row < 0
        Square outOfBoundsDestinationColumn = new Square(0, 9); // Column >= 8
        Square outOfBoundsDestinationColumn2 = new Square(7, -1); // Column < 0
        
        assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(outOfBoundsDestinationRow, board))
                .getMessage().contains("out of bounds"),
                "Error message should indicate that the row is out of bounds.");

            assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(outOfBoundsDestinationRow2, board))
                .getMessage().contains("out of bounds"),
                "Error message should indicate that the row is out of bounds.");

            assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(outOfBoundsDestinationColumn, board))
                .getMessage().contains("out of bounds"),
                "Error message should indicate that the column is out of bounds.");

            assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(outOfBoundsDestinationColumn2, board))
                .getMessage().contains("out of bounds"),
                "Error message should indicate that the column is out of bounds.");
    }


}
