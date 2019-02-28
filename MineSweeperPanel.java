package project2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.channels.NotYetBoundException;

import static project2.GameStatus.Lost;
import static project2.GameStatus.NotOverYet;
import static project2.GameStatus.Won;

/**********************************************************************
 * Panel for MineSweeper
 *
 * @author Chase Johnston
 * @author Nicholas Berens
 * @version February 26, 2019
 *********************************************************************/

public class MineSweeperPanel extends JPanel {
    /** Array of tiles for the board layout */
    private JButton[][] board;
    /** Instantiates variable for cell properties */
    private Cell iCell;
    /** Quit button */
    private JButton quitButton;

    /** Instantiates game object of type MineSweeperGame */
    private MineSweeperGame game;

    /** Instantiates Menu Options */
    private JPanel winLoss, buttonPanel;
    private Menu FileMenu;
    private JMenuBar menuBar;
    private JMenuItem resetGame;

    //Variables depending on user input
    private int tileCount;
    private int totMines;

    public int rowVal;
    public int colVal;
    public int bombVal;

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

    /***********************************
     * Arrays used for easy image loading for ImageIcons
     * tileImages[] and fileImg[] Index:
     * 0: unpressedTile
     * 1: scoreOne
     * 2: scoreTwo
     * 3: scoreThree
     * 4: scoreFour
     * 5: scoreFive
     * 6: scoreSix
     * 7: scoreSeven
     * 8: pressedTile
     * 9: flaggedTile
     * 10: bombTile
     **********************************/
    private Image[] tileImages = new Image[12];
    private String[] fileImg = {"resources/unpressedTile.jpg", "resources/scoreOne.jpg", "resources/scoreTwo.jpg", "resources/scoreThree.jpg",
            "resources/scoreFour.jpg", "resources/scoreFive.jpg", "resources/scoreSix.jpg", "resources/scoreSeven.jpg", "resources/woodriir.jpg",
            "resources/pressedTile.jpg", "resources/flaggedTile.jpg", "resources/bombTile.jpg"};

    public MineSweeperPanel(){
        rowVal = 0;
        colVal = 0;
        bombVal = 0;
    }


    /******************************************************************
     Constructor creates grid of buttons with specified dimensions,
     includes listeners for the buttons and displays the proper jpgs
     to their respective tiles
     *****************************************************************/
    public MineSweeperPanel( int row, int col, int bomb){
            this.rowVal = row;
            this.colVal = col;
            this.bombVal = bomb;
            setLayout(new GridLayout(row, col));
            setPreferredSize(new Dimension(1000, 1000));
            setBackground(Color.white);
            setVisible(true);

            game = new MineSweeperGame(row, col, bomb);

            board = new JButton[row][col];


            /**
             Loop initializes images from project 2 package using the fileImg[] and tileImages[]
             */
            for (int i = 0; i < 12; i++) {
                String strIO = "project2/resources/";
                String filepath = "";
                try {
                    tileImages[i] = ImageIO.read(getClass().getResource(fileImg[i]));

                } catch (IOException ex) {

                }
            }


/** Button Listener for buttons to enable responsiveness across board*/
            ButtonListener listener = new ButtonListener();
            //Initialization of 10x10 JButton 2D Array + placement
            for (int row1 = 0; row1 < getRowVal(); row1++) {
                for (int col1 = 0; col1 < getColVal(); col1++) {
                    board[row1][col1] = new JButton(" ");
                    board[row1][col1].addMouseListener(listener);

                    board[row1][col1].setIcon(new ImageIcon(tileImages[0]));
                    add(board[row1][col1]);
                }
            }
            //Horizontal glue + JMenu + Score Panel

            displayBoard();

        }



