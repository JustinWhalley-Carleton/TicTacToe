package Java_tictactoe;

/**
 * Panel to hold the stats associated with the o player for the stats frame.
 * 
 * @author Justin Whalley
 */
import java.awt.*;
import javax.swing.*;
public class OStatsPanel extends JPanel {
    private JLabel oWins;
    private JLabel oWinPercentage;
    private int oWon;
    private int gamesPlayed;
    public OStatsPanel() {
        oWins = new JLabel();
        oWinPercentage = new JLabel();
        oWins.setText("O Won: " + oWon);
        oWinPercentage.setText("O percent win: " + String.format("%.2f",calculatePercentWin()) + "%");
        setLayout(new GridLayout(1,2));
        add(oWins,BorderLayout.WEST);
        add(oWinPercentage, BorderLayout.EAST);
    }

    /**
     * update both stats labels
     */
    public void updateLabels() {
        oWins.setText("O Won: " + oWon);
        oWinPercentage.setText("O percent win: " + String.format("%.2f",calculatePercentWin()) + "%");
    }

    /**
     * increment the number of games played
     */
    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    /**
     * increment the number of games o won
     */
    public void incrementOWon() {
        oWon++;
    }

    /**
     * calculate the win percentage of o by dividing the number of time o won by games played then mulitplying by 100. Return 0 when games played is 0 to avoid zero divider error
     * 
     * @return double
     */
    public double calculatePercentWin() {
        if(gamesPlayed==0) {
            return 0;
        } else {
            return ((double)oWon/(double)gamesPlayed)*(double)100;
        }
    }

    /**
     * get the number of time o has won
     * 
     * @return int
     */
    public int getOWon() {
        return oWon;
    }

    /**
     * get the number of games played
     * 
     * @return int
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }
}