package project2;

import java.util.*;

public class MineSweeperGame {
    private Cell[][] board;
    private GameStatus status;
    private int totalMineCount = 10;
    private Random rng;
    private int mineCount;
    private boolean flagged, exposed, isMine;

    public MineSweeperGame() {
        board = new Cell[10][10];

        for(int row = 0; row < 10; row++) {
            for(int col = 0; col < 10; col++) {
                board[row][col](0, false, false, false);
            }
        }
        Random rand = new Random();
        int mineCount = 0;
        while(mineCount < totalMineCount) {
            int col = rand.nextInt(10);
            int row = rand.nextInt(10);

            if(!board[row][col].isMine()){
                board[row][col].setMine(true);
                mineCount++;
            }
        }

    }
    public Cell getCell(int row, int col) {
        Cell iCell = new Cell(row, col);
        return iCell;
    }

    public void select(int row, int col) {

    }
}