package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import model.Color;
import model.MockBoard;
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
	 
	// Test that the initial turn is white
	@Test
	void testInitialTurnIsWhite() {
	     assertEquals(game.getActualTurn().getColor(), Color.WHITE, "The first turn should be of the White player.");
	}
	
	// Test invalid move when trying to move outside the board bounds
	@Test
    void testInvalidMoveOutsideBounds() {
        assertFalse(game.makeMove(6, 0, -1, 0), "Move should be invalid: outside of bounds.");
    }
	
	// Test game over condition when the black king is captured
	@Test
	void testGameOverWhenBlackKingIsCaptured() {
	    // Remove Black King to simulate game over
	    game.getBoard().getSquare(0, 4).setPiece(null); // Assume Black King is captured

	    // Check if the game correctly identifies that it is over
	    assertTrue(game.checkGameOver(), "Game should be over when the Black King is captured.");
	}
	 
	// Test game over condition when the white king is captured
	 @Test
	 void testGameOverWhenWhiteKingIsCaptured() {
	     game.getBoard().getSquare(7, 4).setPiece(null);
	     assertTrue(game.checkGameOver(), "Game should be over when the White King is captured.");
	}
	 
	// Test that the game is not over if both kings are still on the board
	 @Test
	 void testNotGameOver() {
	     game.getBoard().getSquare(7, 4);
	     game.getBoard().getSquare(0, 4);
	     assertFalse(game.checkGameOver(), "Game should not be over.");
	}
	
	 // Simulate a real game with valid and invalid movements
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
	void testStartGame() {
	    //Simulating inputs
		String simulatedInput = 
			    "1 7 2 7\n" +
			    "6 3 4 3\n" +
			    "1 1 3 1\n" +
			    "12 34.5 56 78\n" +
			    "1 2\n" +
			    "6 4 4 4\n" +
			    "1 2 2 2\n" +
			    "6 6 -1 6\n" +
			    "-1 6 5 6\n" +
			    "-1 6 -1 6\n" +
			    "6 6 7 6\n" +
			    "6 6 4 6\n" +
			    "0 2 2 0\n" +
			    "4 4 3 4\n" +
			    "0 3 3 0\n" +
			    "3 4 2 4\n" +
			    "3 0 7 4\n";

	    InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
	    System.setIn(inputStream);

	    // Controller and view created
	    MockGameView mockView = new MockGameView();
	    GameController gameController = new GameController(mockView);

	    // Start game
	    gameController.startGame();

	    // Check expected results
	    assertNotNull(mockView.getDisplayedBoard(), "The board should have been displayed.");
	    assertNotNull(mockView.getCurrentPlayer(), "The current player should have been displayed.");
	    assertTrue(mockView.isGameOverDisplayed(), "The game over message should have been displayed.");
	}
	
	
	 // Mock View: Simulate a real game with valid and invalid movements
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

	// Test if game over is displayed correctly
	 @Test
	 void testDisplayGameOver() {
	     Color winnerColor = game.getBoard().getSquare(0, 4).getPiece().getColor();
	     mockView.displayGameOver(new Player(winnerColor));
	     assertTrue(mockView.isGameOverDisplayed(), "Game over should be displayed.");
	     assertEquals(winnerColor, mockView.getWinner().getColor(), "The winner's color should be correctly recorded.");
	     assertEquals("Game Over! Winner: " + winnerColor, mockView.getLastDisplayedMessage(), "The last displayed message should match.");
	 }

	// Test if the current player's turn is displayed correctly
	 @Test
	 void testDisplayTurn() {
	     Color currentPlayerColor = game.getActualTurn().getColor();
	     mockView.displayTurn(new Player(currentPlayerColor));
	     assertEquals(currentPlayerColor, mockView.getCurrentPlayer().getColor(), "The current player's color should be displayed correctly.");
	     assertEquals("Current turn: " + currentPlayerColor, mockView.getLastDisplayedMessage(), "The last displayed message should match the current turn.");
	 }

	  // Test the reset functionality of the mock view
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
	 
	 
	 /* Tests with Mock */
	// Test game over when the white king is captured using the mock board
	 
	 @Test
	 void testGameOverWhenWhiteKingIsCapturedWithMock() {
	     MockBoard mockBoard = new MockBoard();
	     GameController gameController = new GameController();
	     gameController.setBoard(mockBoard);

	     mockBoard.setKingPresence(Color.WHITE, false);// Simulate White King captured

	     assertTrue(gameController.checkGameOver(), "Game should be over.");
	}
	 
	// Test game over when the black king is captured using the mock board
	 @Test
	 void testGameOverWhenBlackKingIsCapturedWithMock() {
	     MockBoard mockBoard = new MockBoard();
	     GameController gameController = new GameController();
	     gameController.setBoard(mockBoard);

	     mockBoard.setKingPresence(Color.BLACK, false);// Simulate Black King captured

	     assertTrue(gameController.checkGameOver(), "Game should be over.");
	}

	// Test a valid move with the mock board
	@Test
	void testValidMoveWithMockBoardWithMock() {
	    MockBoard mockBoard = new MockBoard();
	    GameController gameController = new GameController();
	    gameController.setBoard(mockBoard);

	    assertTrue(gameController.makeMove(0, 0, 1, 1), "Valid movement.");
	}

}
