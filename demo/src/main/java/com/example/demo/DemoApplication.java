package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner probarCosas(ColegioRepository colegioRepo) {
        return args -> {
            System.out.println("--- Iniciando Pruebas de Persistencia ---");

            Colegio colegioEncontrado = colegioRepo.findById(1L).orElse(null);

            if (colegioEncontrado != null) {
                System.out.println("¡Éxito! Colegio encontrado:");
                System.out.println("Nombre: " + colegioEncontrado.getNombre());
                System.out.println("ID: " + colegioEncontrado.getId());
            } else {
                System.err.println("Error: No se encontró ningún colegio con el ID " + 1);
            }
        };
    }
}
