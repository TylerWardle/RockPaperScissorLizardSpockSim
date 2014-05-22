package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;

public class SimPlayer {
	
	private String name;		
	private Integer roundsPlayed;
	private Integer roundsWon;
	private Integer roundsLost;
	private Integer roundsTied;
	private Gesture gestureBias;
	
	/**
	 * @param name
	 * @param roundsPlayed
	 * @param roundsWon
	 * @param roundsLost
	 * @param roundsTied
	 * @param gestureBias
	 */
	public SimPlayer(String name, Integer roundsPlayed, Integer roundsWon,
			Integer roundsLost, Integer roundsTied, Gesture gestureBias) {
		super();
		this.name = name;
		this.roundsPlayed = roundsPlayed;
		this.roundsWon = roundsWon;
		this.roundsLost = roundsLost;
		this.roundsTied = roundsTied;
		this.gestureBias = gestureBias;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public String toString(){
		return "";
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
	 * @return the gestureBias
	 */
	public Gesture getGestureBias() {
		return gestureBias;
	}

	/**
	 * @param gestureBias the gestureBias to set
	 */
	public void setGestureBias(Gesture gestureBias) {
		this.gestureBias = gestureBias;
	}
	
	

}
