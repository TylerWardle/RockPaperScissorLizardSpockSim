/**
 * 
 */
package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Tyler Wardle
 * @version July 21, 2014
 * Gestures is an enum class which generates objects representing valid RPSLS gestures 
 */
@Entity
public enum Gestures {
	ROCK(1,"Rock", new ArrayList<Integer>(Arrays.asList(2,4))), 
	PAPER( 2, "Paper",new ArrayList<Integer>(Arrays.asList(3,5))),
	SCISSORS( 3,"Scissors", new ArrayList<Integer>(Arrays.asList(1,4))),
	SPOCK(4,"Spock",  new ArrayList<Integer>(Arrays.asList(2,5))),
	LIZARD(5,"Lizard", new ArrayList<Integer>(Arrays.asList(1,3)));

	@Id
	private int gestureNumber;
	private String description;
	private ArrayList<Integer> defeatingGestures;	
		
	Gestures(int gestureNumber, String description, ArrayList<Integer> defeatingGestures) {
		this.description = description;
		this.gestureNumber = gestureNumber;		
		this.defeatingGestures = defeatingGestures;
	}
	/**
	 * get description
	 * @return description as a String
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * set description
	 * @param description as a String
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * get gesture number
	 * @return gestureNumber as an int
	 */
	public int getGestureNumber() {
		return gestureNumber;
	}
	/**
	 * set gesture number
	 * @param gestureNumber as an int
	 */
	public void setGestureNumber(int gestureNumber) {
		this.gestureNumber = gestureNumber;
	}
	/**
	 * get a random valid gesture
	 * @return gesture as a Gestures
	 */
	public static Gestures getRandomGesture(){
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
	/**
	 *  Returns an array list of gestures which defeat the gesture called upon
	 * @return defeatingGestures as an ArrayList<Gestures>
	 */
	public ArrayList<Gestures> getDefeatingGestures(){
		ArrayList<Gestures> gestures = new ArrayList<Gestures>();
		for (int gesture: defeatingGestures){
			gestures.add(getGestureById(gesture));
		}		
		return gestures;		
	}	
	
	/**
	 * Finds a gesture by id
	 * @param id as an int
	 * @return gesture as a Gestures
	 */
	public static Gestures getGestureById(int id){
		for(Gestures gesture: Gestures.values()){
			if( gesture.getGestureNumber() == id){
				return gesture;
			}			
		}
		return null;		 
	}
}

