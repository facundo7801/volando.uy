package uy.edu.fing.volandouy.mobile.servlets;


import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uy.edu.fing.volandouy.webservices.ListarReservasService;
import uy.edu.fing.volandouy.webservices.ReservaDto;
import uy.edu.fing.volandouy.webservices.VueloControllerService;
import uy.edu.fing.volandouy.webservices.VueloDto;


@SuppressWarnings("serial")
@WebServlet("/ListarReservasServlet")
public class ListarReservasServlet extends HttpServlet {
	//private IVueloController ivuelo;
	VueloControllerService serviceImplementacion = WebServiceAdapter.getService(VueloControllerService.class, "VueloControllerService"); 
       
    public ListarReservasServlet() {
        super();
        //ivuelo = ManagerFactory.getInstance().getVueloController();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = (String)request.getSession().getAttribute("tipo");
		if ("cliente".equals(tipo)) {
			String clienteConsulta = (String)request.getParameter("clienteConsulta");
			if (clienteConsulta == null) {
				VueloDto vuelo = (VueloDto)request.getSession().getAttribute("datosVuelo");
				List<ReservaDto> reservas = obtenerReservas(vuelo.getNombre(), request);
				request.getSession().setAttribute("reservasList", reservas);
				request.getRequestDispatcher("/WEB-INF/views/listaReservas.jsp").forward(request, response);
			}else {
				String clienteNombre = (String)request.getSession().getAttribute("usuario");
				
				/*Cliente clienteSistema = (Cliente)UserManager.getInstance().findUserByNickname(clienteNombre);
				
				
				
				List<ReservaDto> reservas = new ArrayList<>();
				
				for (Reserva reserva : clienteSistema.getReservas().values()) {
					reservas.add(reserva.toDto());
				}*/
				
				ListarReservasService serviceListReservasImpl = WebServiceAdapter.getService(ListarReservasService.class, "ListarReservasService");
				
				List<ReservaDto> reservas = serviceListReservasImpl.obtenerReservasCliente(clienteNombre).getListaReserva();
				
				request.setAttribute("clienteConsulta", "consultaDirecta");
				request.getSession().setAttribute("reservasList", reservas);
				request.getRequestDispatcher("/WEB-INF/views/listaReservas.jsp").forward(request, response);
				
			}
		}else {
			request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
			request.getRequestDispatcher("loginServlet").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private List<ReservaDto> obtenerReservas(String vuelo, HttpServletRequest request){
		
		/*Vuelo vuelosistema = VueloManager.getInstance().buscarVuelosPorNombre(vuelo);
		List<ReservaDto> reservas = new ArrayList<>();
		String tipoUsuario = (String)request.getSession().getAttribute("tipo");
		if (tipoUsuario.equals("aerolinea")) {
			for (Reserva reserva : vuelosistema.getClientes().values()) {
				reservas.add(reserva.toDto());
			}
		}else if (tipoUsuario.equals("cliente")) {
			String nicknameCliente = (String)request.getSession().getAttribute("usuario");
			
			for (Reserva reserva : vuelosistema.getClientes().values()) {
				if (reserva.getCliente().getNickName().equals(nicknameCliente)) {
					reservas.add(reserva.toDto());
				}
			}
		}
		*/
	
		ListarReservasService serviceListReservasImpl = WebServiceAdapter.getService(ListarReservasService.class, "ListarReservasService");
		String tipoUsuario = (String)request.getSession().getAttribute("tipo");
		String nicknameCliente = (String)request.getSession().getAttribute("usuario");
		List<ReservaDto> reservas = serviceListReservasImpl.obtenerReservasVuelo(vuelo,tipoUsuario,nicknameCliente).getListaReserva();
		
		return reservas;
	}

}
