package uy.edu.fing.volandouy.managers;

import java.util.ArrayList;
import java.util.List;

import uy.edu.fing.volandouy.modelo.Categoria;
import uy.edu.fing.volandouy.modelo.Ciudad;
import uy.edu.fing.volandouy.modelo.Vuelo;
import uy.edu.fing.volandouy.util.SearchUtil;

public class VueloManager {
	private static VueloManager instance;
	private List<Ciudad> ciudades;
	private List<Vuelo> vuelos;
	private List<Categoria> categorias;

	private VueloManager() {
		ciudades = new ArrayList<>();
		vuelos = new ArrayList<>();
		categorias = new ArrayList<>();
	}

	public static synchronized VueloManager getInstance() {
		if (instance == null) {
			instance = new VueloManager();
		}
		return instance;
	}

	public void agregarCiudad(Ciudad ciudad) throws Exception {
		ciudades.add(ciudad);
	}

	public Ciudad buscarCiudadPorNombre(String nombre, String pais) {
		return SearchUtil.buscarPorCondicion(ciudades,
				ciudad -> ciudad.getNombre().equals(nombre) && ciudad.getPais().equals(pais));
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void agregarCategoria(Categoria categoria) {
		categorias.add(categoria);
	}

	public Categoria buscarCategoriaPorNombre(String nombre) {
		return SearchUtil.buscarPorCondicion(categorias, categoria -> categoria.getNombre().equals(nombre));
	}

	public void agregarVuelos(Vuelo vuelo) throws Exception {
		vuelos.add(vuelo);
	}

	public Vuelo buscarVuelosPorNombre(String nombre) {
		return SearchUtil.buscarPorCondicion(vuelos, vuelo -> vuelo.getNombre().equals(nombre));
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void removerCategoria(Categoria categoria) {
		categorias.remove(categoria);
	}

	public List<Vuelo> getVuelos() {
		return vuelos;
	}
	
	// Funciones solo para la carga de datos
	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public void setCategoria(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void setVuelos(List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

}
