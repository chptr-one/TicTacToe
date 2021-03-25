package com.github.chptr_one.tictactoe.player;

import com.github.chptr_one.tictactoe.Coordinates;
import com.github.chptr_one.tictactoe.GameBoard;
import com.github.chptr_one.tictactoe.Move;
import com.github.chptr_one.tictactoe.Tile;
import com.github.chptr_one.tictactoe.ui.UI;

public class HumanPlayer implements Player {
    private final String name;
    private final Tile tile;
    private final UI ui;

    public HumanPlayer(String name, Tile tile, UI ui) {
        this.name = name;
        this.tile = tile;
        this.ui = ui;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Tile getTile() {
        return tile;
    }

    @Override
    public Move makeMove(GameBoard board) {
        Move move;
        Coordinates coordinates;
        do {
            coordinates = ui.readCoordinates();
            move = new Move(coordinates, tile);
        } while (!board.isValidMove(move));
        return move;
    }
}
