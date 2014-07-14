/**
 * 
 */
package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;
import java.util.Random;
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
 * @author A00913377 Tyler Wardle
 * Gestures is an enum class which generates objects representing valid RPSLS gestures 
 */
@Entity
public enum Gestures {
	ROCK("Rock", 1), PAPER("Paper", 2), SCISSORS("Scissors", 3), LIZARD("Lizard",4), SPOCK("Spock", 5);

	@Id
	private int gestureNumber;
	private String description;
		
	Gestures(String description, int gestureNumber) {
		this.description = description;
		this.gestureNumber = gestureNumber;
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
}
