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
        board = new Board();
        whiteRook = new Rook(Color.WHITE);
        blackRook = new Rook(Color.BLACK);
    }

    // **Black Box Tests**
    
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

    // **White Box Tests**
    
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

    @Test
    void testRookCannotMoveOutOfBound() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Rook(Color.BLACK));
        Square outOfBoundsDestinationRow = new Square(9, 0);
        Square outOfBoundsDestinationColumn = new Square(0, 9);
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn, board));
    }
}