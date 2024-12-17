package uy.edu.fing.volandouy.dto;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "nombre",
    "descripcion",
    "costoTurista",
    "costoEjecutivo",
    "costoEquipajeExtra",
    "fechaAlta",
    "hora",
    "categorias",
    "vuelos",
    "aerolinea",
    "ciudadOrigen",
    "ciudadDestino",
    "estado",
    "resumen",
    "imagen",
    "video",
    "visitas",
    "horaString",
    "fechaFinalizada"
})
public class RutaDeVueloDto {
	@XmlElement
	private String nombre;
	@XmlElement
	private String descripcion;
	@XmlElement
	private float costoTurista;
	@XmlElement
	private float costoEjecutivo;
	@XmlElement
	private float costoEquipajeExtra;
	@XmlElement
	private Date fechaAlta;
	@XmlElement
	private Time hora;
	@XmlElement
	private List<CategoriaDto> categorias;
	@XmlElement
	private List<String> vuelos;
	@XmlElement
	private AerolineaDto aerolinea;
	@XmlElement
	private CiudadDto ciudadOrigen;
	@XmlElement
	private CiudadDto ciudadDestino;
	@XmlElement
	private EstadoRuta estado;
	@XmlElement
	private String resumen;
	@XmlElement
	private String imagen;
	@XmlElement
	private String video;
	@XmlElement
	private int visitas;
	@XmlElement
	private String horaString;
	@XmlElement
	private Date fechaFinalizada;
	

	//Constructor para el web service
	public RutaDeVueloDto() {
		this.nombre = null;
		this.descripcion = null;
		this.costoTurista = 0;
		this.costoEjecutivo = 0;
		this.costoEquipajeExtra = 0;
		this.fechaAlta = null;
		this.aerolinea = null;
		this.categorias = null;
		this.vuelos = null;
		this.hora = null;
		this.ciudadOrigen = null;
		this.ciudadDestino = null;
		this.estado = null;
		this.resumen = null;
		this.imagen = null;
		this.video = null;
		this.visitas = 0;
		this.horaString = null;
		this.fechaFinalizada = null;	
		
	}
	
	// Constructor
	public RutaDeVueloDto(String nombre, String descripcion, float costoTurista, float costoEjecutivo,
			float costoEquipajeExtra, Date fechaAlta, AerolineaDto aerolinea, List<CategoriaDto> categorias,
			List<String> vuelos, Time hora, CiudadDto ciudadOrigen, CiudadDto ciudadDestino, EstadoRuta estado, String resumen, String imagen, String video, int visitas, Date fechaFinalizada) {

		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costoTurista = costoTurista;
		this.costoEjecutivo = costoEjecutivo;
		this.costoEquipajeExtra = costoEquipajeExtra;
		this.fechaAlta = fechaAlta;
		this.aerolinea = aerolinea;
		this.categorias = categorias == null ? new ArrayList<>() : categorias;
		this.vuelos = vuelos == null ? new ArrayList<>() : vuelos;
		this.hora = hora;
		this.ciudadOrigen = ciudadOrigen;
		this.ciudadDestino = ciudadDestino;
		this.estado = estado;
		this.resumen = resumen;
		this.imagen = imagen;
		this.video = video;
		this.visitas = visitas;
		this.horaString = hora.toString();
		this.fechaFinalizada = fechaFinalizada;
	}

	// Getters
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public float getCostoTurista() {
		return costoTurista;
	}
	
	public void setCostoTurista(int costoTurista) {
		this.costoTurista = costoTurista;
	}

	public float getCostoEjecutivo() {
		return costoEjecutivo;
	}
	
	public void setCostoEjecutivo(int costoEjecutivo) {
		this.costoEjecutivo = costoEjecutivo;
	}

	public float getCostoEquipajeExtra() {
		return costoEquipajeExtra;
	}
	
	public void setCostoEquipajeExtra(int costoEquipajeExtra) {
		this.costoEquipajeExtra = costoEquipajeExtra;
	}
	
	public Time getHora() {
		return hora;
	}
	
	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}
	
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public AerolineaDto getAerolinea() {
		return this.aerolinea;
	}
	
	public void setAerolinea(AerolineaDto aerolinea) {
		this.aerolinea = aerolinea;
	}

	public List<CategoriaDto> getCategorias() {
		return this.categorias;
	}
	
	public void setCategorias(List<CategoriaDto> categorias) {
		this.categorias = categorias;
	}

	public List<String> getVuelos() {
		return this.vuelos;
	}
	
	public void setVuelos(List<String> vuelos) {
		this.vuelos = vuelos;
	}

	public CiudadDto getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadorigen(CiudadDto ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}
	
	public CiudadDto getCiudadDestino() {
		return ciudadDestino;
	}
	
	public void setCiudadDestino(CiudadDto ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}
	
	public EstadoRuta getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoRuta estado) {
		this.estado = estado;
	}
	
	public String getResumen() {
		return resumen;
	}
	
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getVideo() {
		return video;
	}
	
	public void setVideo(String video) {
		this.video = video;
	}
	
	public int getVisitas() {
		return visitas;
	}
	
	public void setVisitas(int visitas) {
		this.visitas = visitas;
	}
	
	public String getHoraString() {
		return horaString;
	}
	
	public void setHoraString(String horaString) {
		this.horaString = horaString;
	}
	
	public Date getFechaFinalizada() {
		return fechaFinalizada;
	}
	
	public void settFechaFinalizada(Date fechaFinalizada) {
		this.fechaFinalizada = fechaFinalizada;
	}
}
