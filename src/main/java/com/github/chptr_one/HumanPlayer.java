package com.github.chptr_one;

import java.util.Scanner;

public class HumanPlayer implements Player {

    private final String name;
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position getMove(GameBoard gameBoard) {
        GameBoardPrinter.print(gameBoard);
        System.out.print(name + ". Enter row and col: ");
        int row = scanner.nextInt() - 1;
        int col = scanner.nextInt() - 1;
        return Position.of(row, col);
    }
}
