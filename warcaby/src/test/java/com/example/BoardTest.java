package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.example.Board;
import main.java.com.example.Player;

/**
 * Unit test for simple App.
 */
public class BoardTest 
{
    Board testBoard = new Board();
    /**
     * Rigorous Test :-)
     */
    @Test
    public void isPlayerMoveTest() {
        Player testPlayer = new Player();
        testBoard.whitePlayer = testPlayer;
        testBoard.isWhiteTurn = true;
        assertEquals(true, testBoard.isPlayerMove(testPlayer));
    }

    @Test
    public void makeBoardTest() {
        testBoard.makeBoard();
        assertEquals(0, testBoard.board[0][0]);
    }
}
