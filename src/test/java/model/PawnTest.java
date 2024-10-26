package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PawnTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testPawnDiagonalAttack() {
        Square whitePawnSquare = new Square(6, 0, new Pawn(Color.WHITE));
        Square blackPawnSquare = new Square(5, 1, new Pawn(Color.BLACK));

        board.getSquare(6, 0).setPiece(whitePawnSquare.getPiece());
        board.getSquare(5, 1).setPiece(blackPawnSquare.getPiece());

        assertTrue(board.movePiece(whitePawnSquare, blackPawnSquare));
        assertNull(whitePawnSquare.getPiece());
        assertNotNull(blackPawnSquare.getPiece());
        assertEquals(Color.WHITE, blackPawnSquare.getPiece().getColor());
    }

    @Test
    void testPawnInvalidDiagonalMove() {
        Square whitePawnSquare = new Square(6, 0, new Pawn(Color.WHITE));
        Square emptySquare = new Square(5, 1);

        board.getSquare(6, 0).setPiece(whitePawnSquare.getPiece());
        board.getSquare(5, 1).setPiece(emptySquare.getPiece());

        assertFalse(board.movePiece(whitePawnSquare, emptySquare));
    }

    @Test
    void testPawnMoveForward() {
        Square whitePawnSquare = new Square(6, 0, new Pawn(Color.WHITE));
        Square forwardSquare = new Square(5, 0); 

        board.getSquare(6, 0).setPiece(whitePawnSquare.getPiece());
        board.getSquare(5, 0).setPiece(forwardSquare.getPiece()); 

        assertTrue(board.movePiece(whitePawnSquare, forwardSquare));
        assertNull(whitePawnSquare.getPiece());
        assertNotNull(forwardSquare.getPiece());
    }

    @Test
    void testPawnCannotMoveBackward() {
        Square whitePawnSquare = new Square(5, 0, new Pawn(Color.WHITE)); 
        Square pawnBehind = new Square(6, 0); 

        board.getSquare(5, 0).setPiece(whitePawnSquare.getPiece()); 
        board.getSquare(6, 0).setPiece(pawnBehind.getPiece());

        assertFalse(board.movePiece(whitePawnSquare, pawnBehind));
    }

    @Test
    void testPawnCannotCaptureSameColorPiece() {
        Square blackPawnSquare1 = new Square(3, 1, new Pawn(Color.BLACK));
        Square blackPawnSquare2 = new Square(4, 0, new Pawn(Color.BLACK));

        board.getSquare(3, 1).setPiece(blackPawnSquare1.getPiece()); 
        board.getSquare(4, 0).setPiece(blackPawnSquare2.getPiece()); 

        assertFalse(board.movePiece(blackPawnSquare1, blackPawnSquare2));
    }
    
    @Test
    void testPawnFirstMoveDoubleForward() {
        Square whitePawnSquare = new Square(6, 0, new Pawn(Color.WHITE));
        Square doubleForwardSquare = new Square(4, 0);

        board.getSquare(6, 0).setPiece(whitePawnSquare.getPiece());
        board.getSquare(4, 0).setPiece(doubleForwardSquare.getPiece());

        assertTrue(board.movePiece(whitePawnSquare, doubleForwardSquare));
        assertNull(whitePawnSquare.getPiece());
        assertNotNull(doubleForwardSquare.getPiece());
    }

    @Test
    void testPawnDoubleForwardBlocked() {
        Square whitePawnSquare = new Square(6, 0, new Pawn(Color.WHITE));
        Square forwardSquare = new Square(5, 0, new Pawn(Color.WHITE)); // Bloquea el paso
        Square doubleForwardSquare = new Square(4, 0);

        board.getSquare(6, 0).setPiece(whitePawnSquare.getPiece());
        board.getSquare(5, 0).setPiece(forwardSquare.getPiece());
        board.getSquare(4, 0).setPiece(doubleForwardSquare.getPiece());

        assertFalse(board.movePiece(whitePawnSquare, doubleForwardSquare));
    }

}
