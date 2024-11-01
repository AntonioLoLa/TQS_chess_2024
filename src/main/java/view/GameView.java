package view;

import model.Board;
import model.Player;

public interface GameView {
    void displayBoard(Board board);
    void displayTurn(Player player);
    void displayGameOver(Player winner);
    int[] getMoveInput();
}
