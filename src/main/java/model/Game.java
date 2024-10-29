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

    public void initGame() {
        while (!isGameOver) {
            playTurn();
        }
    }
    
    public Player getActualTurn() {
    	return actualTurn;
    }
    
    public Board getBoard() {
    	return board;
    }

    private void playTurn() {
        System.out.println("Turn of the player: " + actualTurn.getColor());
        
        int[] move = getMoveFromUser();
        // Check if the move input is valid
        if (move == null) {
            System.out.println("Invalid input. Please try again.");
            return;
        }

        // Extract start and destination coordinates from the move
        int startRow = move[0];
        int startColumn = move[1];
        int destRow = move[2];
        int destColumn = move[3];

        // Attempt to make the move and check if it was successful
        if (makeMove(startRow, startColumn, destRow, destColumn)) {
            System.out.println("Move made.");
            //changeTurn(); // Change to the next player's turn
        } else {
            System.out.println("Invalid move. Please try again.");
        }

        // Check if the game is over after the move
        isGameOver = checkGameOver();
        // If the game is over, display a message
        if (isGameOver) {
            System.out.println("The game has ended.");
        }
    }

    private int[] getMoveFromUser() {
        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[4]; // Array to hold the move coordinates

        try {
            // Prompt user for starting row
            System.out.print("Enter the starting row (0-7): ");
            move[0] = Integer.parseInt(scanner.nextLine());
            // Prompt user for starting column
            System.out.print("Enter the starting column (0-7): ");
            move[1] = Integer.parseInt(scanner.nextLine());
            // Prompt user for destination row
            System.out.print("Enter the destination row (0-7): ");
            move[2] = Integer.parseInt(scanner.nextLine());
            // Prompt user for destination column
            System.out.print("Enter the destination column (0-7): ");
            move[3] = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return null; // Return null if the input is invalid
        }

        return move;
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
        
        // If the white king is not present, black wins
        if (!whiteKingExists) {
            System.out.println("The black player has won.");
            return true;
        } 
        // If the black king is not present, white wins
        else if (!blackKingExists) {
            System.out.println("The white player has won.");
            return true;
        }

        return false;
    }
}