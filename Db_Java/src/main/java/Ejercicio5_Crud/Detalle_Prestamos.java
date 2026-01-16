package Ejercicio5_Crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Detalle_Prestamos {
    private int id_prestamos;
    private int id_libro;
    private int id_editorial;
    private int cantidad;

    public Detalle_Prestamos() {
    }

    public Detalle_Prestamos(int id_prestamos, int id_libro, int id_editorial, int cantidad) {
        this.id_prestamos = id_prestamos;
        this.id_libro = id_libro;
        this.id_editorial = id_editorial;
        this.cantidad = cantidad;
    }

    private boolean existe(Connection conn) throws SQLException {
        String sql = "SELECT 1 FROM detalle_prestamos WHERE id_prestamos = ? AND id_libro = ? AND id_editorial = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, this.id_prestamos);
            stmt.setInt(2, this.id_libro);
            stmt.setInt(3, this.id_editorial);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void guardar() {
        try (Connection conn = Conexion.getConexion()) {

            if (existe(conn)) {
                String sql = "UPDATE detalle_prestamos SET cantidad = ? WHERE id_prestamos = ? AND id_libro = ? AND id_editorial = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, this.cantidad);
                    stmt.setInt(2, this.id_prestamos);
                    stmt.setInt(3, this.id_libro);
                    stmt.setInt(4, this.id_editorial);

                    stmt.executeUpdate();
                    System.out.println("Detalle actualizado: Cantidad modificada.");
                }
            } else {
                String sql = "INSERT INTO detalle_prestamos (id_prestamos, id_libro, id_editorial, cantidad) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, this.id_prestamos);
                    stmt.setInt(2, this.id_libro);
                    stmt.setInt(3, this.id_editorial);
                    stmt.setInt(4, this.cantidad);

                    stmt.executeUpdate();
                    System.out.println("Detalle guardado exitosamente.");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar detalle: " + e.getMessage());
        }
    }



    public void eliminar() {
        try (Connection conn = Conexion.getConexion()) {

            String sql = "DELETE FROM detalle_prestamos WHERE id_prestamos = ? AND id_libro = ? AND id_editorial = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, this.id_prestamos);
                stmt.setInt(2, this.id_libro);
                stmt.setInt(3, this.id_editorial);

                int filasAfectadas = stmt.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Detalle eliminado.");
                } else {
                    System.out.println("No se encontró el registro para eliminar.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
        }
    }

    public int getId_prestamos() { return id_prestamos; }
    public void setId_prestamos(int id_prestamos) { this.id_prestamos = id_prestamos; }

    public int getId_libro() { return id_libro; }
    public void setId_libro(int id_libro) { this.id_libro = id_libro; }

    public int getId_editorial() { return id_editorial; }
    public void setId_editorial(int id_editorial) { this.id_editorial = id_editorial; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}