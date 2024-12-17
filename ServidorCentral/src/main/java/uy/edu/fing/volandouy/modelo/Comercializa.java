package uy.edu.fing.volandouy.modelo;

import uy.edu.fing.volandouy.dto.ComercializaDto;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;

public class Comercializa {
	private int cantRutasDeVuelo;
	private TipoAsiento tipoDeAsiento;
	private RutaDeVuelo rutaDeVuelo;
	private Paquete paquete;

	// Constructor
	public Comercializa(int cantRutasDeVuelo, TipoAsiento tipoDeAsiento, RutaDeVuelo rutaDeVuelo, Paquete paquete) {
		this.cantRutasDeVuelo = cantRutasDeVuelo;
		this.tipoDeAsiento = tipoDeAsiento;
		this.rutaDeVuelo = rutaDeVuelo;
		this.paquete = paquete;
	}

	// Getters y Setters
	public int getCantRutasDeVuelo() {
		return cantRutasDeVuelo;
	}

	public void setCantRutasDeVuelo(int cantRutasDeVuelo) {
		this.cantRutasDeVuelo = cantRutasDeVuelo;
	}

	public TipoAsiento getTipoDeAsiento() {
		return tipoDeAsiento;
	}

	public void setTipoDeAsiento(TipoAsiento tipoDeAsiento) {
		this.tipoDeAsiento = tipoDeAsiento;
	}

	public RutaDeVuelo getRutaDeVuelo() {
		return rutaDeVuelo;
	}

	public void setRutaDeVuelo(RutaDeVuelo rutaDeVuelo) {
		this.rutaDeVuelo = rutaDeVuelo;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	public ComercializaDto toDto() {
		return new ComercializaDto(cantRutasDeVuelo, tipoDeAsiento, rutaDeVuelo.getNombre(), paquete.getNombre());
	}
}
