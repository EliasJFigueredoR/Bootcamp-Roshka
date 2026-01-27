package ejercicio.springboot.hibernate.dto.response;

import java.io.Serializable;

/**
 * DTO for {@link ejercicio.springboot.hibernate.models.ColegioProfesor}
 */
public record ColegioProfesorResponseDto(long id, long idProfesor, String NombreProfesor, long idColegio,
                                         String NombreColegio) implements Serializable {
}