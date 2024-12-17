package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import uy.edu.fing.volandouy.dto.CategoriaDto;

public class CategoriaDtoTest {

    private CategoriaDto categoriaDto;
    private String nombre;

    @Before
    public void setUp() {
        nombre = "Categoria 1";
        categoriaDto = new CategoriaDto(nombre);
    }

    @Test
    public void testConstructorAndGetters() {
        // Verificar que el valor del constructor sea correcto
        assertEquals(nombre, categoriaDto.getNombre());
    }

    @Test
    public void testSetNombre() {
        categoriaDto.setNombre("Nueva Categoria");
        assertEquals("Nueva Categoria", categoriaDto.getNombre());
    }

    @Test
    public void testGetNombre() {
        // Verificar que el getter devuelva el valor correcto
        assertEquals(nombre, categoriaDto.getNombre());
    }
}
