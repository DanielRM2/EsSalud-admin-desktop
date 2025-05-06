package Controller;

import Model.Administrador;

public interface GestorAdministrador {

    // Valida si existe un administrador con ese correo y contraseña
    boolean validarAdministrador(String correo, String contrasena);

    // Obtiene todos los datos del administrador según el correo
    Administrador obtenerAdministradorPorCorreo(String correo);

    // Modifica los datos de un administrador
    boolean modificarDatos(Administrador admin);
}
