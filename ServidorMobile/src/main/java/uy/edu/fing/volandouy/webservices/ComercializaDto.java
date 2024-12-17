
package uy.edu.fing.volandouy.webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para comercializaDto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="comercializaDto">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="cantRutasDeVuelo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="tipoDeAsiento" type="{http://volandouy.fing.edu.uy/webServices/}tipoAsiento" minOccurs="0"/>
 *         <element name="ruta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="paquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comercializaDto", propOrder = {
    "cantRutasDeVuelo",
    "tipoDeAsiento",
    "ruta",
    "paquete"
})
public class ComercializaDto {

    protected int cantRutasDeVuelo;
    @XmlSchemaType(name = "string")
    protected TipoAsiento tipoDeAsiento;
    protected String ruta;
    protected String paquete;

    /**
     * Obtiene el valor de la propiedad cantRutasDeVuelo.
     * 
     */
    public int getCantRutasDeVuelo() {
        return cantRutasDeVuelo;
    }

    /**
     * Define el valor de la propiedad cantRutasDeVuelo.
     * 
     */
    public void setCantRutasDeVuelo(int value) {
        this.cantRutasDeVuelo = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDeAsiento.
     * 
     * @return
     *     possible object is
     *     {@link TipoAsiento }
     *     
     */
    public TipoAsiento getTipoDeAsiento() {
        return tipoDeAsiento;
    }

    /**
     * Define el valor de la propiedad tipoDeAsiento.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoAsiento }
     *     
     */
    public void setTipoDeAsiento(TipoAsiento value) {
        this.tipoDeAsiento = value;
    }

    /**
     * Obtiene el valor de la propiedad ruta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * Define el valor de la propiedad ruta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuta(String value) {
        this.ruta = value;
    }

    /**
     * Obtiene el valor de la propiedad paquete.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaquete() {
        return paquete;
    }

    /**
     * Define el valor de la propiedad paquete.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaquete(String value) {
        this.paquete = value;
    }

}
