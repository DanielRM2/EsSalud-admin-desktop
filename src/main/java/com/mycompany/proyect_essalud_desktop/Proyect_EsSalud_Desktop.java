package com.mycompany.proyect_essalud_desktop;

import Pattern.Conexion;
import View.Login;
import java.sql.Connection;

public class Proyect_EsSalud_Desktop {

    public static void main(String[] args) {

        // Registra un shutdown hook (apagado) que cerrará el pool al apagar la aplicación
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Conexion.cerrarPool();
        }));

        // Usa try-with-resources para asegurarse de cerrar la conexión automáticamente al salir del bloque
        try (Connection conn = Conexion.obtenerConexion()) {
            if (conn != null) {
                System.out.println("Conexión exitosa con la base de datos.");
                Login.main(args); // Llama a la interfaz de login al iniciar el programa
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            // Captura cualquier error al intentar conectar y muestra el mensaje
            System.out.println("No se pudo establecer la conexión a la base de datos: " + e.getMessage());
        }
    }
}
