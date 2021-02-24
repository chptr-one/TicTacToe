package com.github.chptr_one.tictactoe.ui;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Mark;
import com.github.chptr_one.tictactoe.common.Position;
import com.github.chptr_one.tictactoe.player.Player;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleUI {

    private static final Scanner scanner = new Scanner(System.in);
    private static String columnNumbers;

    private ConsoleUI() {
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
                    .map(ConsoleUI::markToString)
                    .collect(Collectors.joining(" "));
            System.out.println(result);
        }
    }

    public static Position readPosition(Player player) {
        int row;
        int col;
        do {
            System.out.print(player.getName() + ". Enter row and col: ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (!Position.isValid(row, col));
        return Position.of(row, col);
    }
}
