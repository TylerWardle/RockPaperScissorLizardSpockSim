/**
 * 
 */
package ca.bcit.comp2613.a00913377.util;
import ca.bcit.comp2613.a00913377.util.Helper;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;

/**
 * @author A00913377 Tyler Wardle
 *
 */
public class TestTeams {
	
	private static int NUMBER_OF_PLAYERS = 100;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			generateTeams();
		}catch (DislikePlayerException e) {
			System.out.println(e);
		}

	}
	
	public static void generateTeams() throws DislikePlayerException{
		Helper helper = new Helper();
		helper.generatePlayers(NUMBER_OF_PLAYERS);
		for (Player player : helper.getPlayers()){
			
		}
		
	}
	

}
