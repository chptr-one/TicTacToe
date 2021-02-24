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

    private static String columnNumbers;
    private static Scanner scanner;

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
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        int row = -1;
        int col = -1;
        do {
            System.out.print(player.getName() + ". Enter row and col: ");
            String input = scanner.nextLine();
            try {
                String[] parts = input.split("\s+");
                row = Integer.parseInt(parts[0]);
                col = Integer.parseInt(parts[1]);
            } catch (NumberFormatException ignored) {
                System.out.println("Wrong number format");
            }
        } while (!Position.isValid(row, col));

        return Position.of(row, col);
    }
}
