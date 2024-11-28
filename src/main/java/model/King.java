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
    	// Preconditions and invariant
        assert destination != null : "Destination square cannot be null.";
        assert destination.getRow() >= 0 && destination.getRow() < board.getSizeRows() 
            : "Row is out of bounds.";
        assert destination.getColumn() >= 0 && destination.getColumn() < board.getSizeCols() 
            : "Column is out of bounds.";
        assert checkInvariants() : "King's state invariant violated: color cannot be null.";
        
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
