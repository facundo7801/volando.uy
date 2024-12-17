package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.CiudadDto;
import uy.edu.fing.volandouy.dto.RutaDeVueloDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;

public class RutaDeVueloTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testRutaDeVueloConstructorAndGetters() {
		Date fechaAlta = new Date();
		Time hora = new Time(14, 12, 0);
		List<Vuelo> vuelos = new ArrayList<>();
		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("Ruta1", "Descripción de la ruta", 100.0f, 200.0f, 50.0f, fechaAlta,
				null, null, hora, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);

		assertEquals("Ruta1", rutaDeVuelo.getNombre());
		assertEquals("Descripción de la ruta", rutaDeVuelo.getDescripcion());
		assertEquals(100.0f, rutaDeVuelo.getCostoTurista(), 0.01);
		assertEquals(200.0f, rutaDeVuelo.getCostoEjecutivo(), 0.01);
		assertEquals(50.0f, rutaDeVuelo.getCostoEquipajeExtra(), 0.01);
		assertEquals(fechaAlta, rutaDeVuelo.getFechaAlta());
		assertNotNull(rutaDeVuelo.getVuelos());
		assertEquals(vuelos, rutaDeVuelo.getVuelos());
		assertEquals(EstadoRuta.Confirmada, rutaDeVuelo.getEstado());
		assertEquals("Resumen", rutaDeVuelo.getResumen());
		assertEquals(null, rutaDeVuelo.getImagen());
		
		rutaDeVuelo.getVideo();
		rutaDeVuelo.setVideo(null);
		rutaDeVuelo.getVisitas();
		rutaDeVuelo.setVisitas(0);
		rutaDeVuelo.setCategorias(rutaDeVuelo.getCategorias());
		rutaDeVuelo.setListComercializa(rutaDeVuelo.getListComercializa());
		rutaDeVuelo.getHora();
		rutaDeVuelo.getCiudadOrigen();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRutaDeVueloSetters() {
		Date fechaAlta = new Date();
		List<Vuelo> vuelos = new ArrayList<>();
		Time hora = new Time(2, 3, 0);
		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("Ruta1", "Descripción de la ruta", 100.0f, 200.0f, 50.0f, fechaAlta,
				null, null, hora, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);

		rutaDeVuelo.setNombre("Ruta2");
		rutaDeVuelo.setDescripcion("Nueva descripción");
		rutaDeVuelo.setCostoTurista(150.0f);
		rutaDeVuelo.setCostoEjecutivo(250.0f);
		rutaDeVuelo.setCostoEquipajeExtra(60.0f);
		Date nuevaFechaAlta = new Date(fechaAlta.getTime() + 10000);
		rutaDeVuelo.setFechaAlta(nuevaFechaAlta);
		List<Vuelo> nuevosVuelos = new ArrayList<>();
		rutaDeVuelo.setVuelos(nuevosVuelos);
		rutaDeVuelo.setEstado(EstadoRuta.Rechazada);
		rutaDeVuelo.setResumen("ResumenNuevo");
		rutaDeVuelo.setImagen("Imagen");

		assertEquals("Ruta2", rutaDeVuelo.getNombre());
		assertEquals("Nueva descripción", rutaDeVuelo.getDescripcion());
		assertEquals(150.0f, rutaDeVuelo.getCostoTurista(), 0.01);
		assertEquals(250.0f, rutaDeVuelo.getCostoEjecutivo(), 0.01);
		assertEquals(60.0f, rutaDeVuelo.getCostoEquipajeExtra(), 0.01);
		assertEquals(nuevaFechaAlta, rutaDeVuelo.getFechaAlta());
		assertEquals(nuevosVuelos, rutaDeVuelo.getVuelos());
		assertEquals(EstadoRuta.Rechazada, rutaDeVuelo.getEstado());
		assertEquals("ResumenNuevo", rutaDeVuelo.getResumen());
		assertEquals("Imagen", rutaDeVuelo.getImagen());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRutaDeVueloToDto() {
		AerolineaDto aerolineaDto = new AerolineaDto("nickName11231232123123", "Nombre Aerolinea",
				"email1231233232123@domain.com", "Descripcion de Aerolinea", "http://website.com", null, new Date(1924-1900, 0, 1), "Contra",
				null, null, null);

		// Crear un objeto Aerolinea usando el DTO
		Aerolinea aerolinea = new Aerolinea(aerolineaDto);

		Time hora = new Time(0, 0, 0);
		Date fechaAlta = new Date();
		CiudadDto ciudadDto = new CiudadDto("Montevideo", "Uruguay", "Aeropuerto Internacional de Carrasco",
				"Capital del país", "www.montevideo.gub.uy", fechaAlta);

		Ciudad ciudad = new Ciudad(ciudadDto);
		CiudadDto ciudadDto2 = new CiudadDto("Punta del este", "Uruguay", "Aeropuerto Internacional de Carrasco",
				"Capital del país", "www.montevideo.gub.uy", fechaAlta);

		Ciudad ciudad2 = new Ciudad(ciudadDto);
		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("Ruta1", "Descripción de la ruta", 100.0f, 200.0f, 50.0f, fechaAlta,
				aerolinea, new ArrayList<>(), hora, ciudad, ciudad2, EstadoRuta.Confirmada, "Resumen", null, null, null);
		aerolinea.getRutasDeVuelo().put(rutaDeVuelo.getNombre(), rutaDeVuelo);

		RutaDeVueloDto rutaDeVueloDto = rutaDeVuelo.toDto();

		assertEquals("Ruta1", rutaDeVueloDto.getNombre());
		assertEquals("Descripción de la ruta", rutaDeVueloDto.getDescripcion());
		assertEquals(100.0f, rutaDeVueloDto.getCostoTurista(), 0.01);
		assertEquals(200.0f, rutaDeVueloDto.getCostoEjecutivo(), 0.01);
		assertEquals(50.0f, rutaDeVueloDto.getCostoEquipajeExtra(), 0.01);
		assertEquals(fechaAlta, rutaDeVueloDto.getFechaAlta());
		assertEquals(EstadoRuta.Confirmada, rutaDeVueloDto.getEstado());
		assertEquals("Resumen", rutaDeVueloDto.getResumen());
		assertEquals(null, rutaDeVueloDto.getImagen());
	}
}
