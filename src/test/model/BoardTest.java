package test.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.model.Board;
import main.model.Color;
import main.model.Pawn;
import main.model.Square;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testBoard() {
        assertNotNull(board, "The board should be initialized");
    }

    @Test
    void testInitializeBoard() {
        Square whitePawnSquare = board.getSquare(6, 0);
        Square blackPawnSquare = board.getSquare(1, 0);

        assertNotNull(whitePawnSquare.getPiece());
        assertNotNull(blackPawnSquare.getPiece());
        
        assertTrue(whitePawnSquare.getPiece() instanceof Pawn);
        assertEquals(Color.WHITE, whitePawnSquare.getPiece().getColor());
        
        assertTrue(blackPawnSquare.getPiece() instanceof Pawn);
        assertEquals(Color.BLACK, blackPawnSquare.getPiece().getColor());
    }

    @Test
    void testGetSquare() {
        Square square = board.getSquare(0, 0);
        assertNotNull(square);

        Square invalidSquare = board.getSquare(-1, -1);
        assertNull(invalidSquare);
    }

    @Test
    void testMovePiece() {
        Square origin = board.getSquare(6, 0);
        Square destination = board.getSquare(5, 0);
        

        assertNotNull(origin.getPiece());
        assertNull(destination.getPiece());
        assertTrue(board.movePiece(origin, destination));
        assertNull(origin.getPiece());
        assertNotNull(destination.getPiece());
        assertTrue(destination.getPiece() instanceof Pawn);
    }
    
    @Test
    void testDiagonalAttack() {
    	//Pawn possible Movement
        Square whitePawnSquare = board.getSquare(6, 0);
        whitePawnSquare.setPiece(new Pawn(Color.WHITE));
        Square blackPawnSquare = board.getSquare(5, 1);
        blackPawnSquare.setPiece(new Pawn(Color.BLACK));
        assertTrue(board.movePiece(whitePawnSquare, blackPawnSquare));
        
       //Pawn not possible Movement
        whitePawnSquare = board.getSquare(6, 0);
        whitePawnSquare.setPiece(new Pawn(Color.WHITE));
        blackPawnSquare = board.getSquare(5, 0);
        blackPawnSquare.setPiece(new Pawn(Color.BLACK));
        assertFalse(board.movePiece(whitePawnSquare, blackPawnSquare));

    }
}
