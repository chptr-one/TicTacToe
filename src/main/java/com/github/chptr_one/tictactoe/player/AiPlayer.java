package com.github.chptr_one.tictactoe.player;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Mark;
import com.github.chptr_one.tictactoe.common.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.github.chptr_one.tictactoe.common.Mark.O;
import static com.github.chptr_one.tictactoe.common.Mark.X;

public class AiPlayer extends AbstractPlayer {

    private final Mark opponentMark;

    public AiPlayer(String name, Mark mark) {
        super(name, mark);
        this.opponentMark = mark == X ? O : X;
    }

    @Override
    public Position getMove(GameBoard gameBoard) {
        return getPossibleMoves(gameBoard).stream().findAny().get();
    }

    static public Set<Position> getPossibleMoves(GameBoard gameBoard) {
        Set<Position> moves = new HashSet<>();
        for (int row = 0; row < gameBoard.getSize(); row++) {
            for (int col = 0; col < gameBoard.getSize(); col++) {
                Position move = Position.of(row, col);
                if (gameBoard.isEmptyCell(move)) {
                    moves.add(move);
                }
            }
        }
        return moves;
    }
}
