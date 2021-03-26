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
        return board.getBlankTilesCoordinates().stream()
                .map(coord -> new Move(coord, tile))
                .max((m1, m2) -> {
                    int v1 = minimax(board.accept(m1), Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    int v2 = minimax(board.accept(m2), Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    return Integer.compare(v1, v2);
                })
                .orElse(null);
    }

    private int minimax(GameBoard board, int alpha, int beta, boolean isMax) {
        if (board.hasCompletedLine(X) || board.hasCompletedLine(O) || !board.hasPossibleMoves()) {
            return evaluateBoard(board);
        }

        int bestValue;
        if (isMax) {
            bestValue = Integer.MIN_VALUE;
            for (var coord : board.getBlankTilesCoordinates()) {
                var move = new Move(coord, tile);
                var newBoard = board.accept(move);
                int value = minimax(newBoard, alpha, beta, false);
                bestValue = Math.max(value, bestValue);
                alpha = Math.max(alpha, bestValue);
                if (alpha >= beta) {
                    break;
                }
            }
        } else {
            bestValue = Integer.MAX_VALUE;
            for (var coord : board.getBlankTilesCoordinates()) {
                var move = new Move(coord, opponentTile);
                var newBoard = board.accept(move);
                int value = minimax(newBoard, alpha, beta, true);
                bestValue = Math.min(value, bestValue);
                beta = Math.min(beta, bestValue);
                if (beta <= alpha) {
                    break;
                }
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
