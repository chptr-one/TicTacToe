package com.github.chptr_one.tictactoe;

import com.github.chptr_one.tictactoe.player.HumanPlayer;
import com.github.chptr_one.tictactoe.player.Player;
import com.github.chptr_one.tictactoe.ui.ConsoleUI;
import com.github.chptr_one.tictactoe.ui.UI;

public class TicTacToeGame {

    private enum GameState {
        WIN,
        TIE,
        RUNNING
    }

    private GameState gameState;
    private GameBoard board;
    private Player currentPlayer;
    private final Player player1;
    private final Player player2;
    private final UI ui;

    public TicTacToeGame() {
        this.board = new GameBoard(3);
        this.ui = new ConsoleUI();
        this.player1 = new HumanPlayer("X Player", Tile.X, ui);
        this.player2 = new HumanPlayer("O Player", Tile.O, ui);
        this.currentPlayer = player1;
        this.gameState = GameState.RUNNING;
    }

    public void start() {
        do {
            ui.drawBoard(board);
            ui.showInputPrompt(currentPlayer.getName());
            makeMove();
            changeGameState();
            if (gameState == GameState.RUNNING) {
                nextPlayer();
            }
        } while (gameState == GameState.RUNNING);
        ui.drawBoard(board);
        if (gameState == GameState.WIN) {
            ui.showMessage(currentPlayer.getName() + " win!");
        } else {
            ui.showMessage("Tie!");
        }
    }

    private void makeMove() {
        Move move = currentPlayer.makeMove(board);
        board = board.accept(move);
    }

    private void nextPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    private void changeGameState() {
        if (board.hasCompletedLine(currentPlayer.getTile())) {
            gameState = GameState.WIN;
        } else if (!board.hasPossibleMoves()) {
            gameState = GameState.TIE;
        }
    }

    public static void main(String[] args) {
        TicTacToeGame ticTacToeGame = new TicTacToeGame();
        ticTacToeGame.start();
    }
}
