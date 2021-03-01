package com.github.chptr_one.tictactoe;

import com.github.chptr_one.tictactoe.common.Position;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

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

    @Test
    public void isValidReturnsTrueOnValidInput() {
        assertTrue(Position.isValid(0, 0));
        assertTrue(Position.isValid(1, 1));
        assertTrue(Position.isValid(2, 2));
    }

    @Test
    public void isValidReturnsFalseOnInvalidInput() {
        assertFalse(Position.isValid(-1, 0));
        assertFalse(Position.isValid(0, -1));
        assertFalse(Position.isValid(0, 4));
        assertFalse(Position.isValid(4, 0));
    }

    @Test
    public void equalityCheck() {
        Position p1 = Position.of(0, 0);
        Position p2 = Position.of(0, 0);
        assertEquals(p1, p2);

        Position p3 = Position.of(1, 1);
        assertNotEquals(p1, p3);
    }

    @Test
    public void hashCodeCheck() {
        Position p1 = Position.of(0, 0);
        Position p2 = Position.of(0, 0);

        assertEquals(p1.hashCode(), p2.hashCode());
        Position p3 = Position.of(1, 1);
        assertNotEquals(p1.hashCode(), p3.hashCode());
    }
}