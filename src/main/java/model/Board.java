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
            squares[1][column].setPiece(new Pawn(Color.BLACK));  // Black pawns
            squares[6][column].setPiece(new Pawn(Color.WHITE));  // White pawns
        }

        // Initialize other pieces
        squares[0][3].setPiece(new Queen(Color.BLACK)); // Black queen
        squares[7][3].setPiece(new Queen(Color.WHITE)); // White queen
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
}
