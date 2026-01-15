package Ejercicio5_Crud;

import java.sql.*;

public class Asignatura_Habilidad {
    private int id_asignatura = 0;
    private String nombre;

    public Asignatura_Habilidad(String nombre) {
        this.nombre = nombre;
    }

    public Asignatura_Habilidad() {}

    public void guardar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_asignatura == 0) {
                String sql = "INSERT INTO Asignatura_Habilidad (nombre) VALUES (?) RETURNING id_asignatura";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    this.id_asignatura = rs.getInt(1);
                    System.out.println("asignatura creado con ID: " + this.id_asignatura);
                }
            } else {
                String sql = "UPDATE Asignatura_Habilidad SET nombre = ? WHERE id_asignatura = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);
                stmt.setInt(2, this.id_asignatura);

                stmt.executeUpdate();
                System.out.println("asignatura ID " + this.id_asignatura + " actualizado.");
            }

        } catch (SQLException e) {
            System.out.println("Error en la conexión al guardar asignatura");
        }
    }

    public void eliminar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_asignatura == 0) {
                return;
            }
            String sql = "DELETE FROM Asignatura_Habilidad WHERE id_asignatura = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id_asignatura);
            stmt.executeUpdate();

            System.out.println("asignatura Eliminado");
            this.id_asignatura = 0;

        } catch (SQLException e) {
            System.out.println("Error en la conexión al eliminar asignatura");
        }
    }

    public static Asignatura_Habilidad buscarPorId(int id) {
        Asignatura_Habilidad l = null;
        try (Connection conn = Conexion.getConexion()) {
            String sql = "SELECT * FROM Asignatura_Habilidad WHERE id_asignatura = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                l = new Asignatura_Habilidad();
                l.setId_asignatura(rs.getInt("id_asignatura"));
                l.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}