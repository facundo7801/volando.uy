package uy.edu.fing.volandouy.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"nombre"
})
public class CategoriaDto {
	@XmlElement
	private String nombre;

	public CategoriaDto() {
		this.nombre = null;
	}
	
	// Constructor
	public CategoriaDto(String nombre) {
		this.nombre = nombre;
	}

	// Getters
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
