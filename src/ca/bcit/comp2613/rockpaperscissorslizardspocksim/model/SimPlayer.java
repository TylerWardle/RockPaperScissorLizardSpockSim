package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;

/**
 * @author A00913377 Tyler Wardle
 *
 */
public class SimPlayer extends Player{
	
	private Gestures gestureBias;
	
	/**
	 * @param gestureBias
	 */
	public SimPlayer(long id, String name, Integer roundsPlayed, Integer roundsWon,
			Integer roundsLost, Integer roundsTied, Gestures gestureBias) {
		super(id, name, roundsPlayed, roundsWon, roundsLost, roundsTied);
		this.gestureBias = gestureBias;
	}
	
	public String toString(){
		return "";
	}

	/**
	 * @return the gestureBias
	 */
	public Gestures getGestureBias() {
		return gestureBias;
	}

	/**
	 * @param gestureBias the gestureBias to set
	 */
	public void setGestureBias(Gestures gestureBias) {
		this.gestureBias = gestureBias;
	}
	
	

}
