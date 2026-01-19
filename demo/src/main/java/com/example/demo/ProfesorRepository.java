package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ProfesorRepository extends JpaRepository<Profesor, BigDecimal> {
}