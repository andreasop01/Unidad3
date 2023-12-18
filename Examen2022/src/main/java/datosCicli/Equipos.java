package datosCicli;
// Generated 18 dic 2023 20:11:39 by Hibernate Tools 5.6.15.Final

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Equipos generated by hbm2java
 */
public class Equipos implements java.io.Serializable {

	private BigInteger codigoequipo;
	private String nombreequipo;
	private String director;
	private String pais;
	private Set resumenCamisetases = new HashSet(0);
	private Set ciclistases = new HashSet(0);

	public Equipos() {
	}

	public Equipos(BigInteger codigoequipo, String nombreequipo, String director, String pais) {
		this.codigoequipo = codigoequipo;
		this.nombreequipo = nombreequipo;
		this.director = director;
		this.pais = pais;
	}

	public Equipos(BigInteger codigoequipo, String nombreequipo, String director, String pais, Set resumenCamisetases,
			Set ciclistases) {
		this.codigoequipo = codigoequipo;
		this.nombreequipo = nombreequipo;
		this.director = director;
		this.pais = pais;
		this.resumenCamisetases = resumenCamisetases;
		this.ciclistases = ciclistases;
	}

	public BigInteger getCodigoequipo() {
		return this.codigoequipo;
	}

	public void setCodigoequipo(BigInteger codigoequipo) {
		this.codigoequipo = codigoequipo;
	}

	public String getNombreequipo() {
		return this.nombreequipo;
	}

	public void setNombreequipo(String nombreequipo) {
		this.nombreequipo = nombreequipo;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Set getResumenCamisetases() {
		return this.resumenCamisetases;
	}

	public void setResumenCamisetases(Set resumenCamisetases) {
		this.resumenCamisetases = resumenCamisetases;
	}

	public Set getCiclistases() {
		return this.ciclistases;
	}

	public void setCiclistases(Set ciclistases) {
		this.ciclistases = ciclistases;
	}

}
