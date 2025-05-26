package Controller;

import Model.Usuario;
import java.util.List;

public interface GestorUsuario {

    //Obtiene todos los usuarios registrados.
    List<Usuario> obtenerTodos();

    //Elimina un usuario de la base de datos según su ID
    boolean eliminarPorId(int idUsuario);
    
}
