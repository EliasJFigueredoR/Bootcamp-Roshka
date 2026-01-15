package Ejercicio5_Crud;

import java.sql.*;

public class Profesor {
    private int id_profesor = 0;
    private String nombre;

    public Profesor(String nombre) {
        this.nombre = nombre;
    }

    public Profesor() {}

    public void guardar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_profesor == 0) {
                String sql = "INSERT INTO Profesor (nombre) VALUES (?) RETURNING id_profesor";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    this.id_profesor = rs.getInt(1);
                    System.out.println("profesor creado con ID: " + this.id_profesor);
                }
            } else {
                String sql = "UPDATE Profesor SET nombre = ? WHERE id_profesor = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);
                stmt.setInt(2, this.id_profesor);

                stmt.executeUpdate();
                System.out.println("profesor ID " + this.id_profesor + " actualizado.");
            }

        } catch (SQLException e) {
            System.out.println("Error en la conexión al guardar profesor");
        }
    }

    public void eliminar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_profesor == 0) {
                return;
            }
            String sql = "DELETE FROM profesor WHERE id_profesor = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id_profesor);
            stmt.executeUpdate();

            System.out.println("profesor Eliminado");
            this.id_profesor = 0;

        } catch (SQLException e) {
            System.out.println("Error en la conexión al eliminar profesor");
        }
    }

    public static Profesor buscarPorId(int id) {
        Profesor l = null;
        try (Connection conn = Conexion.getConexion()) {
            String sql = "SELECT * FROM profesor WHERE id_profesor = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                l = new Profesor();
                l.setId_profesor(rs.getInt("id_profesor"));
                l.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}