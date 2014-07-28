package ca.bcit.comp2613.rockpaperscissorslizardspocksim.util;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;

public class DuplicatePlayerException extends RuntimeException{
	private Player player;
	
	public DuplicatePlayerException(Player player){
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
