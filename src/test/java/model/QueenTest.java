package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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

   @Test
    void blackQueen() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square destination = board.getSquare(4, 7);

        assertTrue(whiteQueen.validMovement(destination, board));
    }


    @Test
    void testQueenDiagonalMove() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square destination = board.getSquare(2, 2);

        assertTrue(whiteQueen.validMovement(destination, board));
    }

    @Test
    void testQueenCaptureEnemyPiece() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square enemySquare = board.getSquare(4, 7);
        enemySquare.setPiece(blackQueen);

        assertTrue(whiteQueen.validMovement(enemySquare, board));
    }

    @Test
    void testQueenCannotCaptureSameColorPiece() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square friendlySquare = board.getSquare(4, 7);
        friendlySquare.setPiece(whiteQueen);
        // Piece of the same color, so invalid movement
        assertFalse(whiteQueen.validMovement(friendlySquare, board));
    }
    
    
    @Test
    void testQueenInteractionWithBoardUsingMock() {
        // Creating Queen Mock
        Queen queenMock = mock(Queen.class);
        Square origin = new Square(4, 4);
        Square destination = new Square(4, 7);

        when(queenMock.validMovement(destination, board)).thenReturn(true);

        // Verify that the mock behaves as expected
        assertTrue(queenMock.validMovement(destination, board));
        verify(queenMock).validMovement(destination, board);
    }

    @Test
    void testMockQueenCanCaptureEnemyPiece() {
        // Mocking the Queen
        Queen queenMock = mock(Queen.class);
        Square origin = board.getSquare(4, 4);
        origin.setPiece(queenMock);
        Square enemySquare = board.getSquare(4, 7);
        enemySquare.setPiece(blackQueen); // Place a black queen

        // Mocking the validMovement method
        when(queenMock.validMovement(enemySquare, board)).thenReturn(true);
        
        assertTrue(queenMock.validMovement(enemySquare, board)); // Assert movement
        verify(queenMock).validMovement(enemySquare, board); // Verify method call
    }

    @Test
    void testMockQueenCannotCaptureSameColorPiece() {
        // Mocking the Queen
        Queen queenMock = mock(Queen.class);
        Square origin = board.getSquare(4, 4);
        origin.setPiece(queenMock);
        Square friendlySquare = board.getSquare(4, 7);
        friendlySquare.setPiece(whiteQueen); // Another white queen

        // Mocking the validMovement method to return false
        when(queenMock.validMovement(friendlySquare, board)).thenReturn(false);
        
        assertFalse(queenMock.validMovement(friendlySquare, board)); // Assert cannot capture
        verify(queenMock).validMovement(friendlySquare, board); // Verify method call
    }
}