package com.gurus.mobility;

import com.gurus.mobility.dto.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableScheduling
@SpringBootApplication
@EnableWebSecurity
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class MobilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobilityApplication.class, args);
	}

}
