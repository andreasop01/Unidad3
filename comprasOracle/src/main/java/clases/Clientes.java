package clases;
// Generated 20 nov 2023 20:28:47 by Hibernate Tools 6.1.7.Final

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Clientes generated by hbm2java
 */
public class Clientes implements java.io.Serializable {

	private BigInteger codcliente;
	private String nombre;
	private String poblacion;
	private String tlf;
	private Set comprases = new HashSet(0);

	public Clientes() {
	}

	public Clientes(BigInteger codcliente) {
		this.codcliente = codcliente;
	}

	public Clientes(BigInteger codcliente, String nombre, String poblacion, String tlf, Set comprases) {
		this.codcliente = codcliente;
		this.nombre = nombre;
		this.poblacion = poblacion;
		this.tlf = tlf;
		this.comprases = comprases;
	}

	public BigInteger getCodcliente() {
		return this.codcliente;
	}

	public void setCodcliente(BigInteger codcliente) {
		this.codcliente = codcliente;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getTlf() {
		return this.tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public Set getComprases() {
		return this.comprases;
	}

	public void setComprases(Set comprases) {
		this.comprases = comprases;
	}

}
