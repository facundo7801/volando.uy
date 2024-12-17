package uy.edu.fing.volandouy.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import uy.edu.fing.volandouy.webservices.ListaPaqueteService;
import uy.edu.fing.volandouy.webservices.PaqueteControllerService;
import uy.edu.fing.volandouy.webservices.PaqueteDto;

@SuppressWarnings("serial")
@WebServlet("/listaPaqueteServlet")
public class listaPaqueteServlet extends HttpServlet {
	public listaPaqueteServlet() {
		super();
	}
	
    private PaqueteControllerService serviceImplementationPaquete = WebServiceAdapter.getService(PaqueteControllerService.class, "PaqueteControllerService");
    
	private ListaPaqueteService serviceListaPaqueteImplementation = WebServiceAdapter.getService(ListaPaqueteService.class, "ListaPaqueteService");
		
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String clienteConsulta = request.getParameter("clienteConsulta");
    	if (clienteConsulta == null) {
	        List<PaqueteDto> paquetes = serviceImplementationPaquete.listarPaquetes().getListaPaquetes();
	        request.setAttribute("paquetes", paquetes);
	        request.getSession().setAttribute("pagina", "consultaPaquetes");
	        request.getRequestDispatcher("/WEB-INF/views/listaPaquetes.jsp").forward(request, response);
    	}else {
    		String clienteNombre = (String)request.getSession().getAttribute("usuario");
    		List<PaqueteDto> paquetes = serviceListaPaqueteImplementation.obtenerPaquetesList(clienteNombre).getListaPaquetes();
    		request.setAttribute("clienteConsulta", clienteConsulta);
    		request.setAttribute("paquetes", paquetes);
	        request.getSession().setAttribute("pagina", "consultaPaquetes");
	        request.getRequestDispatcher("/WEB-INF/views/listaPaquetes.jsp").forward(request, response);
    		
    	}
    	
    }
    
}
