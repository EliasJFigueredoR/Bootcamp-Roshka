package ejercicio.springboot.hibernate.repositorys;

import ejercicio.springboot.hibernate.models.DetallePrestamo;
import ejercicio.springboot.hibernate.models.DetallePrestamoId;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.Optional;

public interface DetallePrestamoRepository extends JpaRepository<DetallePrestamo, DetallePrestamoId> {

    @NullMarked
    @EntityGraph(attributePaths = {"idLibro", "idEditorial"})
    Optional<DetallePrestamo> findById(DetallePrestamoId id);

    @NullMarked
    @EntityGraph(attributePaths = {"idLibro", "idEditorial"})
    List<DetallePrestamo> findAll();

}