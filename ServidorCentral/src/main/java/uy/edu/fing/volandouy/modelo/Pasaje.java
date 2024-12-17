package uy.edu.fing.volandouy.modelo;

import uy.edu.fing.volandouy.dto.PasajeDto;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;

public class Pasaje {

	private String nombre;
	private String apellido;
	private Reserva reserva;
	private String numeroAsiento;

	public Pasaje(String nombre, String apellido, Reserva reserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.reserva = reserva;
		numeroAsiento = null;
	}

	public PasajeDto toDto() {
		return new PasajeDto(nombre, apellido, numeroAsiento);
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public String getNumeroAsiento() {
		return numeroAsiento;
	}
	
	//Se llama desde Vuelo al hacer una reserva desde vueloController o paqueteController
	public void setNumeroAsiento(int numero) {
		String prefijo = null;
		if (reserva.getTipoAsiento() == TipoAsiento.Ejecutivo) {
			prefijo = "E";
		}else {
			prefijo = "T";
		}
		numeroAsiento = prefijo + numero;
		
	}
	
}
