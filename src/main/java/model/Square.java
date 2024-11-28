package model;

public class Square {
    // Represents the row index of this square on the board.
    private int row;

    // Represents the column index of this square on the board.
    private int column;

    // The piece currently occupying this square, or null if the square is empty.
    private Piece piece;

    // Constructor: initializes a square with specified row, column, and a piece.
    // Parameters:
    // - row: the row index of the square.
    // - column: the column index of the square.
    // - piece: the piece occupying the square (or null if empty).
    public Square(int row, int column, Piece piece) {
        this.row = row;
        this.column = column;
        this.piece = piece;
    }

    // Constructor: initializes a square with specified row and column, empty by default.
    // Parameters:
    // - row: the row index of the square.
    // - column: the column index of the square.
    public Square(int row, int column) {
        this(row, column, null);
    }

    // Getter: returns the piece occupying the square.
    // If the square is empty, this method returns null.
    public Piece getPiece() {
        return piece;
    }

    // Setter: sets a piece to occupy this square.
    // Parameters:
    // - piece: the piece to place on the square.
    // If the piece is not null, its position is updated to this square.
    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null) {
            piece.setPosition(this);
        }
    }

    // Getter: returns the row index of this square.
    public int getRow() {
        return row;
    }

    // Getter: returns the column index of this square.
    public int getColumn() {
        return column;
    }

    // Checks if the square is occupied by a piece.
    // Returns true if there is a piece on the square, otherwise false.
    public boolean isOccupied() {
        return piece != null;
    }
}
