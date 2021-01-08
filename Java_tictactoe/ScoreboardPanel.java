package Java_tictactoe;

/**
 * Hold the scoreboard above the game to show how many time each player has win or tied.
 * 
 * @author Justin Whalley
 */
import java.awt.*;
import javax.swing.*;
public class ScoreboardPanel extends JPanel {
    private JLabel scoreboardLeft;
    private JLabel scoreboardRight;
    private JLabel scoreboardCenter;
    private int xWon;
    private int oWon;
    private int tie;
    public ScoreboardPanel() {
        scoreboardLeft = new JLabel();
        scoreboardRight = new JLabel();
        scoreboardCenter = new JLabel();
        setLayout(new GridLayout(1,3));
        xWon = 0;
        oWon = 0;
        tie = 0;
        updateLabels();
        add(scoreboardLeft, BorderLayout.WEST);
        add(scoreboardCenter, BorderLayout.CENTER);
        add(scoreboardRight, BorderLayout.EAST);
        
    }

    /**
     * increment the number of time x won
     */
    public void incrementXWon() {
        xWon++;
    }

    /**
     * increment the number of times o won
     */
    public void incrementOWon() {
        oWon++;
    }

    /**
     * increment the number of tied games
     */
    public void incrementTie() {
        tie++;
    }
    
    /**
     * update all scoreboard labels
     */
    public void updateLabels() {
        scoreboardCenter.setText("Ties: " +tie);
        scoreboardLeft.setText("X won: " + xWon);
        scoreboardRight.setText("O won: "+oWon);
    }

    /**
     * get the left scoreboard label. used for testing
     * 
     * @return JLabel
     */
    public JLabel getLeft() {
        return scoreboardLeft;
    }

    /**
     * get the right scoreboard label. used for testing
     * 
     * @return JLabel
     */
    public JLabel getRight() {
        return scoreboardRight;
    }

    /**
     * get the center scoreboard label. used for testing
     * 
     * @return JLabel
     */
    public JLabel getCenter() {
        return scoreboardCenter;
    }
}
