/**
 * 
 */
package ca.bcit.comp2613.a00913377.util;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Gestures;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.SimPlayer;

/**
 * The Helper class generates random instances of Data class objects which can then be used in testing
 * @author A00913377 Tyler Wardle
 *
 */
public class Helper {
   
	/**
	 * @param players is a list of players to be populated as an ArrayList of type Player
	 */
	private ArrayList<Player> players;
	private static int RANDOM_NAME_LENGTH = 3;
	
	/**
	 * default constructor
	 */
	public Helper(){
		players = new ArrayList<Player>();
	}
	
	/**
	 * get players
	 * @return players as an ArrayList<Player>
	 */
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	/**
	 * set players
	 * @param players as an ArrayList<Player>
	 */
	public void setPlayers(ArrayList<Player> players){
		this.players = players;
	}
	
	/**
	 * Populates the players ArrayList with Amount number of randomly generated Players or SimPlayers
	 * @param amount as an int
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
								random.nextInt(), 
								null, null));
			}else{
				players.add(new SimPlayer(i, 
								generateName(),
								random.nextInt(),
								random.nextInt(),
								random.nextInt(),
								random.nextInt(),
								null, null,
								Gestures.getRandomGesture())); 								
			}
		}		
		
	}
	
	/**
	 * Generates a random player name of length RANDOM_NAME_LENGTH  
	 * @return name as a String
	 */
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

	/**
	 * Finds a player name in the list of players by way of an exact match using .equals(name) 
	 * @param name as a String
	 * @return foundPlayers as an Array List of Players
	 */
	public ArrayList<Player> findPlayerByName(String name){
		ArrayList<Player> foundPlayers = new ArrayList<Player>();
		
		for (Player player: players){
			if (player.getName().equals(name)){
				foundPlayers.add(player);
			}
		}
		return foundPlayers;				
	}
	
	/**
	 * Finds a player name in the list of players by using a regex matching
	 * @param name as a String
	 * @return foundPlayers as an Array list of Players
	 */
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

