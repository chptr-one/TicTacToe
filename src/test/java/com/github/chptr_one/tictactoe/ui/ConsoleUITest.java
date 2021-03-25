package com.github.chptr_one.tictactoe.ui;

import com.github.chptr_one.tictactoe.Coordinates;
import com.github.chptr_one.tictactoe.GameBoard;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class ConsoleUITest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void printBlankGameBoard() {
        ConsoleUI ui = new ConsoleUI();
        GameBoard board;

        String blankBoard3x3 = """
                  1 2 3
                1 . . .
                2 . . .
                3 . . .
                """;
        board = new GameBoard(3);
        ui.drawBoard(board);
        assertEquals(blankBoard3x3, systemOutRule.getLogWithNormalizedLineSeparator());

        systemOutRule.clearLog();
        String blankBoard4x4 = """
                  1 2 3 4
                1 . . . .
                2 . . . .
                3 . . . .
                4 . . . .
                """;
        board = new GameBoard(4);
        ui = new ConsoleUI();
        ui.drawBoard(board);
        assertEquals(blankBoard4x4, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void readCoordinatesReduceInputByOne() {
        systemInMock.provideLines("1 1");
        Coordinates expected = Coordinates.of(0, 0);
        Coordinates actual = new ConsoleUI().readCoordinates();
        assertEquals(expected, actual);
    }
}