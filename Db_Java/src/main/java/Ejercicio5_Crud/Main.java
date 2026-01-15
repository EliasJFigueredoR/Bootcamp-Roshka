package Ejercicio5_Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Editorial Ed = Editorial.buscarPorId(3);
        System.out.println(Ed.getNombre());

        Libro l = Libro.buscarPorId(1);
        System.out.println(l.getNombre());

        Curso Cu = Curso.buscarPorId(1);
        System.out.println(Cu.getNombre());

        Colegio Co = Colegio.buscarPorId(1);
        System.out.println(Co.getNombre());

        Aula Al = Aula.buscarPorId(1);
        System.out.println(Al.getNombre());

        Asignatura_Habilidad As = Asignatura_Habilidad.buscarPorId(1);
        System.out.println(As.getNombre());

        Profesor pr = Profesor.buscarPorId(1);
        System.out.println(pr.getNombre());
    }
}