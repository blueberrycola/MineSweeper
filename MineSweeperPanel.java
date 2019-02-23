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
    private JMenuBar menuBar;
    private JMenuItem resetGame;
    private JMenuItem devDisplay, devRandomizeBomb;
    private ActionListener tileClick;

    /***********************************
     * Arrays used for easy image loading for ImageIcons
     * tileImages[] and fileImg[] Index:
     * 0: unpressedTile
     * 1: scoreOne
     * 2: scoreTwo
     * 3: scoreThree
     * 4: scoreFour
     * 5: rareScore
     * 6: bombTile
     * 7: pressedTile
     **********************************/
    private Image[] tileImages = new Image[8];
    private String[] fileImg = {"resources/unpressedTile.jpg", "resources/scoreOne.jpg", "resources/scoreTwo.jpg", "resources/scoreThree.jpg",
            "resources/scoreFour.jpg", "resources/rareScore.jpg", "resources/bombTile.jpg", "resources/pressedTile.jpg"};




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
            String strIO = "project2/resources/";
            String filepath = "";
            try {
                tileImages[i] = ImageIO.read(getClass().getResource(fileImg[i]));

            } catch (IOException ex) {

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

                if(iCell.isExposed() && (!iCell.isMine())){
                    int temp = getMineCount();


                    if(temp == 1) {board[row][col].setIcon(new ImageIcon (tileImages[1]));}
                    else if(temp == 2) {board[row][col].setIcon(new ImageIcon (tileImages[2]));}
                    else if(temp == 3) {board[row][col].setIcon(new ImageIcon (tileImages[3]));}
                    else if(temp == 4) {board[row][col].setIcon(new ImageIcon (tileImages[4]));}
                    else if(temp >= 5) {board[row][col].setIcon(new ImageIcon (tileImages[5]));}


                    board[row][col].setText(""  + getMineCount());
                    iCell.setExposed(false);
                }
                //Draws bomb image on board
                if(iCell.isMine()) {
                    board[row][col].setIcon(new ImageIcon(tileImages[6]));
                }


            }
        }
        //if()
    }
    private void setupMenu() {
        //Initialization of JMenuBar + etc

        menuBar = new JMenuBar();
        add(menuBar);

        JMenu fileMenu = new JMenu("Game");
        menuBar.add(fileMenu);

        //Initialization of menu options
        resetGame = new JMenuItem("Reset");
        fileMenu.add(resetGame);
        resetGame.addActionListener(e -> reset());

    }

    public void reset() {
        System.out.println("FIXME: ADD RESET BUTTON");
    }

    public void gameOver() {

    }




    public int getMineCount(){
        return game.getScanMineCount();
    }
    private void setTotMines(int tot) {totMines = tot;}



    private class ButtonListener implements ActionListener{
        public void actionPerformed (ActionEvent event) {
            for(int row = 0; row < 10; row++) {
                for(int col = 0; col < 10; col++) {
                    if(board[row][col] == event.getSource()) {
                        iCell = game.getCell(row, col);
                        if(iCell.isMine()) {
                            reset();
                        }
                        game.select(row, col);



                        System.out.println("DEBUG: Selected tile at x: " + col + " ,y: " + row);
                        System.out.println(game.getScanMineCount());
                        displayBoard();
                    }
                }
            }
        }
    }


}



