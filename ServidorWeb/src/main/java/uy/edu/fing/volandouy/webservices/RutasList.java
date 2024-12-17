
package uy.edu.fing.volandouy.webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para rutasList complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="rutasList">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="listaRutas" type="{http://volandouy.fing.edu.uy/webServices/}rutaDeVueloDto" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rutasList", propOrder = {
    "listaRutas"
})
public class RutasList {

    protected List<RutaDeVueloDto> listaRutas;

    /**
     * Gets the value of the listaRutas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the listaRutas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaRutas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RutaDeVueloDto }
     * 
     * 
     * @return
     *     The value of the listaRutas property.
     */
    public List<RutaDeVueloDto> getListaRutas() {
        if (listaRutas == null) {
            listaRutas = new ArrayList<>();
        }
        return this.listaRutas;
    }

}
