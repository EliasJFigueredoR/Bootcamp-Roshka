package ejercicio.springboot.hibernate.dto.request;

import java.io.Serializable;

/**
 * DTO for {@link ejercicio.springboot.hibernate.models.ColegioProfesor}
 */
public record ColegioProfesorRequestDto(long idProfesor, long idColegio) implements Serializable {
}