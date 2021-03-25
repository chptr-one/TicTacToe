package com.github.chptr_one.tictactoe.player;

import com.github.chptr_one.tictactoe.GameBoard;
import com.github.chptr_one.tictactoe.Move;
import com.github.chptr_one.tictactoe.Tile;

public interface Player {
    Move makeMove(GameBoard board);

    String getName();

    Tile getTile();
}
