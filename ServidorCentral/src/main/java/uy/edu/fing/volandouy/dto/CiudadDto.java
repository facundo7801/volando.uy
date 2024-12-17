package uy.edu.fing.volandouy.dto;

import java.util.Date;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"nombre",
		"pais",
		"nombreAeropuerto",
		"descripcion",
		"website",
		"fechaAlta"
})
public class CiudadDto {
	@XmlElement
	private String nombre;
	@XmlElement
	private String pais;
	@XmlElement
	private String nombreAeropuerto;
	@XmlElement
	private String descripcion;
	@XmlElement
	private String website;
	@XmlElement
	private Date fechaAlta;

	public CiudadDto() {
		this.nombre = null;
		this.pais = null;
		this.nombreAeropuerto = null;
		this.descripcion = null;
		this.website = null;
		this.fechaAlta = null;
	}
	
	// Constructor
	public CiudadDto(String nombre, String pais, String nombreAeropuerto, String descripcion, String website,
			Date fechaAlta) {
		this.nombre = nombre;
		this.pais = pais;
		this.nombreAeropuerto = nombreAeropuerto;
		this.descripcion = descripcion;
		this.website = website;
		this.fechaAlta = fechaAlta;
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getNombreAeropuerto() {
		return nombreAeropuerto;
	}

	public void setNombreAeropuerto(String nombreAeropuerto) {
		this.nombreAeropuerto = nombreAeropuerto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
}
