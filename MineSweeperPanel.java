package project2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MineSweeperPanel extends JPanel {
    private int totMines;
    private JButton[][] board;
    private Cell iCell;
    private JButton quitButton;

    private MineSweeperGame game;
    private JPanel winLoss, buttonPanel;
    private Menu FileMenu;
    private MenuBar menuBar, devBar;
    private MenuItem saveScore, leadScore, highScore;
    private MenuItem devDisplay, devRandomizeBomb;
    private ActionListener tileClick;

    /***********************************
     * Arrays used for easy image loading for ImageIcons
     * tileImages[] and fileImg[] Index:
     * 0: unpressedTile
     * 1: scoreOne
     * 2: scoreTwo
     * 3: scoreThree
     * 4: scoreFour
     * 5: pressedTile
     * 6: bombTile
     **********************************/
    private Image[] tileImages = new Image[7];
    private String[] fileImg = {"unpressedTile.jpg", "scoreOne.jpg", "scoreTwo.jpg", "scoreThree.jpg",
            "scoreFour.jpg", "pressedTile.jpg", "bombTile.jpg"};




    public MineSweeperPanel() {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(1000,850));
        setBackground(Color.white);
        setVisible(true);

        game = new MineSweeperGame();
        board = new JButton[10][10];



        /*Loop initializes images from project 2 package using the fileImg[] and tileImages[]
         *
         */
        for(int i = 0; i < 7; i++) {
            String strIO = "resources/";
            String filepath = "";
            try {
                tileImages[i] = ImageIO.read(getClass().getResource(fileImg[i]));

            } catch (IOException ex) {
                System.out.println("FIXME:");
            }
        }



        ButtonListener listener = new ButtonListener();
        //Initialization of 10x10 JButton 2D Array + placement
        for(int row = 0; row < 10; row++) {
            for(int col = 0; col < 10; col++) {
                board[row][col] = new JButton(" ");
                board[row][col].addActionListener(listener);
                board[row][col].setIcon(new ImageIcon(tileImages[0]));
                add(board[row][col]);
                }
        }
        //Horizontal glue + JMenu + Score Panel

        displayBoard();
    }

    private void displayBoard() {

        for(int row = 0; row < 10; row++) {
            for(int col = 0; col < 10; col++) {
                iCell = game.getCell(row, col);

                if(iCell.isExposed() ){
                    board[row][col].setText("" + getMineCount());
                }
                else {
                    board[row][col].setText(" ");
                }
                //Draws bomb image on board
                if(iCell.isMine()) {
                    board[row][col].setIcon(new ImageIcon(tileImages[6]));
                }

            }
        }
        //if()
    }




    public int getMineCount(){
        return totMines;
    }
    public void setMineCount(int mine) {totMines = mine;}


    private class ButtonListener implements ActionListener{
        public void actionPerformed (ActionEvent event) {
            for(int row = 0; row < 10; row++) {
                for(int col = 0; col < 10; col++) {
                    if(board[row][col] == event.getSource()) {
                        game.select(row, col);
                        board[row][col].setEnabled(false);

                        System.out.println("DEBUG: Selected tile at x: " + col + " ,y: " + row);
                        System.out.println(game.getMineArea());
                        totMines = game.getMineArea();
                        displayBoard();
                    }
                }
            }
        }
    }


}



