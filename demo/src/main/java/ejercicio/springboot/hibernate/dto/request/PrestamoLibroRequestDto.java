package ejercicio.springboot.hibernate.dto.request;

import java.time.LocalDate;
import java.util.List;

public class PrestamoLibroRequestDto {
    private long idColegioProfesor;

    private long idAsignatura;

    private long  idAula;

    private long idCurso;

    private List<DetallePrestamoRequestDto> detalles;

    public PrestamoLibroRequestDto (
                                     long idColegioProfesor,
                                     long idAsignatura,
                                     long idAula,
                                     long idCurso,
                                     List<DetallePrestamoRequestDto> detalles) {
        this.idColegioProfesor = idColegioProfesor;
        this.idAsignatura = idAsignatura;
        this.idAula = idAula;
        this.idCurso = idCurso;
        this.detalles = detalles;
    }


    public long getIdColegioProfesor() {
        return idColegioProfesor;
    }

    public void setIdColegioProfesor(long idColegioProfesor) {
        this.idColegioProfesor = idColegioProfesor;
    }

    public long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public long getIdAula() {
        return idAula;
    }

    public void setIdAula(long string) {
        this.idAula = string;
    }

    public List<DetallePrestamoRequestDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePrestamoRequestDto> detalles) {
        this.detalles = detalles;
    }

    public long getIdCurso() {
        return idCurso;
    }
    public void setIdCurso(long idCurso) {
        this.idCurso = idCurso;
    }
}
