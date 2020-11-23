package com.suhas.springboot.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSocketApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(SpringBootSocketApplication.class);

	public static void main(String[] args) {
		LOGGER.info("application stated..");
		SpringApplication.run(SpringBootSocketApplication.class, args);
		LOGGER.info("application finished..");
	}

}
