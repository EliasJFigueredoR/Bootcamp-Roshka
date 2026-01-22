package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.models.Libro;
import ejercicio.springboot.hibernate.repositorys.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibroServiceImp implements CrudService<Libro, Long> {

    private final LibroRepository libroRepository;

    // ===== OPERACIONES CRUD ===== //
    public LibroServiceImp(LibroRepository LibroRepository) {
        this.libroRepository = LibroRepository;
    }

    @Override
    public Libro create(Libro prestamo) {
        return libroRepository.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> list() {
        return libroRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Libro get(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Libro update(Libro prestamo) {
        if (libroRepository.existsById(prestamo.getId())) {
            return libroRepository.save(prestamo);
        }
        throw new RuntimeException("Préstamo no encontrado con ID: " + prestamo.getId());
    }

    @Override
    public boolean delete(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
        } else {
            throw new RuntimeException("Préstamo no encontrado con ID: " + id);
        }
        return true;
    }

}
