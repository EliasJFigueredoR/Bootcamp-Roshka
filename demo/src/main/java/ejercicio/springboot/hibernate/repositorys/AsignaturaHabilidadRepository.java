package ejercicio.springboot.hibernate.repositorys;

import ejercicio.springboot.hibernate.models.AsignaturaHabilidad;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AsignaturaHabilidadRepository extends JpaRepository<AsignaturaHabilidad, Long> {
}