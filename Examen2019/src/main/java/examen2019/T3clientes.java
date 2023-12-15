package examen2019;
// Generated 14 dic 2023 15:59:52 by Hibernate Tools 5.6.15.Final

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * T3clientes generated by hbm2java
 */
public class T3clientes implements java.io.Serializable {

	private BigInteger codigocliente;
	private String nombrecliente;
	private String apellido;
	private String telefono;
	private String direccion;
	private String ciudad;
	private String region;
	private String pais;
	private String codigopostal;
	private Double limitecredito;
	private Set t3reservases = new HashSet(0);

	public T3clientes() {
	}

	public T3clientes(BigInteger codigocliente, String nombrecliente, String ciudad, String pais) {
		this.codigocliente = codigocliente;
		this.nombrecliente = nombrecliente;
		this.ciudad = ciudad;
		this.pais = pais;
	}

	public T3clientes(BigInteger codigocliente, String nombrecliente, String apellido, String telefono,
			String direccion, String ciudad, String region, String pais, String codigopostal, Double limitecredito,
			Set t3reservases) {
		this.codigocliente = codigocliente;
		this.nombrecliente = nombrecliente;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.region = region;
		this.pais = pais;
		this.codigopostal = codigopostal;
		this.limitecredito = limitecredito;
		this.t3reservases = t3reservases;
	}

	public BigInteger getCodigocliente() {
		return this.codigocliente;
	}

	public void setCodigocliente(BigInteger codigocliente) {
		this.codigocliente = codigocliente;
	}

	public String getNombrecliente() {
		return this.nombrecliente;
	}

	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodigopostal() {
		return this.codigopostal;
	}

	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}

	public Double getLimitecredito() {
		return this.limitecredito;
	}

	public void setLimitecredito(Double limitecredito) {
		this.limitecredito = limitecredito;
	}

	public Set getT3reservases() {
		return this.t3reservases;
	}

	public void setT3reservases(Set t3reservases) {
		this.t3reservases = t3reservases;
	}

}