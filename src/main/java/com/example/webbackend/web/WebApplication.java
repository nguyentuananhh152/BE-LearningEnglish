package com.example.webbackend.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

//	public static void test() {
//		for (int i = 1; i < 101; i++) {
//			System.out.println("('" + i + "','text Enghlish " + i + "','text VN " + i + "'),");
//		}
//	}


}