package uy.edu.fing.volandouy.modelo;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import uy.edu.fing.volandouy.dto.UsuarioDto;

public abstract class Usuario {
	private String nickName;
	private String nombre;
	private String email;
	private Date fechaAlta;
	private String contrasenia;
	private String imagen;
	private Map<String, Usuario> seguidos;
	private Map<String, Usuario> seguidores;

	// Constructor
	public Usuario(String nickName, String nombre, String email, Date fechaAlta, String contrasenia, String imagen) {
		this.nickName = nickName;
		this.nombre = nombre;
		this.email = email;
		this.fechaAlta = fechaAlta;
		this.contrasenia = contrasenia;
		this.imagen = imagen;
		seguidos = new TreeMap<>();
		seguidores = new TreeMap<>();
	}

	// Getters y Setters
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public Map<String, Usuario> getSeguidos(){
		return seguidos;
	}
	
	public void setSeguidos(Map<String, Usuario> seguidos) {
		this.seguidos = seguidos;
	}

	public abstract UsuarioDto toDto();

	public Map<String, Usuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(Map<String, Usuario> seguidores) {
		this.seguidores = seguidores;
	}
}