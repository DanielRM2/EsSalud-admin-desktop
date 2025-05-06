package Controller;

import Model.Cita;
import Pattern.Conexion;
import java.util.Date;
import java.sql.*;
import java.util.*;

public class GestorCitaImpl implements GestorCita {

    //Obtiene todas las citas de la base de datos
    @Override
    public List<Cita> obtenerTodas() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cita";

        // Se usan try-with-resources para cerrar automáticamente los recursos
        try (Connection conn = Conexion.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Recorre los resultados y construye objetos Cita
            while (rs.next()) {
                Cita c = new Cita();
                c.setIdCita(rs.getInt("idCita"));
                c.setIdUsuario(rs.getInt("idUsuario"));
                c.setIdMedico(rs.getInt("idMedico"));
                c.setIdCentroMedico(rs.getInt("idCentroMedico"));
                c.setFechaCita(rs.getTimestamp("fechaCita"));
                c.setEstado(rs.getString("estado"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener citas: " + e.getMessage());
        }

        return lista;
    }

    //Actualiza la fecha de una cita específica
    @Override
    public boolean actualizarFecha(int idCita, Date nuevaFecha) {
        String sql = "UPDATE Cita SET fechaCita = ? WHERE idCita = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Asigna los parámetros al PreparedStatement
            stmt.setTimestamp(1, new java.sql.Timestamp(nuevaFecha.getTime()));
            stmt.setInt(2, idCita);
            return stmt.executeUpdate() > 0;  // Devuelve true si al menos una fila fue actualizada

        } catch (SQLException e) {
            System.out.println("Error al actualizar fecha: " + e.getMessage());
            return false;
        }
    }

    //Actualiza el estado de una cita específica
    @Override
    public boolean actualizarEstado(int idCita, String nuevoEstado) {
        String sql = "UPDATE Cita SET estado = ? WHERE idCita = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Asigna los parámetros al PreparedStatement
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idCita);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }
}
