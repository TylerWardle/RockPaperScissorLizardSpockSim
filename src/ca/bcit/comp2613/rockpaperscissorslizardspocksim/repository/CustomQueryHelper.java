package ca.bcit.comp2613.rockpaperscissorslizardspocksim.repository;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;

public class CustomQueryHelper {
	
	private EntityManagerFactory emf;

	public CustomQueryHelper(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public Player getPlayer(Long playerId) {
		Player player = null;
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			player = em.find(Player.class, playerId);
			return player;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				em.close();
			} catch (Exception e) {
			}
		}
		return player;
	}

}
