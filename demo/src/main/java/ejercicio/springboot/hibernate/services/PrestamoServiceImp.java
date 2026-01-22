package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.models.PrestamosLibro;
import ejercicio.springboot.hibernate.repositorys.DetallePrestamoRepository;
import ejercicio.springboot.hibernate.repositorys.PrestamosLibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoServiceImp implements CrudService<PrestamosLibro, Long> {
    
    @Autowired
    private PrestamosLibroRepository prestamosLibroRepository;

    // ===== OPERACIONES CRUD ===== //
    public PrestamoServiceImp(PrestamosLibroRepository prestamosLibroRepository) {
        this.prestamosLibroRepository = prestamosLibroRepository;
    }

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

}
