package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueenTest {

    private Board board;
    private Queen whiteQueen;
    private Queen blackQueen;

    @BeforeEach
    void setUp() {
        board = new Board();
        whiteQueen = new Queen(Color.WHITE);
        blackQueen = new Queen(Color.BLACK);
    }

    // **Black Box Tests**
    
    @Test
    void testWhiteQueenCanMoveStraight() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square destination = board.getSquare(4, 7); // Move straight right

        assertTrue(whiteQueen.validMovement(destination, board));
    }

    @Test
    void testWhiteQueenCanMoveDiagonally() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square destination = board.getSquare(2, 2); // Move diagonally

        assertTrue(whiteQueen.validMovement(destination, board));
    }

    @Test
    void testWhiteQueenCanCaptureBlackQueen() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square enemySquare = board.getSquare(4, 7);
        enemySquare.setPiece(blackQueen); // Place a black queen to capture

        assertTrue(whiteQueen.validMovement(enemySquare, board));
    }

    @Test
    void testWhiteQueenCannotCaptureSameColorPiece() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square friendlySquare = board.getSquare(4, 7);
        friendlySquare.setPiece(new Queen(Color.WHITE)); // Another white queen

        // Piece of the same color, so invalid movement
        assertFalse(whiteQueen.validMovement(friendlySquare, board));
    }

    // **White Box Tests**
    
    @Test
    void testQueenCannotMoveThroughPieces() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square blockingSquare = board.getSquare(4, 5);
        blockingSquare.setPiece(new Pawn(Color.BLACK)); // Blocked by a pawn
        Square destination = board.getSquare(4, 7); // Attempting to move straight

        assertFalse(whiteQueen.validMovement(destination, board));
    }

    @Test
    void testQueenCannotMoveDiagonallyThroughPieces() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square blockingSquare = board.getSquare(3, 5);
        blockingSquare.setPiece(new Pawn(Color.BLACK)); // Blocked by a pawn
        Square destination = board.getSquare(2, 6); // Attempting diagonal move

        assertFalse(whiteQueen.validMovement(destination, board));
    }

    // **Invariant Tests**
    @Test
    void testQueenCannotOccupySameSquare() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square destination = board.getSquare(4, 4); // Same square

        assertFalse(whiteQueen.validMovement(destination, board));
    }

    @Test
    void testQueenRemainsWithinBoardBounds() {
        Square origin = board.getSquare(0, 0);
        origin.setPiece(whiteQueen);
        
        // Attempt to move outside the board
        Square outOfBoundsSquare = board.getSquare(-1, -1); // Invalid move
        assertFalse(whiteQueen.validMovement(outOfBoundsSquare, board));

        outOfBoundsSquare = board.getSquare(8, 8); // Invalid move
        assertFalse(whiteQueen.validMovement(outOfBoundsSquare, board));
    }
}
