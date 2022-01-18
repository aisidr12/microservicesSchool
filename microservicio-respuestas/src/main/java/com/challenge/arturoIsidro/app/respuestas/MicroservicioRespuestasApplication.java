package com.challenge.arturoIsidro.app.respuestas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.challenge.arturoIsidro.app.respuestas.models.entity",
"com.challenge.arturoIsidro.commons.alumnos.models.entity",
"com.challenge.arturoIsidro.commons.examenes.models.entity"
	
})
public class MicroservicioRespuestasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioRespuestasApplication.class, args);
	}

}
