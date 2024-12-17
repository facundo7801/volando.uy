package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.CategoriaDto;
import uy.edu.fing.volandouy.dto.CiudadDto;
import uy.edu.fing.volandouy.dto.RutaDeVueloDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;

public class RutaDeVueloDtoTest {

    private RutaDeVueloDto rutaDeVueloDto;
    private AerolineaDto aerolineaDto;
    private CiudadDto ciudadOrigen;
    private CiudadDto ciudadDestino;
    private List<CategoriaDto> categorias;
    private List<String> vuelos;

    @Before
    public void setUp() {
        // Inicializar los datos necesarios para la prueba
        aerolineaDto = new AerolineaDto("Aerolinea1", "Aerolinea de prueba", "contacto@aerolinea1.com", 
                                         "Descripción Aerolinea 1", "www.aerolinea1.com", new ArrayList<>(), 
                                         new Date(), "password123", "imagen.jpg", new ArrayList<>(), new ArrayList<>());
        
        ciudadOrigen = new CiudadDto("Montevideo", "UY", "Aeropuerto de Montevideo", "Descripción de Montevideo", "www.montevideo.com", new Date());
        ciudadDestino = new CiudadDto("Buenos Aires", "AR", "Aeropuerto de Buenos Aires", "Descripción de Buenos Aires", "www.buenosaires.com", new Date());
        categorias = new ArrayList<>();
        vuelos = new ArrayList<>();
        
        // Agregar categorías y vuelos de prueba
        categorias.add(new CategoriaDto("Economica"));
        categorias.add(new CategoriaDto("Ejecutiva"));
        vuelos.add("Vuelo1");
        vuelos.add("Vuelo2");

        // Crear una instancia de RutaDeVueloDto con datos de prueba
        rutaDeVueloDto = new RutaDeVueloDto("Ruta 1", "Ruta de vuelo test", 200, 350, 50, new Date(),
                                            aerolineaDto, categorias, vuelos, new Time(System.currentTimeMillis()),
                                            ciudadOrigen, ciudadDestino, EstadoRuta.Confirmada, "Resumen de la ruta", 
                                            "imagen.jpg", "video.mp4", 100, null);
    }

    @Test
    public void testConstructorAndGetters() {
        // Verificar que los valores asignados en el constructor sean correctos
        assertEquals("Ruta 1", rutaDeVueloDto.getNombre());
        assertEquals("Ruta de vuelo test", rutaDeVueloDto.getDescripcion());
        assertEquals(200, rutaDeVueloDto.getCostoTurista(), 0);
        assertEquals(350, rutaDeVueloDto.getCostoEjecutivo(), 0);
        assertEquals(50, rutaDeVueloDto.getCostoEquipajeExtra(), 0);
        assertNotNull(rutaDeVueloDto.getFechaAlta());
        assertEquals(2, rutaDeVueloDto.getCategorias().size());
        assertEquals(2, rutaDeVueloDto.getVuelos().size());
        assertEquals("Montevideo", rutaDeVueloDto.getCiudadOrigen().getNombre());
        assertEquals("Buenos Aires", rutaDeVueloDto.getCiudadDestino().getNombre());
        assertEquals(EstadoRuta.Confirmada, rutaDeVueloDto.getEstado());
        assertEquals("Resumen de la ruta", rutaDeVueloDto.getResumen());
        assertEquals("imagen.jpg", rutaDeVueloDto.getImagen());
        assertEquals("video.mp4", rutaDeVueloDto.getVideo());
        assertEquals(100, rutaDeVueloDto.getVisitas());
    }

    @Test
    public void testSetters() {
        // Cambiar valores utilizando los setters y verificar que se actualicen correctamente
        rutaDeVueloDto.setNombre("Ruta modificada");
        rutaDeVueloDto.setDescripcion("Descripción modificada");
        rutaDeVueloDto.setCostoTurista(300);
        rutaDeVueloDto.setCostoEjecutivo(400);
        rutaDeVueloDto.setCostoEquipajeExtra(70);
        rutaDeVueloDto.setFechaAlta(new Date());
        rutaDeVueloDto.setCategorias(new ArrayList<>());
        rutaDeVueloDto.setVuelos(new ArrayList<>());
        rutaDeVueloDto.setCiudadorigen(new CiudadDto("Santiago", "Chile", "Aeropuerto de Santiago", "Descripción de Santiago", "www.santiago.com", new Date()));
        rutaDeVueloDto.setCiudadDestino(new CiudadDto("Lima", "PE", "Aeropuerto de Lima", "Descripción de Lima", "www.lima.com", new Date()));
        rutaDeVueloDto.setEstado(EstadoRuta.Ingresada);
        rutaDeVueloDto.setResumen("Nuevo resumen");
        rutaDeVueloDto.setImagen("nueva_imagen.jpg");
        rutaDeVueloDto.setVideo("nuevo_video.mp4");
        rutaDeVueloDto.setVisitas(150);

        // Verificar que los valores hayan sido modificados correctamente
        assertEquals("Ruta modificada", rutaDeVueloDto.getNombre());
        assertEquals("Descripción modificada", rutaDeVueloDto.getDescripcion());
        assertEquals(300, rutaDeVueloDto.getCostoTurista(), 0);
        assertEquals(400, rutaDeVueloDto.getCostoEjecutivo(), 0);
        assertEquals(70, rutaDeVueloDto.getCostoEquipajeExtra(), 0);
        assertNotNull(rutaDeVueloDto.getFechaAlta());
        assertEquals(0, rutaDeVueloDto.getCategorias().size()); // Verificar lista vacía
        assertEquals(0, rutaDeVueloDto.getVuelos().size()); // Verificar lista vacía
        assertEquals("Santiago", rutaDeVueloDto.getCiudadOrigen().getNombre());
        assertEquals("Lima", rutaDeVueloDto.getCiudadDestino().getNombre());
        assertEquals(EstadoRuta.Ingresada, rutaDeVueloDto.getEstado());
        assertEquals("Nuevo resumen", rutaDeVueloDto.getResumen());
        assertEquals("nueva_imagen.jpg", rutaDeVueloDto.getImagen());
        assertEquals("nuevo_video.mp4", rutaDeVueloDto.getVideo());
        assertEquals(150, rutaDeVueloDto.getVisitas());
    }

    @Test
    public void testHoraString() {
        // Verificar si el getter 'horaString' devuelve una cadena válida
        assertNotNull(rutaDeVueloDto.getHoraString());
    }
}
