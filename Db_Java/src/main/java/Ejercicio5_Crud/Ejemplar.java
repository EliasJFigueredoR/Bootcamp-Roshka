package Ejercicio5_Crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejemplar {
    private int id_ejemplar = 0;
    private int id_editorial;
    private int id_libro;

    public Ejemplar(int id_editorial, int id_libro) {
        this.id_editorial = id_editorial;
        this.id_libro = id_libro;
    }

    public void guardar(){
        try(Connection conn = Conexion.getConexion())
        {
            if(this.id_ejemplar==0)
            {
                String sql = "INSERT INTO Ejemplar (id_editorial,id_libro) VALUES (?,?) RETURNING id";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, this.id_editorial);
                stmt.setInt(2, this.id_libro);

                ResultSet rs = stmt.executeQuery();
                if(rs.next())
                {
                    this.id_ejemplar= rs.getInt(1);
                    System.out.println("Ejemplar creado con ID: " + this.id_ejemplar);
                }
            }else
            {
                String sql = "UPDATE Ejemplar SET (id_editorial, id_libro) VALUES (?,?) WHERE id_ejemplar = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1,this.id_editorial);
                stmt.setInt(2, this.id_libro);
                stmt.setInt(3, this.id_ejemplar);

                stmt.executeUpdate();
                System.out.println("Ejemplar ID " + this.id_ejemplar+ " actualizado.");
            }

        }catch (SQLException e)
        {
            System.out.println("Error en la conexión");
        }
    }

    public void eliminar(){
        try(Connection conn = Conexion.getConexion())
        {
            if(this.id_ejemplar == 0)
            {
                return;
            }
            String sql = "Delete FROM Ejemplar WHERE id_ejemplar= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id_ejemplar);
            stmt.executeUpdate();

            System.out.println("Usuario Eliminado");
            this.id_ejemplar = 0;

        }catch (SQLException e)
        {
            System.out.println("Error en la conexión");
        }
    }


    public int getId_ejemplar() {
        return id_ejemplar;
    }

    public void setId_ejemplar(int id_ejemplar) {
        this.id_ejemplar = id_ejemplar;
    }

    public int getId_editorial() {
        return id_editorial;
    }

    public void setId_editorial(int id_editorial) {
        this.id_editorial = id_editorial;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }
}
