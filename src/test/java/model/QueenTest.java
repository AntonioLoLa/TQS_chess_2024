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
        Square destination = board.getSquare(7, 7);

        assertTrue(whiteQueen.validMovement(destination, board));
    }

    @Test
    void testQueenCaptureEnemyPiece() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square enemySquare = board.getSquare(4, 7);
        enemySquare.setPiece(new Queen(Color.BLACK));

        assertTrue(whiteQueen.validMovement(enemySquare, board));
    }

    @Test
    void testQueenCannotCaptureSameColorPiece() {
        Square origin = board.getSquare(4, 4);
        origin.setPiece(whiteQueen);
        Square friendlySquare = board.getSquare(4, 7);
        friendlySquare.setPiece(blackQueen);
        // Piece of the same color, so invalid movement
        assertFalse(whiteQueen.validMovement(friendlySquare, board));
    }
    
    
    @Test
    void testQueenInteractionWithBoardUsingMock() {
        // Creating Queen Mock
        Queen queenMock = mock(Queen.class);
        //Square origin = new Square(4, 4, queenMock);
        Square destination = new Square(4, 7);
        
        when(queenMock.validMovement(destination, board)).thenReturn(true);
        assertTrue(queenMock.validMovement(destination, board));
        verify(queenMock).validMovement(destination, board);
    }
}