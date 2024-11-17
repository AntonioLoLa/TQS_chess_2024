package model;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }
    
    @Override
    public String getName() {
        return (this.color == Color.WHITE ? "W.Pawn" : "B.Pawn");
    }

	@Override
	public boolean validMovement(Square destination, Board board) {
		if (destination == null) {
            return false; // Return false for out-of-bounds
        }

        // Invariants to ensure destination is within bounds of the board
		if ((destination.getRow() < 0 || destination.getRow() >= board.getSize()) && destination.getColumn() < 0 || 
				destination.getColumn() >= board.getSize()) {
		    return false;
		}

        
        // Basic logic for pawn movement (only forward, or diagonal attack)
        int rowDelta = destination.getRow() - this.position.getRow();
        int colDelta = Math.abs(destination.getColumn() - this.position.getColumn());
        if (color == Color.WHITE) {
            if (rowDelta == -1 && colDelta == 0 && !destination.isOccupied()) {
                return true; // Move forward
            } else if (rowDelta == -2 && colDelta == 0 && this.position.getRow() == 6 &&
                    !destination.isOccupied() && !board.getSquare(this.position.getRow() - 1, this.position.getColumn()).isOccupied()) {
               return true; //first move
	          } else if (rowDelta == -1 && colDelta == 1 && destination.isOccupied() && destination.getPiece().getColor() == Color.BLACK) {
	                return true; // Diagonal attack
            }
        } else {
            if (rowDelta == 1 && colDelta == 0 && !destination.isOccupied()) {
                return true; // Move forward
            } else if (rowDelta == 2 && colDelta == 0 && this.position.getRow() == 1 &&
                    !destination.isOccupied() && !board.getSquare(this.position.getRow() + 1, this.position.getColumn()).isOccupied()) {
               return true; //first move
            } else if (rowDelta == 1 && colDelta == 1 && destination.isOccupied() && destination.getPiece().getColor() == Color.WHITE) {
                return true; // Diagonal attack
            }
        }
        

        return false;
    }
}
