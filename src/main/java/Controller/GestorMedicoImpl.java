package Controller;

import Model.Medico;
import Pattern.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorMedicoImpl implements GestorMedico {

    //Obtiene todos los médicos almacenados en la base de datos
    @Override
    public List<Medico> obtenerTodos() {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM Medico";

        // Se usan try-with-resources para asegurar el cierre automático de recursos
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Recorre el ResultSet y construye objetos Medico
            while (rs.next()) {
                Medico m = new Medico();
                m.setIdMedico(rs.getInt("idMedico"));
                m.setNombre(rs.getString("nombre"));
                m.setApellido(rs.getString("apellido"));
                m.setIdEspecialidad(rs.getInt("idEspecialidad"));
                m.setIdCentroMedico(rs.getInt("idCentroMedico"));
                m.setEstado(rs.getBoolean("estado"));
                lista.add(m);  // Agrega el médico a la lista
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener médicos: " + e.getMessage());
        }

        return lista;  // Devuelve la lista de médicos
    }

}
