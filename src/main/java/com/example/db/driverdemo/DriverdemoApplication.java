package com.example.db.driverdemo;

import com.example.db.driverdemo.service.DBConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DBConfiguration.class)
public class DriverdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriverdemoApplication.class, args);
	}

}
