package uy.edu.fing.volandouy.dto;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"nombre",
		"fecha",
		"duracion",
		"cantMaxAsTurista",
		"cantMaxAsEjecutivo",
		"fechaAlta",
		"aerolinea",
		"ruta",
		"clientes",
		"imagen",
		"duracionString"
})

public class VueloDto {
	@XmlElement
	private String nombre;
	@XmlElement
	private Date fecha;
	@XmlElement
	private Time duracion;
	@XmlElement
	private int cantMaxAsTurista;
	@XmlElement
	private int cantMaxAsEjecutivo;
	@XmlElement
	private Date fechaAlta;
	@XmlElement
	private String aerolinea;
	@XmlElement
	private String ruta;
	@XmlElement
	private List<String> clientes;
	@XmlElement
	private String imagen;
	@XmlElement
	private String duracionString;


	public VueloDto() {
        this.nombre = null;
        this.fecha = null;
        this.duracion = null;
        this.cantMaxAsTurista = 0;
        this.cantMaxAsEjecutivo = 0;
        this.fechaAlta = null;
        this.aerolinea = null;
        this.ruta = null;
        this.clientes = null;
        this.imagen = null;
        this.duracionString = null;
    }
	
	// Constructor
	public VueloDto(String nombre, Date fecha, Time duracion, int cantMaxAsTurista, int cantMaxAsEjecutivo,
			Date fechaAlta, String aerolinea, String ruta, List<String> clientes, String imagen) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.duracion = duracion;
		this.cantMaxAsTurista = cantMaxAsTurista;
		this.cantMaxAsEjecutivo = cantMaxAsEjecutivo;
		this.fechaAlta = fechaAlta;
		this.aerolinea = aerolinea;
		this.ruta = ruta;
		this.clientes = clientes;
		this.imagen = imagen;
		this.duracionString = duracion.toString();
	}


	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getDuracion() {
		return duracion;
	}
	
	public void setDuracion(Time duracion) {
		this.duracion = duracion;
	}

	public int getCantMaxAsTurista() {
		return cantMaxAsTurista;
	}
	
	public void setCantMaxAsTurista(int cantMaxAsTurista) {
		this.cantMaxAsTurista = cantMaxAsTurista;
	}

	public int getCantMaxAsEjecutivo() {
		return cantMaxAsEjecutivo;
	}

	public void setCantMaxAsEjecutivo(int cantMaxAsEjecutivo) {
		this.cantMaxAsEjecutivo = cantMaxAsEjecutivo;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getAerolinea() {
		return aerolinea;
	}
	
	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}

	public String getRuta() {
		return ruta;
	}

	public List<String> getClientes() {
		return clientes;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public String getDuracionString() {
		return duracionString;
	}
	
	public void setDuracionString(String duracionString) {
		this.duracionString = duracionString;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public void setClientes(List<String> clientes) {
		this.clientes = clientes;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	

}
