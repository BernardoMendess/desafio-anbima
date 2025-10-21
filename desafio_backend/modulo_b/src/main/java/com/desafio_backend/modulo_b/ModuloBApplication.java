package com.desafio_backend.modulo_b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.anbima.model")
public class ModuloBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuloBApplication.class, args);
    }

}

