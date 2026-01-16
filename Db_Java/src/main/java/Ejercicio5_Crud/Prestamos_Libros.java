package Ejercicio5_Crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Prestamos_Libros {


    private int id_prestamos = 0;
    private Date fecha_prestamo;
    private int id_colegio_profesor;
    private int id_curso;
    private int id_asignatura;
    private int id_aula;


    public Prestamos_Libros() {
    }

    public Prestamos_Libros(Date fecha_prestamo, int id_colegio_profesor, int id_curso, int id_asignatura, int id_aula) {
        this.fecha_prestamo = fecha_prestamo;
        this.id_colegio_profesor = id_colegio_profesor;
        this.id_curso = id_curso;
        this.id_asignatura = id_asignatura;
        this.id_aula = id_aula;
    }

    public void guardar() {
        try (Connection conn = Conexion.getConexion()) {

            if (this.id_prestamos == 0) {

                String sql = "INSERT INTO prestamos_libros (fecha_prestamo, id_colegio_profesor, id_curso, id_asignatura, id_aula) " +
                        "VALUES (?, ?, ?, ?, ?) RETURNING id_prestamos";

                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setDate(1, this.fecha_prestamo);
                    stmt.setInt(2, this.id_colegio_profesor);
                    stmt.setInt(3, this.id_curso);
                    stmt.setInt(4, this.id_asignatura);
                    stmt.setInt(5, this.id_aula);

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        this.id_prestamos = rs.getInt(1);
                        System.out.println("Préstamo creado con ID: " + this.id_prestamos);
                    }
                }
            } else {
                String sql = "UPDATE prestamos_libros SET fecha_prestamo=?, id_colegio_profesor=?, id_curso=?, id_asignatura=?, id_aula=? " +
                        "WHERE id_prestamos=?";

                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setDate(1, this.fecha_prestamo);
                    stmt.setInt(2, this.id_colegio_profesor);
                    stmt.setInt(3, this.id_curso);
                    stmt.setInt(4, this.id_asignatura);
                    stmt.setInt(5, this.id_aula);
                    stmt.setInt(6, this.id_prestamos);

                    stmt.executeUpdate();
                    System.out.println("Préstamo ID " + this.id_prestamos + " actualizado.");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar préstamo: " + e.getMessage());
        }
    }

    public void imprimirPrestamo()
    {


    }

    public static Prestamos_Libros buscarPorId(int id) {
        Prestamos_Libros Pl = null;
        try (Connection conn = Conexion.getConexion()) {
            String sql = "SELECT * FROM prestamos_libros WHERE id_prestamos = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pl = new Prestamos_Libros();
                Pl.setId_prestamos(id);
                Pl.setFecha_prestamo(rs.getDate("fecha_prestamo"));
                Pl.setId_prestamos(rs.getInt("id_colegio_Profesor"));
                Pl.setId_curso(rs.getInt("id_curso"));
                Pl.setId_asignatura(rs.getInt("id_asignatura"));
                Pl.setId_aula(rs.getInt("id_aula"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Pl;
    }

    public void eliminar() {
        if (this.id_prestamos == 0) return;

        try (Connection conn = Conexion.getConexion()) {
            String sql = "DELETE FROM prestamos_libros WHERE id_prestamos = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, this.id_prestamos);
                stmt.executeUpdate();

                System.out.println("Préstamo eliminado.");
                this.id_prestamos = 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar préstamo: " + e.getMessage());
        }
    }

    public int getId_prestamos() { return id_prestamos; }
    public void setId_prestamos(int id_prestamos) { this.id_prestamos = id_prestamos; }

    public Date getFecha_prestamo() { return fecha_prestamo; }
    public void setFecha_prestamo(Date fecha_prestamo) { this.fecha_prestamo = fecha_prestamo; }

    public int getId_colegio_profesor() { return id_colegio_profesor; }
    public void setId_colegio_profesor(int id_colegio_profesor) { this.id_colegio_profesor = id_colegio_profesor; }

    public int getId_curso() { return id_curso; }
    public void setId_curso(int id_curso) { this.id_curso = id_curso; }

    public int getId_asignatura() { return id_asignatura; }
    public void setId_asignatura(int id_asignatura) { this.id_asignatura = id_asignatura; }

    public int getId_aula() { return id_aula; }
    public void setId_aula(int id_aula) { this.id_aula = id_aula; }
}