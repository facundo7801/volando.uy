package uy.edu.fing.volandouy.modelo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uy.edu.fing.volandouy.dto.VueloDto;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;

public class Vuelo {
	private String nombre;
	private Date fecha;
	private Time duracion;
	private int cantMaxAsTurista;
	private int cantMaxAsEjecutivo;
	private Date fechaAlta;
	private RutaDeVuelo rutaDeVuelo;
	private Map<String, Reserva> clientes;
	private String imagen;
	private List<Pasaje> asientosTurista;
	private List<Pasaje> asientosEjecutivo;

	// Constructor
	public Vuelo(VueloDto vuelodto, RutaDeVuelo rutaDeVuelo) {
		this.nombre = vuelodto.getNombre();
		this.fecha = vuelodto.getFecha();
		this.duracion = vuelodto.getDuracion();
		this.cantMaxAsTurista = vuelodto.getCantMaxAsTurista();
		this.cantMaxAsEjecutivo = vuelodto.getCantMaxAsEjecutivo();
		this.fechaAlta = vuelodto.getFechaAlta();
		this.rutaDeVuelo = rutaDeVuelo;
		this.clientes = new TreeMap<>();
		this.imagen = vuelodto.getImagen();
		this.asientosTurista = new ArrayList<>();
		this.asientosEjecutivo = new ArrayList<>();
	}

	public VueloDto toDto() {
		List<String> nicknamesClientes = new ArrayList<>();

		for (String nicknameCliente : clientes.keySet()) {
			nicknamesClientes.add(nicknameCliente);
		}

		return new VueloDto(nombre, fecha, duracion, cantMaxAsTurista, cantMaxAsEjecutivo, fechaAlta,
				rutaDeVuelo.getAerolinea().getNickName(), rutaDeVuelo.getNombre(), nicknamesClientes, imagen);
	}

	// Getters
	public String getNombre() {
		return nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public Time getDuracion() {
		return duracion;
	}

	public int getCantMaxAsTurista() {
		return cantMaxAsTurista;
	}

	public int getCantMaxAsEjecutivo() {
		return cantMaxAsEjecutivo;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public RutaDeVuelo getRutaDeVuelo() {
		return rutaDeVuelo;
	}

	public Map<String, Reserva> getClientes() {
		return clientes;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public List<Pasaje> getAsientosTurista() {
		return asientosTurista;
	}
	
	public List<Pasaje> getAsientosEjecutivo() {
		return asientosEjecutivo;
	}
	
	public void setAsientosTurista(List<Pasaje> asientosTurista) {
		this.asientosTurista = asientosTurista;
	}
	
	public void setAsientosEjecutivo(List<Pasaje> asientosEjecutivo) {
		this.asientosEjecutivo = asientosEjecutivo;
	}
	
	//Se llama desde VueloController y PaqueteController al hacer una reserva
	public void agregarAsiento(Pasaje pasajero) {
		if (pasajero.getReserva().getTipoAsiento() == TipoAsiento.Ejecutivo) {
			//pasajero.setNumeroAsiento(asientosEjecutivo.size());
			if (pasajero.getNumeroAsiento() == null || pasajero.getNumeroAsiento().equals("vacio")) {
				pasajero.setNumeroAsiento(obtenerNumeroPasajero(asientosEjecutivo));
			}
			asientosEjecutivo.add(pasajero);
		}else {
			//pasajero.setNumeroAsiento(asientosTurista.size());
			if (pasajero.getNumeroAsiento() == null || pasajero.getNumeroAsiento().equals("vacio")) {
				pasajero.setNumeroAsiento(obtenerNumeroPasajero(asientosTurista));
			}
			asientosTurista.add(pasajero);
		}
	}
	
	private int obtenerNumeroPasajero (List<Pasaje> pasajeros) {
		int indice = 0;
		int numeroAsiento = 1;
		boolean asientoValido = false;
		boolean asientoDisponible = true;
		while (!asientoValido) {
			
			indice = 0;
			asientoDisponible = true;
			while (indice < pasajeros.size() && asientoDisponible) {
				asientoDisponible = numeroAsiento != Integer.parseInt(pasajeros.get(indice).getNumeroAsiento().substring(1));
				indice = indice + 1;
			}
			
			asientoValido = asientoDisponible;
			if (!asientoValido) {
				numeroAsiento = numeroAsiento + 1;
			}
		
		}
		
		return numeroAsiento;
	}
	
}
