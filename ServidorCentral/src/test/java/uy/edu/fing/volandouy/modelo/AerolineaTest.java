package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;

public class AerolineaTest {

	@Test
	public void testAerolineaConstructorAndGetters() {
		// Crear un objeto AerolineaDto para usar en el constructor
		List<String> rutas = new ArrayList<>();
		rutas.add("Ruta1");
		rutas.add("Ruta2");

		@SuppressWarnings("deprecation")
		AerolineaDto aerolineaDto = new AerolineaDto("nickName1", "Nombre Aerolinea", "email@domain.com",
				"Descripcion de Aerolinea", "http://website.com", rutas, new Date(1924 - 1900, 0, 1), "Contra", null, null, null);

		// Crear un objeto Aerolinea usando el DTO
		Aerolinea aerolinea = new Aerolinea(aerolineaDto);

		// Verificar los valores del objeto Aerolinea
		assertEquals("nickName1", aerolinea.getNickName());
		assertEquals("Nombre Aerolinea", aerolinea.getNombre());
		assertEquals("email@domain.com", aerolinea.getEmail());
		assertEquals("Descripcion de Aerolinea", aerolinea.getDescripcion());
		assertEquals("http://website.com", aerolinea.getWebsite());
		assertEquals("Contra", aerolinea.getContrasenia());
		assertEquals(null, aerolinea.getImagen());

		// Verificar las rutas de vuelo (deberían estar vacías al inicio)
		Map<String, RutaDeVuelo> rutasDeVuelo = aerolinea.getRutasDeVuelo();
		assertNotNull(rutasDeVuelo);
		assertTrue(rutasDeVuelo.isEmpty());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testToDto() {
		// Crear una Aerolinea
		Date fechaAlta = new Date();
		Time hora = new Time(12, 45, 0);
		List<Categoria> categorias = new ArrayList<>();
		//List<Vuelo> vuelos = new ArrayList<>();
		Aerolinea aerolinea = new Aerolinea(
				new AerolineaDto("nickName2", "Otro Nombre", "anotheremail@domain.com", "Otra Descripcion",
						"http://anotherwebsite.com", new ArrayList<>(), new Date(1924 - 1900, 0, 1), "Contra", null, null, null));

		// Crear una RutaDeVuelo
		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("Ruta1", "Descripción de la ruta", 100.0f, 200.0f, 50.0f, fechaAlta,
				aerolinea, categorias, hora, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);

		// Añadir la ruta de vuelo a la Aerolinea
		aerolinea.getRutasDeVuelo().put("Ruta1", rutaDeVuelo);

		// Convertir Aerolinea a DTO
		AerolineaDto dto = aerolinea.toDto();

		// Verificar los valores del DTO
		assertEquals("nickName2", dto.getNickName());
		assertEquals("Otro Nombre", dto.getNombre());
		assertEquals("anotheremail@domain.com", dto.getEmail());
		assertEquals("Otra Descripcion", dto.getDescripcion());
		assertEquals("http://anotherwebsite.com", dto.getWebsite());
		assertEquals("Contra", dto.getContrasenia());
		assertEquals(null, dto.getImagen());

		// Verificar las rutas de vuelo en el DTO
		List<String> nombreRutas = dto.getRutasDeVuelo();
		assertNotNull(nombreRutas);
		assertEquals(1, nombreRutas.size());
		assertTrue(nombreRutas.contains("Ruta1"));
	}
	
	@SuppressWarnings("deprecation")
	@Test
    public void testToDto2() {
		// Crear un objeto AerolineaDto
        AerolineaDto aerolineaDto = new AerolineaDto("nickName2", "Otro Nombre", "anotheremail@domain.com", 
            "Otra Descripcion", "http://anotherwebsite.com", new ArrayList<>(), new java.util.Date(), "Contra", null, 
            new ArrayList<>(), new ArrayList<>());

        Aerolinea aerolinea = new Aerolinea(aerolineaDto);

        // Crear una RutaDeVuelo y agregarla a la Aerolinea
        RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("Ruta1", "Descripción de la ruta", 100.0f, 200.0f, 50.0f, 
            new java.util.Date(), aerolinea, new ArrayList<>(), new java.sql.Time(12, 45, 0), null, null, EstadoRuta.Confirmada, 
            "Resumen", null, null, null);
        
        aerolinea.getRutasDeVuelo().put("Ruta1", rutaDeVuelo);

        aerolinea.getSeguidos().put("nickNameSeguido", aerolinea);
        aerolinea.getSeguidores().put("nickNameSeguidor", aerolinea);

        AerolineaDto dto = aerolinea.toDto();

        assertEquals("nickName2", dto.getNickName());
        assertEquals("Otro Nombre", dto.getNombre());
        assertEquals("anotheremail@domain.com", dto.getEmail());
        assertEquals("Otra Descripcion", dto.getDescripcion());
        assertEquals("http://anotherwebsite.com", dto.getWebsite());
        assertEquals("Contra", dto.getContrasenia());
        assertEquals(null, dto.getImagen());

        List<String> nombreRutas = dto.getRutasDeVuelo();
        assertNotNull(nombreRutas);
        assertEquals(1, nombreRutas.size());
        assertTrue(nombreRutas.contains("Ruta1"));

        List<String> seguidos = dto.getSeguidos();
        assertNotNull(seguidos);
        assertEquals(1, seguidos.size());
        assertTrue(seguidos.contains("nickNameSeguido"));

        List<String> seguidores = dto.getSeguidores();
        assertNotNull(seguidores);
        assertEquals(1, seguidores.size());
        assertTrue(seguidores.contains("nickNameSeguidor"));
    }
}
