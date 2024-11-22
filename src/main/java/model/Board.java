package model;

public class Board {
	private final int size = 8;
    private Square[][] squares;

    public Board() {
        squares = new Square[8][8];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                squares[row][column] = new Square(row, column);
            }
        }

        // Initialize pieces: Example of pawns
        for (int column = 0; column < 8; column++) {
        	squares[1][column].setPiece(new Pawn(Color.WHITE));  // White pawns
            squares[6][column].setPiece(new Pawn(Color.BLACK));  // Black pawns
        }

        // Initialize rooks
        squares[0][0].setPiece(new Rook(Color.WHITE));
        squares[0][7].setPiece(new Rook(Color.WHITE));
        squares[7][0].setPiece(new Rook(Color.BLACK));
        squares[7][7].setPiece(new Rook(Color.BLACK));

        // Initialize knights
        squares[0][1].setPiece(new Knight(Color.WHITE));
        squares[0][6].setPiece(new Knight(Color.WHITE));
        squares[7][1].setPiece(new Knight(Color.BLACK));
        squares[7][6].setPiece(new Knight(Color.BLACK));

        // Initialize bishops
        squares[0][2].setPiece(new Bishop(Color.WHITE));
        squares[0][5].setPiece(new Bishop(Color.WHITE));
        squares[7][2].setPiece(new Bishop(Color.BLACK));
        squares[7][5].setPiece(new Bishop(Color.BLACK));

        // Initialize queens
        squares[0][3].setPiece(new Queen(Color.WHITE));
        squares[7][3].setPiece(new Queen(Color.BLACK));

        // Initialize kings
        squares[0][4].setPiece(new King(Color.WHITE));
        squares[7][4].setPiece(new King(Color.BLACK));
    }
    
    public int getSize() {
        return size;
    }

    public Square getSquare(int row, int column) {
        if (row < 0 || row >= size || column < 0 || column >= size) {
            return null; // or throw an exception
        }
        return squares[row][column];
    }

    public boolean movePiece(Square origin, Square destination) {
        if (origin.getPiece() != null && origin.getPiece().validMovement(destination, this)) {
            destination.setPiece(origin.getPiece());
            origin.setPiece(null);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean hasKing(Color color) {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                Square square = squares[row][column];
                // Check if the square contains a piece that is a King of the specified color
                if (square.getPiece() instanceof King && square.getPiece().getColor() == color) {
                    return true;
                }
            }
        }
        return false;
    }
}
