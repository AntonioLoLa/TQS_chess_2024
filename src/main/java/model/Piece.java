package model;

public abstract class Piece {
    protected Color color;
    protected Square position;

    public Piece(Color color) {this.color = color;}

    public Color getColor() {return color;}

    public void setPosition(Square position) {this.position = position;}

    public String getName() {return "";}
    
    public abstract boolean validMovement(Square destination, Board board);
}

