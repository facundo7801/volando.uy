package uy.edu.fing.volandouy.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import uy.edu.fing.volandouy.webservices.CategoriaDto;
import uy.edu.fing.volandouy.webservices.AerolineaDto;
import uy.edu.fing.volandouy.webservices.CiudadDto;
import uy.edu.fing.volandouy.webservices.RutaDeVueloDto;
import uy.edu.fing.volandouy.webservices.EstadoRuta;
import uy.edu.fing.volandouy.webservices.UsuarioControllerService;
import uy.edu.fing.volandouy.webservices.VueloControllerService;
import uy.edu.fing.volandouy.webservices.ObjectFactory;




@WebServlet("/AltaRutaServlet")
@SuppressWarnings("serial")
@MultipartConfig

public class AltaRutaServlet extends HttpServlet {
	public AltaRutaServlet() {
        super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipo = (String)request.getSession().getAttribute("tipo");
		if (tipo != null && "aerolinea".equals(tipo)) {
			request.getSession().setAttribute("pagina", "altaRuta");
			
			VueloControllerService vueloService = WebServiceAdapter.getService(VueloControllerService.class, "VueloControllerService");
	
			List<CategoriaDto> categorias = vueloService.listarCategorias().getListaCategorias();
			List<String> categoriasStr = new ArrayList<>();
	
	        for (CategoriaDto categoria : categorias) {
	            categoriasStr.add(categoria.getNombre()); 
	        }
			
	        List<String> ciudadesStr = new ArrayList<>();
	        
	        List<CiudadDto> ciudades = vueloService.listarCiudades().getListaCiudades();
	        
	        for (CiudadDto iterCiudad : ciudades) {
	        	ciudadesStr.add(iterCiudad.getNombre() + " - " + iterCiudad.getPais());
	        }
	        request.getSession().setAttribute("ciudades", ciudadesStr);
			request.getSession().setAttribute("categorias", categoriasStr);
			request.getRequestDispatcher("/WEB-INF/views/AltaRuta.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
			request.getRequestDispatcher("loginServlet").forward(request, response);
		}
	}

    @SuppressWarnings({ "deprecation", "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcionCorta = request.getParameter("descripcioncorta");
        String descripcion = request.getParameter("descripcion");
        String horastr = request.getParameter("hora");   
        String costoTu = request.getParameter("costoTu");
        String costoEj = request.getParameter("costoEj");
        String costoEq = request.getParameter("costoEq");
        String ciudadO = request.getParameter("ciudadO");
        String ciudadD = request.getParameter("ciudadD");
        
        String[] categorias = request.getParameterValues("categorias[]");

        /*Part imagenPart = request.getPart("imagen");
        String imagenFileName = null;

        if (imagenPart != null && imagenPart.getSize() > 0) {
            imagenFileName = imagenPart.getSubmittedFileName();
            String uploads = getServletContext().getRealPath("") + "media\\images";
            File uploadDir = new File(uploads);
            if (!uploadDir.exists()) uploadDir.mkdir(); 
            File file = new File(uploadDir, imagenFileName);
            imagenPart.write(file.getAbsolutePath());
        }*/
        
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
        
        ObjectFactory fabrica = new ObjectFactory();
        RutaDeVueloDto rutaDto = fabrica.createRutaDeVueloDto();
        
        float costoEjecutivo = 0.0f;

        try {
        	costoEjecutivo = Float.parseFloat(costoEj);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "El valor ingresado no es un número válido");
            request.getRequestDispatcher("/WEB-INF/views/AltaRuta.jsp").forward(request, response);
            return;
        }
        float costoTurista = 0.0f;

        try {
        	costoTurista = Float.parseFloat(costoTu);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "El valor ingresado de costo Turista no es un número válido");
            request.getRequestDispatcher("/WEB-INF/views/AltaRuta.jsp").forward(request, response);
            return;
        }
        float costoEquipajeExtra = 0.0f;

        try {
        	costoEquipajeExtra = Float.parseFloat(costoEq);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "El valor ingresado no es un número válido");
            request.getRequestDispatcher("/WEB-INF/views/AltaRuta.jsp").forward(request, response);
            return;
        }
        
		UsuarioControllerService serviceImpl = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
 
    	String mailAero = (String) request.getSession().getAttribute("email");
    	if (mailAero == null || mailAero.isEmpty()) {
    	    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El correo electrónico no puede estar vacío");
    	    return;
    	}
    	AerolineaDto aerolinea =  (AerolineaDto) serviceImpl.obtenerUsuarioPorCorreo(mailAero);// user manager find user by nickname or email

