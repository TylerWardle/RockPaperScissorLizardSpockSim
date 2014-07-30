package ca.bcit.comp2613.rockpaperscissorslizardspocksim.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;

/**
 * An Interface which will represent our database of Players 
 * @author Tyler Wardle
 * @version July 30 2014
 * 
 */
public interface PlayerRepository extends CrudRepository<Player, Long>{
	
	/**
	 * Finds a player by name from the repository
	 * @param name as a string
	 * @return player as a Player 
	 */
	Player findByName(String name);
}
