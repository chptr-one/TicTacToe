package com.github.chptr_one.tictactoe;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Position;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameBoardHasWinningLineTest {

    @BeforeClass
    public static void init() {
        Position.initialize(3);
    }

    private static final TestCase[] xWins = {
            new TestCase("xxx" + "oo." + "...", Position.of(0, 0), 4),
            new TestCase(".oo" + "xxx" + "...", Position.of(1, 1), 4),
            new TestCase("..o" + ".o." + "xxx", Position.of(2, 2), 4),

            new TestCase("x.." + "xoo" + "x..", Position.of(1, 0), 4),
            new TestCase(".xo" + "ox." + ".x.", Position.of(0, 1), 4),
            new TestCase("o.x" + "..x" + "o.x", Position.of(2, 2), 4),

            new TestCase("x.o" + ".xo" + "..x", Position.of(1, 1), 4),
            new TestCase("o.x" + ".x." + "x.o", Position.of(2, 0), 4)
    };

    private static final TestCase[] gameIsNotOverYet = {
            new TestCase("..." + "..." + "...", Position.of(0, 0), 9),
            new TestCase("..." + ".x." + "...", Position.of(1, 1), 8),
            new TestCase("..." + ".x." + "..o", Position.of(2, 2), 7),
            new TestCase("x.." + ".x." + "..o", Position.of(0, 0), 6),
            new TestCase("x.." + ".x." + "o.o", Position.of(2, 0), 5),
            new TestCase("xx." + ".x." + "o.o", Position.of(0, 1), 4),
            new TestCase("xxo" + ".x." + "o.o", Position.of(0, 2), 3)
    };

    private static final TestCase[] draw = {
            new TestCase("xox" + "oox" + "xxo", Position.of(2, 0), 0),
            new TestCase("xxo" + "oox" + "xox", Position.of(1, 2), 0)
    };


    @Test
    public void hasWinningLineReturnsTrueOnWinningLines() {
        for (var testCase : xWins) {
            GameBoard gameBoard = testCase.getGameBoard();
            assertTrue("Failed testCase:\n" + testCase, gameBoard.hasWinningLine(testCase.getPos()));
        }
    }

    @Test
    public void hasWinningLineReturnsFalseIfGameNotOver() {
        for (var testCase : gameIsNotOverYet) {
            GameBoard gameBoard = testCase.getGameBoard();
            assertFalse("Failed testCase:\n" + testCase, gameBoard.hasWinningLine(testCase.getPos()));
        }
    }

    @Test
    public void HasWinningLineReturnsFalseIfDraw() {
        for (var testCase : draw) {
            GameBoard gameBoard = testCase.getGameBoard();
            assertFalse("Failed testCase:\n" + testCase, gameBoard.hasWinningLine(testCase.getPos()));
        }
    }
}
