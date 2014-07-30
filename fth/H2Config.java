package ca.bcit.comp2613.coursematerial.fth;

import java.sql.SQLException;

import javax.persistence.PersistenceUnit;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import ca.bcit.comp2613.coursematerial.day09.model.Teacher;
import ca.bcit.comp2613.coursematerial.day09.repository.TeacherRepository;
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
/*
 * <bean id="h2Server" class="org.h2.tools.Server" factory-method="createTcpServer" init-method="start" destroy-method="stop" depends-on="h2WebServer">
    <constructor-arg value="-tcp,-tcpAllowOthers,-tcpPort,9092"/>
</bean>
<bean id="h2WebServer" class="org.h2.tools.Server" factory-method="createWebServer" init-method="start" destroy-method="stop">
    <constructor-arg value="-web,-webAllowOthers,-webPort,8082"/>
</bean>
 */
public class H2Config {
	static {
		try {
			org.h2.tools.Server.createWebServer(null).start();
		} catch (SQLException e) {
		}
	}
	
}
