package model;

public class King extends Piece {

	// Constructor to initialize the King with a specific color.
    public King(Color color) {
        super(color);
    }
    
    @Override
    public String getName() {
        return (this.color == Color.WHITE ? "W.King" : "B.King");
    }

    @Override
    public boolean validMovement(Square destination, Board board) {
    	// Invariants: If the destination is null, the move is invalid.
    	// Check if the destination is within the bounds of the board.
        // If the row or column is out of range, the move is invalid.
    	// Invalid if king does not have color.
        if (destination == null || destination.getRow() < 0 
        		|| destination.getRow() >= board.getSizeRows() && destination.getColumn() < 0 
        		|| destination.getColumn() >= board.getSizeCols() || !checkInvariants()) {
            return false;
        }
        
        // Calculate the row and column differences between the current position and the destination.
        int rowDelta = Math.abs(destination.getRow() - this.position.getRow());
        int colDelta = Math.abs(destination.getColumn() - this.position.getColumn());

        // Check that movement is one square in any direction
        if ((rowDelta <= 1 && colDelta <= 1) && !(rowDelta == 0 && colDelta == 0)) {
            Piece destinationPiece = destination.getPiece();

            // Check if the destination square is occupied.
            if (destinationPiece != null && destinationPiece.getColor() == this.color) {
                return false;
            }

            return true;
        }

        // If the move is more than one square in any direction, return false
        return false;
    }

    private boolean checkInvariants() {
        return this.color != null;
    }
}
