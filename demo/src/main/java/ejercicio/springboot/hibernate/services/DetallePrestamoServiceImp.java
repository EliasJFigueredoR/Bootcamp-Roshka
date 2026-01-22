package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.models.DetallePrestamo;
import ejercicio.springboot.hibernate.models.DetallePrestamoId;
import ejercicio.springboot.hibernate.repositorys.DetallePrestamoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
