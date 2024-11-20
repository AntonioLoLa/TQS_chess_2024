package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PawnTest {

    private Board board;
    private Pawn whitePawn;
    private Pawn blackPawn;


    @BeforeEach
    void setUp() {
        board = new Board();
        whitePawn = new Pawn(Color.WHITE);
        blackPawn = new Pawn(Color.BLACK);
    }

    // Tests for White Pawn
    @Test
    void testPawnGetName() {
        assertEquals("W.Pawn", whitePawn.getName());
        assertEquals("B.Pawn", blackPawn.getName());
    }
    
    @Test
    void testWhitePawnValidMovementForwardOneSquare() {
        Square start = board.getSquare(6, 0);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(5, 0); // Un movimiento adelante
        assertTrue(start.getPiece().validMovement(destination, board), "White Pawn should be able to move one square forward.");
    }

    @Test
    void testWhitePawnValidMovementForwardTwoSquares() {
        Square start = board.getSquare(6, 0);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(4, 0); // Dos movimientos adelante desde la posición inicial
        assertTrue(start.getPiece().validMovement(destination, board), "White Pawn should be able to move two squares forward on its first move.");
    }

    @Test
    void testWhitePawnDiagonalAttack() {
        Square start = board.getSquare(6, 0);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(5, 1);
        destination.setPiece(new Pawn(Color.BLACK)); // Oponente en la diagonal
        assertTrue(start.getPiece().validMovement(destination, board), "White Pawn should be able to attack diagonally.");
    }

    @Test
    void testWhitePawnCannotMoveBackward() {
        Square start = board.getSquare(5, 0);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(6, 0); // Intentar moverse hacia atrás
        assertFalse(start.getPiece().validMovement(destination, board), "White Pawn cannot move backward.");
    }

    @Test
    void testWhitePawnCannotCaptureSameColorPiece() {
        Square start = board.getSquare(5, 1);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(4, 0);
        destination.setPiece(new Pawn(Color.WHITE)); // Bloquear con la misma color
        assertFalse(start.getPiece().validMovement(destination, board), "White Pawn cannot capture its own piece.");
    }

    @Test
    void testWhitePawnInvalidDiagonalMove() {
        Square start = board.getSquare(6, 0);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(5, 1); // No hay pieza para atacar
        assertFalse(start.getPiece().validMovement(destination, board), "White Pawn cannot move diagonally without capturing.");
    }

    @Test
    void testWhitePawnCannotMoveToOutOfBounds() {
        Square start = board.getSquare(6, 0);
        start.setPiece(new Pawn(Color.WHITE));
        Square invalidDestination = board.getSquare(-1, 0); // Fuera del límite
        assertFalse(start.getPiece().validMovement(invalidDestination, board), "White Pawn should not move out of bounds.");
    }

    @Test
    void testWhitePawnDoubleForwardBlocked() {
        Square start = board.getSquare(6, 0);
        start.setPiece(new Pawn(Color.WHITE));
        board.getSquare(5, 0).setPiece(new Pawn(Color.WHITE)); // Bloqueo en la primera fila
        Square destination = board.getSquare(4, 0); // Intentar mover dos casillas
        assertFalse(start.getPiece().validMovement(destination, board), "White Pawn cannot move double forward if blocked.");
    }

    // Tests for Black Pawn
    @Test
    void testBlackPawnValidMovementForwardOneSquare() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(2, 0); // Un movimiento adelante
        assertTrue(start.getPiece().validMovement(destination, board), "Black Pawn should be able to move one square forward.");
    }

    @Test
    void testBlackPawnValidMovementForwardTwoSquares() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(3, 0); // Dos movimientos adelante desde la posición inicial
        assertTrue(start.getPiece().validMovement(destination, board), "Black Pawn should be able to move two squares forward on its first move.");
    }

    @Test
    void testBlackPawnDiagonalAttack() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(2, 1);
        destination.setPiece(new Pawn(Color.WHITE)); // Oponente en la diagonal
        assertTrue(start.getPiece().validMovement(destination, board), "Black Pawn should be able to attack diagonally.");
    }

    @Test
    void testBlackPawnCannotMoveBackward() {
        Square start = board.getSquare(2, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(1, 0); // Intentar moverse hacia atrás
        assertFalse(start.getPiece().validMovement(destination, board), "Black Pawn cannot move backward.");
    }

    @Test
    void testBlackPawnCannotCaptureSameColorPiece() {
        Square start = board.getSquare(2, 1);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(3, 0);
        destination.setPiece(new Pawn(Color.BLACK)); // Bloquear con la misma color
        assertFalse(start.getPiece().validMovement(destination, board), "Black Pawn cannot capture its own piece.");
    }

    @Test
    void testBlackPawnInvalidDiagonalMove() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(2, 1); // No hay pieza para atacar
        assertFalse(start.getPiece().validMovement(destination, board), "Black Pawn cannot move diagonally without capturing.");
    }

    @Test
    void testBlackPawnCannotMoveToOutOfBounds() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square invalidDestination = board.getSquare(8, 0); // Fuera del límite
        assertFalse(start.getPiece().validMovement(invalidDestination, board), "Black Pawn should not move out of bounds.");
    }

    @Test
    void testBlackPawnDoubleForwardBlocked() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.BLACK));
        board.getSquare(2, 0).setPiece(new Pawn(Color.BLACK)); // Bloqueo en la primera fila
        Square destination = board.getSquare(3, 0); // Intentar mover dos casillas
        assertFalse(start.getPiece().validMovement(destination, board), "Black Pawn cannot move double forward if blocked.");
    }
    
    //Limit Cases
    
    @Test
    void testPawnCannotMoveBackward() {
        Square whitePawnSquare = new Square(5, 0, new Pawn(Color.WHITE)); 
        Square pawnBehind = new Square(6, 0); 

        board.getSquare(5, 0).setPiece(whitePawnSquare.getPiece()); 
        board.getSquare(6, 0).setPiece(pawnBehind.getPiece());

        assertFalse(board.movePiece(whitePawnSquare, pawnBehind));
    }
    
    @Test
    void testPawnCannotMoveInvalidPosition() {
    	Square start = board.getSquare(1, 0);
    	Square invalid = board.getSquare(-1, 0); // Fuera del límite
        assertFalse(start.getPiece().validMovement(invalid, board));
    }
    
    @Test
    void testOutOfBound() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square outOfBoundsDestinationRow = new Square(9, 0);
        Square outOfBoundsDestinationRow2 = new Square(8, -1);
        Square outOfBoundsDestinationColumn = new Square(0, 9);
        Square outOfBoundsDestinationColumn2 = new Square(-1, 8);
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow2, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn2, board));
    }
}
