package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;

public class ClienteDtoTest {

    private ClienteDto cliente;

    @Before
    public void setUp() {
        // Crear listas de ejemplo para reservas y compras
        List<String> reservas = new ArrayList<>();
        reservas.add("Reserva1");

        List<String> comprasPaquete = new ArrayList<>();
        comprasPaquete.add("Paquete1");

        // Crear instancia de ClienteDto con datos de prueba
        cliente = new ClienteDto("nickTest", "Nombre Test", "test@domain.com", "Apellido Test", 
                new Date(1980 - 1900, 5, 15), TipoDocumento.CI, "12345678", "Uruguay", 
                reservas, comprasPaquete, new Date(), "pass123", "imagen.jpg", null, null);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("nickTest", cliente.getNickName());
        assertEquals("Nombre Test", cliente.getNombre());
        assertEquals("test@domain.com", cliente.getEmail());
        assertEquals("Apellido Test", cliente.getApellido());
        assertEquals(TipoDocumento.CI, cliente.getTipoDocumento());
        assertEquals("12345678", cliente.getNumeroDocumento());
        assertEquals("Uruguay", cliente.getNacionalidad());
        assertNotNull(cliente.getFechaNacimiento());
        assertNotNull(cliente.getReservas());
        assertEquals(1, cliente.getReservas().size());
        assertTrue(cliente.getReservas().contains("Reserva1"));
        assertNotNull(cliente.getComprasPaquete());
        assertEquals(1, cliente.getComprasPaquete().size());
        assertTrue(cliente.getComprasPaquete().contains("Paquete1"));
    }

    @Test
    public void testSetters() {
        // Test de setters
        cliente.setApellido("Nuevo Apellido");
        assertEquals("Nuevo Apellido", cliente.getApellido());

        cliente.setFechaNacimiento(new Date(1990 - 1900, 0, 1));
        assertEquals(new Date(1990 - 1900, 0, 1), cliente.getFechaNacimiento());

        cliente.setTipoDocumento(TipoDocumento.PASAPORTE);
        assertEquals(TipoDocumento.PASAPORTE, cliente.getTipoDocumento());

        cliente.setNumeroDocumento("87654321");
        assertEquals("87654321", cliente.getNumeroDocumento());

        cliente.setNacionalidad("Argentina");
        assertEquals("Argentina", cliente.getNacionalidad());

        List<String> nuevasReservas = new ArrayList<>();
        nuevasReservas.add("NuevaReserva1");
        cliente.setReservas(nuevasReservas);
        assertEquals(1, cliente.getReservas().size());
        assertTrue(cliente.getReservas().contains("NuevaReserva1"));

        List<String> nuevasComprasPaquete = new ArrayList<>();
        nuevasComprasPaquete.add("NuevoPaquete1");
        cliente.setComprasPaquete(nuevasComprasPaquete);
        assertEquals(1, cliente.getComprasPaquete().size());
        assertTrue(cliente.getComprasPaquete().contains("NuevoPaquete1"));
    }
}

