package test;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class test {
	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		JFrame frame = new JFrame();	
		MyPanel panel = new MyPanel();
		panel.start();
		frame.add(panel);
		frame.setBounds(100,100,530,550);
		frame.setUndecorated(true);//È¥µô±ß¿ò
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
