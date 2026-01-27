package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.dto.request.ColegioProfesorRequestDto;
import ejercicio.springboot.hibernate.dto.response.ColegioProfesorResponseDto;
import ejercicio.springboot.hibernate.models.ColegioProfesor;
import ejercicio.springboot.hibernate.repositorys.ColegioProfesorRepository;
import ejercicio.springboot.hibernate.repositorys.ColegioRepository;
import ejercicio.springboot.hibernate.repositorys.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColegioProfesorServiceImp implements CrudService<ColegioProfesor, Long> {

    private final ColegioProfesorRepository colegioProfesorRepository;
    private final ColegioRepository colegioRepository;
    private final ProfesorRepository profesorRepository;

    // ===== OPERACIONES CRUD ===== //
    public ColegioProfesorServiceImp(ColegioProfesorRepository colegioProfesorRepository, ColegioRepository colegioRepository, ProfesorRepository profesorRepository) {
        this.colegioProfesorRepository = colegioProfesorRepository;
        this.colegioRepository = colegioRepository;
        this.profesorRepository = profesorRepository;
    }

    @Override
    public ColegioProfesor create(ColegioProfesor prestamo) {
        return colegioProfesorRepository.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ColegioProfesor> list() {
        return colegioProfesorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ColegioProfesor get(Long id) {
        return colegioProfesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public ColegioProfesor update(ColegioProfesor prestamo) {
        if (colegioProfesorRepository.existsById(prestamo.getId())) {
            return colegioProfesorRepository.save(prestamo);
        }
        throw new RuntimeException("Préstamo no encontrado con ID: " + prestamo.getId());
    }

    @Override
    public boolean delete(Long id) {
        if (colegioProfesorRepository.existsById(id)) {
            colegioProfesorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Préstamo no encontrado con ID: " + id);
        }
        return true;
    }

    // === ColegioDTO Crud === //
    @Transactional
    public ColegioProfesorResponseDto CrearColegioProfesorDto(ColegioProfesorRequestDto request)
    {
        ColegioProfesor entidad = new ColegioProfesor();
        entidad.setIdColegio(colegioRepository.findById(request.idColegio())
                .orElseThrow(() -> new RuntimeException("Colegio no encontrado")));

        entidad.setIdProfesor(profesorRepository.findById(request.idProfesor())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado")));

        ColegioProfesor entidadCreada = colegioProfesorRepository.save(entidad);
        return convertirADTO(entidadCreada);
    }

    @Transactional
    public ColegioProfesorResponseDto actualizarColegioProfesorDto(Long id, ColegioProfesorRequestDto request) {
        ColegioProfesor entidad = this.get(id);
        entidad.setIdColegio(colegioRepository.findById(request.idColegio())
                .orElseThrow(() -> new RuntimeException("ColegioProfesor no encontrado")));

        entidad.setIdProfesor(profesorRepository.findById(request.idProfesor())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado")));
        return convertirADTO(entidad);
    }

    @Transactional(readOnly = true)
    public ColegioProfesorResponseDto obtenerPorId(Long id) {
        ColegioProfesor entidad = this.get(id);
        return convertirADTO(entidad);
    }

    @Transactional(readOnly = true)
    public List<ColegioProfesorResponseDto> listarTodos() {
        List<ColegioProfesor> entidades = colegioProfesorRepository.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .toList();
    }

    // ==== MÉTODOS AUXILIARES DE CONVERSIÓN ==== //
    private ColegioProfesorResponseDto convertirADTO(ColegioProfesor entidad) {
        return new ColegioProfesorResponseDto(
                entidad.getId(),
                entidad.getIdProfesor().getId(),
                entidad.getIdProfesor().getNombre(),
                entidad.getIdColegio().getId(),
                entidad.getIdColegio().getNombre()
        );
    }

}
