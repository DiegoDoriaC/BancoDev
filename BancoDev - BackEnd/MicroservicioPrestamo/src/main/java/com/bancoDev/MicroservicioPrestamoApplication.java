package com.bancoDev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MicroservicioPrestamoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioPrestamoApplication.class, args);
	}

}
