package uy.edu.fing.volandouy.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;

public class Cliente extends Usuario {
	private String apellido;
	private Date fechaNacimiento;
	private TipoDocumento tipoDocumento;
	private String numeroDocumento;
	private String nacionalidad;
	private Map<String, Reserva> reservas;
	private Map<String, CompraPaquete> comprasPaquete;

	public Cliente(ClienteDto cliente) {
		super(cliente.getNickName(), cliente.getNombre(), cliente.getEmail(), cliente.getFechaAlta(), cliente.getContrasenia(), cliente.getImagen());
		this.apellido = cliente.getApellido();
		this.fechaNacimiento = cliente.getFechaNacimiento();
		this.tipoDocumento = cliente.getTipoDocumento();
		this.numeroDocumento = cliente.getNumeroDocumento();
		this.nacionalidad = cliente.getNacionalidad();
		this.reservas = new TreeMap<>();
		this.comprasPaquete = new TreeMap<>();
	}

	// Getters y Setters
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Map<String, Reserva> getReservas() {
		return reservas;
	}

	public Map<String, CompraPaquete> getComprasPaquete() {
		return comprasPaquete;
	}

	@Override
	public ClienteDto toDto() {
		List<String> nombresVuelos = new ArrayList<>();
		List<String> nombresPaquetes = new ArrayList<>();

		for (String nombreVuelo : reservas.keySet()) {
			nombresVuelos.add(nombreVuelo);
		}

		for (String nombrePaquete : comprasPaquete.keySet()) {
			nombresPaquetes.add(nombrePaquete);
		}
		
		List<String> seguidos = new ArrayList<>();
		for (String nick : getSeguidos().keySet()) {
			seguidos.add(nick);
		}
		
		List<String> seguidores = new ArrayList<>();
		for (String nick : getSeguidores().keySet()) {
			seguidores.add(nick);
		}

		return new ClienteDto(getNickName(), getNombre(), getEmail(), apellido, fechaNacimiento, tipoDocumento,
				numeroDocumento, nacionalidad, nombresVuelos, nombresPaquetes, getFechaAlta(), getContrasenia(), getImagen(), seguidos, seguidores);
	}
}
