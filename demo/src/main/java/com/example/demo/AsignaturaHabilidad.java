package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "asignatura_habilidad", schema = "ejercicio5")
public class AsignaturaHabilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignatura", nullable = false)
    private long id;

    @Column(name = "nombre", nullable = false, length = Integer.MAX_VALUE)
    private String nombre;

    public AsignaturaHabilidad() {
    }

    public AsignaturaHabilidad(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}