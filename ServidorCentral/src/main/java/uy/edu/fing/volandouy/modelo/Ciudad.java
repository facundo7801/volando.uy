package uy.edu.fing.volandouy.modelo;

import java.util.Date;

import uy.edu.fing.volandouy.dto.CiudadDto;

public class Ciudad {
	private String nombre;
	private String pais;
	private String nombreAeropuerto;
	private String descripcion;
	private String website;
	private Date fechaAlta;

	// Constructor
	public Ciudad(CiudadDto ciudadDto) {
		this.nombre = ciudadDto.getNombre();
		this.pais = ciudadDto.getPais();
		this.nombreAeropuerto = ciudadDto.getNombreAeropuerto();
		this.descripcion = ciudadDto.getDescripcion();
		this.website = ciudadDto.getWebsite();
		this.fechaAlta = ciudadDto.getFechaAlta();
	}

	public CiudadDto toDto() {
		return new CiudadDto(nombre, pais, nombreAeropuerto, descripcion, website, fechaAlta);
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
