package Ejercicio5_Crud;

import java.sql.*;

public class Libro {
    private int id_libro = 0; // Inicializamos en 0 para saber si es nuevo
    private String nombre;

    public Libro(String nombre) {
        this.nombre = nombre;
    }

    // Constructor vacío necesario para buscarPorId
    public Libro() {}

    public void guardar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_libro == 0) {
                // INSERT: Asumo que la columna en BD se llama 'id_libro'
                // Usamos RETURNING para obtener el ID generado automáticamente por Postgres
                String sql = "INSERT INTO Libro (nombre) VALUES (?) RETURNING id_libro";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    this.id_libro = rs.getInt(1);
                    System.out.println("Libro creado con ID: " + this.id_libro);
                }
            } else {
                // UPDATE
                String sql = "UPDATE Libro SET nombre = ? WHERE id_libro = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.nombre);
                stmt.setInt(2, this.id_libro);

                stmt.executeUpdate();
                System.out.println("Libro ID " + this.id_libro + " actualizado.");
            }

        } catch (SQLException e) {
            System.out.println("Error en la conexión al guardar libro");
            // e.printStackTrace(); // Descomentar para ver el error real si falla
        }
    }

    public void eliminar() {
        try (Connection conn = Conexion.getConexion()) {
            if (this.id_libro == 0) {
                return; // No se puede eliminar algo que no existe en BD
            }
            String sql = "DELETE FROM Libro WHERE id_libro = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id_libro);
            stmt.executeUpdate();

            System.out.println("Libro Eliminado");
            this.id_libro = 0; // Reseteamos el ID localmente

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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l; // Retorna el objeto lleno o null
    }

    // Getters y Setters
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
}