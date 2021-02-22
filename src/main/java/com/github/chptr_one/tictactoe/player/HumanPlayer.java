package com.github.chptr_one.tictactoe.player;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Mark;
import com.github.chptr_one.tictactoe.common.Position;

import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer {

    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name, Mark mark) {
        super(name, mark);
    }

    @Override
    public Position getMove(GameBoard gameBoard) {
        int row;
        int col;
        do {
            System.out.print(name + ". Enter row and col: ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (!Position.isValid(row, col));
        return Position.of(row, col);
    }
}
