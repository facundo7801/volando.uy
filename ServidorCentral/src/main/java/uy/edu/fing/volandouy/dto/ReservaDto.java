package uy.edu.fing.volandouy.dto;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"fechaReserva",
		"tipoAsiento",
		"uniEquipajeExtra",
		"costo",
		"pasajeros",
		"cliente",
		"aerolinea",
		"ruta",
		"vuelo",
		"paquete",
		"embarqueUrl",
		"embarqueHora",
		"fechaCheckIn",
		"embarqueHoraString"
})
public class ReservaDto {
	@XmlElement
	private Date fechaReserva;
	@XmlElement
	private TipoAsiento tipoAsiento;
	@XmlElement
	private int uniEquipajeExtra;
	@XmlElement
	private float costo;
	@XmlElement
	private List<PasajeDto> pasajeros;
	@XmlElement
	private String cliente;
	@XmlElement
	private String aerolinea;
	@XmlElement
	private String ruta;
	@XmlElement
	private String vuelo;
	@XmlElement
	private String paquete;
	@XmlElement
	private String embarqueUrl;
	@XmlElement
	private Time embarqueHora;
	@XmlElement
	private Date fechaCheckIn;
	@XmlElement
	private String embarqueHoraString;

	public ReservaDto() {
		this.fechaReserva = null;
		this.tipoAsiento = null;
		this.uniEquipajeExtra = 0;
		this.costo = 0;
		this.pasajeros = null;
		this.cliente = null;
		this.aerolinea = null;
		this.ruta = null;
		this.vuelo = null;
		this.paquete = null;
		this.embarqueUrl = null;
		this.embarqueHora = null;
		this.fechaCheckIn = null;
		this.embarqueHoraString = null;
	}
	
	// Constructor
	public ReservaDto(Date fechaReserva, TipoAsiento tipoAsiento, int uniEquipajeExtra, float costo,
			List<PasajeDto> pasajeros, String cliente, String aerolinea, String ruta, String vuelo, String paquete,String embarqueUrl,Time embarqueHora,Date fechaCheckIn) {
		this.fechaReserva = fechaReserva;
		this.tipoAsiento = tipoAsiento;
		this.uniEquipajeExtra = uniEquipajeExtra;
		this.costo = costo;
		this.pasajeros = pasajeros;
		this.cliente = cliente;
		this.aerolinea = aerolinea;
		this.ruta = ruta;
		this.vuelo = vuelo;
		this.paquete = paquete;
		this.embarqueUrl = embarqueUrl;
		this.embarqueHora = embarqueHora;
		this.fechaCheckIn = fechaCheckIn;
		if (embarqueHora != null) {
		this.embarqueHoraString = embarqueHora.toString();
		}else {
			embarqueHoraString = null;
		}
		
	}

	// Getters
	public Date getFechaReserva() {
		return fechaReserva;
	}
	
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public TipoAsiento getTipoAsiento() {
		return tipoAsiento;
	}
	
	public void setTipoAsiento(TipoAsiento tipoAsiento) {
		this.tipoAsiento = tipoAsiento;
	}

	public int getUniEquipajeExtra() {
		return uniEquipajeExtra;
	}
	
	public void setUniEquipajeExtra(int uniEquipajeExtra) {
		this.uniEquipajeExtra = uniEquipajeExtra;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public List<PasajeDto> getPasajeros() {
		return pasajeros;
	}
	
	public void setPasajeros(List<PasajeDto> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public String getCliente() {
		return cliente;
	}
	
	public void setCliente(String cliente) {
		this.cliente = cliente;
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
	
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getVuelo() {
		return vuelo;
	}

	public void setVuelo(String vuelo) {
		this.vuelo = vuelo;
	}
	
	public String getPaquete() {
		return paquete;
	}
	
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}
	
	public String getEmbarqueUrl() {
		return embarqueUrl;
	}
	
	public void setEmbarqueUrl(String embarqueUrl) {
		this.embarqueUrl = embarqueUrl;
	}
	
	public void setEmbarqueHora(Time embarqueHora) {
		this.embarqueHora = embarqueHora;
	}
	
	public Time getEmbarqueHora() {
		return embarqueHora;
	}
	

	public Date getFechaCheckIn() {
		return fechaCheckIn;
	}

	public void setFechaCheckIn(Date fechaCheckIn) {
		this.fechaCheckIn = fechaCheckIn;
	}
	
	

}
