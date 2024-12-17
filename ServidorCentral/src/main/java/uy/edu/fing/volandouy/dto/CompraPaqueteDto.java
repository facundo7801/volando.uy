package uy.edu.fing.volandouy.dto;

import java.util.Date;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"fechaCompra",
		"vencimiento",
		"comprador",
		"paquete",
		"reservas"
})
public class CompraPaqueteDto {
	@XmlElement
	private Date fechaCompra;
	@XmlElement
	private Date vencimiento;
	@XmlElement
	private String comprador;
	@XmlElement
	private String paquete;
	// el string de las reservas es el nombre del vuelo
	@XmlElement
	private List<String> reservas;
	
	public CompraPaqueteDto() {
		this.fechaCompra = null;
		this.vencimiento = null;
		this.comprador = null;
		this.paquete = null;
		this.reservas = null;
	}
	
	public CompraPaqueteDto(Date fechaCompra, Date vencimiento, String comprador, String paquete, List<String> reservas) {
		this.fechaCompra = fechaCompra;
		this.vencimiento = vencimiento;
		this.comprador = comprador;
		this.paquete = paquete;
		this.reservas = reservas;
		
	}
	
	public Date getFechaCompra() {
		return fechaCompra;
	}
	
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
	public Date getVencimiento() {
		return vencimiento;
	}
	
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	
	public String getComprador() {
		return comprador;
	}
	
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	
	public String getPaquete() {
		return paquete;
	}
	
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}
	
	public List<String> getReservas() {
		return reservas;
	}
	
	public void setReservas(List<String> reservas) {
		this.reservas = reservas;
	}
	
}
