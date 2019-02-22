package project2;

import javax.swing.*;
import java.util.*;

public class MineSweeperGame {
    int mineArea = 0;
    private Cell[][] board;
    private GameStatus status;
    private int totalMineCount = 10;
    private int mineCount;
    private boolean flagged, exposed, isMine;


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

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public void resetTiles() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (board[row][col].isMine()) {
                    board[row][col].setMine(false);
                }
                if (board[row][col].isExposed()) {
                    //FIXME:
                    System.out.println("See fixme on resetTiles()");
                }
                if (board[row][col].isFlagged()) {
                    board[row][col].setFlagged(false);
                }
            }
        }

    }

    public void select(int row, int col) {
        int temp = 0;

        //initial click check for mines
        if(board[row][col].isMine()) {
            status = GameStatus.Lost;
            JOptionPane.showMessageDialog(null,"YOU LOST!");
        } else {
            //if not a mine
            board[row][col].setExposed(true);

            //Qualifier for 3x3 scan of tiles (avoids corners)

            //FIXME replace 9 and 0 with the min and max
            if ((row < 9 && row > 0) && (col < 9 && col > 0)) {

                if (board[row][col - 1].isMine()) {
                    temp++;
                }
                if (board[row][col + 1].isMine()) {
                    temp++;
                }

                if (board[row + 1][col].isMine()) {
                    temp++;
                }
                if (board[row + 1][col - 1].isMine()) {
                    temp++;
                }
                if (board[row + 1][col + 1].isMine()) {
                    temp++;
                }

                if (board[row - 1][col].isMine()) {
                    temp++;
                }
                if (board[row - 1][col - 1].isMine()) {
                    temp++;
                }
                if (board[row - 1][col + 1].isMine()) {
                    temp++;
                }
                if(temp == 0) {
                    //RECURSION START; the code below just shows a blank 3x3 on the gui
                    board[row][col - 1].setExposed(true);
                    board[row][col + 1].setExposed(true);
                    board[row + 1][col].setExposed(true);
                    board[row + 1][col - 1].setExposed(true);
                    board[row + 1][col + 1].setExposed(true);
                    board[row - 1][col].setExposed(true);
                    board[row - 1][col - 1].setExposed(true);
                    board[row - 1][col + 1].setExposed(true);

                }

            }

            //Upper left corner
            else if (row == 0 && col == 0) {
                if (!board[row][col].isMine()) {
                    board[row][col].setExposed(true);
                    if (!board[row][col + 1].isMine()) {
                        board[row][col + 1].setExposed(true);
                    } else { temp++; }
                    if (!board[row + 1][col].isMine()) {
                        board[row + 1][col].setExposed(true);
                    } else {temp++;}
                }

            }
            //Upper right corner
            else if (row == 0 && col == 9) {
                if (!board[row][col].isMine()) {
                    board[row][col].setExposed(true);
                    if (!board[row + 1][col].isMine()) {
                        board[row + 1][col].setExposed(true);
                    }else{temp++;}
                    if (!board[row][col - 1].isMine()) {
                        board[row][col - 1].setExposed(true);
                    }else{temp++;}
                }
            }
            //Bottom left corner
            else if (row == 9 && col == 0) {
                if (!board[row][col].isMine()) {
                    board[row][col].setExposed(true);
                    if (!board[row - 1][col].isMine()) {
                        board[row - 1][col].setExposed(true);
                    }else{temp++;}
                    if (!board[row][col + 1].isMine()) {
                        board[row][col + 1].setExposed(true);
                    }else{temp++;}
                }
            }

            //Bottom right corner
            else if (row == 9 && col == 9) {
                if (!board[row][col].isMine()) {
                    board[row][col].setExposed(true);
                    if (!board[row - 1][col].isMine()) {
                        board[row - 1][col].setExposed(true);
                    }else{temp++;}
                    if (!board[row][col - 1].isMine()) {
                        board[row][col - 1].setExposed(true);
                    }else{temp++;}
                }
            }
            //Left horizontal
            else if((row >= 1 && row <= 9) && col == 0) {

            }
            //Right horizontal
            else if((col >= 1 && col <= 9) && row == 0) {

            }
            //vertical edges

            mineCount = temp;
        }


    }
    public int getScanMineCount() {return mineCount;}
}