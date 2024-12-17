package uy.edu.fing.volandouy.dataContainers;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import uy.edu.fing.volandouy.dto.PaqueteDto;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"listaPaquetes"
})
public class PaqueteList {
	@XmlElement
	private List<PaqueteDto> listaPaquetes;
	
	public PaqueteList() {
		listaPaquetes = new ArrayList<>();
	}
	
	public List<PaqueteDto> getListaPaquetes() {
		return listaPaquetes;
	}
	
	public void setListaPaquetes(List<PaqueteDto> listaPaquetes) {
		this.listaPaquetes = listaPaquetes;
	}
	
}
