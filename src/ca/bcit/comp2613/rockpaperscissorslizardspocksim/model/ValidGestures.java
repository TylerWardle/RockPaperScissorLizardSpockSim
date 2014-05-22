package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;
import java.util.ArrayList;

public class ValidGestures {
	private ArrayList<Gesture> gestures;

	/**
	 * @param gestures
	 */
	public ValidGestures(ArrayList<Gesture> gestures) {
		super();
		this.gestures = gestures;
	}

	public String toString(){
		return "";
	}
	/**
	 * @return the gestures
	 */
	public ArrayList<Gesture> getGestures() {
		return gestures;
	}

	/**
	 * @param gestures the gestures to set
	 */
	public void setGestures(ArrayList<Gesture> gestures) {
		this.gestures = gestures;
	}
	
	
}
