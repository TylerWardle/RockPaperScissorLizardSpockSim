package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

public interface PlayerEntity {
		
	
	String toString();
			
	long getId() ;
	
	void setId(long id) ;
	
	String getName() ;
	
	void setName(String name) ;
	
	Integer getRoundsPlayed() ;
	
	void setRoundsPlayed(Integer roundsPlayed) ;

	Integer getRoundsWon() ;
	
	void setRoundsWon(Integer roundsWon) ;
	
	Integer getRoundsLost() ;
	
	void setRoundsLost(Integer roundsLost) ;
	
	Integer getRoundsTied() ;
	
	void setRoundsTied(Integer roundsTied) ;
	
	//List<Player> getDefeatedPlayers();
	
	//List<SimPlayer> getDefeatedSimPlayers();
	
}