    /******************************************************************
     Constructor that displays the board by checking each individual
     cell
     *****************************************************************/
    private void displayBoard() {

        for(int row = 0; row < getRowVal(); row++) {
            for(int col = 0; col < getColVal(); col++) {
                iCell = game.getCell(row, col);

                //function will store the neighboring mine count into a temp variable
                int temp = iCell.getMineCount();

                //displays all score tiles, unpressed tiles, and pressed tiles
                if(iCell.isExposed() && (!iCell.isMine()) && !iCell.isFlagged()){
                    if(temp == 0) {
                        board[row][col].setIcon(new ImageIcon (tileImages[9]));
                    }
                    else if(temp == 1) {
                        board[row][col].setIcon(new ImageIcon (tileImages[1]));
                    }
                    else if(temp == 2) {
                        board[row][col].setIcon(new ImageIcon (tileImages[2]));
                    }
                    else if(temp == 3) {
                        board[row][col].setIcon(new ImageIcon (tileImages[3]));
                    }
                    else if(temp == 4) {
                        board[row][col].setIcon(new ImageIcon (tileImages[4]));
                    }
                    else if(temp == 5) {
                        board[row][col].setIcon(new ImageIcon (tileImages[5]));
                    }
                    else if(temp == 6) {
                        board[row][col].setIcon(new ImageIcon (tileImages[6]));
                    }
                    else if(temp == 7) {
                        board[row][col].setIcon(new ImageIcon (tileImages[7]));
                    }
                    else if(temp == 8) {
                        board[row][col].setIcon(new ImageIcon (tileImages[8]));
                    }
                    //sets text next to tile if it was pressed
                    board[row][col].setText(""  + iCell.getMineCount());

                }
                //Draws bomb image on board
                if(iCell.isMine() && !iCell.isFlagged()) {
                    board[row][col].setIcon(new ImageIcon(tileImages[11]));
                }


            }
        }
    }

    /****
     * resets game by setting all cell boolean values back to false
     */
    public void reset() {
        for(int row = 0; row < getRowVal(); row++) {
            for(int col = 0; col < getColVal(); col++) {
                iCell = game.getCell(row, col);
                if(!iCell.isMine()) {
                    iCell.setExposed(false);
                    iCell.setMineCount(0);
                    board[row][col].setIcon(new ImageIcon(tileImages[0]));
                } else {
                    iCell.setFlagged(false);
                }
            }
        }
    }

    /****
     *
     * @return the amount of mines surrounding a selected tile
     */
    public int getMineCount(){
        return game.getMineCount();
    }
    private void setTotMines(int tot) {totMines = tot;}


    /****
     * button listener inner class
     */
    private class ButtonListener implements MouseListener {



        @Override
        public void mouseClicked(MouseEvent e) {
            MineSweeperGame win = new MineSweeperGame();
            MineSweeperGame loss = new MineSweeperGame();
            if(e.getButton() == MouseEvent.BUTTON1) {
                for(int row = 0; row < getRowVal(); row++) {
                    for(int col = 0; col < getColVal(); col++) {
                        if(board[row][col] == e.getSource()) {
                            iCell = game.getCell(row, col);
                            game.select(row, col);
                            //if the game was lost we update the score and go again!
                            if(game.getGameStatus() == Lost) {
                                JOptionPane.showMessageDialog(null, "You Lost!\nWins: " + win.getWinVal() +
                                        " Losses: " + loss.getLossVal());
                                //sets up a fresh, new board for us
                                game = new MineSweeperGame(getRowVal(), getColVal(), getBombVal());
                                //the nested for loops get rid of the score values on the side at the start
                                for(row = 0; row < getRowVal(); row++) {
                                    for (col = 0; col < getColVal(); col++) {
                                        board[row][col].setText("");
                                    }
                                }
                                //display the board
                                displayBoard();
                                //reset our parameters
                                reset();
                                //reset our game status
                                game.setGameStatus(NotOverYet);
                            }

                            //if the game was won we update the score and go again!
                            if(game.getGameStatus() == Won) {
                                JOptionPane.showMessageDialog(null, "You Won! You are a Super Star!\nWins: " + win.getWinVal() +
                                        " Losses: " + loss.getLossVal());
                                //sets up a fresh, new board for us
                                game = new MineSweeperGame(getRowVal(), getColVal(), getBombVal());
                                //nested for loops get rid of score values at the start
                                for(row = 0; row < getRowVal(); row++) {
                                    for (col = 0; col < getColVal(); col++) {
                                        board[row][col].setText("");
                                    }
                                }
                                //displays the board
                                displayBoard();
                                //resets our parameters
                                reset();
                                //resets our game status
                                game.setGameStatus(NotOverYet);
                            }
                            displayBoard();
                        }

                    }
                }
            }
            //designed to enable the flagging feature inherent to minesweeper
            if(e.getButton() == MouseEvent.BUTTON3) {
                for(int row = 0; row < getRowVal(); row++) {
                    for (int col = 0; col < getColVal(); col++) {
                        if(board[row][col] == e.getSource()) {
                            iCell = game.getCell(row, col);
                            if(iCell.isMine()) {
                                //Sets a flag image and state
                                board[row][col].setIcon(new ImageIcon(tileImages[9]));
                                iCell.setFlagged(true);
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


}