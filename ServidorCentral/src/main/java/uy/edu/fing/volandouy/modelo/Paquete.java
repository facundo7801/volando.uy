package uy.edu.fing.volandouy.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uy.edu.fing.volandouy.dto.ComercializaDto;
import uy.edu.fing.volandouy.dto.PaqueteDto;

public class Paquete {
	private String nombre;
	private String descripcion;
	private Integer periodoValidez;
	private Integer descuento;
	private Date fechaAlta;
	private float costoAsociado;
	private String imagen;
	private List<Comercializa> listComercializa;
	private Map<String, CompraPaquete> compradores;

	// Constructor
	public Paquete(PaqueteDto paqueteDto) {
		this.nombre = paqueteDto.getNombre();
		this.descripcion = paqueteDto.getDescripcion();
		this.periodoValidez = paqueteDto.getPeriodoValidez();
		this.descuento = paqueteDto.getDescuento();
		this.fechaAlta = paqueteDto.getFechaAlta();
		this.costoAsociado = 0;
		this.listComercializa = new ArrayList<>();
		this.compradores = new TreeMap<>();
		this.imagen = paqueteDto.getImagen();
	}

	public PaqueteDto toDto() {
		//List<String> rutas = new ArrayList<>();
		List<ComercializaDto> rutas = new ArrayList<>();
		for (Comercializa comercializaRuta : listComercializa) {
			//rutas.add(comercializaRuta.getRutaDeVuelo().getNombre());
			rutas.add(comercializaRuta.toDto());
		}
		
		List<String> compradoresNombres = new ArrayList<>();
		for (String key : compradores.keySet()) {
			compradoresNombres.add(key);
		}
		
		return new PaqueteDto(nombre, descripcion, periodoValidez, descuento, fechaAlta, costoAsociado, rutas, compradoresNombres, imagen);
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

	
	public List<Comercializa> getListComercializa() {
		return listComercializa;
	}

	public void setListComercializa(List<Comercializa> listComercializa) {
		this.listComercializa = listComercializa;
	}
	
	public Map<String, CompraPaquete> getCompradores() {
		return compradores;
	}
	
}
