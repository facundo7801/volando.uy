package uy.edu.fing.volandouy.dto;

import java.util.Date;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"nombre",
		"descripcion",
		"periodoValidez",
		"descuento",
		"fechaAlta",
		"costoAsociado",
		"imagen",
		"rutas",
		"compradores"
})
public class PaqueteDto {
	@XmlElement
	private String nombre;
	@XmlElement
	private String descripcion;
	@XmlElement
	private Integer periodoValidez;
	@XmlElement
	private Integer descuento;
	@XmlElement
	private Date fechaAlta;
	@XmlElement
	private float costoAsociado;
	@XmlElement
	private String imagen;
	@XmlElement
	private List<ComercializaDto> rutas;
	@XmlElement
	private List<String> compradores;

	// Constructor
	public PaqueteDto(String nombre, String descripcion, Integer periodoValidez, Integer descuento, Date fechaAlta,
			float costoAsociado, List<ComercializaDto> rutas, List<String> compradores, String imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.fechaAlta = fechaAlta;
		this.costoAsociado = costoAsociado;
		this.rutas = rutas;
		this.compradores = compradores;
		this.imagen = imagen;
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPeriodoValidez() {
		return periodoValidez;
	}

	public void setPeriodoValidez(int periodoValidez) {
		this.periodoValidez = periodoValidez;
	}

	public Integer getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public float getCostoAsociado() {
		return costoAsociado;
	}

	public void setCostoAsociado(float costoAsociado) {
		this.costoAsociado = costoAsociado;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public List<ComercializaDto> getRutas() {
		return rutas;
	}
	
	public void setRutas(List<ComercializaDto> rutas) {
		this.rutas = rutas;
	}
	
	public List<String> getCompradores() {
		return compradores;
	}
	
	public void setCompradores(List<String> compradores) {
		this.compradores = compradores;
	}
}
