package com.github.chptr_one;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameBoardTest {

    @Test
    public void emptyGameBoardCreates() {
        Assertions.assertDoesNotThrow(() -> new GameBoard(3, 3));
    }
}