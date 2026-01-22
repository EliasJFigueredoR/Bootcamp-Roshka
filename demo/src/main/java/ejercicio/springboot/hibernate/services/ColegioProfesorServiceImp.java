package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.models.ColegioProfesor;
import ejercicio.springboot.hibernate.repositorys.ColegioProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColegioProfesorServiceImp implements CrudService<ColegioProfesor, Long> {

    private final ColegioProfesorRepository colegioProfesorRepository;

    // ===== OPERACIONES CRUD ===== //
    public ColegioProfesorServiceImp(ColegioProfesorRepository colegioProfesorRepository) {
        this.colegioProfesorRepository = colegioProfesorRepository;
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

}
