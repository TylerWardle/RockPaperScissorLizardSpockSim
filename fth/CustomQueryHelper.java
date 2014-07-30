package ca.bcit.comp2613.coursematerial.fth;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class CustomQueryHelper {
	private EntityManagerFactory emf;
	private PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public CustomQueryHelper(EntityManagerFactory emf) {
		this.emf = emf;
		try {
			propertiesConfiguration.load(CustomQueryHelper.class.getResourceAsStream("query.properties"));
		} catch (ConfigurationException e) {
			ForTheHordeSwingApplication.log.error("", e);
		}
		
	}

	// report - I want the color of my query report to be ... green (hint as in the
	// color of
	// his skin
	// http://wowpedia.org/File:Grom_Glowei_Cropped.jpg?version=a9ab91e8ee037e2a547ee570eb42a117
	// )
	//
	// OPTIONAL, run the mysteriousQuery via the browser aka (H2 Console).  See comments under the H2Config java file
	public List<Character> mysteriousQuery() {
		List<Character> retval = null;
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			// TODO - mysteriousQuerySQL is pulled from a .properties file, but this is broken - fix it
			String mysteriousQuerySQL = propertiesConfiguration.getString("?mysteriousQuery?");
			ForTheHordeSwingApplication.log.info("mysteriousQuerySQL : " + mysteriousQuerySQL);
			Query query = em.createNativeQuery(
					mysteriousQuerySQL,
					Character.class);
			// query.setParameter("teacher_id", teacherId);
			retval = query.getResultList();
		} catch (Exception e) {

		} finally {
			try {
				em.close();
			} catch (Exception e) {
			}
		}
		return retval;
	}

}
