package com.github.chptr_one.tictactoe.ui;

import com.github.chptr_one.tictactoe.Coordinates;
import com.github.chptr_one.tictactoe.GameBoard;

public interface UI {

    void drawBoard(GameBoard board);

    Coordinates readCoordinates();

    void showInputPrompt(String name);

    void showMessage(String message);
}
