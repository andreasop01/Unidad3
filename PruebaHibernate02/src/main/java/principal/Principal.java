package principal;

import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import datosCentros.C1Asignaturas;
import datosCentros.C1Centros;
import datosCentros.C1Profesores;

public class Principal {
	private static SessionFactory sesion;
	
	public static void main(String[] args) {
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		
		sesion = Conexion.getSession(); 
		System.out.println("----------------------" );
		consultarprofesor((short)1010);
		System.out.println("----------------------" );
		consultarprofesor((short)1011);
		System.out.println("----------------------" );
		consultarprofesor((short)444);
		
		System.out.println("----------------------" );
		vercentro((short)1000);
		System.out.println("----------------------" );
		vercentro((short)1015);
		
		
		System.out.println("----------------------" );
		vercentro((short)1045);
		sesion.close();

	}
	private static void vercentro(short cod) {
		/*
		 *Cod Centro:  xxxxxx                    Nombre: xxxxxxx
	Lista de Profesores del centro
	Cod  NombreProfesor NombrEspecialidad  Nombre Jefe  NúmAsig que imparte
	---  -------------- -----------------  ------------ --------------------
		 */
		Session session = sesion.openSession();
		C1Centros cen = (C1Centros) session.get(C1Centros.class, cod);
        if (cen==null) {
        	System.out.println("El centro con cod: " + cod + " No existe");
        }
        else {       
        	String nombredire="SIN DIRECTOR";
        	if (cen.getDirector()!=null ) {
        		//buscarlo en la clase profesores
        		C1Profesores direc = (C1Profesores) 
        				session.get(C1Profesores.class, cen.getDirector());
         		nombredire=direc.getNombreApe();
        	}
        	System.out.println("Cod Centro: "+ cod + 
        			"   Nombre: "+cen.getNomCentro()+
        			"   NOMBRE Director: "+nombredire);
        	Set<C1Profesores> listap = cen.getC1Profesoreses();
        	if (listap.size()>0 ) {
        		System.out.println("Lista de profesores del centro:");
        		System.out.printf("%5s %30s %20s %30s %10s%n",
        				"Cod","NombreProfesor","Especialidad","Nombre Jefe","NúmAsig");
        		System.out.printf("%5s %30s %20s %30s %10s%n",
        				"----","--------------", "--------------","--------------","---------");
        		
        		for (C1Profesores prof : listap) {
        			String jefe="NO TIENE JEFE";
                	if (prof.getC1Profesores()!=null)
                		jefe=prof.getC1Profesores().getNombreApe();
        			System.out.printf("%5s %30s %20s %30s %10s%n",
        					prof.getCodProf(), prof.getNombreApe(),
        					prof.getC1Especialidad().getNombreEspe(),
        					jefe, prof.getC1Asignaturases().size());
        					
             	}
        		System.out.printf("%5s %30s %20s %30s %10s%n",
        				"----","--------------", "--------------","--------------","---------");
        	
        		
        		
        		
        		
        	}
        	else {
        		System.out.println("NO tiene profesores.");
        	}
        	
        	
        }
		
        session.close();
	}
	

	private static void consultarprofesor(short codp) {
		// TODO Auto-generated method stub
		Session session = sesion.openSession();
		C1Profesores pr = (C1Profesores) session.get(C1Profesores.class, codp);
        if (pr==null) {
        	System.out.println("El profesor con cod: " + codp + " No existe");
        }
        else {
        	
        	System.out.println("Los datos del prof con código: "+codp);
        	System.out.println("Nombre: " + pr.getNombreApe());
        	String jefe="NO TIENE JEFE";
        	if (pr.getC1Profesores()!=null)
        		jefe=pr.getC1Profesores().getNombreApe();
        		
        	System.out.println("Nombre jefe: " + jefe );
           	System.out.println("Nombre centro: " + pr.getC1Centros().getNomCentro());
        	System.out.println("Nombre especialidad: " + pr.getC1Especialidad().getNombreEspe());
        	System.out.println("Num asignaturas que imparte: "+ pr.getC1Asignaturases().size());
        	Set<C1Asignaturas> lista = pr.getC1Asignaturases();
        	for (C1Asignaturas asig : lista) {
        	   System.out.println(asig.getCodAsig() + " * " + asig.getNombreAsi());
        	}
        	
        	System.out.println("Num prof subordinados: "+ pr.getC1Profesoreses().size());
        	Set<C1Profesores> listap = pr.getC1Profesoreses();
        	for (C1Profesores pp : listap) {
        	   System.out.println(pp.getCodProf() + " * " + pp.getNombreApe());
        	}
        }
		
        session.close();
		
	}

}
