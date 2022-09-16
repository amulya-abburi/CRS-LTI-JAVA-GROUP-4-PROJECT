package com.lti.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CrsSpringGroup04EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsSpringGroup04EurekaServerApplication.class, args);
	}

}
