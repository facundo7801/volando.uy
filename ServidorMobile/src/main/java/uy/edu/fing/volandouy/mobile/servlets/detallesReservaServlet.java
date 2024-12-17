package uy.edu.fing.volandouy.mobile.servlets;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uy.edu.fing.volandouy.webservices.DetallesReservaService;
import uy.edu.fing.volandouy.webservices.ReservaDto;
import uy.edu.fing.volandouy.webservices.VueloDto;

/**
 * Servlet implementation class detallesVueloServlet
 */
@SuppressWarnings("serial")
@WebServlet("/detallesReservaServlet")
public class detallesReservaServlet extends HttpServlet {
	
    public detallesReservaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = (String)request.getSession().getAttribute("tipo");
		if ("cliente".equals(tipo)) {
			String clienteConsulta = (String)request.getParameter("clienteConsulta");
			if (clienteConsulta == null) {
				ReservaDto reservaConsultada = obtenerDatosReserva(request);
				request.setAttribute("reservaConsultada", reservaConsultada);
				request.setAttribute("pasajerosReserva", reservaConsultada.getPasajeros());
				request.setAttribute("cantidadPasajeros", reservaConsultada.getPasajeros().size());			
				request.getRequestDispatcher("/WEB-INF/views/detallesReserva.jsp").forward(request, response);
			}else {
				String nombreCliente = (String)request.getSession().getAttribute("usuario");
				String nombreVuelo = (String)request.getParameter("vueloReservaCliente");
				
				//Vuelo vueloConsultado = VueloManager.getInstance().buscarVuelosPorNombre(nombreVuelo);
				
				
				/*VueloDto vueloConsultado = serviceVueloImpl.buscarVueloPornombre(nombreVuelo);
				
				ReservaDto reservaConsultada = null;
				
				
				for(Reserva reservas : vueloConsultado.getClientes().values()) {
					if (reservas.getCliente().getNickName().equals(nombreCliente)) {
						reservaConsultada = reservas.toDto();
					}
				}*/
				DetallesReservaService serviceDetallesImpl = WebServiceAdapter.getService(DetallesReservaService.class, "detallesReservaService");
				ReservaDto reservaConsultada = serviceDetallesImpl.reservaCliente(nombreCliente, nombreVuelo);
				
				
				
				request.setAttribute("reservaConsultada", reservaConsultada);
				request.setAttribute("pasajerosReserva", reservaConsultada.getPasajeros());
				request.setAttribute("cantidadPasajeros", reservaConsultada.getPasajeros().size());
				request.getRequestDispatcher("/WEB-INF/views/detallesReserva.jsp").forward(request, response);
				
			}
		} else {
			request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
			request.getRequestDispatcher("loginServlet").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private ReservaDto obtenerDatosReserva(HttpServletRequest request) {
		String nombreCliente = (String)request.getParameter("clienteReserva");
		VueloDto datosVuelo = (VueloDto)request.getSession().getAttribute("datosVuelo");
		String nombreVuelo = datosVuelo.getNombre();
		
		/*Vuelo vueloConsultado = VueloManager.getInstance().buscarVuelosPorNombre(nombreVuelo);
		ReservaDto reservaConsultada = null;
		
		for(Reserva reservas : vueloConsultado.getClientes().values()) {
			if (reservas.getCliente().getNickName().equals(nombreCliente)) {
				reservaConsultada = reservas.toDto();
			}
		}*/
		
		DetallesReservaService serviceDetallesImpl = WebServiceAdapter.getService(DetallesReservaService.class, "detallesReservaService");
		ReservaDto reservaConsultada = serviceDetallesImpl.reservaCliente(nombreCliente, nombreVuelo);
		
		/* CheckIn */
		String realizarCheckInParametro = request.getParameter("realizarCheckIn");
		if (realizarCheckInParametro != null) {
			serviceDetallesImpl.realizarCheckIn(nombreCliente, nombreVuelo);
		}
		/* CheckIn */
		
		return reservaConsultada;
	}
	
}
