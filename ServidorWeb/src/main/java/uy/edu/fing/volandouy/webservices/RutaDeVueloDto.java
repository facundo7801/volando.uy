
package uy.edu.fing.volandouy.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para rutaDeVueloDto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="rutaDeVueloDto">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="costoTurista" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="costoEjecutivo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="costoEquipajeExtra" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="hora" type="{http://volandouy.fing.edu.uy/webServices/}time" minOccurs="0"/>
 *         <element name="categorias" type="{http://volandouy.fing.edu.uy/webServices/}categoriaDto" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="vuelos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="aerolinea" type="{http://volandouy.fing.edu.uy/webServices/}aerolineaDto" minOccurs="0"/>
 *         <element name="ciudadOrigen" type="{http://volandouy.fing.edu.uy/webServices/}ciudadDto" minOccurs="0"/>
 *         <element name="ciudadDestino" type="{http://volandouy.fing.edu.uy/webServices/}ciudadDto" minOccurs="0"/>
 *         <element name="estado" type="{http://volandouy.fing.edu.uy/webServices/}estadoRuta" minOccurs="0"/>
 *         <element name="resumen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="video" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="visitas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="horaString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaFinalizada" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rutaDeVueloDto", propOrder = {
    "nombre",
    "descripcion",
    "costoTurista",
    "costoEjecutivo",
    "costoEquipajeExtra",
    "fechaAlta",
    "hora",
    "categorias",
    "vuelos",
    "aerolinea",
    "ciudadOrigen",
    "ciudadDestino",
    "estado",
    "resumen",
    "imagen",
    "video",
    "visitas",
    "horaString",
    "fechaFinalizada"
})
public class RutaDeVueloDto {

    protected String nombre;
    protected String descripcion;
    protected float costoTurista;
    protected float costoEjecutivo;
    protected float costoEquipajeExtra;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAlta;
    protected Time hora;
    protected List<CategoriaDto> categorias;
    protected List<String> vuelos;
    protected AerolineaDto aerolinea;
    protected CiudadDto ciudadOrigen;
    protected CiudadDto ciudadDestino;
    @XmlSchemaType(name = "string")
    protected EstadoRuta estado;
    protected String resumen;
    protected String imagen;
    protected String video;
    protected int visitas;
    protected String horaString;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaFinalizada;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad costoTurista.
     * 
     */
    public float getCostoTurista() {
        return costoTurista;
    }

    /**
     * Define el valor de la propiedad costoTurista.
     * 
     */
    public void setCostoTurista(float value) {
        this.costoTurista = value;
    }

    /**
     * Obtiene el valor de la propiedad costoEjecutivo.
     * 
     */
    public float getCostoEjecutivo() {
        return costoEjecutivo;
    }

    /**
     * Define el valor de la propiedad costoEjecutivo.
     * 
     */
    public void setCostoEjecutivo(float value) {
        this.costoEjecutivo = value;
    }

    /**
     * Obtiene el valor de la propiedad costoEquipajeExtra.
     * 
     */
    public float getCostoEquipajeExtra() {
        return costoEquipajeExtra;
    }

    /**
     * Define el valor de la propiedad costoEquipajeExtra.
     * 
     */
    public void setCostoEquipajeExtra(float value) {
        this.costoEquipajeExtra = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAlta.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Define el valor de la propiedad fechaAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAlta(XMLGregorianCalendar value) {
        this.fechaAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad hora.
     * 
     * @return
     *     possible object is
     *     {@link Time }
     *     
     */
    public Time getHora() {
        return hora;
    }

    /**
     * Define el valor de la propiedad hora.
     * 
     * @param value
     *     allowed object is
     *     {@link Time }
     *     
     */
    public void setHora(Time value) {
        this.hora = value;
    }

    /**
     * Gets the value of the categorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the categorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CategoriaDto }
     * 
     * 
     * @return
     *     The value of the categorias property.
     */
    public List<CategoriaDto> getCategorias() {
        if (categorias == null) {
            categorias = new ArrayList<>();
        }
        return this.categorias;
    }

    /**
     * Gets the value of the vuelos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the vuelos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVuelos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the vuelos property.
     */
    public List<String> getVuelos() {
        if (vuelos == null) {
            vuelos = new ArrayList<>();
        }
        return this.vuelos;
    }

    /**
     * Obtiene el valor de la propiedad aerolinea.
     * 
     * @return
     *     possible object is
     *     {@link AerolineaDto }
     *     
     */
    public AerolineaDto getAerolinea() {
        return aerolinea;
    }

    /**
     * Define el valor de la propiedad aerolinea.
     * 
     * @param value
     *     allowed object is
     *     {@link AerolineaDto }
     *     
     */
    public void setAerolinea(AerolineaDto value) {
        this.aerolinea = value;
    }

    /**
     * Obtiene el valor de la propiedad ciudadOrigen.
     * 
     * @return
     *     possible object is
     *     {@link CiudadDto }
     *     
     */
    public CiudadDto getCiudadOrigen() {
        return ciudadOrigen;
    }

    /**
     * Define el valor de la propiedad ciudadOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link CiudadDto }
     *     
     */
    public void setCiudadOrigen(CiudadDto value) {
        this.ciudadOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad ciudadDestino.
     * 
     * @return
     *     possible object is
     *     {@link CiudadDto }
     *     
     */
    public CiudadDto getCiudadDestino() {
        return ciudadDestino;
    }

    /**
     * Define el valor de la propiedad ciudadDestino.
     * 
     * @param value
     *     allowed object is
     *     {@link CiudadDto }
     *     
     */
    public void setCiudadDestino(CiudadDto value) {
        this.ciudadDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link EstadoRuta }
     *     
     */
    public EstadoRuta getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoRuta }
     *     
     */
    public void setEstado(EstadoRuta value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad resumen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * Define el valor de la propiedad resumen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResumen(String value) {
        this.resumen = value;
    }

    /**
     * Obtiene el valor de la propiedad imagen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Define el valor de la propiedad imagen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

    /**
     * Obtiene el valor de la propiedad video.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideo() {
        return video;
    }

    /**
     * Define el valor de la propiedad video.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideo(String value) {
        this.video = value;
    }

    /**
     * Obtiene el valor de la propiedad visitas.
     * 
     */
    public int getVisitas() {
        return visitas;
    }

    /**
     * Define el valor de la propiedad visitas.
     * 
     */
    public void setVisitas(int value) {
        this.visitas = value;
    }

    /**
     * Obtiene el valor de la propiedad horaString.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraString() {
        return horaString;
    }

    /**
     * Define el valor de la propiedad horaString.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraString(String value) {
        this.horaString = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFinalizada.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFinalizada() {
        return fechaFinalizada;
    }

    /**
     * Define el valor de la propiedad fechaFinalizada.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFinalizada(XMLGregorianCalendar value) {
        this.fechaFinalizada = value;
    }

}
