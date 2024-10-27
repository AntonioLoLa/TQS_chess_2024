package model;

public class Rook extends Piece{
	
	public Rook(Color color) {
        super(color);
    }

	@Override
	public boolean validMovement(Square destination, Board board) {
		// TODO
		return false;
	}

}
