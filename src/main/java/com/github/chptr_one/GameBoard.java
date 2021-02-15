package com.github.chptr_one;

import java.util.Arrays;

public class GameBoard {

    private final Cell[][] board;
    private final int width;
    private final int height;

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;

        this.board = new Cell[height][width];
        for (var row : board) {
            Arrays.fill(row, new Cell());
        }
    }

    public boolean isValidCoordinates(Position pos) {
        return pos.getRow() >= 0 && pos.getRow() < width
                && pos.getCol() >= 0 && pos.getCol() < height;
    }

    public boolean isEmpty(Position pos) {
        return board[pos.getRow()][pos.getCol()] == null;
    }

    public void setMark(Position pos, Mark mark) {
        board[pos.getRow()][pos.getCol()].setMark(mark);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] getBoard() {
        return board;
    }

}
