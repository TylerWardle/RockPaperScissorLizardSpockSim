package ca.bcit.comp2613.rockpaperscissorslizardspocksim.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;

public interface PlayerRepository extends CrudRepository<Player, String>{
	List<Player> findByName(String name);
}
