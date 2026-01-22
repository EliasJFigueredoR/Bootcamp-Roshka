package ejercicio.springboot.hibernate.controller;

import ejercicio.springboot.hibernate.models.Colegio;
import ejercicio.springboot.hibernate.services.ColegioServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colegios")
public class ColegioController {

    private final ColegioServiceImp colegioService;

    public ColegioController(ColegioServiceImp colegioService) {
        this.colegioService = colegioService;
    }

    @GetMapping
    public ResponseEntity<List<Colegio>> listarTodos() {
        List<Colegio> colegios = colegioService.list();
        return ResponseEntity.ok(colegios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colegio> obtenerPorId(@PathVariable Long id) {
        try {
            Colegio resultado = colegioService.get(id);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Colegio> crear(@RequestBody Colegio colegio) {
        Colegio nuevo = colegioService.create(colegio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colegio> actualizar(@PathVariable Long id, @RequestBody Colegio colegio) {
        try {
            colegio.setId(id);
            Colegio actualizado = colegioService.update(colegio);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = colegioService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
