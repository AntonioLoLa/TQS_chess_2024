package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Color;

class GameControllerTest {
	
	 private GameController game;

	 @BeforeEach
	 void setUp() {
	     game = new GameController();
	}
	 
	@Test
	void testInitialTurnIsWhite() {
	     assertEquals(game.getActualTurn().getColor(), Color.WHITE, "The first turn should be of the White player.");
	}
	
	@Test
    void testInvalidMoveOutsideBounds() {
        assertFalse(game.makeMove(6, 0, -1, 0), "Move should be invalid: outside of bounds.");
    }
	
	@Test
	    void testGameOverWhenBlackKingIsCaptured() {
	        // Remove Black King to simulate game over
	        game.getBoard().getSquare(0, 4).setPiece(null); // Assume Black King is captured

	        // Check if the game correctly identifies that it is over
	        assertTrue(game.checkGameOver(), "Game should be over when the Black King is captured.");
	    }
	 
	 @Test
	 void testGameOverWhenWhiteKingIsCaptured() {
	     game.getBoard().getSquare(7, 4).setPiece(null);
	     assertTrue(game.checkGameOver(), "Game should be over when the White King is captured.");
	}
	 
	 @Test
	 void testNotGameOver() {
	     game.getBoard().getSquare(7, 4);
	     game.getBoard().getSquare(0, 4);
	     assertFalse(game.checkGameOver(), "Game should not be over.");
	}
	
	@Test
	void testSimulateGame() {	     
	     game = new GameController();

	     assertTrue(game.makeMove(6, 3, 4, 3), "White Pawn should move from (6, 3) to (4, 3)"); 
	     assertTrue(game.makeMove(1, 1, 3, 1), "Black Pawn should move from (1, 1) to (3, 1)"); 
	     assertTrue(game.makeMove(6, 4, 4, 4), "White Pawn should move from (6, 4) to (4, 4)"); 
	     assertTrue(game.makeMove(1, 2, 2, 2), "Black Pawn should move from (1, 2) to (2, 2)"); 
	     assertFalse(game.makeMove(6, 6, -1, 6), "White Pawn can not move"); 
	     assertFalse(game.makeMove(-1, 6, 5, 6), "White Pawn can not move"); 
	     assertFalse(game.makeMove(-1, 6, -1, 6), "White Pawn can not move"); 
	     assertFalse(game.makeMove(6, 6, 7, 6), "White Pawn can not move"); 
	     assertTrue(game.makeMove(6, 6, 4, 6), "White Pawn should move from (6, 6) to (4, 6)"); 
	     assertTrue(game.makeMove(0, 2, 2, 0), "Black Bishop should move from (0, 2) to (2, 0)"); 
	     assertTrue(game.makeMove(4, 4, 3, 4), "White Pawn should move from (4, 4) to (3, 4)"); 
	     assertTrue(game.makeMove(0, 3, 3, 0), "Black Queen should move from (0, 3) to (3, 0)"); 
	     assertTrue(game.makeMove(3, 4, 2, 4), "White Pawn should move from (3, 4) to (2, 4)"); 
	     assertTrue(game.makeMove(3, 0, 7, 4), "Black Queen should move from (3, 0) to (7, 4)"); 
	     assertTrue(game.makeMove(2, 4, 1, 3), "White Pawn should move from (2, 4) to (1, 3) attacking White Pawn"); 
	     assertTrue(game.makeMove(7, 4, 7, 3), "Black King should capture White King");
	     assertTrue(game.checkGameOver(), "The game should be over as the White King has been captured.");
	 }
}
