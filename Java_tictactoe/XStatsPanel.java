package Java_tictactoe;

/**
 * Jpanel to hold the Xstats which includes the x win percentage and the number of x wins.
 * 
 * @author Justin Whalley
 */

import java.awt.*;
import javax.swing.*;
public class XStatsPanel extends JPanel {
    private JLabel xWins;
    private JLabel xWinPercentage;
    private int xWon;
    private int gamesPlayed;
    public XStatsPanel() {
        xWon=0;
        gamesPlayed=0;
        xWins = new JLabel();
        xWinPercentage = new JLabel();
        setLayout(new GridLayout(1,2));
        add(xWins,BorderLayout.WEST);
        add(xWinPercentage, BorderLayout.EAST);
        xWins.setText("X Won: " + xWon);
        xWinPercentage.setText("X percent win: " + String.format("%.2f",calculatePercentWin()) + "%");
    }

    /**
     * calculate the win percentage of x by dividing the xWon by games played and mulitplying by 100. To avoid the zero divider error if games played is zero it returns zero
     * 
     * @return double
     */
    public double calculatePercentWin() {
        if(gamesPlayed == 0) {
            return 0;
        } else {
            return ((double)xWon/(double)gamesPlayed) *(double)100;
        }
    }

    /**
     * update the x stat labels with current values stored in the xStatsPanel class
     */
    public void updateLabels() {
        xWins.setText("X Won: " + xWon);
        xWinPercentage.setText("X percent win: " + String.format("%.2f",calculatePercentWin()) + "%");
    }

    /**
     * increment games played
     */
    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    /**
     * increment how many times x won
     */
    public void incrementXWon() {
        xWon++;
    }

    /**
     * get number of times x won
     * 
     * @return int
     */
    public int getXWon() {
        return xWon;
    }

    /**
     * get number of games played
     * 
     * @return int
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }
}