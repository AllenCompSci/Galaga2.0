import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Created by 239803 on 1/19/2017.
 */
public class driver {
    public static void main(String [] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        graphics testCase = new graphics();
        (new Thread(testCase)).start();
        screentitle testCase2 = new screentitle();
        audioInputStream testCase3 = new audioInputStream();



        //(new String(testCase2)).start();

    }

}
