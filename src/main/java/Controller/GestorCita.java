package Controller;

import Model.Cita;
import java.util.List;

public interface GestorCita {

    //Obtiene la lista de todas las citas registradas en el sistema
    List<Cita> obtenerTodas();

    //Actualiza el estado actual de una cita
    boolean actualizarEstado(int idCita, String nuevoEstado);
    
}
