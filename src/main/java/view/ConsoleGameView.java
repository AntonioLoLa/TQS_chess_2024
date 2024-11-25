package view;

import model.Board;
import model.Player;
import model.Square;

import java.util.Scanner;

public class ConsoleGameView implements GameView {
    private Scanner scanner;

    // Constructor initializes the scanner to capture user input.
    public ConsoleGameView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the current state of the board on the console.
     * The board is displayed with row and column labels, and each square
     * shows either the piece on it or a dot (if the square is empty).
     * @param board The current game board to be displayed.
     */
    @Override
    public void displayBoard(Board board) {
        System.out.println("  0 1 2 3 4 5 6 7"); // Column labels
        System.out.println("  ----------------");
        for (int row = 7; row >= 0; row--) {
            System.out.print((0 + row) + " "); // Row labels
            for (int col = 0; col < 8; col++) {
                Square square = board.getSquare(row, col);
                if (square.isOccupied()) {
                    System.out.print(square.getPiece().getName() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println((0 + row)); // Repeat row label for readability
        }
        System.out.println("  ----------------");
        System.out.println("  0 1 2 3 4 5 6 7"); // Repeat column labels
    }

    /**
     * Displays the current player's turn.
     * @param player The player whose turn it is.
     */
    @Override
    public void displayTurn(Player player) {
        System.out.println("Current turn: " + player.getColor());
    }

    /**
     * Displays the game-over message and the winner.
     * @param winner The player who won the game.
     */
    @Override
    public void displayGameOver(Player winner) {
        System.out.println("Game over. Winner: " + winner.getColor());
    }

    /**
     * Reads and parses the user's move input from the console.
     * The input is expected in the format: startRow startColumn destRow destColumn
     * @return An array of integers representing the move in the format:
     *         {startRow, startColumn, destRow, destColumn}.
     *         Returns an empty array in case of invalid input.
     */
    @Override
    public int[] getMoveInput() {
        System.out.println("Enter your move (format: startRow startColumn destRow destColumn):");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");// Split the input string into parts
        
        if (parts.length == 4) {
            try {
                int startRow = Integer.parseInt(parts[0]);
                int startColumn = Integer.parseInt(parts[1]);
                int destRow = Integer.parseInt(parts[2]);
                int destColumn = Integer.parseInt(parts[3]);
                return new int[]{startRow, startColumn, destRow, destColumn};
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter numbers.");
            }
        } else {
            System.out.println("Invalid input format, please enter four numbers.");
        }
        return new int[0]; // Return an empty array in case of invalid input
    }
}
