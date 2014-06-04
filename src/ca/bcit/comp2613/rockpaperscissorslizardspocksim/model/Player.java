package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;
import java.util.ArrayList;

/**
 * @author A00913377 Tyler Wardle
 * generates objects which represent RPSLS players 
 */
public class Player {
	
	private long id;
	private String name;	
	private Integer roundsPlayed;
	private Integer roundsWon;
	private Integer roundsLost;
	private Integer roundsTied;	
	private ArrayList<Player> teamMembers;
		
	/**
	 * Default constructor
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
		teamMembers = new ArrayList<Player>();
	}

	/**
	 * over ridden toString method	
	 */
	public String toString(){
		
		return name;
	}
		
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
	 * get team members
	 * @return teamMembers as an ArrayList of Players
	 */	
	public ArrayList<Player> getTeamMembers(){
		return teamMembers;
	}
	
	/**
	 * set team members
	 * @param teamMembers as an ArrayList of Players
	 */
	public void setTeamMembers(ArrayList<Player> teamMembers){
		this.teamMembers = teamMembers;
	}
}
