package uy.edu.fing.volandouy.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uy.edu.fing.volandouy.webservices.ObjectFactory;
import uy.edu.fing.volandouy.webservices.RutaDeVueloDto;
import uy.edu.fing.volandouy.webservices.VueloControllerService;
import uy.edu.fing.volandouy.webservices.VueloDto;

@WebServlet("/altaVueloServlet")
@MultipartConfig
public class altaVueloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final VueloControllerService serviceIVuelo;

    public altaVueloServlet() {
        super();
        serviceIVuelo = WebServiceAdapter.getService(VueloControllerService.class, "VueloControllerService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String tipo = (String)request.getSession().getAttribute("tipo");
		if (tipo != null && "aerolinea".equals(tipo)) {
			request.getRequestDispatcher("/WEB-INF/views/altaVuelo.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
			request.getRequestDispatcher("loginServlet").forward(request, response);
		}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String aerolineaNombre = (String) request.getSession().getAttribute("usuario");
            String nombre = request.getParameter("nombre");
            String rutaSeleccionada = ((RutaDeVueloDto) request.getSession().getAttribute("ruta")).getNombre();
            LocalDate fecha = LocalDate.parse(request.getParameter("fecha"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalTime duracion = LocalTime.parse(request.getParameter("duracion"),
                    DateTimeFormatter.ofPattern("HH:mm"));
            int asientosTurista = Integer.parseInt(request.getParameter("cantMaxAsTurista"));
            int asientosEjecutivo = Integer.parseInt(request.getParameter("cantMaxAsEjecutivo"));
            /*Part imagenPart = request.getPart("imagen");
            String imagenFileName = guardarImagen(imagenPart);*/
            
            Part imagenPart = request.getPart("imagen");
    		String imagenNombre = new String();
    		byte[] imagenBytes = new ByteArrayOutputStream().toByteArray();

    		if (imagenPart != null && imagenPart.getSize() > 0) {
    			
                imagenNombre = imagenPart.getSubmittedFileName();

                InputStream inputStream = imagenPart.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                
                imagenBytes = byteArrayOutputStream.toByteArray();
                
    		}

            VueloDto nuevoVuelo = crearVueloDto(aerolineaNombre, nombre, rutaSeleccionada, fecha, asientosTurista,
                    asientosEjecutivo/*, imagenFileName*/);
            serviceIVuelo.agregarVuelo(nuevoVuelo, duracion.toString(), imagenNombre, imagenBytes);

            response.sendRedirect("homeServlet");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            doGet(request, response);
        }
    }

    /*private String guardarImagen(Part imagenPart) throws IOException {
        if (imagenPart != null && imagenPart.getSize() > 0) {
            String imagenFileName = imagenPart.getSubmittedFileName();
            String uploads = getServletContext().getRealPath("") + "media/images";
            File uploadDir = new File(uploads);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            File file = new File(uploadDir, imagenFileName);
            imagenPart.write(file.getAbsolutePath());
            return imagenFileName;
        }
        return null;
    }*/

    private VueloDto crearVueloDto(String aerolineaNombre, String nombre, String rutaSeleccionada, LocalDate fecha,
            int asientosTurista, int asientosEjecutivo/*, String imagenFileName*/) throws DatatypeConfigurationException {
        ObjectFactory fabrica = new ObjectFactory();
        VueloDto nuevoVuelo = fabrica.createVueloDto();
        XMLGregorianCalendar xmlFecha = convertirFechaAGregorian(fecha);
        XMLGregorianCalendar fechaAlta = convertirFechaAGregorianDate(new Date());

        nuevoVuelo.setFecha(xmlFecha);
        nuevoVuelo.setNombre(nombre);
        nuevoVuelo.setCantMaxAsTurista(asientosTurista);
        nuevoVuelo.setCantMaxAsEjecutivo(asientosEjecutivo);
        nuevoVuelo.setAerolinea(aerolineaNombre);
        nuevoVuelo.setRuta(rutaSeleccionada);
        //nuevoVuelo.setImagen(imagenFileName);
        nuevoVuelo.setFechaAlta(fechaAlta);
        
        return nuevoVuelo;
    }

    private XMLGregorianCalendar convertirFechaAGregorian(LocalDate fecha) throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = GregorianCalendar.from(fecha.atStartOfDay(ZoneId.systemDefault()));
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    }
    
    private XMLGregorianCalendar convertirFechaAGregorianDate(Date fecha) throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(fecha);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    }
}
