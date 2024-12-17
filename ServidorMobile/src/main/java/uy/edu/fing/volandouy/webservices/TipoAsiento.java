
package uy.edu.fing.volandouy.webservices;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoAsiento.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="tipoAsiento">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Turista"/>
 *     <enumeration value="Ejecutivo"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "tipoAsiento")
@XmlEnum
public enum TipoAsiento {

    @XmlEnumValue("Turista")
    TURISTA("Turista"),
    @XmlEnumValue("Ejecutivo")
    EJECUTIVO("Ejecutivo");
    private final String value;

    TipoAsiento(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoAsiento fromValue(String v) {
        for (TipoAsiento c: TipoAsiento.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
