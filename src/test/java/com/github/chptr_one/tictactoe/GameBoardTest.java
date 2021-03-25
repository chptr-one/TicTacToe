package com.github.chptr_one.tictactoe;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.chptr_one.tictactoe.Tile.*;
import static org.junit.Assert.*;

public class GameBoardTest {

    private final GameBoard initialBoard3 = new GameBoard(3);
    private final List<Coordinates> validCoordinates3 = new ArrayList<>(3 * 3);
    private final List<Coordinates> invalidCoordinates3;

    public GameBoardTest() {
        for (int row = 0; row < initialBoard3.getSize(); row++) {
            for (int col = 0; col < initialBoard3.getSize(); col++) {
                validCoordinates3.add(Coordinates.of(row, col));
            }
        }
        invalidCoordinates3 = List.of(
                Coordinates.of(-1, 0),
                Coordinates.of(0, -1),
                Coordinates.of(4, 0),
                Coordinates.of(0, 4)
        );
    }

    @Test
    public void acceptThrowsExceptionOnNullArg() {
        assertThrows(NullPointerException.class, () -> initialBoard3.accept(null));
    }

    @Test
    public void acceptReturnsNewBoardIfValidMove() {
        GameBoard actual = initialBoard3.accept(new Move(Coordinates.of(0, 0), Tile.X));
        Assert.assertNotEquals(actual, initialBoard3);
    }

    @Test
    public void acceptReturnsNullIfInvalidMove() {
        GameBoard actual = initialBoard3.accept(new Move(Coordinates.of(-1, 4), Tile.O));
        Assert.assertNull(actual);
    }

    @Test
    public void acceptReturnsNullIfTileIsBlank() {
        GameBoard actual = initialBoard3.accept(new Move(Coordinates.of(2, 2), BLANK));
        Assert.assertNull(actual);
    }

    @Test
    public void acceptReducesNumberOfEmptyCells() {
        GameBoard actual = initialBoard3.accept(new Move(Coordinates.of(1, 1), Tile.X));
        assertEquals(initialBoard3.getEmptyTilesCount() - 1, actual.getEmptyTilesCount());
    }

    @Test
    public void acceptWritesPlayersTileToRightPlace() {
        Tile[][] expected = {
                {BLANK, BLANK, BLANK},
                {BLANK, Tile.X, BLANK},
                {BLANK, BLANK, BLANK},
        };
        Tile[][] actual = initialBoard3.accept(new Move(Coordinates.of(1, 1), Tile.X)).getTiles();
        assertTrue(Arrays.deepEquals(expected, actual));
    }

    @Test
    public void isValidCoordinatesReturnsTrueOnValidInput() {
        for (var coord : validCoordinates3) {
            assertTrue(initialBoard3.isValidCoordinates(coord));
        }
    }

    @Test
    public void isValidCoordinatesReturnsFalseOnInvalidInput() {
        for (var coord : invalidCoordinates3) {
            assertFalse(initialBoard3.isValidCoordinates(coord));
        }
    }

    @Test
    public void isValidCoordinatesThrowsExceptionOnNullArg() {
        assertThrows(NullPointerException.class, () -> initialBoard3.isValidMove(null));
    }

    @Test
    public void isValidMoveThrowExceptionOnNullArg() {
        assertThrows(NullPointerException.class, () -> initialBoard3.isValidMove(null));
    }

    @Test
    public void isValidMoveReturnsTrueOnValidMoves() {
        List<Move> validMoves = validCoordinates3.stream()
                .map(coord -> new Move(coord, Tile.X))
                .collect(Collectors.toList());

        for (var move : validMoves) {
            assertTrue(initialBoard3.isValidMove(move));
        }
    }

    @Test
    public void isValidMovesCallsIsValidCoordinatesMethod() {
        GameBoard mock = Mockito.mock(GameBoard.class);
        mock.isValidMove(new Move(Coordinates.of(1, 1), Tile.X));
        Mockito.verify(mock).isValidMove(Mockito.any());
        Mockito.verifyNoMoreInteractions(mock);
    }

    @Test
    public void isValidMoveReturnsFalseIfTargetTileNotBlank() {
        Move move = new Move(Coordinates.of(2, 2), Tile.O);
        GameBoard board = initialBoard3.accept(move);
        assertFalse(board.isValidMove(move));
    }

    @Test
    public void isValidMoveReturnsFalseIfPlayersTileIsBlank() {
        Move move = new Move(Coordinates.of(0, 2), BLANK);
        assertFalse(initialBoard3.isValidMove(move));
    }

    @Test
    public void hasPossibleMoves() {
        assertTrue(initialBoard3.hasPossibleMoves());
    }

    @Test
    public void getTilesCopiesArray() {
        Tile[][] a1 = initialBoard3.getTiles();
        Tile[][] a2 = initialBoard3.getTiles();
        assertNotSame(a1, a2);
    }

    @Test
    public void hasCompletedLineReturnsFalse() {
        assertFalse(initialBoard3.hasCompletedLine(X));
        Tile[][] tiles = {
                {X, O, X},
                {X, O, O},
                {O, X, X}
        };
        GameBoard tieBoard = new GameBoard(tiles);
        assertFalse(tieBoard.hasCompletedLine(O));
        assertFalse(tieBoard.hasCompletedLine(X));
    }

    @Test
    public void hasCompletedLineReturnsTrue() {
        Tile[][][] completedLines = {
                {
                        {X, X, X},
                        {O, O, BLANK},
                        {BLANK, BLANK, BLANK}
                },
                {
                        {O, O, BLANK},
                        {X, X, X},
                        {BLANK, BLANK, BLANK}
                },
                {
                        {BLANK, BLANK, BLANK},
                        {O, O, BLANK},
                        {X, X, X}
                },
                {
                        {X, O, BLANK},
                        {X, O, BLANK},
                        {X, BLANK, BLANK}
                },
                {
                        {O, X, BLANK},
                        {O, X, BLANK},
                        {BLANK, X, BLANK}
                },
                {
                        {BLANK, O, X},
                        {BLANK, O, X},
                        {BLANK, BLANK, X}
                },
                {
                        {X, BLANK, O},
                        {BLANK, X, BLANK},
                        {BLANK, O, X}
                },
                {
                        {BLANK, BLANK, X},
                        {O, X, O},
                        {X, BLANK, BLANK}
                }
        };
        for (Tile[][] tiles : completedLines) {
            GameBoard board = new GameBoard(tiles);
            assertTrue(board.hasCompletedLine(X));
        }
    }
}