package controller;

import java.util.Scanner;

import model.Board;
import model.Color;
import model.Player;
import model.Square;
import view.GameView;

public class GameController {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player actualTurn;
    private boolean isGameOver;
    private GameView view;

    public GameController(GameView view) {
    	this.view = view;
        board = new Board();
        whitePlayer = new Player(Color.WHITE);
        blackPlayer = new Player(Color.BLACK);
        actualTurn = whitePlayer;
        isGameOver = false;
    }
    
    public GameController() {
        this(null);
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
            //System.out.println((!whiteKingExists ? "Black" : "White") + " player has won.");
            isGameOver = true;
            return true;
        }
        return false;
    }
    
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (!isGameOver) {
            view.displayBoard(board);
            view.displayTurn(actualTurn);

            System.out.println("Enter your move (format: startRow startColumn destRow destColumn):");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length == 4) {
                try {
                    int startRow = Integer.parseInt(parts[0]);
                    int startColumn = Integer.parseInt(parts[1]);
                    int destRow = Integer.parseInt(parts[2]);
                    int destColumn = Integer.parseInt(parts[3]);

                    if (makeMove(startRow, startColumn, destRow, destColumn)) {
                        checkGameOver();
                    } else {
                        System.out.println("Invalid move, please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid entry, please enter numbers.");
                }
            } else {
                System.out.println("Invalid input format, please enter four numbers.");
            }
        }

        scanner.close();
        view.displayGameOver(actualTurn);
    }
}
