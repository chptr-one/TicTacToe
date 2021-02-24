package com.github.chptr_one.tictactoe;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.ui.GameBoardPrinter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class GameBoardPrinterTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream content = new ByteArrayOutputStream();
    private final PrintStream redirectedOut = new PrintStream(content);

    @BeforeEach
    public void setup() {
        System.setOut(redirectedOut);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        content.reset();
    }

    @Test
    public void blankGameBoardPrints() {
        String ls = System.getProperty("line.separator");
        String blankBoard3x3String = "  1 2 3" + ls + "1 . . ." + ls + "2 . . ." + ls + "3 . . ." + ls;
        var gameBoard = new GameBoard(3);
        GameBoardPrinter.print(gameBoard);
        Assertions.assertEquals(blankBoard3x3String, content.toString());
    }
}