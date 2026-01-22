package ejercicio.springboot.hibernate.repositorys;

import ejercicio.springboot.hibernate.models.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorialRepository extends JpaRepository<Editorial, Long> {
}