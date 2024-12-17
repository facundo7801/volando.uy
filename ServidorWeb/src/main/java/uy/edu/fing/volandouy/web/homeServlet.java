package uy.edu.fing.volandouy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import uy.edu.fing.volandouy.webservices.UsuarioControllerService;
import uy.edu.fing.volandouy.webservices.AerolineaDto;
import uy.edu.fing.volandouy.webservices.RutaDeVueloDto;
import uy.edu.fing.volandouy.webservices.UsuarioDto;
import uy.edu.fing.volandouy.webservices.EstadoRuta;

@SuppressWarnings("serial")
@WebServlet("/homeServlet")
public class homeServlet extends HttpServlet {
	
    public homeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("pagina", "home");
		cargarRutas(request);
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	private void cargarRutas(HttpServletRequest request) {
		List<RutaDeVueloDto> rutas = new ArrayList<>();
		
		UsuarioControllerService serviceImplementacion = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
		
		List<UsuarioDto> listaUsuarios = serviceImplementacion.listarUsuario().getListaUsuarios();
		for (UsuarioDto datauser : listaUsuarios) {
			
			if (datauser instanceof AerolineaDto) {
				try {
					for (RutaDeVueloDto dataruta : serviceImplementacion.listarRutas(datauser.getNickName()).getListaRutas()) {
						if (dataruta.getEstado() == EstadoRuta.CONFIRMADA) {
							rutas.add(dataruta);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		request.getSession().setAttribute("rutasHome", rutas);
		
	}
	
}
