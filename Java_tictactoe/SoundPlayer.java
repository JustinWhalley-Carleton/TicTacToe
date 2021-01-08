package Java_tictactoe;

/**
 * Sound player to play a .wav file from assets folder
 * 
 * @author Justin Whalley
 */
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;

public class SoundPlayer {
    
    public SoundPlayer() {

    }

    /**
     * Play the sound file at path ./assets/soundName 
     * 
     * @param String soundName
     */
    public void playSound(String soundName) {
        try 
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/"+soundName).getAbsoluteFile( ));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start( );
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            System.out.print(ex);
            ex.printStackTrace( );
        }
    }
}
