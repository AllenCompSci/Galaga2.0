/**
 * Created by 239803 on 1/19/2017.
 */
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import static java.lang.Thread.sleep;


/**
 * Created by taylor hudson on 1/5/2017.
 * Edited by Omar Khan, Josh Hughes, Indhar Gopalakrishnan, and Anuj Gupta
 */
public class graphics implements Runnable, KeyListener, WindowListener, MouseListener {
        public final String TITLE = "GALAGA 2.0";
        public final Dimension SIZE = new Dimension(1920, 1080);
        public JFrame frame;
        private boolean isRunning, isDone, isPlaying;
        private Image imgBuffer;
        private BufferedImage stone, grass, aashay, dirt, titleScreen,BlackScreen;
        private TexturePaint stoneOcta, dirty, blacKEndScreen, SCREEN;
        private boolean change;
        @SuppressWarnings("unused")
        private Color BROWN;
        @SuppressWarnings("unused")
        private boolean AITurn, UserTurn;
        private Rectangle myRect;
        private Point current;
        private static int dx17, dx18, dx19, dx28, dy17, dy18, dy19, dy28, dx4, dx5, dy4, dy5, dx2, dx3, dy2, dy9, dt,dtx, dty, dx6, dx7, dy6, dy7, dtx1, dty1, dx45, dx46, dy45, dy46, dy47, dy48, dx47, dx48, dty2, dy20, dy90, dx1010; //the multitudes of integers
        private int bx, by; // bullet integers


    public void setChange(boolean change) {
        this.change = change;
    }

    private void loadImages() {

        try {


            aashay = ImageIO.read(this.getClass().getResource("spaceship.png")); // player image
            grass = ImageIO.read(this.getClass().getResource("enemyaf.png")); // enemy image
            titleScreen = ImageIO.read(this.getClass().getResource("titleScreen.png")); // titleScreen
            dirt = ImageIO.read(this.getClass().getResource("space.jpg")); // backtground image
            BlackScreen = ImageIO.read(this.getClass().getResource("blacksquare.png")); // black End Screen



            dirty = new TexturePaint(dirt, new Rectangle(0, 0, 1920, 1080)); //Space Texture Paint
            blacKEndScreen = new TexturePaint(BlackScreen, new Rectangle(0,0,1200, 1080)); //Black End Screen Texture Paint
            SCREEN = new TexturePaint(titleScreen, new Rectangle(0,0,1920,1080)); //TitleScreen Texture Paint

        } catch (IOException ex) {

            Logger.getLogger(driver.class.getName()).log(Level.SEVERE,null, ex);
        }
    }

    boolean readyTofire, shot = false; //bullet bolleans
    Rectangle bullet; //bullet object


    public graphics(){

        //positioning
        dx4 = 900;//PLayer
        dy4 = 900;
        dx5 = 1060;
        dy5 = 1030;

        dx2 = 400;//Enemy 1
        dx3 = 500;
        dy2 = 400;
        dt = 20;
        dy9 = 550;

        dtx = 10;//Enemy 2
        dty = -10;
        dx6 = 0;
        dy6 = 225;
        dx7 = 100;
        dy7 = 350;

        dtx1 = -10;//Enemy 3
        dty1 = -10;
        dx45 = 1820;
        dx46 = 1920;
        dy45 = 225;
        dy46 = 350;

        dty2 = 10;//Enemy 4
        dx47 = 910;
        dx48 = 1010;
        dy47 = 225;
        dy48 = 350;


        dx17 = 1080;//Enemy5
        dx18 = 1080;
        dy17 = 1080;
        dy18 = 1080;

        dx19 = 1080;//Enemy6
        dx28 = 1080;
        dy19 = 1080;
        dy28 = 1080;
        dy28 = 1080;


        dx1010 = 1920; //TIME BAR

        //Stuff? Stuff to make code work (bolleans, keypress add, etc.

        loadImages();
        setChange(true);
        current = new Point(920,940);
        myRect = new Rectangle((int)current.getX(), (int)current.getY(), 700, 100);
        BROWN = new Color(35, 63, 139);
        frame = new JFrame();
        frame.addKeyListener(this);
        frame.addWindowListener(this);
        frame.addMouseListener(this);
        frame.setSize(SIZE);
        frame.setTitle(TITLE);
        isRunning = true;
        isPlaying = false;
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

        if (Key == KeyEvent.VK_LEFT) { //moving speed left

            dx4 -= 50;
            dx5 -= 50;

        } else if (Key == KeyEvent.VK_RIGHT) {// moving speed right

            dx4 += 50;
            dx5 += 50;

        } else if (Key == KeyEvent.VK_SPACE) { //bullet fire

            if(isPlaying) {

                if (bullet == null) {
                    readyTofire = true;

                }
                if (readyTofire == true) {
                    by = dy4 - 20;
                    bx = dx4 + 50;
                    bullet = new Rectangle(bx, by, 40, 40);
                    shot = true;
                }
                enemy2detech(); //enemy detection
                enemy4detech();
                enemy1detech();
                enemy3detech();
                enemy6detech();
                enemy5detech();

            }
        }
        else if (Key == KeyEvent.VK_W){  //bullet move up
            bullet.y -= 125;
        }

        else if (Key == KeyEvent.VK_A){  //bullet move left
            bullet.x -= 100;

            if(dx17 < 1920) {
                Random rand = new Random();  //enemy5 movement
                dx17 = rand.nextInt(1820) + 1;
                dy17 = rand.nextInt(880) + 60;
                dx18 = dx17 + 100;
                dy18 = dy17 + 125;
            }
        }

        else if (Key == KeyEvent.VK_D){  //bullet move right
            bullet.x += 100;

            if (dx19 < 1921) {
                Random rund = new Random();  //enemy6 movement
                dx19 = rund.nextInt(1820) + 1;
                dy19 = rund.nextInt(880) + 60;
                dx28 = dx19 + 100;
                dy28 = dy19 + 125;
            }
        }
        else if (Key == KeyEvent.VK_X){  //Start Game Key
            isPlaying = true;
        }
    }

