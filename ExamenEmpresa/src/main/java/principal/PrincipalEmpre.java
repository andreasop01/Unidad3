package principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datosEmpre.Departamentos;
import datosEmpre.Empleados;
import datosEmpre.Empresas;
import datosEmpre.Oficios;


public class PrincipalEmpre {
	private static SessionFactory sesion;
	public static void main(String[] args) {
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		sesion=Conexion.getSession();
		
		//ejercicio1(1);
		//ejercicio2("hjfh","ddsgsd","jkdsghijk",1,11);
		//ejercicio3();
		//ejercicio4(11,"fdgsdfg","dkhf","jhfsd",107,3);
		ejercicio5();
		
		sesion.close();
	

	}
	
	private static void ejercicio5() {
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		Query cons=session.createQuery("from Empresas",Empresas.class);
		
		int tPresupuestoA=0;
		int tnEmple=0;
		int tsumSala=0;
		int tnuevoPre=0;
		System.out.printf("%10s %-35s %-60s %15s %10s %10s %10s%n","Codigo-emp","Nombre","Direccion","Presup-Ant","Num-emples","Sum Salarios","Presup nuevo");
		System.out.printf("%10s %-35s %-60s %15s %10s %10s %10s%n","----------","-----------------------------------","------------------------------------------------------------","---------------","----------","----------","----------");
		
		List<Empresas> listEmpre=cons.list();
		for (Empresas emp : listEmpre) {
			int nEmpleados=0;
			int sumSalario=0;
			
			Set<Departamentos> listDepa=emp.getDepartamentoses();
			for (Departamentos depa : listDepa) {
				nEmpleados+=depa.getEmpleadoses().size();
				
				Set<Empleados> listaEmple=depa.getEmpleadoses();
				for (Empleados emple : listaEmple) {
					sumSalario+=emple.getOficios().getSalariomes();
				}
			}
			
			double nuevoPre=emp.getPresupuesto()+sumSalario;
			tPresupuestoA+=emp.getPresupuesto();
			tnEmple+=nEmpleados;
			tsumSala+=sumSalario;
			tnuevoPre+=nuevoPre;
			System.out.printf("%10s %-35s %-60s %15s %10s %10s %10s%n",emp.getCodempre(),emp.getNombre(),emp.getDireccion(),emp.getPresupuesto(),nEmpleados,sumSalario,nuevoPre);
			
			emp.setPresupuesto(nuevoPre);
			session.merge(emp);
			
		}
		
		System.out.printf("%10s %-35s %-60s %15s %10s %10s %10s%n","----------","-----------------------------------","------------------------------------------------------------","---------------","----------","----------","----------");
		System.out.printf("%10s %-35s %-60s %15s %10s %10s %10s%n","TOTALES: ","","",tPresupuestoA,tnEmple,tsumSala,tnuevoPre);

		tx.commit();
		session.close();
	}

	private static void ejercicio4(int codDep, String nomDep, String direcDep, String locaDep, int codJefe, int codEmpresa) {
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		boolean err= false;
		
		Departamentos dep=(Departamentos)session.get(Departamentos.class, codDep);
		if(dep==null) {
			System.out.println("Departemento no existe");
			err=true;
		}
		
		Empleados jefe=(Empleados)session.get(Empleados.class,codJefe );
		if(jefe==null) {
			System.out.println("No existe Empleado");
			err=true;
		}
		
		Empresas e=(Empresas)session.get(Empresas.class, codEmpresa);
		if(e==null) {
			System.out.println("No existe cod empresa");
			err=true;
		}
		
		if(err) {
			System.out.println("No se ha podido actualizar");
			return;
		}
		
		dep.setNombre(nomDep);
		dep.setDireccion(direcDep);
		dep.setLocalidad(locaDep);
		dep.setEmpleados(jefe);
		dep.setEmpresas(e);
		
		System.out.println("Se ha actualizado el departamento");
		session.merge(dep);
		tx.commit();
		session.close();
			
	}

