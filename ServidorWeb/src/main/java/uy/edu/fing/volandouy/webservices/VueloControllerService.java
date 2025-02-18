
package uy.edu.fing.volandouy.webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "VueloControllerService", targetNamespace = "http://volandouy.fing.edu.uy/webServices/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface VueloControllerService {


    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns uy.edu.fing.volandouy.webservices.CiudadDto
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/buscarCiudadPorNombreRequest", output = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/buscarCiudadPorNombreResponse")
    public CiudadDto buscarCiudadPorNombre(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns uy.edu.fing.volandouy.webservices.CategoriaDto
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/buscarCategoriaPorNombreRequest", output = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/buscarCategoriaPorNombreResponse")
    public CategoriaDto buscarCategoriaPorNombre(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns uy.edu.fing.volandouy.webservices.VueloDto
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/buscarVueloPornombreRequest", output = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/buscarVueloPornombreResponse")
    public VueloDto buscarVueloPornombre(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @throws Exception_Exception
     */
    @WebMethod
    @Action(input = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/agregarVueloRequest", output = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/agregarVueloResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/agregarVuelo/Fault/Exception")
    })
    public void agregarVuelo(
        @WebParam(name = "arg0", partName = "arg0")
        VueloDto arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        byte[] arg3)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns uy.edu.fing.volandouy.webservices.VuelosList
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/listarVueloRequest", output = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/listarVueloResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/listarVuelo/Fault/Exception")
    })
    public VuelosList listarVuelo(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @Action(input = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/reservarVueloRequest", output = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/reservarVueloResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/reservarVuelo/Fault/Exception")
    })
    public void reservarVuelo(
        @WebParam(name = "arg0", partName = "arg0")
        ReservaDto arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @Action(input = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/agregarCiudadRequest", output = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/agregarCiudadResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/agregarCiudad/Fault/Exception")
    })
    public void agregarCiudad(
        @WebParam(name = "arg0", partName = "arg0")
        CiudadDto arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns uy.edu.fing.volandouy.webservices.CiudadesList
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/listarCiudadesRequest", output = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/listarCiudadesResponse")
    public CiudadesList listarCiudades();

    /**
     * 
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @Action(input = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/altaCategoriaRequest", output = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/altaCategoriaResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/altaCategoria/Fault/Exception")
    })
    public void altaCategoria(
        @WebParam(name = "arg0", partName = "arg0")
        CategoriaDto arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns uy.edu.fing.volandouy.webservices.CategoriasList
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/listarCategoriasRequest", output = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/listarCategoriasResponse")
    public CategoriasList listarCategorias();

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/sePuedeFinalizarRutaRequest", output = "http://volandouy.fing.edu.uy/webServices/VueloControllerService/sePuedeFinalizarRutaResponse")
    public boolean sePuedeFinalizarRuta(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

}
