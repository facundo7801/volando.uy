
package uy.edu.fing.volandouy.webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para vuelosList complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="vuelosList">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="listaVuelos" type="{http://volandouy.fing.edu.uy/webServices/}vueloDto" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vuelosList", propOrder = {
    "listaVuelos"
})
public class VuelosList {

    protected List<VueloDto> listaVuelos;

    /**
     * Gets the value of the listaVuelos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the listaVuelos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaVuelos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VueloDto }
     * 
     * 
     * @return
     *     The value of the listaVuelos property.
     */
    public List<VueloDto> getListaVuelos() {
        if (listaVuelos == null) {
            listaVuelos = new ArrayList<>();
        }
        return this.listaVuelos;
    }

}
