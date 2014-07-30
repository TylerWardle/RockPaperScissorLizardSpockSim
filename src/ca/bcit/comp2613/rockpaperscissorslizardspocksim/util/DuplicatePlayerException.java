package ca.bcit.comp2613.rockpaperscissorslizardspocksim.util;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.PlayerEntity;

public class DuplicatePlayerException extends RuntimeException{
	private PlayerEntity player;
	
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
