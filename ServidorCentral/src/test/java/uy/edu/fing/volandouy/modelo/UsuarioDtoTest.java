package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioDtoTest {

    @Test
    public void testClienteDto() {
        List<String> seguidos = new ArrayList<>();
        seguidos.add("Seguido1");
        List<String> seguidores = new ArrayList<>();
        seguidores.add("Seguidor1");

        ClienteDto cliente = new ClienteDto(
                "clienteNick", "NombreCliente", "cliente@correo.com", "ApellidoCliente",
                new Date(), TipoDocumento.CI, "12345678", "Uruguay", null, null,
                new Date(), "password123", "imagenCliente", seguidos, seguidores
        );

        assertEquals("clienteNick", cliente.getNickName());
        assertEquals("NombreCliente", cliente.getNombre());
        assertEquals("cliente@correo.com", cliente.getEmail());
        assertEquals("ApellidoCliente", cliente.getApellido());
        assertEquals("imagenCliente", cliente.getImagen());
        assertEquals(seguidos, cliente.getSeguidos());
        assertEquals(seguidores, cliente.getSeguidores());
    }

    @Test
    public void testAerolineaDto() {
        List<String> rutas = new ArrayList<>();
        rutas.add("Ruta1");

        AerolineaDto aerolinea = new AerolineaDto(
                "aerolineaNick", "NombreAerolinea", "aerolinea@correo.com", 
                "DescripcionAerolinea", "http://website.com", rutas, new Date(),
                "password123", "imagenAerolinea", null, null
        );

        assertEquals("aerolineaNick", aerolinea.getNickName());
        assertEquals("NombreAerolinea", aerolinea.getNombre());
        assertEquals("aerolinea@correo.com", aerolinea.getEmail());
        assertEquals("DescripcionAerolinea", aerolinea.getDescripcion());
        assertEquals("http://website.com", aerolinea.getWebsite());
        assertEquals("imagenAerolinea", aerolinea.getImagen());
        assertNotNull(aerolinea.getRutasDeVuelo());
        assertEquals(1, aerolinea.getRutasDeVuelo().size());
    }
}

