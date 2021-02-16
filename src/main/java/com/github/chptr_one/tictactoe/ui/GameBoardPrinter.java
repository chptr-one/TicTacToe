package com.github.chptr_one.tictactoe.ui;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Mark;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameBoardPrinter {

    private static String columnNumbers;

    private GameBoardPrinter() {
    }

    private static String markToString(Mark mark) {
        if (mark == null) return ".";
        return mark == Mark.X ? "x" : "o";
    }

    public static void print(GameBoard gameBoard) {
        if (columnNumbers == null) {
            columnNumbers = "  " + IntStream.range(1, gameBoard.getSize() + 1)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
        }

        System.out.println(columnNumbers);
        int rowNumber = 1;
        for (var row : gameBoard.getBoard()) {
            String result = rowNumber++ + " " + Arrays.stream(row)
                    .map(GameBoardPrinter::markToString)
                    .collect(Collectors.joining(" "));
            System.out.println(result);
        }
    }
}
