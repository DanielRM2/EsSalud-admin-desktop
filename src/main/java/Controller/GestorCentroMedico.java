package Controller;

import Model.CentroMedico;
import java.util.List;


public interface GestorCentroMedico {
    
    // Obtener todas los centros medicos
    List<CentroMedico> obtenerTodos(); 
    
}