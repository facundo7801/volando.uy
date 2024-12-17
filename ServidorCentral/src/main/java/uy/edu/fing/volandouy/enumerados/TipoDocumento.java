package uy.edu.fing.volandouy.enumerados;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "TipoDocumento")
@XmlEnum
public enum TipoDocumento {
	CI, PASAPORTE, OTRO;
}
