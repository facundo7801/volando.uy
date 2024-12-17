
package uy.edu.fing.volandouy.webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ciudadesList complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="ciudadesList">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="listaCiudades" type="{http://volandouy.fing.edu.uy/webServices/}ciudadDto" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ciudadesList", propOrder = {
    "listaCiudades"
})
public class CiudadesList {

    protected List<CiudadDto> listaCiudades;

    /**
     * Gets the value of the listaCiudades property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the listaCiudades property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaCiudades().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CiudadDto }
     * 
     * 
     * @return
     *     The value of the listaCiudades property.
     */
    public List<CiudadDto> getListaCiudades() {
        if (listaCiudades == null) {
            listaCiudades = new ArrayList<>();
        }
        return this.listaCiudades;
    }

}
