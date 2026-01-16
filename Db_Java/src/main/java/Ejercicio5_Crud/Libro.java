package Ejercicio5_Crud;

import java.sql.*;

public class Libro {
    private int id_libro = 0;
    private String nombre;
    private int cantidad;

    public Libro(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Libro() {}

    public void guardar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_libro == 0) {
                String sql = "INSERT INTO Libro (nombre, cantidad) VALUES (?,?) RETURNING id_libro";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);
                stmt.setInt(2, this.cantidad);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    this.id_libro = rs.getInt(1);
                    System.out.println("Libro creado con ID: " + this.id_libro);
                }
            } else {
                String sql = "UPDATE Libro SET (nombre, cantidad) values(?,?) WHERE id_libro = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);
                stmt.setInt(2, this.id_libro);
                stmt.setInt(3, this.cantidad);

                stmt.executeUpdate();
                System.out.println("Libro ID " + this.id_libro + " actualizado.");
            }

        } catch (SQLException e) {
            System.out.println("Error en la conexión al guardar libro");
        }
    }

    public void eliminar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_libro == 0) {
                return;
            }
            String sql = "DELETE FROM Libro WHERE id_libro = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id_libro);
            stmt.executeUpdate();

            System.out.println("Libro Eliminado");
            this.id_libro = 0;

        } catch (SQLException e) {
            System.out.println("Error en la conexión al eliminar libro");
        }
    }

    public static Libro buscarPorId(int id) {
        Libro l = null;
        try (Connection conn = Conexion.getConexion()) {
            String sql = "SELECT * FROM Libro WHERE id_libro = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                l = new Libro();
                l.setId_libro(rs.getInt("id_libro"));
                l.setNombre(rs.getString("nombre"));
                l.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}