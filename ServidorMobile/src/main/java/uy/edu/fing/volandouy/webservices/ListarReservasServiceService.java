
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
@WebServiceClient(name = "ListarReservasServiceService", targetNamespace = "http://volandouy.fing.edu.uy/webServices/", wsdlLocation = "http://localhost:9130/ListarReservasService?wsdl")
public class ListarReservasServiceService
    extends Service
{

    private final static URL LISTARRESERVASSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException LISTARRESERVASSERVICESERVICE_EXCEPTION;
    private final static QName LISTARRESERVASSERVICESERVICE_QNAME = new QName("http://volandouy.fing.edu.uy/webServices/", "ListarReservasServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9130/ListarReservasService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LISTARRESERVASSERVICESERVICE_WSDL_LOCATION = url;
        LISTARRESERVASSERVICESERVICE_EXCEPTION = e;
    }

    public ListarReservasServiceService() {
        super(__getWsdlLocation(), LISTARRESERVASSERVICESERVICE_QNAME);
    }

    public ListarReservasServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), LISTARRESERVASSERVICESERVICE_QNAME, features);
    }

    public ListarReservasServiceService(URL wsdlLocation) {
        super(wsdlLocation, LISTARRESERVASSERVICESERVICE_QNAME);
    }

    public ListarReservasServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LISTARRESERVASSERVICESERVICE_QNAME, features);
    }

    public ListarReservasServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ListarReservasServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ListarReservasService
     */
    @WebEndpoint(name = "ListarReservasServicePort")
    public ListarReservasService getListarReservasServicePort() {
        return super.getPort(new QName("http://volandouy.fing.edu.uy/webServices/", "ListarReservasServicePort"), ListarReservasService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ListarReservasService
     */
    @WebEndpoint(name = "ListarReservasServicePort")
    public ListarReservasService getListarReservasServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://volandouy.fing.edu.uy/webServices/", "ListarReservasServicePort"), ListarReservasService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LISTARRESERVASSERVICESERVICE_EXCEPTION!= null) {
            throw LISTARRESERVASSERVICESERVICE_EXCEPTION;
        }
        return LISTARRESERVASSERVICESERVICE_WSDL_LOCATION;
    }

}
