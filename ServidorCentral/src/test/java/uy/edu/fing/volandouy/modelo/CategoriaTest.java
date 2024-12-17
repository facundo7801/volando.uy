package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import uy.edu.fing.volandouy.dto.CategoriaDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;

public class CategoriaTest {

	@Test
	public void testCategoríaConstructorAndGetters() {
		List<RutaDeVuelo> rutasDeVuelo = new ArrayList<>();
		CategoriaDto categoriaDto = new CategoriaDto("Económica");
		Categoria categoria = new Categoria(categoriaDto);
		categoria.setRutasDeVuelo(rutasDeVuelo);

		assertEquals("Económica", categoria.getNombre());
		assertNotNull(categoria.getRutasDeVuelo());
		assertEquals(rutasDeVuelo, categoria.getRutasDeVuelo());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCategoríaSetters() {
		List<RutaDeVuelo> rutasDeVuelo = new ArrayList<>();
		CategoriaDto categoriaDto = new CategoriaDto("Económica");
		Categoria categoria = new Categoria(categoriaDto);
		categoria.setRutasDeVuelo(rutasDeVuelo);

		categoria.setNombre("Primera Clase");
		assertEquals("Primera Clase", categoria.getNombre());

		List<RutaDeVuelo> nuevasRutasDeVuelo = new ArrayList<>();
		categoria.setRutasDeVuelo(nuevasRutasDeVuelo);
		assertEquals(nuevasRutasDeVuelo, categoria.getRutasDeVuelo());
		
		Date fechaAlta = new Date();
		//List<Vuelo> vuelos = new ArrayList<>();
		Time hora = new Time(2, 3, 0);
		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("Ruta1", "Descripción de la ruta", 100.0f, 200.0f, 50.0f, fechaAlta,
				null, null, hora, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);
		categoria.agregaRutaDeVuelo(rutaDeVuelo);
	}

	@Test
	public void testCategoríaToDto() {
		List<RutaDeVuelo> rutasDeVuelo = new ArrayList<>();
		CategoriaDto categoriaDto = new CategoriaDto("Económica");
		Categoria categoria = new Categoria(categoriaDto);
		categoria.setRutasDeVuelo(rutasDeVuelo);
		CategoriaDto categoriaToDto = categoria.toDto();

		assertEquals("Económica", categoriaToDto.getNombre());
	}
}
