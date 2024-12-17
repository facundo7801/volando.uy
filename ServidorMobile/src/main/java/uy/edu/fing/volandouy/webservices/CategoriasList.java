
package uy.edu.fing.volandouy.webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para categoriasList complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="categoriasList">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="listaCategorias" type="{http://volandouy.fing.edu.uy/webServices/}categoriaDto" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categoriasList", propOrder = {
    "listaCategorias"
})
public class CategoriasList {

    protected List<CategoriaDto> listaCategorias;

    /**
     * Gets the value of the listaCategorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the listaCategorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaCategorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CategoriaDto }
     * 
     * 
     * @return
     *     The value of the listaCategorias property.
     */
    public List<CategoriaDto> getListaCategorias() {
        if (listaCategorias == null) {
            listaCategorias = new ArrayList<>();
        }
        return this.listaCategorias;
    }

}
