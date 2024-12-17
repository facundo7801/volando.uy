package uy.edu.fing.volandouy.dataContainers;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import uy.edu.fing.volandouy.dto.CiudadDto;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"listaCiudades"
})
public class CiudadesList {
	@XmlElement
	private List<CiudadDto> listaCiudades;
	
	public CiudadesList(){
		listaCiudades = new ArrayList<>();
	}
	
	public List<CiudadDto> getListaCiudades(){
		return listaCiudades;
	}
	
	public void setListaCiudades(List<CiudadDto> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}

}
