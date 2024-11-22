package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import model.Color;
import model.Player;
import view.MockGameView;

class GameControllerTest {
	
	 private GameController game;
	 private MockGameView mockView;
	 private GameController gameMock;

	 @BeforeEach
	 void setUp() {
		 mockView = new MockGameView();
	     game = new GameController();
	     gameMock = new GameController(mockView);
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
	     assertTrue(game.makeMove(1, 7, 2, 7), "White Pawn should move from (6, 3) to (4, 3)"); 
	     assertTrue(game.makeMove(6, 3, 4, 3), "Black Pawn should move from (6, 3) to (4, 3)"); 
	     assertTrue(game.makeMove(1, 1, 3, 1), "White Pawn should move from (1, 1) to (3, 1)"); 
	     assertTrue(game.makeMove(6, 4, 4, 4), "Black Pawn should move from (6, 4) to (4, 4)"); 
	     assertTrue(game.makeMove(1, 2, 2, 2), "White Pawn should move from (1, 2) to (2, 2)"); 
	     assertFalse(game.makeMove(6, 6, -1, 6), "Black Pawn can not move"); 
	     assertFalse(game.makeMove(-1, 6, 5, 6), "Black Pawn can not move"); 
	     assertFalse(game.makeMove(-1, 6, -1, 6), "Black Pawn can not move"); 
	     assertFalse(game.makeMove(6, 6, 7, 6), "Black Pawn can not move"); 
	     assertTrue(game.makeMove(6, 6, 4, 6), "Black Pawn should move from (6, 6) to (4, 6)"); 
	     assertTrue(game.makeMove(0, 2, 2, 0), "White Bishop should move from (0, 2) to (2, 0)"); 
	     assertTrue(game.makeMove(4, 4, 3, 4), "Black Pawn should move from (4, 4) to (3, 4)"); 
	     assertTrue(game.makeMove(0, 3, 3, 0), "White Queen should move from (0, 3) to (3, 0)"); 
	     assertTrue(game.makeMove(3, 4, 2, 4), "Black Pawn should move from (3, 4) to (2, 4)"); 
	     assertTrue(game.makeMove(3, 0, 7, 4), "White Queen should capture Black King"); 
	     assertTrue(game.checkGameOver(), "The game should be over as the Black King has been captured.");
	 }
	
	 @Test
	    void testSimulateGameWithMock() {
	        mockView.setInputMove(new int[]{6, 3, 5, 3}); // Simulating a movement
	        assertTrue(gameMock.makeMove(6, 3, 5, 3), "White Pawn should move from (6, 3) to (5, 3)");

	        mockView.setInputMove(new int[]{1, 1, 3, 1});
	        assertTrue(gameMock.makeMove(1, 1, 3, 1), "Black Pawn should move from (1, 1) to (3, 1)");

	        mockView.displayBoard(gameMock.getBoard());
	        mockView.displayTurn(gameMock.getActualTurn());
	        assertNotNull(mockView.getDisplayedBoard(), "The board should be displayed.");
	        assertNotNull(mockView.getCurrentPlayer(), "The current player should be displayed.");
	        
	        mockView.reset();
	    }

	 
	 @Test
	 void testDisplayGameOver() {
	     Color winnerColor = game.getBoard().getSquare(0, 4).getPiece().getColor();
	     mockView.displayGameOver(new Player(winnerColor));
	     assertTrue(mockView.isGameOverDisplayed(), "Game over should be displayed.");
	     assertEquals(winnerColor, mockView.getWinner().getColor(), "The winner's color should be correctly recorded.");
	     assertEquals("Game Over! Winner: " + winnerColor, mockView.getLastDisplayedMessage(), "The last displayed message should match.");
	 }

	 @Test
	 void testDisplayTurn() {
	     Color currentPlayerColor = game.getActualTurn().getColor();
	     mockView.displayTurn(new Player(currentPlayerColor));
	     assertEquals(currentPlayerColor, mockView.getCurrentPlayer().getColor(), "The current player's color should be displayed correctly.");
	     assertEquals("Current turn: " + currentPlayerColor, mockView.getLastDisplayedMessage(), "The last displayed message should match the current turn.");
	 }

	 @Test
	 void testResetMethod() {
	     mockView.displayTurn(new Player(game.getActualTurn().getColor()));
	     mockView.displayGameOver(new Player(game.getBoard().getSquare(0, 4).getPiece().getColor()));
	     mockView.reset();
	     assertNull(mockView.getDisplayedBoard(), "Board should be reset to null.");
	     assertNull(mockView.getCurrentPlayer(), "Current player should be reset to null.");
	     assertNull(mockView.getWinner(), "Winner should be reset to null.");
	     assertFalse(mockView.isGameOverDisplayed(), "Game over displayed status should be reset to false.");
	     assertEquals("", mockView.getLastDisplayedMessage(), "Last displayed message should be reset to empty.");
	     assertNull(mockView.getMoveInput(), "Input move should be reset to null.");
	 }

}
