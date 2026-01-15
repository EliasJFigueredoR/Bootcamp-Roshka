package Ejercicio5_Crud;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.*;

public class Editorial {
    private int id_editorial;
    private String nombre;

    public Editorial(String nombre) {
        this.nombre = nombre;
    }

    public Editorial(){}

    public void guardar(){
        try(Connection conn = Conexion.getConexion())
        {
            if(this.id_editorial==0)
            {
                String sql = "INSERT INTO Editorial (nombre) VALUES (?) RETURNING id";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);

                ResultSet rs = stmt.executeQuery();
                if(rs.next())
                {
                    this.id_editorial= rs.getInt(1);
                    System.out.println("Usuario creado con ID: " + this.id_editorial);
                }
            }else
            {
                String sql = "UPDATE Editorial SET nombre = ? WHERE id_editorial = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,this.nombre);
                stmt.setInt(2, this.id_editorial);

                stmt.executeUpdate();
                System.out.println("Usuario ID " + this.id_editorial+ " actualizado.");
            }

        }catch (SQLException e)
        {
            System.out.println("Error en la conexión");
        }
    }

    public void eliminar(){
        try(Connection conn = Conexion.getConexion())
        {
            if(this.id_editorial == 0 )
            {
                return;
            }
            String sql = "Delete FROM Editorial WHERE id_editorial= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id_editorial);
            stmt.executeUpdate();

            System.out.println("Usuario Eliminado");
            this.id_editorial = 0;

        }catch (SQLException e)
        {
            System.out.println("Error en la conexión");
        }
    }

    public static Editorial buscarPorId(int id) {
        Editorial u = null;
        try (Connection conn = Conexion.getConexion()) {
            String sql = "SELECT * FROM Editorial WHERE id_editorial = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                u = new Editorial();
                u.setId_editorial(rs.getInt("id_editorial"));
                u.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u; // Retorna el objeto lleno o null
    }

    public int getId_editorial() {
        return id_editorial;
    }

    public void setId_editorial(int id_editorial) {
        this.id_editorial = id_editorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
