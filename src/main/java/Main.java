

import controller.GameController;
import view.ConsoleGameView;

public class Main {
    public static void main(String[] args) {
        ConsoleGameView consoleView = new ConsoleGameView();
        GameController gameController = new GameController(consoleView);     
        gameController.startGame();
    }
}
