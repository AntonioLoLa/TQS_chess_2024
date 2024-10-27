package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KnightTest {

    private Board board;
    private Knight whiteKnight;
    private Knight blackKnight;

    @BeforeEach
    void setUp() {
        board = new Board();
        whiteKnight = new Knight(Color.WHITE);
        blackKnight = new Knight(Color.BLACK);
    }

    // **Black Box Tests**
    
    @Test
    void testBlackBoxValidLShapedMove() {
        Square origin = board.getSquare(2, 2);
        origin.setPiece(whiteKnight);
        Square destination = board.getSquare(4, 3); // L movement

        assertTrue(whiteKnight.validMovement(destination, board));
    }

    @Test
    void testBlackBoxInvalidStraightMove() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteKnight);
        Square destination = board.getSquare(4, 5); // Straight

        assertFalse(whiteKnight.validMovement(destination, board));
    }

    @Test
    void testBlackBoxCannotMoveOutOfBounds() {
        Square origin = board.getSquare(1, 0);
        origin.setPiece(whiteKnight);
        Square outOfBoundsSquare = new Square(-1, -2); // Outside bounds

        assertFalse(whiteKnight.validMovement(outOfBoundsSquare, board));
    }

}
