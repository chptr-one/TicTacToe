package com.github.chptr_one.tictactoe.common;

import java.util.stream.IntStream;

public class Position {

    private static int size;
    private static Position[] pool;
    private static boolean initialized = false;

    private final int row;
    private final int col;

    private Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static void initialize(int size) {
        Position.size = size;
        Position.pool = IntStream.range(0, size * size)
                .mapToObj(i -> new Position(i / Position.size, i % Position.size))
                .toArray(Position[]::new);
        initialized = true;
    }

    private static void checkInitialize() {
        if (!initialized) {
            throw new IllegalStateException("You must call Position.initialize() before first usage.");
        }
    }

    public static boolean isValid(int row, int col) {
        checkInitialize();
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    public static Position of(int row, int col) {
        checkInitialize();
        if (!isValid(row, col)) {
            throw new IllegalArgumentException("Arguments must be in range [0.." + size + ").");
        }
        return pool[row * size + col];
    }

    public static int getSize() {
        return size;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "[" + row + ", " + col + "]";
    }
}
