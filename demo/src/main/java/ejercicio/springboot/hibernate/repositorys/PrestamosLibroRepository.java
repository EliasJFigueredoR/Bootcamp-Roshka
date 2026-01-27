package ejercicio.springboot.hibernate.repositorys;

import ejercicio.springboot.hibernate.models.ColegioProfesor;
import ejercicio.springboot.hibernate.models.PrestamosLibro;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PrestamosLibroRepository extends JpaRepository<PrestamosLibro, Long> {

    @NullMarked
    @EntityGraph(attributePaths = {"idColegioProfesor",
            "idColegioProfesor.idColegio",
            "idColegioProfesor.idProfesor",
            "idCurso",
            "idAsignatura",
            "idAula",
            "detalles",
            "detalles.idLibro",
            "detalles.idEditorial"
    })
    Optional<PrestamosLibro> findById(long id);

    @NullMarked
    @EntityGraph(attributePaths = {"idColegioProfesor",
            "idColegioProfesor.idColegio",
            "idColegioProfesor.idProfesor",
            "idCurso",
            "idAsignatura",
            "idAula",
            "detalles",
            "detalles.idLibro",
            "detalles.idEditorial"
    })
    List<PrestamosLibro> findAll();

}

