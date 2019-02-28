package project2;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.Border;
import java.awt.*;
import static project2.GameStatus.Lost;
import static project2.GameStatus.Won;

import static java.lang.Integer.parseInt;

/**********************************************************************
 * GUI for MineSweeper
 *
 * @author Chase Johnston
 * @author Nicholas Berens
 * @version February 26, 2019
 *********************************************************************/

public class MineSweeperGUI extends MineSweeperPanel {

    public static void main(String args[]) {
        int row = 0;
        int col = 0;
        int bomb = 0;
        String message;
        String message1;
        String message2;
        do {
            message = JOptionPane.showInputDialog(null, "Enter Row Size between 3 and 30",
                    "Size", JOptionPane.PLAIN_MESSAGE);
            message1 = JOptionPane.showInputDialog(null, "Enter Column Size between 3 and 30",
                    "Size", JOptionPane.PLAIN_MESSAGE);
            message2 = JOptionPane.showInputDialog(null, "Enter Appropriate Mine Count",
                    "Size", JOptionPane.PLAIN_MESSAGE);
            try {
                row = parseInt(message);
                col = parseInt(message1);
                bomb = parseInt(message2);
                //is an integer!
            } catch (NumberFormatException e) {
                //not an integer!
            }
            if(message.equals("") || message1.equals("") || message2.equals("")){
                break;
            }

        }
        while ((row > 30 || row < 3) || (col > 30 || col < 3) || (bomb > ((col * row) - 1)) || bomb <= 0);

//        MineSweeperPanel panel1 = new MineSweeperPanel(row, col, bomb);
        MineSweeperPanel panel1 = new MineSweeperPanel(row, col, bomb);
        MineSweeperGame game1 = new MineSweeperGame(row, col, bomb);
        panel1.setRowVal(row);
        panel1.setColVal(col);
        panel1.setBombVal(bomb);
        game1.setRowVal(row);
        game1.setColVal(col);
        game1.setBombVal(bomb);


//        size.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MineSweeperPanel panel = new MineSweeperPanel(row, col, bomb);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}


