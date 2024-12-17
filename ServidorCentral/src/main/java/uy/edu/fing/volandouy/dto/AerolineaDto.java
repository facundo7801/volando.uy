package uy.edu.fing.volandouy.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aerolineaDto", propOrder = {
    "descripcion",
    "website",
    "rutasDeVuelo"
})

public class AerolineaDto extends UsuarioDto {
	@XmlElement
	private String descripcion;
	@XmlElement
	private String website;
	@XmlElement
	private List<String> rutasDeVuelo;

	//Constructor para el web service
	public AerolineaDto() {
		super(null, null, null, null, null, null, null, null);
		descripcion = null;
		website = null;
		rutasDeVuelo = null;
	}
	
	// Constructor
	public AerolineaDto(String nickName, String nombre, String email, String descripcion, String website,
			List<String> rutasDeVuelo, Date fechaAlta, String contrasenia, String imagen, List<String> seguidos, List<String> seguidores) {
		super(nickName, nombre, email, fechaAlta, contrasenia, imagen, seguidos, seguidores);
		this.descripcion = descripcion;
		this.website = website;
		this.rutasDeVuelo = rutasDeVuelo == null ? new ArrayList<>() : rutasDeVuelo;
	}

	// Getters
	public String getDescripcion() {
		return descripcion;
	}

	public String getWebsite() {
		return website;
	}

	public List<String> getRutasDeVuelo() {
		return rutasDeVuelo;
	}

	public void setDescripcion(String string) {
		this.descripcion = string;

	}

	public void setWebsite(String string) {
		this.website = string;

	}
	
	public void setRutasDeVuelo (List<String> rutasDeVuelo) {
		this.rutasDeVuelo = rutasDeVuelo;
	}

	@Override
	public void metodoAbstracto() {
		// TODO Auto-generated method stub
		
	}

}
