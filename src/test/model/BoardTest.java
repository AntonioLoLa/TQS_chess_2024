package test.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.model.Board;
import main.model.Color;
import main.model.Pawn;
import main.model.Square;

class BoardTest {

	private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testBoardInitialization() {
        assertNotNull(board, "The board should be initialized");
    }

    @Test
    void testGetSquare() {
        Square square = board.getSquare(0, 0);
        assertNotNull(square, "Square at (0,0) should exist");

        Square invalidSquare = board.getSquare(-1, -1);
        assertNull(invalidSquare, "Invalid square positions should return null");
    }

    @Test
    void testMovePiece() {
        Square origin = board.getSquare(6, 0);
        Square destination = board.getSquare(5, 0);

        assertNotNull(origin.getPiece(), "Origin should have a piece");
        assertNull(destination.getPiece(), "Destination should be empty before the move");

        assertTrue(board.movePiece(origin, destination), "Move should be successful");

        assertNull(origin.getPiece(), "Origin should be empty after the move");
        assertNotNull(destination.getPiece(), "Destination should have the piece after the move");
    }
}
