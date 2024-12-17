package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import uy.edu.fing.volandouy.dto.ComercializaDto;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;

public class ComercializaDtoTest {

    private ComercializaDto comercializaDto;
    private int cantRutasDeVuelo;
    private TipoAsiento tipoDeAsiento;
    private String ruta;
    private String paquete;

    @Before
    public void setUp() {
        cantRutasDeVuelo = 5;
        tipoDeAsiento = TipoAsiento.Turista; // Suponiendo que TipoAsiento tiene esta constante
        ruta = "RutaEjemplo";
        paquete = "PaqueteEjemplo";

        comercializaDto = new ComercializaDto(cantRutasDeVuelo, tipoDeAsiento, ruta, paquete);
    }

    @Test
    public void testConstructorAndGetters() {
        // Verificar que los valores del constructor sean correctos
        assertEquals(cantRutasDeVuelo, comercializaDto.getCantRutasDeVuelo());
        assertEquals(tipoDeAsiento, comercializaDto.getTipoDeAsiento());
        assertEquals(ruta, comercializaDto.getRuta());
        assertEquals(paquete, comercializaDto.getPaquete());
    }

    @Test
    public void testSetCantRutasDeVuelo() {
        comercializaDto.setCantRutasDeVuelo(10);
        assertEquals(10, comercializaDto.getCantRutasDeVuelo());
    }

    @Test
    public void testSetTipoDeAsiento() {
        TipoAsiento nuevoTipoAsiento = TipoAsiento.Ejecutivo; // Suponiendo que esta constante exista
        comercializaDto.setTipoDeAsiento(nuevoTipoAsiento);
        assertEquals(nuevoTipoAsiento, comercializaDto.getTipoDeAsiento());
    }

    @Test
    public void testSetRuta() {
        comercializaDto.setRuta("RutaNueva");
        assertEquals("RutaNueva", comercializaDto.getRuta());
    }

    @Test
    public void testSetPaquete() {
        comercializaDto.setPaquete("PaqueteNuevo");
        assertEquals("PaqueteNuevo", comercializaDto.getPaquete());
    }
}
