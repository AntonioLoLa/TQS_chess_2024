package model;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }
    
    @Override
    public String getName() {
        return (this.color == Color.WHITE ? "W.Rook" : "B.Rook");
    }

    @Override
    public boolean validMovement(Square destination, Board board) {
        if (destination == null) {
            return false; // out-of-bounds
        }

        // Invariant: The destination must be within the bounds of the board
        if ((destination.getRow() < 0 || destination.getRow() >= board.getSizeRows()) 
        		&& destination.getColumn() < 0 || 
                destination.getColumn() >= board.getSizeCols()) {
            return false;
        }

        int rowDelta = Math.abs(destination.getRow() - this.position.getRow());
        int colDelta = Math.abs(destination.getColumn() - this.position.getColumn());

        // Can move only in straight lines (either row or column must remain constant)
        if (rowDelta == 0 || colDelta == 0) {
            int rowStep = Integer.compare(destination.getRow(), this.position.getRow());
            int colStep = Integer.compare(destination.getColumn(), this.position.getColumn());

            // Check if there are pieces blocking the path
            int currentRow = this.position.getRow() + rowStep;
            int currentCol = this.position.getColumn() + colStep;
            while (currentRow != destination.getRow() 
            		|| currentCol != destination.getColumn()) {
                if (board.getSquare(currentRow, currentCol).getPiece() != null) {
                    return false; // Path is blocked
                }
                currentRow += rowStep;
                currentCol += colStep;
            }

            // Destination occupied by a piece of the same color?
            Piece destinationPiece = destination.getPiece();
            if (destinationPiece != null && destinationPiece.getColor() == this.color) {
                return false; // Cannot capture a friendly piece
            }

            // Check invariants at the end of the method
            if (!checkInvariants()) {
                return false;
            }
            return true; // Movement is valid
        }

        return false; // Movement is invalid
    }

    // Invariant check method
    private boolean checkInvariants() {
        return this.color != null;
    }
}
