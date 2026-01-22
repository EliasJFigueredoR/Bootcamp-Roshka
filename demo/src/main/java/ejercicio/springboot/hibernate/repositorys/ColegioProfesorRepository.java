package ejercicio.springboot.hibernate.repositorys;

import ejercicio.springboot.hibernate.models.ColegioProfesor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ColegioProfesorRepository extends JpaRepository<ColegioProfesor, Long> {

    @EntityGraph(attributePaths = {"idProfesor", "idColegio"})
    Optional<ColegioProfesor> findById(long id);

}