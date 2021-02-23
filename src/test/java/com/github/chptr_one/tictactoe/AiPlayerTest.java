package com.github.chptr_one.tictactoe;

import com.github.chptr_one.tictactoe.common.Position;
import com.github.chptr_one.tictactoe.player.AiPlayer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.chptr_one.tictactoe.common.Mark.O;
import static com.github.chptr_one.tictactoe.common.Mark.X;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AiPlayerTest {

    private final List<TestCase> xWins = List.of(
            new TestCase("xx." + ".o." + "o..", Position.of(0, 2), 5),
            new TestCase("x.o" + ".x." + "o..", Position.of(2, 2), 5),
            new TestCase(".xo" + "o.." + ".x.", Position.of(1, 1), 5)
    );

    @Test
    void aiPlayerWinsByCompletedAlmostWinningLine() {
        var aiPlayer = new AiPlayer("Test AI", X);
        for (var testCase : xWins) {
            var gameBoard = testCase.getGameBoard();
            var move = aiPlayer.getMove(gameBoard);
            assertEquals(testCase.getPos(), move);
        }
    }

    private final List<TestCase> oDefeats = List.of(
            new TestCase("xx." + ".o." + "...", Position.of(0, 2), 6),
            new TestCase("x.o" + ".x." + "...", Position.of(2, 2), 6),
            new TestCase(".xo" + "o.x" + ".x.", Position.of(1, 1), 4)
    );

    @Test
    void aiPlayerBlocksOpponentsWinningLine() {
        var aiPlayer = new AiPlayer("Test AI", O);
        for (var testCase : oDefeats) {
            var gameBoard = testCase.getGameBoard();
            var move = aiPlayer.getMove(gameBoard);
            assertEquals(testCase.getPos(), move, testCase.toString());
        }
    }

    private final List<TestCase> forkByO = List.of(
            new TestCase(".xo" + ".o." + "xx.", Position.of(2, 2), 4),
            new TestCase(".xx" + ".o." + "ox.", Position.of(0, 0), 4)
    );

    @Test
    void aiPlayerMakesFork() {
        var aiPlayer = new AiPlayer("Test AI", O);
        for (var testCase : forkByO) {
            var gameBoard = testCase.getGameBoard();
            var move = aiPlayer.getMove(gameBoard);
            assertEquals(testCase.getPos(), move, testCase.toString());
        }
    }
}