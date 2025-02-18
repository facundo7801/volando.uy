
package uy.edu.fing.volandouy.webservices;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "DetallesUsuarioServiceService", targetNamespace = "http://volandouy.fing.edu.uy/webServices/", wsdlLocation = "http://localhost:9130/DetallesUsuarioService?wsdl")
public class DetallesUsuarioServiceService
    extends Service
{

    private final static URL DETALLESUSUARIOSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException DETALLESUSUARIOSERVICESERVICE_EXCEPTION;
    private final static QName DETALLESUSUARIOSERVICESERVICE_QNAME = new QName("http://volandouy.fing.edu.uy/webServices/", "DetallesUsuarioServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9130/DetallesUsuarioService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DETALLESUSUARIOSERVICESERVICE_WSDL_LOCATION = url;
        DETALLESUSUARIOSERVICESERVICE_EXCEPTION = e;
    }

    public DetallesUsuarioServiceService() {
        super(__getWsdlLocation(), DETALLESUSUARIOSERVICESERVICE_QNAME);
    }

    public DetallesUsuarioServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), DETALLESUSUARIOSERVICESERVICE_QNAME, features);
    }

    public DetallesUsuarioServiceService(URL wsdlLocation) {
        super(wsdlLocation, DETALLESUSUARIOSERVICESERVICE_QNAME);
    }

    public DetallesUsuarioServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DETALLESUSUARIOSERVICESERVICE_QNAME, features);
    }

    public DetallesUsuarioServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DetallesUsuarioServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DetallesUsuarioService
     */
    @WebEndpoint(name = "DetallesUsuarioServicePort")
    public DetallesUsuarioService getDetallesUsuarioServicePort() {
        return super.getPort(new QName("http://volandouy.fing.edu.uy/webServices/", "DetallesUsuarioServicePort"), DetallesUsuarioService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DetallesUsuarioService
     */
    @WebEndpoint(name = "DetallesUsuarioServicePort")
    public DetallesUsuarioService getDetallesUsuarioServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://volandouy.fing.edu.uy/webServices/", "DetallesUsuarioServicePort"), DetallesUsuarioService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DETALLESUSUARIOSERVICESERVICE_EXCEPTION!= null) {
            throw DETALLESUSUARIOSERVICESERVICE_EXCEPTION;
        }
        return DETALLESUSUARIOSERVICESERVICE_WSDL_LOCATION;
    }

}
