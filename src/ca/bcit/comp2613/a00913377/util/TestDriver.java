package ca.bcit.comp2613.a00913377.util;
import java.util.ArrayList;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.SimPlayer;
/**
 * 
 * @author A00913377 Tyler Wardle
 *
 */
public class TestDriver {
	//AMOUNT must be << than 17576 as there are only that many unique three letter names.
	private static int AMOUNT = 10000;
	private static String NAME = "xyz";
	private static String REGEX_NAME = "xy.";
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		testNameLookup();
	}

	/**
	 * 
	 */
	public static void testNameLookup(){
		Helper helper = new Helper();
		helper.generatePlayers(AMOUNT);
		helper.buildTeams();
		ArrayList<Player> players = helper.findPlayerByName(NAME);
		ArrayList<Player> regexPlayers = helper.findPlayerByNameRegex(REGEX_NAME);
		
		for (Player player: players){
			System.out.println("Found exact player Name " + player.getName());
			if (player instanceof SimPlayer){
				System.out.println("Gesture Bias " + ((SimPlayer)player).getGestureBias().getDescription());
				System.out.println("Team Captain " + ((SimPlayer)player).getTeamCaptain().getName());
			}
		}
		
		for (Player player: regexPlayers){
			System.out.println("Found regex player Name " + player.getName());
			if (player instanceof SimPlayer){
				System.out.println("Gesture Bias " + ((SimPlayer)player).getGestureBias().getDescription());
			}
		}	
	}
}
