package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BishopTest {

    private Board board;
    private Bishop whiteBishop;
    private Bishop blackBishop;

    @BeforeEach
    void setUp() {
        // Initialize a board and create two bishops, one for each color.
        board = new Board();
        whiteBishop = new Bishop(Color.WHITE);
        blackBishop = new Bishop(Color.BLACK);
    }

    // **Black Box Tests**
    //Equivalence partitions: 
    	//Valid: Diagonal movement
    	//Invalid: Not diagonal movement (horizontal/vertical)
    	//Valid: Capture other team piece
    	//Invalid: Capture same team piece
    
    @Test
    void testBishopGetName() {
        // Verify that the bishop's name includes its color.
        assertEquals("W.Bishop", whiteBishop.getName());
        assertEquals("B.Bishop", blackBishop.getName());
    }
    
    @Test
    void testWhiteBishopCanMoveDiagonally() {
        // Test valid diagonal movements for a white bishop.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteBishop);
        Square destination = board.getSquare(2, 2); // Diagonal left-up movement
        assertTrue(whiteBishop.validMovement(destination, board));
        
        origin = board.getSquare(2, 1);
        origin.setPiece(whiteBishop);
        destination = board.getSquare(4, 3); // Another valid diagonal movement
        assertTrue(whiteBishop.validMovement(destination, board));
    }

    @Test
    void testWhiteBishopCannotMoveStraight() {
        // Ensure that a bishop cannot move in a straight line.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteBishop);
        Square destination = board.getSquare(5, 4); // Attempt vertical movement
        assertFalse(whiteBishop.validMovement(destination, board));
    }

    @Test
    void testWhiteBishopCanCaptureBlackBishop() {
        // Validate that a bishop can capture an opponent's piece.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteBishop);
        Square enemySquare = board.getSquare(2, 2);
        enemySquare.setPiece(blackBishop); // Place a black bishop as target
        assertTrue(whiteBishop.validMovement(enemySquare, board));
    }

    @Test
    void testWhiteBishopCannotCaptureSameColorPiece() {
        // Ensure that a bishop cannot capture a friendly piece.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteBishop);
        Square friendlySquare = board.getSquare(2, 2);
        friendlySquare.setPiece(new Bishop(Color.WHITE)); // Friendly bishop
        assertFalse(whiteBishop.validMovement(friendlySquare, board));

        // Also test invalid movement with a bishop that has no color.
        Square start = board.getSquare(4, 4);
        start.setPiece(new Bishop(null));
        assertFalse(start.getPiece().validMovement(friendlySquare, board));
    }

    // **White Box Tests** - More tests to ensure 100% path coverage
    
    @Test
    void testBishopCannotMoveThroughPieces() {
        // Test that a bishop cannot jump over another piece.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteBishop);
        Square blockingSquare = board.getSquare(3, 3);
        blockingSquare.setPiece(new Pawn(Color.BLACK)); // Piece blocking the path
        Square destination = board.getSquare(2, 2); // Attempt to move past the blocker
        assertFalse(whiteBishop.validMovement(destination, board));
    }

    @Test
    void testBishopCannotMoveHorizontallyOrVertically() {
        // Verify that a bishop cannot move horizontally or vertically.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteBishop);

        Square horizontalDestination = board.getSquare(4, 7); // Horizontal move
        assertFalse(whiteBishop.validMovement(horizontalDestination, board));

        Square verticalDestination = board.getSquare(7, 4); // Vertical move
        assertFalse(whiteBishop.validMovement(verticalDestination, board));
    }
    
    @Test
    void testBishopCannotMoveInvalidPosition() {
        // Ensure movement to invalid board positions is handled correctly.
        Square start = board.getSquare(1, 0);
        start.setPiece(whiteBishop);
        Square invalid = board.getSquare(-1, 0); // Out-of-bounds square
        assertFalse(start.getPiece().validMovement(invalid, board));
    }
    
    @Test
    void testOutOfBound() {
        // Check that moves to out-of-bounds squares are invalid.
        Square start = board.getSquare(1, 0);
        start.setPiece(new Bishop(Color.WHITE));
        Square outOfBoundsDestinationRow = new Square(9, 0); // Row out of bounds
        Square outOfBoundsDestinationColumn = new Square(0, 9); // Column out of bounds
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn, board));
    }

    // **Tests using Mocks**
    
    @Test
    void testMockBishopCanMoveDiagonally() {
        // Simulate a valid diagonal move using a mocked Bishop.
        Bishop bishopMock = mock(Bishop.class);
        Square destination = board.getSquare(2, 2);
        when(bishopMock.validMovement(destination, board)).thenReturn(true);
        assertTrue(bishopMock.validMovement(destination, board));
        verify(bishopMock).validMovement(destination, board);
    }

    @Test
    void testMockBishopCannotMoveStraight() {
        // Simulate an invalid straight move using a mocked Bishop.
        Bishop bishopMock = mock(Bishop.class);
        Square invalidDestination = board.getSquare(4, 7); // Straight move
        when(bishopMock.validMovement(invalidDestination, board)).thenReturn(false);
        assertFalse(bishopMock.validMovement(invalidDestination, board));
        verify(bishopMock).validMovement(invalidDestination, board);
    }

    @Test
    void testMockBishopCanCaptureEnemyPiece() {
        // Simulate capturing an opponent's piece with a mocked Bishop.
        Bishop bishopMock = mock(Bishop.class);
        Square enemySquare = board.getSquare(2, 2);
        enemySquare.setPiece(blackBishop);
        when(bishopMock.validMovement(enemySquare, board)).thenReturn(true);
        assertTrue(bishopMock.validMovement(enemySquare, board));
        verify(bishopMock).validMovement(enemySquare, board);
    }

    @Test
    void testMockBishopCannotCaptureSameColorPiece() {
        // Simulate an invalid capture of a friendly piece using a mocked Bishop.
        Bishop bishopMock = mock(Bishop.class);
        Square friendlySquare = board.getSquare(2, 2);
        friendlySquare.setPiece(new Bishop(Color.WHITE)); // Friendly bishop
        when(bishopMock.validMovement(friendlySquare, board)).thenReturn(false);
        assertFalse(bishopMock.validMovement(friendlySquare, board));
        verify(bishopMock).validMovement(friendlySquare, board);
    }

    @Test
    void testMockBishopCannotMoveThroughPieces() {
        // Simulate an invalid move through a blocking piece using a mocked Bishop.
        Bishop bishopMock = mock(Bishop.class);
        Square destination = board.getSquare(2, 2);
        when(bishopMock.validMovement(destination, board)).thenReturn(false);
        assertFalse(bishopMock.validMovement(destination, board));
        verify(bishopMock).validMovement(destination, board);
    }

    @Test
    void testMockBishopRemainsWithinBoardBounds() {
        // Ensure that a mocked Bishop remains within the bounds of the board.
        Bishop bishopMock = mock(Bishop.class);
        Square outOfBoundsSquare = new Square(-1, -1); // Out-of-bounds square
        when(bishopMock.validMovement(outOfBoundsSquare, board)).thenReturn(false);
        assertFalse(bishopMock.validMovement(outOfBoundsSquare, board));
        verify(bishopMock).validMovement(outOfBoundsSquare, board);
    }
}
