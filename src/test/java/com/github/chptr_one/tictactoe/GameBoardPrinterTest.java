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
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private final PrintStream redirectedOut = new PrintStream(baos);

    @BeforeEach
    public void setup() {
        System.setOut(redirectedOut);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        baos.reset();
    }

    @Test
    public void blankGameBoardPrints() {
        String blankBoard3x3String = """
                  1 2 3
                1 . . .
                2 . . .
                3 . . .
                """;
        var gameBoard = new GameBoard(3);
        GameBoardPrinter.print(gameBoard);
        Assertions.assertEquals(blankBoard3x3String, baos.toString());
    }
}