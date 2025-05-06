package Controller;

import Model.CentroMedico;
import Pattern.Conexion;
import java.sql.*;
import java.util.*;

public class GestorCentroMedicoImpl implements GestorCentroMedico {

    // Obtiene todos los centros médicos desde la base de datos
    @Override
    public List<CentroMedico> obtenerTodos() {
        List<CentroMedico> lista = new ArrayList<>();
        String sql = "SELECT * FROM CentroMedico";

        // try-with-resources para asegurar cierre de conexión, statement y resultset
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Recorre cada fila del resultado y construye el objeto CentroMedico
            while (rs.next()) {
                CentroMedico cm = new CentroMedico();
                cm.setIdCentroMedico(rs.getInt("idCentroMedico"));
                cm.setNombre(rs.getString("nombre"));
                cm.setDireccion(rs.getString("direccion"));
                lista.add(cm);
            }

        } catch (SQLException e) {
            // Manejo de errores al obtener centros médicos
            System.out.println("Error al obtener centros médicos: " + e.getMessage());
        }

        return lista;
    }
}
