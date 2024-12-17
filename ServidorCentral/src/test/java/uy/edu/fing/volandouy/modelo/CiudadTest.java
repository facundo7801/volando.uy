package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import uy.edu.fing.volandouy.dto.CiudadDto;

public class CiudadTest {

	@Test
	public void testCiudadConstructorAndGetters() {
		Date fechaAlta = new Date();
		CiudadDto ciudadDto = new CiudadDto("Montevideo", "Uruguay", "Aeropuerto Internacional de Carrasco",
				"Capital del país", "www.montevideo.gub.uy", fechaAlta);

		Ciudad ciudad = new Ciudad(ciudadDto);

		assertEquals("Montevideo", ciudad.getNombre());
		assertEquals("Uruguay", ciudad.getPais());
		assertEquals("Aeropuerto Internacional de Carrasco", ciudad.getNombreAeropuerto());
		assertEquals("Capital del país", ciudad.getDescripcion());
		assertEquals("www.montevideo.gub.uy", ciudad.getWebsite());
		assertEquals(fechaAlta, ciudad.getFechaAlta());
	}

	@Test
	public void testCiudadSetters() {
		Date fechaAlta = new Date();
		CiudadDto ciudadDto = new CiudadDto("Montevideo", "Uruguay", "Aeropuerto Internacional de Carrasco",
				"Capital del país", "www.montevideo.gub.uy", fechaAlta);

		Ciudad ciudad = new Ciudad(ciudadDto);

		ciudad.setNombre("Punta del Este");
		ciudad.setPais("Uruguay");
		ciudad.setNombreAeropuerto("Aeropuerto Internacional de Laguna del Sauce");
		ciudad.setDescripcion("Ciudad turística");
		ciudad.setWebsite("www.puntadeleste.com");
		Date nuevaFechaAlta = new Date(fechaAlta.getTime() + 10000);
		ciudad.setFechaAlta(nuevaFechaAlta);

		assertEquals("Punta del Este", ciudad.getNombre());
		assertEquals("Uruguay", ciudad.getPais());
		assertEquals("Aeropuerto Internacional de Laguna del Sauce", ciudad.getNombreAeropuerto());
		assertEquals("Ciudad turística", ciudad.getDescripcion());
		assertEquals("www.puntadeleste.com", ciudad.getWebsite());
		assertEquals(nuevaFechaAlta, ciudad.getFechaAlta());
	}

	@Test
	public void testCiudadToDto() {
		Date fechaAlta = new Date();
		CiudadDto ciudadDto = new CiudadDto("Montevideo", "Uruguay", "Aeropuerto Internacional de Carrasco",
				"Capital del país", "www.montevideo.gub.uy", fechaAlta);

		Ciudad ciudad = new Ciudad(ciudadDto);
		CiudadDto resultadoDto = ciudad.toDto();

		assertEquals("Montevideo", resultadoDto.getNombre());
		assertEquals("Uruguay", resultadoDto.getPais());
		assertEquals("Aeropuerto Internacional de Carrasco", resultadoDto.getNombreAeropuerto());
		assertEquals("Capital del país", resultadoDto.getDescripcion());
		assertEquals("www.montevideo.gub.uy", resultadoDto.getWebsite());
		assertEquals(fechaAlta, resultadoDto.getFechaAlta());
	}
}
