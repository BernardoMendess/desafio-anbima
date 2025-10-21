package com.desafio_backend.moduloA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.anbima.model")
public class ModuloAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuloAApplication.class, args);
	}

}
