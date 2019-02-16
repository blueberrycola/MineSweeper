package project2;

import javax.swing.*;
import java.util.*;

public class MineSweeperGame {
    private Cell[][] board;
    private GameStatus status;
    private int totalMineCount = 10;
    private int mineCount;
    private boolean flagged, exposed, isMine;

    public MineSweeperGame() {
        board = new Cell[10][10];


        for(int row = 0; row < 10; row++) {
            for(int col = 0; col < 10; col++) {
                board[row][col] = new Cell();
            }
        }
        Random rng = new Random();
        //Sets coords for mines (does not set image)
        int mineCount = 0;
        while(mineCount < totalMineCount) {
            int col = rng.nextInt(10);
            int row = rng.nextInt(10);

            if(!board[row][col].isMine()){
                board[row][col].setMine(true);
                System.out.println("setting mine to y: " + row + ", x: " + col);

                mineCount++;
            }
        }

    }
    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public void select(int row, int col) {
        if(!board[row][col].isExposed()) {
            if(board[row][col].isMine()) {
                status = GameStatus.Lost;
                JOptionPane.showMessageDialog(null, "GAME OVER: It really do be like that sometimes");
            } else {
                if(board[row][col].isFlagged()) {
                    //FIXME: Flag tile mechanics
                }
                status = GameStatus.NotOverYet;
            }
        }
    }
}