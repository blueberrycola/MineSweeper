package project2;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.lang.*;

public class MineSweeperPanel extends JPanel {
    private JButton[][] board;
    private Cell iCell;
    private JButton quitButton;
    private MineSweeperGame game;
    private JPanel winLoss, buttonPanel;
    private Menu FileMenu;
    private MenuBar menuBar, devBar;
    private MenuItem saveScore, leadScore, highScore;
    private MenuItem devDisplay, devRandomizer;
    private ActionListener tileClick;
    private ImageIcon emptyIcon, bombIcon;




    public MineSweeperPanel() {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(400,400));
        setBackground(Color.white);
        setVisible(true);

        board = new JButton[10][10];
        ButtonListener listener = new ButtonListener();
        game = new MineSweeperGame();

        //ImageIcon Initialization
        emptyIcon = new ImageIcon("bomb_sprite.jpg");







        for(int row = 0; row < 10; row++) {
            for(int col = 0; col < 10; col++) {
                board[row][col] = new JButton(" ");
                board[row][col].addActionListener(listener);
                add(board[row][col]);
                }
            }

        game = new MineSweeperGame();


    }
/*
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
    */
    private class ButtonListener implements ActionListener{
        public void actionPerformed (ActionEvent event) {

        }
    }




}



