package main.model;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

	@Override
	public boolean validMovement(Square destination, Board board) {
        // Basic logic for pawn movement (only forward, or diagonal attack)
        int rowDelta = destination.getRow() - this.position.getRow();
        int colDelta = Math.abs(destination.getColumn() - this.position.getColumn());
        if (color == Color.WHITE) {
            if (rowDelta == -1 && colDelta == 0 && !destination.isOccupied()) {
                return true; // Move forward
            } else if (rowDelta == -1 && colDelta == 1 && destination.isOccupied() && destination.getPiece().getColor() == Color.BLACK) {
                return true; // Diagonal attack
            }
        } else {
            if (rowDelta == 1 && colDelta == 0 && !destination.isOccupied()) {
                return true; // Move forward
            } else if (rowDelta == 1 && colDelta == 1 && destination.isOccupied() && destination.getPiece().getColor() == Color.WHITE) {
                return true; // Diagonal attack
            }
        }
        return false;
    }
}
