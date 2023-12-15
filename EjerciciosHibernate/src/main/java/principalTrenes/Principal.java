package principalTrenes;

import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import datos.TLineaEstacion;
import datos.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;




public class Principal {
	private static SessionFactory sesion;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		sesion=Conexion.getSession();
		List<String> serie=new ArrayList();
		serie.add("SERIE 3000");
		serie.add("SERIE 4000");
		serie.add("SERIE 5000");
//		
//		//verLinea((short)2);
//		//verestacionesunique();
//		
//		//mostrarTrenesDir("Madrid");
		//mostrarTrenesSerie(serie);
		//consultasobjetos();
		
		//verEstacionAcceso();
//		accesosPorEstacion();
//		trenesTipo();
//		updateDelete("AAAA", 2005.0f, 10); //apellido, salario, dept
//		modificaemplesalario(7521, 200); //Existe
//		modificaemplesalario(21, 200); //NO Existe
//		modificaempledepart(7521, BigInteger.valueOf(20)); //Existe
//		modificaempledepart(21, BigInteger.valueOf(20)); //No existe emple
//		modificaempledepart(7521, BigInteger.valueOf(13)); //No existe depa
//		insertardepardenuevos();
		insertarNuevosTrenes();
		
		sesion.close();
	}
	
	private static void insertarNuevosTrenes() {
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		Query cons = session.createQuery("from TNuevosTrenes", TNuevosTrenes.class);
		
		List<TNuevosTrenes> list = cons.list();
		for (TNuevosTrenes tren : list) {
			System.out.println("-----------------------");
			TLineas linea = (TLineas) session.get(TLineas.class, tren.getCodLinea());
			if (linea == null) {
				System.out.println("Tren: " + tren.getCodTren());
				System.out.println("No existe la linea: " + tren.getCodLinea());
				continue;
			}
			
			TCocheras cochera = (TCocheras) session.get(TCocheras.class, tren.getCodCochera());
			if (cochera == null) {
				System.out.println("Tren: " + tren.getCodTren());
				System.out.println("No existe la cochera: " + tren.getCodCochera());
				continue;
			}
			
			TTrenes t = (TTrenes) session.get(TTrenes.class, tren.getCodTren());
			if (t == null) {
				session.createMutationQuery("insert into TTrenes(codTren, nombre, TCocheras.codCochera, tipo, TLineas.codLinea) select codTren, nombre, codLinea, tipo, codCochera from TNuevosTrenes where codTren = :cod")
					.setParameter("cod", tren.getCodTren())
					.executeUpdate();
				
				System.out.println("Se ha insertado el tren: " + tren.getCodTren());
			} else {
				session.createMutationQuery("update TTrenes set nombre = :nombre, TCocheras.codCochera = :cochera, tipo = :tipo, TLineas.codLinea = :linea where codTren = :cod")
					.setParameter("nombre", tren.getNombre())
					.setParameter("cochera", tren.getCodCochera())
					.setParameter("tipo", tren.getTipo())
					.setParameter("linea", tren.getCodLinea())
					.setParameter("cod", tren.getCodTren())
					.executeUpdate();
				
				System.out.println("Se ha insertado el tren: " + tren.getCodTren());
			}
		}
		
		tx.commit();
		session.close();
	}

	private static void insertardepardenuevos() {
		Session session = sesion.openSession();
		System.out.println("\nInsertar nuevos departamentos\n----------------------------");
		Transaction tx = session.beginTransaction();

		String hqlInsert = "insert into Departamentos (deptNo, dnombre, loc)"
				+ " select n.deptNo, n.dnombre, n.loc from Nuevos n";

		try {
			int filascreadas = session.createMutationQuery(hqlInsert).executeUpdate();
			tx.commit();
			System.out.printf("FILAS INSERTADAS: %d%n", filascreadas);

		} catch (jakarta.persistence.PersistenceException e) {
			if (e.getMessage().contains("ConstraintViolationException")) {
				System.out.println("CLAVE DUPLICADA. ");
			} else if (e.getMessage().contains("DataException")) {
				System.out.println("ERROR EN LOS DATOS, DEMASIADOS CARACTERES");
			} else
				System.out.println("Ha ocurrido un error: " + e.getMessage());

		} catch (Exception e) {
			System.out.println("ERROR NO CONTROLADO....");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		session.close();
	}


	private static void modificaempledepart(int empNo, BigInteger dept) {
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		Departamentos depart = (Departamentos) session.get(Departamentos.class, dept);
		
		if (depart == null) {
			System.out.println("No existe el departamento: " + dept);
			return;
		}
		
		int filasMod = session.createMutationQuery("update Empleados set departamentos = :depart where empNo = :empNo")
				.setParameter("depart", depart)
				.setParameter("empNo", empNo)
				.executeUpdate();
		
		System.out.println("Filas modificadas: " + filasMod);
		
		tx.commit();   //valida la transacción
		session.close();
	}

	private static void modificaemplesalario(int empNo, float subida) {
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		int filasMod = session.createMutationQuery("update Empleados set salario = salario + :subida where empNo = :empNo")
				.setParameter("subida", subida)
				.setParameter("empNo", empNo)
				.executeUpdate();
		
		System.out.println("Filas modificadas: " + filasMod);
		
		tx.commit();   //valida la transacción
		session.close();
	}

	private static void updateDelete(String apellido, float salario, int dept) {
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		// Modificamos el salario de GIL

		String hqlModif = "update Empleados e set e.salario = :nuevoSal where e.apellido = :ape";
		int  filasModif=session.createMutationQuery( hqlModif )
			              .setParameter( "nuevoSal", salario)
			              .setParameter( "ape", apellido )
			              .executeUpdate();
			      
		System.out.println("FILAS MODIFICADAS: "+filasModif);	//Nº entidades afectadas    

		// Eliminamos los empleados del departamento 20

		String hqlDel = "delete Empleados e where e.departamentos.deptNo = ?1";
		int  filasDel=session.createMutationQuery( hqlDel )
			              .setParameter( 1, dept )
			              .executeUpdate();
			      
		System.out.println("FILAS ELIMINADAS: "+filasDel); //Nº entidades afectadas   

		//tx.rollback(); //Deshace la transacción
		tx.commit();   //valida la transacción
		session.close();
	}

	private static void trenesTipo() {
		Session session = sesion.openSession();
		Query cons = session.createQuery("select t.tipo, count(t.tipo) from TTrenes t GROUP BY t.tipo");
		
		System.out.printf("%-20s %6s%n", "TIPO", "NTIPOS");
		System.out.printf("%-20s %6s%n", "--------------------", "------");
		
		List datos = cons.list();
		for (Object o : datos) {
			Object[] data = (Object[]) o;
			String tipo = (String) data[0];
			Long n = (Long) data[1];
			System.out.printf("%-20s %6s%n", tipo, n);
		}
		
		System.out.printf("%-20s %6s%n", "--------------------", "------");
		
		session.close();
	}

	private static void accesosPorEstacion() {
		Session session = sesion.openSession();
		Query cons = session.createQuery("select new datos.AuxAccesosporestacion(e.codEstacion, e.nombre, e.direccion, count(a)) from TEstaciones e left join e.TAccesoses a group by e.codEstacion, e.nombre, e.direccion order by e.codEstacion", AuxAccesosporestacion.class);
		
		System.out.printf("%4s %-30s %-30s %8s%n", "CDES", "NOMBRE", "DIRECCION", "NACCESOS");
		System.out.printf("%4s %-30s %-30s %8s%n", "----", "------------------------------", "------------------------------", "--------");
		
		Long max = 0l;
		String nomMax = "";
		float suma = 0;
		
		List<AuxAccesosporestacion> lista = cons.list();
		for (AuxAccesosporestacion acc : lista) {
			System.out.printf("%4s %-30s %-30s %8s%n", acc.getCodEstacion(), acc.getNombre(), acc.getDireccion(), acc.getCuenta());
			
			if (acc.getCuenta() >= max) {
				if (acc.getCuenta() == max) {
					nomMax += acc.getNombre() + ". ";
				} else {
					nomMax = acc.getNombre() + ". ";
				}
				
				max = acc.getCuenta();
			}
			
			suma += acc.getCuenta();
		}
		
		System.out.printf("%4s %-30s %-30s %8s%n", "----", "------------------------------", "------------------------------", "--------");
		System.out.println("Maximo: " + nomMax);
		System.out.println("Media: " + (suma / lista.size()));
		
		session.close();
	}

	private static void verEstacionAcceso() {
		Session session = sesion.openSession();
		Query q=session.createQuery("select l,lt,ta from TLineas l join l.TLineaEstacions lt join lt.TEstaciones.TAccesoses ta order by l.codLinea");
		System.out.printf("%5s %-50s %-50s %5s %-50s%n","CODLIN","NOMBRELIN","CODESTACION","NOMBREESTACION","CODACCESO","DESC.ACCESO");
		System.out.printf("%5s %-50s %-50s %5s %-50s%n","-----","--------------------------------------------------","--------------------------------------------------","-----","--------------------------------------------------");
		
		
		List datos=q.list();
		
		for (Object object : datos) {
			Object[] par = (Object[]) object;
			TLineas li=(TLineas)par[0];
			TLineaEstacion lie=(TLineaEstacion)par[1];
			TAccesos ac=(TAccesos)par[2];
			
			System.out.printf("%5s %-50s %-50s %5s %-50s%n",li.getCodLinea(),li.getNombre(),lie.getTEstaciones().getCodEstacion(),lie.getTEstaciones().getNombre(),ac.getCodAcceso(),ac.getDescripcion());
			
		}
		session.close();
	}

	private static void consultasobjetos() {
		// TODO Auto-generated method stub
		Session session = sesion.openSession();
		Query cons = session.createQuery("from Empleados e, Departamentos d where  e.departamentos.deptNo=d.deptNo order by e.apellido");
		List datos = cons.list();
		
		for (Object listaDatos : datos) {
			Object[] par = (Object[]) listaDatos;
			Empleados em = (Empleados) par[0]; // objeto empleado el primero
			Departamentos de = (Departamentos) par[1]; // objeto departamento el segundo
			System.out.println(em.getApellido() + "*" + em.getSalario() + "*" + 
	                  de.getDnombre() + "*" + de.getLoc());
		}
		session.close();
			
	}

	private static void mostrarTrenesSerie(List<String> serie) {
		// TODO Auto-generated method stub
		Session session = sesion.openSession();
		Query<TTrenes> q=session.createQuery("from TTrenes t where t.tipo in(:serie) order by t.codTren",TTrenes.class);
		q.setParameterList("serie", serie);
		
		System.out.printf("%8s %-50s %-20s%n","COD TREN","NOMBRE","SERIE");
		System.out.printf("%8s %-50s %-20s%n","--------","--------------------------------------------------","--------------------");
		List<TTrenes> listaTrenes=q.list();
		
		for (TTrenes tTrenes : listaTrenes) {
			System.out.printf("%8s %-50s %-20s%n",tTrenes.getCodTren(),tTrenes.getNombre(),tTrenes.getTipo());
		}
		session.close();
		
	}

	private static void mostrarTrenesDir(String dir) {
		// TODO Auto-generated method stub
		Session session = sesion.openSession();
		Query<TTrenes> q=session.createQuery("from TTrenes t where t.TCocheras.direccion like :dir order by t.codTren",TTrenes.class);
		q.setParameter("dir", dir.toUpperCase()+"%");
		
		System.out.printf("%8s %-50s %6s%n","COD TREN","NOMBRE","COD CO");
		System.out.printf("%8s %-50s %6s%n","--------","--------------------------------------------------","------");
		List<TTrenes> listaTrenes=q.list();
		
		for (TTrenes tTrenes : listaTrenes) {
			System.out.printf("%8s %-50s %6s%n",tTrenes.getCodTren(),tTrenes.getNombre(),tTrenes.getTCocheras().getCodCochera());
		}
		session.close();
	}

	private static void verestacionesunique() {
		Session session = sesion.openSession();
//		COD ESTACIÓN   NOMBRE   DIRECCIÓN  NÚMERO ACCESOS  NÚMERO DE LÍNEAS
//		----------------------   ------------    ----------------  ---------------------------- -------------------------------
		Query<TEstaciones> q=session.createQuery("from TEstaciones e order by e.codEstacion",TEstaciones.class);
		List<TEstaciones> listaEstacion= q.list();
		System.out.printf("%12s %-50s %-50s %7s %7s %n","COD ESTACIÓN","NOMBRE","DIRECCION","NUMERO ACCESOS","NUMERO DE LINEAS");
		System.out.printf("%12s %-50s %-50s %7s %7s %n","----------------------","------------","----------------","----------------------------","-------------------------------");
		
		for (TEstaciones tEstacion : listaEstacion) {
			
			Long nAccesos=(Long) session.createQuery("select count(*) from TAccesos a where a.TEstaciones.codEstacion= "+tEstacion.getCodEstacion(),Long.class ).uniqueResult();
			Long nLineas=(Long) session.createQuery("select count(*) from TLineaEstacion l where l.TEstaciones.codEstacion= "+tEstacion.getCodEstacion(),Long.class ).uniqueResult();
			
			System.out.printf("%12s %-50s %-50s %7s %7s %n",tEstacion.getCodEstacion(),tEstacion.getNombre(),tEstacion.getDireccion(),nAccesos,nLineas);
		}
		session.close();
		
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
			System.out.printf("Trenes de la linea ",linea.getTTreneses());
			//mostrar los trenes de la linea
			if(linea.getTTreneses().size()>0) {
				System.out.printf("%5s %-40s %-40s %5s %-40s %n","COD","NOMBRE","TIPO","COD_COCHERA","NOMBRE_COCHERA");
				System.out.printf("%5s %-40s %-40s %5s %-40s %n","---","------","----","-----------","--------------");
				
				Set<TTrenes> listaTrenes=linea.getTTreneses();
				
				for(TTrenes tre:listaTrenes) {
					System.out.printf("%5s %-40s %-40s %5s %-40s %n",tre.getCodTren(),tre.getNombre(),tre.getTipo(),tre.getTCocheras().getCodCochera(),tre.getTCocheras().getNombre() );
				}
				
			}
			
		}
		
	}

}