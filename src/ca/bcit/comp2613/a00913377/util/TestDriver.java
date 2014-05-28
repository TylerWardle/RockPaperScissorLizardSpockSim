package ca.bcit.comp2613.a00913377.util;
import java.util.ArrayList;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;

public class TestDriver {

	private static int AMOUNT = 10000;
	private static String NAME = "xyz";
	private static String REGEX_NAME = "xy.";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Helper helper = new Helper();
		helper.generatePlayers(AMOUNT);
		ArrayList<Player> players = helper.findPlayerByName(NAME);
		ArrayList<Player> regexPlayers = helper.findPlayerByNameRegex(REGEX_NAME);
		
		for (Player player: players){
			System.out.println("Found exact player Name " + player.getName());
		}
		for (Player player: regexPlayers){
			System.out.println("Found regex player Name " + player.getName());
		}		
	}

}
