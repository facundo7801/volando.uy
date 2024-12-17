package uy.edu.fing.volandouy.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uy.edu.fing.volandouy.enumerados.TipoDocumento;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"apellido",
		"fechaNacimiento",
		"tipoDocumento",
		"numeroDocumento",
		"nacionalidad",
		"reservas",
		"comprasPaquete"
})

public class ClienteDto extends UsuarioDto {
	@XmlElement
	private String apellido;
	@XmlElement
	private Date fechaNacimiento;
	@XmlElement
	private TipoDocumento tipoDocumento;
	@XmlElement
	private String numeroDocumento;
	@XmlElement
	private String nacionalidad;
	@XmlElement
	private List<String> reservas;
	@XmlElement
	private List<String> comprasPaquete;

	//Constructor para el web Service
	public ClienteDto() {
		super(null, null, null, null, null, null, null, null);
		apellido = null;
		fechaNacimiento = null;
		tipoDocumento = null;
		numeroDocumento = null;
		nacionalidad = null;
		reservas = null;
		comprasPaquete = null;
	}
	
	// Constructor
	public ClienteDto(String nickName, String nombre, String email, String apellido, Date fechaNacimiento,
			TipoDocumento tipoDocumento, String numeroDocumento, String nacionalidad, List<String> reservas,
			List<String> comprasPaquete, Date fechaAlta, String contrasenia, String imagen, List<String> seguidos, List<String> seguidores) {
		super(nickName, nombre, email, fechaAlta, contrasenia, imagen, seguidos, seguidores);
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.nacionalidad = nacionalidad;
		this.reservas = reservas == null ? new ArrayList<>() : reservas;
		this.comprasPaquete = comprasPaquete == null ? new ArrayList<>() : comprasPaquete;
	}

	// Getters
	public String getApellido() {
		return apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public List<String> getReservas() {
		return reservas;
	}

	public List<String> getComprasPaquete() {
		return comprasPaquete;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;

	}

	public void setFechaNacimiento(Date date) {
		this.fechaNacimiento = date;

	}

	public void setTipoDocumento(TipoDocumento valueOf) {
		this.tipoDocumento = valueOf;

	}

	public void setNacionalidad(String string) {
		this.nacionalidad = string;

	}

	public void setNumeroDocumento(String string) {
		this.numeroDocumento = string;

	}
	
	public void setReservas(List<String> reservas) {
		this.reservas = reservas;
	}

	public void setComprasPaquete(List<String> comprasPaquete) {
		this.comprasPaquete = comprasPaquete;
	}

	@Override
	public void metodoAbstracto() {
		// TODO Auto-generated method stub
		
	}

}
