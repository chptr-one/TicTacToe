package com.github.chptr_one.tictactoe;

import com.github.chptr_one.tictactoe.common.Position;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PositionTest {

    private final static int SIZE = 3;

    @Before
    public void init() {
        Position.initialize(SIZE);
    }

    @Test
    public void positionInitializedCorrectly() {
        assertEquals(SIZE, Position.getSize());
    }

    @Test
    public void returnsCorrectPosition() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Position pos = Position.of(row, col);
                assertEquals("Wrong row: " + row, row, pos.getRow());
                assertEquals("Wrong col: " + col, col, pos.getCol());
            }
        }
    }

    @Test
    public void throwsExceptionIfNotInitialized() {
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
    public void throwsExceptionIfArgumentsOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> Position.of(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> Position.of(0, -1));
        assertThrows(IllegalArgumentException.class, () -> Position.of(3, 0));
        assertThrows(IllegalArgumentException.class, () -> Position.of(0, 3));
    }

    @Test
    public void isValidThrowsExceptionIfNotInitialized() {
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