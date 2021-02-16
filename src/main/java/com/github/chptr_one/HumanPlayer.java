package com.github.chptr_one;

import java.util.Scanner;

public class HumanPlayer implements Player {

    private final String name;
    private final Mark mark;
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name, Mark mark) {
        this.name = name;
        this.mark = mark;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Mark getMark() {
        return mark;
    }

    @Override
    public Position getMove(GameBoard gameBoard) {
        System.out.print(name + ". Enter row and col: ");
        int row = scanner.nextInt() - 1;
        int col = scanner.nextInt() - 1;
        return Position.of(row, col);
    }
}
