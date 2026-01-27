package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.dto.request.DetallePrestamoRequestDto;
import ejercicio.springboot.hibernate.dto.request.PrestamoLibroRequestDto;
import ejercicio.springboot.hibernate.dto.response.DetallePrestamoResponseDto;
import ejercicio.springboot.hibernate.dto.response.PrestamosLibroResponseDto;
import ejercicio.springboot.hibernate.models.DetallePrestamo;
import ejercicio.springboot.hibernate.models.Editorial;
import ejercicio.springboot.hibernate.models.Libro;
import ejercicio.springboot.hibernate.models.PrestamosLibro;
import ejercicio.springboot.hibernate.repositorys.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrestamoServiceImp implements CrudService<PrestamosLibro, Long> {
    
    // ===== REPOSITORIOS ===== //
    private final PrestamosLibroRepository prestamosLibroRepository;
    private final AsignaturaHabilidadRepository asignaturaHabilidadRepository;
    private final AulaRepository aulaRepository;
    private final ColegioProfesorRepository colegioProfesorRepository;
    private final CursoRepository cursoRepository;
    private final EditorialRepository editorialRepository;
    private final LibroRepository libroRepository;


    public PrestamoServiceImp(PrestamosLibroRepository prestamosLibroRepository, AsignaturaHabilidadRepository asignaturaHabilidadRepository, AulaRepository aulaRepository, ColegioProfesorRepository colegioProfesorRepository, CursoRepository cursoRepository, DetallePrestamoRepository detallePrestamoRepository, EditorialRepository editorialRepository, LibroRepository libroRepository) {
        this.prestamosLibroRepository = prestamosLibroRepository;
        this.asignaturaHabilidadRepository = asignaturaHabilidadRepository;
        this.aulaRepository = aulaRepository;
        this.colegioProfesorRepository = colegioProfesorRepository;
        this.cursoRepository = cursoRepository;
        this.editorialRepository = editorialRepository;
        this.libroRepository = libroRepository;
    }

    // ===== OPERACIONES CRUD ===== //

    @Override
    public PrestamosLibro create(PrestamosLibro prestamo) {
        return prestamosLibroRepository.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrestamosLibro> list() {
        return prestamosLibroRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PrestamosLibro get(Long id) {
        return prestamosLibroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public PrestamosLibro update(PrestamosLibro prestamo) {
        if (prestamosLibroRepository.existsById(prestamo.getId())) {
            return prestamosLibroRepository.save(prestamo);
        }
        throw new RuntimeException("Préstamo no encontrado con ID: " + prestamo.getId());
    }

    @Override
    public boolean delete(Long id) {
        if (prestamosLibroRepository.existsById(id)) {
            prestamosLibroRepository.deleteById(id);
        } else {
            throw new RuntimeException("Préstamo no encontrado con ID: " + id);
        }
        return true;
    }

    // ==== CRUD DTOS ==== //
    @Transactional
    public PrestamosLibroResponseDto crearPrestamoDesdeDto(PrestamoLibroRequestDto request) {

        // 1. CREAR ENTIDAD CABECERA
        PrestamosLibro prestamo = new PrestamosLibro();
        prestamo.setFechaPestamo(LocalDate.now());

        prestamo.setIdColegioProfesor(colegioProfesorRepository.findById(request.getIdColegioProfesor())
                .orElseThrow(() -> new RuntimeException("ColegioProfesor no encontrado")));

        prestamo.setIdCurso(cursoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado")));

        prestamo.setIdAsignatura(asignaturaHabilidadRepository.findById(request.getIdAsignatura())
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada")));

        prestamo.setIdAula(aulaRepository.findById(request.getIdAula())
                .orElseThrow(() -> new RuntimeException("Aula no encontrada")));

        // 2. GUARDAR CABECERA (Para generar el ID de Préstamo)
        PrestamosLibro prestamoGuardado = prestamosLibroRepository.save(prestamo);

        // 3. PROCESAR DETALLES
        List<DetallePrestamo> listaDetalles = new ArrayList<>();

        for (DetallePrestamoRequestDto detReq : request.getDetalles()) {
            // Buscar Libro y Editorial
            Libro libro = libroRepository.findById(detReq.getIdLibro())
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

            Editorial editorial = editorialRepository.findById(detReq.getIdEditorial())
                    .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));

            // Crear entidad Detalle
            DetallePrestamo detalle = new DetallePrestamo(
                    prestamoGuardado, // Enlazamos con el padre guardado
                    libro,
                    editorial,
                    detReq.getCantidad()
            );
            listaDetalles.add(detalle);
        }

        // Tenemos cascadeType.all, los hijos se guardarán al guardar la cabecera
        // Como estamos en una transacción, todos los cambios se guardarán al retornar.

        prestamoGuardado.setDetalles(listaDetalles);

        return convertirADTO(prestamoGuardado);
    }

    @Transactional
    public PrestamosLibroResponseDto actualizarPrestamoDesdeDto(Long id, PrestamoLibroRequestDto request) {
        PrestamosLibro prestamoExistente = this.get(id);

        prestamoExistente.setIdColegioProfesor(colegioProfesorRepository.findById(request.getIdColegioProfesor())
                .orElseThrow(() -> new RuntimeException("ColegioProfesor no encontrado")));

        prestamoExistente.setIdCurso(cursoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado")));

        prestamoExistente.setIdAsignatura(asignaturaHabilidadRepository.findById(request.getIdAsignatura())
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada")));

        prestamoExistente.setIdAula(aulaRepository.findById(request.getIdAula())
                .orElseThrow(() -> new RuntimeException("Aula no encontrada")));

        //No hace falta hacer el update, hibernate ya lo dectará al estar en una transacción
        return convertirADTO(prestamoExistente);
    }


    @Transactional(readOnly = true)
    public PrestamosLibroResponseDto obtenerPorId(Long id) {
        PrestamosLibro entidad = this.get(id);
        return convertirADTO(entidad);
    }

    @Transactional(readOnly = true)
    public List<PrestamosLibroResponseDto> listarTodos() {
        List<PrestamosLibro> entidades = prestamosLibroRepository.findAll();

        // Convertimos la lista de Entidades a lista de DTOs
        return entidades.stream()
                .map(this::convertirADTO)
                .toList();
    }

    // ==== MÉTODOS AUXILIARES DE CONVERSIÓN ==== //

    private PrestamosLibroResponseDto convertirADTO(PrestamosLibro entidad) {

        List<DetallePrestamoResponseDto> detallesDTO = entidad.getDetalles().stream()
                .map(detalle -> new DetallePrestamoResponseDto(
                        detalle.getIdPrestamos().getId(),
                        detalle.getIdLibro().getId(),
                        detalle.getIdEditorial().getId(),
                        detalle.getIdLibro().getNombre(),
                        detalle.getIdEditorial().getNombre(),
                        detalle.getCantidad()
                ))
                .toList();

        return new PrestamosLibroResponseDto(
                entidad.getId(),
                entidad.getFechaPestamo(),
                entidad.getIdColegioProfesor().getId(),
                entidad.getIdColegioProfesor().getIdColegio().getId(),
                entidad.getIdColegioProfesor().getIdColegio().getNombre(),
                entidad.getIdColegioProfesor().getIdProfesor().getId(),
                entidad.getIdColegioProfesor().getIdProfesor().getNombre(),
                entidad.getIdCurso().getId(),
                entidad.getIdCurso().getNombre(),
                entidad.getIdAsignatura().getId(),
                entidad.getIdAsignatura().getNombre(),
                entidad.getIdAula().getId(),
                entidad.getIdAula().getNombre(),
                detallesDTO
        );
    }

}
