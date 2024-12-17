package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uy.edu.fing.volandouy.dto.ComercializaDto;
import uy.edu.fing.volandouy.dto.PaqueteDto;

public class PaqueteDtoTest {

    private PaqueteDto paqueteDto;
    private String nombre;
    private String descripcion;
    private Integer periodoValidez;
    private Integer descuento;
    private Date fechaAlta;
    private float costoAsociado;
    private String imagen;
    private List<ComercializaDto> rutas;
    private List<String> compradores;

    @Before
    public void setUp() {
        nombre = "Paquete de Vacaciones";
        descripcion = "Paquete turístico para vacaciones en la playa";
        periodoValidez = 30;
        descuento = 10;
        fechaAlta = new Date();
        costoAsociado = 500.75f;
        imagen = "ruta/a/la/imagen.jpg";
        
        // Inicializando las listas
        rutas = new ArrayList<>();
        compradores = new ArrayList<>();
        
        // Agregando datos de ejemplo a las listas
        rutas.add(new ComercializaDto(5, null, "Ruta1", "PaqueteA"));
        compradores.add("Cliente1");

        paqueteDto = new PaqueteDto(nombre, descripcion, periodoValidez, descuento, fechaAlta, costoAsociado, rutas, compradores, imagen);
    }

    @Test
    public void testConstructorAndGetters() {
        // Verificar que los valores del constructor sean correctos
        assertEquals(nombre, paqueteDto.getNombre());
        assertEquals(descripcion, paqueteDto.getDescripcion());
        assertEquals(periodoValidez, paqueteDto.getPeriodoValidez());
        assertEquals(descuento, paqueteDto.getDescuento());
        assertEquals(fechaAlta, paqueteDto.getFechaAlta());
        assertEquals(costoAsociado, paqueteDto.getCostoAsociado(), 0.01f);
        assertEquals(imagen, paqueteDto.getImagen());
        assertEquals(rutas, paqueteDto.getRutas());
        assertEquals(compradores, paqueteDto.getCompradores());
    }

    @Test
    public void testSetNombre() {
        paqueteDto.setNombre("Nuevo Paquete");
        assertEquals("Nuevo Paquete", paqueteDto.getNombre());
    }

    @Test
    public void testSetDescripcion() {
        paqueteDto.setDescripcion("Nuevo paquete turístico");
        assertEquals("Nuevo paquete turístico", paqueteDto.getDescripcion());
    }

    @Test
    public void testSetPeriodoValidez() {
        paqueteDto.setPeriodoValidez(40);
        assertEquals(Integer.valueOf(40), paqueteDto.getPeriodoValidez());
    }

    @Test
    public void testSetDescuento() {
        paqueteDto.setDescuento(15);
        assertEquals(Integer.valueOf(15), paqueteDto.getDescuento());
    }

    @Test
    public void testSetFechaAlta() {
        Date nuevaFecha = new Date();
        paqueteDto.setFechaAlta(nuevaFecha);
        assertEquals(nuevaFecha, paqueteDto.getFechaAlta());
    }

    @Test
    public void testSetCostoAsociado() {
        paqueteDto.setCostoAsociado(600.00f);
        assertEquals(600.00f, paqueteDto.getCostoAsociado(), 0.01f);
    }

    @Test
    public void testSetImagen() {
        paqueteDto.setImagen("nueva/ruta/a/la/imagen.jpg");
        assertEquals("nueva/ruta/a/la/imagen.jpg", paqueteDto.getImagen());
    }

    @Test
    public void testSetRutas() {
        List<ComercializaDto> nuevasRutas = new ArrayList<>();
        nuevasRutas.add(new ComercializaDto(3, null, "Ruta2", "PaqueteB"));
        paqueteDto.setRutas(nuevasRutas);
        assertEquals(nuevasRutas, paqueteDto.getRutas());
    }

    @Test
    public void testSetCompradores() {
        List<String> nuevosCompradores = new ArrayList<>();
        nuevosCompradores.add("Cliente2");
        paqueteDto.setCompradores(nuevosCompradores);
        assertEquals(nuevosCompradores, paqueteDto.getCompradores());
    }
}

