package uy.edu.fing.volandouy.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uy.edu.fing.volandouy.webservices.*;


/**
 * Servlet implementation class detallesCheckInServlet
 */
@WebServlet("/detallesCheckInServlet")
public class detallesCheckInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detallesCheckInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = (String)request.getSession().getAttribute("tipo");
		if (tipo != null && "cliente".equals(tipo)) {
			String nombreCliente = request.getParameter("nickNameCliente");
			String nombreVuelo = request.getParameter("nombreVueloReserva");
			
			VueloControllerService serviceVueloImpl = WebServiceAdapter.getService(VueloControllerService.class, "VueloControllerService");
			VueloDto vueloReservado = serviceVueloImpl.buscarVueloPornombre(nombreVuelo);
			
			DetallesReservaService serviceReservaImpl = WebServiceAdapter.getService(DetallesReservaService.class, "detallesReservaService");
			ReservaDto reservaCliente = serviceReservaImpl.reservaCliente(nombreCliente, nombreVuelo);
			
			String nombreRuta = vueloReservado.getRuta();
			
			UsuarioControllerService serviceUserImpl = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
			RutaDeVueloDto ruta = serviceUserImpl.obtenerRutaDeVueloPorNombre(nombreRuta);
			
			request.setAttribute("rutaCheckIn", ruta);
			request.setAttribute("reservaCheckIn", reservaCliente);
			
			request.getRequestDispatcher("WEB-INF/views/detallesCheckIn.jsp").forward(request, response);
		}else {
			request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
			request.getRequestDispatcher("loginServlet").forward(request, response);
		}
				
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
