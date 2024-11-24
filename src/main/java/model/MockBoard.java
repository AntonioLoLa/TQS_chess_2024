package model;

public class MockBoard extends Board {

    private int mockSizeRows = 8;
    private int mockSizeCols = 8;
    private Square[][] mockSquares;

    // //Simulate Board
    public MockBoard() {
        mockSquares = new Square[mockSizeRows][mockSizeCols];
        initializeMockBoard();
    }

    // Init Mock board
    private void initializeMockBoard() {
        for (int row = 0; row < mockSizeRows; row++) {
            for (int column = 0; column < mockSizeCols; column++) {
                mockSquares[row][column] = new Square(row, column);
            }
        }
    }

    //
    public void setMockSize(int rows, int cols) {
        mockSizeRows = rows;
        mockSizeCols = cols;
        mockSquares = new Square[rows][cols];
        initializeMockBoard();
    }

    @Override
    public int getSizeRows() {
        return mockSizeRows;
    }

    @Override
    public int getSizeCols() {
        return mockSizeCols;
    }

    @Override
    public Square getSquare(int row, int column) {
        if (row < 0 || row >= mockSizeRows || column < 0 || column >= mockSizeCols) {
            return null; // out of bound
        }
        return mockSquares[row][column];
    }

    // Add piece in Mock board
    public void setMockPieceAt(int row, int column, Piece piece) {
        if (row >= 0 && row < mockSizeRows && column >= 0 && column < mockSizeCols) {
            mockSquares[row][column].setPiece(piece);
        }
    }

    @Override
    public boolean hasKing(Color color) {
        return true; 
    }
}
