package ejercicio.springboot.hibernate.repositorys;

import ejercicio.springboot.hibernate.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}