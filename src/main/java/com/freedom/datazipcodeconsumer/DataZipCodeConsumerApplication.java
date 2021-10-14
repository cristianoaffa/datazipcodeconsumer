package com.freedom.datazipcodeconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class DataZipCodeConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataZipCodeConsumerApplication.class, args);
	}

}