package ca.bcit.comp2613.rockpaperscissorslizardspocksim;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.util.PlayerUtil;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.repository.PlayerRepository;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.repository.CustomQueryHelper;

@EnableAutoConfiguration
@ImportResource("applicationContext.xml")
public class TestDriver {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TestDriver.class); 
		
		PlayerRepository playerRepository = context.getBean(PlayerRepository.class);
		EntityManagerFactory emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
		CustomQueryHelper customQueryHelper = new CustomQueryHelper(emf);		
		
		//Empty previous players from repository 
		playerRepository.deleteAll();
		//generate 100 new random players
		List<Player> players = PlayerUtil.generatePlayers(100);
		//add those players to the repository
		playerRepository.save(players);
		
		//create a player and set it to id 20  
		Player player = new Player();			
		player.setId((long) 20);		
		player.setName("Tyler");
		playerRepository.save(player); 		
		
		//Search for and print out the name of the player with id 20
		System.out.println(customQueryHelper.getPlayer((long)20).getName());
		
		context.close();
		
	}
}
