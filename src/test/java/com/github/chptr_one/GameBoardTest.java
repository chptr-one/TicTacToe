package com.github.chptr_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    private final GameBoard gameBoard3x3 = new GameBoard(3);

    @Test
    void getEmptyCellsReturnsRightNumberForEmptyBoard() {
        assertEquals(3 * 3, gameBoard3x3.getEmptyCells());
    }

    @Test
    void validCoordinatesReturnsTrue() {
        assertTrue(gameBoard3x3.isValidCoordinates(Position.of(0, 0)));
        assertTrue(gameBoard3x3.isValidCoordinates(Position.of(2, 2)));
    }

    @Test
    void invalidCoordinatesReturnsFalse() {
        assertFalse(gameBoard3x3.isValidCoordinates(Position.of(-1, 0)));
        assertFalse(gameBoard3x3.isValidCoordinates(Position.of(3, 0)));
        assertFalse(gameBoard3x3.isValidCoordinates(Position.of(0, -1)));
        assertFalse(gameBoard3x3.isValidCoordinates(Position.of(0, 3)));
    }

    @Test
    void setMarkInEmptyCell() {
        gameBoard3x3.setMark(Position.of(0, 0), Mark.X);
        assertEquals(Mark.X, gameBoard3x3.getBoard()[0][0]);
        assertEquals(3 * 3 - 1, gameBoard3x3.getEmptyCells());
    }

    @Test
    void setMarkDoNothingOnNonEmptyCell() {
        gameBoard3x3.setMark(Position.of(1, 1), Mark.O);
        gameBoard3x3.setMark(Position.of(1, 1), Mark.X);
        assertEquals(Mark.O, gameBoard3x3.getBoard()[1][1]);
        assertEquals(3 * 3 - 1, gameBoard3x3.getEmptyCells());
    }

    @Test
    void setMarkThrowsExceptionIfMarkIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> gameBoard3x3.setMark(Position.of(1, 1), null)
        );

        assertNull(gameBoard3x3.getBoard()[1][1]);
        assertEquals(3 * 3, gameBoard3x3.getEmptyCells());
    }

    @Test
    void setMarkTrowsExceptionIfInvalidPosition() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> gameBoard3x3.setMark(Position.of(-1, 3), Mark.O)
        );
    }

    @Test
    void unsetMarkUnsetsNonEmptyCell() {
        gameBoard3x3.setMark(Position.of(2, 2), Mark.X);
        gameBoard3x3.unsetMark(Position.of(2, 2));
        assertNull(gameBoard3x3.getBoard()[2][2]);
        assertEquals(3 * 3, gameBoard3x3.getEmptyCells());
    }

    @Test
    void unsetMarkDoNothingOnEmptyCell() {
        gameBoard3x3.unsetMark(Position.of(0, 0));
        assertEquals(3 * 3, gameBoard3x3.getEmptyCells());
    }
}