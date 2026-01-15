package Ejercicio5_Crud;

import java.sql.*;

public class Curso {
    private int id_curso = 0;
    private String nombre;

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public Curso() {}

    public void guardar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_curso == 0) {
                String sql = "INSERT INTO Curso (nombre) VALUES (?) RETURNING id_curso";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    this.id_curso = rs.getInt(1);
                    System.out.println("curso creado con ID: " + this.id_curso);
                }
            } else {
                String sql = "UPDATE Curso SET nombre = ? WHERE id_curso = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);
                stmt.setInt(2, this.id_curso);

                stmt.executeUpdate();
                System.out.println("curso ID " + this.id_curso + " actualizado.");
            }

        } catch (SQLException e) {
            System.out.println("Error en la conexión al guardar curso");
        }
    }

    public void eliminar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_curso == 0) {
                return;
            }
            String sql = "DELETE FROM curso WHERE id_curso = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id_curso);
            stmt.executeUpdate();

            System.out.println("curso Eliminado");
            this.id_curso = 0;

        } catch (SQLException e) {
            System.out.println("Error en la conexión al eliminar curso");
        }
    }

    public static Curso buscarPorId(int id) {
        Curso l = null;
        try (Connection conn = Conexion.getConexion()) {
            String sql = "SELECT * FROM curso WHERE id_curso = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                l = new Curso();
                l.setId_curso(rs.getInt("id_curso"));
                l.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}