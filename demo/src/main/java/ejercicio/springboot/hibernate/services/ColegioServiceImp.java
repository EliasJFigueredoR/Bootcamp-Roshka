package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.dto.request.ColegioProfesorRequestDto;
import ejercicio.springboot.hibernate.dto.request.DetallePrestamoRequestDto;
import ejercicio.springboot.hibernate.dto.request.PrestamoLibroRequestDto;
import ejercicio.springboot.hibernate.dto.response.ColegioProfesorResponseDto;
import ejercicio.springboot.hibernate.dto.response.PrestamosLibroResponseDto;
import ejercicio.springboot.hibernate.models.*;
import ejercicio.springboot.hibernate.repositorys.ColegioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColegioServiceImp implements CrudService<Colegio, Long> {

    private final ColegioRepository colegioRepository;

    // ===== OPERACIONES CRUD ===== //
    public ColegioServiceImp(ColegioRepository colegioRepository) {
        this.colegioRepository = colegioRepository;
    }

    @Override
    public Colegio create(Colegio prestamo) {
        return colegioRepository.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Colegio> list() {
        return colegioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Colegio get(Long id) {
        return colegioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Colegio update(Colegio prestamo) {
        if (colegioRepository.existsById(prestamo.getId())) {
            return colegioRepository.save(prestamo);
        }
        throw new RuntimeException("Préstamo no encontrado con ID: " + prestamo.getId());
    }

    @Override
    public boolean delete(Long id) {
        if (colegioRepository.existsById(id)) {
            colegioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Préstamo no encontrado con ID: " + id);
        }
        return true;
    }

}
