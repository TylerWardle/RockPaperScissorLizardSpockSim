package ca.bcit.comp2613.a00913377.util;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.SimPlayer;
/**
 * 
 * @author A00913377 Tyler Wardle
 *
 */
public class TestDriver {
	//AMOUNT must be << than 17576 as there are only that many unique three letter names.
	private static int PLAYERS_TO_GENERATE = 100;
	private static String NAME = "xyz";
	private static String REGEX_NAME = "x..";
	private static int MIN_TEAM_SIZE = 0;
	private static Logger log = Logger.getLogger(TestDriver.class);
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure(TestDriver.class.getResourceAsStream("log4j.properties"));		
		//testNameLookup();
		//testGenerateTeams();
		//testComparePlayers();
	}
	
	/**
	 * Builds an ArrayList of players and sorts them. This method is testing the compareTo method of Player.
	 * then print out a sorted list of players
	 */
	public static void testComparePlayers(){
		ArrayList<Player> players = buildPlayersAndTeams();
		Collections.sort(players);
		log.info("Sorted list of players");
		for (Player player: players){
			log.info("Name: " + player.getName() + " number of team mates: " + player.getTeamMembers().size());
		}
	}
	
	/**
	 * Attempts to place each player on a team with at least on other player. If one or the other player 
	 * is already on a team then the player without a team joins. If both players have a 
	 * team no action is taken. If neither player has a team they form one. 
	 */
	public static void testGenerateTeams(){			
		//Generate players, Build teams, and Print out the teams with a number of members >= MIN_TEAM_SIZE
		printTeams(buildPlayersAndTeams());	
	}
	
	/**
	 * Build and return an ArrayList of Players
	 * @return players as an ArrayList
	 */
	public static ArrayList<Player> buildPlayersAndTeams(){
		Random random = new Random();
		Helper helper = new Helper();
		helper.generatePlayers(PLAYERS_TO_GENERATE);
		ArrayList<Player> players = helper.getPlayers();
		
		log.info("Building Teams");
		
		//Build the teams from random players, if an error is thrown those players may
		//not be added to any team. All errors are recorded in testDriverErrorLog.txt.
		for (Player  playerOne : players){
			Player playerTwo = players.get(random.nextInt(players.size()-1));
			try{
				helper.createTeam(playerOne, playerTwo);
			}catch (DislikePlayerException e){
				log.error(e);
			}catch (TeamWithSelfException e){
				log.error(e);
			}
		}
		return players;		
	}
	
	/**
	 * Print out teams
	 * @param players as an ArrayList
	 */
	public static void printTeams(ArrayList<Player> players){
		ArrayList<Player> playersOnTeams = new ArrayList<Player>();
		
		for (Player player : players){
			if(player.getTeamMembers().size() >= MIN_TEAM_SIZE && !playersOnTeams.contains(player)){
				log.info("Team Members of " + player.getName() + "'s team");
				playersOnTeams.add(player);
				for(Player teamMember : player.getTeamMembers()){
					log.info(teamMember.getName() + " ");
					playersOnTeams.add(teamMember);
				}
			}
		}		
	}
	
	/**
	 * Look up and print to the console a name of a player by a direct matching method and a 
	 * regex lookup.
	 */
	public static void testNameLookup(){
		Helper helper = new Helper();
		helper.generatePlayers(PLAYERS_TO_GENERATE);		
		
		ArrayList<Player> players = helper.findPlayerByName(NAME);
		ArrayList<Player> regexPlayers = helper.findPlayerByNameRegex(REGEX_NAME);
		log.info("Name lookup test");
		
		for (Player player: players){
			
			if (player instanceof SimPlayer){
				log.info("Found exact sim player Name " + player.getName());
				log.info("Gesture Bias " + ((SimPlayer)player).getGestureBias().getDescription());
			}else{
				log.info("Found exact player Name " + player.getName());
			}
				
		}
		
		for (Player player: regexPlayers){			
			if (player instanceof SimPlayer){
				log.info("Found regex sim player Name " + player.getName());
				log.info("Gesture Bias " + ((SimPlayer)player).getGestureBias().getDescription());
			}else{
				log.info("Found regex player Name " + player.getName());
			}
		}	
	}
}
