package com.github.chptr_one.tictactoe.player;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Mark;
import com.github.chptr_one.tictactoe.common.Position;

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
        Position bestMove = null;
        int bestWeight = Integer.MIN_VALUE;
        for (var move : gameBoard.getEmptyCells()) {
            gameBoard.setMark(move, mark);
            int weight = minimax(gameBoard, move, false, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
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
            return Integer.MAX_VALUE - 1;
        } else {
            return 0;
        }
    }

    private int minimax(GameBoard gameBoard,
                        Position move,
                        boolean isMax,
                        int depth,
                        int alpha,
                        int beta) {

        if (gameBoard.isGameOver(move)) {
            int weight = calculateWeight(gameBoard, move);
            weight = isMax ? -weight + depth : weight - depth;
            return weight;
        }

        int bestWeight;
        if (isMax) {
            bestWeight = Integer.MIN_VALUE;
            for (var pos : gameBoard.getEmptyCells()) {
                gameBoard.setMark(pos, mark);
                int weight = minimax(gameBoard, pos, false, depth++, alpha, beta);
                gameBoard.unsetMark(pos);
                bestWeight = Math.max(weight, bestWeight);
                alpha = Math.max(alpha, bestWeight);
                if (alpha >= beta) {
                    break;
                }
            }
        } else {
            bestWeight = Integer.MAX_VALUE;
            for (var pos : gameBoard.getEmptyCells()) {
                gameBoard.setMark(pos, opponentMark);
                int weight = minimax(gameBoard, pos, true, depth++, alpha, beta);
                gameBoard.unsetMark(pos);
                bestWeight = Math.min(weight, bestWeight);
                beta = Math.min(beta, bestWeight);
                if (beta <= alpha) {
                    break;
                }
            }
        }

        return bestWeight;
    }
}
