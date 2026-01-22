package ejercicio.springboot.hibernate.repositorys;

import ejercicio.springboot.hibernate.models.Colegio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColegioRepository extends JpaRepository<Colegio, Long> {
}