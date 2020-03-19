package com.shanemartin.codetest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CodetestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodetestApplication.class, args);
	}

	// How to Run when application starts up
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Application Started");
		};
	}

}
