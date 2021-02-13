package com.github.chptr_one;

public class Cell {

    private Mark mark;

    public boolean isEmpty() {
        return mark == null;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
