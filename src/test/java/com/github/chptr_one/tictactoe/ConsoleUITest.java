package com.github.chptr_one.tictactoe;

import com.github.chptr_one.tictactoe.common.Position;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;

public class ConsoleUITest {

    @BeforeClass
    public static void init() {
        Position.initialize(3);
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

/*
    @Rule
    final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    void printBlankGameBoard() {
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
*/

    @Test
    public void test1() {
        String s = "HelloWorld";
        System.out.println(s);
        assertEquals("HelloWorld\n", systemOutRule.getLogWithNormalizedLineSeparator());
    }
/*
    @Test
    public void readPosition() {
        systemInMock.provideLines("1 1" + ls);
        Position expected = Position.of(1, 1);
        Position actual = ConsoleUI.readPosition(new HumanPlayer("Test human", Mark.X));
        assertEquals(expected, actual);
    }
*/
}