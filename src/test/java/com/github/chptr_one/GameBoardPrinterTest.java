package com.github.chptr_one;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class GameBoardPrinterTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private final PrintStream redirectedOut = new PrintStream(baos);

    @BeforeAll
    static void init() {
        Position.initialize(3);
    }

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