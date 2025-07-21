package com.webapp.quizapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class QuizService1Application {

	public static void main(String[] args) {
		SpringApplication.run(QuizService1Application.class, args);
	}

}
