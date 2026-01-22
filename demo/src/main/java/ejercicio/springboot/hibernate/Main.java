package ejercicio.springboot.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ejercicio.springboot.hibernate.repositorys.*;
import ejercicio.springboot.hibernate.models.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner aplicacionPrincipal(
            ColegioRepository colegioRepo,
            AulaRepository aulaRepo,
            ProfesorRepository profesorRepo,
            ColegioProfesorRepository colProfRepo,
            CursoRepository cursoRepo,
            LibroRepository libroRepo,
            EditorialRepository editorialRepo,
            PrestamosLibroRepository prestamoRepo,
            DetallePrestamoRepository detalleRepo,
            AsignaturaHabilidadRepository asignaturaRepo
    )
    {
        return args -> {
            System.out.println("====== INICIO DE CARGA DE DATOS DE PRUEBA ======");

            // PASO 1: Vamos a crear y mostrar los datos de prueba.

            Editorial editorial = new Editorial();
            editorial.setNombre("Editorial Planeta");
            editorial = editorialRepo.save(editorial);
            System.out.println(editorial);


            Libro libro = new Libro();
            libro.setNombre("Aprendiendo Spring Boot");
            libro.setCantidad(5);
            libro = libroRepo.save(libro);
            System.out.println(libro);

            Colegio colegio = new Colegio();
            colegio.setNombre("Colegio San José");
            colegio = colegioRepo.save(colegio);
            System.out.println(colegio);

            Profesor profesor = new Profesor();
            profesor.setNombre("Juan");
            profesor = profesorRepo.save(profesor);
            System.out.println(profesor);

            AsignaturaHabilidad asignatura = new AsignaturaHabilidad();
            asignatura.setNombre("Matemáticas");
            asignatura = asignaturaRepo.save(asignatura);
            System.out.println(asignatura);

            Aula aula = new Aula();
            aula.setNombre("Aula 101");
            aula = aulaRepo.save(aula);
            System.out.println(aula);

            Curso curso = new Curso();
            curso.setNombre("Curso de Primaria");
            curso = cursoRepo.save(curso);
            System.out.println(curso);

            ColegioProfesor colegioProfesor = new ColegioProfesor();
            colegioProfesor.setIdColegio(colegio);
            colegioProfesor.setIdProfesor(profesor);
            colegioProfesor = colProfRepo.save(colegioProfesor);
            System.out.println(colegioProfesor);

            PrestamosLibro prestamo = new PrestamosLibro();
            prestamo.setFechaPestamo(java.time.LocalDate.now());
            prestamo.setIdColegioProfesor(colegioProfesor);
            prestamo.setIdCurso(curso);
            prestamo.setIdAsignatura(asignatura);
            prestamo.setIdAula(aula);
            prestamo = prestamoRepo.save(prestamo);

            DetallePrestamoId detalleId = new DetallePrestamoId(prestamo.getId(), libro.getId(), editorial.getId());

            DetallePrestamo detallePrestamo = new DetallePrestamo();
            detallePrestamo.setId(detalleId);
            detallePrestamo.setIdPrestamos(prestamo);
            detallePrestamo.setIdLibro(libro);
            detallePrestamo.setIdEditorial(editorial);
            detallePrestamo.setCantidad(2);
            detallePrestamo = detalleRepo.save(detallePrestamo);

            System.out.println(prestamo);


        };
    }
}

