package ejercicio.springboot.hibernate.controller;

import ejercicio.springboot.hibernate.models.Aula;
import ejercicio.springboot.hibernate.services.AulaServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    private final AulaServiceImp aulaService;

    public AulaController(AulaServiceImp aulaService) {
        this.aulaService = aulaService;
    }

    @GetMapping
    public ResponseEntity<List<Aula>> listarTodos() {
        List<Aula> aulas = aulaService.list();
        return ResponseEntity.ok(aulas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> obtenerPorId(@PathVariable Long id) {
        try {
            Aula resultado = aulaService.get(id);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Aula> crear(@RequestBody Aula aula) {
        Aula nueva = aulaService.create(aula);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aula> actualizar(@PathVariable Long id, @RequestBody Aula aula) {
        try {
            aula.setId(id);
            Aula actualizada = aulaService.update(aula);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = aulaService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
