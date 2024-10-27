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

    // Tests for White Pawn
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
    void testOutOfBoundRow() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square outOfBoundsDestinationRow = new Square(9, 0);

        //AssertionError
        assertThrows(AssertionError.class, () -> {
            start.getPiece().validMovement(outOfBoundsDestinationRow, board);
        }, "Expected an AssertionError for out-of-bound row.");
    }

    @Test
    void testOutOfBoundColumn() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square outOfBoundsDestinationColumn = new Square(0, 9);

        //AssertionError
        assertThrows(AssertionError.class, () -> {
            start.getPiece().validMovement(outOfBoundsDestinationColumn, board);
        }, "Expected an AssertionError for out-of-bound column.");
    }

    
   
    
    

}
