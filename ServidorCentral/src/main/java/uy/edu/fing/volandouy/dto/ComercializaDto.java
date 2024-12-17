package uy.edu.fing.volandouy.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"cantRutasDeVuelo",
		"tipoDeAsiento",
		"ruta",
		"paquete",
})
public class ComercializaDto {
	@XmlElement
	private int cantRutasDeVuelo;
	@XmlElement
	private TipoAsiento tipoDeAsiento;
	@XmlElement
	private String ruta;
	@XmlElement
	private String paquete;

	public ComercializaDto() {
		this.cantRutasDeVuelo = 0;
		this.tipoDeAsiento = null;
		this.ruta = null;
		this.paquete = null;
	}
	
	// Constructor
	public ComercializaDto(int cantRutasDeVuelo, TipoAsiento tipoDeAsiento, String ruta, String paquete) {
		this.cantRutasDeVuelo = cantRutasDeVuelo;
		this.tipoDeAsiento = tipoDeAsiento;
		this.ruta = ruta;
		this.paquete = paquete;
	}

	// Getters
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

	public String getRuta() {
		return ruta;
	}
	
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getPaquete() {
		return paquete;
	}
	
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}

}
