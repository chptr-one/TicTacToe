package com.github.chptr_one.tictactoe;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Position;
import com.github.chptr_one.tictactoe.player.AiPlayer;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.github.chptr_one.tictactoe.common.Mark.X;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AiPlayerTest {

    private final List<TestCase> oneMoveToWin = List.of(
            new TestCase("xx." + ".o." + "o..", Position.of(0, 2), 5)
    );

    @Test
    void aiPlayerWinsIfCanFillTheLine() {
        var aiPlayer = new AiPlayer("Test AI", X);
        for (var testCase : oneMoveToWin) {
            var gameBoard = testCase.getGameBoard();
            var move = aiPlayer.getMove(gameBoard);
            assertEquals(testCase.getPos(), move);
        }
    }


}