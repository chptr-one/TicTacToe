package com.github.chptr_one.tictactoe.player;

import com.github.chptr_one.tictactoe.Tile;

public abstract class AbstractPlayer implements Player {
    protected final String name;
    protected final Tile tile;

    public AbstractPlayer(String name, Tile tile) {
        this.name = name;
        this.tile = tile;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Tile getTile() {
        return tile;
    }
}
