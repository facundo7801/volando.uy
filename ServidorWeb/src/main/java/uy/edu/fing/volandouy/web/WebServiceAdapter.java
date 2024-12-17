package uy.edu.fing.volandouy.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;

public class WebServiceAdapter {

    private static final String CONFIG_PATH = System.getProperty("user.dir") + "/../../.volandoUy/config.properties";
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo de configuración: " + CONFIG_PATH, e);
        }
    }

    public static <T> T getService(Class<T> serviceClass, String serviceName) {
        try {
            String baseURL = properties.getProperty("baseURL");
            if (baseURL == null || baseURL.isEmpty()) {
                throw new IllegalArgumentException("La propiedad 'baseURL' no está definida en " + CONFIG_PATH);
            }

            String wsdlLocation = baseURL + "/" + serviceName + "?wsdl";
            URL wsdlURL = new URL(wsdlLocation);

            QName qName = new QName("http://volandouy.fing.edu.uy/webServices/", serviceName + "Service");
            Service service = Service.create(wsdlURL, qName);

            QName portName = new QName("http://volandouy.fing.edu.uy/webServices/", serviceName + "Port");
            return service.getPort(portName, serviceClass);

        } catch (Exception e) {
            throw new RuntimeException("Error al crear el servicio web: " + serviceName, e);
        }
    }
}
