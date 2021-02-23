package com.github.chptr_one.tictactoe.player;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Mark;
import com.github.chptr_one.tictactoe.common.Position;

import java.util.function.IntBinaryOperator;

import static com.github.chptr_one.tictactoe.common.Mark.*;

public class AiPlayer extends AbstractPlayer {

    private final Mark opponentMark;

    public AiPlayer(String name, Mark mark) {
        super(name, mark);
        this.opponentMark = mark == X ? O : X;
    }

    @Override
    public Position getMove(GameBoard gameBoard) {
        Position bestMove = null;
        int bestWeight = Integer.MIN_VALUE;
        for (var move : gameBoard.getEmptyCells()) {
            gameBoard.setMark(move, mark);
            int weight = minimax(gameBoard, move, false);
            gameBoard.unsetMark(move);
            if (weight > bestWeight) {
                bestWeight = weight;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int calculateWeight(GameBoard gameBoard, Position move) {
        if (gameBoard.hasWinningLine(move)) {
            return 100;
        } else {
            return 0;
        }
    }

    private int minimax(GameBoard gameBoard, Position move, boolean isMax) {
        if (gameBoard.isGameOver(move)) {
            int weight = calculateWeight(gameBoard, move);
            weight = isMax ? -weight : weight;
            return weight;
        }

        int bestWeight = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Mark currentMark = isMax ? mark : opponentMark;
        IntBinaryOperator maximizer = isMax ? Math::max : Math::min;

        for (var pos : gameBoard.getEmptyCells()) {
            gameBoard.setMark(pos, currentMark);
            int weight = minimax(gameBoard, pos, !isMax);
            gameBoard.unsetMark(pos);
            bestWeight = maximizer.applyAsInt(weight, bestWeight);
        }

        return bestWeight;
    }
}
