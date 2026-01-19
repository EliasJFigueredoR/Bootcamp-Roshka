package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner probarCosas(ColegioRepository colegioRepo) {
        return args -> {
            System.out.println("--- Iniciando Pruebas de Persistencia ---");

            BigDecimal idBuscado = new BigDecimal("1");

            Colegio colegioEncontrado = colegioRepo.findById(idBuscado).orElse(null);

            if (colegioEncontrado != null) {
                System.out.println("¡Éxito! Colegio encontrado:");
                System.out.println("Nombre: " + colegioEncontrado.getNombre());
                System.out.println("ID: " + colegioEncontrado.getId());
            } else {
                System.err.println("Error: No se encontró ningún colegio con el ID " + idBuscado);
            }
        };
    }
}
