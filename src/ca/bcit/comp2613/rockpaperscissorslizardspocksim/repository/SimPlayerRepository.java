package ca.bcit.comp2613.rockpaperscissorslizardspocksim.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.SimPlayer;

/**
 * An Interface which will represent our database of SimPlayers 
 * @author Tyler Wardle
 * @version July 30 2014 *
 */
public interface SimPlayerRepository extends CrudRepository<SimPlayer, Long>{
	
	/**
	 * Finds a simPlayer by name in the repository
	 * @param name as a String
	 * @return simPlayer as a SimPlayer
	 */
	SimPlayer findByName(String name);
	
}