package com.github.chptr_one.tictactoe.player;

import com.github.chptr_one.tictactoe.GameBoard;
import com.github.chptr_one.tictactoe.Move;
import com.github.chptr_one.tictactoe.Tile;

import static com.github.chptr_one.tictactoe.Tile.O;
import static com.github.chptr_one.tictactoe.Tile.X;

public class AIPlayer extends AbstractPlayer {

    private final Tile opponentTile;

    public AIPlayer(String name, Tile tile) {
        super(name, tile);
        opponentTile = tile == X ? O : X;
    }

    @Override
    public Move makeMove(GameBoard board) {
        Move bestMove = null;
        int bestValue = Integer.MIN_VALUE;
        for (var coord : board.getBlankTilesCoordinates()) {
            Move move = new Move(coord, tile);
            int value = minimax(board.accept(move), false);
            if (value > bestValue) {
                bestMove = move;
                bestValue = value;
            }
        }
        return bestMove;
    }

    private int minimax(GameBoard board, boolean isMax) {
        if (board.hasCompletedLine(X) || board.hasCompletedLine(O) || !board.hasPossibleMoves()) {
            return evaluateBoard(board);
        }

        int bestValue;
        if (isMax) {
            bestValue = Integer.MIN_VALUE;
            for (var coord : board.getBlankTilesCoordinates()) {
                var move = new Move(coord, tile);
                var newBoard = board.accept(move);
                int value = minimax(newBoard, false);
                bestValue = Math.max(value, bestValue);
            }
        } else {
            bestValue = Integer.MAX_VALUE;
            for (var coord : board.getBlankTilesCoordinates()) {
                var move = new Move(coord, opponentTile);
                var newBoard = board.accept(move);
                int value = minimax(newBoard, true);
                bestValue = Math.min(value, bestValue);
            }
        }
        return bestValue;
    }

    private int evaluateBoard(GameBoard board) {
        if (board.hasCompletedLine(tile)) {
            return 10;
        } else if (board.hasCompletedLine(opponentTile)) {
            return -10;
        } else {
            return 0;
        }
    }
}
