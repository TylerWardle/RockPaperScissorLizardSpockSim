package ca.bcit.comp2613.a00913377.util;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
/**
 * 
 * @author A00913377 Tyler Wardle
 *
 */
public class TeamWithSelfException extends RuntimeException{

	private Player playerOne;
	
	/**
	 * default constructor
	 * @param playerOne
	 */
	public TeamWithSelfException(Player playerOne){
		this.playerOne = playerOne;		
	}
	
	/**
	 * over ridden toString method
	 * @return error message as a String
	 */
	public String toString(){
		return playerOne.getName() + " cannot form a team with themself";
	}
}
