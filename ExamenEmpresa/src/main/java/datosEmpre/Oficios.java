package datosEmpre;
// Generated 18 dic 2023 16:12:57 by Hibernate Tools 5.6.15.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Oficios generated by hbm2java
 */
public class Oficios implements java.io.Serializable {

	private long codoficio;
	private String nombre;
	private Double salariomes;
	private Double preciotrienio;
	private Set empleadoses = new HashSet(0);

	public Oficios() {
	}

	public Oficios(long codoficio, String nombre) {
		this.codoficio = codoficio;
		this.nombre = nombre;
	}

	public Oficios(long codoficio, String nombre, Double salariomes, Double preciotrienio, Set empleadoses) {
		this.codoficio = codoficio;
		this.nombre = nombre;
		this.salariomes = salariomes;
		this.preciotrienio = preciotrienio;
		this.empleadoses = empleadoses;
	}

	public long getCodoficio() {
		return this.codoficio;
	}

	public void setCodoficio(long codoficio) {
		this.codoficio = codoficio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getSalariomes() {
		return this.salariomes;
	}

	public void setSalariomes(Double salariomes) {
		this.salariomes = salariomes;
	}

	public Double getPreciotrienio() {
		return this.preciotrienio;
	}

	public void setPreciotrienio(Double preciotrienio) {
		this.preciotrienio = preciotrienio;
	}

	public Set getEmpleadoses() {
		return this.empleadoses;
	}

	public void setEmpleadoses(Set empleadoses) {
		this.empleadoses = empleadoses;
	}

}
