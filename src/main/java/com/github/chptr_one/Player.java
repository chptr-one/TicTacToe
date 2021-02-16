package com.github.chptr_one;

public interface Player {

    Position getMove(GameBoard gameBoard);

    String getName();

    Mark getMark();

}
