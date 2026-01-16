package Ejercicio5_Crud;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("\nPrueba de carga de datos y listado\n");

        // Cargamos datos catalago
        Asignatura_Habilidad AH = new Asignatura_Habilidad("Algoritmos");
        AH.guardar();

        Aula Al = new Aula("3.C01");
        Al.guardar();

        Colegio Co = new Colegio("Colegio");
        Co.guardar();

        Profesor Pf = new Profesor("Estrella Galeano");
        Pf.guardar();

        Libro Lb = new Libro("Don Quijote", 3);
        Lb.guardar();

        Editorial Ed = Editorial.buscarPorId(1);
        Curso Cu = Curso.buscarPorId(1);

        //Creamos relaciones
        Colegio_Profesor CP = new Colegio_Profesor(Pf.getId_profesor(), Co.getId_colegio());
        CP.guardar();

        //Creamos los datos para el prestamo
        LocalDate fechaLocal = LocalDate.now();
        Date fecha = Date.valueOf(fechaLocal);
        Prestamos_Libros Plib = new Prestamos_Libros(fecha, CP.getId_colegio_profesor(),Cu.getId_curso(),
               AH.getId_asignatura(), Al.getId_aula() );
        Plib.guardar();

        Detalle_Prestamos Dp = new Detalle_Prestamos(Plib.getId_prestamos(), Lb.getId_libro(),Ed.getId_editorial()
                                    , 2);
        Dp.guardar();

        Detalle_Prestamos Dp1 = new Detalle_Prestamos(Plib.getId_prestamos(), Libro.buscarPorId(1).getId_libro(),Ed.getId_editorial()
                , 1);
        Dp1.guardar();

        System.out.println("\n\n");

        Plib.imprimirPrestamo();

        System.out.println("\nPrueba de datos ya cargados\n");

        Prestamos_Libros Pl = Prestamos_Libros.buscarPorId(1);
        Pl.imprimirPrestamo();
    }
}