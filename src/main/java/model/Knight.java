package model;

public class Knight extends Piece {

    // Constructor to initialize the Knight with a specific color.
    public Knight(Color color) {
        super(color);
    }
    
    @Override
    public String getName() {
        // Returns the name of the piece, prefixed with its color.
        return (this.color == Color.WHITE ? "W.Knight" : "B.Knight");
    }

    @Override
    public boolean validMovement(Square destination, Board board) {
    	// Preconditions and invariant
        assert destination != null : "Destination square cannot be null.";
        assert destination.getRow() >= 0 && destination.getRow() < board.getSizeRows() 
            : "Row is out of bounds.";
        assert destination.getColumn() >= 0 && destination.getColumn() < board.getSizeCols() 
            : "Column is out of bounds.";
        assert checkInvariants() : "Knight's state invariant violated: color cannot be null.";

        // Calculate the absolute differences in rows and columns between the current position and destination.
        int rowDelta = Math.abs(destination.getRow() - this.position.getRow());
        int colDelta = Math.abs(destination.getColumn() - this.position.getColumn());

        // A knight moves in an "L" shape: two squares in one direction and one in the perpendicular direction.
        if ((rowDelta == 2 && colDelta == 1) || (rowDelta == 1 && colDelta == 2)) {
            // Retrieve the piece at the destination square, if any.
            Piece destinationPiece = destination.getPiece();

            // Ensure the destination square does not contain a piece of the same color.
            if (destinationPiece != null && destinationPiece.getColor() == this.color) {
                return false; // Cannot capture a piece of the same color.
            }

            // Check invariants for the Knight.
            return checkInvariants();
        }

        // If none of the valid movement conditions are met, the move is invalid.
        return false;
    }

    // Ensures the invariants of the Knight are upheld (e.g., it has a valid color).
    private boolean checkInvariants() {
        return this.color != null;
    }

    // Sets the color of the Knight (used for testing or initialization purposes).
    public void setColor(Color color) {
        this.color = color;
    }
}
