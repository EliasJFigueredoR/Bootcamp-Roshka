package Ejercicio5_Crud;

import java.sql.*;

public class Aula {
    private int id_aula = 0;
    private String nombre;

    public Aula(String nombre) {
        this.nombre = nombre;
    }

    public Aula() {}

    public void guardar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_aula == 0) {
                String sql = "INSERT INTO Aula (nombre) VALUES (?) RETURNING id_aula";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    this.id_aula = rs.getInt(1);
                    System.out.println("aula creado con ID: " + this.id_aula);
                }
            } else {
                String sql = "UPDATE Aula SET nombre = ? WHERE id_aula = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);
                stmt.setInt(2, this.id_aula);

                stmt.executeUpdate();
                System.out.println("aula ID " + this.id_aula + " actualizado.");
            }

        } catch (SQLException e) {
            System.out.println("Error en la conexión al guardar aula");
        }
    }

    public void eliminar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_aula == 0) {
                return;
            }
            String sql = "DELETE FROM aula WHERE id_aula = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id_aula);
            stmt.executeUpdate();

            System.out.println("aula Eliminado");
            this.id_aula = 0;

        } catch (SQLException e) {
            System.out.println("Error en la conexión al eliminar aula");
        }
    }

    public static Aula buscarPorId(int id) {
        Aula l = null;
        try (Connection conn = Conexion.getConexion()) {
            String sql = "SELECT * FROM aula WHERE id_aula = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                l = new Aula();
                l.setId_aula(rs.getInt("id_aula"));
                l.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}