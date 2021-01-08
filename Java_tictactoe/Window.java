package Java_tictactoe;

/**
 * Main window for the GUI. Stores main frames in the program and serves as a connection between classes. Configures the program to be in test mode or real mode where test mode 
 * wont play sounds or show GUI.
 * 
 * @author Justin Whalley
 */
import java.awt.*;
import javax.swing.*;
public class Window extends JFrame{
    private GameFrame game;
    private HistoryFrame history;
    private StatsFrame stats;
    private JTabbedPane tabs;
    private MenuBar menuBar;

    public Window(boolean isTest) {
        tabs = new JTabbedPane();
        history = new HistoryFrame();
        stats = new StatsFrame();
        game = new GameFrame(this,history,isTest);
        menuBar = new MenuBar(this, game,history);
        tabs.add("Game",game.getContentPane());
        tabs.add("History",history.getContentPane());
        tabs.add("Stats",stats.getContentPane());
        setBounds(400,400,400,400);
        if(!isTest){
            setVisible(true);
        } else {
            setVisible(false);
        }
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(tabs, BorderLayout.CENTER);
        setJMenuBar(menuBar);
    }

    /**
     * Increment x won in the parameter moves. Redirects to stats frame
     * 
     * @param int moves
     */
    public void incrementXWon(int moves) {
        stats.incrementXWon(moves);
    }

    /**
     * Increment o won in the parameter moves. Redirects to stats frame
     * 
     * @param moves
     */
    public void incrementOWon(int moves) {
        stats.incrementOWon(moves);
    }

    /**
     * tied in the parameter moves. Redirects to stats frame
     * 
     * @param moves
     */
    public void tie(int moves) {
        stats.tie(moves);
    }
    
    /**
     * get the tabbed pane. Used for testing
     * 
     * @return JTabbedPane
     */
    public JTabbedPane getTabbedPane() {
        return tabs;
    }

    /**
     * get the history frame. Used for testing
     * 
     * @return HistoryFrame
     */
    public HistoryFrame getHistory() {
        return history;
    }

    /**
     * get the stats frame. Used for testing
     * 
     * @return StatsFrame
     */
    public StatsFrame getStatsFrame() {
        return stats;
    }

    /**
     * get the game frame. used for testing
     * 
     * @return GameFrame
     */
    public GameFrame getGame() {
        return game;
    }

    /**
     * get the menu bar. used for testing
     * 
     * @return MenuBar
     */
    public MenuBar getMenu() {
        return menuBar;
    }
}
