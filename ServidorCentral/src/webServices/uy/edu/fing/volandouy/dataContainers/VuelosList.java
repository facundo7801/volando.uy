package uy.edu.fing.volandouy.dataContainers;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import uy.edu.fing.volandouy.dto.VueloDto;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"listaVuelos"
})
public class VuelosList {
	@XmlElement
	private List<VueloDto> listaVuelos;
	
	public VuelosList() {
		listaVuelos = new ArrayList<>();
	}
	
	public List<VueloDto> getListaVuelos() {
		return listaVuelos;
	}
	
	public void setListaVuelos(List<VueloDto> listaVuelos) {
		this.listaVuelos = listaVuelos;
	}
	
}
