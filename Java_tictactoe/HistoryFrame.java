package Java_tictactoe;

/**
 * frame to hold and add to the history tab
 * 
 * @author Justin Whalley 
 */
import java.awt.*;
import javax.swing.*;
public class HistoryFrame extends JFrame{
    private JTextArea historyText;
    private JScrollPane historyScroll;

    public HistoryFrame() {
        historyText = new JTextArea();
        historyScroll = new JScrollPane(historyText);
        add(historyScroll);
    }

    /**
     * add a text line to the history 
     * 
     * @param String text
     */
    public void addLine(String text) {
        historyText.append(text);
        historyText.setCaretPosition(historyText.getDocument().getLength());
    }

    /**
     * get the history text area. used for testing
     * 
     * @return JTextArea
     */
    public JTextArea getText() {
        return historyText;
    }
}
