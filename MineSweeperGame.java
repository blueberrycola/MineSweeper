package project2;

/**********************************************************************
 * GUI for MineSweeper
 *
 * @author Chase Johnston
 * @author Nicholas Berens
 * @version February 26, 2019
 *********************************************************************/

import javax.swing.*;
import java.util.*;

import static project2.GameStatus.Won;

public class MineSweeperGame {
    /** Array of tiles across the board */
    private Cell[][] board;
    /** Status of game: Win, Lose, or still playing */
    private GameStatus status;
    /** Instance variable for mines in area */
    private int mineCount;

    public int rowVal;
    public int colVal;
    public int bombVal;
    public int winVal;
    public int lossVal;

    /**
     Here are a bunch of getters and setters that are used frequently in for loops that deal with recursion functioning,
     general display functioning, and many other capacities throughout the different classes
      @return
     */
    public int getWinVal() {
        return winVal;
    }

    public void setWinVal(int winVal) {
        this.winVal = winVal;
    }

    public int getLossVal() {
        return lossVal;
    }

    public void setLossVal(int lossVal) {
        this.lossVal = lossVal;
    }

    public int getRowVal() {
        return rowVal;
    }

    public void setRowVal(int rowVal) {
        this.rowVal = rowVal;
    }

    public int getColVal() {
        return colVal;
    }

    public void setColVal(int colVal) {
        this.colVal = colVal;
    }

    public int getBombVal() {
        return bombVal;
    }

    public void setBombVal(int bombVal) {
        this.bombVal = bombVal;
    }

    /******************************************************************
     Constructor that initializes the board with mines and tile
     properties
     */
    public MineSweeperGame(){
        rowVal = 0;
        colVal = 0;
        bombVal = 0;
        winVal = 0;
        lossVal = 0;
    }

    /**
     Constructor that puts variables into the context of the game logic
     @param row
     @param col
     @param bomb
     */
    public MineSweeperGame(int row, int col, int bomb) {
        this.rowVal = row;
        this.colVal = col;
        this.bombVal = bomb;
        board = new Cell[row][col];

        for (row = 0; row < getRowVal(); row++) {
            for (col = 0; col < getColVal(); col++) {
                board[row][col] = new Cell();
            }
        }
        Random rng = new Random();
        //Sets coords for mines (does not set image)
        int mineCount = 0;
        while (mineCount < bombVal) {
            col = rng.nextInt(getRowVal());
            row = rng.nextInt(getColVal());

            if (!board[row][col].isMine()) {
                board[row][col].setMine(true);
                System.out.println("setting mine to y: " + row + ", x: " + col);

                mineCount++;
            }
        }


    }

    /****
     Accessor Method that returns a cell for a given row and column so
     the panel can make the appropriate display
     @param row
     @param col
     @return Cell Object
     */
    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    /****
     Mutator Method that labels a cell as selected and recursively
     accounts for cells that do not have neighboring mines.
     @param row
     @param col
     */
    public void select(int row, int col) {
        int totExposed = (getRowVal() * getColVal() - getBombVal());
        int temp = 0;
        int temp1 = 0;
        //Minesweeper tile borders
        if ((row >= 0 && row < getRowVal()) && (col >= 0 && col < getColVal())) {
            //Checks if you clicked a mine
            if(board[row][col].isMine()) {
                status = GameStatus.Lost;
                lossVal++;
                setLossVal(lossVal);
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
//                for(row = 0; row <= getRowVal(); row++){
//                    for(col = 0; col <= getColVal(); col++){
//                        if(board[row][col].isExposed()){
//                            temp++;
//                        }
//                    }
//                }
                System.out.println(temp);
                temp1 = totExposed - temp;
                System.out.println(temp1);
                if(temp1 == 0){
                    setGameStatus(Won);
                    winVal++;
                    setWinVal(winVal);
                }
                return;
            }



        }
    }

    /****
     Checks the amount of neighboring bombs next to the selected tile, only used in select()
     @param row
     @param col
     @return amount of bombs in a 3x3 area or a 2x3 area or a corner
     */
    private int bombCheck(int row, int col) {
        int temp = 0;
        //Inside the perimeter
        if((row < (getRowVal() - 1) && row > 0) && (col < (getColVal() - 1) && col > 0)) {
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
        if((row >= 0 && row <= (getRowVal() - 1)) && col == 0) {
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
            } else if(row == (getRowVal() - 1)) {
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
        if((row >= 0 && row <= (getRowVal() - 1)) && col == (getColVal() - 1)) {
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
            else if(row == (getRowVal() - 1)) {
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
        if(row == 0 && (col > 0 && col < (getColVal() - 1))) {
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
        //Lower Horizontal Edge
        if(row == (getRowVal() - 1) && (col > 0 && col < (getColVal() - 1))){
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

    public void setGameStatus(GameStatus status) {
        this.status = status;
    }
}