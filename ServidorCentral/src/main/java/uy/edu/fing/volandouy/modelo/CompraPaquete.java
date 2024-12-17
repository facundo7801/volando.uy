package uy.edu.fing.volandouy.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uy.edu.fing.volandouy.dto.CompraPaqueteDto;

public class CompraPaquete {
	private Date fechaCompra;
	private Date vencimiento;
	private Cliente comprador;
	private Paquete paquete;
	// La key de las reservas es el nombre del vuelo
	private Map<String, Reserva> reservas;

	public CompraPaquete(Date fechaCompra, Date vencimiento, Cliente comprador, Paquete paquete) {
		this.fechaCompra = fechaCompra;
		this.vencimiento = vencimiento;
		this.comprador = comprador;
		this.paquete = paquete;
		this.reservas = new TreeMap<>();
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public Cliente getComprador() {
		return comprador;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	public Map<String, Reserva> getReservas() {
		return reservas;
	}
	
	public CompraPaqueteDto toDto() {
		List<String> nombreVuelos = new ArrayList<>();
		for (String nombreVuelo : reservas.keySet()) {
			nombreVuelos.add(nombreVuelo);
		}
		
		return new CompraPaqueteDto(fechaCompra, vencimiento, comprador.getNickName(), paquete.getNombre(), nombreVuelos);
		
	}

}
