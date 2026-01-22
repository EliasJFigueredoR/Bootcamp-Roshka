package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.models.Aula;
import ejercicio.springboot.hibernate.repositorys.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AulaServiceImp implements CrudService<Aula, Long> {
    

    private final AulaRepository aulaRepository;

    // ===== OPERACIONES CRUD ===== //
    public AulaServiceImp(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    @Override
    public Aula create(Aula prestamo) {
        return aulaRepository.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Aula> list() {
        return aulaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Aula get(Long id) {
        return aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Aula update(Aula prestamo) {
        if (aulaRepository.existsById(prestamo.getId())) {
            return aulaRepository.save(prestamo);
        }
        throw new RuntimeException("Préstamo no encontrado con ID: " + prestamo.getId());
    }

    @Override
    public boolean delete(Long id) {
        if (aulaRepository.existsById(id)) {
            aulaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Préstamo no encontrado con ID: " + id);
        }
        return true;
    }

}
