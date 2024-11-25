package model;

public class Player {
    // The color assigned to the player, either WHITE or BLACK.
    private Color color;

    // Constructor: initializes the player with a specific color.
    // Parameters:
    // - color: the color assigned to the player, determining which pieces they control.
    public Player(Color color) {
        this.color = color;
    }

    // Getter: returns the color of the player.
    // This method is used to identify which pieces belong to the player.
    public Color getColor() {
        return color;
    }
}
