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
public class screentitle implements Runnable, KeyListener, WindowListener, MouseListener {
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

        } catch (IOException ex) {

            Logger.getLogger(driver.class.getName()).log(Level.SEVERE,null, ex);
        }
    }

    public screentitle() {

        // SIZE OF IMAGE
        dx4 = 450;//450
        dy4 = 600;//600
        dx5 = 650;//650
        dy5 = 890;//890
        dx2 = 100;
        dx3 = 200;
        dy2 = 300;

        dy9 = 500;


        loadImages();
        setChange(true);
        current = new Point(920, 940); // 920,940
        myRect = new Rectangle((int) current.getX(), (int) current.getY(), 700, 100); // x,y,h,w to move just change x and y
        BROWN = new Color(35, 63, 139);
        frame = new JFrame();
        frame.addKeyListener(this);
        frame.addWindowListener(this);
        frame.addMouseListener(this);
        frame.setSize(SIZE);
        frame.setTitle(TITLE);
        isRunning = true;
        isDone = false;
        frame.setVisible(true);
        frame.setLayout(null);
        imgBuffer = frame.createImage(SIZE.width, SIZE.height);
    }