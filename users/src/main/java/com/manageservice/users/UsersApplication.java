package com.manageservice.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, JdbcTemplateAutoConfiguration.class })
@Slf4j
@Configuration
@EnableTransactionManagement
public class UsersApplication {

	public static void main(String[] args) {

		SpringApplication.run(UsersApplication.class, args);
		log.info("API-MANAGE_USERS Application Started");
	}

}
