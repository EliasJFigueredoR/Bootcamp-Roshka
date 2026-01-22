package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.models.AsignaturaHabilidad;
import ejercicio.springboot.hibernate.repositorys.AsignaturaHabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AsignaturaHabilidadServiceImp implements CrudService<AsignaturaHabilidad, Long> {
    

    private final AsignaturaHabilidadRepository asignaturaHabilidadRepository;

    // ===== OPERACIONES CRUD ===== //
    public AsignaturaHabilidadServiceImp(AsignaturaHabilidadRepository asignaturaHabilidadRepository) {
        this.asignaturaHabilidadRepository = asignaturaHabilidadRepository;
    }

    @Override
    public AsignaturaHabilidad create(AsignaturaHabilidad prestamo) {
        return asignaturaHabilidadRepository.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AsignaturaHabilidad> list() {
        return asignaturaHabilidadRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public AsignaturaHabilidad get(Long id) {
        return asignaturaHabilidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public AsignaturaHabilidad update(AsignaturaHabilidad prestamo) {
        if (asignaturaHabilidadRepository.existsById(prestamo.getId())) {
            return asignaturaHabilidadRepository.save(prestamo);
        }
        throw new RuntimeException("Préstamo no encontrado con ID: " + prestamo.getId());
    }

    @Override
    public boolean delete(Long id) {
        if (asignaturaHabilidadRepository.existsById(id)) {
            asignaturaHabilidadRepository.deleteById(id);
        } else {
            throw new RuntimeException("Préstamo no encontrado con ID: " + id);
        }
        return true;
    }

}
