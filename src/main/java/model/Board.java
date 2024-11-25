package model;

public class Board {
    private int sizeRows = 8; // Default number of rows on the board.
    private int sizeCols = 8; // Default number of columns on the board.
    private Square[][] squares; // A 2D array representing the board's squares.

    // Default constructor that initializes an 8x8 board with pieces placed in their starting positions.
    public Board() {
        squares = new Square[sizeRows][sizeCols];
        initializeBoard(sizeRows, sizeCols);
    }

    // Constructor to initialize a board with custom dimensions. Can be empty or initialized with default pieces.
    public Board(int rows, int cols, boolean empty) {
        squares = new Square[rows][cols];
        if (empty) {
            initializeEmptyBoard(rows, cols);
        } else {
            initializeBoard(rows, cols);
        }
    }

    // Initializes an empty board with the specified number of rows and columns.
    public void initializeEmptyBoard(int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                squares[row][column] = new Square(row, column);
            }
        }
    }

    // Initializes the board with pieces in their starting positions for a standard chess game.
    public void initializeBoard(int rows, int cols) {
        // Create squares for the board.
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                squares[row][column] = new Square(row, column);
            }
        }

        // Place pawns on the second and seventh rows.
        for (int column = 0; column < 8; column++) {
            squares[1][column].setPiece(new Pawn(Color.WHITE));
            squares[6][column].setPiece(new Pawn(Color.BLACK));
        }

        // Place rooks in their starting positions.
        squares[0][0].setPiece(new Rook(Color.WHITE));
        squares[0][7].setPiece(new Rook(Color.WHITE));
        squares[7][0].setPiece(new Rook(Color.BLACK));
        squares[7][7].setPiece(new Rook(Color.BLACK));

        // Place knights in their starting positions.
        squares[0][1].setPiece(new Knight(Color.WHITE));
        squares[0][6].setPiece(new Knight(Color.WHITE));
        squares[7][1].setPiece(new Knight(Color.BLACK));
        squares[7][6].setPiece(new Knight(Color.BLACK));

        // Place bishops in their starting positions.
        squares[0][2].setPiece(new Bishop(Color.WHITE));
        squares[0][5].setPiece(new Bishop(Color.WHITE));
        squares[7][2].setPiece(new Bishop(Color.BLACK));
        squares[7][5].setPiece(new Bishop(Color.BLACK));

        // Place queens in their starting positions.
        squares[0][3].setPiece(new Queen(Color.WHITE));
        squares[7][3].setPiece(new Queen(Color.BLACK));

        // Place kings in their starting positions.
        squares[0][4].setPiece(new King(Color.WHITE));
        squares[7][4].setPiece(new King(Color.BLACK));
    }

    // Returns the number of rows on the board.
    public int getSizeRows() {
        return sizeRows;
    }

    // Returns the number of columns on the board.
    public int getSizeCols() {
        return sizeCols;
    }

    // Sets the number of rows on the board (adjusts dynamically if required).
    public void setSizeRows(int n) {
        this.sizeRows = n;
    }

    // Sets the number of columns on the board (adjusts dynamically if required).
    public void setSizeCols(int n) {
        this.sizeCols = n;
    }

    // Retrieves a specific square on the board by row and column indices.
    // Returns null if the indices are out of bounds.
    public Square getSquare(int row, int column) {
        if (row < 0 || row >= sizeRows || column < 0 || column >= sizeCols) {
            return null; // Or throw an exception for invalid indices.
        }
        return squares[row][column];
    }

    // Moves a piece from the origin square to the destination square if the move is valid.
    // Returns true if the move was successful, false otherwise.
    public boolean movePiece(Square origin, Square destination) {
        if (origin.getPiece() != null && origin.getPiece().validMovement(destination, this)) {
            destination.setPiece(origin.getPiece());
            origin.setPiece(null);
            return true;
        } else {
            return false;
        }
    }

    // Checks if the board contains a king of the specified color.
    public boolean hasKing(Color color) {
        for (int row = 0; row < sizeRows; row++) {
            for (int column = 0; column < sizeCols; column++) {
                Square square = squares[row][column];
                // Check if the square contains a king of the specified color.
                if (square.getPiece() instanceof King && square.getPiece().getColor() == color) {
                    return true;
                }
            }
        }
        return false;
    }
}