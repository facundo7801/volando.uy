package uy.edu.fing.volandouy.modelo;

import java.util.ArrayList;
import java.util.List;

import uy.edu.fing.volandouy.dto.CategoriaDto;

public class Categoria {
	private String nombre;
	private List<RutaDeVuelo> rutasDeVuelo;

	// Constructor
	public Categoria(CategoriaDto categoriaDto) {
		this.nombre = categoriaDto.getNombre();
		this.rutasDeVuelo = new ArrayList<>();
	}

	public CategoriaDto toDto() {
		return new CategoriaDto(nombre);
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<RutaDeVuelo> getRutasDeVuelo() {
		return rutasDeVuelo;
	}

	public void setRutasDeVuelo(List<RutaDeVuelo> rutasDeVuelo) {
		this.rutasDeVuelo = rutasDeVuelo;
	}

	public void agregaRutaDeVuelo(RutaDeVuelo rutaDeVuelo) {
		this.rutasDeVuelo.add(rutaDeVuelo);
	}
}
