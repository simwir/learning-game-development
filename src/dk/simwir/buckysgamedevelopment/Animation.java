package dk.simwir.buckysgamedevelopment;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {

	private ArrayList scenes;
	private int sceneIndex;
	private long movieTime, totalTime;
	
	public Animation(){
		scenes = new ArrayList();
		totalTime = 0;
		start();
	}
	
	

	//add scene to array list and set time for each scene
	public synchronized void addScene(Image image, long time){
		totalTime += time;
		scenes.add(new OneScene(image, totalTime));
	}
	
	//Start animation from the beginning
	public synchronized void start() {
		movieTime = 0;
		sceneIndex = 0;
	}
	
	//changes scenes
	public synchronized void update(long timePassed){
		if(scenes.size()>1){
			movieTime += timePassed;
			
			/*
			 * if the animation loop is done, but there is more time left
			 * the animation should be running, this resets it.
			 */
			if(movieTime >= totalTime){
				movieTime = 0;
				sceneIndex = 0;
			}
			while(movieTime > getScene(sceneIndex).endTime){
				sceneIndex++;
			}
		}
	}
	
	//get animation current image
	public synchronized Image getImage(){
		if(scenes.size()==0){
			return null;
		}else{
			return getScene(sceneIndex).pic;
		}
	}



	private OneScene getScene(int x) {
		return (OneScene) scenes.get(x);
	}
	
///////////// PRIVATE INNER CLASS//////////
	
	private class OneScene{
		Image pic;
		long endTime;
		
		public OneScene(Image pic, long endTime){
			this.pic = pic;
			this.endTime = endTime;
		}
	}
	
}
