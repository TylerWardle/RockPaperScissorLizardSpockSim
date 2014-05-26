package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;
import java.util.ArrayList;

/**
 * @author A00913377 Tyler Wardle
 *
 */
public class ValidGestures {
	
	private long id;
	private ArrayList<Gesture> gestures;

	/**
	 * @param gestures
	 */
	public ValidGestures(long id, ArrayList<Gesture> gestures) {
		super();
		this.id = id;
		this.gestures = gestures;
	}

	public String toString(){
		return "";
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
