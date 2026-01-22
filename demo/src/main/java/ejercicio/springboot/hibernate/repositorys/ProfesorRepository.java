package ejercicio.springboot.hibernate.repositorys;

import ejercicio.springboot.hibernate.models.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}