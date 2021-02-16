package com.github.chptr_one.tictactoe;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.GameState;
import com.github.chptr_one.tictactoe.common.Mark;
import com.github.chptr_one.tictactoe.common.Position;
import com.github.chptr_one.tictactoe.player.HumanPlayer;
import com.github.chptr_one.tictactoe.player.Player;
import com.github.chptr_one.tictactoe.ui.GameBoardPrinter;

public class TicTacToeGame {

    private final GameBoard gameBoard;
    private final Player firstPlayer;
    private final Player secondPlayer;

    private GameState gameState = GameState.RUNNING;

    public TicTacToeGame(GameBoard gameBoard, Player firstPlayer, Player secondPlayer) {
        this.gameBoard = gameBoard;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public static void main(String[] args) {
        Position.initialize(3);
        var game = new TicTacToeGame(
                new GameBoard(3),
                new HumanPlayer("X player", Mark.X),
                new HumanPlayer("O player", Mark.O)
        );

        game.run();
    }

    public void run() {
        GameBoardPrinter.print(gameBoard);

        Player currentPlayer = firstPlayer;
        do {
            Position position;
            do {
                position = currentPlayer.getMove(gameBoard);
            } while (!isValidMove(position));

            gameBoard.setMark(position, currentPlayer.getMark());

            if (!gameBoard.hasEmptyCells()) {
                gameState = GameState.DRAW;
            } else if (gameBoard.hasWinningLine(position)) {
                gameState = currentPlayer == firstPlayer ? GameState.X_WINS : GameState.O_WINS;
            }

            GameBoardPrinter.print(gameBoard);
            currentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
        } while (gameState == GameState.RUNNING);

        System.out.println(gameState);
    }

    private boolean isValidMove(Position position) {
        return gameBoard.isEmptyCell(position);
    }
}
