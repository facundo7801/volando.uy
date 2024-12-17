package uy.edu.fing.volandouy.dataContainers;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import uy.edu.fing.volandouy.dto.RutaDeVueloDto;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"listaRutas"
})
public class RutasList {
	@XmlElement
	private List<RutaDeVueloDto> listaRutas;
	
	public RutasList() {
		listaRutas = new ArrayList<>();
	}
	
	public List<RutaDeVueloDto> getListaRutas() {
		return listaRutas;
	}
	
	public void setListaRutas(List<RutaDeVueloDto> listaRutas) {
		this.listaRutas = listaRutas;
	}
	
}
