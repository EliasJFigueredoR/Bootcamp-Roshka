package ejercicio.springboot.hibernate.controller;

import ejercicio.springboot.hibernate.models.DetallePrestamo;
import ejercicio.springboot.hibernate.models.DetallePrestamoId;
import ejercicio.springboot.hibernate.services.DetallePrestamoServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-prestamos")
public class DetallePrestamoController {

    private final DetallePrestamoServiceImp detallePrestamoService;

    public DetallePrestamoController(DetallePrestamoServiceImp detallePrestamoService) {
        this.detallePrestamoService = detallePrestamoService;
    }

    @GetMapping
    public ResponseEntity<List<DetallePrestamo>> listarTodos() {
        List<DetallePrestamo> detalles = detallePrestamoService.list();
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{idPrestamo}/{idLibro}/{idEditorial}")
    public ResponseEntity<DetallePrestamo> obtenerPorId(
            @PathVariable Long idPrestamo,
            @PathVariable Long idLibro,
            @PathVariable Long idEditorial) {
        try {
            DetallePrestamoId id = new DetallePrestamoId(idPrestamo, idLibro, idEditorial);
            DetallePrestamo resultado = detallePrestamoService.get(id);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DetallePrestamo> crear(@RequestBody DetallePrestamo detallePrestamo) {
        DetallePrestamo nuevo = detallePrestamoService.create(detallePrestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{idPrestamo}/{idLibro}/{idEditorial}")
    public ResponseEntity<DetallePrestamo> actualizar(
            @PathVariable Long idPrestamo,
            @PathVariable Long idLibro,
            @PathVariable Long idEditorial,
            @RequestBody DetallePrestamo detallePrestamo) {
        try {
            DetallePrestamoId id = new DetallePrestamoId(idPrestamo, idLibro, idEditorial);
            detallePrestamo.setId(id);
            DetallePrestamo actualizado = detallePrestamoService.update(detallePrestamo);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idPrestamo}/{idLibro}/{idEditorial}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long idPrestamo,
            @PathVariable Long idLibro,
            @PathVariable Long idEditorial) {
        try {
            DetallePrestamoId id = new DetallePrestamoId(idPrestamo, idLibro, idEditorial);
            boolean eliminado = detallePrestamoService.delete(id);
            if (eliminado) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
