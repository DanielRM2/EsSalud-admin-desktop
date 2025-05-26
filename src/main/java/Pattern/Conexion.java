package Pattern;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Conexion {
    
    // Instancia única del pool de conexiones para toda la aplicación
    private static HikariDataSource dataSource;

    static {
        // Configuración inicial del pool de conexiones HikariCP
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://beukuvlrhrmlx8gqe7xo-mysql.services.clever-cloud.com:3306/beukuvlrhrmlx8gqe7xo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Lima");
        config.setUsername("us5cns1bqofgsggs");
        config.setPassword("sdRA1urjC406Qc95V68g");

        // Parámetros
        config.setMaximumPoolSize(5);         // Máximo de 5 conexiones activas al mismo tiempo
        config.setMinimumIdle(1);             // Mantener mínimo 1 conexión en espera
        config.setIdleTimeout(30000);         // Tiempo máximo (30 seg) antes de cerrar conexiones inactivas
        config.setConnectionTimeout(10000);   // Esperar hasta 10 seg al pedir una conexión antes de lanzar error
        config.setLeakDetectionThreshold(15000); // Detectar posibles fugas si una conexión no se cierra en 15 seg

        // Nombre personalizado para identificar el pool en los logs
        config.setPoolName("HikariCleverCloudPool");

        // Inicializar el pool de conexiones
        dataSource = new HikariDataSource(config);
    }

    // Obtiene una conexión del pool.
    public static Connection obtenerConexion() throws SQLException {
        return dataSource.getConnection();
    }

    //Cierra completamente el pool de conexiones.
    public static void cerrarPool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("El pool de conexiones ha sido cerrado.");
        }
    }
}
