package principal;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;

public class PrincipalCiclista {
	private static SessionFactory sesion;
	public static void main(String[] args) {
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		sesion=Conexion.getSession();
		
		
		ejercicio1();
		sesion.close();

	}
	private static void ejercicio1() {
		
		
	}

}
