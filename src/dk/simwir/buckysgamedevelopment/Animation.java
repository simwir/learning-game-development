package dk.simwir.buckysgamedevelopment;

import java.util.ArrayList;

public class Animation {

	private ArrayList scenes;
	private int sceneIndex;
	private long movieTime, totalTime;
	
	private Animation(){
		scenes = new ArrayList();
		totalTime = 0;
		start();
	}
	
	//add scene to array list and set time for each scene
	public synchronized void addScene(Image image, long time){
		totalTime += time;
		scenes.add(new OneScene(image, totalTime));
	}
	
}
