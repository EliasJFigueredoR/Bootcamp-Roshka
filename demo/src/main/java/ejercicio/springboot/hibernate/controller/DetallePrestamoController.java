package ejercicio.springboot.hibernate.controller;

import ejercicio.springboot.hibernate.dto.request.DetallePrestamoRequestDto;
import ejercicio.springboot.hibernate.dto.response.DetallePrestamoResponseDto;
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
    public ResponseEntity<List<DetallePrestamoResponseDto>> listarTodos() {
        List<DetallePrestamoResponseDto> detalles = detallePrestamoService.listarTodos();
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{idPrestamo}/{idLibro}/{idEditorial}")
    public ResponseEntity<DetallePrestamoResponseDto> obtenerPorId(
            @PathVariable Long idPrestamo,
            @PathVariable Long idLibro,
            @PathVariable Long idEditorial) {
        try {

            DetallePrestamoResponseDto detalleDto = detallePrestamoService
                    .obtenerPorId(idPrestamo, idLibro, idEditorial);

            return ResponseEntity.ok(detalleDto);
        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping("/agregar-a/{id}")
    public ResponseEntity<DetallePrestamoResponseDto> crear(@PathVariable Long id
                                                            ,@RequestBody DetallePrestamoRequestDto detallePrestamo)
    {
        DetallePrestamoResponseDto nuevo = detallePrestamoService.crearDetallePrestamo(id, detallePrestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{idPrestamo}/{idLibro}/{idEditorial}")
    public ResponseEntity<DetallePrestamoResponseDto> actualizar(
            @PathVariable Long idPrestamo,
            @PathVariable Long idLibro,
            @PathVariable Long idEditorial,
            @RequestBody DetallePrestamoRequestDto detallePrestamo) {
        try {
            DetallePrestamoResponseDto actualizado = detallePrestamoService
                    .ActualizarDetallePrestamo(idPrestamo,
                    idLibro,
                    idEditorial,
                    detallePrestamo);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/prestamo/{idPrestamo}/Detalle{idLibro}/{idEditorial}")
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
