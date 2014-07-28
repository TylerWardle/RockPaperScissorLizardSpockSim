package ca.bcit.comp2613.rockpaperscissorslizardspocksim.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Gestures;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.SimPlayer;

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
		public static List<Player> generatePlayers(int amount){
			List<Player> players = new ArrayList<Player>();
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
		
		public static SimPlayer generateSimPlayer(List<Player> players){			
			return new SimPlayer(getMaxID(players) + 1 , generateName(),0,0,0,0,Gestures.getRandomGesture());			
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
		
		public static void deletePlayer(List<Player> players, Player player) {
			Iterator<Player> iterator = players.iterator();
			while (iterator.hasNext()) {
				Player currentPlayer = iterator.next();
				if (currentPlayer.getId() == player.getId()) {
					iterator.remove();
					break;
				}
			}
		}
		
		public static void updatePlayer(List<Player> players, Player player){
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
		
		public static long getMaxID(List<Player> players){
			long maxID = 0;
			for(Player player : players){
				if(player.getId()> maxID){
					maxID = player.getId();
				}
			}
			return maxID;
		}

	}

