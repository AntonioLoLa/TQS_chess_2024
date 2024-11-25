package view;

import model.Board;
import model.Player;

public class MockGameView implements GameView {
    private Board board;
    private Player currentPlayer;
    private Player winner;
    private boolean gameOverDisplayed = false;
    private String lastDisplayedMessage = "";
    private int[] inputMove;

    /**
     * Simulates displaying the board by storing its reference.
     * @param board The current state of the board to be "displayed".
     */
    @Override
    public void displayBoard(Board board) {
        this.board = board;
    }

    /**
     * Simulates displaying the current player's turn.
     * Updates the last displayed message with the player's color.
     * @param player The player whose turn is being displayed.
     */
    @Override
    public void displayTurn(Player player) {
        this.currentPlayer = player;
        lastDisplayedMessage = "Current turn: " + player.getColor();
    }

    /**
     * Simulates displaying the game-over message with the winner.
     * Sets the winner, updates the last message, and flags the game as over.
     * @param winner The player who won the game.
     */
    @Override
    public void displayGameOver(Player winner) {
        this.winner = winner;
        gameOverDisplayed = true;
        lastDisplayedMessage = "Game Over! Winner: " + winner.getColor();
    }

    /**
     * Simulates receiving input for a move.
     * @return The pre-set move input array.
     */
    @Override
    public int[] getMoveInput() {
        return inputMove;
    }

    /**
     * Allows setting a simulated move input for testing purposes.
     * @param move The array representing a simulated move input.
     */
    public void setInputMove(int[] move) {
        this.inputMove = move;
    }

    /**
     * Checks if the game-over message has been displayed.
     * @return True if the game-over message was displayed; false otherwise.
     */
    public boolean isGameOverDisplayed() {
        return gameOverDisplayed;
    }

    /**
     * Retrieves the winner of the game as recorded by the mock.
     * @return The player who won the game.
     */
    public Player getWinner() {
        return winner;
    }


    /**
     * Retrieves the last "displayed" state of the board.
     * @return The board instance last passed to `displayBoard`.
     */
    public Board getDisplayedBoard() {
        return board;
    }

    /**
     * Retrieves the current player as recorded by the mock.
     * @return The player whose turn was last "displayed".
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Retrieves the last message displayed by the mock.
     * @return The last message string.
     */
    public String getLastDisplayedMessage() {
        return lastDisplayedMessage;
    }

    /**
     * Resets all mock state to initial values, simulating a fresh start.
     */
    public void reset() {
        this.board = null;
        this.currentPlayer = null;
        this.winner = null;
        this.gameOverDisplayed = false;
        this.lastDisplayedMessage = "";
        this.inputMove = null;
    }
}
