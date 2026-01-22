package ejercicio.springboot.hibernate.controller;

import ejercicio.springboot.hibernate.models.Editorial;
import ejercicio.springboot.hibernate.services.EditorialServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoriales")
public class EditorialController {

    private final EditorialServiceImp editorialService;

    public EditorialController(EditorialServiceImp editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping
    public ResponseEntity<List<Editorial>> listarTodos() {
        List<Editorial> editoriales = editorialService.list();
        return ResponseEntity.ok(editoriales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editorial> obtenerPorId(@PathVariable Long id) {
        try {
            Editorial resultado = editorialService.get(id);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Editorial> crear(@RequestBody Editorial editorial) {
        Editorial nueva = editorialService.create(editorial);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editorial> actualizar(@PathVariable Long id, @RequestBody Editorial editorial) {
        try {
            editorial.setId(id);
            Editorial actualizada = editorialService.update(editorial);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = editorialService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
