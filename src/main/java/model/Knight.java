package model;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean validMovement(Square destination, Board board) {
        if (destination == null) {
            return false;
        }

        // Invariant check for board bounds
        if ((destination.getRow() < 0 || destination.getRow() >= board.getSize()) ||
            (destination.getColumn() < 0 || destination.getColumn() >= board.getSize())) {
            return false;
        }

        int rowDelta = Math.abs(destination.getRow() - this.position.getRow());
        int colDelta = Math.abs(destination.getColumn() - this.position.getColumn());

        // Validate "L" shape movement
        if ((rowDelta == 2 && colDelta == 1) || (rowDelta == 1 && colDelta == 2)) {
            Piece destinationPiece = destination.getPiece();

            // TODO: check same color
            
            return checkInvariants();
        }

        return false;
    }

    // Invariant check method
    private boolean checkInvariants() {
        return this.color != null;
    }
}
