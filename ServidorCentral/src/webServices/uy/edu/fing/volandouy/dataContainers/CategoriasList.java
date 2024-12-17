package uy.edu.fing.volandouy.dataContainers;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import uy.edu.fing.volandouy.dto.CategoriaDto;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"listaCategorias"
})
public class CategoriasList {
	@XmlElement
	private List<CategoriaDto> listaCategorias;
	
	public CategoriasList(){
		listaCategorias = new ArrayList<>();
	}
	
	public List<CategoriaDto> getListaCategorias() {
		return listaCategorias;
	}
	
	public void setListaCategorias(List<CategoriaDto> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}
	
}
