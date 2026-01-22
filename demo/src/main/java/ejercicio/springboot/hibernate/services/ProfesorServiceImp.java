package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.models.Profesor;
import ejercicio.springboot.hibernate.repositorys.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfesorServiceImp implements CrudService<Profesor, Long> {

    private final ProfesorRepository profesorRepository;

    // ===== OPERACIONES CRUD ===== //
    public ProfesorServiceImp(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    @Override
    public Profesor create(Profesor prestamo) {
        return profesorRepository.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Profesor> list() {
        return profesorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Profesor get(Long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Profesor update(Profesor prestamo) {
        if (profesorRepository.existsById(prestamo.getId())) {
            return profesorRepository.save(prestamo);
        }
        throw new RuntimeException("Préstamo no encontrado con ID: " + prestamo.getId());
    }

    @Override
    public boolean delete(Long id) {
        if (profesorRepository.existsById(id)) {
            profesorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Préstamo no encontrado con ID: " + id);
        }
        return true;
    }

}
