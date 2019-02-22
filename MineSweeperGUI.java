package project2;

import javax.swing.*;


public class MineSweeperGUI extends MineSweeperPanel {

    public static void main(String args[]) {
            JFrame frame = new JFrame("Minesweeper");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MineSweeperPanel panel = new MineSweeperPanel();
            frame.getContentPane().add(panel);


            frame.pack();
            frame.setVisible(true);

    }
}


