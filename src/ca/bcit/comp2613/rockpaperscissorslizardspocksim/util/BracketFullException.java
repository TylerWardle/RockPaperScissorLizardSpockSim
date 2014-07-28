package ca.bcit.comp2613.rockpaperscissorslizardspocksim.util;

public class BracketFullException extends RuntimeException {
	
	/**
	 * over ridden toString method
	 * @return error message as a String
	 */
	public String toString(){
		return "Starting bracket is full! Delete a player before adding a new one.";
	}

}
