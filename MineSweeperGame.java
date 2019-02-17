package project2;

import javax.swing.*;
import java.util.*;

public class MineSweeperGame {
    int mineArea  = 0;
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
        mineArea = 0;
        board[row][col].setExposed(true);
        if(board[row][col].isExposed()) {
            if (board[row][col].isMine()) {
                status = GameStatus.Lost;
                JOptionPane.showMessageDialog(null, "GAME OVER: It really do be like that sometimes");
            } else if (!(board[row][col].isMine())) {

                //this is for inside the perimeter
                if((row < 9 && row > 0) && (col < 9 && col > 0)) {
                    for (int i = row - 1; i <= row + 1; i++) {
                        if (board[i][col - 1].isMine()) {
                            mineArea++;
                        }
                    }
                    for (int i = row - 1; i <= row + 1; i++) {
                        if (board[i][col].isMine()) {
                            mineArea++;
                        }
                    }
                    for (int i = row - 1; i <= row + 1; i++) {
                        if (board[i][col + 1].isMine()) {
                            mineArea++;
                        }
                    }
                }

                //this is for the bottom row
                else if(row == 9){
                    if(col == 0){
                        for(int i = row - 1; i <= row; i++){
                            if(board[i][col].isMine()){
                                mineArea++;
                            }
                        }
                        for(int i = row - 1; i <= row; i++){
                            if(board[i][col + 1].isMine()){
                                mineArea++;
                            }
                        }
                    }

                    else if(col == 9){
                        for(int i = row - 1; i <= row; i++){
                            if(board[i][col - 1].isMine()){
                                mineArea++;
                            }
                        }
                        for(int i = row - 1; i <= row; i++){
                            if(board[i][col].isMine()){
                                mineArea++;
                            }
                        }
                    }

                    else{
                        for (int i = row - 1; i <= row; i++) {
                            if (board[i][col - 1].isMine()) {
                                mineArea++;
                            }
                        }
                        for (int i = row - 1; i <= row; i++) {
                            if (board[i][col].isMine()) {
                                mineArea++;
                            }
                        }
                        for(int i = row - 1; i <= row; i++){
                            if (board[i][col + 1].isMine()) {
                                mineArea++;
                            }
                        }
                    }
                }

                //this is for the top row
                else if(row == 0){
                    if(col == 0){
                        for(int i = row; i <= row + 1; i++){
                            if(board[i][col].isMine()){
                                mineArea++;
                            }
                        }
                        for(int i = row; i <= row + 1; i++){
                            if(board[i][col + 1].isMine()){
                                mineArea++;
                            }
                        }
                    }

                    else if(col == 9){
                        for(int i = row; i <= row + 1; i++){
                            if(board[i][col - 1].isMine()){
                                mineArea++;
                            }
                        }
                        for(int i = row; i <= row + 1; i++){
                            if(board[i][col].isMine()){
                                mineArea++;
                            }
                        }
                    }

                    else{
                        for (int i = row; i <= row + 1; i++) {
                            if (board[i][col - 1].isMine()) {
                                mineArea++;
                            }
                        }
                        for (int i = row; i <= row + 1; i++) {
                            if (board[i][col].isMine()) {
                                mineArea++;
                            }
                        }
                        for (int i = row; i <= row + 1; i++) {
                            if (board[i][col + 1].isMine()) {
                                mineArea++;
                            }
                        }
                    }
                }

                //this is for the right column
                else if(col == 9){
                    if(row < 9 && row > 0){
                        for(int i = row - 1; i <= row + 1; i++){
                            if (board[i][col - 1].isMine()) {

                                mineArea++;
                            }
                        }
                        for(int i = row - 1; i <= row + 1; i++){
                            if(board[i][col].isMine()){
                                mineArea++;
                            }
                        }
                    }
            }
                //this is for the left column
                else if(col == 0){
                    if(row < 9 && row > 0){
                        for(int i = row - 1; i <= row + 1; i++){
                            if (board[i][col].isMine()) {

                                mineArea++;
                            }
                        }
                        for(int i = row - 1; i <= row + 1; i++){
                            if(board[i][col + 1].isMine()){
                                mineArea++;
                            }
                        }
                    }
                }
            else {
                if(board[row][col].isFlagged()) {
                    //FIXME: Flag tile mechanics
                }
                status = GameStatus.NotOverYet;
            }

            }
        }
    }
    public int getMineArea() {return mineArea;}
}