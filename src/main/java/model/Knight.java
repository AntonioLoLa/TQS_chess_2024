package model;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean validMovement(Square destination, Board board) {
        return false;
    }

    // Invariant check method
    private boolean checkInvariants() {
        return this.color != null;
    }
}
