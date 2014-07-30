package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * @author Tyler Wardle
 * @version July 30, 2014
 * generates objects which represent RPSLS players 
 */

@Entity
public class Player implements PlayerEntity{
//public class Player implements Comparable<Player>, PlayerEntity{
	
	@Id
	private long id;
	private String name;	
	private Integer roundsPlayed;
	private Integer roundsWon;
	private Integer roundsLost;
	private Integer roundsTied;	
	/*@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "player_defeated_players",
	joinColumns = { @JoinColumn(name = "player_name") }, inverseJoinColumns = { @JoinColumn(name = "defeated_player_name") })
	private List<Player> defeatedPlayers;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "player_defeated_sim_players",
	joinColumns = { @JoinColumn(name = "player_name") }, inverseJoinColumns = { @JoinColumn(name = "defeated_sim_player_name") })
	private List<SimPlayer> defeatedSimPlayers;*/
	
	/**
	 * Default constructor 
	 */
	public Player() {
		super();
		this.id = 0;
		this.name = "";
		this.roundsPlayed = 0;
		this.roundsWon = 0;
		this.roundsLost = 0;
		this.roundsTied = 0;		
		//this.defeatedPlayers = new ArrayList<Player>();
		//this.defeatedSimPlayers = new ArrayList<SimPlayer>();
	}
		
	/**
	 * Overloaded constructor
	 * 
	 * @param id as a long
	 * @param name as a String
	 * @param roundsPlayer as an int
	 * @param roundsWon as an int
	 * @param roundsLost as an int
	 * @param roundsTied as an int 
	 */
	public Player(long id, String name, Integer roundsPlayed,
			Integer roundsWon, Integer roundsLost, Integer roundsTied) {
		super();
		this.id = id;
		this.name = name;
		this.roundsPlayed = roundsPlayed;
		this.roundsWon = roundsWon;
		this.roundsLost = roundsLost;
		this.roundsTied = roundsTied;
		//this.defeatedPlayers = new ArrayList<Player>();
		//this.defeatedSimPlayers = new ArrayList<SimPlayer>();
	}

	/**
	 * over ridden toString method	
	 */
	@Override
	public String toString(){
		
		return name;
	}
	
	/*@Override	
	public int compareTo(Player player){	
		int retval = this.getName().compareTo(player.getName());
		if (retval == 0){
			if (this.getDefeatedPlayers().size() < player.getDefeatedPlayers().size()){
				retval = 1;
			}else if (this.getDefeatedPlayers().size() > player.getDefeatedPlayers().size()){
				retval = -1;
			}			 
		}		
		return retval;
	}	*/
		
	/**
	 * get id 
	 * @return id as a Long
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * set id 
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * get name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	* set the name
	* @param name the name to set
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get rounds played
	 * @return the roundsPlayed
	 */
	public Integer getRoundsPlayed() {
		return roundsPlayed;
	}

	/**
	 * set rounds played
	 * @param roundsPlayed the roundsPlayed to set
	 */
	public void setRoundsPlayed(Integer roundsPlayed) {
		this.roundsPlayed = roundsPlayed;
	}

	/**
	 * get rounds won 
	 * @return the roundsWon
	 */
	public Integer getRoundsWon() {
		return roundsWon;
	}

	/**
	 * set rounds won
	 * @param roundsWon the roundsWon to set
	 */
	public void setRoundsWon(Integer roundsWon) {
		this.roundsWon = roundsWon;
	}

	/**
	 * get rounds lost
	 * @return the roundsLost
	 */
	public Integer getRoundsLost() {
		return roundsLost;
	}

	/**
	 * set rounds lost
	 * @param roundsLost the roundsLost to set
	 */
	public void setRoundsLost(Integer roundsLost) {
		this.roundsLost = roundsLost;
	}

	/**
	 * get rounds tied
	 * @return the roundsTied
	 */
	public Integer getRoundsTied() {
		return roundsTied;
	}

	/**
	 * set rounds tied
	 * @param roundsTied the roundsTied to set
	 */
	public void setRoundsTied(Integer roundsTied) {
		this.roundsTied = roundsTied;
	}
	
	/**
	 * get defeated players
	 * @return defeatedPlayers as an ArrayList of Players
	 *//*	
	public List<Player> getDefeatedPlayers(){
		return defeatedPlayers;
	}
	
	*//**
	 * set defeated players
	 * @param defeatedPlayers as an ArrayList of Players
	 *//*
	public void setDefeatedPlayers(List<Player> defeatedPlayers){
		this.defeatedPlayers = defeatedPlayers;
	}

	*//**
	 * @return the defeatedSimPlayers
	 *//*
	public List<SimPlayer> getDefeatedSimPlayers() {
		return defeatedSimPlayers;
	}

	*//**
	 * @param defeatedSimPlayers the defeatedSimPlayers to set
	 *//*
	public void setDefeatedSimPlayers(List<SimPlayer> defeatedSimPlayers) {
		this.defeatedSimPlayers = defeatedSimPlayers;
	}
	*/
	
}
