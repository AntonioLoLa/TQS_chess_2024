package model;

public abstract class Piece {
    // Color of the piece, either WHITE or BLACK.
    protected Color color;

    // Current position of the piece on the board.
    protected Square position;

    // Constructor: initializes the piece with its color.
    public Piece(Color color) {
        this.color = color;
    }

    // Getter: returns the color of the piece.
    public Color getColor() {
        return color;
    }

    // Setter: updates the current position of the piece on the board.
    // This method is typically called when the piece is moved to a new square.
    public void setPosition(Square position) {
        this.position = position;
    }

    // Returns the name of the piece as a string.
    // Default implementation returns an empty string.
    // This method should be overridden by subclasses to provide specific names (e.g., "Rook", "Bishop").
    public String getName() {
        return "";
    }

    // Abstract method: checks if the movement to the specified destination square is valid.
    // Must be implemented by each specific piece type (e.g., Rook, Bishop).
    // Parameters:
    // - destination: the target square to which the piece wants to move.
    // - board: the current state of the board, used to validate the move.
    public abstract boolean validMovement(Square destination, Board board);
}
