package uy.edu.fing.volandouy.managers;

import java.util.ArrayList;
import java.util.List;

import uy.edu.fing.volandouy.modelo.RutaDeVuelo;
import uy.edu.fing.volandouy.modelo.Usuario;
import uy.edu.fing.volandouy.util.SearchUtil;

public class UserManager {
	private static UserManager instance;
	private List<Usuario> usuarios;
	private List<RutaDeVuelo> rutaDeVuelos;

	private UserManager() {
		usuarios = new ArrayList<>();
		rutaDeVuelos = new ArrayList<>();
	}

	public static synchronized UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	public void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	public void agregarRutaDeVuelo(RutaDeVuelo ruta) {
		rutaDeVuelos.add(ruta);
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public Usuario findUserByNickname(String nickName) {
		return SearchUtil.buscarPorCondicion(usuarios, usuario -> usuario.getNickName().equals(nickName));
	}

	public Usuario findUserByEmail(String email) {
		return SearchUtil.buscarPorCondicion(usuarios, usuario -> usuario.getEmail().equals(email));
	}

	public RutaDeVuelo findRutaDeVueloByNombre(String nombre) {
		return SearchUtil.buscarPorCondicion(rutaDeVuelos, ruta -> ruta.getNombre().equals(nombre));
	}

	// Solo para carga de datos
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
