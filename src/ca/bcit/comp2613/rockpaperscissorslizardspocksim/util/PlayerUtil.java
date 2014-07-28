package ca.bcit.comp2613.rockpaperscissorslizardspocksim.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;

public class PlayerUtil{
	   
	/**
 	* @param players is a list of players to be populated as an ArrayList of type Player
 	*/
	
	private static int RANDOM_NAME_LENGTH = 3;
	private static int MAX_GAMES = 0;
		
		/**
		 * default constructor
		 */
		public PlayerUtil(){		
		}
		
	
		
		/**
		 * Populates the players ArrayList with Amount number of randomly generated Players or SimPlayers
		 * @param amount as an int
		 */
		public static ArrayList<Player> generatePlayers(int amount){
			ArrayList<Player> players = new ArrayList<Player>();
			Random random = new Random();
			
			for (int i = 0; i < amount; i++){
				
					players.add(new Player(i, 
									generateName(),
									random.nextInt(MAX_GAMES),
									random.nextInt(MAX_GAMES),
									random.nextInt(MAX_GAMES),
									random.nextInt(MAX_GAMES)));
				
			}		
			return players;			
		}
		
		/**
		 * Generates a unique random player name of length RANDOM_NAME_LENGTH  
		 * @return name as a String
		 */
		public static String generateName(){
			Random random = new Random();
			String alphabet = "abcdefghijklmnopqrstuvbxyz";
			String name = "";
			//boolean uniqueName = false;
			int letterIndex;
			
			//while (!uniqueName){
				name = "";
				for (int i = 0; i < RANDOM_NAME_LENGTH; i++){			
					letterIndex = random.nextInt(alphabet.length());
					name = name.concat(alphabet.substring(letterIndex,letterIndex + 1));								
				}
			//	if(findPlayerByName(name).isEmpty()){
			//		uniqueName = true;
			//	}
			//}
			return name;
		}
		
		public static void deletePlayer(ArrayList<Player> players, Player player) {
			Iterator<Player> iterator = players.iterator();
			while (iterator.hasNext()) {
				Player currentPlayer = iterator.next();
				if (currentPlayer.getId() == player.getId()) {
					iterator.remove();
					break;
				}
			}
		}
		
		public static void updatePlayer(ArrayList<Player> players, Player player){
			Iterator<Player> iterator = players.iterator();
			while (iterator.hasNext()) {
				Player currentPlayer = iterator.next();
				if (currentPlayer.getId() == player.getId()) {
					//currentPlayer = player;
					int index = players.indexOf(currentPlayer);
					iterator.remove();
					players.add(index,player);
					break;
				}		
			}
		}
		
		public static long getMaxID(ArrayList<Player> players){
			long maxID = 0;
			for(Player player : players){
				if(player.getId()> maxID){
					maxID = player.getId();
				}
			}
			return maxID;
		}

	}

