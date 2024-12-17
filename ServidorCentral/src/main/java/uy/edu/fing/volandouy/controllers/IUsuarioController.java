package uy.edu.fing.volandouy.controllers;

import java.util.List;

import uy.edu.fing.volandouy.dto.RutaDeVueloDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;

public interface IUsuarioController {

	void agregarUsuario(UsuarioDto usuarioDto) throws Exception;

	void actualizarUsuario(UsuarioDto usuarioDto) throws Exception;

	List<UsuarioDto> listarUsuario();

	UsuarioDto obtenerUsuarioPorCorreo(String correo);

	void agregarRuta(RutaDeVueloDto rutaDto) throws Exception;

	List<RutaDeVueloDto> listarRutas(String aerolinea) throws Exception;

	void cambiarEstadoRuta(String aerolinea, String ruta, EstadoRuta estado) throws Exception;
	
	RutaDeVueloDto obtenerRutaDeVueloPorNombre(String nombre);
	
	UsuarioDto obtenerUsuarioPorNickName(String nickName);
}
