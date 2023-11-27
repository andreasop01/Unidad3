package datos2;
// Generated 24 nov 2023 18:09:52 by Hibernate Tools 5.6.15.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * C1Profesores generated by hbm2java
 */
public class C1Profesores implements java.io.Serializable {

	private short codProf;
	private C1Centros c1Centros;
	private C1Especialidad c1Especialidad;
	private C1Profesores c1Profesores;
	private String nombreApe;
	private Date fechaNac;
	private Character sexo;
	private Set c1Asignaturases = new HashSet(0);
	private Set c1Profesoreses = new HashSet(0);

	public C1Profesores() {
	}

	public C1Profesores(short codProf) {
		this.codProf = codProf;
	}

	public C1Profesores(short codProf, C1Centros c1Centros, C1Especialidad c1Especialidad, C1Profesores c1Profesores,
			String nombreApe, Date fechaNac, Character sexo, Set c1Asignaturases, Set c1Profesoreses) {
		this.codProf = codProf;
		this.c1Centros = c1Centros;
		this.c1Especialidad = c1Especialidad;
		this.c1Profesores = c1Profesores;
		this.nombreApe = nombreApe;
		this.fechaNac = fechaNac;
		this.sexo = sexo;
		this.c1Asignaturases = c1Asignaturases;
		this.c1Profesoreses = c1Profesoreses;
	}

	public short getCodProf() {
		return this.codProf;
	}

	public void setCodProf(short codProf) {
		this.codProf = codProf;
	}

	public C1Centros getC1Centros() {
		return this.c1Centros;
	}

	public void setC1Centros(C1Centros c1Centros) {
		this.c1Centros = c1Centros;
	}

	public C1Especialidad getC1Especialidad() {
		return this.c1Especialidad;
	}

	public void setC1Especialidad(C1Especialidad c1Especialidad) {
		this.c1Especialidad = c1Especialidad;
	}

	public C1Profesores getC1Profesores() {
		return this.c1Profesores;
	}

	public void setC1Profesores(C1Profesores c1Profesores) {
		this.c1Profesores = c1Profesores;
	}

	public String getNombreApe() {
		return this.nombreApe;
	}

	public void setNombreApe(String nombreApe) {
		this.nombreApe = nombreApe;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Character getSexo() {
		return this.sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public Set getC1Asignaturases() {
		return this.c1Asignaturases;
	}

	public void setC1Asignaturases(Set c1Asignaturases) {
		this.c1Asignaturases = c1Asignaturases;
	}

	public Set getC1Profesoreses() {
		return this.c1Profesoreses;
	}

	public void setC1Profesoreses(Set c1Profesoreses) {
		this.c1Profesoreses = c1Profesoreses;
	}

}
