package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import uy.edu.fing.volandouy.dto.PasajeDto;

public class PasajeDtoTest {

    private PasajeDto pasajeDto;
    private String nombre;
    private String apellido;
    private String numeroAsiento;

    @Before
    public void setUp() {
        nombre = "Juan";
        apellido = "Perez";
        numeroAsiento = "12A";
        
        pasajeDto = new PasajeDto(nombre, apellido, numeroAsiento);
    }

    @Test
    public void testConstructorAndGetters() {
        // Verificar que los valores del constructor sean correctos
        assertEquals(nombre, pasajeDto.getNombre());
        assertEquals(apellido, pasajeDto.getApellido());
        assertEquals(numeroAsiento, pasajeDto.getNumeroAsiento());
    }

    @Test
    public void testSetNombre() {
        pasajeDto.setNombre("Carlos");
        assertEquals("Carlos", pasajeDto.getNombre());
    }

    @Test
    public void testSetApellido() {
        pasajeDto.setApellido("Gomez");
        assertEquals("Gomez", pasajeDto.getApellido());
    }

    @Test
    public void testSetNumeroAsiento() {
        pasajeDto.setNumeroAsiento("15B");
        assertEquals("15B", pasajeDto.getNumeroAsiento());
    }
}
