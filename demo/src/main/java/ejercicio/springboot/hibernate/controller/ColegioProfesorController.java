package ejercicio.springboot.hibernate.controller;

import ejercicio.springboot.hibernate.dto.request.ColegioProfesorRequestDto;
import ejercicio.springboot.hibernate.dto.response.ColegioProfesorResponseDto;
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
    public ResponseEntity<List<ColegioProfesorResponseDto>> listarTodos() {
        List<ColegioProfesorResponseDto> colegiosProfesores = colegioProfesorService.listarTodos();
        return ResponseEntity.ok(colegiosProfesores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColegioProfesorResponseDto> obtenerPorId(@PathVariable Long id) {
        try {
            ColegioProfesorResponseDto resultado = colegioProfesorService.obtenerPorId(id);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ColegioProfesorResponseDto> crear(@RequestBody ColegioProfesorRequestDto colegioProfesor) {
        ColegioProfesorResponseDto nuevo = colegioProfesorService.CrearColegioProfesorDto(colegioProfesor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColegioProfesorResponseDto> actualizar(@PathVariable Long id, @RequestBody ColegioProfesorRequestDto colegioProfesor) {
        try {
            ColegioProfesorResponseDto actualizado = colegioProfesorService.actualizarColegioProfesorDto(id, colegioProfesor);
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
