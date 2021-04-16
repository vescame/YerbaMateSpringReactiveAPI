package org.vescm.yerbamate;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamoDBRepositories
public class YerbamateApplication {

	public static void main(String[] args) {
		SpringApplication.run(YerbamateApplication.class, args);
	}

}
