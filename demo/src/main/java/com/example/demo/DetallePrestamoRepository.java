package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePrestamoRepository extends JpaRepository<DetallePrestamo, DetallePrestamoId> {
}