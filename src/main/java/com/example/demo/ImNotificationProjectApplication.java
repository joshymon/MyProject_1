package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@EnableAsync
@Controller
@ComponentScan("com.example") 
@ServletComponentScan
public class ImNotificationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImNotificationProjectApplication.class, args);
	}

}
