package Controller;

import Model.Administrador;
import Pattern.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GestorAdministradorImpl implements GestorAdministrador {

    @Override
    public boolean validarAdministrador(String correo, String contrasena) {
        // Consulta SQL para validar correo y contraseña
        String sql = "SELECT * FROM Administrador WHERE correo = ? AND contrasena = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Asigna parámetros a la consulta
            ps.setString(1, correo);
            ps.setString(2, contrasena);

            // Ejecuta consulta
            ResultSet rs = ps.executeQuery();

            // Retorna true si encontró un registro, false si no
            return rs.next();

        } catch (Exception e) {
            System.out.println("Error al validar administrador: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Administrador obtenerAdministradorPorCorreo(String correo) {
        // Consulta SQL para obtener administrador por correo
        String sql = "SELECT * FROM Administrador WHERE correo = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Asigna correo al parámetro
            ps.setString(1, correo);

            // Ejecuta consulta
            ResultSet rs = ps.executeQuery();

            // Si encuentra el registro, llena y retorna el objeto Administrador
            if (rs.next()) {
                Administrador admin = new Administrador();
                admin.setIdAdmin(rs.getInt("idAdmin"));
                admin.setNombre(rs.getString("nombre"));
                admin.setApellido(rs.getString("apellido"));
                admin.setCorreo(rs.getString("correo"));
                return admin;
            }

        } catch (Exception e) {
            System.out.println("Error al obtener administrador: " + e.getMessage());
        }
        // Si no encontró, retorna null
        return null;
    }

    @Override
    public boolean modificarDatos(Administrador admin) {
        // Consulta SQL para actualizar nombre, apellido y correo usando idAdmin
        String sql = "UPDATE Administrador SET nombre = ?, apellido = ?, correo = ? WHERE idAdmin = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Asigna nuevos valores a los parámetros
            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getApellido());
            ps.setString(3, admin.getCorreo());
            ps.setInt(4, admin.getIdAdmin());

            // Ejecuta actualización y verificar si afectó al menos 1 fila
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al modificar datos del administrador: " + e.getMessage());
            return false;
        }
    }
}
