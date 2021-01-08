package Java_tictactoe;

/**
 * Game frame to combine the buttons, scoreboard and current player label to one frame
 * 
 * @author Justin Whalley
 */
import java.awt.*;
import javax.swing.*;
public class GameFrame extends JFrame{
    private JLabel label;
    private ScoreboardPanel scoreboard;
    private ButtonPanel buttonPanel;
    public GameFrame(Window window, HistoryFrame history,boolean isTest) {
        label = new JLabel();
        scoreboard = new ScoreboardPanel();
        buttonPanel = new ButtonPanel(this, window,history,scoreboard, isTest);
        label.setText("X's turn");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        add(label, BorderLayout.SOUTH);
        add(scoreboard, BorderLayout.NORTH);
        getContentPane().add(buttonPanel,BorderLayout.CENTER);
    }
    
    /**
     * reset the buttons on the games(new game)
     * 
     * @param int startingPlayer
     */
    public void reset(int startingPlayer) {
        buttonPanel.reset(startingPlayer);
    }

    /**
     * update the bottom label
     * 
     * @param String text
     */
    public void updateLabel(String text) {
        label.setText(text);
    }

    /**
     * get the bottom label.used for testing
     * 
     * @return JLabel 
     */
    public JLabel getCurrentLabel() {
        return label;
    }

    /**
     * get the button panel. used for testing
     * 
     * @return ButtonPanel
     */
    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    /**
     * get the scoreboard panel. used for testing
     * 
     * @return ScoreboardPanel
     */
    public ScoreboardPanel getScoreboard() {
        return scoreboard;
    }

}
