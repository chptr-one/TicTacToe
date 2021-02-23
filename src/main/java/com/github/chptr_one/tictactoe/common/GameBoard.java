package com.github.chptr_one.tictactoe.common;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GameBoard {

    private final Mark[][] board;
    private final int size;

    private int emptyCells;

    public GameBoard(int size) {
        this.size = size;
        this.emptyCells = size * size;
        this.board = new Mark[size][size];
    }

    public Set<Position> getPossibleMoves() {
        Set<Position> moves = new HashSet<>();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Position move = Position.of(row, col);
                if (isEmptyCell(move)) {
                    moves.add(move);
                }
            }
        }
        return moves;
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
        return IntStream.range(0, size).allMatch(predicate::test);
    }

    public boolean hasWinningLine(Position pos) {
        if (isEmptyCell(pos)) return false;

        // Players have not yet made enough moves to win
        if (emptyCells > size * size - (size * 2 - 1)) return false;

        var origin = board[pos.getRow()][pos.getCol()];

        Stream.Builder<Predicate<Integer>> builder = Stream.builder();
        builder.add(i -> board[pos.getRow()][i] == origin);
        builder.add(i -> board[i][pos.getCol()] == origin);
        if (pos.getRow() == pos.getCol()) {
            builder.add(i -> board[i][i] == origin);
        }
        if (size - pos.getRow() - 1 == pos.getCol()) {
            builder.add(i -> board[size - i - 1][i] == origin);
        }

        return builder.build().anyMatch(this::isLineCompleted);
    }

    public boolean hasEmptyCells() {
        return emptyCells > 0;
    }

    public boolean isGameOver(Position move) {
        return !hasEmptyCells() || hasWinningLine(move);
    }

    public int getNumberOfEmptyCells() {
        return emptyCells;
    }

    public int getSize() {
        return size;
    }

    public Mark[][] getBoard() {
        return board;
    }
}
