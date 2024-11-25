package model;

public class Pawn extends Piece {

    // Constructor to initialize the Pawn with a specific color.
    public Pawn(Color color) {
        super(color);
    }
    
    @Override
    public String getName() {
        // Returns the name of the piece, prefixed with its color.
        return (this.color == Color.WHITE ? "W.Pawn" : "B.Pawn");
    }

    @Override
    public boolean validMovement(Square destination, Board board) {
        // Invariant and pre-conditions: 
        // If the destination is null, the move is invalid.
        // Check if the destination is within the bounds of the board.
        // If the row or column is out of range, the move is invalid.
        // Invalid if pawn does not have color.
        if (destination == null || destination.getRow() < 0 
                || destination.getRow() >= board.getSizeRows() && destination.getColumn() < 0 
                || destination.getColumn() >= board.getSizeCols() || !checkInvariants()) {
            return false;
        }
        
        // Basic logic for pawn movement (only forward, or diagonal attack)
        int rowDelta = destination.getRow() - this.position.getRow();
        int colDelta = Math.abs(destination.getColumn() - this.position.getColumn());

        // If the pawn is black, it moves downward the board
        if (color == Color.BLACK) {
            // Move forward one square if the destination is not occupied
            if (rowDelta == -1 && colDelta == 0 && !destination.isOccupied()) {
                return true; // Move forward
            } 
            // First move: move forward two squares if no pieces are in the way
            else if (rowDelta == -2 && colDelta == 0 && this.position.getRow() == 6 &&
                    !destination.isOccupied() && !board.getSquare(this.position.getRow() - 1, 
                            this.position.getColumn()).isOccupied()) {
               return true; // First move
            } 
            // Diagonal attack: move one square diagonally to capture an opponent's piece
            else if (rowDelta == -1 && colDelta == 1 && destination.isOccupied() 
                    && destination.getPiece().getColor() == Color.WHITE) {
                return true; // Diagonal attack
            }
        } else {
            // If the pawn is white, it moves upward the board
            // Move forward one square if the destination is not occupied
            if (rowDelta == 1 && colDelta == 0 && !destination.isOccupied()) {
                return true; // Move forward
            } 
            // First move: move forward two squares if no pieces are in the way
            else if (rowDelta == 2 && colDelta == 0 && this.position.getRow() == 1 &&
                    !destination.isOccupied() && !board.getSquare(this.position.getRow() + 1, 
                            this.position.getColumn()).isOccupied()) {
               return true; // First move
            } 
            // Diagonal attack: move one square diagonally to capture an opponent's piece
            else if (rowDelta == 1 && colDelta == 1 && destination.isOccupied() 
                    && destination.getPiece().getColor() == Color.BLACK) {
                return true; // Diagonal attack
            }
        }
        // If none of the above conditions are met, the move is invalid
        return false;
    }

    // Ensures the invariants of the Pawn are upheld (e.g., it has a valid color).
    private boolean checkInvariants() {
        return this.color != null;
    }
}
