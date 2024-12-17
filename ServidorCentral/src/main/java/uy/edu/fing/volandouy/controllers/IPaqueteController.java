package uy.edu.fing.volandouy.controllers;

import java.util.List;

import uy.edu.fing.volandouy.dto.ComercializaDto;
import uy.edu.fing.volandouy.dto.CompraPaqueteDto;
import uy.edu.fing.volandouy.dto.PaqueteDto;

public interface IPaqueteController {

	void crearPaqueteDeRutaDeVuelo(PaqueteDto paqueteDto) throws Exception;

	void agregarRutaDeVueloAPaquete(ComercializaDto comercializaDto) throws Exception;

	List<PaqueteDto> listarPaquetes();

	void comprarPaquete(CompraPaqueteDto compraPaquetedto) throws Exception;

	public PaqueteDto buscarPaquetePorNombre(String nombre);

}
