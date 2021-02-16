package com.github.chptr_one.tictactoe.player;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Mark;
import com.github.chptr_one.tictactoe.common.Position;

public interface Player {

    Position getMove(GameBoard gameBoard);

    String getName();

    Mark getMark();

}
