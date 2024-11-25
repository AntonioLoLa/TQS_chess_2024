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

    /**
     * Constructor for initializing the game with a specified view.
     * Sets up the board and players, and sets the initial turn to white.
     * @param view The view to display the game state
     */
    public GameController(GameView view) {
    	this.view = view;
        board = new Board();
        whitePlayer = new Player(Color.WHITE);
        blackPlayer = new Player(Color.BLACK);
        actualTurn = whitePlayer;
        isGameOver = false;
    }
    
    /**
     * Sets a custom board to the game.
     * @param board The board to be set
     */
    public void setBoard(Board board) {
        this.board = board;
    }
    
    /**
     * Default constructor
     */
    public GameController() {
        this(null);
    }
    
    /**
     * Returns the player whose turn it currently is.
     * @return The player whose turn it is
     */
    public Player getActualTurn() {
    	return actualTurn;
    }
    
    /**
     * Returns the current board state.
     * @return The current board
     */
    public Board getBoard() {
    	return board;
    }

    /**
     * Switches the turn between the two players.
     * If it’s white’s turn, it switches to black, and vice versa.
     */
    public void changeTurn() {
        actualTurn = (actualTurn == whitePlayer) ? blackPlayer : whitePlayer;
    }

    /**
     * Attempts to make a move on the board.
     * If the move is valid, it updates the board and switches the turn.
     * @param startRow The starting row of the piece
     * @param startColumn The starting column of the piece
     * @param destRow The destination row of the piece
     * @param destColumn The destination column of the piece
     * @return true if the move was successfully made, false otherwise
     */
    public boolean makeMove(int startRow, int startColumn, int destRow, int destColumn) {
        Square origin = board.getSquare(startRow, startColumn);Square destination = board.getSquare(destRow, destColumn);
        

        // If both squares are valid, attempt to move the piece && can move piece
        if (origin != null && destination != null && board.movePiece(origin, destination)) {
        	changeTurn();
            return true;
        }
        return false; // Return false if the move could not be made
    }

    /**
     * Checks if the game is over.
     * The game is over if either the white or black king is missing.
     * @return true if the game is over, false otherwise
     */
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
    
    /**
     * Starts the game and keeps running until the game is over.
     * It continuously displays the board, the current player's turn, 
     * and handles user input for making moves.
     */
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
