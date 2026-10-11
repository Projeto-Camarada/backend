package com.santoflores.camarada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class CamaradaApplication {

	public static void main(String[] args) {
	
		Dotenv.configure()
			.systemProperties()
			.load();
		SpringApplication.run(CamaradaApplication.class, args);
	}

}
