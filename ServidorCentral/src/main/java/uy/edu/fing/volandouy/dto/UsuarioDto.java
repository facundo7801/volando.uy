package uy.edu.fing.volandouy.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usuarioDto", propOrder = {
    "nickName",
    "nombre",
    "email",
    "fechaAlta",
    "contrasenia",
    "imagen",
    "seguidos",
    "seguidores"
})

public abstract class UsuarioDto {
	@XmlElement
	private String nickName;
	@XmlElement
	private String nombre;
	@XmlElement
	private String email;
	@XmlElement
	private Date fechaAlta;
	@XmlElement
	private String contrasenia;
	@XmlElement
	private String imagen;
	@XmlElement
	private List<String> seguidos;
	@XmlElement
	private List<String> seguidores;

	// Constructor
	public UsuarioDto(String nickName, String nombre, String email, Date fechaAlta, String contrasenia, String imagen, List<String> seguidos, List<String> seguidores) {
		this.nickName = nickName;
		this.nombre = nombre;
		this.email = email;
		this.fechaAlta = fechaAlta;
		this.contrasenia = contrasenia;
		this.imagen = imagen;
		this.seguidos = (ArrayList<String>)seguidos;
		this.seguidores = (ArrayList<String>)seguidores;
	}

	public abstract void metodoAbstracto();
	
	// Getters
	public String getNickName() {
		return nickName;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}
	
	public Date getFechaAlta(){
		return fechaAlta;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public List<String> getSeguidos() {
		return seguidos;
	}
	
	public List<String> getSeguidores() {
		return seguidores;
	}
	
	 // Setters para propiedades modificables
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia; // Considera si necesitas validar la nueva contraseña
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public void setSeguidos(List<String> seguidos) {
    	this.seguidos = (ArrayList<String>)seguidos;
    }
    
    public void setSeguidores(List<String> seguidores) {
    	this.seguidores = seguidores;    
    }
    
    // Setters para el resto de propiedades
    public void setNickName(String nickName) {
    	this.nickName = nickName;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public void setFechaAlta(Date fechaAlta) {
    	this.fechaAlta = fechaAlta;
    }

}
