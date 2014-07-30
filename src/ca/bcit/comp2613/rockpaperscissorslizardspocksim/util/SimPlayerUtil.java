package ca.bcit.comp2613.rockpaperscissorslizardspocksim.util;

import java.util.List;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Gestures;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.PlayerEntity;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.SimPlayer;

public class SimPlayerUtil {
	
	public static SimPlayer generateSimPlayer(List<PlayerEntity> players){			
		return new SimPlayer(PlayerUtil.getMaxID(players) + 1 , PlayerUtil.generateName(),0,0,0,0,Gestures.getRandomGesture());			
	}

}
