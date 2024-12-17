package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uy.edu.fing.volandouy.dto.VueloDto;

public class VueloDtoTest {

    private VueloDto vueloDto;
    private List<String> clientes;

    @Before
    public void setUp() {
        // Inicializar los datos necesarios para la prueba
        clientes = new ArrayList<>();
        clientes.add("Cliente1");
        clientes.add("Cliente2");

        // Crear una instancia de VueloDto con datos de prueba
        vueloDto = new VueloDto("Vuelo 1", new Date(), new Time(System.currentTimeMillis()), 
                                150, 50, new Date(), "Aerolinea 1", "Ruta 1", clientes, "imagen.jpg");
    }

    @Test
    public void testConstructorAndGetters() {
        // Verificar que los valores asignados en el constructor sean correctos
        assertEquals("Vuelo 1", vueloDto.getNombre());
        assertNotNull(vueloDto.getFecha());
        assertNotNull(vueloDto.getDuracion());
        assertEquals(150, vueloDto.getCantMaxAsTurista());
        assertEquals(50, vueloDto.getCantMaxAsEjecutivo());
        assertNotNull(vueloDto.getFechaAlta());
        assertEquals("Aerolinea 1", vueloDto.getAerolinea());
        assertEquals("Ruta 1", vueloDto.getRuta());
        assertNotNull(vueloDto.getClientes());
        assertEquals(2, vueloDto.getClientes().size());
        assertEquals("imagen.jpg", vueloDto.getImagen());
        assertNotNull(vueloDto.getDuracionString());
    }

    @Test
    public void testSetters() {
        // Cambiar valores utilizando los setters y verificar que se actualicen correctamente
        vueloDto.setNombre("Vuelo modificado");
        vueloDto.setFecha(new Date());
        vueloDto.setDuracion(new Time(System.currentTimeMillis()));
        vueloDto.setCantMaxAsTurista(200);
        vueloDto.setCantMaxAsEjecutivo(80);
        vueloDto.setFechaAlta(new Date());
        vueloDto.setAerolinea("Aerolinea modificada");
        vueloDto.setRuta("Ruta modificada");
        vueloDto.setClientes(new ArrayList<>());
        vueloDto.setImagen("nueva_imagen.jpg");
        vueloDto.setDuracionString("2:30:00");

        // Verificar que los valores hayan sido modificados correctamente
        assertEquals("Vuelo modificado", vueloDto.getNombre());
        assertNotNull(vueloDto.getFecha());
        assertNotNull(vueloDto.getDuracion());
        assertEquals(200, vueloDto.getCantMaxAsTurista());
        assertEquals(80, vueloDto.getCantMaxAsEjecutivo());
        assertNotNull(vueloDto.getFechaAlta());
        assertEquals("Aerolinea modificada", vueloDto.getAerolinea());
        assertEquals("Ruta modificada", vueloDto.getRuta());
        assertEquals(0, vueloDto.getClientes().size()); // Verificar lista vacía
        assertEquals("nueva_imagen.jpg", vueloDto.getImagen());
        assertEquals("2:30:00", vueloDto.getDuracionString());
    }

    @Test
    public void testDuracionString() {
        // Verificar si el getter 'duracionString' devuelve una cadena válida
        assertNotNull(vueloDto.getDuracionString());
    }
}
