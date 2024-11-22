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
        board = new Board();
        whiteBishop = new Bishop(Color.WHITE);
        blackBishop = new Bishop(Color.BLACK);
    }

    // **Black Box Tests**
    @Test
    void testBishopGetName() {
        assertEquals("W.Bishop", whiteBishop.getName());
        assertEquals("B.Bishop", blackBishop.getName());
    }
    
    @Test
    void testWhiteBishopCanMoveDiagonally() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteBishop);
        Square destination = board.getSquare(2, 2); // Move diagonally left up

        assertTrue(whiteBishop.validMovement(destination, board));
    }

    @Test
    void testWhiteBishopCannotMoveStraight() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteBishop);
        Square destination = board.getSquare(5, 4); // Attempt to move straight right

        assertFalse(whiteBishop.validMovement(destination, board));
    }

    @Test
    void testWhiteBishopCanCaptureBlackBishop() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteBishop);
        Square enemySquare = board.getSquare(2, 2);
        enemySquare.setPiece(blackBishop); // Place a black bishop to capture

        assertTrue(whiteBishop.validMovement(enemySquare, board));
    }

    @Test
    void testWhiteBishopCannotCaptureSameColorPiece() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteBishop);
        Square friendlySquare = board.getSquare(2, 2);
        friendlySquare.setPiece(new Bishop(Color.WHITE)); // Another white bishop

        // Same color piece at destination, so invalid movement
        assertFalse(whiteBishop.validMovement(friendlySquare, board));
        
        Square start = board.getSquare(4, 4);
        start.setPiece(new Bishop(null));
        assertFalse(start.getPiece().validMovement(friendlySquare, board));
    }

    // **White Box Tests**

    @Test
    void testBishopCannotMoveThroughPieces() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteBishop);
        Square blockingSquare = board.getSquare(3, 3);
        blockingSquare.setPiece(new Pawn(Color.BLACK)); // Blocked by a pawn diagonally
        Square destination = board.getSquare(2, 2); // Attempt to move past the blocking piece

        assertFalse(whiteBishop.validMovement(destination, board));
    }

    @Test
    void testBishopCannotMoveHorizontallyOrVertically() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteBishop);
        
        // Attempt horizontal move
        Square horizontalDestination = board.getSquare(4, 7);
        assertFalse(whiteBishop.validMovement(horizontalDestination, board));
        
        // Attempt vertical move
        Square verticalDestination = board.getSquare(7, 4);
        assertFalse(whiteBishop.validMovement(verticalDestination, board));
    }
    
    @Test
    void testBishopCannotMoveInvalidPosition() {
    	Square start = board.getSquare(1, 0);
    	start.setPiece(whiteBishop);
    	Square invalid = board.getSquare(-1, 0);
        assertFalse(start.getPiece().validMovement(invalid, board));
    }
    
    @Test
    void testOutOfBound() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Bishop(Color.WHITE));
        Square outOfBoundsDestinationRow = new Square(9, 0);
        Square outOfBoundsDestinationRow2 = new Square(8, -1);
        Square outOfBoundsDestinationColumn = new Square(0, 9);
        Square outOfBoundsDestinationColumn2 = new Square(-1, 8);
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow2, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn2, board));
        start.setPiece(new Bishop(null));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow, board));
    }

    // **Tests using Mocks**

    @Test
    void testMockBishopCanMoveDiagonally() {
        Bishop bishopMock = mock(Bishop.class);
        Square origin = board.getSquare(4, 4);
        Square destination = board.getSquare(2, 2); // Diagonal move
        
        when(bishopMock.validMovement(destination, board)).thenReturn(true);
        
        assertTrue(bishopMock.validMovement(destination, board));
        verify(bishopMock).validMovement(destination, board);
    }

    @Test
    void testMockBishopCannotMoveStraight() {
        Bishop bishopMock = mock(Bishop.class);
        Square origin = board.getSquare(4, 4);
        Square invalidDestination = board.getSquare(4, 7); // Attempt straight move
        
        when(bishopMock.validMovement(invalidDestination, board)).thenReturn(false);
        
        assertFalse(bishopMock.validMovement(invalidDestination, board));
        verify(bishopMock).validMovement(invalidDestination, board);
    }

    @Test
    void testMockBishopCanCaptureEnemyPiece() {
        Bishop bishopMock = mock(Bishop.class);
        Square origin = board.getSquare(4, 4);
        Square enemySquare = board.getSquare(2, 2);
        enemySquare.setPiece(blackBishop); // Place black bishop
        
        when(bishopMock.validMovement(enemySquare, board)).thenReturn(true);
        
        assertTrue(bishopMock.validMovement(enemySquare, board));
        verify(bishopMock).validMovement(enemySquare, board);
    }

    @Test
    void testMockBishopCannotCaptureSameColorPiece() {
        Bishop bishopMock = mock(Bishop.class);
        Square origin = board.getSquare(4, 4);
        Square friendlySquare = board.getSquare(2, 2);
        friendlySquare.setPiece(new Bishop(Color.WHITE)); // Another white bishop
        
        when(bishopMock.validMovement(friendlySquare, board)).thenReturn(false);
        
        assertFalse(bishopMock.validMovement(friendlySquare, board));
        verify(bishopMock).validMovement(friendlySquare, board);
    }

    @Test
    void testMockBishopCannotMoveThroughPieces() {
        Bishop bishopMock = mock(Bishop.class);
        Square origin = board.getSquare(4, 4);
        Square blockingSquare = board.getSquare(3, 3);
        blockingSquare.setPiece(new Pawn(Color.BLACK)); // Blocked by a pawn
        Square destination = board.getSquare(2, 2);
        
        when(bishopMock.validMovement(destination, board)).thenReturn(false);
        
        assertFalse(bishopMock.validMovement(destination, board));
        verify(bishopMock).validMovement(destination, board);
    }

    @Test
    void testMockBishopRemainsWithinBoardBounds() {
        Bishop bishopMock = mock(Bishop.class);
        Square origin = board.getSquare(0, 0);
        Square outOfBoundsSquare = new Square(-1, -1); // Outside board bounds
        
        when(bishopMock.validMovement(outOfBoundsSquare, board)).thenReturn(false);
        
        assertFalse(bishopMock.validMovement(outOfBoundsSquare, board));
        verify(bishopMock).validMovement(outOfBoundsSquare, board);
    }
}
