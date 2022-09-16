package com.lti.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
@AutoConfiguration
@ComponentScan({"com.lti.*"})
@EnableEurekaClient
public class CrsSpringGroup04StudentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsSpringGroup04StudentMicroserviceApplication.class, args);
	}

}
