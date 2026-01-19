package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface AulaRepository extends JpaRepository<Aula, BigDecimal> {
}