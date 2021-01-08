package testing;

/**
 * Testing class to test the tictactoe game as a whole and individual components. JUnit jupiter api must be installed to run
 * 
 * @author Justin Whalley 
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import Java_tictactoe.*;

@Testable
public class testing {
    Window window = new Window(true);
    GameFrame gameFrame = window.getGame();
    JTabbedPane tabbedPane = window.getTabbedPane();
    MenuBar menu = window.getMenu();
    HistoryFrame history = window.getHistory();
    StatsFrame stats = window.getStatsFrame();
    ScoreboardPanel scoreboard = gameFrame.getScoreboard();
    ButtonPanel buttonsPanel = gameFrame.getButtonPanel();
    OStatsPanel oPanel = stats.getOPanel();
    XStatsPanel xPanel = stats.getXPanel();
    GeneralStatsPanel generalStats = stats.getGeneral();
    JButton[][] buttons = buttonsPanel.getButtons();
    JLabel scoreboardLeft = scoreboard.getLeft();
    JLabel scoreboardCenter = scoreboard.getCenter();
    JLabel scoreboardRight = scoreboard.getRight();

    /**
     * test to ensure all the windows are created(not null)
     */
    @Test
    public void testAllWindowsCreated() {
        assertNotNull(window);
        assertNotNull(gameFrame);
        assertNotNull(tabbedPane);
        assertNotNull(menu);
        assertNotNull(history);
        assertNotNull(stats);
        assertNotNull(scoreboard);
        assertNotNull(buttonsPanel);
        assertNotNull(oPanel);
        assertNotNull(xPanel);
        assertNotNull(generalStats);
    }

    /**
     * test to ensure all buttons are created and initialized to empty strings
     */
    @Test
    public void testButtonsInitialized() {
        JButton[][] buttons = buttonsPanel.getButtons();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                assertEquals(buttons[i][j].getText(), "");
            }
        }
    }

    /**
     * test to ensure stats are zero on start up
     */
    @Test
    public void testStatsAreZeroed() {
        assertEquals(0, oPanel.getOWon());
        assertEquals(0, oPanel.getGamesPlayed());
        assertEquals(0, xPanel.getXWon());
        assertEquals(0, xPanel.getGamesPlayed());
        assertEquals(0, generalStats.getTotalMoves());
        assertEquals(0, generalStats.getGamesPlayed());
    }
    
    /**
     * test to see if all the stats update automatically with x won, o won and a tied game. Had to be implemented in 1 method in case not all test cases are run in series and to ensure
     * the history panel is appended the text rather than overwriting
     */
    @Test
    public void testStatsScoreboardAndHistoryUpdate() {
        //o wins
        int startingPlayer = buttonsPanel.getAlternate() %2;
        int totalMoves = 0;
        boolean[] path = {false,false};
        if(startingPlayer == 1) {
            buttons[0][0].doClick();
            buttons[1][0].doClick();
            buttons[0][1].doClick();
            buttons[2][2].doClick();
            buttons[0][2].doClick();
            totalMoves+=5;
            path[1] = true;
            assertEquals("Player O won in 5 moves\n", history.getText().getText());
        } else {
            buttons[1][1].doClick();
            buttons[1][0].doClick();
            buttons[0][1].doClick();
            buttons[2][0].doClick();
            buttons[2][2].doClick();
            buttons[0][0].doClick();
            totalMoves+=6;
            assertEquals("Player O won in 6 moves\n", history.getText().getText());
        }
        assertEquals("0.00", String.format("%.2f",xPanel.calculatePercentWin()));
        assertEquals(0, xPanel.getXWon());
        assertEquals(1, xPanel.getGamesPlayed());
        assertEquals("100.00", String.format("%.2f",oPanel.calculatePercentWin()));
        assertEquals(1, oPanel.getOWon());
        assertEquals(1, oPanel.getGamesPlayed());
        assertEquals(1, generalStats.getGamesPlayed());
        assertEquals(totalMoves, generalStats.getTotalMoves());
        assertEquals("X won: 0", scoreboardLeft.getText());
        assertEquals("O won: 1", scoreboardRight.getText());
        assertEquals("Ties: 0", scoreboardCenter.getText());
        // x wins
        startingPlayer = buttonsPanel.getAlternate() %2;
        if(startingPlayer == 0) {
            buttons[0][0].doClick();
            buttons[1][0].doClick();
            buttons[0][1].doClick();
            buttons[2][2].doClick();
            buttons[0][2].doClick();
            totalMoves+=5;
            if(path[0]) {
                assertEquals("Player O won in 5 moves\nPlayer X won in 5 moves\n", history.getText().getText());
            } else {
                assertEquals("Player O won in 6 moves\nPlayer X won in 5 moves\n", history.getText().getText());
            }
            path[1] = true;
        } else {
            buttons[1][1].doClick();
            buttons[1][0].doClick();
            buttons[0][1].doClick();
            buttons[2][0].doClick();
            buttons[2][2].doClick();
            buttons[0][0].doClick();
            totalMoves+=6;
            if(path[0]) {
                assertEquals("Player O won in 5 moves\nPlayer X won in 6 moves\n", history.getText().getText());
            } else {
                assertEquals("Player O won in 6 moves\nPlayer X won in 6 moves\n", history.getText().getText());
            }
        }
        assertEquals("50.00", String.format("%.2f",xPanel.calculatePercentWin()));
        assertEquals(1, xPanel.getXWon());
        assertEquals(2, xPanel.getGamesPlayed());
        assertEquals("50.00", String.format("%.2f",oPanel.calculatePercentWin()));
        assertEquals(1, oPanel.getOWon());
        assertEquals(2, oPanel.getGamesPlayed());
        assertEquals(2, generalStats.getGamesPlayed());
        assertEquals(totalMoves, generalStats.getTotalMoves());
        assertEquals("X won: 1", scoreboardLeft.getText());
        assertEquals("O won: 1", scoreboardRight.getText());
        assertEquals("Ties: 0", scoreboardCenter.getText());
        //game tied
        buttons[0][0].doClick();
        buttons[0][1].doClick();
        buttons[1][2].doClick();
        buttons[0][2].doClick();
        buttons[2][0].doClick();
        buttons[1][0].doClick();
        buttons[1][1].doClick();
        buttons[2][2].doClick();
        buttons[2][1].doClick();
        totalMoves+=9;
        if(path[0] && path[1]){
            assertEquals("Player O won in 5 moves\nPlayer X won in 5 moves\nTied in 9 moves\n", history.getText().getText());
        } else if (!path[0] && path[1]){
            assertEquals("Player O won in 6 moves\nPlayer X won in 6 moves\nTied in 9 moves\n", history.getText().getText());
        } else if (path[0] && !path[1]){
            assertEquals("Player O won in 5 moves\nPlayer X won in 5 moves\nTied in 9 moves\n", history.getText().getText());
        } else {
            assertEquals("Player O won in 6 moves\nPlayer X won in 6 moves\nTied in 9 moves\n", history.getText().getText());
        }
        assertEquals("33.33", String.format("%.2f",xPanel.calculatePercentWin()));
        assertEquals(1, xPanel.getXWon());
        assertEquals(3, xPanel.getGamesPlayed());
        assertEquals("33.33", String.format("%.2f",oPanel.calculatePercentWin()));
        assertEquals(1, oPanel.getOWon());
        assertEquals(3, oPanel.getGamesPlayed());
        assertEquals(3, generalStats.getGamesPlayed());
        assertEquals(totalMoves, generalStats.getTotalMoves());
        assertEquals("X won: 1", scoreboardLeft.getText());
        assertEquals("O won: 1", scoreboardRight.getText());
        assertEquals("Ties: 1", scoreboardCenter.getText());
    }

    /**
     * test to see if a user clicks an already selected button, it doesnt overwrite the first player and doesnt take a move 
     */
    @Test
    public void testNoOverwriteClick() {
        buttons[0][0].doClick();
        String text = buttons[0][0].getText();
        buttons[0][0].doClick();
        assertEquals(text,buttons[0][0].getText());
        buttons[1][0].doClick();
        assertNotEquals(text, buttons[1][0].getText());
    }

    /**
     * test to ensure the history is empty on start up
     */
    @Test
    public void testHistoryEmpty() {
        assertEquals("", history.getText().getText());
    }

    /**
     * test to ensure checkForWin method return false when game not won and differentiates between players
     */
    @Test
    public void testCheckWinNoWin() {
        assertFalse(buttonsPanel.checkForWin());
        buttons[0][0].doClick();
        buttons[1][0].doClick();
        buttons[2][0].doClick();
        assertFalse(buttonsPanel.checkForWin());
        buttonsPanel.reset(1);
    }

    /**
     * test to ensure that the checkForWin method will return true if game is won
     */
    @Test
    public void testCheckWin() {
        buttons[0][0].setText("X");
        buttons[1][0].setText("X");
        buttons[2][0].setText("X");
        assertTrue(buttonsPanel.checkForWin());
        buttonsPanel.reset(1);
    }

    /**
     * test that text changes on button click
     */
    @Test
    public void testButtonClick() {
        assertEquals("", buttons[0][0].getText());
        buttons[0][0].doClick();
        assertNotEquals("", buttons[0][0].getText());
    }

    /**
     * test that players automatically alternate
     */
    @Test
    public void testAlternatingPlayers() {
        int startingPlayer = buttonsPanel.getAlternate();
        buttons[0][0].doClick();
        assertEquals(startingPlayer+1, buttonsPanel.getAlternate());
        buttonsPanel.reset(1);
    }

    /**
     * test to ensure the bottom label is updated after each move
     */
    @Test
    public void testLabelUpdatesForAlternatingPlayers() {
        String initialText = gameFrame.getCurrentLabel().getText();
        buttons[0][0].doClick();
        assertNotEquals(initialText,gameFrame.getCurrentLabel().getText());
        buttonsPanel.reset(1);
    }

}