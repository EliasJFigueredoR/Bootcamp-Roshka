package ejercicio.springboot.hibernate.repositorys;

import ejercicio.springboot.hibernate.models.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AulaRepository extends JpaRepository<Aula, Long> {
}