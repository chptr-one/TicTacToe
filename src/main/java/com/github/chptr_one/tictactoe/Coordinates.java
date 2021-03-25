package com.github.chptr_one.tictactoe;

public class Coordinates {
    private final int row;
    private final int col;

    private Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static Coordinates of(int row, int col) {
        return new Coordinates(row, col);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (row != that.row) return false;
        return col == that.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }
}
