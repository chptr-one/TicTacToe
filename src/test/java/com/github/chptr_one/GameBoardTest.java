package com.github.chptr_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    private final GameBoard gameBoard3x3 = new GameBoard(3, 3);

    @Test
    void isValidCoordinatesReturnsTrue() {
        assertTrue(gameBoard3x3.isValidCoordinates(Position.of(0, 0)));
        assertTrue(gameBoard3x3.isValidCoordinates(Position.of(2, 2)));
    }

    @Test
    void isValidCoordinatesReturnsFalse() {
        assertFalse(gameBoard3x3.isValidCoordinates(Position.of(-1, 0)));
        assertFalse(gameBoard3x3.isValidCoordinates(Position.of(3, 0)));
        assertFalse(gameBoard3x3.isValidCoordinates(Position.of(0, -1)));
        assertFalse(gameBoard3x3.isValidCoordinates(Position.of(0, 3)));
    }

    @Test
    void setMarkReallySetsMarkInValidCoordinates() {
        gameBoard3x3.setMark(Position.of(0, 0), Mark.X);
        assertEquals(Mark.X, gameBoard3x3.getBoard()[0][0].getMark());

        gameBoard3x3.setMark(Position.of(1, 1), null);
        assertNull(gameBoard3x3.getBoard()[1][1].getMark());
    }

    @Test
    void setMarkTrowsExceptionIfInvalidPosition() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> gameBoard3x3.setMark(Position.of(-1, 3), Mark.O)
        );
    }
}