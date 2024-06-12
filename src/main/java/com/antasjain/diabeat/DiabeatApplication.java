package com.antasjain.diabeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DiabeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiabeatApplication.class, args);
	}

}
