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
 * @author A00913377
 *
 */
public class Helper {

	/**
	 * @param args
	 */
	private ArrayList<Player> players;
	
	public Helper(){
		players = new ArrayList<Player>();
	}
	
	public void generatePlayers(int amount){
		Random random = new Random();
		
		for (int i = 0; i < amount; i++){
			if (random.nextBoolean()){
				players.add(new Player(random.nextLong(), 
										"" +random.nextInt(),
										random.nextInt(),
										random.nextInt(),
										random.nextInt(),
										random.nextInt()));
			}else{
				players.add(new SimPlayer(random.nextLong(), 
						"" +random.nextInt(),
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

}
