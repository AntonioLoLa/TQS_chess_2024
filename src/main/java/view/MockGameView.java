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

    @Override
    public void displayBoard(Board board) {
        this.board = board;
    }

    @Override
    public void displayTurn(Player player) {
        this.currentPlayer = player;
        lastDisplayedMessage = "Current turn: " + player.getColor();
    }

    @Override
    public void displayGameOver(Player winner) {
        this.winner = winner;
        gameOverDisplayed = true;
        lastDisplayedMessage = "Game Over! Winner: " + winner.getColor();
    }

    @Override
    public int[] getMoveInput() {
        return inputMove;
    }

    public void setInputMove(int[] move) {
        this.inputMove = move;
    }

    public boolean isGameOverDisplayed() {
        return gameOverDisplayed;
    }

    public Player getWinner() {
        return winner;
    }

    public Board getDisplayedBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public String getLastDisplayedMessage() {
        return lastDisplayedMessage;
    }

    public void reset() {
        this.board = null;
        this.currentPlayer = null;
        this.winner = null;
        this.gameOverDisplayed = false;
        this.lastDisplayedMessage = "";
        this.inputMove = null;
    }
}
