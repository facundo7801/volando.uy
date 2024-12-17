package uy.edu.fing.volandouy.dataContainers;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import uy.edu.fing.volandouy.dto.ReservaDto;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"listaReserva"
})
public class ReservasList {
	@XmlElement
	private List<ReservaDto> listaReserva;
	
	public ReservasList(){
		listaReserva = new ArrayList<>();
	}

	public List<ReservaDto> getListaReserva() {
		return listaReserva;
	}

	public void setListaReserva(List<ReservaDto> listaReserva) {
		this.listaReserva = listaReserva;
	}
	
	
}
