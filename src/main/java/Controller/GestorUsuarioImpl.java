package Controller;

import Model.Usuario;
import Pattern.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuarioImpl implements GestorUsuario {

    //Obtiene todos los usuarios registrados en la base de datos
    @Override
    public List<Usuario> obtenerTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        // try-with-resources: asegura que la conexión, el statement y el resultset se cierren automáticamente
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Recorremos el resultado y construimos objetos Usuario
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("idUsuario"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("dni"),
                    rs.getString("correo"),
                    rs.getString("contrasena")
                );
                lista.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        }

        return lista;
    }

    //Elimina un usuario de la base de datos según su ID
    @Override
    public boolean eliminarPorId(int idUsuario) {
        String sql = "DELETE FROM Usuario WHERE idUsuario = ?";

        // try-with-resources para liberar recursos automáticamente
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // true si al menos una fila fue eliminada

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}
