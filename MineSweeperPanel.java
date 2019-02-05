package project2;

import project2.Cell;
import project2.MineSweeperGame;

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
    /*
        tileImages[] and fileImg[] Index:
            0: unpressedTile
            1: scoreOne
            2: scoreTwo
            3: scoreThree
            4: scoreFour
            5: pressedTile
            6: bombTile
     */
    private Image[] tileImages = new Image[7];
    private String[] fileImg = {"unpressedTile.jpg", "scoreOne.jpg", "scoreTwo.jpg", "scoreThree.jpg",
        "scoreFour.jpg", "pressedTile.jpg", "bombTile.jpg"};

    private MineSweeperGame game;
    private JPanel winLoss, buttonPanel;
    private Menu FileMenu;
    private MenuBar menuBar, devBar;
    private MenuItem saveScore, leadScore, highScore;
    private MenuItem devDisplay, devRandomizer;
    private ActionListener tileClick;
    // image icon order: default, flag, bomb, scoreOne, scoreTwo, scoreThree, scoreFour




    public MineSweeperPanel() {

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(1000,850));
        setBackground(Color.white);
        setVisible(true);

        for(int i = 0; i < 7; i++) {
            String strIO = "resources/";
            String filepath = "";
            try {
                tileImages[i] = ImageIO.read(getClass().getResource(fileImg[i]));

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }



        board = new JButton[10][10];
        ButtonListener listener = new ButtonListener();
        game = new MineSweeperGame();


        for(int row = 0; row < 10; row++) {
            for(int col = 0; col < 10; col++) {

                board[row][col] = new JButton(" ");
                board[row][col].addActionListener(listener);
                board[row][col].setIcon(new ImageIcon(tileImages[0]));
                add(board[row][col]);
                }
            }

        game = new MineSweeperGame();


    }

    private void displayBoard() {
        for(int row = 0; row < 10; row++) {
            for(int col = 0; col < 10; col++) {
                iCell = game.getCell(row, col);

                if(iCell.isExposed()) {
                    board[row][col].setEnabled(true);
                } else {
                    board[row][col].setText("" + getMineCount());
                }
            }
        }
    }
    public int getMineCount(){
        return totMines;
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed (ActionEvent event) {

        }
    }




}



