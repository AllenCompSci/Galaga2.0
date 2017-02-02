/**
 * Created by 239803 on 1/19/2017.
 */
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//package dap;

/**
 * Created by taylor hudson on 1/5/2017.
 */
public class graphics implements Runnable, KeyListener, WindowListener, MouseListener {
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
        private int dx4, dx5, dy4, dy5;

    public void setChange(boolean change) {
        this.change = change;
    }

    private void loadImages() {

        try {


            stone = ImageIO.read(this.getClass().getResource("space.jpg"));
            aashay = ImageIO.read(this.getClass().getResource("aashay.png"));
            grass = ImageIO.read(this.getClass().getResource("grass.png"));
            pig = ImageIO.read(this.getClass().getResource("16.png"));
            dirt = ImageIO.read(this.getClass().getResource("space.jpg"));
            grassOcta = new TexturePaint(grass, new Rectangle(0, 0, 90, 60));
            stoneOcta = new TexturePaint(stone, new Rectangle(0, 0, 1920, 1080));
            dirty = new TexturePaint(dirt, new Rectangle(0, 0, 1920, 1080));


        } catch (IOException ex) {

            Logger.getLogger(driver.class.getName()).log(Level.SEVERE,null, ex);
        }
    }

    public graphics(){

        dx4 = 450;
        dy4 = 600;
        dx5 = 650;
        dy5 = 890;
        loadImages();
        setChange(true);
        current = new Point(920,940);
        myRect = new Rectangle((int)current.getX(), (int)current.getY(), 700, 200); // x,y,h,w to move just change x and y
        BROWN = new Color(139,69,19);
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int Key;
        Key = e.getKeyCode();


        if(Key == KeyEvent.VK_UP){ // UP

        dy4 -= 10;
        dy5 -= 10;


        }
        else if(Key == KeyEvent.VK_LEFT){

        dx4 -= 10;
        dx5 -= 10;


        }
        else if(Key == KeyEvent.VK_DOWN){ // DOWN


         dy4 += 10;
         dy5 += 10;

        }
        else if(Key == KeyEvent.VK_RIGHT){


        dx4 += 10;
        dx5 += 10;


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        frame.setVisible(false);
        frame.dispose();
        isRunning = false;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        while(true){

            if(isDone){
                System.exit(0);
            }
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void run() {
        while(isRunning){

            draw();

            if(change){
                setChange(false);

            }
            try{Thread.sleep(50);}
            catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
        isDone = true;
    }

    private void draw() {

        // TODO Auto-generated method stub
        Graphics2D g2d = (Graphics2D) imgBuffer.getGraphics();
        g2d.setPaint(dirty);
        g2d.fillRect(0, 0, SIZE.width, SIZE.height);
        g2d.setPaint(stoneOcta);
        g2d.fillRect((int)myRect.getX(), (int)myRect.getY(), (int)myRect.getWidth(), (int)myRect.getHeight());
        g2d.setColor(Color.PINK);
        Stroke old = g2d.getStroke();
        g2d.setStroke(new BasicStroke(3));
        g2d.drawImage(aashay,dx4,dy4,dx5,dy5, 0, 0, 650, 1033, null );

        g2d.setStroke(old);
        if(isRunning)
            g2d = (Graphics2D) frame.getGraphics();
        g2d.drawImage(imgBuffer, 0,  0, SIZE.width, SIZE.height, 0, 0, SIZE.width, SIZE.height, null);

        g2d.dispose();
    }
}


