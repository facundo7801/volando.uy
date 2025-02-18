
package uy.edu.fing.volandouy.webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
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
@WebService(name = "DataLoaderService", targetNamespace = "http://volandouy.fing.edu.uy/webServices/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DataLoaderService {


    /**
     * 
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @Action(input = "http://volandouy.fing.edu.uy/webServices/DataLoaderService/cargarDatosRequest", output = "http://volandouy.fing.edu.uy/webServices/DataLoaderService/cargarDatosResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://volandouy.fing.edu.uy/webServices/DataLoaderService/cargarDatos/Fault/Exception")
    })
    public void cargarDatos(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws Exception_Exception
    ;

}
