package uy.edu.fing.volandouy.web;

import uy.edu.fing.volandouy.webservices.PaqueteDto;
import uy.edu.fing.volandouy.webservices.PaqueteList;
import uy.edu.fing.volandouy.webservices.ClienteDto;
import uy.edu.fing.volandouy.webservices.CompraPaqueteDto;
import uy.edu.fing.volandouy.webservices.ObjectFactory;
import uy.edu.fing.volandouy.webservices.PaqueteControllerService;
import uy.edu.fing.volandouy.webservices.UsuarioControllerService;
import uy.edu.fing.volandouy.webservices.UsuarioDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.util.ArrayList;

@SuppressWarnings("serial")
@WebServlet("/CompraPaqueteServlet")
public class CompraPaqueteServlet extends HttpServlet {

    public CompraPaqueteServlet() {
    	super();
    }
    
    private ObjectFactory fabrica = new ObjectFactory();
    private PaqueteControllerService serviceImplementationPaquete = WebServiceAdapter.getService(PaqueteControllerService.class, "PaqueteControllerService");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String tipo = (String)request.getSession().getAttribute("tipo");
		if (tipo != null && "cliente".equals(tipo)) {
	    	// Obtiene todos los paquetes disponibles y se los pasa al JSP
	    	request.getSession().setAttribute("pagina", "comprarPaquete");
	    	String nombreCliente = (String)request.getSession().getAttribute("usuario");
	    	
			UsuarioControllerService serviceImplementation = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
	    	
			
			ClienteDto clienteDto = buscarUsuario(serviceImplementation.listarUsuario().getListaUsuarios(), nombreCliente);    	 
	    	
	    	PaqueteList paqueteList = serviceImplementationPaquete.listarPaquetes();
	    	List<PaqueteDto> paquetes = paqueteList.getListaPaquetes();
	        List<PaqueteDto> paquetesDisponibles = new ArrayList<PaqueteDto>();
	
	        for (PaqueteDto paquete : paquetes) {
	            if (paquete.getRutas().size() > 0 && !perteneceLista(paquete.getNombre(), clienteDto.getComprasPaquete())) {
	                paquetesDisponibles.add(paquete);
	            }
	        }
	        
	        if(paquetes.size() > 0 && paquetesDisponibles.size() == 0) {
	        	request.setAttribute("compradosTodos", "si");
	        }
	        
	        request.setAttribute("paquetes", paquetesDisponibles);
	        request.getRequestDispatcher("/WEB-INF/views/compraPaquete.jsp").forward(request, response);
		}else {
			request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
			request.getRequestDispatcher("loginServlet").forward(request, response);
		}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	String nombrePaquete = request.getParameter("paqueteSeleccionado");
            Date fechaCompra = new Date();
            
            String comprador = (String) request.getSession().getAttribute("usuario");
            
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(fechaCompra);
            
            CompraPaqueteDto compraDto = fabrica.createCompraPaqueteDto();
            compraDto.setFechaCompra(null);
            
            try {
				XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
				compraDto.setFechaCompra(xmlGregorianCalendar);
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            compraDto.setVencimiento(null);
            compraDto.setComprador(comprador);
            compraDto.setPaquete(nombrePaquete);
            compraDto.getReservas();
           

            serviceImplementationPaquete.comprarPaquete(compraDto);
            response.sendRedirect("homeServlet");
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            doGet(request, response);
            //request.getRequestDispatcher("/WEB-INF/views/compraPaquete.jsp").forward(request, response);
        }
    }
    
    private boolean perteneceLista(String nombrePaquete, List<String>paquetes) {
    	for (String paquete : paquetes) {
    		if (paquete.equals(nombrePaquete)) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    private ClienteDto buscarUsuario(List<UsuarioDto> usuarios, String nickname) {
    	ClienteDto usuarioLogeado = null;
    	for (UsuarioDto usuario : usuarios) {
    		if (usuario.getNickName().equals(nickname)) {
    			usuarioLogeado = (ClienteDto) usuario;
    		}
    	}
    	
    	return usuarioLogeado;
    }
    
}
