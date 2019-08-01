package com.clickbus.place;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories({"com.clickbus.place"})
@EnableJpaAuditing
@ComponentScan({"com.clickbus.place"})
public class PlaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaceApplication.class, args);
	}
	
	
}