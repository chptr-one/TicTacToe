package com.github.chptr_one.tictactoe;

public class Move {
    private final Coordinates coordinates;
    private final Tile tile;

    public Move(Coordinates coordinates, Tile tile) {
        this.coordinates = coordinates;
        this.tile = tile;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Tile getTile() {
        return tile;
    }
}
