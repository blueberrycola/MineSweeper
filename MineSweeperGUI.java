package project2;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Integer.parseInt;

/**********************************************************************
 * GUI for MineSweeper
 *
 * @author Chase Johnston
 * @author Nicholas Berens
 * @version February 26, 2019
 *********************************************************************/

public class MineSweeperGUI extends MineSweeperPanel {
    public static MineSweeperGUI rowSize = new MineSweeperGUI();
    public static MineSweeperGUI colSize = new MineSweeperGUI();
    public static MineSweeperGUI bombCount = new MineSweeperGUI();
    public static int row = 10;
    public static int col = 10;
    public static int bomb = 10;

    public static void main(String args[]) {
        int row;
        int col;
        int bomb;
        do {
            String message = JOptionPane.showInputDialog(rowSize, "Enter Row Size between 3 and 30", "Size", JOptionPane.PLAIN_MESSAGE);
            String message1 = JOptionPane.showInputDialog(colSize, "Enter Column Size between 3 and 30", "Size", JOptionPane.PLAIN_MESSAGE);
            String message2 = JOptionPane.showInputDialog(bombCount, "Enter Appropriate Mine Count", "Size", JOptionPane.PLAIN_MESSAGE);
            row = parseInt(message);
            col = parseInt(message1);
            bomb = parseInt(message2);
            try {
                row = parseInt(message);
                col = parseInt(message1);
                bomb = parseInt(message2);
                //is an integer!
            } catch (NumberFormatException e){
                //not an integer!
            }
        }
        while((row > 30 || row < 3) || (col > 30 || col < 3) || (bomb > ((col * row) - 1)));
        MineSweeperGUI.row = row;
        MineSweeperGUI.col = col;
        MineSweeperGUI.bomb = bomb;
//        size.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JFrame frame = new JFrame("Minesweeper");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MineSweeperPanel panel = new MineSweeperPanel();
            frame.getContentPane().add(panel);


            frame.pack();

            frame.addMouseListener(new MouseListener() {
                public void mousePressed(MouseEvent me) { }
                public void mouseReleased(MouseEvent me) { }
                public void mouseEntered(MouseEvent me) { }
                public void mouseExited(MouseEvent me) { }
                public void mouseClicked(MouseEvent me) {
                    if(me.getButton() == MouseEvent.BUTTON1) {
                        System.out.println("Tile is clicked");
                    }

                    if(me.getButton() == MouseEvent.BUTTON3) {
                        System.out.print("Tile is flagged!");
                    }
                }
            });

        frame.setVisible(true);
    }
}


