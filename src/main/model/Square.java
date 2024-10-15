package main.model;

public class Square {
    private int row;
    private int column;
    private Piece piece;

    public Square(int fila, int columna) {
        this.row = fila;
        this.column = columna;
        this.piece = null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public boolean isOccupied() {
    	return false;
    }
}
