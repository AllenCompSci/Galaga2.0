import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class audioInputStream {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // TODO Auto-generated method stub
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                new File("starwars.mp3"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
        clip.start();
    }

}
