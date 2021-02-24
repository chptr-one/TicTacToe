package com.github.chptr_one.tictactoe;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Mark;
import com.github.chptr_one.tictactoe.common.Position;
import com.github.chptr_one.tictactoe.player.HumanPlayer;
import com.github.chptr_one.tictactoe.ui.ConsoleUI;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class ConsoleUITest {

    @Before
    public void init() {
        Position.initialize(3);
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void printBlankGameBoard() {
        String blankBoard3x3String = """
                  1 2 3
                1 . . .
                2 . . .
                3 . . .
                """;
        var gameBoard = new GameBoard(3);
        ConsoleUI.print(gameBoard);
        assertEquals(blankBoard3x3String, systemOutRule.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void readPosition() {
        systemInMock.provideLines("1 1");
        Position expected = Position.of(1, 1);
        Position actual = ConsoleUI.readPosition(new HumanPlayer("Test human", Mark.X));
        assertEquals(expected, actual);
    }
}