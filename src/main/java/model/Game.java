package model;

import java.util.Scanner;

public class Game {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player actualTurn;
    private boolean isGameOver;

    public Game() {
        board = new Board();
        whitePlayer = new Player(Color.WHITE);
        blackPlayer = new Player(Color.BLACK);
        actualTurn = whitePlayer;
        isGameOver = false;
    }
    
    public Player getActualTurn() {
    	return actualTurn;
    }
    
    public Board getBoard() {
    	return board;
    }

    public void changeTurn() {
        actualTurn = (actualTurn == whitePlayer) ? blackPlayer : whitePlayer;
    }

    public boolean makeMove(int startRow, int startColumn, int destRow, int destColumn) {
        Square origin = board.getSquare(startRow, startColumn);
        Square destination = board.getSquare(destRow, destColumn);

        // If both squares are valid, attempt to move the piece && can move piece
        if (origin != null && destination != null && board.movePiece(origin, destination)) {
        	changeTurn();
            return true;
        }
        return false; // Return false if the move could not be made
    }

    public boolean checkGameOver() {
        // Check if the kings of both colors are still on the board
        boolean whiteKingExists = board.hasKing(Color.WHITE);
        boolean blackKingExists = board.hasKing(Color.BLACK);
        
        if (!whiteKingExists || !blackKingExists) {
            System.out.println((!whiteKingExists ? "Black" : "White") + " player has won.");
            isGameOver = true;
            return true;
        }
        return false;
    }
}