
package uy.edu.fing.volandouy.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para reservaDto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="reservaDto">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="fechaReserva" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="tipoAsiento" type="{http://volandouy.fing.edu.uy/webServices/}tipoAsiento" minOccurs="0"/>
 *         <element name="uniEquipajeExtra" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="pasajeros" type="{http://volandouy.fing.edu.uy/webServices/}pasajeDto" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="aerolinea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ruta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="vuelo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="paquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="embarqueUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="embarqueHora" type="{http://volandouy.fing.edu.uy/webServices/}time" minOccurs="0"/>
 *         <element name="fechaCheckIn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="embarqueHoraString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservaDto", propOrder = {
    "fechaReserva",
    "tipoAsiento",
    "uniEquipajeExtra",
    "costo",
    "pasajeros",
    "cliente",
    "aerolinea",
    "ruta",
    "vuelo",
    "paquete",
    "embarqueUrl",
    "embarqueHora",
    "fechaCheckIn",
    "embarqueHoraString"
})
public class ReservaDto {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaReserva;
    @XmlSchemaType(name = "string")
    protected TipoAsiento tipoAsiento;
    protected int uniEquipajeExtra;
    protected float costo;
    protected List<PasajeDto> pasajeros;
    protected String cliente;
    protected String aerolinea;
    protected String ruta;
    protected String vuelo;
    protected String paquete;
    protected String embarqueUrl;
    protected Time embarqueHora;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCheckIn;
    protected String embarqueHoraString;

    /**
     * Obtiene el valor de la propiedad fechaReserva.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Define el valor de la propiedad fechaReserva.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaReserva(XMLGregorianCalendar value) {
        this.fechaReserva = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoAsiento.
     * 
     * @return
     *     possible object is
     *     {@link TipoAsiento }
     *     
     */
    public TipoAsiento getTipoAsiento() {
        return tipoAsiento;
    }

    /**
     * Define el valor de la propiedad tipoAsiento.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoAsiento }
     *     
     */
    public void setTipoAsiento(TipoAsiento value) {
        this.tipoAsiento = value;
    }

    /**
     * Obtiene el valor de la propiedad uniEquipajeExtra.
     * 
     */
    public int getUniEquipajeExtra() {
        return uniEquipajeExtra;
    }

    /**
     * Define el valor de la propiedad uniEquipajeExtra.
     * 
     */
    public void setUniEquipajeExtra(int value) {
        this.uniEquipajeExtra = value;
    }

    /**
     * Obtiene el valor de la propiedad costo.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Gets the value of the pasajeros property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the pasajeros property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPasajeros().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PasajeDto }
     * 
     * 
     * @return
     *     The value of the pasajeros property.
     */
    public List<PasajeDto> getPasajeros() {
        if (pasajeros == null) {
            pasajeros = new ArrayList<>();
        }
        return this.pasajeros;
    }

    /**
     * Obtiene el valor de la propiedad cliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Define el valor de la propiedad cliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCliente(String value) {
        this.cliente = value;
    }

    /**
     * Obtiene el valor de la propiedad aerolinea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAerolinea() {
        return aerolinea;
    }

    /**
     * Define el valor de la propiedad aerolinea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAerolinea(String value) {
        this.aerolinea = value;
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
     * Obtiene el valor de la propiedad vuelo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVuelo() {
        return vuelo;
    }

    /**
     * Define el valor de la propiedad vuelo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVuelo(String value) {
        this.vuelo = value;
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

    /**
     * Obtiene el valor de la propiedad embarqueUrl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmbarqueUrl() {
        return embarqueUrl;
    }

    /**
     * Define el valor de la propiedad embarqueUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmbarqueUrl(String value) {
        this.embarqueUrl = value;
    }

    /**
     * Obtiene el valor de la propiedad embarqueHora.
     * 
     * @return
     *     possible object is
     *     {@link Time }
     *     
     */
    public Time getEmbarqueHora() {
        return embarqueHora;
    }

    /**
     * Define el valor de la propiedad embarqueHora.
     * 
     * @param value
     *     allowed object is
     *     {@link Time }
     *     
     */
    public void setEmbarqueHora(Time value) {
        this.embarqueHora = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaCheckIn.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCheckIn() {
        return fechaCheckIn;
    }

    /**
     * Define el valor de la propiedad fechaCheckIn.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCheckIn(XMLGregorianCalendar value) {
        this.fechaCheckIn = value;
    }

    /**
     * Obtiene el valor de la propiedad embarqueHoraString.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmbarqueHoraString() {
        return embarqueHoraString;
    }

    /**
     * Define el valor de la propiedad embarqueHoraString.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmbarqueHoraString(String value) {
        this.embarqueHoraString = value;
    }

}
