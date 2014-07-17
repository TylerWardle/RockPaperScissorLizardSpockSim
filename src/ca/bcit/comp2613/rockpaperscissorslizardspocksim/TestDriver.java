package ca.bcit.comp2613.rockpaperscissorslizardspocksim;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.util.PlayerUtil;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.repository.PlayerRepository;

@EnableAutoConfiguration
@ImportResource("applicationContext.xml")
public class TestDriver {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TestDriver.class); 
		
		PlayerRepository playerRepository = context.getBean(PlayerRepository.class);
		ArrayList<Player> players = PlayerUtil.generatePlayers(100);
		playerRepository.save(players);

		//Player player = new Player();
		//player.setId(20);		
		//player.setName("Tyler");
		//playerRepository.save(player); 
		
		context.close();
		
	}
}
