package graphics;
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

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	private BufferedImage stone, enemy, aashay, dirt, pig, enemt2;
	private TexturePaint stoneOcta, grassOcta, dirty;
	private boolean change;
	@SuppressWarnings("unused")
	private Color BROWN;
	@SuppressWarnings("unused")
	private boolean AITurn, UserTurn;
	private Rectangle myRect;
	private Point current;
	private int dx4, dx5, dy4, dy5, dx2, dx3, dy2, dy9, dt, dtx, dty, dx6, dx7, dy6, dy7, dtx1, dty1, dx45, dx46, dy45, dy46, dy47, dy48, dx47, dx48, dty2;
	private boolean blank;




	public void setChange(boolean change) {
		this.change = change;
	}

	private void loadImages() {

		try {

			enemt2 = ImageIO.read(this.getClass().getResource("imgres.jpg"));
			stone = ImageIO.read(this.getClass().getResource("space.jpg"));
			aashay = ImageIO.read(this.getClass().getResource("aashay.png"));
			enemy = ImageIO.read(this.getClass().getResource("trump.jpg"));
			pig = ImageIO.read(this.getClass().getResource("16.png"));
			dirt = ImageIO.read(this.getClass().getResource("space.jpg"));
			//grassOcta = new TexturePaint(grass, new Rectangle(0, 0, 1000, 60));
			stoneOcta = new TexturePaint(stone, new Rectangle(0, 0, 1920, 1080));
			dirty = new TexturePaint(dirt, new Rectangle(0, 0, 1920, 1080));


		} catch (IOException ex) {

			Logger.getLogger(driver.class.getName()).log(Level.SEVERE,null, ex);
		}
	}

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
		dy9 = 600;

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




		loadImages();
		setChange(true);
		current = new Point(920,940); // 920,940
		myRect = new Rectangle((int)current.getX(), (int)current.getY(), 700, 100); // x,y,h,w to move just change x and y
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

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int Key;
		Key = e.getKeyCode();



        /*
        if(Key == KeyEvent.VK_UP){ // UP
        dy4 -= 10;
        dy5 -= 10;
        }
        */
		if(Key == KeyEvent.VK_LEFT){ //moving speed left

			dx4 -= 50;
			dx5 -= 50;


		}
		else if(Key == KeyEvent.VK_SPACE){//space bar

			//dx4 -= 10;
			//dx5 -= 10;
			//createPlayer();
			//g2d.fillOval(0, 0, SIZE.width, SIZE.height);


		}
        /*
        else if(Key == KeyEvent.VK_DOWN){ // DOWN
         dy4 += 10;
         dy5 += 10;
        }
        */
		else if(Key == KeyEvent.VK_RIGHT){// moving speed right


			dx4 += 50;
			dx5 += 50;


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
			//enemy
			draw();
			//Enemy 1 mvmt
			if (dx3 <= 1520){
				dx3 += dt;
				dx2 += dt;
			}
			if (dx3 == 1520 || dx2 == 400){
				dt *= -1;
			}

			//Enemy 2 mvmt
			if (dy7 <= 350){
				dy6 += dty;
				dy7 += dty;
			}

			if (dy7 == 350 || dy6 == 75){
				dty *= -1;
			}

			if (dx7 <= 400){
				dx6 += dtx;
				dx7 += dtx;
			}
			if (dx7 == 400 || dx6 == 0){
				dtx *= -1;
			}

			//Enemy 3 mvmt
			if (dy46 <= 350){
				dy45 += dty1;
				dy46 += dty1;
			}

			if (dy46 == 350 || dy45 == 75){
				dty1 *= -1;
			}

			if (dx46 <= 1920){
				dx45 += dtx1;
				dx46 += dtx1;
			}
			if (dx46 == 1920 || dx45 == 1520){
				dtx1 *= -1;
			}

			//Enemy 4 mvmt
			if (dy48 <= 1080){
				dy47 += dty2;
				dy48 += dty2;
			}
			if (dy48 >= 1040 || dy47 <= 30){
				dty2 *= -1;
			}

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


	//oscillaiting movement of enemy

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
		//player
		g2d.drawImage(aashay,dx4,dy4,dx5,dy5, 0, 0, 650, 1033, null );//Player
		g2d.drawImage(enemy,dx2, dy2, dx3, dy9,0,0,1024,1365,null); // Enemy1
		g2d.drawImage(enemt2, dx6, dy6, dx7, dy7, 0, 0, 900, 808, null ); // Enemy2
		g2d.drawImage(enemt2, dx45, dy45, dx46, dy46, 0, 0, 900, 808, null); // Enemy3
		g2d.drawImage(enemt2, dx47, dy47, dx48, dy48, 0, 0, 900, 808, null); //Enemy4
		System.out.println( "ENEMY 4 : " + dy47 + " : " + dy48 );
		g2d.setStroke(old);
		if(isRunning)
			g2d = (Graphics2D) frame.getGraphics();
		g2d.drawImage(imgBuffer, 0,  0, SIZE.width, SIZE.height, 0, 0, SIZE.width, SIZE.height, null);

		g2d.dispose();
	}

	private void createPlayer(){

	}

}
