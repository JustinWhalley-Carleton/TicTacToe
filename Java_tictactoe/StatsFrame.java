package Java_tictactoe;
/**
 * Frame to combine the general stats, x stats, and the o stats into a visable states frame.
 * 
 * @author Justin Whalley
 */
import java.awt.*;
import javax.swing.*;
public class StatsFrame extends JFrame {
    private GeneralStatsPanel generalPanel;
    private OStatsPanel oStatsPanel;
    private XStatsPanel xStatsPanel;
    public StatsFrame() {
        generalPanel = new GeneralStatsPanel();
        oStatsPanel = new OStatsPanel();
        xStatsPanel = new XStatsPanel();
        setLayout(new GridLayout(3,1));
        add(generalPanel, BorderLayout.NORTH);
        add(xStatsPanel, BorderLayout.CENTER);
        add(oStatsPanel, BorderLayout.SOUTH);
    }

    /**
     * increment all stats panels gamesplayed, increment x won in x stats panel and update all stat labels
     * 
     * @param int moves
     */
    public void incrementXWon(int moves) {
        xStatsPanel.incrementXWon();
        xStatsPanel.incrementGamesPlayed();
        oStatsPanel.incrementGamesPlayed();
        generalPanel.incrementGamesPlayed();
        generalPanel.incrementMoves(moves);
        xStatsPanel.updateLabels();
        oStatsPanel.updateLabels();
        generalPanel.updateLabels();
    }

    /**
     * increment all stats panels gamesplayed, increment o won in o stats panel and update all stat labels
     * 
     * @param int moves
     */
    public void incrementOWon(int moves) {
        oStatsPanel.incrementOWon();
        xStatsPanel.incrementGamesPlayed();
        oStatsPanel.incrementGamesPlayed();
        generalPanel.incrementGamesPlayed();
        generalPanel.incrementMoves(moves);
        xStatsPanel.updateLabels();
        oStatsPanel.updateLabels();
        generalPanel.updateLabels();
    }

    /**
     * increment all stats panels gamesplayed, and update all stat labels
     * 
     * @param int moves
     */
    public void tie(int moves) {
        oStatsPanel.incrementGamesPlayed();
        xStatsPanel.incrementGamesPlayed();
        generalPanel.incrementGamesPlayed();
        generalPanel.incrementMoves(moves);
        xStatsPanel.updateLabels();
        oStatsPanel.updateLabels();
        generalPanel.updateLabels();
    }

    /**
     * get x stats panel. used for testing
     * 
     * @return XStatsPanel
     */
    public XStatsPanel getXPanel() {
        return xStatsPanel;
    }

    /**
     * get o stats panel. used for testing
     * 
     * @return OStatsPanel
     */
    public OStatsPanel getOPanel() {
        return oStatsPanel;
    }

    /**
     * get general stats panel. used for testing 
     * 
     * @return GeneralStatsPanel
     */
    public GeneralStatsPanel getGeneral() {
        return generalPanel;
    }
}
