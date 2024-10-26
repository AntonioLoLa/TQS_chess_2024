package model;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean validMovement(Square destination, Board board) {
        if (destination == null) {
            return false;
        }

        // Invariant
        assert destination.getRow() >= 0 && destination.getRow() < board.getSize() :
               "Destination row is out of bounds.";
        assert destination.getColumn() >= 0 && destination.getColumn() < board.getSize() :
               "Destination column is out of bounds.";

        int rowDelta = Math.abs(destination.getRow() - this.position.getRow());
        int colDelta = Math.abs(destination.getColumn() - this.position.getColumn());

        // Allow movement only diagonally
        if (rowDelta == colDelta) {
            // Determine direction of movement in rows and columns
            int rowStep = Integer.compare(destination.getRow(), this.position.getRow());
            int colStep = Integer.compare(destination.getColumn(), this.position.getColumn());

            // Check if any piece blocks the path
            int currentRow = this.position.getRow() + rowStep;
            int currentCol = this.position.getColumn() + colStep;
            while (currentRow != destination.getRow() || currentCol != destination.getColumn()) {
                if (board.getSquare(currentRow, currentCol).getPiece() != null) {
                    return false; // Path is blocked
                }
                currentRow += rowStep;
                currentCol += colStep;
            }

            // Verify if destination has a piece of the same color
            Piece destinationPiece = destination.getPiece();
            if (destinationPiece != null && destinationPiece.getColor() == this.color) {
                return false; // Cannot capture a friendly piece
            }

            
            checkInvariants();
            return true;
        }

        checkInvariants();
        return false;
    }

    // Invariant check method
    private void checkInvariants() {
        assert this.color != null : "Bishop color cannot be null.";
    }
}
