package uy.edu.fing.volandouy.dataAux;

import java.util.Date;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lupaDto", propOrder = {
    "nombre",
    "tipo",
    "entidad",
    "fechaAlta"
})
public class LupaDto {
	@XmlElement
	private String nombre;
	@XmlElement
	private String tipo;
	@XmlElement
	private Object entidad;
	@XmlElement
	private Date fechaAlta;
	
	public LupaDto() {
		nombre = null;
		tipo = null;
		entidad = null;
		fechaAlta = null;
	}
	
	public LupaDto(String nombre, String tipo, Object entidad, Date fechaAlta){
		this.setNombre(nombre);
		this.setTipo(tipo);
		this.setEntidad(entidad);
		this.setFechaAlta(fechaAlta);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Object getEntidad() {
		return entidad;
	}

	public void setEntidad(Object entidad) {
		this.entidad = entidad;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
}
