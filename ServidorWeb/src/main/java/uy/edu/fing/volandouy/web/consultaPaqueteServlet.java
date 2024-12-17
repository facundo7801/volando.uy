package uy.edu.fing.volandouy.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uy.edu.fing.volandouy.webservices.PaqueteDto;
import uy.edu.fing.volandouy.webservices.PaqueteControllerService;

@SuppressWarnings("serial")
@WebServlet("/consultaPaqueteServlet")
@MultipartConfig
public class consultaPaqueteServlet extends HttpServlet {
	public consultaPaqueteServlet() {
		super();
		
	}
	
    private PaqueteControllerService serviceImplementationPaquete = WebServiceAdapter.getService(PaqueteControllerService.class, "PaqueteControllerService");
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre = (String) request.getParameter("nombre");

		
		PaqueteDto paquete = serviceImplementationPaquete.buscarPaquetePorNombre(nombre);

		if (paquete == null) {
			response.getWriter().write("No hay paquete disponible en la sesión.");
			return;
		}

		String clienteConsulta = (String)request.getParameter("clienteConsulta");
		
		if (clienteConsulta != null) {
			request.setAttribute("clienteConsulta", clienteConsulta);
		}
		
		request.setAttribute("paquete", paquete);		
		request.getRequestDispatcher("WEB-INF/views/consultaPaquete.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
