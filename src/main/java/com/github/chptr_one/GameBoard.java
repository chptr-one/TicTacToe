package com.github.chptr_one;

import java.util.List;
import java.util.function.Predicate;

public class GameBoard {

    private final Mark[][] board;
    private final int size;

    private int emptyCells;

    public GameBoard(int size) {
        this.size = size;
        this.emptyCells = size * size;

        this.board = new Mark[size][size];
    }

    public boolean isValidCoordinates(Position pos) {
        return pos.getRow() >= 0 && pos.getRow() < size
                && pos.getCol() >= 0 && pos.getCol() < size;
    }

    public boolean isEmptyCell(Position pos) {
        return board[pos.getRow()][pos.getCol()] == null;
    }

    public void setMark(Position pos, Mark mark) {
        if (mark == null) throw new IllegalArgumentException("mark can not be null");
        if (isEmptyCell(pos)) {
            board[pos.getRow()][pos.getCol()] = mark;
            emptyCells--;
        }
    }

    public void unsetMark(Position pos) {
        if (!isEmptyCell(pos)) {
            board[pos.getRow()][pos.getCol()] = null;
            emptyCells++;
        }
    }

    private boolean isLineCompleted(Predicate<Integer> predicate) {
        boolean result = true;
        int i = 0;
        while (result && i < size) {
            result = predicate.test(i);
            i++;
        }
        return result;
    }

    public boolean hasWinningLine(Position pos) {
        if (isEmptyCell(pos)) return false;

        // Players have not yet made enough moves to win
        if (emptyCells > size * size - (size * 2 - 1)) return false;

        var origin = board[pos.getRow()][pos.getCol()];
        List<Predicate<Integer>> predicates = List.of(
                i -> board[pos.getRow()][i] == origin,
                i -> board[i][pos.getCol()] == origin,
                i -> board[i][i] == origin,
                i -> board[size - i - 1][i] == origin
        );

        boolean result = false;
        for (var predicate : predicates) {
            result = isLineCompleted(predicate);
            if (result) {
                break;
            }
        }
        return result;
    }

    public int getEmptyCells() {
        return emptyCells;
    }

    public int getSize() {
        return size;
    }

    public Mark[][] getBoard() {
        return board;
    }
}
