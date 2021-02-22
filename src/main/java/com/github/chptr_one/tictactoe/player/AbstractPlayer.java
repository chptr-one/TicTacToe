package com.github.chptr_one.tictactoe.player;

import com.github.chptr_one.tictactoe.common.GameBoard;
import com.github.chptr_one.tictactoe.common.Mark;
import com.github.chptr_one.tictactoe.common.Position;

abstract class AbstractPlayer implements Player {

    protected final String name;
    protected final Mark mark;

    protected AbstractPlayer(String name, Mark mark) {
        this.name = name;
        this.mark = mark;
    }

    @Override
    abstract public Position getMove(GameBoard gameBoard);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Mark getMark() {
        return mark;
    }
}
