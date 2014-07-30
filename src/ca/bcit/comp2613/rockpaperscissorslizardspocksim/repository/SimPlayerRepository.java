package ca.bcit.comp2613.rockpaperscissorslizardspocksim.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.SimPlayer;

public interface SimPlayerRepository extends CrudRepository<SimPlayer, Long>{
	
	SimPlayer findByName(String name);
	
}