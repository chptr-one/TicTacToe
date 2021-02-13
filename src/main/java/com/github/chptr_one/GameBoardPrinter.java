package com.github.chptr_one;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameBoardPrinter {

    private final GameBoard gameBoard;
    private final String columnNumbers;

    private GameBoardPrinter(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        columnNumbers = "  " + IntStream.range(1, gameBoard.getWidth() + 1)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    private static GameBoardPrinter instance;

    public static GameBoardPrinter getInstance(GameBoard gameBoard) {
        if (instance == null) {
            instance = new GameBoardPrinter(gameBoard);
        }
        return instance;
    }

    private static String markToString(Mark mark) {
        if (mark == null) return ".";
        return mark == Mark.X ? "x" : "o";
    }

    public void print() {
        System.out.println(columnNumbers);
        int rowNumber = 1;
        for (var row : gameBoard.getBoard()) {
            String result = rowNumber++ + " " + Arrays.stream(row)
                    .map(Cell::getMark)
                    .map(GameBoardPrinter::markToString)
                    .collect(Collectors.joining(" "));
            System.out.println(result);
        }
    }
}
