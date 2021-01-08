package Java_tictactoe;

/**
 * Button panel to hold the 3x3 array of buttons for the game.
 * 
 * @author Justin Whalley
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ButtonPanel extends JPanel{
    private JButton[][] buttons;
    private boolean buttonOff;
    private int alternate;
    private SoundPlayer sp;
    private String winner;
    private GameFrame game;
    private Window window;
    private HistoryFrame history;
    private int moves;
    private int startingPlayer;
    private boolean isTest;
    private ScoreboardPanel scoreboard;
    public ButtonPanel(GameFrame game, Window window, HistoryFrame history,ScoreboardPanel scoreboard, boolean isTest) {
        this.window = window;
        this.history = history;
        this.sp = new SoundPlayer();
        moves=0;
        buttons = new JButton[3][3];
        alternate = 0;
        buttonOff = false;
        this.isTest = isTest;
        winner = "";
        this.game = game;
        startingPlayer = 0;
        this.scoreboard = scoreboard;
        setLayout(new GridLayout(3,3));
        initializeButtons();
    }

    /**
     * initialize all buttons to text "", add the button functionality to it and add the buttons to the panel
     */
    public void initializeButtons() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setText("");
                buttons[i][j].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        if(!buttonOff) {
                            JButton buttonClicked = (JButton)e.getSource();
                            if(alternate%2==0 && buttonClicked.getText() =="") {
                                buttonClicked.setText("X");
                                game.updateLabel("O's turn");
                                alternate++;
                                moves++;
                                if(!isTest){
                                    sp.playSound("clicking.wav");
                                }
                            } else if(buttonClicked.getText()=="") {
                                buttonClicked.setText("O");
                                game.updateLabel("X's turn");
                                alternate++;
                                moves++;
                                if(!isTest){
                                    sp.playSound("clicking.wav");
                                }
                            }
                            if(checkForWin()) {
                                buttonOff = true;
                                int n=0;
                                if(!isTest){
                                    sp.playSound("end game.wav");
                                
                                    Object[] options = {"Yes","No"};
                                    n = JOptionPane.showOptionDialog(window,
                                            "Would you like to start a new game?",
                                            "New Game",
                                            JOptionPane.YES_NO_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            options,
                                            options[0]);
                                }
                                
                                if(winner.equals("X")) {
                                    game.updateLabel("X Won");
                                    scoreboard.incrementXWon();
                                    scoreboard.updateLabels();
                                    window.incrementXWon(moves);
                                } else if (winner.equals("O")) {
                                    game.updateLabel("O Won");
                                    scoreboard.incrementOWon();
                                    scoreboard.updateLabels();
                                    window.incrementOWon(moves);
                                } else {
                                    game.updateLabel("Tied");
                                    scoreboard.incrementTie();
                                    scoreboard.updateLabels();
                                    window.tie(moves);
                                }
                                if(winner.equals("X") || winner.equals("O")) {
                                    history.addLine("Player " + winner + " won in " + moves + " moves\n");
                                } else {
                                    history.addLine("Tied in " + moves + " moves\n");
                                }
                                if(n==0) {
                                    if(startingPlayer ==0) {
                                        reset(1);
                                    } else {
                                        reset(0);
                                    }
                                }
                                moves=0;
                            }
                        } else {
                            Object[] options = {"Yes", "No"};
                            int n = JOptionPane.showOptionDialog(window,
                                "You can't add anything to the board until new game is started\nWould you like to start a new game?",
                                "New Game",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                            if(n==0) {
                                if(startingPlayer == 0) {
                                    reset(1);
                                } else {
                                    reset(0);
                                }
                            }
                        }
                    }
                });
                add(buttons[i][j]);
            }
        }
    }

    /**
     * get the button array. used for testing
     * 
     * @return JButton[][]
     */
    public JButton[][] getButtons() {
        return buttons;
    }

    /**
     * check for win in either all buttons clicked(tie) or 3 in a row. set the winner variable to the winner for button action performed to read 
     * 
     * @return true if game won or false otherwise
     */
    public boolean checkForWin() {
        int clicked=0;
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(buttons[i][j].getText()!=""){
                    clicked++;
                }
            }
        }
        
        //check rows
        if (buttons[0][0].getText().equals(buttons[0][1].getText()) &&
        buttons[0][1].getText().equals(buttons[0][2].getText()) &&
        !buttons[0][0].getText().equals("")){
            winner = buttons[0][0].getText();
            return true;
        }

        else if ( buttons[1][0].getText().equals(buttons[1][1].getText()) &&
        buttons[1][1].getText().equals(buttons[1][2].getText()) &&
        !buttons[1][0].getText().equals("") ){
            winner = buttons[1][0].getText();
            return true;
        }

        else if ( buttons[2][0].getText().equals(buttons[2][1].getText()) &&
        buttons[2][1].getText().equals(buttons[2][2].getText()) &&
        !buttons[2][0].getText().equals("") ){
            winner = buttons[2][0].getText();
            return true;
        }

        //check column
        else if ( buttons[0][0].getText().equals(buttons[1][0].getText()) &&
        buttons[1][0].getText().equals(buttons[2][0].getText()) &&
        !buttons[0][0].getText().equals("") ){
            winner = buttons[0][0].getText();
            return true;
        }

        else if ( buttons[0][1].getText().equals(buttons[1][1].getText()) &&
        buttons[1][1].getText().equals(buttons[2][1].getText()) &&
        !buttons[0][1].getText().equals("") ){
            winner = buttons[0][1].getText();
            return true;
        }

        else if ( buttons[0][2].getText().equals(buttons[1][2].getText()) &&
        buttons[1][2].getText().equals(buttons[2][2].getText()) &&
        !buttons[0][2].getText().equals("") ){
            winner = buttons[0][2].getText();
            return true;
        }

        //check diagonal
        else if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
        buttons[0][0].getText().equals(buttons[2][2].getText()) &&
        !buttons[0][0].getText().equals("") ){
            winner = buttons[0][0].getText();
            return true;
        }

        else if ( buttons[0][2].getText().equals(buttons[1][1].getText()) &&
        buttons[0][2].getText().equals(buttons[2][0].getText()) &&
        !buttons[0][2].getText().equals("") ){
            winner = buttons[0][2].getText();
            return true;
        }

        //check tie
        if(clicked == 9){
            winner = "TIE";
            return true;
        }
        return false;

    }

    /**
     * restart the game with the starting player being the parametered player. 0 is x starts, 1 is o starts
     * 
     * @param int startingPlayer
     */
    public void reset(int startingPlayer) {
        buttonOff = false;
        for(int i =0; i<3;i++){
            for(int j=0; j<3;j++) {
                buttons[i][j].setText("");
            }
        }
        alternate = startingPlayer;
        if(alternate ==0) {
            game.updateLabel("X's turn");
        } else {
            game.updateLabel("O's turn");
        }
        this.startingPlayer = startingPlayer;
        moves=0;
    }

    /**
     * get alternate to see whos turn it is.used for testing
     * 
     * @return int
     */
    public int getAlternate() {
        return alternate;
    }
}
