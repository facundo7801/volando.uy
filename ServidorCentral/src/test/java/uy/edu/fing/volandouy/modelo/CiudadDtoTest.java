package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uy.edu.fing.volandouy.dto.CiudadDto;

public class CiudadDtoTest {

    private CiudadDto ciudadDto;
    private String nombre;
    private String pais;
    private String nombreAeropuerto;
    private String descripcion;
    private String website;
    private Date fechaAlta;

    @Before
    public void setUp() {
        nombre = "Ciudad de Ejemplo";
        pais = "Pais de Ejemplo";
        nombreAeropuerto = "Aeropuerto Ejemplo";
        descripcion = "Descripción de la ciudad";
        website = "http://ciudad-ejemplo.com";
        fechaAlta = new Date();

        ciudadDto = new CiudadDto(nombre, pais, nombreAeropuerto, descripcion, website, fechaAlta);
    }

    @Test
    public void testConstructorAndGetters() {
        // Verificar que los valores del constructor sean correctos
        assertEquals(nombre, ciudadDto.getNombre());
        assertEquals(pais, ciudadDto.getPais());
        assertEquals(nombreAeropuerto, ciudadDto.getNombreAeropuerto());
        assertEquals(descripcion, ciudadDto.getDescripcion());
        assertEquals(website, ciudadDto.getWebsite());
        assertEquals(fechaAlta, ciudadDto.getFechaAlta());
    }

    @Test
    public void testSetNombre() {
        ciudadDto.setNombre("Nuevo Nombre Ciudad");
        assertEquals("Nuevo Nombre Ciudad", ciudadDto.getNombre());
    }

    @Test
    public void testSetPais() {
        ciudadDto.setPais("Nuevo Pais");
        assertEquals("Nuevo Pais", ciudadDto.getPais());
    }

    @Test
    public void testSetNombreAeropuerto() {
        ciudadDto.setNombreAeropuerto("Nuevo Aeropuerto");
        assertEquals("Nuevo Aeropuerto", ciudadDto.getNombreAeropuerto());
    }

    @Test
    public void testSetDescripcion() {
        ciudadDto.setDescripcion("Nueva descripción");
        assertEquals("Nueva descripción", ciudadDto.getDescripcion());
    }

    @Test
    public void testSetWebsite() {
        ciudadDto.setWebsite("https://nuevo-website.com");
        assertEquals("https://nuevo-website.com", ciudadDto.getWebsite());
    }

    @Test
    public void testSetFechaAlta() {
        Date nuevaFecha = new Date();
        ciudadDto.setFechaAlta(nuevaFecha);
        assertEquals(nuevaFecha, ciudadDto.getFechaAlta());
    }
}
