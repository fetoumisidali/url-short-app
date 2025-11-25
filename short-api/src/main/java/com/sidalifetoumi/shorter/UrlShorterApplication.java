package com.sidalifetoumi.shorter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.interceptor.CacheableOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


@SpringBootApplication
@EnableMongoAuditing
public class UrlShorterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShorterApplication.class, args);
	}


	private static final Logger log = LoggerFactory.getLogger(UrlShorterApplication.class);

	@Bean
	public CommandLineRunner checkConfiguration(Environment env) {
		return args -> {
			log.info("=== Application Configuration Check ===");
			log.info("Active Profile: {}", env.getProperty("spring.profiles.active"));
			log.info("MongoDB Host: {}", env.getProperty("spring.data.mongodb.host"));
			log.info("MongoDB Port: {}", env.getProperty("spring.data.mongodb.port"));
			log.info("MongoDB Database: {}", env.getProperty("spring.data.mongodb.database"));
			log.info("MongoDB Username: {}", env.getProperty("spring.data.mongodb.username"));
			log.info("MongoDB URI: {}", env.getProperty("spring.data.mongodb.uri"));
			log.info("App URL: {}", env.getProperty("app.url"));
			log.info("Front URL: {}", env.getProperty("app.front"));
			log.info("URL Suffix: {}", env.getProperty("app.url-suffix"));

			// Check environment variables
			log.info("MONGO_HOST env: {}", System.getenv("MONGO_HOST"));
			log.info("MONGO_USER env: {}", System.getenv("MONGO_USER"));
			log.info("MONGO_DATABASE env: {}", System.getenv("MONGO_DATABASE"));
			log.info("=======================================");
		};
	}

}
