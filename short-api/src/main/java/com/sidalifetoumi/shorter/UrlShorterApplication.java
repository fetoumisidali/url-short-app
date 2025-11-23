package com.sidalifetoumi.shorter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class UrlShorterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShorterApplication.class, args);
	}

}
