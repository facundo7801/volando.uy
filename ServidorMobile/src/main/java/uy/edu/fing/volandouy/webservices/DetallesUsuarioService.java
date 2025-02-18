
package uy.edu.fing.volandouy.webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "DetallesUsuarioService", targetNamespace = "http://volandouy.fing.edu.uy/webServices/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DetallesUsuarioService {


    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://volandouy.fing.edu.uy/webServices/DetallesUsuarioService/dejarSeguirUsuarioRequest", output = "http://volandouy.fing.edu.uy/webServices/DetallesUsuarioService/dejarSeguirUsuarioResponse")
    public void dejarSeguirUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://volandouy.fing.edu.uy/webServices/DetallesUsuarioService/seguirUsuarioRequest", output = "http://volandouy.fing.edu.uy/webServices/DetallesUsuarioService/seguirUsuarioResponse")
    public void seguirUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

}
