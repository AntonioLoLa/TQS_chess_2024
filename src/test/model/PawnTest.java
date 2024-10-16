package test.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.model.Board;
import main.model.Color;
import main.model.Pawn;
import main.model.Square;

class PawnTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testPawnDiagonalAttack() {
        // White pawn attacks black pawn diagonally
        Square whitePawnSquare = board.getSquare(6, 0);
        whitePawnSquare.setPiece(new Pawn(Color.WHITE));
        Square blackPawnSquare = board.getSquare(5, 1);
        blackPawnSquare.setPiece(new Pawn(Color.BLACK));

        assertTrue(board.movePiece(whitePawnSquare, blackPawnSquare), "White pawn should capture black pawn diagonally");
        assertNull(whitePawnSquare.getPiece(), "Original square should be empty after the move");
        assertNotNull(blackPawnSquare.getPiece(), "Black pawn's square should now contain the white pawn");
        assertEquals(Color.WHITE, blackPawnSquare.getPiece().getColor(), "White pawn should be in the new square");
    }

    @Test
    void testPawnInvalidDiagonalMove() {
        // White pawn tries to move diagonally to an empty square (invalid)
        Square whitePawnSquare = board.getSquare(6, 0);
        whitePawnSquare.setPiece(new Pawn(Color.WHITE));
        Square emptySquare = board.getSquare(5, 1);

        assertFalse(board.movePiece(whitePawnSquare, emptySquare), "White pawn should not move diagonally to an empty square");
    }

    @Test
    void testPawnMoveForward() {
        // White pawn moves forward to an empty square
        Square whitePawnSquare = board.getSquare(6, 0);
        whitePawnSquare.setPiece(new Pawn(Color.WHITE));
        Square forwardSquare = board.getSquare(5, 0);

        assertTrue(board.movePiece(whitePawnSquare, forwardSquare), "White pawn should move forward to an empty square");
        assertNull(whitePawnSquare.getPiece(), "Original square should be empty after the move");
        assertNotNull(forwardSquare.getPiece(), "Forward square should now contain the white pawn");
    }

    @Test
    void testPawnCannotMoveBackward() {
        // White pawn tries to move backward (invalid)
        Square pawnBehind = board.getSquare(6, 0);
        Square whitePawnSquare = board.getSquare(5, 0);
        whitePawnSquare.setPiece(new Pawn(Color.WHITE));

        assertFalse(board.movePiece(whitePawnSquare, pawnBehind), "White pawn should not move backward");
    }

    @Test
    void testPawnCannotCaptureSameColorPiece() {
        // White pawn tries to capture another white pawn diagonally (invalid)
        Square blackPawnSquare1 = board.getSquare(2, 0);
        blackPawnSquare1.setPiece(new Pawn(Color.BLACK));
        Square blackPawnSquare2 = board.getSquare(3, 1);
        blackPawnSquare2.setPiece(new Pawn(Color.BLACK));

        assertFalse(board.movePiece(blackPawnSquare1, blackPawnSquare2), "White pawn should not capture a piece of the same color");
    }
}
