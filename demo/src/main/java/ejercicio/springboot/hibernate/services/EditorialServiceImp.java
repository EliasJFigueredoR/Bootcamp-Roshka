package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.models.Editorial;
import ejercicio.springboot.hibernate.repositorys.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EditorialServiceImp implements CrudService<Editorial, Long> {

    private final EditorialRepository editorialRepository;

    // ===== OPERACIONES CRUD ===== //
    public EditorialServiceImp(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    @Override
    public Editorial create(Editorial prestamo) {
        return editorialRepository.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Editorial> list() {
        return editorialRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Editorial get(Long id) {
        return editorialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Editorial update(Editorial prestamo) {
        if (editorialRepository.existsById(prestamo.getId())) {
            return editorialRepository.save(prestamo);
        }
        throw new RuntimeException("Préstamo no encontrado con ID: " + prestamo.getId());
    }

    @Override
    public boolean delete(Long id) {
        if (editorialRepository.existsById(id)) {
            editorialRepository.deleteById(id);
        } else {
            throw new RuntimeException("Préstamo no encontrado con ID: " + id);
        }
        return true;
    }

}
