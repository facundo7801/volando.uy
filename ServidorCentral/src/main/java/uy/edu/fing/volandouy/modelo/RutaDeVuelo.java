package uy.edu.fing.volandouy.modelo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uy.edu.fing.volandouy.dto.CategoriaDto;
import uy.edu.fing.volandouy.dto.RutaDeVueloDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;

public class RutaDeVuelo {
	private String nombre;
	private String descripcion;
	private float costoTurista;
	private float costoEjecutivo;
	private float costoEquipajeExtra;
	private Date fechaAlta;
	private Aerolinea aerolinea;
	private List<Categoria> categorias;
	private List<Vuelo> vuelos;
	private List<Comercializa> listComercializa;
	private Ciudad ciudadOrigen;
	private Ciudad ciudadDestino;
	private Time hora;
	private EstadoRuta estado;
	private String resumen;
	private String imagen;
	private String video;
	private int visitas;
	private Date fechaFinalizada;

	// Constructor
	public RutaDeVuelo(String nombre, String descripcion, float costoTurista, float costoEjecutivo,
			float costoEquipajeExtra, Date fechaAlta, Aerolinea aerolinea, List<Categoria> categorias, Time hora,
			Ciudad ciudadOrigen, Ciudad ciudadDestino, EstadoRuta estado, String resumen, String imagen, String video, Date fechaFinalizada) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costoTurista = costoTurista;
		this.costoEjecutivo = costoEjecutivo;
		this.costoEquipajeExtra = costoEquipajeExtra;
		this.fechaAlta = fechaAlta;
		this.aerolinea = aerolinea;
		this.categorias = categorias;
		this.vuelos = new ArrayList<>();
		this.hora = hora;
		this.ciudadOrigen = ciudadOrigen;
		this.ciudadDestino = ciudadDestino;
		this.listComercializa = new ArrayList<>();
		this.estado = estado;
		this.resumen = resumen;
		this.imagen = imagen;
		this.video = video;
		this.visitas = 0;
		this.fechaFinalizada = fechaFinalizada;
	}

	public RutaDeVueloDto toDto() {
		List<CategoriaDto> categoriasDto = new ArrayList<>();
		for (Categoria categoria : categorias) {
			CategoriaDto catDto = new CategoriaDto(categoria.getNombre());
			categoriasDto.add(catDto);
		}

		List<String> vuelosNombres = new ArrayList<>();
		for (Vuelo vuelo : vuelos) {
			vuelosNombres.add(vuelo.getNombre());
		}
		
		return new RutaDeVueloDto(nombre, descripcion, costoTurista, costoEjecutivo, costoEquipajeExtra, fechaAlta,
				aerolinea.toDto(), categoriasDto, vuelosNombres, hora, ciudadOrigen.toDto(), ciudadDestino.toDto(), estado, resumen, imagen, video, visitas, fechaFinalizada);

	}

	// Getters y Setters
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

	public void setCostoTurista(float costoTurista) {
		this.costoTurista = costoTurista;
	}

	public float getCostoEjecutivo() {
		return costoEjecutivo;
	}

	public void setCostoEjecutivo(float costoEjecutivo) {
		this.costoEjecutivo = costoEjecutivo;
	}

	public float getCostoEquipajeExtra() {
		return costoEquipajeExtra;
	}

	public void setCostoEquipajeExtra(float costoEquipajeExtra) {
		this.costoEquipajeExtra = costoEquipajeExtra;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Aerolinea getAerolinea() {
		return aerolinea;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Vuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public List<Comercializa> getListComercializa() {
		return listComercializa;
	}

	public void setListComercializa(List<Comercializa> listComercializa) {
		this.listComercializa = listComercializa;
	}

	public Time getHora() {
		return hora;
	}

	public Ciudad getCiudadOrigen() {
		return ciudadOrigen;
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
	
	public Date getFechaFinalizada() {
		return fechaFinalizada;
	}
	
	public void settFechaFinalizada(Date fechaFinalizada) {
		this.fechaFinalizada = fechaFinalizada;
	}
}
