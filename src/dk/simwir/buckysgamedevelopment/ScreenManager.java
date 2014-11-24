package dk.simwir.buckysgamedevelopment;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class ScreenManager {

	private GraphicsDevice vc;

	// give vc access to the monitor
	public ScreenManager() {
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = e.getDefaultScreenDevice();
	}

	// get all compatible DM's
	public DisplayMode[] getCompatibleDisplayModes() {
		return vc.getDisplayModes();
	}

	// compares DM's passed in the vc and see if they match
	public DisplayMode findFirstCompatibleMode(DisplayMode modes[]) {
		DisplayMode goodmodes[] = vc.getDisplayModes();
		for (int x = 0; x < modes.length; x++) {
			for (int y = 0; y < goodmodes.length; y++) {
				if (displayModesMatch(modes[x], goodmodes[y])) {
					return modes[x];
				}
			}
		}
		return null;
	}

	// get current DM
	public DisplayMode getCurrentDisplayMode() {
		return vc.getDisplayMode();
	}

	// Checks if 2 display modes match each other
	private boolean displayModesMatch(DisplayMode m1, DisplayMode m2) {
		if (m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()) {
			return false;
		}
		if (m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
				&& m1.getBitDepth() != m2.getBitDepth()) {
			return false;
		}
		if (m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
				&& m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
				&& m1.getRefreshRate() != m2.getRefreshRate()) {
			return false;
		}

		return true;
	}

	// make frame fullscreen
	public void setFullScreen(DisplayMode dm) {
		JFrame f = new JFrame();
		f.setUndecorated(true);
		f.setIgnoreRepaint(true);
		f.setResizable(false);
		vc.setFullScreenWindow(f);

		if (dm != null && vc.isDisplayChangeSupported()) {
			try {
				vc.setDisplayMode(dm);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		f.createBufferStrategy(2);

	}
	
	//We will set Graphis object = to this
	public Graphics2D getGraphics(){
		Window w = vc.getFullScreenWindow();
		if(w != null){
			BufferStrategy s = w.getBufferStrategy();
			return (Graphics2D) s.getDrawGraphics();
		}else{
			return null;
		}
	}
	
	
}
