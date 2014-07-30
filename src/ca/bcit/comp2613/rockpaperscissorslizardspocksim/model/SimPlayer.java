package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;

import java.util.ArrayList;
import java.util.List;

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
 * @author A00913377 Tyler Wardle
 * generates objects which represent simulated RPSLS players
 */
@Entity
public class SimPlayer implements PlayerEntity {
//public class SimPlayer implements Comparable<SimPlayer>, PlayerEntity {
	
	@Id 
	private long id;
	private String name;	
	private Integer roundsPlayed;
	private Integer roundsWon;
	private Integer roundsLost;
	private Integer roundsTied;	
	private Gestures gestureBias;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "sim_player_defeated_players",
	joinColumns = { @JoinColumn(name = "sim_player_name") }, inverseJoinColumns = { @JoinColumn(name = "defeated_player_name") })
	private List<Player> defeatedPlayers;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "sim_player_defeated_sim_players",
	joinColumns = { @JoinColumn(name = "sim_player_name") }, inverseJoinColumns = { @JoinColumn(name = "defeated_sim_player_name") })
	private List<SimPlayer> defeatedSimPlayers;
		
	
	/**
	 * default constructor
	 * 
	 */
	
	public SimPlayer() {
		super();
		this.id = 0;
		this.name = "";
		this.roundsPlayed = 0;
		this.roundsWon = 0;
		this.roundsLost = 0;
		this.roundsTied = 0;		
		this.defeatedPlayers = new ArrayList<Player>();
		this.defeatedSimPlayers = new ArrayList<SimPlayer>();
	}
	
	/**
	 * @param id as a long
	 * @param name as a String
	 * @param roundsPlayer as an int
	 * @param roundsWon as an int
	 * @param roundsLost as an int
	 * @param roundsTied as an int
	 * @param gestureBias as a Gestures
	 * 
	 */
	public SimPlayer(long id, String name, Integer roundsPlayed, Integer roundsWon,
			Integer roundsLost, Integer roundsTied, Gestures gestureBias) {
		super();
		this.id = id;
		this.name = name;
		this.roundsPlayed = roundsPlayed;
		this.roundsWon = roundsWon;
		this.roundsLost = roundsLost;
		this.roundsTied = roundsTied;
		this.defeatedPlayers = new ArrayList<Player>();
		this.defeatedSimPlayers = new ArrayList<SimPlayer>();
		this.gestureBias = gestureBias;		
	}
	
	/**
	 * overide toString method
	 */
	public String toString(){
		return name;
	}
	
/*	@Override	
	public int compareTo(SimPlayer player){
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
	 * get Gesture bias
	 * @return the gestureBias as a Gestures
	 */
	public Gestures getGestureBias() {
		return gestureBias;
	}

	/**
	 * set gesture bias
	 * @param gestureBias as a Gestures
	 */
	public void setGestureBias(Gestures gestureBias) {
		this.gestureBias = gestureBias;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the roundsPlayed
	 */
	public Integer getRoundsPlayed() {
		return roundsPlayed;
	}

	/**
	 * @param roundsPlayed the roundsPlayed to set
	 */
	public void setRoundsPlayed(Integer roundsPlayed) {
		this.roundsPlayed = roundsPlayed;
	}

	/**
	 * @return the roundsWon
	 */
	public Integer getRoundsWon() {
		return roundsWon;
	}

	/**
	 * @param roundsWon the roundsWon to set
	 */
	public void setRoundsWon(Integer roundsWon) {
		this.roundsWon = roundsWon;
	}

	/**
	 * @return the roundsLost
	 */
	public Integer getRoundsLost() {
		return roundsLost;
	}

	/**
	 * @param roundsLost the roundsLost to set
	 */
	public void setRoundsLost(Integer roundsLost) {
		this.roundsLost = roundsLost;
	}

	/**
	 * @return the roundsTied
	 */
	public Integer getRoundsTied() {
		return roundsTied;
	}

	/**
	 * @param roundsTied the roundsTied to set
	 */
	public void setRoundsTied(Integer roundsTied) {
		this.roundsTied = roundsTied;
	}

	/**
	 * @return the defeatedPlayers
	 *//*
	public List<Player> getDefeatedPlayers() {
		return defeatedPlayers;
	}

	*//**
	 * @param defeatedPlayers the defeatedPlayers to set
	 *//*
	public void setDefeatedPlayers(List<Player> defeatedPlayers) {
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
