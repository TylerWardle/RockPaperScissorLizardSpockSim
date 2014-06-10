package ca.bcit.comp2613.a00913377.util;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

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
	
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {		
		//testNameLookup();
		//testGenerateTeams();
		testComparePlayers();
	}
	
	/**
	 * 
	 */
	public static void testComparePlayers(){
		ArrayList<Player> players = buildPlayersAndTeams();
		Collections.sort(players);
		System.out.println("Sorted list of players");
		for (Player player: players){
			System.out.println("Name: " + player.getName() + " number of team mates: " + player.getTeamMembers().size());
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
	 * 
	 * @return
	 */
	public static ArrayList<Player> buildPlayersAndTeams(){
		Random random = new Random();
		Helper helper = new Helper();
		helper.generatePlayers(PLAYERS_TO_GENERATE);
		ArrayList<Player> players = helper.getPlayers();
		
		System.out.println("Building Teams");
		
		//Build the teams from random players, if an error is thrown those players may
		//not be added to any team.
		for (Player  playerOne : players){
			Player playerTwo = players.get(random.nextInt(players.size()-1));
			try{
				helper.createTeam(playerOne, playerTwo);
			}catch (DislikePlayerException e){
				System.out.println(e);
			}catch (TeamWithSelfException e){
				System.out.println(e);
			}
		}
		return players;		
	}
	
	/**
	 * 
	 * @param players
	 */
	public static void printTeams(ArrayList<Player> players){
		ArrayList<Player> playersOnTeams = new ArrayList<Player>();
		
		for (Player player : players){
			if(player.getTeamMembers().size() >= MIN_TEAM_SIZE && !playersOnTeams.contains(player)){
				System.out.println("Team Members of " + player.getName() + "'s team");
				playersOnTeams.add(player);
				for(Player teamMember : player.getTeamMembers()){
					System.out.print(teamMember.getName() + " ");
					playersOnTeams.add(teamMember);
				}
				System.out.println(" ");
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
		System.out.println("Name lookup test");
		
		for (Player player: players){
			
			if (player instanceof SimPlayer){
				System.out.println("Found exact sim player Name " + player.getName());
				System.out.println("Gesture Bias " + ((SimPlayer)player).getGestureBias().getDescription());
				System.out.println(" ");
			}else{
				System.out.println("Found exact player Name " + player.getName());
				System.out.println(" ");
			}
				
		}
		
		for (Player player: regexPlayers){			
			if (player instanceof SimPlayer){
				System.out.println("Found regex sim player Name " + player.getName());
				System.out.println("Gesture Bias " + ((SimPlayer)player).getGestureBias().getDescription());
				System.out.println(" ");
			}else{
				System.out.println("Found regex player Name " + player.getName());
				System.out.println(" ");
			}
		}	
	}
}