    public void shoot () {   //function to keep bulley moving
        if(shot) {
            bullet.y -= 90;
            try{
                Thread.sleep(40);
            }
            catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public void enemy1detech(){  //enemy 1 collision with bullet? NOTE BULLET MUST BE IN CENTER (?)
        for (int i = dx2; i <= dx3; i++){
            for (int j = dy2; j <= dy9; j++){
                if ((j >= bullet.y - 75 && j <= bullet.y + 75) && (i >=  bullet.x - 20 && i <= bullet.x + 20)){
                    dx2 = 1920;
                    dx3 = 1920;
                    dy2 = 1080;
                    dy9 = 1080;
                }

            }
        }
    }

    public void enemy2detech() {  //enemy 2 collision with bullet?
        for (int q = dx6; q <= dx7; q++) {
            for (int r = dy6; r <= dy7; r++) {
                if ((r >= bullet.y - 62 && r <= bullet.y + 62) && (q >=  bullet.x - 20 && q <= bullet.x + 20)){
                    dx6 = 1920;
                    dx7 = 1920;
                    dy6 = 1080;
                    dy7 = 1080;
                }

            }
        }
    }

    public void enemy3detech() {  //enemy 3 collision with bullet?
        for (int t = dx45; t <= dx46; t++) {
            for (int u = dy45; u <= dy46; u++) {
                if ((u >= bullet.y - 62 && u <= bullet.y + 62) && (t >=  bullet.x - 20 && t <= bullet.x + 20)){

                    dx45 = 1920;
                    dx46 = 1920;
                    dy45 = 1080;
                    dy46 = 1080;
                }


            }
        }
    }

    public void enemy4detech(){  //enemy 4 collision with bullet?
        for (int e = dx47; e <= dx48; e++){
            for (int f = dy47; f <= dy48; f++){
                if ((f >= bullet.y - 62 && f <= bullet.y + 62) && (e >=  bullet.x - 20 && e <= bullet.x + 20)){
                    dx47 = 1920;
                    dx48 = 1920;
                    dy47 = 1080;
                    dy48 = 1070;
                }

            }
        }
    }
      public void enemy5detech() {
        for (int v = dx17; v <= dx18; v++) {
            for (int w = dy17; w <= dy18; w++) {
                if ((w >= bullet.y - 62 && w <= bullet.y + 62) && (v >= bullet.x - 20 && v <= bullet.x + 20)) {
                    dx17 = 1920;
                    dx18 = 1920;
                    dy17 = 1080;
                    dy18 = 1080;
                }
            }
        }
    }


    public void enemy6detech() {
        for (int g = dx19; g <= dx28; g++) {
            for (int h = dy19; h <= dy28; h++) {
                if ((h >= bullet.y - 62 && h <= bullet.y + 62) && (g >= bullet.x - 20 && g <= bullet.x + 20)) {
                    dx19 = 1920;
                    dx28 = 1920;
                    dy19 = 1080;
                    dy28 = 1080;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == e.VK_SPACE){  //reload (press space after bulley leaves screen)
            readyTofire = false;
            if (bullet.y <= 0){
                bullet = new Rectangle(0,0,0,0);
                shot = false;
                readyTofire = true;
            }
        }

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
                sleep(100);
            }
            catch(InterruptedException ie) {
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
        while (isRunning) {
            draw();
            shoot();

            if (isPlaying) //Time Bar decrease
            dx1010 -= 30;

            //Enemy 1 mvmt
            if (dx3 <= 1520) {
                dx3 += dt;
                dx2 += dt;
            }
            if (dx3 == 1520 || dx2 == 400) {
                dt *= -1;
            }
            //Enemy 2 mvmt
            if (dy7 <= 350) {
                dy6 += dty;
                dy7 += dty;
            }

            if (dy7 == 350 || dy6 == 75) {
                dty *= -1;
            }

            if (dx7 <= 400) {
                dx6 += dtx;
                dx7 += dtx;
            }
            if (dx7 == 400 || dx6 == 0) {
                dtx *= -1;
            }
            //Enemy 3 mvmt
            if (dy46 <= 350) {
                dy45 += dty1;
                dy46 += dty1;
            }

            if (dy46 == 350 || dy45 == 75) {
                dty1 *= -1;
            }

            if (dx46 <= 1920) {
                dx45 += dtx1;
                dx46 += dtx1;
            }
            if (dx46 == 1920 || dx45 == 1520) {
                dtx1 *= -1;
            }
            //Enemy 4 mvmt
            if (dy48 <= 1080) {
                dy47 += dty2;
                dy48 += dty2;
            }
            if (dy48 >= 1040 || dy47 <= 30) {
                dty2 *= -1;
            }

            dy20 -= 10;
            dy90 -= 10;

            if (change) {
                setChange(false);

            }
            try {
                sleep(50);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
         }

            isDone = true;

    }

    private void draw() {

        // TODO Auto-generated method stub
        Graphics2D g2d = (Graphics2D) imgBuffer.getGraphics();

        if (!isPlaying) {
            //draw Title Screen
            g2d.setPaint(SCREEN);
            g2d.fillRect(0, 0,SIZE.width, SIZE.height);
            if (isRunning)
                g2d = (Graphics2D) frame.getGraphics();
            g2d.drawImage(imgBuffer, 0, 0, SIZE.width, SIZE.height, 0, 0, SIZE.width, SIZE.height, null);
        }
            //Draw Game
        if (isPlaying) {
            isRunning = true;
            g2d.setPaint(dirty);
            g2d.fillRect(0, 0, SIZE.width, SIZE.height);
            g2d.setPaint(stoneOcta);
            g2d.fillRect((int) myRect.getX(), (int) myRect.getY(), (int) myRect.getWidth(), (int) myRect.getHeight());
            g2d.setColor(Color.PINK);
            Stroke old = g2d.getStroke();
            g2d.setStroke(new BasicStroke(3));


            g2d.setColor(Color.GREEN);
            g2d.fillRect(0, 0, dx1010, 50);
            g2d.drawImage(aashay, dx4, dy4, dx5, dy5, 0, 0, 1200, 1200, null);// player
            if(dy2 < 1920)
            g2d.drawImage(grass, dx2, dy2, dx3, dy9, 0, 0, 211, 237, null);// enemy 1 image
            if(dy6 < 1920)
            g2d.drawImage(grass, dx6, dy6, dx7, dy7, 0, 0, 211, 237, null); // Enemy2 image
            if(dy45 < 1920)
            g2d.drawImage(grass, dx45, dy45, dx46, dy46, 0, 0, 211, 237, null); // Enemy3 image
            if(dy47 < 1920)
            g2d.drawImage(grass, dx47, dy47, dx48, dy48, 0, 0, 211, 237, null); //Enemy4 image
            if(dy17 < 1920)
            g2d.drawImage(grass, dx17, dy17, dx18, dy18, 0, 0, 211, 237, null); // Enemy5 image
            if(dy19 < 1920)
            g2d.drawImage(grass, dx19, dy19, dx28, dy28, 0, 0, 211, 237, null); //Enemy6 image

            g2d.setStroke(old);

            if (isRunning)
                g2d = (Graphics2D) frame.getGraphics();
            g2d.drawImage(imgBuffer, 0, 0, SIZE.width, SIZE.height, 0, 0, SIZE.width, SIZE.height, null);


            if (shot) { //bulley
                g2d.setColor(Color.RED);
                g2d.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
            }


            // win condition
            if (dy2 == dy7 && dy7 == dy45 && dy45 == dy48 && dy48 == dy17 && dy17 == dy19 && dy19 == dy2){
                g2d.setPaint(blacKEndScreen);
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, 1920, 1080);
                Font myFont = new Font("Courier New", 1, 500);
                g2d.setFont(myFont);
                g2d.setColor(Color.GREEN);
                g2d.drawString("You", 350, 400);
                g2d.drawString("Win!", 350, 900);
                isRunning = false;
            }


            //lose condition
            if (dx1010 <= 0 && isRunning && isPlaying) {
                g2d.setPaint(blacKEndScreen);
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, 1920, 1080);
                Font myFont = new Font("Courier New", 1, 500);
                g2d.setFont(myFont);
                g2d.setColor(Color.RED);
                g2d.drawString("Game", 350, 400);
                g2d.drawString("Over!", 350, 900);
                isRunning = false;


            }
        }
        g2d.dispose();
    }



}


