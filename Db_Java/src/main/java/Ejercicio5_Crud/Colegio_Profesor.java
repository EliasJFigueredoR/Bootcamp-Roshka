package Ejercicio5_Crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Colegio_Profesor {
    private int id_colegio_profesor = 0;
    private int id_profesor;
    private int id_colegio;

    public Colegio_Profesor(int id_profesor, int id_colegio) {
        this.id_profesor = id_profesor;
        this.id_colegio = id_colegio;
    }

    public void guardar(){
        try(Connection conn = Conexion.getConexion())
        {
            if(this.id_colegio_profesor==0)
            {
                String sql = "INSERT INTO Colegio_Profesor (id_profesor,id_colegio) VALUES (?,?) RETURNING id";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, this.id_profesor);
                stmt.setInt(2, this.id_colegio);

                ResultSet rs = stmt.executeQuery();
                if(rs.next())
                {
                    this.id_colegio_profesor= rs.getInt(1);
                    System.out.println("Colegio_Profesor creado con ID: " + this.id_colegio_profesor);
                }
            }else
            {
                String sql = "UPDATE Colegio_Profesor SET (id_profesor, id_colegio) VALUES (?,?) WHERE id_colegio_profesor = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1,this.id_profesor);
                stmt.setInt(2, this.id_colegio);
                stmt.setInt(3, this.id_colegio_profesor);

                stmt.executeUpdate();
                System.out.println("Colegio_Profesor ID " + this.id_colegio_profesor+ " actualizado.");
            }

        }catch (SQLException e)
        {
            System.out.println("Error en la conexión");
        }
    }

    public void eliminar(){
        try(Connection conn = Conexion.getConexion())
        {
            if(this.id_colegio_profesor == 0)
            {
                return;
            }
            String sql = "Delete FROM Colegio_Profesor WHERE id_colegio_profesor= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id_colegio_profesor);
            stmt.executeUpdate();

            System.out.println("Usuario Eliminado");
            this.id_colegio_profesor = 0;

        }catch (SQLException e)
        {
            System.out.println("Error en la conexión");
        }
    }


    public int getId_colegio_profesor() {
        return id_colegio_profesor;
    }

    public void setId_colegio_profesor(int id_colegio_profesor) {
        this.id_colegio_profesor = id_colegio_profesor;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public int getId_colegio() {
        return id_colegio;
    }

    public void setId_colegio(int id_colegio) {
        this.id_colegio = id_colegio;
    }
}
