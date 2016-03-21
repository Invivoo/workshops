package com.invivoo.springboot.keyconcepts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KeyConcepts {
	private static final Logger LOGGER = LoggerFactory.getLogger(KeyConcepts.class);

	public static void main(String[] args) {
		SpringApplication.run(KeyConcepts.class, args);

		LOGGER.info("Demo application has been initialized successfully.");
	}
}