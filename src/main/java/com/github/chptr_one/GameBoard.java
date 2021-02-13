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
