package datos2;
// Generated 24 nov 2023 17:41:54 by Hibernate Tools 5.6.15.Final

import java.util.HashSet;
import java.util.Set;

/**
 * C1Especialidad generated by hbm2java
 */
public class C1Especialidad implements java.io.Serializable {

	private String especialidad;
	private String nombreEspe;
	private Set c1Profesoreses = new HashSet(0);

	public C1Especialidad() {
	}

	public C1Especialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public C1Especialidad(String especialidad, String nombreEspe, Set c1Profesoreses) {
		this.especialidad = especialidad;
		this.nombreEspe = nombreEspe;
		this.c1Profesoreses = c1Profesoreses;
	}

	public String getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getNombreEspe() {
		return this.nombreEspe;
	}

	public void setNombreEspe(String nombreEspe) {
		this.nombreEspe = nombreEspe;
	}

	public Set getC1Profesoreses() {
		return this.c1Profesoreses;
	}

	public void setC1Profesoreses(Set c1Profesoreses) {
		this.c1Profesoreses = c1Profesoreses;
	}

}
