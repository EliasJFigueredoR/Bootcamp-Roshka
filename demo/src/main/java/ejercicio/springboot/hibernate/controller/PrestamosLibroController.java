package ejercicio.springboot.hibernate.controller;

import ejercicio.springboot.hibernate.models.PrestamosLibro;
import ejercicio.springboot.hibernate.services.PrestamoServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamosLibroController {

    private final PrestamoServiceImp prestamoService;

    public PrestamosLibroController(PrestamoServiceImp prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public ResponseEntity<List<PrestamosLibro>> listarTodos() {
        List<PrestamosLibro> prestamos = prestamoService.list();
        return ResponseEntity.ok(prestamos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamosLibro> obtenerPorId(@PathVariable Long id) {
        try {
            PrestamosLibro resultado = prestamoService.get(id);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PrestamosLibro> crear(@RequestBody PrestamosLibro prestamo) {
        PrestamosLibro nuevo = prestamoService.create(prestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestamosLibro> actualizar(@PathVariable Long id, @RequestBody PrestamosLibro prestamo) {
        try {
            prestamo.setId(id);
            PrestamosLibro actualizado = prestamoService.update(prestamo);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = prestamoService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
