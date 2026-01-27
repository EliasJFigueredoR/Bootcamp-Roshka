package ejercicio.springboot.hibernate.controller;

import ejercicio.springboot.hibernate.dto.request.PrestamoLibroRequestDto;
import ejercicio.springboot.hibernate.dto.response.PrestamosLibroResponseDto;
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
    public ResponseEntity<List<PrestamosLibroResponseDto>> listarTodos() {
        List<PrestamosLibroResponseDto> prestamos = prestamoService.listarTodos();
        return ResponseEntity.ok(prestamos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamosLibroResponseDto> obtenerPorId(@PathVariable Long id) {
        try {
            PrestamosLibroResponseDto resultado = prestamoService.obtenerPorId(id);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PrestamosLibroResponseDto> crear(@RequestBody PrestamoLibroRequestDto prestamo) {
        PrestamosLibroResponseDto nuevo = prestamoService.crearPrestamoDesdeDto(prestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestamosLibroResponseDto> actualizar(@PathVariable Long id, @RequestBody PrestamoLibroRequestDto prestamo) {
        try {
            PrestamosLibroResponseDto actualizado = prestamoService.actualizarPrestamoDesdeDto(id, prestamo);
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
