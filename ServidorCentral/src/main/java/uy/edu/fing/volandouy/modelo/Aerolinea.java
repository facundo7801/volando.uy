package uy.edu.fing.volandouy.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uy.edu.fing.volandouy.dto.AerolineaDto;

public class Aerolinea extends Usuario {
	private String descripcion;
	private String website;
	private Map<String, RutaDeVuelo> rutasDeVuelo;

	// Constructor
	public Aerolinea(AerolineaDto aerolinea) {
		super(aerolinea.getNickName(), aerolinea.getNombre(), aerolinea.getEmail(), aerolinea.getFechaAlta(), aerolinea.getContrasenia(), aerolinea.getImagen());
		this.descripcion = aerolinea.getDescripcion();
		this.website = aerolinea.getWebsite();
		this.rutasDeVuelo = new TreeMap<>();
	}

	// Getters y Setters
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

	public Map<String, RutaDeVuelo> getRutasDeVuelo() {
		return rutasDeVuelo;
	}

	@Override
	public AerolineaDto toDto() {
		List<String> nombreRutas = new ArrayList<>();
		for (String nombreRuta : rutasDeVuelo.keySet()) {
			nombreRutas.add(nombreRuta);
		}
		List<String> seguidos = new ArrayList<>();
		for (String nick : getSeguidos().keySet()) {
			seguidos.add(nick);
		}
		
		List<String> seguidores = new ArrayList<>();
		for (String nick : getSeguidores().keySet()) {
			seguidores.add(nick);
		}

		return new AerolineaDto(getNickName(), getNombre(), getEmail(), descripcion, website, nombreRutas, getFechaAlta(), getContrasenia(), getImagen(), seguidos, seguidores);
	}

}
