package com.github.chptr_one.tictactoe;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.GameState;
import com.github.chptr_one.tictactoe.common.Mark;
import com.github.chptr_one.tictactoe.common.Position;
import com.github.chptr_one.tictactoe.player.AiPlayer;
import com.github.chptr_one.tictactoe.player.HumanPlayer;
import com.github.chptr_one.tictactoe.player.Player;
import com.github.chptr_one.tictactoe.ui.ConsoleUI;

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
                new AiPlayer("O player", Mark.O)
        );

        game.run();
    }

    public void run() {
        ConsoleUI.print(gameBoard);

        Player currentPlayer = firstPlayer;
        do {
            Position position;
            do {
                position = currentPlayer.getMove(gameBoard);
            } while (!gameBoard.isEmptyCell(position));

            gameBoard.setMark(position, currentPlayer.getMark());
            ConsoleUI.printMessage(currentPlayer.getName() + " made a move to "
                    + (position.getRow() + 1) + ", " + (position.getCol() + 1));
            ConsoleUI.print(gameBoard);

            if (gameBoard.isGameOver(position)) {
                if (!gameBoard.hasEmptyCells()) {
                    gameState = GameState.DRAW;
                } else {
                    gameState = currentPlayer == firstPlayer ? GameState.X_WINS : GameState.O_WINS;
                }
            } else {
                currentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
            }
        } while (gameState == GameState.RUNNING);

        ConsoleUI.printMessage(gameState + " current player: " + currentPlayer.getName());
    }
}
