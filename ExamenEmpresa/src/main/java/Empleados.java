// default package
// Generated 17 dic 2023 15:30:51 by Hibernate Tools 5.6.15.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Empleados generated by hbm2java
 */
public class Empleados implements java.io.Serializable {

	private long codemple;
	private Empleados empleados;
	private Departamentos departamentos;
	private Oficios oficios;
	private String nombre;
	private String direccion;
	private String poblacion;
	private Date fechaalta;
	private Set empleadoses = new HashSet(0);
	private Set departamentoses = new HashSet(0);

	public Empleados() {
	}

	public Empleados(long codemple, Departamentos departamentos, Oficios oficios, String nombre) {
		this.codemple = codemple;
		this.departamentos = departamentos;
		this.oficios = oficios;
		this.nombre = nombre;
	}

	public Empleados(long codemple, Empleados empleados, Departamentos departamentos, Oficios oficios, String nombre,
			String direccion, String poblacion, Date fechaalta, Set empleadoses, Set departamentoses) {
		this.codemple = codemple;
		this.empleados = empleados;
		this.departamentos = departamentos;
		this.oficios = oficios;
		this.nombre = nombre;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.fechaalta = fechaalta;
		this.empleadoses = empleadoses;
		this.departamentoses = departamentoses;
	}

	public long getCodemple() {
		return this.codemple;
	}

	public void setCodemple(long codemple) {
		this.codemple = codemple;
	}

	public Empleados getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(Empleados empleados) {
		this.empleados = empleados;
	}

	public Departamentos getDepartamentos() {
		return this.departamentos;
	}

	public void setDepartamentos(Departamentos departamentos) {
		this.departamentos = departamentos;
	}

	public Oficios getOficios() {
		return this.oficios;
	}

	public void setOficios(Oficios oficios) {
		this.oficios = oficios;
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

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public Date getFechaalta() {
		return this.fechaalta;
	}

	public void setFechaalta(Date fechaalta) {
		this.fechaalta = fechaalta;
	}

	public Set getEmpleadoses() {
		return this.empleadoses;
	}

	public void setEmpleadoses(Set empleadoses) {
		this.empleadoses = empleadoses;
	}

	public Set getDepartamentoses() {
		return this.departamentoses;
	}

	public void setDepartamentoses(Set departamentoses) {
		this.departamentoses = departamentoses;
	}

}
