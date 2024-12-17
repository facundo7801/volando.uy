package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;

public class UsuarioTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testClienteToDto() {
		ClienteDto clientedto = new ClienteDto("nick123", "John", "john@example.com", "Doe", new Date(),
				TipoDocumento.CI, "12345678", "Uruguay", null, null, new Date(1924 - 1900, 0, 1), "Contra", null, null, null);
		Cliente cliente = new Cliente(clientedto);
		ClienteDto clienteDtoPrueba = cliente.toDto();

		assertEquals("nick123", clienteDtoPrueba.getNickName());
		assertEquals("John", clienteDtoPrueba.getNombre());
		assertEquals("john@example.com", clienteDtoPrueba.getEmail());
		assertEquals("Doe", clienteDtoPrueba.getApellido());
		assertEquals("12345678", clienteDtoPrueba.getNumeroDocumento());
		assertEquals("Uruguay", clienteDtoPrueba.getNacionalidad());
		assertEquals(TipoDocumento.CI, clienteDtoPrueba.getTipoDocumento());
		assertEquals(new ArrayList<>(), cliente.toDto().getReservas());
		assertEquals(new ArrayList<>(), cliente.toDto().getComprasPaquete());
		assertEquals("Contra", cliente.toDto().getContrasenia());
		assertEquals(null, cliente.toDto().getImagen());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testAerolíneaToDto() {
		AerolineaDto aerolineadto = new AerolineaDto("air123", "Airline", "contact@airline.com", "Leading airline",
				"www.airline.com", null, new Date(1924 - 1900, 0, 1), "Contra", null, null, null);
		Aerolinea aerolinea = new Aerolinea(aerolineadto);
		assertNotNull(aerolinea.toDto());

		assertEquals("air123", aerolinea.toDto().getNickName());
		assertEquals("Airline", aerolinea.toDto().getNombre());
		assertEquals("contact@airline.com", aerolinea.toDto().getEmail());
		assertEquals("Leading airline", aerolinea.toDto().getDescripcion());
		assertEquals("www.airline.com", aerolinea.toDto().getWebsite());
		assertEquals(new ArrayList<>(), aerolinea.toDto().getRutasDeVuelo());
		assertEquals("Contra", aerolinea.toDto().getContrasenia());
		assertEquals(null, aerolinea.toDto().getImagen());
	}
}
