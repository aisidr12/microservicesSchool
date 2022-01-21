package com.challenge.arturoIsidro.app.subscripcion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroservicioSubscripcionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioSubscripcionApplication.class, args);
	}

}
