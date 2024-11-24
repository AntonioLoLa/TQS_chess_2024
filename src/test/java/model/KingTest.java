package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KingTest {

    private Board board;
    private King whiteKing;
    private King blackKing;

    @BeforeEach
    void setUp() {
    	// Initialize a board and create two kings, one for each color.
        board = new Board();
        whiteKing = new King(Color.WHITE);
        blackKing = new King(Color.BLACK);
    }
    
    // **Black Box Tests**
    // Equivalence partitions:
	    // - Valid: Move one square in any direction
	    //     - Limit and boundary values:
	    //         - ((4,4),(4,5)) (horizontal move)
	    //         - ((4,4),(5,5)) (diagonal move)
	    //         - ((4,4),(3,4)) (vertical move)
	    // - Invalid: Move more than one square
	    //     - Limit and boundary values:
	    //         - ((4,4),(4,6)) (horizontal move)
	    //         - ((4,4),(6,6)) (diagonal move)
	    // - Valid: Capture opponent's piece
	    //     - Limit and boundary values:
	    //         - ((4,4),(5,5)) where (5,5) has an enemy piece
	    // - Invalid: Capture same color piece
	    //     - Limit and boundary values:
	    //         - ((4,4),(5,5)) where (5,5) has a friendly piece
	    // - Invalid: Move out-of-bounds
	    //     - Limit and boundary values:
	    //         - ((1,0),(-1,0))
	    //         - ((1,0),(9,0))
	    //         - ((1,0),(0,9))
	    // - Invalid: Move through pieces (king doesn't jump but still added for completeness)
    
    @Test
    void testKingGetName() {
    	// Verify that the king's name includes its color.
        assertEquals("W.King", whiteKing.getName());
        assertEquals("B.King", blackKing.getName());
    }

    @Test
    void testWhiteKingCanMoveOneSquare() {
    	// Test valid movement for a white king: Move one square right.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteKing);
        Square destination = board.getSquare(4, 5); // Move right

        assertTrue(whiteKing.validMovement(destination, board));
    }

    @Test
    void testWhiteKingCannotMoveTwoSquares() {
    	// Ensure that the white king cannot move two squares.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteKing);
        Square destination = board.getSquare(4, 6);

        assertFalse(whiteKing.validMovement(destination, board));
    }
    
    @Test
    void testBlackKingCannotMoveToSamePosition() {
    	// Verify that the black king cannot move to the same position.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(blackKing);
        Square destination = board.getSquare(4, 4);

        assertFalse(blackKing.validMovement(destination, board));
    }

    @Test
    void testWhiteKingCanCaptureBlackPiece() {
    	// Ensure that the white king can capture an opponent's piece.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteKing);
        Square enemySquare = board.getSquare(5, 5);
        enemySquare.setPiece(blackKing);// Place a black king to capture

        assertTrue(whiteKing.validMovement(enemySquare, board));
    }

    @Test
    void testBlackKingCannotCaptureSameColorPiece() {
    	// Ensure that the black king cannot capture a friendly piece.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(blackKing);
        Square friendlySquare = board.getSquare(5, 5);
        friendlySquare.setPiece(new King(Color.BLACK)); // Another black king

        assertFalse(blackKing.validMovement(friendlySquare, board));
        
        // Test invalid movement with a king that has no color.
        Square start = board.getSquare(4, 4);
        start.setPiece(new King(null));
        assertFalse(start.getPiece().validMovement(friendlySquare, board));
    }
    
    // **White Box Tests**
    // Ensure path coverage and additional checks for valid and invalid moves
    @Test
    void testKingCannotMoveThroughPieces() {
    	// Ensure that the king cannot move through a piece.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteKing);
        Square blockingSquare = board.getSquare(5, 5);
        blockingSquare.setPiece(new Pawn(Color.BLACK)); // Blocked by a pawn diagonally
        Square destination = board.getSquare(6, 6); // Attempt to move to blocked square

        assertFalse(whiteKing.validMovement(destination, board));
    }

    @Test
    void testKingCannotMoveToInvalidPosition() {
    	// Ensure the king cannot move to an invalid position.
        Square start = board.getSquare(1, 0);
        start.setPiece(whiteKing);
        Square invalid = board.getSquare(-1, 0);
        assertFalse(start.getPiece().validMovement(invalid, board));
    }

    @Test
    void testOutOfBound() {
    	// Check that moves to out-of-bounds squares are invalid.
        Square start = board.getSquare(1, 0);
        start.setPiece(new King(Color.WHITE));
        
        // Test out-of-bounds positions
        Square outOfBoundsDestinationRow = new Square(9, 0); // Row out of bounds
        Square outOfBoundsDestinationRow2 = new Square(8, -1); // Column < 0
        Square outOfBoundsDestinationColumn = new Square(0, 9); // Column >= 8
        Square outOfBoundsDestinationColumn2 = new Square(-1, 8); // Row < 0
        
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow2, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn2, board));
        
        // Test a king with no color
        start.setPiece(new King(null));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow, board));
    }

   
}
