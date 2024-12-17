package uy.edu.fing.volandouy.web;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uy.edu.fing.volandouy.webservices.RutaDeVueloDto;
import uy.edu.fing.volandouy.webservices.VueloControllerService;
import uy.edu.fing.volandouy.webservices.VueloDto;

@SuppressWarnings("serial")
@WebServlet("/listaVuelosServlet")
public class listaVuelosServlet extends HttpServlet {
	private final VueloControllerService serviceIVuelo;

	public listaVuelosServlet() {
		super();
		serviceIVuelo = WebServiceAdapter.getService(VueloControllerService.class, "VueloControllerService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			RutaDeVueloDto ruta = (RutaDeVueloDto) request.getSession().getAttribute("ruta");
			if (ruta != null) {
				String nombreRuta = ruta.getNombre();
				String nombreAerolinea = ruta.getAerolinea().getNickName();

				List<VueloDto> vuelos = serviceIVuelo.listarVuelo(nombreAerolinea, nombreRuta).getListaVuelos();
				request.setAttribute("vuelosRuta", vuelos);
				request.getSession().setAttribute("aerolinea", nombreAerolinea);
			} else {
				request.setAttribute("error", "Ruta no encontrada en la sesión.");
			}
			request.getRequestDispatcher("/WEB-INF/views/listaVuelos.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", "Ocurrió un error al obtener los vuelos: " + e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/listaVuelos.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
