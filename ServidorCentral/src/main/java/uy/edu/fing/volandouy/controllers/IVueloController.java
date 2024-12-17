package uy.edu.fing.volandouy.controllers;

import java.util.List;

import uy.edu.fing.volandouy.dto.CategoriaDto;
import uy.edu.fing.volandouy.dto.CiudadDto;
import uy.edu.fing.volandouy.dto.ReservaDto;
import uy.edu.fing.volandouy.dto.VueloDto;

public interface IVueloController {

	void agregarVuelo(VueloDto vuelodto) throws Exception;

	List<VueloDto> listarVuelo(String aerolinea, String ruta) throws Exception;

	void reservarVuelo(ReservaDto reservadto) throws Exception;

	void agregarCiudad(CiudadDto ciudadDto) throws Exception;

	List<CiudadDto> listarCiudades();

	void altaCategoria(CategoriaDto categoriaDto) throws Exception;

	List<CategoriaDto> listarCategorias();

	CiudadDto buscarCiudadPorNombre(String nombre, String pais);

	CategoriaDto buscarCategoriaPorNombre(String nombre);

	VueloDto buscarVueloPornombre(String nombre);
}
