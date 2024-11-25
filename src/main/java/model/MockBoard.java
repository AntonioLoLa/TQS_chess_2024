package model;

public class MockBoard extends Board {
    private boolean hasKingWhite = true;
    private boolean hasKingBlack = true;

    @Override
    public boolean hasKing(Color color) {
        if (color == Color.WHITE) {
            return hasKingWhite;
        } else {
            return hasKingBlack;
        }
    }

    public void setKingPresence(Color color, boolean presence) {
        if (color == Color.WHITE) {
            hasKingWhite = presence;
        } else {
            hasKingBlack = presence;
        }
    }

    @Override
    public boolean movePiece(Square origin, Square destination) {
        return true; // All valid movements
    }

 
}
