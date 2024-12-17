package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uy.edu.fing.volandouy.dto.PasajeDto;

public class PasajeTest {

	@Test
	public void testPasaje() {
		Pasaje pasaje = new Pasaje("Juan", "Rodriguez", null);

		assertEquals("Juan", pasaje.getNombre());
		assertEquals("Rodriguez", pasaje.getApellido());
		assertEquals(null, pasaje.getReserva());
	}

	@Test
	public void testPasajeToDto() {
		Pasaje pasaje = new Pasaje("Juan", "Rodriguez", null);
		PasajeDto pasajedto = pasaje.toDto();
		pasaje.getNumeroAsiento();

		assertEquals("Juan", pasajedto.getNombre());
		assertEquals("Rodriguez", pasajedto.getApellido());
	}
}
