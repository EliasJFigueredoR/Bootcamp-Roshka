package ejercicio.springboot.hibernate.controller;

import ejercicio.springboot.hibernate.models.AsignaturaHabilidad;
import ejercicio.springboot.hibernate.services.AsignaturaHabilidadServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaHabilidadController {

    private final AsignaturaHabilidadServiceImp asignaturaService;

    public AsignaturaHabilidadController(AsignaturaHabilidadServiceImp asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @GetMapping
    public ResponseEntity<List<AsignaturaHabilidad>> listarTodos() {
        List<AsignaturaHabilidad> asignaturas = asignaturaService.list();
        return ResponseEntity.ok(asignaturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaHabilidad> obtenerPorId(@PathVariable Long id) {
        try {
            AsignaturaHabilidad resultado = asignaturaService.get(id);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AsignaturaHabilidad> crear(@RequestBody AsignaturaHabilidad asignatura) {
        AsignaturaHabilidad nueva = asignaturaService.create(asignatura);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaHabilidad> actualizar(@PathVariable Long id, @RequestBody AsignaturaHabilidad asignatura) {
        try {
            asignatura.setId(id);
            AsignaturaHabilidad actualizada = asignaturaService.update(asignatura);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = asignaturaService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
