package principal;

import java.math.BigInteger;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import datos.Departamentos;


public class Principal {
	private static SessionFactory sesion;


	public static void main(String[] args) {
		
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		
		sesion = Conexion.getSession(); //Creo la sessionFactory una única vez.

		insertardepartamento();
		insertarunempleado();
		cargardepar(BigInteger.valueOf(10));
		cargardeparget(BigInteger.valueOf(10));
		cargardepar(BigInteger.valueOf(88));
		cargardeparget(BigInteger.valueOf(88));


		sesion.close();
	}


	private static void cargardepar(BigInteger nu) {
		Session session = sesion.openSession();
		try {
			Departamentos dep = (Departamentos) session.getReference(Departamentos.class, nu);
			System.out.println("Nombre: " + dep.getDnombre());
			System.out.println("Localidad: " + dep.getLoc());
			
		} catch (ObjectNotFoundException ob) {
			System.out.println("NO EXISTE EL DEPARTAMENTO.");
		}
		session.close();
	}

	private static void cargardeparget(BigInteger nu) {
		Session session = sesion.openSession();
		Departamentos dep = (Departamentos) session.get(Departamentos.class, nu);
		if (dep == null) {
			System.out.println("El departamento no existe");
		} else {
			System.out.println("Nombre Dep:" + dep.getDnombre());
			System.out.println("Localidad:" + dep.getLoc());
		}
		session.close();
	}


	private static void insertarunempleado() {
		// TODO Auto-generated method stub
		
	}


	private static void insertardepartamento() {
		// TODO Auto-generated method stub
		Session session = sesion.openSession(); //creo una sesión de trabajo
		Transaction tx = session.beginTransaction();

		Departamentos dep = new Departamentos();
		dep.setDeptNo((BigInteger.valueOf(61)) );
		dep.setDnombre("MARKETs");
		dep.setLoc("GUADALAJARA");

		try {
			session.persist(dep);
			tx.commit();
		       System.out.println("Reg INSERTADO.");

		}catch (jakarta.persistence.PersistenceException e) {
			if (e.getMessage().contains("org.hibernate.exception.ConstraintViolationException")) {
				System.out.println("CLAVE DUPLICADA. DEPARTAMENTO YA EXISTE");
			} else if (e.getMessage().contains("org.hibernate.exception.DataException")) {
				System.out.println("ERROR EN LOS DATOS DE DEPARTAMENTO, DEMASIADOS CARACTERES");
			} else if (e.getMessage().contains("org.hibernate.exception.GenericJDBCException")) {
				System.out.println("ERROR JDBC. NO SE HA PODIDO EJECUATR LA CONSULTA");
			} else
				System.out.println("HA ocurrido un error: " + e.getMessage());

		}catch (Exception e) {
			System.out.println("ERROR NO CONTROLADO....");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		session.close(); //cierro la sesión de trabajo

	}

}

