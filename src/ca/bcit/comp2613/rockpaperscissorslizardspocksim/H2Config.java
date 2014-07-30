package ca.bcit.comp2613.rockpaperscissorslizardspocksim;

import java.sql.SQLException;

import javax.persistence.PersistenceUnit;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.repository.PlayerRepository;

import org.h2.tools.Server;
@EnableAutoConfiguration
// uses Spring boot's default in-memory DB http://www.h2database.com
// to start the embedded server:
// org.h2.tools.Server.createWebServer(null).start();
// then goto your browser:
// http://localhost:8082
// Generic H2 (Embedded)
// org.h2.Driver
// jdbc:h2:mem:testdb
// blank username and password
//
// see also: http://stackoverflow.com/questions/17803718/view-content-of-embedded-h2-database-started-by-spring

/**
 * Used to auto configure an in memory database for object storage
 * @author Tyler Wardle
 */
public class H2Config {	
}
