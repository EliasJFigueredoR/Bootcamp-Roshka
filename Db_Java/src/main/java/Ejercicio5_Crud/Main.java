package Ejercicio5_Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Editorial Ed = Editorial.buscarPorId(3);
        System.out.println(Ed.getNombre());
    }
}