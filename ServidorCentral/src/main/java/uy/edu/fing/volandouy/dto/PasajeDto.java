package uy.edu.fing.volandouy.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"nombre",
		"apellido",
		"numeroAsiento"
})
public class PasajeDto {
	@XmlElement
	private String nombre;
	@XmlElement
	private String apellido;
	@XmlElement
	private String numeroAsiento;

	public PasajeDto() {
		this.nombre = null;
		this.apellido = null;
		this.numeroAsiento = null;
	}
	
	public PasajeDto(String nombre, String apellido, String numeroAsiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.numeroAsiento = numeroAsiento;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getNumeroAsiento() {
		return numeroAsiento;
	}
	
	public void setNumeroAsiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}
	
}
