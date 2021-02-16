package com.github.chptr_one;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PositionTest {

    private final static int SIZE = 3;

    @BeforeEach
    void init() {
        Position.initialize(SIZE);
    }

    @Test
    void positionInitializedCorrectly() {
        assertEquals(SIZE, Position.getSize());
    }

    @Test
    void ofReturnsCorrectPosition() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Position pos = Position.of(row, col);
                assertEquals(row, pos.getRow(), "Wrong row: " + row);
                assertEquals(col, pos.getCol(), "Wrong col: " + col);
            }
        }
    }

    @Test
    void ofThrowsExceptionIfNotInitialized() {
        try {
            Field initialized = Position.class.getDeclaredField("initialized");
            initialized.setAccessible(true);
            initialized.set(Position.class, false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        assertThrows(IllegalStateException.class,
                () -> Position.of(0, 0));
    }

    @Test
    void ofThrowsExceptionIfArgumentsOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> Position.of(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> Position.of(0, -1));
        assertThrows(IllegalArgumentException.class, () -> Position.of(3, 0));
        assertThrows(IllegalArgumentException.class, () -> Position.of(0, 3));
    }

    @Test
    void isValidThrowsExceptionIfNotInitialized() {
        try {
            Field initialized = Position.class.getDeclaredField("initialized");
            initialized.setAccessible(true);
            initialized.set(Position.class, false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //noinspection ResultOfMethodCallIgnored
        assertThrows(IllegalStateException.class,
                () -> Position.isValid(-1, 0));
    }
}