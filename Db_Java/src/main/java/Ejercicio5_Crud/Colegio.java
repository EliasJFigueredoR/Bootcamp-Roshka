package Ejercicio5_Crud;

import java.sql.*;

public class Colegio {
    private int id_colegio = 0;
    private String nombre;

    public Colegio(String nombre) {
        this.nombre = nombre;
    }

    public Colegio() {}

    public void guardar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_colegio == 0) {
                String sql = "INSERT INTO Colegio (nombre) VALUES (?) RETURNING id_colegio";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    this.id_colegio = rs.getInt(1);
                    System.out.println("colegio creado con ID: " + this.id_colegio);
                }
            } else {
                String sql = "UPDATE Colegio SET nombre = ? WHERE id_colegio = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);
                stmt.setInt(2, this.id_colegio);

                stmt.executeUpdate();
                System.out.println("colegio ID " + this.id_colegio + " actualizado.");
            }

        } catch (SQLException e) {
            System.out.println("Error en la conexión al guardar colegio");
        }
    }

    public void eliminar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_colegio == 0) {
                return;
            }
            String sql = "DELETE FROM colegio WHERE id_colegio = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id_colegio);
            stmt.executeUpdate();

            System.out.println("colegio Eliminado");
            this.id_colegio = 0;

        } catch (SQLException e) {
            System.out.println("Error en la conexión al eliminar colegio");
        }
    }

    public static Colegio buscarPorId(int id) {
        Colegio l = null;
        try (Connection conn = Conexion.getConexion()) {
            String sql = "SELECT * FROM colegio WHERE id_colegio = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                l = new Colegio();
                l.setId_colegio(rs.getInt("id_colegio"));
                l.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public int getId_colegio() {
        return id_colegio;
    }

    public void setId_colegio(int id_colegio) {
        this.id_colegio = id_colegio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}