/**
 * 
 */
package ca.bcit.comp2613.a00913377.util;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
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
	private static int RANDOM_NAME_LENGTH = 3;
	
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
		
		for (int i = 0; i < RANDOM_NAME_LENGTH; i++){			
			letterIndex = random.nextInt(alphabet.length());
			name = name.concat(alphabet.substring(letterIndex,letterIndex + 1));		
						
		}
				
		return name;
	}

	public ArrayList<Player> findPlayerByName(String name){
		ArrayList<Player> foundPlayers = new ArrayList<Player>();
		
		for (Player player: players){
			if (player.getName().equals(name)){
				foundPlayers.add(player);
			}
		}
		return foundPlayers;				
	}
	public ArrayList<Player> findPlayerByNameRegex(String name){
		ArrayList<Player> foundPlayers = new ArrayList<Player>();
		
		for (Player player: players){
			Pattern pattern = Pattern.compile(name);
			Matcher matcher = pattern.matcher(player.getName());
			if (matcher.find()){
				foundPlayers.add(player);
			}
		}
		return foundPlayers;
	}
	
}

