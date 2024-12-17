package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.CategoriaDto;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.dto.ReservaDto;
import uy.edu.fing.volandouy.dto.VueloDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;

public class VueloTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testVueloConstructorAndGetters() {
		Date fecha = new Date();
		Date fechaAlta = new Date();
		Time hora = new Time(12, 15, 0);

		AerolineaDto aerolineadto = new AerolineaDto("AeroVuelo1", "nombreA", "emailA", "descripcionA", "websiteA",
				null, new Date(1924-1900, 0, 1), "Contra", null, null, null);
		Aerolinea aerolinea = new Aerolinea(aerolineadto);

		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("RutaVuelo1", "Descripción de la ruta", 100.0f, 200.0f, 50.0f,
				fechaAlta, aerolinea, new ArrayList<>(), hora, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);
		List<RutaDeVuelo> rutas = new ArrayList<>();
		rutas.add(rutaDeVuelo);
		aerolinea.getRutasDeVuelo().put(rutaDeVuelo.getNombre(), rutaDeVuelo);

		CategoriaDto categoriaDto = new CategoriaDto("Categoria1");
		Categoria categoria = new Categoria(categoriaDto);
		categoria.setRutasDeVuelo(rutas);
		rutaDeVuelo.getCategorias().add(categoria);

		VueloDto vuelodto = new VueloDto("VueloVuelo1", fecha, hora, 150, 50, fechaAlta, aerolinea.getNickName(),
				rutaDeVuelo.getNombre(), null, null);
		Vuelo vuelo = new Vuelo(vuelodto, rutaDeVuelo);
		rutaDeVuelo.getVuelos().add(vuelo);

		assertEquals("VueloVuelo1", vuelo.getNombre());
		assertEquals(fecha, vuelo.getFecha());
		assertEquals(hora, vuelo.getDuracion());
		assertEquals(150, vuelo.getCantMaxAsTurista());
		assertEquals(50, vuelo.getCantMaxAsEjecutivo());
		assertEquals(fechaAlta, vuelo.getFechaAlta());
		assertEquals(rutaDeVuelo, vuelo.getRutaDeVuelo());
		assertEquals("VueloVuelo1", rutaDeVuelo.getVuelos().get(0).getNombre());
		assertEquals(0, vuelo.getClientes().size());
		assertEquals(null, vuelo.getImagen());
		
		vuelo.setAsientosTurista(vuelo.getAsientosTurista());
		vuelo.setAsientosEjecutivo(vuelo.getAsientosEjecutivo());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testVueloToDto() {
		Date fecha = new Date();
		Date fechaAlta = new Date();
		Time hora = new Time(12, 15, 0);

		AerolineaDto aerolineadto = new AerolineaDto("AeroVuelo2", "nombreA", "emailA", "descripcionA", "websiteA",
				null, new Date(1924-1900, 0, 1), "Contra", null, null, null);
		Aerolinea aerolinea = new Aerolinea(aerolineadto);

		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("RutaVuelo2", "Descripción de la ruta", 100.0f, 200.0f, 50.0f,
				fechaAlta, aerolinea, new ArrayList<>(), hora, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);
		List<RutaDeVuelo> rutas = new ArrayList<>();
		rutas.add(rutaDeVuelo);
		aerolinea.getRutasDeVuelo().put(rutaDeVuelo.getNombre(), rutaDeVuelo);

		CategoriaDto categoriaDto = new CategoriaDto("CategoriaVuelo1");
		Categoria categoria = new Categoria(categoriaDto);
		categoria.setRutasDeVuelo(rutas);
		rutaDeVuelo.getCategorias().add(categoria);

		VueloDto vuelodto = new VueloDto("VueloVuelo2", fecha, hora, 150, 50, fechaAlta, aerolinea.getNickName(),
				rutaDeVuelo.getNombre(), null, null);
		Vuelo vuelo = new Vuelo(vuelodto, rutaDeVuelo);
		rutaDeVuelo.getVuelos().add(vuelo);

		ClienteDto clientedto = new ClienteDto("nickVuelo1", "nombre", "emailVuelo1", "apellido", new Date(),
				TipoDocumento.CI, "1234", "nacionalidad", null, null, new Date(1924-1900, 0, 1), "Contra", null, null, null);
		Cliente cliente = new Cliente(clientedto);
		ReservaDto reservadto = new ReservaDto(new Date(), TipoAsiento.Turista, 2, 4, new ArrayList<>(),
				cliente.getNickName(), aerolinea.getNickName(), rutaDeVuelo.getNombre(), vuelo.getNombre(), null, null, null, null);
		Reserva reserva = new Reserva(reservadto, cliente, vuelo, null, null);
		vuelo.getClientes().put(cliente.getNickName(), reserva);

		VueloDto vuelodtoPrueba = vuelo.toDto();

		assertEquals("VueloVuelo2", vuelodtoPrueba.getNombre());
		assertEquals(fecha, vuelodtoPrueba.getFecha());
		assertEquals(hora, vuelodtoPrueba.getDuracion());
		assertEquals(150, vuelodtoPrueba.getCantMaxAsTurista());
		assertEquals(50, vuelodtoPrueba.getCantMaxAsEjecutivo());
		assertEquals(fechaAlta, vuelodtoPrueba.getFechaAlta());
		assertEquals(reserva, vuelo.getClientes().get(cliente.getNickName()));
		assertEquals(cliente.getNickName(), vuelodtoPrueba.getClientes().get(0));
		assertEquals(aerolinea.getNickName(), vuelodtoPrueba.getAerolinea());
		assertEquals(rutaDeVuelo.getNombre(), vuelodtoPrueba.getRuta());
		assertEquals(null, vuelodtoPrueba.getImagen());
	}
}
