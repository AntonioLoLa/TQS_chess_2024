package main.model;

public class Square {
    private int row;
    private int column;
    private Piece piece;

    public Square(int fila, int columna, Piece piece) {
        this.row = fila;
        this.column = columna;
        this.piece = piece;
    }
    
    public Square(int row, int column) {
        this(row, column, null);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
    	this.piece = piece;
        if (piece != null) {
            piece.setPosition(this);
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public boolean isOccupied() {
    	return piece != null;
    }
}
