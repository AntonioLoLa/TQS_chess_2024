package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    // **Black Box Tests**: Tests for expected behavior (functional tests).
    // Equivalence Partitions:
	    // Valid: Move straight, valid diagonal, capture opponent's piece
	    // Invalid: Move through pieces, capture same color piece
    @Test
    void testQueenGetName() {
    	// Test that the Queen's name includes its color prefix
        assertEquals("W.Queen", whiteQueen.getName());
        assertEquals("B.Queen", blackQueen.getName());
    }
    
    @Test
    void testWhiteQueenCanMoveStraight() {
    	// Test that the white queen can move in a straight line.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square destination = board.getSquare(4, 7); // Move straight to the right

        assertTrue(whiteQueen.validMovement(destination, board));
    }

    @Test
    void testWhiteQueenCanMoveDiagonally() {
    	// Test that the white queen can move diagonally.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square destination = board.getSquare(2, 2); // Move diagonally

        assertTrue(whiteQueen.validMovement(destination, board));
    }

    @Test
    void testWhiteQueenCanCaptureBlackQueen() {
    	// Test that the white queen can capture the black queen.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square enemySquare = board.getSquare(4, 7);
        enemySquare.setPiece(blackQueen); // Place a black queen to capture

        assertTrue(whiteQueen.validMovement(enemySquare, board));
    }

    @Test
    void testWhiteQueenCannotCaptureSameColorPiece() {
    	// Test that the white queen cannot capture a friendly piece.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square friendlySquare = board.getSquare(4, 7);
        friendlySquare.setPiece(new Pawn(Color.WHITE)); // Another white piece

        // Piece of the same color, so invalid movement
        assertFalse(whiteQueen.validMovement(friendlySquare, board));
        
        // Test invalid movement with a queen that has no color.
        Square start = board.getSquare(4, 4);
        start.setPiece(new Queen(null));
        assertFalse(start.getPiece().validMovement(friendlySquare, board));
    }

    // **White Box Tests**: Ensures path coverage and additional checks for valid and invalid moves
    
    @Test
    void testQueenCannotMoveThroughPieces() {
    	// Ensure that the queen cannot move through a piece.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square blockingSquare = board.getSquare(4, 5);
        blockingSquare.setPiece(new Pawn(Color.BLACK)); // Blocked by a pawn
        Square destination = board.getSquare(4, 7); // Attempting to move straight

        assertFalse(whiteQueen.validMovement(destination, board));
    }

    @Test
    void testQueenCannotMoveDiagonallyThroughPieces() {
    	// Ensure the queen cannot move diagonally through a piece.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square blockingSquare = board.getSquare(3, 5);
        blockingSquare.setPiece(new Pawn(Color.BLACK)); // Blocked by a pawn
        Square destination = board.getSquare(2, 6); // Attempting diagonal move

        assertFalse(whiteQueen.validMovement(destination, board));
    }
    
    @Test
    void testBlackQueenCannotMoveToASpecificPosition() {
    	// Ensure the black queen cannot move to a position already occupied by a same-color piece
        Square start = board.getSquare(5, 0);
        start.setPiece(new Queen(Color.BLACK));
        Square destination = board.getSquare(3, 7);
        destination.setPiece(new Queen(Color.BLACK));// Same-color piece at destination
        assertFalse(start.getPiece().validMovement(destination, board));
        
        // Ensure the black queen cannot move to a position occupied by a piece with no color.
        Square destination2 = board.getSquare(3, 7);
        destination2.setPiece(new Queen(null));
        assertFalse(start.getPiece().validMovement(destination2, board));
    }
    
    @Test
    void testBishopCannotMoveInvalidPosition() {
    	// Ensure that the queen cannot move to an invalid position.
    	Square start = board.getSquare(1, 0);
    	start.setPiece(whiteQueen);
    	Square invalid = board.getSquare(-1, 0);
        assertFalse(start.getPiece().validMovement(invalid, board));
    }
    
    @Test
    void testOutOfBound() {
    	// Check that moves to out-of-bounds squares are invalid.
        Square start = board.getSquare(1, 0);
        start.setPiece(new Queen(Color.WHITE));
        Square outOfBoundsDestinationRow = new Square(9, 0);
        Square outOfBoundsDestinationRow2 = new Square(8, -1);
        Square outOfBoundsDestinationColumn = new Square(0, 9);
        Square outOfBoundsDestinationColumn2 = new Square(-1, 8);
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow2, board));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationColumn2, board));
        start.setPiece(new Queen(null));
        assertFalse(start.getPiece().validMovement(outOfBoundsDestinationRow, board));
    }

    // **Mock Tests**: Tests using mock objects for simulating specific behavior
    @Test
    void testMockWhiteQueenCanMoveStraight() {
        Queen queenMock = mock(Queen.class);
        Board boardMock = mock(Board.class);
        Square destination = new Square(4, 7);

        when(queenMock.validMovement(destination, boardMock)).thenReturn(true);

        assertTrue(queenMock.validMovement(destination, boardMock));
        verify(queenMock).validMovement(destination, boardMock);
    }

    @Test
    void testMockWhiteQueenCanMoveDiagonally() {
        Queen queenMock = mock(Queen.class);
        Board boardMock = mock(Board.class);
        Square destination = new Square(2, 2);

        when(queenMock.validMovement(destination, boardMock)).thenReturn(true);

        assertTrue(queenMock.validMovement(destination, boardMock));
        verify(queenMock).validMovement(destination, boardMock);
    }

    @Test
    void testMockWhiteQueenCannotCaptureSameColorPiece() {
        Queen queenMock = mock(Queen.class);
        Board boardMock = mock(Board.class);
        Square friendlySquare = new Square(4, 7);
        Queen sameColorPiece = new Queen(Color.WHITE);
        friendlySquare.setPiece(sameColorPiece); // Mock same color piece in destination

        when(queenMock.validMovement(friendlySquare, boardMock)).thenReturn(false);

        assertFalse(queenMock.validMovement(friendlySquare, boardMock));
        verify(queenMock).validMovement(friendlySquare, boardMock);
    }

    @Test
    void testMockQueenCannotMoveThroughPieces() {
    	Queen queenMock = mock(Queen.class);
        Board boardMock = mock(Board.class);
        Square origin = mock(Square.class);
        Square blockingSquare = mock(Square.class);
        Square destination = mock(Square.class);

        when(origin.getRow()).thenReturn(4);
        when(origin.getColumn()).thenReturn(4);
        when(destination.getRow()).thenReturn(4);
        when(destination.getColumn()).thenReturn(7);
        
        when(boardMock.getSquare(4, 5)).thenReturn(blockingSquare);
        when(blockingSquare.getPiece()).thenReturn(new Pawn(Color.BLACK));

        when(queenMock.validMovement(destination, boardMock)).thenReturn(false);

        assertFalse(queenMock.validMovement(destination, boardMock));
        verify(queenMock).validMovement(destination, boardMock);
    }

    @Test
    void testMockQueenRemainsWithinBoardBounds() {
        Queen queenMock = mock(Queen.class);
        Board boardMock = mock(Board.class);
        
        Square outOfBoundsSquare = new Square(-1, -1); // Mock out-of-bounds move
        when(queenMock.validMovement(outOfBoundsSquare, boardMock)).thenReturn(false);

        assertFalse(queenMock.validMovement(outOfBoundsSquare, boardMock));
        verify(queenMock).validMovement(outOfBoundsSquare, boardMock);
    }
}
