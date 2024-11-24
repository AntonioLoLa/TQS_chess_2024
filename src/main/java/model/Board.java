package model;

public class Board {
	private int sizeRows = 8;
	private int sizeCols = 8;
    private Square[][] squares;

    public Board() {
        squares = new Square[sizeRows][sizeCols];
        initializeBoard(sizeRows, sizeCols);
    }
    
    public Board(int rows, int cols, boolean empty) {
    	squares = new Square[rows][cols];
    	if (empty)
    		initializeEmptyBoard(rows, cols);
    	else
    		initializeBoard(rows, cols);
    }
    
    public void initializeEmptyBoard(int rows, int cols) {
    	for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                squares[row][column] = new Square(row, column);
            }
        }
    }

    public void initializeBoard(int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                squares[row][column] = new Square(row, column);
            }
        }

        // Initialize pieces
        for (int column = 0; column < 8; column++) {//Pawns
        	squares[1][column].setPiece(new Pawn(Color.WHITE));
            squares[6][column].setPiece(new Pawn(Color.BLACK));
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
    
    public int getSizeRows() {
        return sizeRows;
    }
    
    public int getSizeCols() {
        return sizeCols;
    }
    
    public void setSizeRows(int n) {
        this.sizeRows = n;
    }
    
    public void setSizeCols(int n) {
        this.sizeCols = n;
    }

    public Square getSquare(int row, int column) {
        if (row < 0 || row >= sizeRows || column < 0 || column >= sizeCols) {
            return null; // or throw an exception
        }
        return squares[row][column];
    }
    
    public Square[][] getSquares() {
    	return squares;
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
        for (int row = 0; row < sizeRows; row++) {
            for (int column = 0; column < sizeCols; column++) {
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
