package com.github.chptr_one;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    private final GameBoard gameBoard3x3 = new GameBoard(3);

    @BeforeAll
    static void init() {
        Position.initialize(3);
    }

    @Test
    void boardHasRightNumberOfEmptyCells() {
        assertEquals(3 * 3, gameBoard3x3.getNumberOfEmptyCells());
    }

    @Test
    void hasEmptyCellReturnsTrue() {
        assertTrue(gameBoard3x3.hasEmptyCells());
    }

    @Test
    void setMarkInEmptyCellChangesBoard() {
        gameBoard3x3.setMark(Position.of(0, 0), Mark.X);
        assertEquals(Mark.X, gameBoard3x3.getBoard()[0][0]);
    }

    @Test
    void setMarkDoNothingOnNonEmptyCell() {
        gameBoard3x3.setMark(Position.of(1, 1), Mark.O);
        gameBoard3x3.setMark(Position.of(1, 1), Mark.X);
        assertEquals(Mark.O, gameBoard3x3.getBoard()[1][1]);
    }

    @Test
    void setMarkReducesNumberOfEmptyCells() {
        int numberOfEmptyCells = gameBoard3x3.getNumberOfEmptyCells();
        gameBoard3x3.setMark(Position.of(1, 1), Mark.X);
        assertEquals(numberOfEmptyCells - 1, gameBoard3x3.getNumberOfEmptyCells());
    }

    @Test
    void unsetMarkUnsetsNonEmptyCell() {
        gameBoard3x3.setMark(Position.of(2, 2), Mark.X);
        gameBoard3x3.unsetMark(Position.of(2, 2));
        assertNull(gameBoard3x3.getBoard()[2][2]);
    }

    @Test
    void unsetMarkDoNothingOnEmptyCell() {
        gameBoard3x3.unsetMark(Position.of(0, 0));
        assertEquals(3 * 3, gameBoard3x3.getNumberOfEmptyCells());
    }

    @Test
    void unsetMarkIncreasesNumberOfEmptyCells() {
        gameBoard3x3.setMark(Position.of(2, 2), Mark.X);
        int numberOfEmptyCells = gameBoard3x3.getNumberOfEmptyCells();
        gameBoard3x3.unsetMark(Position.of(2, 2));
        assertEquals(numberOfEmptyCells + 1, gameBoard3x3.getNumberOfEmptyCells());
    }
}