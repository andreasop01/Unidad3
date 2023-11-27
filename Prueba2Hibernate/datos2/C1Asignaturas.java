package datos2;
// Generated 24 nov 2023 17:41:54 by Hibernate Tools 5.6.15.Final

import java.util.HashSet;
import java.util.Set;

/**
 * C1Asignaturas generated by hbm2java
 */
public class C1Asignaturas implements java.io.Serializable {

	private String codAsig;
	private String nombreAsi;
	private Set c1Profesoreses = new HashSet(0);

	public C1Asignaturas() {
	}

	public C1Asignaturas(String codAsig) {
		this.codAsig = codAsig;
	}

	public C1Asignaturas(String codAsig, String nombreAsi, Set c1Profesoreses) {
		this.codAsig = codAsig;
		this.nombreAsi = nombreAsi;
		this.c1Profesoreses = c1Profesoreses;
	}

	public String getCodAsig() {
		return this.codAsig;
	}

	public void setCodAsig(String codAsig) {
		this.codAsig = codAsig;
	}

	public String getNombreAsi() {
		return this.nombreAsi;
	}

	public void setNombreAsi(String nombreAsi) {
		this.nombreAsi = nombreAsi;
	}

	public Set getC1Profesoreses() {
		return this.c1Profesoreses;
	}

	public void setC1Profesoreses(Set c1Profesoreses) {
		this.c1Profesoreses = c1Profesoreses;
	}

}
