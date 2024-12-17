package uy.edu.fing.volandouy.mobile.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uy.edu.fing.volandouy.webservices.EstadoRuta;
import uy.edu.fing.volandouy.webservices.RutaDeVueloDto;
import uy.edu.fing.volandouy.webservices.UsuarioControllerService;
import uy.edu.fing.volandouy.webservices.VueloControllerService;
import uy.edu.fing.volandouy.webservices.VueloDto;

@SuppressWarnings("serial")
@WebServlet("/detallesVueloServlet")
public class detallesVueloServlet extends HttpServlet {
	private final VueloControllerService serviceIVuelo;
	private final UsuarioControllerService serviceIUsuario;

	public detallesVueloServlet() {
		super();
		serviceIVuelo = WebServiceAdapter.getService(VueloControllerService.class, "VueloControllerService");
		serviceIUsuario = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tipo = (String)request.getSession().getAttribute("tipo");
		if ("cliente".equals(tipo)) {
			try {
				String nombreVuelo = request.getParameter("vuelo");
				if (nombreVuelo != null && !nombreVuelo.isEmpty()) {
					obtenerDatosVuelo(request, nombreVuelo);
					if (!validarVuelo((VueloDto)request.getSession().getAttribute("datosVuelo"))) {
						request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
						request.getRequestDispatcher("loginServlet").forward(request, response);
					}else {
						request.getRequestDispatcher("/WEB-INF/views/detallesVuelo.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("error", "Nombre de vuelo no proporcionado.");
					request.getRequestDispatcher("/WEB-INF/views/detallesVuelo.jsp").forward(request, response);
				}
			} catch (Exception e) {
				request.setAttribute("error", "Ocurrió un error al obtener los detalles del vuelo: " + e.getMessage());
				request.getRequestDispatcher("/WEB-INF/views/detallesVuelo.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
			request.getRequestDispatcher("loginServlet").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void obtenerDatosVuelo(HttpServletRequest request, String nombreVuelo) {
		VueloDto datosVuelo = serviceIVuelo.buscarVueloPornombre(nombreVuelo);
		if (datosVuelo != null) {
			request.getSession().setAttribute("datosVuelo", datosVuelo);
			String tipoUsuario = (String) request.getSession().getAttribute("tipo");
			String usuario = (String) request.getSession().getAttribute("usuario");

			if ("cliente".equals(tipoUsuario)) {
				boolean reservado = datosVuelo.getClientes().contains(usuario);
				request.getSession().setAttribute("vueloReservado", reservado ? "si" : "no");
			}
		} else {
			request.setAttribute("error", "No se encontraron detalles para el vuelo especificado.");
		}
	}
	
	private boolean validarVuelo(VueloDto datosVuelo){
		RutaDeVueloDto datosRuta = serviceIUsuario.obtenerRutaDeVueloPorNombre(datosVuelo.getRuta());
		return datosRuta.getEstado() == EstadoRuta.CONFIRMADA;
	}
}