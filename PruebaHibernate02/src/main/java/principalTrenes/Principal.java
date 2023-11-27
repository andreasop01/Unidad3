package principalTrenes;

import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import datosCentros.TLineaEstacion;
import datosCentros.*;



public class Principal {
	private static SessionFactory sesion;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		sesion=Conexion.getSession();
		
		verLinea((short)2);
		

	}

	private static void verLinea(short lin) {
		// TODO Auto-generated method stub
		
		Session session= sesion.openSession();
		TLineas linea=(TLineas) session.get(TLineas.class,lin);
		
		if(linea==null) {
			System.out.println("La linea con cod : "+lin+" No existe");
		}else {
			System.out.println("COD LINEA "+lin+" NOMBRE: "+linea.getNombre());
			System.out.println("Estacion: "+linea.getTLineaEstacions().size());
			
			if(linea.getTLineaEstacions().size()>0) {
				System.out.printf("%5s %-40s %-40s %n","COD","NOMBRE","DIRECCION");
				System.out.printf("%5s %-40s %-40s %n","---","------","---------");
				
				Set<TLineaEstacion> listaEstaciones =linea.getTLineaEstacions();
				for(TLineaEstacion le:listaEstaciones) {
					TEstaciones estacion=le.getTEstaciones();
					System.out.printf("%5s %-40s %-40s %n",estacion.getCodEstacion(),estacion.getNombre(),estacion.getDireccion());
					
				}
				
			}
		}
		
	}

}
