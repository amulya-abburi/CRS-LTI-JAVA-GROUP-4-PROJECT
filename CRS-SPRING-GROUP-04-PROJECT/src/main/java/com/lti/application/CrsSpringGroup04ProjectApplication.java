package com.lti.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.lti.*"})
public class CrsSpringGroup04ProjectApplication {

	//comment added by rohit
	// second commit added by rohit
	public static void main(String[] args) {
		SpringApplication.run(CrsSpringGroup04ProjectApplication.class, args);
	}

}
