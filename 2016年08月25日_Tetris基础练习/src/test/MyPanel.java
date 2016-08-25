package test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Shape;

public class MyPanel extends JPanel {
	public static Image bg;
	private Shape currentShape;
	static{
		try {
			bg = ImageIO.read(MyPanel.class.getResource("TETRIS.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void paint(Graphics g) {
		g.drawImage(bg,0, 0,null);
		currentShape.draw(g);
	}
	public void start() {
		currentShape = Shape.getShape();
		loopDown();		
	}
	private void loopDown() {
		Timer time = new Timer();
		time.schedule(new TimerTask() {
			public void run() {
				currentShape.down();
				MyPanel.this.repaint();
			}
		}, 500,500);
	}
}
