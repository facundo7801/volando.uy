package uy.edu.fing.volandouy.dataContainers;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import uy.edu.fing.volandouy.dto.CompraPaqueteDto;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"listaCompraPaquetes"
})
public class CompraPaqueteList {
	@XmlElement
	private List<CompraPaqueteDto> listaCompraPaquetes;
	
	public CompraPaqueteList() {
		this.listaCompraPaquetes = new ArrayList<>();
	}
	
	public List<CompraPaqueteDto> getListaCompraPaquetes() {
		return listaCompraPaquetes;
	}
	
	public void setListaCompraPaquetes(List<CompraPaqueteDto> listaCompraPaquetes) {
		this.listaCompraPaquetes = listaCompraPaquetes;
	}
}
