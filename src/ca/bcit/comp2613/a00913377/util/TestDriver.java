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
	private static int AMOUNT = 100;
	private static String NAME = "xyz";
	private static String REGEX_NAME = "x..";
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		testNameLookup();
		testGenerateTeams();
	}

	public static void testGenerateTeams(){
		Helper helper = new Helper();
		helper.generatePlayers(AMOUNT);
		ArrayList<Player> players = helper.getPlayers();
		
		try{
			helper.buildTeams();
		}catch (DislikePlayerException e){
			System.out.println(e);
		}catch (TeamWithSelfException e){
			System.out.println(e);
		}
		
		
		for (Player player: players){
			System.out.println("Found exact player Name " + player.getName());
			if (player instanceof SimPlayer){
				System.out.println("Gesture Bias " + ((SimPlayer)player).getGestureBias().getDescription());
			}
			System.out.print("Team Members ");
			for (Player teamMember : player.getTeamMembers()){
				System.out.print(teamMember.getName() + " ");					
			}				
			System.out.println("");
			
		}
	}
	/**
	 * 
	 */
	public static void testNameLookup(){
		Helper helper = new Helper();
		helper.generatePlayers(AMOUNT);
		
		
		ArrayList<Player> players = helper.findPlayerByName(NAME);
		ArrayList<Player> regexPlayers = helper.findPlayerByNameRegex(REGEX_NAME);
		
		for (Player player: players){
			System.out.println("Found exact player Name " + player.getName());
			if (player instanceof SimPlayer){
				System.out.println("Gesture Bias " + ((SimPlayer)player).getGestureBias().getDescription());
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
