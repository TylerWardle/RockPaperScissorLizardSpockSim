/**
 * 
 */
package ca.bcit.comp2613.a00913377.util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
	private static int MAX_GAMES = 10;
	
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
			//if (true){
				players.add(new Player(i, 
								generateName(),
								random.nextInt(MAX_GAMES),
								random.nextInt(MAX_GAMES),
								random.nextInt(MAX_GAMES),
								random.nextInt(MAX_GAMES)));
			}else{
				players.add(new SimPlayer(i, 
								generateName(),
								random.nextInt(MAX_GAMES),
								random.nextInt(MAX_GAMES),
								random.nextInt(MAX_GAMES),
								random.nextInt(MAX_GAMES),
								Gestures.getRandomGesture())); 								
			}			
		}		
		
	}
	
	/**
	 * Generates a unique random player name of length RANDOM_NAME_LENGTH  
	 * @return name as a String
	 */
	public String generateName(){
		Random random = new Random();
		String alphabet = "abcdefghijklmnopqrstuvbxyz";
		String name = "";
		boolean uniqueName = false;
		int letterIndex;
		
		while (!uniqueName){
			name = "";
			for (int i = 0; i < RANDOM_NAME_LENGTH; i++){			
				letterIndex = random.nextInt(alphabet.length());
				name = name.concat(alphabet.substring(letterIndex,letterIndex + 1));								
			}
			if(findPlayerByName(name).isEmpty()){
				uniqueName = true;
			}
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
	
	/**
	 * Creates a team between playerOne's existing members and PlayerTwo's existing
	 * members. If playerOne and playerTwo dislike each other an error is thrown.
	 * if playerOne and playerTwo are the same player then an error is thrown.
	 * 
	 * @param playerOne as a Player
	 * @param playerTwo as a Player
	 * @throws DislikePlayerException
	 * @throws TeamWithSelfException
	 */
	public void createTeam(Player playerOne, Player playerTwo) throws DislikePlayerException, TeamWithSelfException{
		Random random = new Random();
		if(random.nextInt() % 5 == 0){
			throw new DislikePlayerException(playerOne, playerTwo);
		}else if(playerTwo == playerOne){
			throw new TeamWithSelfException(playerOne);
		}else{			
			ArrayList<Player> newTeam = new ArrayList<Player>();
			if (playerTwo.getTeamMembers().isEmpty() && playerOne.getTeamMembers().isEmpty()){
				newTeam.add(playerOne);	
				newTeam.add(playerTwo);
				playerOne.setTeamMembers(newTeam);
				playerTwo.setTeamMembers(newTeam);
			}else if(playerTwo.getTeamMembers().isEmpty() && !playerOne.getTeamMembers().isEmpty()){
				newTeam = (ArrayList)playerOne.getTeamMembers();
				newTeam.add(playerTwo);				
				playerOne.setTeamMembers(newTeam);
				playerTwo.setTeamMembers(newTeam);
			}else if(playerOne.getTeamMembers().isEmpty() && !playerTwo.getTeamMembers().isEmpty()){
				newTeam = (ArrayList)playerTwo.getTeamMembers();
				newTeam.add(playerOne);				
				playerOne.setTeamMembers(newTeam);
				playerTwo.setTeamMembers(newTeam);
			}else{
				// both players already on a team so do nothing 
			}
		}
		
	}
	
	public ArrayList<Player> populatePlayers(int size){
		
		generatePlayers(size);
		
		return players;
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
}

