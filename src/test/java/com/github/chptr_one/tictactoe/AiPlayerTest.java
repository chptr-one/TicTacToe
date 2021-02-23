package com.github.chptr_one.tictactoe;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Position;
import com.github.chptr_one.tictactoe.player.AiPlayer;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.github.chptr_one.tictactoe.common.Mark.X;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AiPlayerTest {


    @Test
    void getPossibleMoves() {
        Set<Position> expectedMoves = new HashSet<>(9);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                expectedMoves.add(Position.of(row, col));
            }
        }

        GameBoard gameBoard = new GameBoard(3);
        Iterator<Position> iterator = expectedMoves.iterator();
        while (iterator.hasNext()) {
            var move = iterator.next();
            gameBoard.setMark(move, X);
            iterator.remove();
            Set<Position> actualMoves = AiPlayer.getPossibleMoves(gameBoard);
            assertEquals(expectedMoves, actualMoves);
        }
    }
}