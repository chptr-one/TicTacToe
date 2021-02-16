package com.github.chptr_one;

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
        var game = new TicTacToeGame(
                new GameBoard(3),
                new HumanPlayer("X player", Mark.X),
                new HumanPlayer("O player", Mark.O)
        );

        game.run();
    }

    public void run() {
        Player currentPlayer = firstPlayer;
        do {
            Position position;
            do {
                position = currentPlayer.getMove(gameBoard);
            } while (!isValidMove(position));

            gameBoard.setMark(position, currentPlayer.getMark());

            if (gameBoard.getEmptyCells() == 0) {
                gameState = GameState.DRAW;
            } else if (gameBoard.hasWinningLine(position)) {
                gameState = currentPlayer == firstPlayer ? GameState.X_WINS : GameState.O_WINS;
            }

            currentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;

        } while (gameState == GameState.RUNNING);

        System.out.println(gameState);
    }

    private boolean isValidMove(Position position) {
        return gameBoard.isValidCoordinates(position)
                && gameBoard.isEmptyCell(position);
    }
}
