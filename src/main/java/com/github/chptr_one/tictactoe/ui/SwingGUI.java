package com.github.chptr_one.tictactoe.ui;

import com.github.chptr_one.tictactoe.Coordinates;
import com.github.chptr_one.tictactoe.GameBoard;

import javax.swing.*;
import java.awt.*;

public class SwingGUI implements UI{

    public SwingGUI() {
        JFrame mainFrame = new JFrame("TicTacToe");
        mainFrame.setSize(new Dimension(400,400));
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    @Override
    public void drawBoard(GameBoard board) {

    }

    @Override
    public Coordinates readCoordinates() {
        return null;
    }

    @Override
    public void showInputPrompt(String name) {

    }

    @Override
    public void showMessage(String message) {

    }
}
