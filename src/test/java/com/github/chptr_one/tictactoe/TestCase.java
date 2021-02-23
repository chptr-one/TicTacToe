package com.github.chptr_one.tictactoe;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Mark;
import com.github.chptr_one.tictactoe.common.Position;

import java.lang.reflect.Field;

public class TestCase {
    private final String seed;
    private final Position pos;
    private final int emptyCells;

    public TestCase(String seed, Position pos, int emptyCells) {
        this.seed = seed;
        this.pos = pos;
        this.emptyCells = emptyCells;
    }

    public GameBoard getGameBoard() {
        GameBoard gameBoard = new GameBoard(3);
        Mark[][] board = gameBoard.getBoard();
        for (int i = 0; i < board.length * board.length; i++) {
            char ch = seed.charAt(i);
            board[i / board.length][i % board.length] = ch == 'x' ? Mark.X : ch == 'o' ? Mark.O : null;
        }

        try {
            Field emptyCellsField = GameBoard.class.getDeclaredField("emptyCells");
            emptyCellsField.setAccessible(true);
            emptyCellsField.setInt(gameBoard, emptyCells);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return gameBoard;
    }

    public Position getPos() {
        return pos;
    }

    private String arrToString() {
        return seed.substring(0, 3) + '\n' + seed.substring(3, 6) + '\n' + seed.substring(6, 9) + '\n';
    }

    @Override
    public String toString() {
        return "Seed is \n" + arrToString() + "Pos is" + pos;
    }
}
