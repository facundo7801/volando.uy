package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uy.edu.fing.volandouy.dto.AerolineaDto;

public class AerolineaDtoTest {

    private AerolineaDto aerolineaDto;
    private String nickName;
    private String nombre;
    private String email;
    private String descripcion;
    private String website;
    private List<String> rutasDeVuelo;
    private Date fechaAlta;
    private String contrasenia;
    private String imagen;
    private List<String> seguidos;
    private List<String> seguidores;

    @Before
    public void setUp() {
        nickName = "nickName1";
        nombre = "Nombre Aerolinea";
        email = "email@domain.com";
        descripcion = "Descripcion de Aerolinea";
        website = "http://website.com";
        rutasDeVuelo = new ArrayList<>();
        rutasDeVuelo.add("Ruta1");
        rutasDeVuelo.add("Ruta2");
        fechaAlta = new Date();
        contrasenia = "Contra";
        imagen = "imagen.png";
        seguidos = new ArrayList<>();
        seguidores = new ArrayList<>();

        aerolineaDto = new AerolineaDto(nickName, nombre, email, descripcion, website, rutasDeVuelo, fechaAlta, contrasenia, imagen, seguidos, seguidores);
    }

    @Test
    public void testConstructorAndGetters() {
        // Verificar que los valores del constructor sean correctos
        assertEquals(nickName, aerolineaDto.getNickName());
        assertEquals(nombre, aerolineaDto.getNombre());
        assertEquals(email, aerolineaDto.getEmail());
        assertEquals(descripcion, aerolineaDto.getDescripcion());
        assertEquals(website, aerolineaDto.getWebsite());
        assertEquals(rutasDeVuelo, aerolineaDto.getRutasDeVuelo());
        assertEquals(fechaAlta, aerolineaDto.getFechaAlta());
        assertEquals(contrasenia, aerolineaDto.getContrasenia());
        assertEquals(imagen, aerolineaDto.getImagen());
        assertEquals(seguidos, aerolineaDto.getSeguidos());
        assertEquals(seguidores, aerolineaDto.getSeguidores());
    }

    @Test
    public void testSetDescripcion() {
        aerolineaDto.setDescripcion("Nueva descripcion");
        assertEquals("Nueva descripcion", aerolineaDto.getDescripcion());
    }

    @Test
    public void testSetWebsite() {
        aerolineaDto.setWebsite("https://newwebsite.com");
        assertEquals("https://newwebsite.com", aerolineaDto.getWebsite());
    }

    @Test
    public void testSetRutasDeVuelo() {
        List<String> nuevasRutas = new ArrayList<>();
        nuevasRutas.add("Ruta3");
        aerolineaDto.setRutasDeVuelo(nuevasRutas);
        assertEquals(nuevasRutas, aerolineaDto.getRutasDeVuelo());
    }

    @Test
    public void testSetNombre() {
        aerolineaDto.setNombre("Nuevo Nombre");
        assertEquals("Nuevo Nombre", aerolineaDto.getNombre());
    }

    @Test
    public void testSetContrasenia() {
        aerolineaDto.setContrasenia("newPassword123");
        assertEquals("newPassword123", aerolineaDto.getContrasenia());
    }

    @Test
    public void testSetImagen() {
        aerolineaDto.setImagen("newImage.png");
        assertEquals("newImage.png", aerolineaDto.getImagen());
    }

    @Test
    public void testSetSeguidos() {
        List<String> nuevosSeguidos = new ArrayList<>();
        nuevosSeguidos.add("Seguido1");
        aerolineaDto.setSeguidos(nuevosSeguidos);
        assertEquals(nuevosSeguidos, aerolineaDto.getSeguidos());
    }

    @Test
    public void testSetSeguidores() {
        List<String> nuevosSeguidores = new ArrayList<>();
        nuevosSeguidores.add("Seguidor1");
        aerolineaDto.setSeguidores(nuevosSeguidores);
        assertEquals(nuevosSeguidores, aerolineaDto.getSeguidores());
    }

    @Test
    public void testMetodoAbstracto() {
        aerolineaDto.metodoAbstracto(); // Para cobertura, actualmente sin implementación
    }
}
