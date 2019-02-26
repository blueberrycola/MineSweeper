package project2;

import javax.swing.*;
import java.util.*;

public class MineSweeperGame {
    private Cell[][] board;
    private GameStatus status;
    private int totalMineCount = 10;
    private int mineCount;

    /****
     * game logic object constructor
     */
    public MineSweeperGame() {
        board = new Cell[10][10];

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                board[row][col] = new Cell();
            }
        }
        Random rng = new Random();
        //Sets coords for mines (does not set image)
        int mineCount = 0;
        while (mineCount < totalMineCount) {
            int col = rng.nextInt(10);
            int row = rng.nextInt(10);

            if (!board[row][col].isMine()) {
                board[row][col].setMine(true);
                System.out.println("setting mine to y: " + row + ", x: " + col);

                mineCount++;
            }
        }


    }

    /****
     *
     * @param row
     * @param col
     * @return Cell Object
     */
    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    /****
     * Selects the tile you chose, if no neighboring mines start recursion attempt
     * @param row
     * @param col
     */
    public void select(int row, int col) {

        //Minesweeper tile borders
        if ((row >= 0 && row < 10) && (col >= 0 && col < 10)) {
            //Checks if you clicked a mine
            if(board[row][col].isMine()) {
                status = GameStatus.Lost;
            }

            board[row][col].setMineCount(bombCheck(row, col));
            if (!board[row][col].isExposed() && board[row][col].getMineCount() == 0 && !board[row][col].isMine()) {
                board[row][col].setExposed(true);

                //Recursive 3x3 Scan
                select(row + 1, col);
                select(row + 1, col + 1);
                select(row + 1, col - 1);
                select(row - 1, col);
                select(row - 1, col + 1);
                select(row - 1, col - 1);
                select(row, col - 1);
                select(row, col + 1);
            } else {
                //If not a mine show the score
                if(!board[row][col].isMine()) {
                    board[row][col].setExposed(true);
                }
                return;
            }



        }
    }

    /****
     * Checks the amount of neighboring bombs next to the selected tile, only used in select()
     * @param row
     * @param col
     * @return amount of bombs in a 3x3 area or a 2x3 area or a corner
     */
    private int bombCheck(int row, int col) {
        int temp = 0;
        //Inside the perimeter
        if((row < 9 && row > 0) && (col < 9 && col > 0)) {
            //middle two between select
            if (board[row + 1][col].isMine()) {
                temp++;
            }
            if (board[row - 1][col].isMine()) {
                temp++;
            }
            //top 3
            if (board[row - 1][col - 1].isMine()) {
                temp++;
            }
            if (board[row][col - 1].isMine()) {
                temp++;
            }
            if (board[row + 1][col - 1].isMine()) {
                temp++;
            }
            //bottom 3
            if (board[row - 1][col + 1].isMine()) {
                temp++;
            }
            if (board[row][col + 1].isMine()) {
                temp++;
            }
            if (board[row + 1][col + 1].isMine()) {
                temp++;
            }
            return temp;

        }
        //0-9 y axis, x always 0
        if((row >= 0 && row <= 9) && col == 0) {
            //Upper Corner
            if(row == 0) {
                if(board[row + 1][col].isMine()) {
                    temp++;
                }
                if(board[row][col + 1].isMine()) {
                    temp++;
                }
                return temp;
            //Lower Corner
            } else if(row == 9) {
                if(board[row - 1][col].isMine()) {
                    temp++;
                }
                if(board[row][col + 1].isMine()) {
                    temp++;
                }
                return temp;
            } else {
                if(board[row + 1][col].isMine()) {
                    temp++;
                }
                if(board[row - 1][col].isMine()) {
                    temp++;
                }
                if(board[row][col + 1].isMine()) {
                    temp++;
                }
                if(board[row + 1][col + 1].isMine()) {
                    temp++;
                }
                if(board[row - 1][col + 1].isMine()) {
                    temp++;
                }
                return temp;
            }
        }
        //0-9 x always 9
        if((row >= 0 && row <= 9) && col == 9) {
            //Upper Corner
            if(row == 0) {
                if(board[row + 1][col].isMine()) {
                    temp++;
                }
                if(board[row][col - 1].isMine()) {
                    temp++;
                }
                return temp;
            }
            //Lower Corner
            else if(row == 9) {
                if(board[row - 1][col].isMine()) {
                    temp++;
                }
                if(board[row][col - 1].isMine()) {
                    temp++;
                }
                return temp;
            }else {
                if(board[row + 1][col].isMine()){temp++;}
                if(board[row + 1][col - 1].isMine()){temp++;}
                if(board[row - 1][col].isMine()){temp++;}
                if(board[row - 1][col - 1].isMine()){temp++;}
                if(board[row][col - 1].isMine()){temp++;}
                return temp;
            }
        }
        //1-8 Horizontal edges
        if(row == 0 && (col > 0 && col < 9)) {
            if(board[row][col + 1].isMine()){
                temp++;
            }
            if(board[row][col - 1].isMine()){
                temp++;
            }
            if(board[row + 1][col + 1].isMine()){
                temp++;
            }
            if(board[row + 1][col].isMine()){
                temp++;
            }
            if(board[row + 1][col - 1].isMine()){
                temp++;
            }
            return temp;
        }

        if(row == 9 && (col > 0 && col < 9)){
            if(board[row][col + 1].isMine()){
                temp++;
            }
            if(board[row][col - 1].isMine()){
                temp++;
            }
            if(board[row - 1][col + 1].isMine()){
                temp++;
            }
            if(board[row - 1][col].isMine()){
                temp++;
            }
            if(board[row - 1][col - 1].isMine()){
                temp++;
            }
            return temp;
        }

        return temp;


    }

    /****
     *
     * @return amount of mines neighboring the selected tile
     */
    public int getMineCount() {return mineCount;}
    /****
     * FIXME: IMPLEMENT
     * @return GameStatus Enum
     */
    public GameStatus getGameStatus() {
        return status;
    }
}
