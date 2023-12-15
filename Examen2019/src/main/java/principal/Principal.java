package principal;

import java.math.BigInteger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datosExamen.*;



public class Principal {
	private static SessionFactory sesion;
	public static void main(String[] args) {
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		sesion=Conexion.getSession();
		
		ejercicio2("1010",BigInteger.valueOf(1),BigInteger.valueOf(1),"10/12/2023","14/12/2023");
		sesion.close();

	}
	private static void ejercicio2(String numHab, BigInteger codCli, BigInteger suple, String fechaEntrada,
			String fechaSalida) {
		Session session = sesion.openSession();
		T3habitaciones h=(T3habitaciones)session.get(T3habitaciones.class,numHab);
		if(h==null) {
			System.out.println("Habitacion no existe");
			return;
		}
		
		T3clientes c=(T3clientes)session.get(T3clientes.class,codCli);
		if(c==null) {
			System.out.println("Codigo de cliente no existe");
			return;
		}
		
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		formato.setLenient(false);
		Date mifechaE =null;
		Date mifechaS=null;
		try {
			mifechaE = (Date) formato.parse(fechaEntrada);
			mifechaS = (Date) formato.parse(fechaSalida);
			
		} catch (ParseException e) {
			System.out.println("Formato de fecha incorrecta");
			
			return;
		}
		
		Query cons=session.createQuery("from T3reservas r where r.t3habitaciones.numhabitacion=:numHab and (:fechaEntrada between r.fechaentrada and r.fechasalida ) or (:fechaSalida between r.fechaentrada and r.fechasalida)",T3reservas.class)
				.setParameter("numHab", numHab)
				.setParameter("fechaEntrada", fechaEntrada)
				.setParameter("fechaSalida", fechaSalida);

		List<T3reservas> listaReservas=cons.list();
		if(!listaReservas.isEmpty()) {
			System.out.println("Ya existe una reserva para esta fecha");
			return;
		}
		
		if(fechaEntrada.equals(fechaSalida) || !mifechaE.before(mifechaS) ) {
			System.out.println("Fechas incorrectas");
			return;
		}
		
		int descuento=0;
		int mes=Integer.parseInt(fechaEntrada.split("/")[1]);
		int trimestre=(mes-1)/3;
		
		if(trimestre==0) {
			descuento=10;
		}else if(trimestre==1){
			descuento=4;
		}else if(trimestre==2) {
			descuento=0;
		}else{
			descuento=5;
		}
		
		Transaction tx = session.beginTransaction();

		String consInsert = "insert into Departamentos (deptNo, dnombre, loc)"
				+ " select n.deptNo, n.dnombre, n.loc from Nuevos n";

	}

}
