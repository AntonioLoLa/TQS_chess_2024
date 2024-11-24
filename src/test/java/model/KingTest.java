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
        board = new Board();
        whiteKing = new King(Color.WHITE);
        blackKing = new King(Color.BLACK);
    }
    
    // **Black Box Tests**
    // Equivalence partitions:
	    // Valid: Move one square
	    // Invalid: Move two squares
	    // Valid: Capture opponent's piece
	    // Invalid: Capture same color piece
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
        Square outOfBoundsDestinationRow = new Square(9, 0);
        Square outOfBoundsDestinationRow2 = new Square(8, -1);
        Square outOfBoundsDestinationColumn = new Square(0, 9);
        Square outOfBoundsDestinationColumn2 = new Square(-1, 8);
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow2, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn2, board));
        start.setPiece(new King(null));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow, board));
    }

   
}
