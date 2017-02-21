import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by 232512 on 2/14/2017.
 */
public class screentitle {
    public final String TITLE = "GALAGA";
    public final Dimension SIZE = new Dimension(1920, 1080);
    public JFrame frame;
    private boolean isRunning, isDone;
    private Image imgBuffer;
    private BufferedImage stone, grass, aashay, dirt, pig;
    private TexturePaint stoneOcta, grassOcta, dirty;
    private boolean change;
    @SuppressWarnings("unused")
    private Color BROWN;
    @SuppressWarnings("unused")
    private boolean AITurn, UserTurn;
    private Rectangle myRect;
    private Point current;
    private int dx4, dx5, dy4, dy5, dx2, dx3, dy2, dy9;
    private boolean blank;

    public void setChange(boolean change) {
        this.change = change;
    }

    private void loadImages() {

        try {
            stone = ImageIO.read(this.getClass().getResource("galaga title slide (1).jpg"));
            stoneOcta = new TexturePaint(stone, new Rectangle(0, 0, 1920, 1080));
            dirt = ImageIO.read(this.getClass().getResource("galaga title slide (1).jpg"));
            dirty = new TexturePaint(dirt, new Rectangle(0, 0, 1920, 1080));
        } catch (IOException ex) {

            Logger.getLogger(driver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public screentitle() {

        loadImages();
        ;
        setChange(true);
        frame = new JFrame();
        frame.setSize(SIZE);
        frame.setTitle(TITLE);
        isRunning = true;
        isDone = false;
        frame.setVisible(true);
        frame.setLayout(null);
        imgBuffer = frame.createImage(SIZE.width, SIZE.height);
    }

    private void draw() {

        // TODO Auto-generated method stub
        Graphics2D g2d = (Graphics2D) imgBuffer.getGraphics();
        g2d.setPaint(dirty);
        g2d.fillRect(0, 0, SIZE.width, SIZE.height);
        g2d.setPaint(stoneOcta);
        g2d.fillRect((int) myRect.getX(), (int) myRect.getY(), (int) myRect.getWidth(), (int) myRect.getHeight());
        //player
        if (isRunning)
            g2d = (Graphics2D) frame.getGraphics();
        g2d.drawImage(imgBuffer, 0, 0, SIZE.width, SIZE.height, 0, 0, SIZE.width, SIZE.height, null);

        g2d.dispose();
    }
}