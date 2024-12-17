package uy.edu.fing.volandouy.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uy.edu.fing.volandouy.webservices.RutaDeVueloDto;
import uy.edu.fing.volandouy.webservices.UsuarioControllerService;

@SuppressWarnings("serial")
@WebServlet("/finalizarRutaServlet")
public class finalizarRutaServlet extends HttpServlet {
       
    public finalizarRutaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RutaDeVueloDto ruta = (RutaDeVueloDto) request.getSession().getAttribute("ruta");
		UsuarioControllerService serviceImpl = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
		
		serviceImpl.finalizarRuta(ruta.getAerolinea().getNickName(), ruta.getNombre());
		RutaDeVueloDto rutaActualizada = serviceImpl.obtenerRutaDeVueloPorNombre(ruta.getNombre());
		
		/*List<RutaDeVueloDto> rutas = (List<RutaDeVueloDto>) request.getSession().getAttribute("rutas");
		rutas.remove(ruta);
		rutas.add(rutaActualizada);*/
		
		request.setAttribute("findRuta", rutaActualizada);
		request.getSession().setAttribute("ruta", rutaActualizada);
		//request.getSession().setAttribute("rutas", rutas);
		
		request.getRequestDispatcher("WEB-INF/views/consultaRuta.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
