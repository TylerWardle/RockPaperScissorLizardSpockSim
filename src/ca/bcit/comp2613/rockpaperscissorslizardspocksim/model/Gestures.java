/**
 * 
 */
package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;
import java.util.Random;

/**
 * @author Tyler
 *
 */
public enum Gestures {
	ROCK("Rock", 1), PAPER("Paper", 2), SCISSORS("Scissors", 3), LIZARD("Lizard",4), SPOCK("Spock", 5);

	private String description;
	private int gestureNumber;
	Gestures(String description, int gestureNumber) {
		this.description = description;
		this.gestureNumber = gestureNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getGestureNumber() {
		return gestureNumber;
	}
	public void setGestureNumber(int gestureNumber) {
		this.gestureNumber = gestureNumber;
	}
	public static Gestures getRandomGesture(){
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
