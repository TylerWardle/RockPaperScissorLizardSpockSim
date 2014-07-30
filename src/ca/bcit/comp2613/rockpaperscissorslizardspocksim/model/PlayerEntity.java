package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * An interface which represents a generic player
 * @author Tyler Wardle
 * @version July 30 2014
 *
 */
public interface PlayerEntity {
		
	
	/**
	 * overridden to string method
	 * @return toString as a String
	 */
	String toString();
			
	/**
	 * get id
	 * @return id as a long 
	 */
	long getId() ;
	
	/**
	 * set id 
	 * @param id as a long
	 */
	void setId(long id) ;
	
	/**
	 * get name
	 * @return as a String
	 */
	String getName() ;
	
	/**
	 * set name
	 * @param name as a String
	 */
	void setName(String name) ;
	
	/**
	 * get roundsPlayed
	 * @return as an integer
	 */
	Integer getRoundsPlayed() ;
	
	/**
	 * set roundsPlayed
	 * @param roundsPlayed as an integer
	 */
	void setRoundsPlayed(Integer roundsPlayed) ;

	/**
	 * get roundsWon
	 * @return as an integer
	 */
	Integer getRoundsWon() ;
	
	/**
	 * set RoundsWon
	 * @param roundsWon as an integer
	 */
	void setRoundsWon(Integer roundsWon) ;
	
	/**
	 * get roundsLost
	 * @return as an integer
	 */
	Integer getRoundsLost() ;
	
	/**
	 * set roundsLost
	 * @param roundsLost as an integer
	 */
	void setRoundsLost(Integer roundsLost) ;
	
	/**
	 * get roundsTied
	 * @return roundsTied as an Integer
	 */
	Integer getRoundsTied() ;
	
	/**
	 * set roundsTied
	 * @param roundsTied as an Integer
	 */
	void setRoundsTied(Integer roundsTied) ;
	
	//List<Player> getDefeatedPlayers();
	
	//List<SimPlayer> getDefeatedSimPlayers();
	
}
