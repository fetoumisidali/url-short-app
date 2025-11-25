package com.sidalifetoumi.shorter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


@SpringBootApplication
@EnableMongoAuditing
public class UrlShorterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShorterApplication.class, args);
	}



	@Bean
	CommandLineRunner init(@Value("${spring.data.mongodb.uri}") String mongoUri) {
		return args -> {
			System.out.println("=================================");
			System.out.println("📊 MongoDB URI from config: " + mongoUri);
			System.out.println("=================================");
		};
	}


}
