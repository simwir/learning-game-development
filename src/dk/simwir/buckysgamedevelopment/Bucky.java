package dk.simwir.buckysgamedevelopment;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Bucky extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3083912055609240770L;

	public static void main(String[] args) {

		DisplayMode dispayMode = new DisplayMode(800, 600, 16,
				DisplayMode.REFRESH_RATE_UNKNOWN);
		Bucky b = new Bucky();
		b.run(dispayMode);
	}

	private Screen screen;
	private Image backGround;
	private Animation animation;

	// loads pictures from computer to jave and adds them to a scene
	public void loadPics() {
		backGround = new ImageIcon(
				"C:\\Users\\Simon\\Documents\\java\\Buckys game development\\resources\\back.jpg")
				.getImage();
		Image face1 = new ImageIcon(
				"C:\\Users\\Simon\\Documents\\java\\Buckys game development\\resources\\face.png")
				.getImage();
		Image face2 = new ImageIcon(
				"C:\\Users\\Simon\\Documents\\java\\Buckys game development\\resources\\face2.png")
				.getImage();
		animation = new Animation();
		animation.addScene(face1, 250);
		animation.addScene(face2, 250);
	}

	// main engine run
	public void run(DisplayMode dm) {
		screen = new Screen();
		try {
			screen.setFullScreen(dm, new JFrame());
			loadPics();
			movieLoop();
		} finally {
			screen.restoreScreen();
		}
	}

	// main movie loop
	public void movieLoop() {
		long startingTime = System.currentTimeMillis();
		long cumTime = startingTime;

		while (cumTime - startingTime < 5000) {
			long timePassed = System.currentTimeMillis() - cumTime;
			cumTime += timePassed;
			animation.update(timePassed);

			Graphics g = screen.getFullScreenWindow().getGraphics();
			draw(g);
			g.dispose();

			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// draw method
	public void draw(Graphics g) {
		g.drawImage(backGround, 0, 0, null);
		g.drawImage(animation.getImage(), 0, 0, null);
	}

}
