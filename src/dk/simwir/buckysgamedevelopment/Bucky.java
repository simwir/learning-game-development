package dk.simwir.buckysgamedevelopment;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
/*
 * Video 7
 */
public class Bucky extends JFrame{

	public static void main(String[] args) {
		
		DisplayMode dm = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		Bucky b = new Bucky();
		b.run(dm);
	}

	public void run(DisplayMode dm) {
		setBackground(Color.BLUE);
		setForeground(Color.WHITE);
		setFont(new Font("Arial", Font.PLAIN, 24));
		
		Screen s = new Screen();
		
		try{
			s.setFullScreen(dm, this);
			try {
				Thread.sleep(3000);
			} catch (Exception e) {}
		}finally{
			s.restoreScreen();
		}
	}
	
	public void paint(Graphics g){
		if(g instanceof Graphics2D){
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
		}
		g.drawString("Test", 200, 200);
	}

}
