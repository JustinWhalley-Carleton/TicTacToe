package Java_tictactoe;

/**
 * Panel to hold general stats such as games played and average moves per game.
 * 
 * @author Justin Whalley
 */
import java.awt.*;
import javax.swing.*;
public class GeneralStatsPanel extends JPanel{
    private JLabel gamesPlayedLabel;
    private JLabel averageMoves;
    private int gamesPlayed;
    private int totalMoves;
    public GeneralStatsPanel() {
        gamesPlayed = 0;
        averageMoves = new JLabel();
        gamesPlayedLabel = new JLabel();
        setLayout(new GridLayout(1,2));
        gamesPlayedLabel.setText("Games Played: " + gamesPlayed);
        averageMoves.setText("Average Moves: " + String.format("%.2f",calculateAverageMoves()));
        add(gamesPlayedLabel, BorderLayout.WEST);
        add(averageMoves, BorderLayout.EAST);
    }

    /**
     * increment games played
     */
    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    /**
     * increment total moves
     * 
     * @param int moves
     */
    public void incrementMoves(int moves) {
        totalMoves += moves;
    }

    /**
     * calculate average moves by dividing the total moves by the games played. return 0 if gamesplayed is 0 to avoid zero divider error
     * 
     * @return
     */
    public double calculateAverageMoves() {
        if(gamesPlayed==0) {
            return 0;
        } else {
            return (double)totalMoves/(double)gamesPlayed;
        }
    }

    /**
     * update both general stats labels
     */
    public void updateLabels() {
        gamesPlayedLabel.setText("Games Played: " + gamesPlayed);
        averageMoves.setText("Average Moves: " + String.format("%.2f",calculateAverageMoves()));
    }

    /**
     * get games played. used for testing
     * 
     * @return int
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * get total moves. used for testing
     * 
     * @return int
     */
    public int getTotalMoves() {
        return totalMoves;
    }
}
