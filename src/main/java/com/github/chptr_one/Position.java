package com.github.chptr_one;

public class Position {
    private final int row;
    private final int col;

    private Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    private static int upperIndex;
    private static Position[] pool;
    private static boolean initialized = false;

    public static void initialize(int excluding) {
        upperIndex = excluding;
        pool = new Position[upperIndex * upperIndex];
        for (int i = 0; i < pool.length; i++) {
            pool[i] = new Position(i / upperIndex, i % upperIndex);
        }
        initialized = true;
    }

    public static boolean isValid(int row, int col) {
        if (!initialized) {
            throw new IllegalStateException("You must initialize Position class before first usage.");
        }
        return row >= 0 && row < upperIndex && col >= 0 && col < upperIndex;
    }

    public static Position of(int row, int col) {
        if (!initialized) {
            throw new IllegalStateException("You must initialize Position class before first usage.");
        }
        if (!isValid(row, col)) {
            throw new IllegalArgumentException("Arguments must be in range [0.." + upperIndex + ").");
        }

        return pool[row * upperIndex + col];
    }

    public static int getUpperIndex() {
        return upperIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (row != position.row) return false;
        return col == position.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
