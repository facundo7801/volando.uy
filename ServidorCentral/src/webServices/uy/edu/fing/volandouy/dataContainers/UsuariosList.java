package uy.edu.fing.volandouy.dataContainers;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import uy.edu.fing.volandouy.dto.UsuarioDto;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"listaUsuarios"
})

public class UsuariosList {
	@XmlElement
	private List<UsuarioDto> listaUsuarios;
	
	public UsuariosList(){
		listaUsuarios = new ArrayList<>();
	}
	
	public List<UsuarioDto> getListaUsuarios() {
		return listaUsuarios;
	}
	
	public void setListaUsuarios(List<UsuarioDto> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
}
