package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uy.edu.fing.volandouy.dto.CompraPaqueteDto;

public class CompraPaqueteDtoTest {

    private CompraPaqueteDto compraPaqueteDto;
    private Date fechaCompra;
    private Date vencimiento;
    private String comprador;
    private String paquete;
    private List<String> reservas;

    @Before
    public void setUp() {
        fechaCompra = new Date();
        vencimiento = new Date();
        comprador = "Comprador1";
        paquete = "Paquete1";
        reservas = new ArrayList<>();
        reservas.add("Vuelo1");
        reservas.add("Vuelo2");

        compraPaqueteDto = new CompraPaqueteDto(fechaCompra, vencimiento, comprador, paquete, reservas);
    }

    @Test
    public void testConstructorAndGetters() {
        // Verificar que los valores del constructor sean correctos
        assertEquals(fechaCompra, compraPaqueteDto.getFechaCompra());
        assertEquals(vencimiento, compraPaqueteDto.getVencimiento());
        assertEquals(comprador, compraPaqueteDto.getComprador());
        assertEquals(paquete, compraPaqueteDto.getPaquete());
        assertEquals(reservas, compraPaqueteDto.getReservas());
    }

    @Test
    public void testSetFechaCompra() {
        Date nuevaFecha = new Date();
        compraPaqueteDto.setFechaCompra(nuevaFecha);
        assertEquals(nuevaFecha, compraPaqueteDto.getFechaCompra());
    }

    @Test
    public void testSetVencimiento() {
        Date nuevaFechaVencimiento = new Date();
        compraPaqueteDto.setVencimiento(nuevaFechaVencimiento);
        assertEquals(nuevaFechaVencimiento, compraPaqueteDto.getVencimiento());
    }

    @Test
    public void testSetComprador() {
        compraPaqueteDto.setComprador("NuevoComprador");
        assertEquals("NuevoComprador", compraPaqueteDto.getComprador());
    }

    @Test
    public void testSetPaquete() {
        compraPaqueteDto.setPaquete("NuevoPaquete");
        assertEquals("NuevoPaquete", compraPaqueteDto.getPaquete());
    }

    @Test
    public void testSetReservas() {
        List<String> nuevasReservas = new ArrayList<>();
        nuevasReservas.add("Vuelo3");
        compraPaqueteDto.setReservas(nuevasReservas);
        assertEquals(nuevasReservas, compraPaqueteDto.getReservas());
    }
}
