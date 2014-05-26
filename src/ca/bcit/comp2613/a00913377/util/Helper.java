/**
 * 
 */
package ca.bcit.comp2613.a00913377.util;
import java.util.ArrayList;
import java.util.Random;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.SimPlayer;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Gesture;

/**
 * @author A00913377 Tyler Wardle
 *
 */
public class Helper {

	/**
	 * @param args
	 */
	private ArrayList<Player> players;
	private static int NAME_LENGTH = 4;
	
	public Helper(){
		players = new ArrayList<Player>();
	}
	/**
	 *  
	 * @param amount
	 */
	public void generatePlayers(int amount){
		Random random = new Random();
		
		for (int i = 0; i < amount; i++){
			if (random.nextBoolean()){
				players.add(new Player(i, 
										generateName(),
										random.nextInt(),
										random.nextInt(),
										random.nextInt(),
										random.nextInt()));
			}else{
				players.add(new SimPlayer(i, 
						generateName(),
						random.nextInt(),
						random.nextInt(),
						random.nextInt(),
						random.nextInt(),
						new Gesture(random.nextLong(),
								"" +random.nextInt(),
								"" +random.nextInt())));
			}
		}		
		
	}
	public String generateName(){
		Random random = new Random();
		String alphabet = "abcdefghijklmnopqrstuvbxyz";
		String name = "";
		int letterIndex;
		
		for (int i = 0; i < NAME_LENGTH; i++){
			letterIndex = random.nextInt(alphabet.length());
			name.concat(alphabet.substring(letterIndex,letterIndex));			
		}
				
		return name;
	}

	public ArrayList<Player> findPlayerByName(String name){
		ArrayList<Player> players = new ArrayList<Player>();

		for (Player player: players){
			if (player.getName().equals(name)){
				players.add(player);
			}
		}
		return players;				
	}
	public ArrayList<Player> findPlayerByNameRegex(String name){
		ArrayList<Player> players = new ArrayList<Player>();
		
		for (Player player: players){
			if (player.getName().equals(name)){
				players.add(player);
			}
		}
	}
	
}

