package model;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }
    
    @Override
    public String getName() {
        return (this.color == Color.WHITE ? "W.Knight" : "B.Knight");
    }

    @Override
    public boolean validMovement(Square destination, Board board) {
        if (destination == null) {
            return false;
        }

        // Invariant check for board bounds
        if ((destination.getRow() < 0 || destination.getRow() >= board.getSizeRows()) ||
            (destination.getColumn() < 0 || destination.getColumn() >= board.getSizeCols())) {
            return false;
        }

        int rowDelta = Math.abs(destination.getRow() - this.position.getRow());
        int colDelta = Math.abs(destination.getColumn() - this.position.getColumn());

        // Validate "L" shape movement
        if ((rowDelta == 2 && colDelta == 1) || (rowDelta == 1 && colDelta == 2)) {
            Piece destinationPiece = destination.getPiece();

            // Check if destination has a piece of the same color
            if (destinationPiece != null && destinationPiece.getColor() == this.color) {
                return false; // Cannot capture a friendly piece
            }

            return checkInvariants();
        }

        return false;
    }

    // Invariant check method
    private boolean checkInvariants() {
        return this.color != null;
    }

	public void setColor(Color color) {
		this.color = color;
		
	}
}
