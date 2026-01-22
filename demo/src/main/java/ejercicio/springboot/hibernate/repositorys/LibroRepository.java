package ejercicio.springboot.hibernate.repositorys;

import ejercicio.springboot.hibernate.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}