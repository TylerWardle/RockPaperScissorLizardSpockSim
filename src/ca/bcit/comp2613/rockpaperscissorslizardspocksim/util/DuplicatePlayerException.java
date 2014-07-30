package ca.bcit.comp2613.rockpaperscissorslizardspocksim.util;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.PlayerEntity;

/**
 * An exception to throw when an attempt is made to recreate a pre-existing player
 * @author Tyler Wardle 
 * @version July 30 2014
 *
 */
public class DuplicatePlayerException extends RuntimeException{
	private PlayerEntity player;
	
	/**
	 * default constructor
	 * @param player as a PlayerEntity
	 */
	public DuplicatePlayerException(PlayerEntity player){
		this.player = player;
	}
	
	/**
	 * over ridden toString method
	 * @return error message as a String
	 */
	public String toString(){
		return player.getName() + " already exists";
	}

}
