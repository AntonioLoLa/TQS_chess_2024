package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PawnTest {

    private Board board;
    private Pawn whitePawn;
    private Pawn blackPawn;


    @BeforeEach
    void setUp() {
    	// Initialize a board and create two pawns, one for each color.
        board = new Board();
        whitePawn = new Pawn(Color.WHITE);
        blackPawn = new Pawn(Color.BLACK);
    }
    
    // **Black Box Tests - Black Pawn**
    // Equivalence partitions:
    	// - Valid: One square forward movement
    	//      - Limit and boundary values:
    	//          - ((6,0),(5,0))
    	// - Valid: Two square forward movement
    	//      - Limit and boundary values:
    	//          - ((6,0), (4,0))
    	// - Valid: Can capture one square diagonal
    	//      - Limit and boundary values:
    	//          - ((6,0),(5,1)) where in (5,1) there is an enemy piece
    	// - Invalid: Backwards movement
    	//      - ((5,0),(6,0))
    	// - Invalid: Can't capture same piece color
    	//      - ((5,1),(4,0)) where in (4,0) there is a same color piece
    	// - Invalid: Move diagonally without attacking
    	//      - ((6,0),(5,1)) where (5,1) has no piece
    	// - Invalid: Out-of-bounds movement
    	//      - ((6,0),(-1,0))
    	// - Invalid: Two square forward movement blocked by enemy piece
    	//      - ((6,0),(4,0) but in (5,0) there is an enemy piece blocking)
    	// - Invalid: One square forward movement blocked
    	//      - ((6,0),(5,0) but in (5,0) there is an enemy piece blocking)
    	// - Invalid: Two square diagonal movement
    	//      - ((6,0),(4,1))
    	// - Invalid: Two square forward movement without being in starting position
    	//      - ((5,0),(3,0))
    	// - Invalid: Forward movement blocked by same color piece
    	//      - ((6,0),(4,0)) but in (4,0) there is a same color piece

  //For constructor, check colors
    @Test
    void testPawntGetColors() {
    	// Verify that the pawn's color.
        assertEquals(Color.WHITE, whitePawn.getColor());
        assertEquals(Color.BLACK, blackPawn.getColor());
    }
    @Test
    void testPawnhtGetPositionInBoard() {
    	// Verify that the pawn's initial position.
        assertEquals(whitePawn.getName(), board.getSquare(1, 3).getPiece().getName());
        assertEquals(blackPawn.getName(), board.getSquare(6, 3).getPiece().getName());
    }
    
    @Test
    void testPawnGetName() {
        assertEquals("W.Pawn", whitePawn.getName());
        assertEquals("B.Pawn", blackPawn.getName());
    }
    
    @Test
    void testBlackPawnValidMovementForwardOneSquare() {
        Square start = board.getSquare(6, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(5, 0); // One movement forward
        assertTrue(start.getPiece().validMovement(destination, board), "Black Pawn should be able to move one square forward.");
    }

    @Test
    void testBlackPawnValidMovementForwardTwoSquares() {
        Square start = board.getSquare(6, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(4, 0); // Two movement forward
        assertTrue(start.getPiece().validMovement(destination, board), "Black Pawn should be able to move two squares forward on its first move.");
    }

    @Test
    void testBlackPawnDiagonalAttack() {
        Square start = board.getSquare(6, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(5, 1);
        destination.setPiece(new Pawn(Color.WHITE)); // Oponent in diagonal
        assertTrue(start.getPiece().validMovement(destination, board), "Black Pawn should be able to attack diagonally.");
    }

    @Test
    void testBlackPawnCannotMoveBackward() {
        Square start = board.getSquare(5, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(6, 0); // Try to move backward
        assertFalse(start.getPiece().validMovement(destination, board), "Black Pawn cannot move backward.");
    }

    @Test
    void testBlackPawnCannotCaptureSameColorPiece() {
        Square start = board.getSquare(5, 1);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(4, 0);
        destination.setPiece(new Pawn(Color.BLACK)); // Blocked by the same colour
        assertFalse(start.getPiece().validMovement(destination, board), "Black Pawn cannot capture its own piece.");
    }

    @Test
    void testBlackPawnInvalidDiagonalMove() {
        Square start = board.getSquare(6, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(5, 1); // No piece to attack
        assertFalse(start.getPiece().validMovement(destination, board), "Black Pawn cannot move diagonally without capturing.");
    }

    @Test
    void testBlackPawnCannotMoveToOutOfBounds() {
        Square start = board.getSquare(6, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square invalidDestination = board.getSquare(-1, 0); // Out of bound
        assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(invalidDestination, board))
                .getMessage().contains("Destination square cannot be null."),
                "Error message should indicate that color cannot be nulls.");    }

    @Test
    void testBlackPawnDoubleForwardBlocked() {
        Square start = board.getSquare(6, 0);
        start.setPiece(new Pawn(Color.BLACK));
        board.getSquare(5, 0).setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(4, 0);
        assertFalse(start.getPiece().validMovement(destination, board), "Black Pawn cannot move double forward if blocked.");
    }
    
    @Test
    void testBlackPawnCannotMoveForwardForBeingBlocked() {
        Square start = board.getSquare(5, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(4, 0); 
        destination.setPiece(new Pawn(Color.BLACK));
        assertFalse(start.getPiece().validMovement(destination, board), "Black Pawn should not be able to move one square forward.");
    }
    
    @Test
    void testBlackPawnCannotMoveTwoForwardInDiagonal() {
        Square start = board.getSquare(6, 0);
        Square destination = board.getSquare(4, 1); 
        assertFalse(start.getPiece().validMovement(destination, board), "Black Pawn should not be able to move two squares forward.");
    }
    
    @Test
    void testBlackPawnCannotMoveTwoForwardForNotBeingInStartingPosition() {
        Square start = board.getSquare(5, 0);
        start.setPiece(new Pawn(Color.BLACK));
        Square destination = board.getSquare(3, 0); 
        assertFalse(start.getPiece().validMovement(destination, board), "Black Pawn should not be able to move two squares forward.");
    }
    
    @Test
    void testBlackPawnCannotMoveTwoForwardForBeingBlockBySameColor() {
        Square start = board.getSquare(6, 0);
        Square destination = board.getSquare(4, 0); 
        destination.setPiece(new Pawn(Color.WHITE));
        assertFalse(start.getPiece().validMovement(destination, board), "Black Pawn should not be able to move two squares forward.");
    }

    // **Black Box Tests - White pawn** 
    // Equivalence partitions 
    	// - Valid: One square forward movement 
    	//      - ((1,0),(2,0)) 
    	// - Valid: Two square forward movement 
    	//      - ((1,0), (3,0)) 
    	// - Valid: Can capture one square diagonal 
    	//      - ((1,0),(2,1)) where in (2,1) there is an enemy piece 
    	// - Invalid: Backwards movement 
    	//      - ((2,0),(1,0)) 
    	// - Invalid: Can't capture same piece color 
    	//      - ((2,1),(3,0)) where in (3,0) there is a same color piece 
    	// - Invalid: Move diagonally without attacking 
    	//      - ((1,0),(2,1)) where (2,1) has no piece 
    	// - Invalid: Out-of-bounds movement 
    	//      - ((1,0),(8,0)) 
    	// - Invalid: Two square forward movement blocked by enemy piece 
    	//      - ((1,0),(3,0)) but in (2,0) there is an enemy piece blocking 
    	// - Invalid: One square forward movement blocked 
    	//      - ((3,0),(4,0)) but in (4,0) there is an enemy piece blocking 
    	// - Invalid: Two square diagonal movement 
    	//      - ((1,0),(3,1)) 
    	// - Invalid: Two square forward movement without being in starting position 
    	//      - ((2,0),(4,0)) 
    	// - Invalid: Forward movement blocked by same color piece 
    	//      - ((1,0),(3,0)) but in (3,0) there is a same color piece

    @Test
    void testWhitePawnValidMovementForwardOneSquare() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(2, 0); 
        assertTrue(start.getPiece().validMovement(destination, board), "White Pawn should be able to move one square forward.");
    }
     

    @Test
    void testWhitePawnValidMovementForwardTwoSquares() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(3, 0); 
        assertTrue(start.getPiece().validMovement(destination, board), "White Pawn should be able to move two squares forward on its first move.");
    }

    @Test
    void testWhitePawnDiagonalAttack() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(2, 1);
        destination.setPiece(new Pawn(Color.BLACK));
        assertTrue(start.getPiece().validMovement(destination, board), "White Pawn should be able to attack diagonally.");
    }

    @Test
    void testWhitePawnCannotMoveBackward() {
        Square start = board.getSquare(2, 0);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(1, 0); 
        assertFalse(start.getPiece().validMovement(destination, board), "White Pawn cannot move backward.");
    }

    @Test
    void testWhitePawnCannotCaptureSameColorPiece() {
        Square start = board.getSquare(2, 1);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(3, 0);
        destination.setPiece(new Pawn(Color.WHITE)); 
        assertFalse(start.getPiece().validMovement(destination, board), "White Pawn cannot capture its own piece.");
    }

    @Test
    void testWhitePawnInvalidDiagonalMove() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(2, 1); 
        assertFalse(start.getPiece().validMovement(destination, board), "White Pawn cannot move diagonally without capturing.");
    }

    @Test
    void testPawnCannotMoveInvalidPosition() {
    	Square start = board.getSquare(1, 0);
    	Square invalid = board.getSquare(-1, 0);
    	assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(invalid, board))
                .getMessage().contains("Destination square cannot be null."),
                "Error message should indicate that destination cannot be null.");
    }

    @Test
    void testWhitePawnDoubleForwardBlocked() {
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.WHITE));
        board.getSquare(2, 0).setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(3, 0);
        assertFalse(start.getPiece().validMovement(destination, board), "White Pawn cannot move double forward if blocked.");
    }
    
    @Test
    void testWhitePawnCannotMoveForwardForBeingBlocked() {
        Square start = board.getSquare(3, 0);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(4, 0); 
        destination.setPiece(new Pawn(Color.WHITE));
        assertFalse(start.getPiece().validMovement(destination, board), "White Pawn should not be able to move one square forward.");
    }
    
    @Test
    void testWhitePawnCannotMoveTwoForwardInDiagonal() {
        Square start = board.getSquare(1, 0);
        Square destination = board.getSquare(3, 1); 
        assertFalse(start.getPiece().validMovement(destination, board), "White Pawn should not be able to move two squares forward.");
    }
    
    @Test
    void testWhitePawnCannotMoveTwoForwardForNotBeingInStartingPosition() {
        Square start = board.getSquare(2, 0);
        start.setPiece(new Pawn(Color.WHITE));
        Square destination = board.getSquare(4, 0); 
        assertFalse(start.getPiece().validMovement(destination, board), "White Pawn should not be able to move two squares forward.");
    }
    
    @Test
    void testWhitePawnCannotMoveTwoForwardForBeingBlockBySameColor() {
        Square start = board.getSquare(1, 0);
        Square destination = board.getSquare(3, 0); 
        destination.setPiece(new Pawn(Color.WHITE));
        assertFalse(start.getPiece().validMovement(destination, board), "White Pawn should not be able to move two squares forward.");
    }
    
    // **White Box Tests** - More tests to ensure 100% path coverage
    
    @Test
    void testPawnCannotMoveBackward() {
        Square blackPawnSquare = new Square(5, 0, new Pawn(Color.BLACK)); 
        Square pawnBehind = new Square(6, 0); 

        board.getSquare(5, 0).setPiece(blackPawnSquare.getPiece()); 
        board.getSquare(6, 0).setPiece(pawnBehind.getPiece());

        assertFalse(board.movePiece(blackPawnSquare, pawnBehind));
    }

    @Test
    void testOutOfBound() {
    	// Check that moves to out-of-bounds squares are invalid.
        Square start = board.getSquare(1, 0);
        start.setPiece(new Pawn(Color.WHITE));
        
        Square outOfBoundsDestinationRow = new Square(9, 0); // Row >= 8
        Square outOfBoundsDestinationRow2 = new Square(-1, 7); // Row < 0
        Square outOfBoundsDestinationColumn = new Square(0, 9); // Column >= 8
        Square outOfBoundsDestinationColumn2 = new Square(7, -1); // Column < 0
        
        assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(outOfBoundsDestinationRow, board))
                .getMessage().contains("out of bounds"),
                "Error message should indicate that the row is out of bounds.");

            assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(outOfBoundsDestinationRow2, board))
                .getMessage().contains("out of bounds"),
                "Error message should indicate that the row is out of bounds.");

            assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(outOfBoundsDestinationColumn, board))
                .getMessage().contains("out of bounds"),
                "Error message should indicate that the column is out of bounds.");

            assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(outOfBoundsDestinationColumn2, board))
                .getMessage().contains("out of bounds"),
                "Error message should indicate that the column is out of bounds.");
    }
    
    @Test
    void testPawnWithoutColor() {

        // Test valid movement with a pawn that has no color.
        Square start = board.getSquare(6, 0);
        Square dest = board.getSquare(4, 0);
        start.setPiece(new Pawn(null));
        assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(dest, board))
                .getMessage().contains("Pawn's state invariant violated: color cannot be null."),
                "Error message should indicate that color cannot be null.");
        
        // Test invalid movement with a pawn that has no color.
        Square desti = board.getSquare(3, 0);
        start.setPiece(new Pawn(null));
        assertTrue(assertThrows(AssertionError.class, 
                () -> start.getPiece().validMovement(desti, board))
                .getMessage().contains("Pawn's state invariant violated: color cannot be null."),
                "Error message should indicate that color cannot be null.");
    }
    
}
