package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

	 private Board board;

	    @BeforeEach
	    void setUp() {
	        board = new Board();
	    }

	    @Test
	    void testMovePiece_ValidMove() {
	        Square origin = board.getSquare(6, 0);
	        Square destination = board.getSquare(5, 0);

	        assertTrue(board.movePiece(origin, destination));
	        assertNull(origin.getPiece());
	        assertNotNull(destination.getPiece());
	    }

	    @Test
	    void testMovePiece_InvalidMove() {
	        Square origin = board.getSquare(6, 0);
	        Square invalidDestination = board.getSquare(3, 0);

	        assertFalse(board.movePiece(origin, invalidDestination));
	        assertNotNull(origin.getPiece());
	        assertNull(invalidDestination.getPiece());
	    }

	    // Black tests
	    @Test
	    void testGetSquare_ValidCoordinates() {
	        assertNotNull(board.getSquare(0, 0));
	        assertNotNull(board.getSquare(7, 7));
	    }

	    @Test
	    void testGetSquare_InvalidCoordinates() {
	        assertNull(board.getSquare(-1, 0));
	        assertNull(board.getSquare(8, 0));
	        assertNull(board.getSquare(0, -1));
	        assertNull(board.getSquare(0, 8));
	    }

	    // Pairwise Testing
	    @Test
	    void testMovePiece_Pairwise() {
	        assertTrue(board.movePiece(board.getSquare(6, 0), board.getSquare(5, 0)));
	        assertFalse(board.movePiece(board.getSquare(6, 0), board.getSquare(4, 0)));
	        assertTrue(board.movePiece(board.getSquare(1, 0), board.getSquare(2, 0)));
	        assertFalse(board.movePiece(board.getSquare(1, 0), board.getSquare(3, 0)));
	    }

	    // White tests
	    @Test
	    void testHasKing_WhiteKingPresent() {
	        assertTrue(board.hasKing(Color.WHITE));
	    }

	    @Test
	    void testHasKing_BlackKingPresent() {
	        assertTrue(board.hasKing(Color.BLACK));
	    }

	    @Test
	    void testHasKing_NoKingPresent() {
	        board.getSquare(7, 4).setPiece(null);
	        board.getSquare(0, 4).setPiece(null);

	        assertFalse(board.hasKing(Color.WHITE));
	        assertFalse(board.hasKing(Color.BLACK));
	    }

	    // Loop Testing
	    @Test
	    void testHasKing_AllRowsChecked() {
	        // Chech every row and column of the board
	        boolean kingFound = false;
	        for (int row = 0; row < board.getSize(); row++) {
	            for (int col = 0; col < board.getSize(); col++) {
	                Square square = board.getSquare(row, col);
	                if (square.getPiece() instanceof King) {
	                    kingFound = true;

	                }
	            }
	        }
	        assertTrue(kingFound);
	    }

	    @Test
	    void testInitializeBoard() {
	        for (int row = 0; row < board.getSize(); row++) {
	            for (int col = 0; col < board.getSize(); col++) {
	                Square square = board.getSquare(row, col);
	                
	                if (row == 1) {
	                    assertTrue(square.getPiece() instanceof Pawn);
	                    assertEquals(Color.BLACK, square.getPiece().getColor());

	                } else if (row == 6) {
	                    assertTrue(square.getPiece() instanceof Pawn);
	                    assertEquals(Color.WHITE, square.getPiece().getColor());

	                } else if (row == 0) {
	                    if (col == 0 || col == 7) {
	                        assertTrue(square.getPiece() instanceof Rook);
	                        assertEquals(Color.BLACK, square.getPiece().getColor());

	                    } else if (col == 1 || col == 6) {
	                        assertTrue(square.getPiece() instanceof Knight);
	                        assertEquals(Color.BLACK, square.getPiece().getColor());

	                    } else if (col == 2 || col == 5) {
	                        assertTrue(square.getPiece() instanceof Bishop);
	                        assertEquals(Color.BLACK, square.getPiece().getColor());

	                    } else if (col == 3) {
	                        assertTrue(square.getPiece() instanceof Queen);
	                        assertEquals(Color.BLACK, square.getPiece().getColor());

	                    } else {
	                        assertTrue(square.getPiece() instanceof King);
	                        assertEquals(Color.BLACK, square.getPiece().getColor());
	                    }

	                } else if (row == 7) {
	                    if (col == 0 || col == 7) {
	                        assertTrue(square.getPiece() instanceof Rook);
	                        assertEquals(Color.WHITE, square.getPiece().getColor());

	                    } else if (col == 1 || col == 6) {
	                        assertTrue(square.getPiece() instanceof Knight);
	                        assertEquals(Color.WHITE, square.getPiece().getColor());

	                    } else if (col == 2 || col == 5) {
	                        assertTrue(square.getPiece() instanceof Bishop);
	                        assertEquals(Color.WHITE, square.getPiece().getColor());

	                    } else if (col == 3) {
	                        assertTrue(square.getPiece() instanceof Queen);
	                        assertEquals(Color.WHITE, square.getPiece().getColor());

	                    } else {
	                        assertTrue(square.getPiece() instanceof King);
	                        assertEquals(Color.WHITE, square.getPiece().getColor());
	                    }

	                } else {
	                    assertNull(square.getPiece());
	                }
	            }
	        }
	    }


	}