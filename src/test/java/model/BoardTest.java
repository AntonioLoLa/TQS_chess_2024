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

	    @Test
	    void testInitializeBoard() {
	        for (int row = 0; row < board.getSizeRows(); row++) {
	            for (int col = 0; col < board.getSizeCols(); col++) {
	                Square square = board.getSquare(row, col);
	                
	                if (row == 1) {
	                    assertTrue(square.getPiece() instanceof Pawn);
	                    assertEquals(Color.WHITE, square.getPiece().getColor());

	                } else if (row == 6) {
	                    assertTrue(square.getPiece() instanceof Pawn);
	                    assertEquals(Color.BLACK, square.getPiece().getColor());

	                } else if (row == 0) {
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

	                } else if (row == 7) {
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

	                } else {
	                    assertNull(square.getPiece());
	                }
	            }
	        }
	    }
	    
	    // Loop Testing
	    
	    //Inner loop: 0 iterations, 1 iteration, 2 iterations, 5 iterations, 7 (n-1) iterations and 8 (n) iterations
	    	    
	    @Test
	    void testHasKingAvoidInnerLoop() {
	    	Board boardAux = new Board(0, 0, true); //empty board (no iteration)
	    	boardAux.setSizeRows(0);
	        boardAux.setSizeCols(0);
	    	assertFalse(boardAux.hasKing(Color.WHITE));
	    }
	    
	    @Test
	    void testHasKingOneIterationInnerLoop() {
	        Board boardAux = new Board(1, 1, true); //1-column board (1 iteration)
	        boardAux.setSizeRows(1);
	        boardAux.setSizeCols(1);
	        assertFalse(boardAux.hasKing(Color.WHITE));
	        boardAux.getSquare(0, 0).setPiece(new King(Color.WHITE));
	        assertTrue(boardAux.hasKing(Color.WHITE));
	    }

	    @Test
	    void testHasKingTwoIterationsInnerLoop() {
	        Board boardAux = new Board(1, 2, true); //2-column board (2 iterations))
	        boardAux.setSizeRows(1);
	        boardAux.setSizeCols(2);
	        assertFalse(boardAux.hasKing(Color.WHITE));
	        boardAux.getSquare(0, 1).setPiece(new King(Color.WHITE));
	        assertTrue(boardAux.hasKing(Color.WHITE));
	    }

	    @Test
	    void testHasKingFiveIterationsInnerLoop() {
	        Board boardAux = new Board(1, 5, true); //5-column board (5 iterations))
	        boardAux.setSizeRows(1);
	        boardAux.setSizeCols(5);
	        assertFalse(boardAux.hasKing(Color.WHITE));
	        boardAux.getSquare(0, 4).setPiece(new King(Color.WHITE)); 
	        assertTrue(boardAux.hasKing(Color.WHITE));
	    }

	    @Test
	    void testHasKingSevenIterationsInnerLoop() {
	        Board boardAux = new Board(1, 7, true); //7-column board (n-1 iterations))
	        boardAux.setSizeRows(1);
	        boardAux.setSizeCols(7);
	        assertFalse(boardAux.hasKing(Color.WHITE));
	        boardAux.getSquare(0, 6).setPiece(new King(Color.WHITE));
	        assertTrue(boardAux.hasKing(Color.WHITE));
	    }

	    @Test
	    void testHasKingEightIterationsInnerLoop() {
	        Board boardAux = new Board(1, 8, true); //8-column board (n iterations))
	        boardAux.setSizeRows(1);
	        boardAux.setSizeCols(8);
	        assertFalse(boardAux.hasKing(Color.WHITE));
	        boardAux.getSquare(0, 7).setPiece(new King(Color.WHITE));
	        assertTrue(boardAux.hasKing(Color.WHITE));
	    }
	    
	    //Outer loop (usual value for inner loop = 8): 0 iterations, 1 iteration, 2 iterations, 5 iterations, 7 (n-1) iterations and 8 (n) iterations
	    
	    @Test
	    void testHasKingAvoidOuterLoop() {
	    	Board boardAux = new Board(0, 8, true); //empty board (no iteration)
	    	boardAux.setSizeRows(0);
	        boardAux.setSizeCols(8);
	    	assertFalse(boardAux.hasKing(Color.WHITE));
	    }
	    
	    @Test
	    void testHasKingOneIterationOuterLoop() {
	        Board boardAux = new Board(1, 8, true); //1-column board (1 iteration)
	        boardAux.setSizeRows(1);
	        boardAux.setSizeCols(8);
	        assertFalse(boardAux.hasKing(Color.WHITE));
	        boardAux.getSquare(0, 7).setPiece(new King(Color.WHITE));
	        assertTrue(boardAux.hasKing(Color.WHITE));
	    }
	    
	    @Test
	    void testHasKingTwoIterationOuterLoop() {
	        Board boardAux = new Board(2, 8, true); //1-column board (1 iteration)
	        boardAux.setSizeRows(2);
	        boardAux.setSizeCols(8);
	        assertFalse(boardAux.hasKing(Color.WHITE));
	        boardAux.getSquare(1, 7).setPiece(new King(Color.WHITE));
	        assertTrue(boardAux.hasKing(Color.WHITE));
	    }
	    
	    @Test
	    void testHasKingFiveIterationOuterLoop() {
	        Board boardAux = new Board(5, 8, true); //1-column board (1 iteration)
	        boardAux.setSizeRows(5);
	        boardAux.setSizeCols(8);
	        assertFalse(boardAux.hasKing(Color.WHITE));
	        boardAux.getSquare(4, 7).setPiece(new King(Color.WHITE));
	        assertTrue(boardAux.hasKing(Color.WHITE));
	    }
	    
	    @Test
	    void testHasKingSevenIterationOuterLoop() {
	        Board boardAux = new Board(7, 8, true); //1-column board (1 iteration)
	        boardAux.setSizeRows(7);
	        boardAux.setSizeCols(8);
	        assertFalse(boardAux.hasKing(Color.WHITE));
	        boardAux.getSquare(6, 7).setPiece(new King(Color.WHITE));
	        assertTrue(boardAux.hasKing(Color.WHITE));
	    }
	    
	    @Test
	    void testHasKingEightIterationOuterLoop() {
	        Board boardAux = new Board(8, 8, true); //1-column board (1 iteration)
	        boardAux.setSizeRows(8);
	        boardAux.setSizeCols(8);
	        assertFalse(boardAux.hasKing(Color.WHITE));
	        boardAux.getSquare(7, 7).setPiece(new King(Color.WHITE));
	        assertTrue(boardAux.hasKing(Color.WHITE));
	    }


	}