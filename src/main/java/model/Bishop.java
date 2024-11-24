package model;

public class Bishop extends Piece {

    // Constructor to initialize the Bishop with a specific color.
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public String getName() {
        // Returns the name of the piece, prefixed with its color.
        return (this.color == Color.WHITE ? "W.Bishop" : "B.Bishop");
    }

    @Override
    public boolean validMovement(Square destination, Board board) {
        // Null destination is not valid.
        if (destination == null) {   	
            return false;
        }

        // Check if the destination is within the bounds of the board.
        if ((destination.getRow() < 0 || destination.getRow() >= board.getSizeRows()) || 
            destination.getColumn() < 0 || destination.getColumn() >= board.getSizeCols()) {
            return false;
        }

        // Calculate the absolute row and column differences between current and destination positions.
        int rowDelta = Math.abs(destination.getRow() - this.position.getRow());
        int colDelta = Math.abs(destination.getColumn() - this.position.getColumn());

        // A bishop moves diagonally, so row and column deltas must be equal.
        if (rowDelta == colDelta) {
            // Determine the step direction for rows and columns.
            int rowStep = Integer.compare(destination.getRow(), this.position.getRow());
            int colStep = Integer.compare(destination.getColumn(), this.position.getColumn());

            // Traverse the diagonal path from the current position to the destination.
            int currentRow = this.position.getRow() + rowStep;
            int currentCol = this.position.getColumn() + colStep;
            while (currentRow == currentCol && currentRow != destination.getRow()) {
                // If any square along the path contains a piece, the move is invalid.
                if (board.getSquare(currentRow, currentCol).getPiece() != null) {
                    return false; 
                }
                currentRow += rowStep;
                currentCol += colStep;
            }

            // Ensure the destination square does not contain a piece of the same color.
            Piece destinationPiece = destination.getPiece();
            if (destinationPiece != null && destinationPiece.getColor() == this.color) {
                return false; 
            }

            // Validate that the piece's state satisfies all invariants.
            if (!checkInvariants()) {
                return false;
            }
            return true;
        }

        // If not a valid diagonal move, check invariants before returning false.
        if (!checkInvariants()) {
            return false;
        }
        return false;
    }

    // Ensures the invariants of the Bishop are upheld (e.g., it has a valid color).
    private boolean checkInvariants() {
        return this.color != null;
    }
}
