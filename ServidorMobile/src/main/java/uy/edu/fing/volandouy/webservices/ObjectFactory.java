
package uy.edu.fing.volandouy.webservices;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uy.edu.fing.volandouy.webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Exception_QNAME = new QName("http://volandouy.fing.edu.uy/webServices/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.edu.fing.volandouy.webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     * @return
     *     the new instance of {@link Exception }
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link AerolineaDto }
     * 
     * @return
     *     the new instance of {@link AerolineaDto }
     */
    public AerolineaDto createAerolineaDto() {
        return new AerolineaDto();
    }

    /**
     * Create an instance of {@link CategoriaDto }
     * 
     * @return
     *     the new instance of {@link CategoriaDto }
     */
    public CategoriaDto createCategoriaDto() {
        return new CategoriaDto();
    }

    /**
     * Create an instance of {@link CiudadDto }
     * 
     * @return
     *     the new instance of {@link CiudadDto }
     */
    public CiudadDto createCiudadDto() {
        return new CiudadDto();
    }

    /**
     * Create an instance of {@link ClienteDto }
     * 
     * @return
     *     the new instance of {@link ClienteDto }
     */
    public ClienteDto createClienteDto() {
        return new ClienteDto();
    }

    /**
     * Create an instance of {@link ComercializaDto }
     * 
     * @return
     *     the new instance of {@link ComercializaDto }
     */
    public ComercializaDto createComercializaDto() {
        return new ComercializaDto();
    }

    /**
     * Create an instance of {@link CompraPaqueteDto }
     * 
     * @return
     *     the new instance of {@link CompraPaqueteDto }
     */
    public CompraPaqueteDto createCompraPaqueteDto() {
        return new CompraPaqueteDto();
    }

    /**
     * Create an instance of {@link PaqueteDto }
     * 
     * @return
     *     the new instance of {@link PaqueteDto }
     */
    public PaqueteDto createPaqueteDto() {
        return new PaqueteDto();
    }

    /**
     * Create an instance of {@link PasajeDto }
     * 
     * @return
     *     the new instance of {@link PasajeDto }
     */
    public PasajeDto createPasajeDto() {
        return new PasajeDto();
    }

    /**
     * Create an instance of {@link ReservaDto }
     * 
     * @return
     *     the new instance of {@link ReservaDto }
     */
    public ReservaDto createReservaDto() {
        return new ReservaDto();
    }

    /**
     * Create an instance of {@link Time }
     * 
     * @return
     *     the new instance of {@link Time }
     */
    public Time createTime() {
        return new Time();
    }

    /**
     * Create an instance of {@link RutaDeVueloDto }
     * 
     * @return
     *     the new instance of {@link RutaDeVueloDto }
     */
    public RutaDeVueloDto createRutaDeVueloDto() {
        return new RutaDeVueloDto();
    }

    /**
     * Create an instance of {@link VueloDto }
     * 
     * @return
     *     the new instance of {@link VueloDto }
     */
    public VueloDto createVueloDto() {
        return new VueloDto();
    }

    /**
     * Create an instance of {@link LupaDto }
     * 
     * @return
     *     the new instance of {@link LupaDto }
     */
    public LupaDto createLupaDto() {
        return new LupaDto();
    }

    /**
     * Create an instance of {@link VuelosList }
     * 
     * @return
     *     the new instance of {@link VuelosList }
     */
    public VuelosList createVuelosList() {
        return new VuelosList();
    }

    /**
     * Create an instance of {@link CategoriasList }
     * 
     * @return
     *     the new instance of {@link CategoriasList }
     */
    public CategoriasList createCategoriasList() {
        return new CategoriasList();
    }

    /**
     * Create an instance of {@link CiudadesList }
     * 
     * @return
     *     the new instance of {@link CiudadesList }
     */
    public CiudadesList createCiudadesList() {
        return new CiudadesList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     */
    @XmlElementDecl(namespace = "http://volandouy.fing.edu.uy/webServices/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<>(_Exception_QNAME, Exception.class, null, value);
    }

}