    	List<CategoriaDto> categoriasDto = new ArrayList<>();
    	//IVueloController vueloController = ManagerFactory.getInstance().getVueloController();
    	if (categorias != null) {
    		for (String categoria : categorias) {
    			
    			CategoriaDto catIter;
    			
    			catIter = fabrica.createCategoriaDto();
    			catIter.setNombre(categoria);
    			
    			
    			categoriasDto.add(catIter);
		
    		}
    	}

    	List<String> vuelos = new ArrayList<>();
    	String[] ciudadyPaisOrigen = ciudadO.split(" - ");
		String ciudadOrigenNombre = ciudadyPaisOrigen[0].trim();
		String paisOrigenNombre = ciudadyPaisOrigen[1].trim();
		
		//factory
		CiudadDto ciudadOrigen = fabrica.createCiudadDto();
		ciudadOrigen.setNombre(ciudadOrigenNombre);
		ciudadOrigen.setPais(paisOrigenNombre);
		ciudadOrigen.setDescripcion(null);
		ciudadOrigen.setNombreAeropuerto(null);
		ciudadOrigen.setFechaAlta(null);
		ciudadOrigen.setWebsite(null);
		
		String[] ciudadyPaisDestino = ciudadD.split(" - ");
		String ciudadDestinoNombre = ciudadyPaisDestino[0].trim();
		String paisDestinoNombre = ciudadyPaisDestino[1].trim();
		//factory
		CiudadDto ciudadDestino = fabrica.createCiudadDto();
		ciudadDestino.setNombre(ciudadDestinoNombre);
		ciudadDestino.setPais(paisDestinoNombre);
		ciudadDestino.setDescripcion(null);
		ciudadDestino.setNombreAeropuerto(null);
		ciudadDestino.setFechaAlta(null);
		ciudadDestino.setWebsite(null);
		
    	
    	EstadoRuta estado = EstadoRuta.INGRESADA;

    	

    	//factory
        rutaDto = fabrica.createRutaDeVueloDto();
        rutaDto.setNombre(nombre);
        rutaDto.setResumen(descripcionCorta);
        rutaDto.setCostoTurista(costoTurista);
        rutaDto.setCostoEjecutivo(costoEjecutivo);
        rutaDto.setCostoEquipajeExtra(costoEquipajeExtra);
        rutaDto.setAerolinea(aerolinea);
        
        Date fechaAlt = new Date();
    	GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(fechaAlt);
        XMLGregorianCalendar xmlGregorianCalendar;
		try {
			xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
			rutaDto.setFechaAlta(xmlGregorianCalendar);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
        
        List<CategoriaDto> listCat = rutaDto.getCategorias();
        for (CategoriaDto categoria : categoriasDto) {
        	listCat.add(categoria);
        } 
        List<String> listVuelo = rutaDto.getVuelos();
        
        rutaDto.setCiudadOrigen(ciudadOrigen);
        rutaDto.setCiudadDestino(ciudadDestino);
        rutaDto.setEstado(estado);
        rutaDto.setDescripcion(descripcion);
        //rutaDto.setImagen(imagenFileName);
        
        String urlVideo = formatoCorrectoUrl(request.getParameter("urlVideo"));
        rutaDto.setVideo(urlVideo);
        
        rutaDto.setVisitas(0);
        		
        try {
        	serviceImpl.agregarRuta(rutaDto, horastr, imagenNombre, imagenBytes);
            List<RutaDeVueloDto> rutasRegistradas = serviceImpl.listarRutas(aerolinea.getNickName()).getListaRutas();
            request.getSession().setAttribute("rutas", rutasRegistradas);
            
            response.sendRedirect("homeServlet");
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/AltaRuta.jsp").forward(request, response);
        }
    }
    
    private boolean validarUrl (String videoId) {
    	return videoId != null && videoId.length() == 11;
    }
    
    private String formatoCorrectoUrl(String url) {
    	String ERROR_URL = "https://www.youtube.com/embed/invalid_video?autoplay=1";
    	String youtubePattern = "(https?://(?:www\\.)?youtube\\.com/watch\\?v=([a-zA-Z0-9_-]+))|(?:https?://(?:www\\.)?youtu\\.be/([a-zA-Z0-9_-]+))|(?:https?://(?:www\\.)?youtube\\.com/embed/([a-zA-Z0-9_-]+))";
        
        Pattern pattern = Pattern.compile(youtubePattern);
        Matcher matcher = pattern.matcher(url);
        
        if (matcher.find()) {
            String videoId = matcher.group(2) != null ? matcher.group(2) : matcher.group(3);
            if (validarUrl(videoId) && !url.equals(ERROR_URL)) {
            	return "https://www.youtube.com/embed/" + videoId;
            }else if (url.matches("^(https://(www\\.)?(youtube\\.com/embed/|player\\.vimeo\\.com/video/|google\\.com/maps/embed\\?)([a-zA-Z0-9_-]+))")) {
            	return url;
            }
        }

    	return null;
    }
    
}

