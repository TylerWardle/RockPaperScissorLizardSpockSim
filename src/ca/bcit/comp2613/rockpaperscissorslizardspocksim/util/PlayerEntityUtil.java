package ca.bcit.comp2613.rockpaperscissorslizardspocksim.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Gestures;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.PlayerEntity;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.SimPlayer;

/**
 * A static class which helps generate random player entities and other functions 
 * @author Tyler Wardle
 * @version July 30 2014
 *  *
 */
public class PlayerEntityUtil{	
	
	private static int RANDOM_NAME_LENGTH = 3;
	private static int MAX_GAMES = 0;
		
		/**
		 * default constructor
		 */
		public PlayerEntityUtil(){		
		}
		
	
		
		/**
		 * Populates the players ArrayList with Amount number of randomly generated Players or SimPlayers
		 * @param amount as an int
		 */
		public static List<PlayerEntity> generatePlayers(int amount){
			List<PlayerEntity> players = new ArrayList<PlayerEntity>();
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
		 * Generates a unique random SimPlayer
		 * @param players as a List<PlayerEntity>
		 * @return simPlayer as a SimPlayer
		 */
		public static SimPlayer generateSimPlayer(List<PlayerEntity> players){			
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
			int letterIndex;
			name = "";
			for (int i = 0; i < RANDOM_NAME_LENGTH; i++){			
				letterIndex = random.nextInt(alphabet.length());
				name = name.concat(alphabet.substring(letterIndex,letterIndex + 1));								
			}			
			return name;
		}
		
		/**
		 * delete a player from a List<PlayerEntity>
		 * @deprecated data is stored in an H2 database now and database calls are used to delete
		 * 
		 * @param players as a List<PlayerEntity>
		 * @param player as a PlayerEntity
		 */
		public static void deletePlayer(List<PlayerEntity> players, PlayerEntity player) {
			Iterator<PlayerEntity> iterator = players.iterator();
			while (iterator.hasNext()) {
				PlayerEntity currentPlayer = iterator.next();
				if (currentPlayer.getId() == player.getId()) {
					iterator.remove();
					break;
				}
			}
		}
		
		/**
		 * Update a player from a List<PlayerEntity>
		 * @deprecated data is stored in an H2 database now and database calls are used to update
		 * 
		 * @param players as a List<PlayerEntity>
		 * @param player as a PlayerEntity
		 */
		public static void updatePlayer(List<PlayerEntity> players, PlayerEntity player){
			Iterator<PlayerEntity> iterator = players.iterator();
			while (iterator.hasNext()) {
				PlayerEntity currentPlayer = iterator.next();
				if (currentPlayer.getId() == player.getId()) {
					//currentPlayer = player;
					int index = players.indexOf(currentPlayer);
					iterator.remove();
					players.add(index,player);
					break;
				}		
			}
		}
		
		/**
		 * get the maximum id of players in the bracket
		 * @param players as a List<PlayerEntity>
		 * @return maxID as a long
		 */
		public static long getMaxID(List<PlayerEntity> players){
			long maxID = 0;
			for(PlayerEntity player : players){
				if(player.getId()> maxID){
					maxID = player.getId();
				}
			}
			return maxID;
		}

	}

