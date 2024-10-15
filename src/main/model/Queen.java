package main.model;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean validMovement(Square destination, Board board) {
        return false;
    }
}