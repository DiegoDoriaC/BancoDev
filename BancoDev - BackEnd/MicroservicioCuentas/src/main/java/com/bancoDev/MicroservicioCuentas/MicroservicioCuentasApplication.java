package com.bancoDev.MicroservicioCuentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioCuentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioCuentasApplication.class, args);
	}

}
