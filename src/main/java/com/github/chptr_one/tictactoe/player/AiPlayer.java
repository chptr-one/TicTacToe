package com.github.chptr_one.tictactoe.player;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Mark;
import com.github.chptr_one.tictactoe.common.Position;

import java.util.HashSet;
import java.util.Set;

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
        for (var move : getPossibleMoves(gameBoard)) {
            gameBoard.setMark(move, mark);
            int weight = minimax(gameBoard, move, false);
            gameBoard.unsetMark(move);
            if (weight > bestWeight) {
                bestWeight = weight;
                bestMove = move;
            }
        }
        System.out.println(bestWeight + " " + bestMove);
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

        if (isMax) {
            int maxWeight = Integer.MIN_VALUE;
            for (var pos : getPossibleMoves(gameBoard)) {
                gameBoard.setMark(pos, mark);
                int weight = minimax(gameBoard, pos, !isMax);
                gameBoard.unsetMark(pos);
                maxWeight = Math.max(weight, maxWeight);
            }
            return maxWeight;
        } else {
            int minWeight = Integer.MAX_VALUE;
            for (var pos : getPossibleMoves(gameBoard)) {
                gameBoard.setMark(pos, opponentMark);
                int weight = minimax(gameBoard, pos, !isMax);
                gameBoard.unsetMark(pos);
                minWeight = Math.min(weight, minWeight);
            }
            return minWeight;
        }
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
