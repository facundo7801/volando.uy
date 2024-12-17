package uy.edu.fing.volandouy.mobile.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uy.edu.fing.volandouy.webservices.AerolineaDto;
import uy.edu.fing.volandouy.webservices.EstadoRuta;
import uy.edu.fing.volandouy.webservices.RutaDeVueloDto;

import uy.edu.fing.volandouy.webservices.UsuarioControllerService;
import uy.edu.fing.volandouy.webservices.UsuarioDto;

@SuppressWarnings("serial")
@WebServlet("/consultaRutaServlet")
public class ConsultaRutaServlet extends HttpServlet {
       
    public ConsultaRutaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = (String)request.getSession().getAttribute("tipo");
		if ("cliente".equals(tipo)) {
			String consultaHome = (String)request.getParameter("consultaHome");
			String consultaPaquete = (String)request.getParameter("consultaPaquete");
			//Caso general de consulta de rutas
			if (consultaHome == null && consultaPaquete == null) {
				String nombre = request.getParameter("nombre");
		        
		        @SuppressWarnings("unchecked")
		        List<RutaDeVueloDto> rutas = (List<RutaDeVueloDto>) request.getSession().getAttribute("rutas");
		
		        if (rutas == null || rutas.isEmpty()) {
		            response.getWriter().write("No hay rutas de vuelo disponibles en la sesión.");
		            return;
		        }
		
		        
		        // Buscar el usuario por nickname
		        RutaDeVueloDto rutaselec = null;
		        for (RutaDeVueloDto iterRuta : rutas) {
		            if (iterRuta.getNombre().equalsIgnoreCase(nombre)) {
		            	rutaselec = iterRuta;
		                break;
		            }
		        }
		
		        if (rutaselec != null) {
		        	actualizarVisitas(rutaselec.getNombre());
		        	if (!validarEnvio(rutaselec.getEstado())) {
		        		request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
		    			request.getRequestDispatcher("loginServlet").forward(request, response);
		        	}else {
			            request.setAttribute("findRuta", rutaselec);
			            request.getSession().setAttribute("ruta", rutaselec);
			            request.getRequestDispatcher("WEB-INF/views/consultaRuta.jsp").forward(request, response);
		        	}
		        } else {
		            response.getWriter().write("Ruta de vuelo no encontrada");
		        }
		        
		        //Caso desde el home
			}else if (consultaHome != null){
				String nombreAerolinea = (String)request.getParameter("aerolineaHome");
				String nombreRuta = (String)request.getParameter("rutaHome");
				RutaDeVueloDto rutaselec = null;
				try {
					UsuarioControllerService serviceUsr = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
					
					List<RutaDeVueloDto> rutas = serviceUsr.listarRutas(nombreAerolinea).getListaRutas();
					for (RutaDeVueloDto ruta : rutas) {
						if (ruta.getNombre().equals(nombreRuta)) {
							rutaselec = ruta;
						}
					}
					
					if (rutaselec != null) {
						actualizarVisitas(rutaselec.getNombre());
						if (!validarEnvio(rutaselec.getEstado())) {
			        		request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
			    			request.getRequestDispatcher("loginServlet").forward(request, response);
			        	}else {
				            request.setAttribute("findRuta", rutaselec);
				            request.getSession().setAttribute("ruta", rutaselec);
				            request.getRequestDispatcher("WEB-INF/views/consultaRuta.jsp").forward(request, response);
			        	}
			        } else {
			            response.getWriter().write("Ruta de vuelo no encontrada");
			        }
					
				} catch (Exception e) {
					//Solo entra aca si el nombre de la aerolinea no pertenece a una aerolinea valida
					e.printStackTrace();
				}
				
				//Caso desde los detalles del paquete
			}else {
				String nombreRutaPaquete = (String)request.getParameter("nombre");
				RutaDeVueloDto rutaselec = null;
	    		UsuarioControllerService serviceImpl2 = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
				
				
				for (UsuarioDto user : serviceImpl2.listarUsuario().getListaUsuarios()){
					
					if (user instanceof AerolineaDto) {
						AerolineaDto aerolineaSistema = (AerolineaDto)user;
						for (String rutaSistema : aerolineaSistema.getRutasDeVuelo()) {
							if (rutaSistema.equals(nombreRutaPaquete)) {
								rutaselec = serviceImpl2.obtenerRutaDeVueloPorNombre(rutaSistema);
							}
							
						}
						
					}
					
				}
				
				
				if (rutaselec != null) {
					actualizarVisitas(rutaselec.getNombre());
					if (!validarEnvio(rutaselec.getEstado())) {
		        		request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
		    			request.getRequestDispatcher("loginServlet").forward(request, response);
		        	}else {
			            request.setAttribute("findRuta", rutaselec);
			            request.getSession().setAttribute("ruta", rutaselec);
			            request.getRequestDispatcher("WEB-INF/views/consultaRuta.jsp").forward(request, response);
		        	}
		        } else {
		            response.getWriter().write("Ruta de vuelo no encontrada");
		        }
				
			}
		}else {
			request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
			request.getRequestDispatcher("loginServlet").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/consultaRuta.jsp").forward(request, response);
	}
	
	private void actualizarVisitas(String nombreRuta) {
		UsuarioControllerService serviceUsr = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
		serviceUsr.actualizarVisitasDeRuta(nombreRuta);
	}
	
	private boolean validarEnvio(EstadoRuta estadoRuta) {
		return estadoRuta == EstadoRuta.CONFIRMADA;
	}
}
