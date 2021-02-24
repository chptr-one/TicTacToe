package com.github.chptr_one.tictactoe.player;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Mark;
import com.github.chptr_one.tictactoe.common.Position;
import com.github.chptr_one.tictactoe.ui.ConsoleUI;

public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(String name, Mark mark) {
        super(name, mark);
    }

    @Override
    public Position getMove(GameBoard gameBoard) {
        return ConsoleUI.readPosition(this);
    }
}
