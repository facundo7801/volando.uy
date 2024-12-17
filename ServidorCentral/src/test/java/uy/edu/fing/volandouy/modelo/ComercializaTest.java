package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.ComercializaDto;
import uy.edu.fing.volandouy.dto.PaqueteDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;

public class ComercializaTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testComercializaConstructorAndGetters() {
		// Crear un objeto RutaDeVuelo
		Date fechaAltaRuta = new Date();
		Time hora = new Time(12, 45, 0);
		List<Categoria> categorias = new ArrayList<>();
		//List<Vuelo> vuelos = new ArrayList<>();
		Aerolinea aerolinea = new Aerolinea(new AerolineaDto("nickName1", "Nombre Aerolinea", "email@domain.com",
				"Descripcion de Aerolinea", "http://website.com", new ArrayList<>(), new Date(1924-1900, 0, 1), "Contra", null, null, null));

		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("Ruta1", "Descripción de la ruta", 100.0f, 200.0f, 50.0f,
				fechaAltaRuta, aerolinea, categorias, hora, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);

		// Crear un objeto Paquete
		Date fechaAltaPaquete = new Date();
		PaqueteDto paqueteDto = new PaqueteDto("Paquete1", "Descripción del paquete", 30, 10, fechaAltaPaquete, 300.0f, null, null, null);

		Paquete paquete = new Paquete(paqueteDto);

		// Crear un objeto Comercializa
		Comercializa comercializa = new Comercializa(10, TipoAsiento.Ejecutivo, rutaDeVuelo, paquete);

		// Verificar los valores del objeto Comercializa
		assertEquals(10, comercializa.getCantRutasDeVuelo());
		assertEquals(TipoAsiento.Ejecutivo, comercializa.getTipoDeAsiento());
		assertEquals(rutaDeVuelo, comercializa.getRutaDeVuelo());
		assertEquals(paquete, comercializa.getPaquete());
		
		comercializa.setCantRutasDeVuelo(0);
		comercializa.setPaquete(null);
		comercializa.setRutaDeVuelo(null);
		comercializa.setTipoDeAsiento(TipoAsiento.Turista);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testToDto() {
		// Crear un objeto RutaDeVuelo
		Date fechaAltaRuta = new Date();
		Time hora = new Time(12, 45, 0);
		List<Categoria> categorias = new ArrayList<>();
		//List<Vuelo> vuelos = new ArrayList<>();
		Aerolinea aerolinea = new Aerolinea(new AerolineaDto("nickName2", "Otro Nombre", "anotheremail@domain.com",
				"Otra Descripcion", "http://anotherwebsite.com", new ArrayList<>(), new Date(1924-1900, 0, 1), "Contra", null, null, null));

		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("Ruta2", "Otra descripción", 150.0f, 250.0f, 75.0f, fechaAltaRuta,
				aerolinea, categorias, hora, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);

		// Crear un objeto Paquete
		Date fechaAltaPaquete = new Date();
		PaqueteDto paqueteDto = new PaqueteDto("Paquete2", "Otra descripción del paquete", 60, 15, fechaAltaPaquete,
				400.0f, null, null, null);

		Paquete paquete = new Paquete(paqueteDto);

		// Crear un objeto Comercializa
		Comercializa comercializa = new Comercializa(15, TipoAsiento.Turista, rutaDeVuelo, paquete);

		// Convertir Comercializa a DTO
		ComercializaDto dto = comercializa.toDto();

		// Verificar los valores del DTO
		assertEquals(15, dto.getCantRutasDeVuelo());
		assertEquals(TipoAsiento.Turista, dto.getTipoDeAsiento());
		assertEquals(rutaDeVuelo.getNombre(), dto.getRuta());
		assertEquals(paquete.getNombre(), dto.getPaquete());
	}
}
