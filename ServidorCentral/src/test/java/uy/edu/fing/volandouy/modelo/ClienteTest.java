package uy.edu.fing.volandouy.modelo;

import org.junit.Test;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ClienteTest {

	@Test
	public void testClienteConstructorAndGetters() {
		Date fechaNacimiento = new Date();
		@SuppressWarnings("deprecation")
		ClienteDto clientedto = new ClienteDto("nick123", "John", "john@example.com", "Doe", fechaNacimiento, TipoDocumento.CI, "12345678", "Uruguay", null, null, new Date(1924-1900, 0, 1), "Contra", null, null, null);
		Cliente cliente = new Cliente(clientedto);

		assertEquals("nick123", cliente.getNickName());
		assertEquals("John", cliente.getNombre());
		assertEquals("john@example.com", cliente.getEmail());
		assertEquals("Doe", cliente.getApellido());
		assertEquals(fechaNacimiento, cliente.getFechaNacimiento());
		assertEquals(TipoDocumento.CI, cliente.getTipoDocumento());
		assertEquals("12345678", cliente.getNumeroDocumento());
		assertEquals("Uruguay", cliente.getNacionalidad());
		assertEquals("Contra", cliente.getContrasenia());
		assertEquals(null, cliente.getImagen());
	}

	@Test
	public void testClienteSetters() {
		Date fechaNacimiento = new Date();
		@SuppressWarnings("deprecation")
		ClienteDto clientedto = new ClienteDto("nick123", "John", "john@example.com", "Doe", fechaNacimiento, TipoDocumento.CI, "12345678", "Uruguay", null, null, new Date(1924-1900, 0, 1), "Contra", null, null, null);
		Cliente cliente = new Cliente(clientedto);

		cliente.setNickName("nick456");
		cliente.setNombre("Jane");
		cliente.setEmail("jane@example.com");
		cliente.setApellido("Smith");
		Date nuevaFechaNacimiento = new Date(fechaNacimiento.getTime() - 1000000);
		cliente.setFechaNacimiento(nuevaFechaNacimiento);
		cliente.setTipoDocumento(TipoDocumento.PASAPORTE);
		cliente.setNumeroDocumento("87654321");
		cliente.setNacionalidad("Argentina");
		cliente.setContrasenia("ContraNueva");
		cliente.setImagen("ImagenPrueba");

		assertEquals("nick456", cliente.getNickName());
		assertEquals("Jane", cliente.getNombre());
		assertEquals("jane@example.com", cliente.getEmail());
		assertEquals("Smith", cliente.getApellido());
		assertEquals(nuevaFechaNacimiento, cliente.getFechaNacimiento());
		assertEquals(TipoDocumento.PASAPORTE, cliente.getTipoDocumento());
		assertEquals("87654321", cliente.getNumeroDocumento());
		assertEquals("Argentina", cliente.getNacionalidad());
		assertEquals("ContraNueva", cliente.getContrasenia());
		assertEquals("ImagenPrueba", cliente.getImagen());
	}

	@Test
	public void testClienteToDto() {
		Date fechaNacimiento = new Date();
		@SuppressWarnings("deprecation")
		ClienteDto clientedto = new ClienteDto("nick123", "John", "john@example.com", "Doe", fechaNacimiento, TipoDocumento.CI, "12345678", "Uruguay", null, null, new Date(1924-1900, 0, 1), "Contra", null, null, null);
		Cliente cliente = new Cliente(clientedto);
		
		ClienteDto clienteDtoPrueba = cliente.toDto();

		assertEquals("nick123", clienteDtoPrueba.getNickName());
		assertEquals("John", clienteDtoPrueba.getNombre());
		assertEquals("john@example.com", clienteDtoPrueba.getEmail());
		assertEquals("Doe", clienteDtoPrueba.getApellido());
		assertEquals(fechaNacimiento, clienteDtoPrueba.getFechaNacimiento());
		assertEquals(TipoDocumento.CI, clienteDtoPrueba.getTipoDocumento());
		assertEquals("12345678", clienteDtoPrueba.getNumeroDocumento());
		assertEquals("Uruguay", clienteDtoPrueba.getNacionalidad());
		assertEquals("Contra", clienteDtoPrueba.getContrasenia());
		assertEquals(null, clienteDtoPrueba.getImagen());
	}
	
	@SuppressWarnings("deprecation")
	@Test
    public void testClienteToDto2() {
        // Fecha de nacimiento de prueba
        Date fechaNacimiento = new Date();
        
        // Crear un ClienteDto con valores de prueba
        ClienteDto clientedto = new ClienteDto("nick123", "John", "john@example.com", "Doe", fechaNacimiento, 
                                                TipoDocumento.CI, "12345678", "Uruguay", null, null, 
                                                new Date(1924-1900, 0, 1), "Contra", null, null, null);
        
        // Crear un objeto Cliente a partir del ClienteDto
        Cliente cliente = new Cliente(clientedto);
        
        // Agregar seguidores y seguidos
        Map<String, Usuario> seguidores = new HashMap<>();
        seguidores.put("seguidor1", cliente);
        cliente.setSeguidores(seguidores);
        
        Map<String, Usuario> seguidos = new HashMap<>();
        seguidos.put("seguidor2", cliente);
        cliente.setSeguidos(seguidos);

        // Convertir el Cliente a ClienteDto
        ClienteDto clienteDtoPrueba = cliente.toDto();

        // Verificar que los valores del ClienteDto coincidan con los valores del Cliente
        assertEquals("nick123", clienteDtoPrueba.getNickName());
        assertEquals("John", clienteDtoPrueba.getNombre());
        assertEquals("john@example.com", clienteDtoPrueba.getEmail());
        assertEquals("Doe", clienteDtoPrueba.getApellido());
        assertEquals(fechaNacimiento, clienteDtoPrueba.getFechaNacimiento());
        assertEquals(TipoDocumento.CI, clienteDtoPrueba.getTipoDocumento());
        assertEquals("12345678", clienteDtoPrueba.getNumeroDocumento());
        assertEquals("Uruguay", clienteDtoPrueba.getNacionalidad());
        assertEquals("Contra", clienteDtoPrueba.getContrasenia());
        assertEquals(null, clienteDtoPrueba.getImagen());
        
        // Verificar que los seguidores y seguidos se incluyan correctamente en el ClienteDto
        assertNotNull(clienteDtoPrueba.getSeguidores());
        assertEquals(1, clienteDtoPrueba.getSeguidores().size());
        assertTrue(clienteDtoPrueba.getSeguidores().contains("seguidor1"));

        assertNotNull(clienteDtoPrueba.getSeguidos());
        assertEquals(1, clienteDtoPrueba.getSeguidos().size());
        assertTrue(clienteDtoPrueba.getSeguidos().contains("seguidor2"));

        // Verificar que las reservas y comprasPaquete se gestionen correctamente (en este caso, vacíos)
        assertNotNull(clienteDtoPrueba.getReservas());
        assertTrue(clienteDtoPrueba.getReservas().isEmpty());

        assertNotNull(clienteDtoPrueba.getComprasPaquete());
        assertTrue(clienteDtoPrueba.getComprasPaquete().isEmpty());
    }
}