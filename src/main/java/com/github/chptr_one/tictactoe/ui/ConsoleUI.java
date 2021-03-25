package com.github.chptr_one.tictactoe.ui;

import com.github.chptr_one.tictactoe.Coordinates;
import com.github.chptr_one.tictactoe.GameBoard;
import com.github.chptr_one.tictactoe.Tile;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleUI implements UI {

    public static final String X_STRING = "x";
    public static final String O_STRING = "o";
    public static final String BLANK_STRING = ".";

    private final Scanner scanner = new Scanner(System.in);
    private String header;

    private static String tileToString(Tile tile) {
        String result;
        switch (tile) {
            case X -> result = X_STRING;
            case O -> result = O_STRING;
            default -> result = BLANK_STRING;
        }
        return result;
    }

    @Override
    public void drawBoard(GameBoard board) {
        Tile[][] tiles = board.getTiles();
        int size = tiles.length;
        if (header == null) {
            header = "  " + IntStream.rangeClosed(1, size)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(" "));
        }

        System.out.println(header);
        for (int row = 0; row < size; row++) {
            String line = (row + 1) + " " +
                    Arrays.stream(tiles[row])
                            .map(ConsoleUI::tileToString)
                            .collect(Collectors.joining(" "));
            System.out.println(line);
        }
    }

    @Override
    public Coordinates readCoordinates() {
        System.out.print("Enter row and col: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Enter two numbers separated by spaces.");
        }
        int row = scanner.nextInt();
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Enter col number.");
        }
        int col = scanner.nextInt();
        return Coordinates.of(row - 1, col - 1);
    }

    @Override
    public void showInputPrompt(String name) {
        showMessage("Player " + name + " moves.");
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
