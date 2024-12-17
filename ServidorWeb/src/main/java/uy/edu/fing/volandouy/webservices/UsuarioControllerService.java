
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
@WebService(name = "UsuarioControllerService", targetNamespace = "http://volandouy.fing.edu.uy/webServices/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UsuarioControllerService {


    /**
     * 
     * @param arg0
     * @return
     *     returns uy.edu.fing.volandouy.webservices.UsuarioDto
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/obtenerUsuarioPorNickNameRequest", output = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/obtenerUsuarioPorNickNameResponse")
    public UsuarioDto obtenerUsuarioPorNickName(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns uy.edu.fing.volandouy.webservices.RutaDeVueloDto
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/obtenerRutaDeVueloPorNombreRequest", output = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/obtenerRutaDeVueloPorNombreResponse")
    public RutaDeVueloDto obtenerRutaDeVueloPorNombre(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns uy.edu.fing.volandouy.webservices.UsuarioDto
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/obtenerUsuarioPorCorreoRequest", output = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/obtenerUsuarioPorCorreoResponse")
    public UsuarioDto obtenerUsuarioPorCorreo(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns uy.edu.fing.volandouy.webservices.RutasList
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/listarRutasRequest", output = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/listarRutasResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/listarRutas/Fault/Exception")
    })
    public RutasList listarRutas(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns uy.edu.fing.volandouy.webservices.UsuariosList
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/listarUsuarioRequest", output = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/listarUsuarioResponse")
    public UsuariosList listarUsuario();

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws Exception_Exception
     */
    @WebMethod
    @Action(input = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/agregarUsuarioRequest", output = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/agregarUsuarioResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/agregarUsuario/Fault/Exception")
    })
    public void agregarUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        UsuarioDto arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        byte[] arg2)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws Exception_Exception
     */
    @WebMethod
    @Action(input = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/actualizarUsuarioRequest", output = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/actualizarUsuarioResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/actualizarUsuario/Fault/Exception")
    })
    public void actualizarUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        UsuarioDto arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        byte[] arg2)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @throws Exception_Exception
     */
    @WebMethod
    @Action(input = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/agregarRutaRequest", output = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/agregarRutaResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/agregarRuta/Fault/Exception")
    })
    public void agregarRuta(
        @WebParam(name = "arg0", partName = "arg0")
        RutaDeVueloDto arg0,
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
     * @param arg2
     * @throws Exception_Exception
     */
    @WebMethod
    @Action(input = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/cambiarEstadoRutaRequest", output = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/cambiarEstadoRutaResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/cambiarEstadoRuta/Fault/Exception")
    })
    public void cambiarEstadoRuta(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        EstadoRuta arg2)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/finalizarRutaRequest", output = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/finalizarRutaResponse")
    public void finalizarRuta(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/actualizarVisitasDeRutaRequest", output = "http://volandouy.fing.edu.uy/webServices/UsuarioControllerService/actualizarVisitasDeRutaResponse")
    public void actualizarVisitasDeRuta(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

}
