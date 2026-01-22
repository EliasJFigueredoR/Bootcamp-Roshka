package ejercicio.springboot.hibernate.controller;

import ejercicio.springboot.hibernate.models.ColegioProfesor;
import ejercicio.springboot.hibernate.services.ColegioProfesorServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colegio-profesores")
public class ColegioProfesorController {

    private final ColegioProfesorServiceImp colegioProfesorService;

    public ColegioProfesorController(ColegioProfesorServiceImp colegioProfesorService) {
        this.colegioProfesorService = colegioProfesorService;
    }

    @GetMapping
    public ResponseEntity<List<ColegioProfesor>> listarTodos() {
        List<ColegioProfesor> colegiosProfesores = colegioProfesorService.list();
        return ResponseEntity.ok(colegiosProfesores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColegioProfesor> obtenerPorId(@PathVariable Long id) {
        try {
            ColegioProfesor resultado = colegioProfesorService.get(id);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ColegioProfesor> crear(@RequestBody ColegioProfesor colegioProfesor) {
        ColegioProfesor nuevo = colegioProfesorService.create(colegioProfesor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColegioProfesor> actualizar(@PathVariable Long id, @RequestBody ColegioProfesor colegioProfesor) {
        try {
            colegioProfesor.setId(id);
            ColegioProfesor actualizado = colegioProfesorService.update(colegioProfesor);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = colegioProfesorService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