	private static void ejercicio3() {
		Session session = sesion.openSession();
		Query cons=session.createQuery("from Oficios",Oficios.class);
		int nEmpleados=0;
		
		List<Oficios> listaOfi=cons.getResultList();
		System.out.println("Datos de Oficios");
		System.out.printf("%10s %-30s %10s %16s %14s %10s%n","Codigo","Nombre","Salario Mes","Numero Empleados","Precio Trienio","Total Salario");
		System.out.printf("%10s %-30s %10s %16s %14s %10s%n","----------","------------------------------","----------","----------------","--------------","----------");
		for (Oficios ofi : listaOfi) {

			nEmpleados=ofi.getEmpleadoses().size();
			System.out.printf("%10s %-30s %10s %16s %14s %10s%n",ofi.getCodoficio(),ofi.getNombre(),ofi.getSalariomes(),nEmpleados,ofi.getPreciotrienio(),(ofi.getSalariomes()+ofi.getPreciotrienio()));
			
		}
		
	}
	private static void ejercicio2(String nomEmple, String direc , String poblacion, long codOfi, long codDepart) {
		Session session = sesion.openSession();
		System.out.println("\nInsertar nuevos empleados\n----------------------------");
		Transaction tx = session.beginTransaction();
		boolean err=false;
		
		Oficios o=(Oficios)session.get(Oficios.class,codOfi);
		if(o==null) {
			System.out.println("Oficio no existe");
			err=true;
		}
		
		Departamentos d=(Departamentos)session.get(Departamentos.class,codDepart);
		if(d==null) {
			System.out.println("Departamento no existe");
			err=true;
		}
		
		if(err) {
			System.out.println("No se ha insertado");
			return;
		}
		
		Empleados jefe=d.getEmpleados(); 
		Date fechaActual=new Date();
		
		
		
		long codEmple=(long)session.createQuery("select max(e.codemple)+1 from Empleados e",long.class).uniqueResult();
		
		Empleados e=new Empleados();
		e.setNombre(nomEmple);
		e.setDireccion(direc);
		e.setPoblacion(poblacion);
		e.setOficios(o);
		e.setDepartamentos(d);
		e.setFechaalta(fechaActual);
		e.setEmpleados(jefe);
		e.setCodemple(codEmple);
		
		System.out.println("Se ha insertado");
		
		session.persist(e);
		tx.commit();
		session.close();
	}
	private static void ejercicio1(long codEmp) {
		Session session = sesion.openSession();
		Empresas e=(Empresas)session.get(Empresas.class,codEmp);
		
		if(e==null) {
			System.out.println("No existe Empresa");
			return;
		}
		
		int nDpart=e.getDepartamentoses().size();

		System.out.println("COD-EMPRESA: "+e.getCodempre()+" NOMBRE: "+e.getNombre());
		System.out.println("DIRECCION: "+e.getDireccion()+" Numero de departamentos: "+nDpart);
		System.out.println("-----------------------------------------------------------------------------------");
		
		Set<Departamentos> listDepart=e.getDepartamentoses();
		for (Departamentos dep : listDepart) {
			System.out.println("COD-DEPARTAMENTO: "+dep.getCoddepart()+" NOMBRE: "+dep.getNombre()+" LOCALIDAD: "+dep.getLocalidad());
			
			if(dep.getEmpleadoses().size()==0) {
				System.out.println("NO tiene empleados");
				continue;
			}
			
			System.out.printf("%12s %-35s %-30s %-35s%n","COD-EMPLEADO","NOMBRE","OFICIO","NOMBRE ENCARGADO");
			System.out.printf("%12s %-35s %-30s %-35s%n","------------","-----------------------------------","------------------------------","-----------------------------------");
			
			Set<Empleados> listEmple=dep.getEmpleadoses();
			for (Empleados emp : listEmple) {
				String nomEncargado="";
				
				if(emp.getEmpleados()==null) {
					nomEncargado="No tiene encargado";
				}else {
					nomEncargado=emp.getEmpleados().getNombre();
				}
				
//				try {
//					nomEncargado=emp.getEmpleados().getNombre();
//				} catch (Exception e2) {
//					nomEncargado="No tiene encargado";
//				}
				System.out.printf("%12s %-35s %-30s %-35s%n",emp.getCodemple(),emp.getNombre(),emp.getOficios().getNombre(),nomEncargado);
			}
			
			System.out.printf("%12s %-35s %-30s %-35s%n","------------","-----------------------------------","------------------------------","-----------------------------------");
			System.out.println("Numero de empleados por departamento: "+dep.getEmpleadoses().size());
			System.out.println("Nombre del jefe del departamento: "+dep.getEmpleados().getNombre());
			
		}
		
	}

}
