package model;

public class Queen extends Piece {

	// Constructor to initialize the Queen with a specific color.
    public Queen(Color color) {
        super(color);
    }
    
    @Override
    public String getName() {
        return (this.color == Color.WHITE ? "W.Queen" : "B.Queen");
    }


    @Override
    public boolean validMovement(Square destination, Board board) {
    	// Invariant and pre-conditions: 
    	// If the destination is null, the move is invalid.
    	// Check if the destination is within the bounds of the board.
        // If the row or column is out of range, the move is invalid.
    	// Invalid if queen does not have color.
    	if (destination == null || destination.getRow() < 0 
        		|| destination.getRow() >= board.getSizeRows() && destination.getColumn() < 0 
        		|| destination.getColumn() >= board.getSizeCols() || !checkInvariants()) {
            return false;
        }

    	// Calculate the row and column differences between the current position and the destination.
        int rowDelta = Math.abs(destination.getRow() - this.position.getRow());
        int colDelta = Math.abs(destination.getColumn() - this.position.getColumn());

        // Can move in straight lines or diagonals
        if (rowDelta == colDelta || rowDelta == 0 || colDelta == 0) {
            // Determine the direction of movement in rows
            int rowStep = Integer.compare(destination.getRow(), this.position.getRow());
            // Determine the direction of movement in columns
            int colStep = Integer.compare(destination.getColumn(), this.position.getColumn());

            // Check if there are pieces blocking the path
            int currentRow = this.position.getRow() + rowStep;
            int currentCol = this.position.getColumn() + colStep;
            while (currentRow != destination.getRow() || currentCol != destination.getColumn()) {
                if (board.getSquare(currentRow, currentCol).getPiece() != null) {
                    return false; // Path is blocked
                }
                currentRow += rowStep;
                currentCol += colStep;
            }

            // Ensure the destination square is not occupied by a piece of the same color
            Piece destinationPiece = destination.getPiece();
            if (destinationPiece != null && destinationPiece.getColor() == this.color) {
                return false; // Cannot capture a friendly piece
            }
            
            return true;
        }
        // If the movement is not a valid diagonal or straight line, return false
        return false;
    }

    // Invariant check method
    private boolean checkInvariants() {
        return this.color != null ? true : false;
    }
}
