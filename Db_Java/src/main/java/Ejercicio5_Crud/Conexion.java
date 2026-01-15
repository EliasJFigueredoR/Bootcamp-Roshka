package Ejercicio5_Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String url = "jdbc:postgresql://localhost:5432/Prueba1?currentSchema=ejercicio5";
    private static final String user = "postgres";
    private static final String password = "Robin";

    public static Connection getConexion() throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }
}
