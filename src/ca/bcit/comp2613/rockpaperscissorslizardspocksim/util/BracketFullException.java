package ca.bcit.comp2613.rockpaperscissorslizardspocksim.util;

/**
 * An exception to throw when the bracket is full
 * @author Tyler Wardle
 * @version July 30 2014
 *
 */
public class BracketFullException extends RuntimeException {
	
	/**
	 * over ridden toString method
	 * @return error message as a String
	 */
	public String toString(){
		return "Starting bracket is full! Delete a player before adding a new one.";
	}

}
