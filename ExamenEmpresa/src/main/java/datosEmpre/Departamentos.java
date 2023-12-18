package datosEmpre;
// Generated 18 dic 2023 16:12:57 by Hibernate Tools 5.6.15.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Departamentos generated by hbm2java
 */
public class Departamentos implements java.io.Serializable {

	private long coddepart;
	private Empleados empleados;
	private Empresas empresas;
	private String nombre;
	private String direccion;
	private String localidad;
	private Set empleadoses = new HashSet(0);

	public Departamentos() {
	}

	public Departamentos(long coddepart, Empresas empresas, String nombre) {
		this.coddepart = coddepart;
		this.empresas = empresas;
		this.nombre = nombre;
	}

	public Departamentos(long coddepart, Empleados empleados, Empresas empresas, String nombre, String direccion,
			String localidad, Set empleadoses) {
		this.coddepart = coddepart;
		this.empleados = empleados;
		this.empresas = empresas;
		this.nombre = nombre;
		this.direccion = direccion;
		this.localidad = localidad;
		this.empleadoses = empleadoses;
	}

	public long getCoddepart() {
		return this.coddepart;
	}

	public void setCoddepart(long coddepart) {
		this.coddepart = coddepart;
	}

	public Empleados getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(Empleados empleados) {
		this.empleados = empleados;
	}

	public Empresas getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(Empresas empresas) {
		this.empresas = empresas;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Set getEmpleadoses() {
		return this.empleadoses;
	}

	public void setEmpleadoses(Set empleadoses) {
		this.empleadoses = empleadoses;
	}

}
