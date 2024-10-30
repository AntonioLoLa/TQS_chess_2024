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

    @Test
    void testWhiteKingCanMoveOneSquare() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteKing);
        Square destination = board.getSquare(4, 5); // Move right

        assertTrue(whiteKing.validMovement(destination, board));
    }

    @Test
    void testWhiteKingCannotMoveTwoSquares() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteKing);
        Square destination = board.getSquare(4, 6); // Attempt to move two squares

        assertFalse(whiteKing.validMovement(destination, board));
    }
    
    @Test
    void testBlackKingCannotMoveToSamePosition() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(blackKing);
        Square destination = board.getSquare(4, 4); // Attempt to move two squares

        assertFalse(blackKing.validMovement(destination, board));
    }

    @Test
    void testWhiteKingCanCaptureBlackPiece() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteKing);
        Square enemySquare = board.getSquare(5, 5);
        enemySquare.setPiece(blackKing); // Place a black king to capture

        assertTrue(whiteKing.validMovement(enemySquare, board));
    }

    @Test
    void testBlackKingCannotCaptureSameColorPiece() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(blackKing);
        Square friendlySquare = board.getSquare(5, 5);
        friendlySquare.setPiece(new King(Color.BLACK)); // Another white king

        assertFalse(blackKing.validMovement(friendlySquare, board));
        
        Square start = board.getSquare(4, 4);
        start.setPiece(new King(null));
        assertFalse(start.getPiece().validMovement(friendlySquare, board));
    }
    
    // **White Box Tests**

    @Test
    void testKingCannotMoveThroughPieces() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteKing);
        Square blockingSquare = board.getSquare(5, 5);
        blockingSquare.setPiece(new Pawn(Color.BLACK)); // Blocked by a pawn diagonally
        Square destination = board.getSquare(6, 6); // Attempt to move to blocked square

        assertFalse(whiteKing.validMovement(destination, board));
    }

    @Test
    void testKingCannotMoveToInvalidPosition() {
        Square start = board.getSquare(1, 0);
        start.setPiece(whiteKing);
        Square invalid = board.getSquare(-1, 0); // Out of bounds
        assertFalse(start.getPiece().validMovement(invalid, board));
    }

    @Test
    void testOutOfBound() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new King(Color.BLACK));
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
