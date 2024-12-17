
package uy.edu.fing.volandouy.webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para reservasList complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="reservasList">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="listaReserva" type="{http://volandouy.fing.edu.uy/webServices/}reservaDto" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservasList", propOrder = {
    "listaReserva"
})
public class ReservasList {

    protected List<ReservaDto> listaReserva;

    /**
     * Gets the value of the listaReserva property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the listaReserva property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaReserva().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReservaDto }
     * 
     * 
     * @return
     *     The value of the listaReserva property.
     */
    public List<ReservaDto> getListaReserva() {
        if (listaReserva == null) {
            listaReserva = new ArrayList<>();
        }
        return this.listaReserva;
    }

}
