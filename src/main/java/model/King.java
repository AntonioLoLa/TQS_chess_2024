package model;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }
    
    @Override
    public String getName() {
        return (this.color == Color.WHITE ? "W.King" : "B.King");
    }

    @Override
    public boolean validMovement(Square destination, Board board) {
        if (destination == null) {
            return false;
        }

        if (destination.getRow() < 0 || destination.getRow() >= board.getSize() &&
            destination.getColumn() < 0 || destination.getColumn() >= board.getSize()) {
            return false;
        }

        int rowDelta = Math.abs(destination.getRow() - this.position.getRow());
        int colDelta = Math.abs(destination.getColumn() - this.position.getColumn());

        // Check that movement is one square in any direction
        if ((rowDelta <= 1 && colDelta <= 1) && !(rowDelta == 0 && colDelta == 0)) {
            Piece destinationPiece = destination.getPiece();

            // square occupied?
            if (destinationPiece != null && destinationPiece.getColor() == this.color) {
                return false;
            }

            if (!checkInvariants()) {
                return false;
            }
            return true;
        }

        return false;
    }

    private boolean checkInvariants() {
        return this.color != null;
    }
}
