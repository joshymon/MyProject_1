package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan("com.example") 
public class ImNotificationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImNotificationProjectApplication.class, args);
	}

}
