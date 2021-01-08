package Java_tictactoe;

/**
 * MenuBar to hold new game and quit actions. Adds short cut keys to the items with crtl+q is quit and ctrl+n is new game
 * 
 * @author Justin Whalley
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
public class MenuBar extends JMenuBar {
    private JMenu menu;
    private JMenuItem quitItem;
    private JMenuItem newItem;
    public MenuBar(JFrame window, GameFrame game, HistoryFrame history) {
        menu = new JMenu("Options");
        add(menu);
        quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(quitItem);
        newItem = new JMenuItem("New");
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"X starts", "O starts", "Cancel"};
                int n = JOptionPane.showOptionDialog(window,
                    "Who should start?",
                    "New Game",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);
                if(n==0) {
                    game.reset(0);
                    history.addLine("Game cancelled\n");
                } else if (n==1){
                    game.reset(1);
                    history.addLine("Game cancelled\n");
                }
            }
        });
        menu.add(newItem);
        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, SHORTCUT_MASK));
    }

}
