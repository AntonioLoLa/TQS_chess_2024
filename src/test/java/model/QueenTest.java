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
    	// Initialize a board and create two queens, one for each color.
        board = new Board();
        whiteQueen = new Queen(Color.WHITE);
        blackQueen = new Queen(Color.BLACK);
    }

    // **Black Box Tests**
    // Equivalence partitions:
	    // - Valid: Straight movement
	    //	 - Limit and boundary values:
	    //       - ((4,4),(4,7)) (horizontal move)
	    //       - ((4,4),(6,4)) (vertical move)
	    // - Valid: Diagonal movement
	    //   - Limit and boundary values:
	    //       - ((4,4),(2,2)) (diagonal up-left)
	    // - Valid: Capture opponent's piece
	    //   - Limit and boundary values:
	    //       - ((4,4),(4,7)) where (4,7) has an enemy piece
	    // - Invalid: Capture same-color piece
	    //   - Limit and boundary values:
	    //       - ((4,4),(4,7)) where (4,7) has a friendly piece
	    // - Invalid: Out-of-bounds moves
	    //   - Limit and boundary values:
	    //       - ((1,0),(-1,0)) (row out-of-bounds)
	    //       - ((1,0),(9,0)) (row out-of-bounds)
	    //       - ((1,0),(0,9)) (column out-of-bounds)
	    // - Invalid: Move through pieces
	    //   - Limit and boundary values:
	    //       - ((4,4),(4,7)) with blocking square at (4,5)


    
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
    void testWhiteQueenCanMoveHorizontal() {
    	// Test that the white queen can move in a horizontal line.
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square destination = board.getSquare(5, 4);

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
        assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(friendlySquare, board))
                .getMessage().contains("Queen's state invariant violated: color cannot be null."),
                "Error message should indicate that the row is out of bounds.");
    }
    
    @Test
    void testQueenCannotMoveOutOfBounds() {
        // Ensure movement to invalid board positions is handled correctly.
        Square start = board.getSquare(1, 0);
        start.setPiece(whiteQueen);
        Square invalid = board.getSquare(-1, 0); // Row out-of-bounds

        assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(invalid, board))
                .getMessage().contains("Destination square cannot be null."),
                "Error message should indicate that the row is out of bounds.");
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
    void testQueenOutOfBound() {
    	// Check that moves to out-of-bounds squares are invalid.
        Square start = board.getSquare(1, 0);
        start.setPiece(blackQueen);
        
        // Test out-of-bounds positions
        Square outOfBoundsDestinationRow = new Square(9, 0); // Row out of bounds
        Square outOfBoundsDestinationRow2 = new Square(7, -1); // Column < 0
        Square outOfBoundsDestinationColumn = new Square(0, 9); // Column >= 8
        Square outOfBoundsDestinationColumn2 = new Square(-1, 7); // Row < 0
        
        assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(outOfBoundsDestinationRow, board))
                .getMessage().contains("out of bounds"),
                "Error message should indicate that the row is out of bounds.");

            assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(outOfBoundsDestinationRow2, board))
                .getMessage().contains("out of bounds"),
                "Error message should indicate that the column is out of bounds.");

            assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(outOfBoundsDestinationColumn, board))
                .getMessage().contains("out of bounds"),
                "Error message should indicate that the column is out of bounds.");

            assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(outOfBoundsDestinationColumn2, board))
                .getMessage().contains("out of bounds"),
                "Error message should indicate that the row is out of bounds.");
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
