
package uy.edu.fing.volandouy.webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
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
@WebService(name = "ImagenesService", targetNamespace = "http://volandouy.fing.edu.uy/webServices/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ImagenesService {


    /**
     * 
     * @param arg0
     * @return
     *     returns byte[]
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://volandouy.fing.edu.uy/webServices/ImagenesService/getFileRequest", output = "http://volandouy.fing.edu.uy/webServices/ImagenesService/getFileResponse")
    public byte[] getFile(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

}
