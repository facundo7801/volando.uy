package uy.edu.fing.volandouy.modelo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uy.edu.fing.volandouy.dto.PasajeDto;
import uy.edu.fing.volandouy.dto.ReservaDto;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;

public class Reserva {
	private Date fechaReserva;
	private TipoAsiento tipoAsiento;
	private int uniEquipajeExtra;
	private float costo;
	private List<Pasaje> pasajeros;
	private Cliente cliente;
	private Vuelo vuelo;
	private CompraPaquete comprapaquete;
	private String embarqueUrl;
	private Date fechaCheckIn;
	private Time embarqueHora;

	// Constructor
	public Reserva(ReservaDto reservadto, Cliente cliente, Vuelo vuelo, CompraPaquete comprapaquete, Time embarqueHora) {
		this.fechaReserva = reservadto.getFechaReserva();
		this.tipoAsiento = reservadto.getTipoAsiento();
		this.uniEquipajeExtra = reservadto.getUniEquipajeExtra();
		this.costo = reservadto.getCosto();
		
		this.cliente = cliente;
		this.vuelo = vuelo;
		this.comprapaquete = comprapaquete;
		this.embarqueUrl = reservadto.getEmbarqueUrl();
		this.fechaCheckIn = reservadto.getFechaCheckIn();
		this.embarqueHora = reservadto.getEmbarqueHora();
		
		this.pasajeros = new ArrayList<>();
		for (PasajeDto pasajerodto : reservadto.getPasajeros()) {
			Pasaje pasajero = new Pasaje(pasajerodto.getNombre(), pasajerodto.getApellido(), this);
			if (pasajerodto.getNumeroAsiento() != null && !pasajerodto.getNumeroAsiento().equals("vacio")) {
				pasajero.setNumeroAsiento(Integer.parseInt(pasajerodto.getNumeroAsiento()));
			}
			this.pasajeros.add(pasajero);
		}

	}

	public ReservaDto toDto() {
		List<PasajeDto> dataPasajeros = new ArrayList<>();
		for (Pasaje pasaje : pasajeros) {
			dataPasajeros.add(pasaje.toDto());
		}

		RutaDeVuelo ruta = vuelo.getRutaDeVuelo();
		Aerolinea aerolinea = ruta.getAerolinea();
		String paquete = null;
		String embarqueUrl = this.embarqueUrl;
		
		if (comprapaquete != null) {
			paquete = comprapaquete.getPaquete().getNombre();
		}
		
		return new ReservaDto(fechaReserva, tipoAsiento, uniEquipajeExtra, costo, dataPasajeros, cliente.getNickName(),
				aerolinea.getNickName(), ruta.getNombre(), vuelo.getNombre(), paquete, embarqueUrl,embarqueHora, fechaCheckIn);
	}

	// Getters
	public Date getFechaReserva() {
		return fechaReserva;
	}

	public TipoAsiento getTipoAsiento() {
		return tipoAsiento;
	}

	public int getUniEquipajeExtra() {
		return uniEquipajeExtra;
	}

	public float getCosto() {
		return costo;
	}

	public List<Pasaje> getPasajeros() {
		return pasajeros;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public CompraPaquete getCompraPaquete() {
		return comprapaquete;
	}
	
	public String getEmbarqueUrl() {
		return embarqueUrl;
	}
	
	public void setEmbarqueUrl(String embarqueUrl) {
		this.embarqueUrl = embarqueUrl;
	}

	public Date getFechaCheckIn() {
		return fechaCheckIn;
	}

	public void setFechaCheckIn(Date fechaCheckIn) {
		this.fechaCheckIn = fechaCheckIn;
	}
	
	public void setEmbarqueHora(Time embarqueHora) {
		this.embarqueHora = embarqueHora;
	}
	
	public Time getEmbarqueHora() {
		return embarqueHora;
	}

}
