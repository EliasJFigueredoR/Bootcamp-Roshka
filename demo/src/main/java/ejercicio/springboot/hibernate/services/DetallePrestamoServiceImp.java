package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.dto.request.DetallePrestamoRequestDto;
import ejercicio.springboot.hibernate.dto.request.DetallePrestamoRequestDtoUp;
import ejercicio.springboot.hibernate.dto.request.PrestamoLibroRequestDto;
import ejercicio.springboot.hibernate.dto.response.DetallePrestamoResponseDto;
import ejercicio.springboot.hibernate.dto.response.PrestamosLibroResponseDto;
import ejercicio.springboot.hibernate.models.*;
import ejercicio.springboot.hibernate.repositorys.DetallePrestamoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetallePrestamoServiceImp implements CrudService<DetallePrestamo, DetallePrestamoId>{

    private final DetallePrestamoRepository detallePrestamoRepository;

    // ===== OPERACIONES CRUD ===== //
    public DetallePrestamoServiceImp(DetallePrestamoRepository detallePrestamoRepository) {
        this.detallePrestamoRepository = detallePrestamoRepository;
    }

    @Override
    public DetallePrestamo create(DetallePrestamo prestamo) {
        return detallePrestamoRepository.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePrestamo> list() {
        return detallePrestamoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DetallePrestamo get(DetallePrestamoId id) {
        return detallePrestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public DetallePrestamo update(DetallePrestamo prestamo) {
        if (detallePrestamoRepository.existsById(prestamo.getId())) {
            return detallePrestamoRepository.save(prestamo);
        }
        throw new RuntimeException("Préstamo no encontrado con ID: " + prestamo.getId());
    }

    @Override
    public boolean delete(DetallePrestamoId id) {
        if (detallePrestamoRepository.existsById(id)) {
            detallePrestamoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Préstamo no encontrado con ID: " + id);
        }
        return true;
    }

    // === Crud con Dtos === //
    @Transactional
    public DetallePrestamoResponseDto crearDetallePrestamo (Long idPrestamo,
                                                            Long idLibro,
                                                            Long idEditorial,
                                                            DetallePrestamoRequestDtoUp requestDto)
    {
        PrestamosLibro prestamosLibro = new PrestamosLibro();
        prestamosLibro.setId(idPrestamo);

        Libro libro = new Libro();
        libro.setId(idLibro);

        Editorial editorial = new Editorial();
        editorial.setId(idEditorial);

        DetallePrestamo nuevaEntidad = new DetallePrestamo(
                prestamosLibro,
                libro,
                editorial,
                requestDto.getCantidad()
        );

        DetallePrestamo entidadGuardada = this.create(nuevaEntidad);

        return convertirADTO(entidadGuardada);
    }

    @Transactional
    public DetallePrestamoResponseDto ActualizarDetallePrestamo(Long idPrestamo,
                                                                Long idLibro,
                                                                Long idEditorial,
                                                                DetallePrestamoRequestDtoUp requestDto)
    {
        DetallePrestamoId id = new DetallePrestamoId(idPrestamo, idLibro, idEditorial);
        DetallePrestamo entidad = this.get(id);

        // Actualizar campos
        entidad.setCantidad(requestDto.getCantidad());

        // Guardar cambios
        DetallePrestamo entidadActualizada = this.update(entidad);

        return convertirADTO(entidadActualizada);
    }


    @Transactional(readOnly = true)
    public DetallePrestamoResponseDto obtenerPorId(Long idPrestamo, Long idLibro, Long idEditorial) {
        DetallePrestamoId id = new DetallePrestamoId(idPrestamo, idLibro, idEditorial);
        DetallePrestamo entidad = this.get(id);
        return new DetallePrestamoResponseDto(
                entidad.getIdPrestamos().getId(),
                entidad.getIdLibro().getId(),
                entidad.getIdEditorial().getId(),
                entidad.getIdLibro().getNombre(),
                entidad.getIdEditorial().getNombre(),
                entidad.getCantidad()
        );
    }

    @Transactional(readOnly = true)
    public List<DetallePrestamoResponseDto> listarTodos() {
        List<DetallePrestamo> entidades = this.list();
        List<DetallePrestamoResponseDto> dtos = new ArrayList<>();

        for (DetallePrestamo entidad : entidades) {
            dtos.add(convertirADTO(entidad));
        }
        return dtos;
    }

    // ==== MÉTODOS AUXILIARES DE CONVERSIÓN ==== //

    private DetallePrestamoResponseDto convertirADTO(DetallePrestamo entidad) {

        return new DetallePrestamoResponseDto(
                entidad.getIdPrestamos().getId(),
                entidad.getIdLibro().getId(),
                entidad.getIdEditorial().getId(),
                entidad.getIdLibro().getNombre(),
                entidad.getIdEditorial().getNombre(),
                entidad.getCantidad()
        );
    }
}
