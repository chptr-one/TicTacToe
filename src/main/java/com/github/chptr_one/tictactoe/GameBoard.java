package com.github.chptr_one.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GameBoard {
    private final int size;
    private final Tile[][] tiles;
    private int emptyTilesCount;

    public GameBoard(int size) {
        this.size = size;
        this.tiles = new Tile[size][size];
        for (Tile[] row : tiles) {
            Arrays.fill(row, Tile.BLANK);
        }
        this.emptyTilesCount = size * size;
    }

    // for testing purposes only
    public GameBoard(Tile[][] tiles) {
        this.size = tiles.length;
        this.tiles = new Tile[size][size];
        for (int row = 0; row < size; row++) {
            System.arraycopy(tiles[row], 0, this.tiles[row], 0, size);
        }
        this.emptyTilesCount = (int) Arrays.stream(tiles)
                .flatMap(Arrays::stream)
                .filter(tile -> tile == Tile.BLANK)
                .count();
    }

    private GameBoard(GameBoard origin) {
        this.size = origin.size;
        this.tiles = new Tile[size][size];
        for (int row = 0; row < size; row++) {
            System.arraycopy(origin.tiles[row], 0, this.tiles[row], 0, size);
        }
        this.emptyTilesCount = origin.emptyTilesCount;
    }

    public GameBoard accept(Move playersMove) {
        Objects.requireNonNull(playersMove);
        if (isValidMove(playersMove) && playersMove.getTile() != Tile.BLANK) {
            int row = playersMove.getCoordinates().getRow();
            int col = playersMove.getCoordinates().getCol();
            GameBoard result = new GameBoard(this);
            result.tiles[row][col] = playersMove.getTile();
            result.emptyTilesCount--;
            return result;
        } else {
            return null;
        }
    }

    public boolean isValidCoordinates(Coordinates coordinates) {
        Objects.requireNonNull(coordinates);
        int row = coordinates.getRow();
        int col = coordinates.getCol();
        return (row >= 0 && row < size && col >= 0 && col < size);
    }

    public boolean isValidMove(Move move) {
        Objects.requireNonNull(move);
        Coordinates coordinates = move.getCoordinates();
        return isValidCoordinates(coordinates)
                && move.getTile() != Tile.BLANK
                && tiles[coordinates.getRow()][coordinates.getCol()] == Tile.BLANK;
    }

    public boolean hasPossibleMoves() {
        return emptyTilesCount > 0;
    }

    public List<Coordinates> getBlankTilesCoordinates() {
        List<Coordinates> coordinates = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (tiles[row][col] == Tile.BLANK) {
                    coordinates.add(Coordinates.of(row, col));
                }
            }
        }
        return coordinates;
    }

    public Tile[][] getTiles() {
        Tile[][] copy = new Tile[size][size];
        for (int row = 0; row < size; row++) {
            System.arraycopy(tiles[row], 0, copy[row], 0, size);
        }
        return copy;
    }

    public boolean hasCompletedLine(Tile playerTile) {
        // Players have not yet made enough moves to win
        if (emptyTilesCount > size * size - (size * 2 - 1)) return false;

        for (int i = 0; i < size; i++) {
            boolean row = true;
            boolean col = true;
            for (int j = 0; j < size; j++) {
                row = row && tiles[i][j] == playerTile;
                col = col && tiles[j][i] == playerTile;
            }
            if (row || col) return true;
        }

        boolean topDown = true;
        boolean downTop = true;
        for (int i = 0; i < size; i++) {
            topDown = topDown && tiles[i][i] == playerTile;
            downTop = downTop && tiles[size - i - 1][i] == playerTile;
        }
        return topDown || downTop;
    }

    public int getSize() {
        return size;
    }

    public int getEmptyTilesCount() {
        return emptyTilesCount;
    }
}