package com.github.chptr_one;

public class TicTacToeGame {

    private final GameBoard gameBoard;
    private final Player firstPlayer;
    private final Player secondPlayer;

    public TicTacToeGame(GameBoard gameBoard, Player firstPlayer, Player secondPlayer) {
        this.gameBoard = gameBoard;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    private GameState gameState = GameState.RUNNING;

    private boolean isValidMove(Position position) {
        return gameBoard.isValidCoordinates(position)
                && gameBoard.isEmpty(position);
    }

    public void run() {
        Player currentPlayer = firstPlayer;
        do {
            Position position;
            do {
                position = currentPlayer.getMove(gameBoard);
            } while (!isValidMove(position));


            currentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
        } while (gameState == GameState.RUNNING);
    }

    public static void main(String[] args) {
        var game = new TicTacToeGame(
                new GameBoard(3, 3),
                new HumanPlayer("X player"),
                new HumanPlayer("O player")
        );

        game.run();
    }
}
