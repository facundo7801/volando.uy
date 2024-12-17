package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uy.edu.fing.volandouy.dto.PasajeDto;
import uy.edu.fing.volandouy.dto.ReservaDto;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;

public class ReservaDtoTest {

    private ReservaDto reservaDto;
    private Date fechaReserva;
    private TipoAsiento tipoAsiento;
    private int uniEquipajeExtra;
    private float costo;
    private List<PasajeDto> pasajeros;
    private String cliente;
    private String aerolinea;
    private String ruta;
    private String vuelo;
    private String paquete;
    private String embarqueUrl;
    private Date fechaCheckIn;
    private Time embarqueHora;

    @Before
    public void setUp() {
        fechaReserva = new Date();
        tipoAsiento = TipoAsiento.Turista;
        uniEquipajeExtra = 1;
        costo = 250.5f;
        pasajeros = Arrays.asList(new PasajeDto("Juan", "Perez", "12A"));
        cliente = "cliente123";
        aerolinea = "aerolineaXYZ";
        ruta = "Montevideo - Buenos Aires";
        vuelo = "V123";
        paquete = "Paquete vacaciones";
        embarqueUrl = "http://embarquelink.com";
        fechaCheckIn = new Date();
        embarqueHora = null;
        
        reservaDto = new ReservaDto(fechaReserva, tipoAsiento, uniEquipajeExtra, costo,
                pasajeros, cliente, aerolinea, ruta, vuelo, paquete, embarqueUrl, embarqueHora, fechaCheckIn);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(fechaReserva, reservaDto.getFechaReserva());
        assertEquals(tipoAsiento, reservaDto.getTipoAsiento());
        assertEquals(uniEquipajeExtra, reservaDto.getUniEquipajeExtra());
        assertEquals(costo, reservaDto.getCosto(), 0.01);
        assertEquals(pasajeros, reservaDto.getPasajeros());
        assertEquals(cliente, reservaDto.getCliente());
        assertEquals(aerolinea, reservaDto.getAerolinea());
        assertEquals(ruta, reservaDto.getRuta());
        assertEquals(vuelo, reservaDto.getVuelo());
        assertEquals(paquete, reservaDto.getPaquete());
        assertEquals(embarqueUrl, reservaDto.getEmbarqueUrl());
        assertEquals(fechaCheckIn, reservaDto.getFechaCheckIn());
    }

    @Test
    public void testSetters() {
        reservaDto.setFechaReserva(null);
        reservaDto.setTipoAsiento(TipoAsiento.Ejecutivo);
        reservaDto.setUniEquipajeExtra(2);
        reservaDto.setCosto(300.0f);
        reservaDto.setPasajeros(null);
        reservaDto.setCliente("cliente456");
        reservaDto.setAerolinea("aerolineaABC");
        reservaDto.setRuta("Sao Paulo - Rio de Janeiro");
        reservaDto.setVuelo("V124");
        reservaDto.setPaquete("Paquete fin de semana");
        reservaDto.setEmbarqueUrl("http://nuevoenlace.com");
        reservaDto.setFechaCheckIn(null);

        // Verificar los cambios
        assertNull(reservaDto.getFechaReserva());
        assertEquals(TipoAsiento.Ejecutivo, reservaDto.getTipoAsiento());
        assertEquals(2, reservaDto.getUniEquipajeExtra());
        assertEquals(300.0f, reservaDto.getCosto(), 0.01);
        assertNull(reservaDto.getPasajeros());
        assertEquals("cliente456", reservaDto.getCliente());
        assertEquals("aerolineaABC", reservaDto.getAerolinea());
        assertEquals("Sao Paulo - Rio de Janeiro", reservaDto.getRuta());
        assertEquals("V124", reservaDto.getVuelo());
        assertEquals("Paquete fin de semana", reservaDto.getPaquete());
        assertEquals("http://nuevoenlace.com", reservaDto.getEmbarqueUrl());
        assertNull(reservaDto.getFechaCheckIn());
    }
}

