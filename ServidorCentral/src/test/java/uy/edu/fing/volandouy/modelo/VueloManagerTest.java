package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uy.edu.fing.volandouy.dto.CategoriaDto;
import uy.edu.fing.volandouy.dto.CiudadDto;
import uy.edu.fing.volandouy.managers.VueloManager;

public class VueloManagerTest {

	private VueloManager vueloManager;

	@Before
	public void setUp() {
		vueloManager = VueloManager.getInstance();
		// Elimina los datos previos
		vueloManager.getCiudades().clear();
		vueloManager.getCategorias().clear();
		vueloManager.setCiudades(vueloManager.getCiudades());
		vueloManager.setCategoria(vueloManager.getCategorias());
		vueloManager.setVuelos(vueloManager.getVuelos());
	}

	@Test
	public void testSingletonInstance() {
		VueloManager anotherInstance = VueloManager.getInstance();
		assertSame("VueloManager tendria que ser Singleton", vueloManager, anotherInstance);
	}

	@Test
	public void testAgregarCiudad() throws Exception {
		CiudadDto ciudadDto = new CiudadDto("Montevideo", "Uruguay", "Carrasco", "Capital", "www.montevideo.gub.uy",
				new Date());
		Ciudad ciudad = new Ciudad(ciudadDto);
		vueloManager.agregarCiudad(ciudad);
		List<Ciudad> ciudades = vueloManager.getCiudades();
		assertEquals(1, ciudades.size());
		assertEquals("Montevideo", ciudades.get(0).getNombre());
	}

	@Test
	public void testBuscarCiudadPorNombre() throws Exception {
		CiudadDto ciudadDto = new CiudadDto("Montevideo", "Uruguay", "Carrasco", "Capital", "www.montevideo.gub.uy",
				new Date());
		Ciudad ciudad = new Ciudad(ciudadDto);
		vueloManager.agregarCiudad(ciudad);
		Ciudad encontrada = vueloManager.buscarCiudadPorNombre("Montevideo", "Uruguay");
		assertNotNull("Ciudad tendria que ser encontrada", encontrada);
		assertEquals("Montevideo", encontrada.getNombre());
	}

	@Test
	public void testAgregarCategoria() {
		CategoriaDto categoriadto = new CategoriaDto("Economia");
		Categoria categoria = new Categoria(categoriadto);
		vueloManager.agregarCategoria(categoria);
		List<Categoria> categorias = vueloManager.getCategorias();
		assertEquals(1, categorias.size());
		assertEquals("Economia", categorias.get(0).getNombre());
	}

	@Test
	public void testBuscarCategoriaPorNombre() {
		CategoriaDto categoriadto = new CategoriaDto("Economia");
		Categoria categoria = new Categoria(categoriadto);
		vueloManager.agregarCategoria(categoria);
		Categoria encontrada = vueloManager.buscarCategoriaPorNombre("Economia");
		assertNotNull("Categoria tendria que ser encontrada", encontrada);
		assertEquals("Economia", encontrada.getNombre());
	}

	@Test
	public void testRemoverCategoria() {
		CategoriaDto categoriadto = new CategoriaDto("Economia");
		Categoria categoria = new Categoria(categoriadto);
		vueloManager.agregarCategoria(categoria);
		vueloManager.removerCategoria(categoria);
		List<Categoria> categorias = vueloManager.getCategorias();
		assertTrue(categorias.isEmpty());
	}
}
