package com.github.chptr_one;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameBoardHasWinningLineTest {

    static class TestCase {
        String seed;
        Position pos;
        int emptyCells;

        public TestCase(String seed, Position pos, int emptyCells) {
            this.seed = seed;
            this.pos = pos;
            this.emptyCells = emptyCells;
        }

        private String arrToString() {
            return seed.substring(0, 3) + '\n' + seed.substring(3, 6) + '\n' + seed.substring(6, 9) + '\n';
        }

        @Override
        public String toString() {
            return "Seed is \n" + arrToString() + "Pos is" + pos;
        }

    }

    @BeforeAll
    static void init() {
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

    private final GameBoard gameBoard = new GameBoard(3);

    private static void initializeBoard(GameBoard gameBoard, TestCase testCase) {
        Mark[][] board = gameBoard.getBoard();

        for (int i = 0; i < board.length * board.length; i++) {
            char ch = testCase.seed.charAt(i);
            board[i / board.length][i % board.length] = ch == 'x' ? Mark.X : ch == 'o' ? Mark.O : null;
        }

        try {
            Field emptyCells = gameBoard.getClass().getDeclaredField("emptyCells");
            emptyCells.setAccessible(true);
            emptyCells.set(gameBoard, testCase.emptyCells);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void hasWinningLineReturnsTrueOnWinningLines() {
        for (var testCase : xWins) {
            initializeBoard(gameBoard, testCase);
            assertTrue(gameBoard.hasWinningLine(testCase.pos), "Failed testCase:\n" + testCase);
        }
    }

    @Test
    void hasWinningLineReturnsFalseIfGameNotOver() {
        for (var testCase : gameIsNotOverYet) {
            initializeBoard(gameBoard, testCase);
            assertFalse(gameBoard.hasWinningLine(testCase.pos), "Failed testCase:\n" + testCase);
        }
    }

    @Test
    void HasWinningLineReturnsFalseIfDraw() {
        for (var testCase : draw) {
            initializeBoard(gameBoard, testCase);
            assertFalse(gameBoard.hasWinningLine(testCase.pos), "Failed testCase:\n" + testCase);
        }
    }
}
