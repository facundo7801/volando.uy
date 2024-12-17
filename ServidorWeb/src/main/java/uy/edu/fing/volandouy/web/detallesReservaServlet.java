package uy.edu.fing.volandouy.web;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/*
import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.fabricas.ManagerFactory;
import uy.edu.fing.volandouy.managers.VueloManager;
import uy.edu.fing.volandouy.modelo.Reserva;
import uy.edu.fing.volandouy.modelo.Vuelo;
import uy.edu.fing.volandouy.dto.ReservaDto;
import uy.edu.fing.volandouy.dto.VueloDto;*/

import uy.edu.fing.volandouy.webservices.*;

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
		if (tipo != null && ("cliente".equals(tipo) || "aerolinea".equals(tipo))) {
			String clienteConsulta = (String)request.getParameter("clienteConsulta");
			if (clienteConsulta == null) {
				ReservaDto reservaConsultada = obtenerDatosReserva(request);
				System.out.println("EmbarqueUrl: " + reservaConsultada.getEmbarqueUrl());
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
				
				System.out.println("EmbarqueUrl: " + reservaConsultada.getEmbarqueUrl());
				
				request.setAttribute("reservaConsultada", reservaConsultada);
				request.setAttribute("pasajerosReserva", reservaConsultada.getPasajeros());
				request.setAttribute("cantidadPasajeros", reservaConsultada.getPasajeros().size());
				request.getRequestDispatcher("/WEB-INF/views/detallesReserva.jsp").forward(request, response);
				
			}
		}else {
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
		
		 
		return reservaConsultada;
	}
	
}
