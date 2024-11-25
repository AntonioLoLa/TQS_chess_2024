package model;

public class Rook extends Piece {

    // Constructor to initialize the Rook with a specific color.
    public Rook(Color color) {
        super(color);
    }

    @Override
    public String getName() {
        // Returns the name of the piece, prefixed with its color.
        return (this.color == Color.WHITE ? "W.Rook" : "B.Rook");
    }

    @Override
    public boolean validMovement(Square destination, Board board) {
        // Invariant and pre-conditions:
        // - The destination square must not be null.
        // - The destination must be within the bounds of the board.
        // - The Rook must have a valid color.
        if (destination == null || destination.getRow() < 0
                || destination.getRow() >= board.getSizeRows() && destination.getColumn() < 0
                || destination.getColumn() >= board.getSizeCols() || !checkInvariants()) {
            return false;
        }

        // Calculate the absolute row and column differences between the current position and the destination.
        int rowDelta = Math.abs(destination.getRow() - this.position.getRow());
        int colDelta = Math.abs(destination.getColumn() - this.position.getColumn());

        // A Rook can only move in straight lines (either rows or columns must remain constant).
        if (rowDelta == 0 || colDelta == 0) {
            // Determine the step direction for rows or columns.
            int rowStep = Integer.compare(destination.getRow(), this.position.getRow());
            int colStep = Integer.compare(destination.getColumn(), this.position.getColumn());

            // Check each square along the path to the destination.
            int currentRow = this.position.getRow() + rowStep;
            int currentCol = this.position.getColumn() + colStep;
            while (currentRow != destination.getRow() || currentCol != destination.getColumn()) {
                // If any square along the path contains a piece, the move is invalid.
                if (board.getSquare(currentRow, currentCol).getPiece() != null) {
                    return false; // Path is blocked
                }
                currentRow += rowStep;
                currentCol += colStep;
            }

            // Ensure the destination square does not contain a piece of the same color.
            Piece destinationPiece = destination.getPiece();
            if (destinationPiece != null && destinationPiece.getColor() == this.color) {
                return false; // Cannot capture a friendly piece
            }

            return true; // The move is valid.
        }

        // The move is invalid if it is not in a straight line.
        return false;
    }

    // Ensures the invariants of the Rook are upheld (e.g., it has a valid color).
    private boolean checkInvariants() {
        return this.color != null;
    }
}
