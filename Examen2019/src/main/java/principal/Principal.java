package principal;

import java.math.BigInteger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
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
		
		//ejercicio2("1010",BigInteger.valueOf(1),BigInteger.valueOf(1),"10/12/2023","14/12/2023");
		ejercicio3();
		
		sesion.close();

	}
	private static void ejercicio3() {
		
		Session session = sesion.openSession();
		Query cons=session.createQuery("from T3clientes",T3clientes.class);
		
		List<T3clientes> listaCli=cons.list();
		for (T3clientes cli : listaCli) {
			System.out.println("Cod Cli: "+cli.getCodigocliente()+" Nombre: "+cli.getNombrecliente()+" Apellido: "+cli.getApellido());
			if(cli.getT3reservases().size()==0) {
				System.out.println("El cliente no tiene reservas");
				continue;
			}
			System.out.printf("%6s %8s %7s %12s %11s %10s %4s %6s %7s %9s %10s%n","CODRES","NUMHABIT","CAMSUPL","FECHAENTRADA","FECHASALIDA","TOTALSUPLE","DIAS","PVPDIA","IMPORTE","IMPORDESC","IMPORTOTAL\r\n");
			System.out.printf("%6s %8s %7s %12s %11s %10s %4s %6s %7s %9s %10s%n", "------", "--------", "-------", "------------", "-----------", "----------", "----", "------", "-------", "---------", "----------");
			
			int totalSup=0;
			int totalDias=0;
			double totalPvd=0;
			int totalImport=0;
			int totalImportDesc=0;
			int totalT=0;
			double importMax=0;
			String nResmax="";
			
			Set<T3reservas> listRes=cli.getT3reservases();
			for (T3reservas res : listRes ) {
				
				long difMS = res.getFechasalida().getTime() - res.getFechaentrada().getTime();
				long difDias = TimeUnit.DAYS.convert(difMS, TimeUnit.MILLISECONDS);
				double importe=(res.getT3habitaciones().getT3tiposhabitaciones().getPrecio()+res.getCamassupletorias().intValue()*10)*difDias;
				double desc=importe*(res.getDescuento().intValue()/100);
				totalSup+=res.getCamassupletorias().intValue()*10;
				totalDias+=difDias;
				totalPvd+=res.getT3habitaciones().getT3tiposhabitaciones().getPrecio();
				totalImport+=importe;
				totalImportDesc+=desc;
				totalT+=(importe-desc);
				
				if(importMax==importe-desc) {
					nResmax+=res.getCodreserva()+". ";
				}
				if(importe-desc>importMax) {
					nResmax=res.getCodreserva()+". ";
					importMax=importe-desc;
				}
				
				System.out.printf("%6s %8s %7s %12s %11s %10s %4s %6s %7s %9s %10s%n",res.getCodreserva(),res.getT3habitaciones().getNumhabitacion(),res.getCamassupletorias(),res.getFechaentrada().toString(),res.getFechasalida().toString(),(res.getCamassupletorias().intValue()*10),
						difDias,res.getT3habitaciones().getT3tiposhabitaciones().getPrecio(),importe,desc,(importe-desc));
			}
			
			System.out.printf("%6s %8s %7s %12s %11s %10s %4s %6s %7s %9s %10s%n", "------", "--------", "-------", "------------", "-----------", "----------", "----", "------", "-------", "---------", "----------");
			System.out.printf("%9s %5s %7s %12s %11s %10s %4s %6s %7s %9s %10s%n", "TOTALES: ", "", "", "", "", totalSup,totalDias,totalPvd,totalImport,totalImportDesc,totalT);
			System.out.println("Número de reserva con más importe total (si hay varias visualizarlas): "+nResmax);
			
		
		}
		
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
		BigInteger codRes=(BigInteger) session.createQuery("select max(r.codreserva)+1 from T3reservas r",BigInteger.class).uniqueResult();
		T3reservas r=new T3reservas();
		r.setCodreserva(codRes);
		r.setT3clientes(c);
		r.setT3habitaciones(h);
		r.setCamassupletorias(suple);
		r.setFechaentrada(mifechaE);
		r.setFechasalida(mifechaS);
		r.setDescuento(BigInteger.valueOf(descuento));
		
		session.persist(r);
		session.close();
		
	}

}
