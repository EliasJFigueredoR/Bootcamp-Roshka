package ejercicio.springboot.hibernate.controller;

import ejercicio.springboot.hibernate.models.Profesor;
import ejercicio.springboot.hibernate.services.ProfesorServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorServiceImp profesorService;

    public ProfesorController(ProfesorServiceImp profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> listarTodos() {
        List<Profesor> profesores = profesorService.list();
        return ResponseEntity.ok(profesores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerPorId(@PathVariable Long id) {
        try {
            Profesor resultado = profesorService.get(id);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Profesor> crear(@RequestBody Profesor profesor) {
        Profesor nuevo = profesorService.create(profesor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizar(@PathVariable Long id, @RequestBody Profesor profesor) {
        try {
            profesor.setId(id);
            Profesor actualizado = profesorService.update(profesor);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = profesorService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
