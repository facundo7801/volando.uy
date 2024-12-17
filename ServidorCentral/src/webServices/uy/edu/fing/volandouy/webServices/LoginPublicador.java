package uy.edu.fing.volandouy.webServices;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import jakarta.xml.ws.Endpoint;

    public class LoginPublicador {
        // Mantén aquí las propiedades y métodos estáticos anteriores
    	private static final String CONFIG_PATH = System.getProperty("user.dir") + "/../../../../.volandoUy/config.properties";
        private static final Properties properties = new Properties();

        
        static {
            try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Error al cargar el archivo de configuración: " + CONFIG_PATH, e);
            }
        }
		
		    private static String getProperty(String key) {
		        return properties.getProperty(key);
		    }
		
		    private static String replaceBaseURL(String url) {
		        return url.replace("${baseURL}", getProperty("baseURL"));
		    }
        public void iniciarPublicacion() {
            String loginServiceURL = replaceBaseURL(getProperty("LoginService"));
            String usuarioControllerServiceURL = replaceBaseURL(getProperty("UsuarioControllerService"));
            String dataLoaderServiceURL = replaceBaseURL(getProperty("DataLoaderService"));
            String paqueteControllerServiceURL = replaceBaseURL(getProperty("PaqueteControllerService"));
            String vueloControllerServiceURL = replaceBaseURL(getProperty("VueloControllerService"));
            String detallesUsuarioServiceURL = replaceBaseURL(getProperty("DetallesUsuarioService"));
            String listarReservasServiceURL = replaceBaseURL(getProperty("ListarReservasService"));
            String detallesReservaServiceURL = replaceBaseURL(getProperty("DetallesReservaService"));
            String listaPaqueteServiceURL = replaceBaseURL(getProperty("ListaPaqueteService"));
            String ImagenesServiceURL = replaceBaseURL(getProperty("ImagenesService"));

            if (loginServiceURL == null || usuarioControllerServiceURL == null ||
                dataLoaderServiceURL == null || paqueteControllerServiceURL == null || vueloControllerServiceURL == null || listaPaqueteServiceURL == null) {
                System.out.println("Una o más URLs no se cargaron correctamente desde config.properties.");
                System.exit(1);
            }

            Endpoint.publish(loginServiceURL, new LoginService());
            Endpoint.publish(usuarioControllerServiceURL, new UsuarioControllerService());
            Endpoint.publish(dataLoaderServiceURL, new DataLoaderService());
            Endpoint.publish(paqueteControllerServiceURL, new PaqueteControllerService());
            Endpoint.publish(vueloControllerServiceURL, new VueloControllerService());
            Endpoint.publish(detallesUsuarioServiceURL, new DetallesUsuarioService());
            Endpoint.publish(listarReservasServiceURL, new ListarReservasService());
            Endpoint.publish(detallesReservaServiceURL, new detallesReservaService());
            Endpoint.publish(listaPaqueteServiceURL, new ListaPaqueteService());
            Endpoint.publish(ImagenesServiceURL, new ImagenesService());

            System.out.println("Servicios publicados:");
            System.out.println(" - " + loginServiceURL);
            System.out.println(" - " + usuarioControllerServiceURL);
            System.out.println(" - " + dataLoaderServiceURL);
            System.out.println(" - " + paqueteControllerServiceURL);
            System.out.println(" - " + vueloControllerServiceURL);
            System.out.println(" - " + detallesUsuarioServiceURL);
            System.out.println(" - " + listarReservasServiceURL);
            System.out.println(" - " + detallesReservaServiceURL);
            System.out.println(" - " + listaPaqueteServiceURL);
        }
    }

