package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface PrestamosLibroRepository extends JpaRepository<PrestamosLibro, BigDecimal> {
}