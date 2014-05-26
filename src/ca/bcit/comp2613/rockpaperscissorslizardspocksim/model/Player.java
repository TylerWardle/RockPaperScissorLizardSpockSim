package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;

public class Player {
	/**
	 * @author A00913377 Tyler Wardle
	 *
	 */
	private long id;
	private String name;	
	private Integer roundsPlayed;
	private Integer roundsWon;
	private Integer roundsLost;
	private Integer roundsTied;	
			
	public Player(long id, String name, Integer roundsPlayed,
			Integer roundsWon, Integer roundsLost, Integer roundsTied) {
		super();
		this.id = id;
		this.name = name;
		this.roundsPlayed = roundsPlayed;
		this.roundsWon = roundsWon;
		this.roundsLost = roundsLost;
		this.roundsTied = roundsTied;
	}

		
	public String toString(){
		
		return "";
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
	
}
