package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.models.Curso;
import ejercicio.springboot.hibernate.repositorys.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoServiceImp implements CrudService<Curso, Long> {

    private final CursoRepository cursoRepository;

    // ===== OPERACIONES CRUD ===== //
    public CursoServiceImp(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Curso create(Curso prestamo) {
        return cursoRepository.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> list() {
        return cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Curso get(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Curso update(Curso prestamo) {
        if (cursoRepository.existsById(prestamo.getId())) {
            return cursoRepository.save(prestamo);
        }
        throw new RuntimeException("Préstamo no encontrado con ID: " + prestamo.getId());
    }

    @Override
    public boolean delete(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Préstamo no encontrado con ID: " + id);
        }
        return true;
    }

}
