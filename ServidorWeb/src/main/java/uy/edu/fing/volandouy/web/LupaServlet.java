package uy.edu.fing.volandouy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import uy.edu.fing.volandouy.webservices.PaqueteList;
import uy.edu.fing.volandouy.webservices.RutaDeVueloDto;
import uy.edu.fing.volandouy.webservices.UsuarioControllerService;
import uy.edu.fing.volandouy.webservices.UsuarioDto;
import uy.edu.fing.volandouy.webservices.UsuariosList;
import uy.edu.fing.volandouy.webservices.LupaDto;
import uy.edu.fing.volandouy.webservices.AerolineaDto;
import uy.edu.fing.volandouy.webservices.EstadoRuta;
import uy.edu.fing.volandouy.webservices.PaqueteControllerService;
import uy.edu.fing.volandouy.webservices.PaqueteDto;

@SuppressWarnings("serial")
@WebServlet("/LupaServlet")
public class LupaServlet extends HttpServlet {

	public LupaServlet() {
		super();
	}

	/*
	 * Por defecto ignora las mayusculas con excepcion de que se indique en los
	 * flitros
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String busqueda;
		String llamadoFiltro = request.getParameter("llamadoFiltro");
		boolean ignoraMayus = true;
		if (llamadoFiltro != null) {
			busqueda = (String) request.getSession().getAttribute("busquedaIngresada");
			String opcionMayus = request.getParameter("opcionesMayusculas");
			if (opcionMayus.equals("No")) {
				ignoraMayus = false;
				request.setAttribute("opcionSeleccionadaMayus", "No");
			}
		} else {
			busqueda = request.getParameter("busqueda");
		}

		PaqueteControllerService serviceImplementationPaquete = WebServiceAdapter.getService(PaqueteControllerService.class, "PaqueteControllerService");
				
		UsuarioControllerService serviceImplementationUsuario = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");

		PaqueteList paqueteList = serviceImplementationPaquete.listarPaquetes();
		List<PaqueteDto> listaPaquetes = paqueteList.getListaPaquetes();
		UsuariosList usuariosList = serviceImplementationUsuario.listarUsuario();
		List<UsuarioDto> listaUsuarios = usuariosList.getListaUsuarios();
		List<RutaDeVueloDto> resultadoRutas = new ArrayList<RutaDeVueloDto>();
		List<PaqueteDto> resultadoPaquetes = new ArrayList<PaqueteDto>();

		if (busqueda != null && !busqueda.isBlank()) {
			for (PaqueteDto paquete : listaPaquetes) {
				if (compararStrings(ignoraMayus, paquete.getNombre(), busqueda)
						|| compararStrings(ignoraMayus, paquete.getDescripcion(), busqueda)) {
					resultadoPaquetes.add(paquete);
				}
			}

			for (UsuarioDto usuarioDto : listaUsuarios) {
				if (usuarioDto instanceof AerolineaDto) {
					AerolineaDto aerolinea = (AerolineaDto) usuarioDto;
					for (String nombreRuta : aerolinea.getRutasDeVuelo()) {
						RutaDeVueloDto rutaDeVueloDto = serviceImplementationUsuario
								.obtenerRutaDeVueloPorNombre(nombreRuta);

						if (compararStrings(ignoraMayus, rutaDeVueloDto.getNombre(), busqueda)
								|| compararStrings(ignoraMayus, rutaDeVueloDto.getDescripcion(), busqueda)
								|| compararStrings(ignoraMayus, rutaDeVueloDto.getResumen(), busqueda)) {
							resultadoRutas.add(rutaDeVueloDto);
						}
					}
				}
			}
		} else {
			resultadoPaquetes = listaPaquetes;
			for (UsuarioDto usuarioDto : listaUsuarios) {
				if (usuarioDto instanceof AerolineaDto) {
					AerolineaDto aerolinea = (AerolineaDto) usuarioDto;
					for (String nombreRuta : aerolinea.getRutasDeVuelo()) {
						RutaDeVueloDto rutaDeVueloDto = serviceImplementationUsuario
								.obtenerRutaDeVueloPorNombre(nombreRuta);
						resultadoRutas.add(rutaDeVueloDto);
					}
				}
			}
		}
		
		String opcionSeleccionadaPaquetesRutas = (String)request.getParameter("opcionSeleccionadaPaquetesRutas");
		List<LupaDto> resultadoJuntos = new ArrayList<>();
		agregarResultados(request, resultadoJuntos, resultadoPaquetes, resultadoRutas, opcionSeleccionadaPaquetesRutas);
		
		String opcionDisplay = request.getParameter("opcionesDisplay");
		if (opcionDisplay != null && opcionDisplay.equals("alfabeticamente")) {

			resultadoJuntos.sort(Comparator.comparing(lupa -> lupa.getNombre().toLowerCase()));
			request.setAttribute("opcionSeleccionadaDisplay", "alfabeticamente");
		}
		
		request.getSession().setAttribute("busquedaIngresada", busqueda);
		request.getSession().setAttribute("resultadosLupa", resultadoJuntos);
		request.getRequestDispatcher("/WEB-INF/views/Lupa.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private boolean compararStrings(boolean ignoraMayus, String contenedor, String texto) {
		if (ignoraMayus) {
			return contenedor.toLowerCase().contains(texto.toLowerCase());
		}else {
			return contenedor.contains(texto);
		}
	}
	
	private void agregarResultados(HttpServletRequest request, List<LupaDto> resultadoJuntos, List<PaqueteDto> resultadoPaquetes, List<RutaDeVueloDto> resultadoRutas, String opcionSeleccionadaPaquetesRutas) {
		if (opcionSeleccionadaPaquetesRutas != null && opcionSeleccionadaPaquetesRutas.equals("paquetes")) {
			agregarPaquetes(resultadoJuntos, resultadoPaquetes);
			request.setAttribute("opcionSeleccionadaPaquetesRutas", opcionSeleccionadaPaquetesRutas);
		}else if (opcionSeleccionadaPaquetesRutas != null && opcionSeleccionadaPaquetesRutas.equals("rutas")) {
			agregarRutas(resultadoJuntos, resultadoRutas);
			request.setAttribute("opcionSeleccionadaPaquetesRutas", opcionSeleccionadaPaquetesRutas);
		}else {
			agregarPaquetes(resultadoJuntos, resultadoPaquetes);
			agregarRutas(resultadoJuntos, resultadoRutas);
		}
		Comparator<LupaDto> comparator = Comparator.comparing(lupa -> lupa.getFechaAlta().toGregorianCalendar().getTime());
	    resultadoJuntos.sort(comparator.reversed());
	}
	
	private void agregarPaquetes(List<LupaDto> resultadoJuntos, List<PaqueteDto> resultadoPaquetes) {
		for (PaqueteDto paquete : resultadoPaquetes) {
			LupaDto lupaDto = new LupaDto();
			lupaDto.setNombre(paquete.getNombre());
			lupaDto.setTipo("Paquete");
			lupaDto.setEntidad(paquete);
			lupaDto.setFechaAlta(paquete.getFechaAlta());
			resultadoJuntos.add(lupaDto);
		}
	}
	
	private void agregarRutas(List<LupaDto> resultadoJuntos, List<RutaDeVueloDto> resultadoRutas) {
		for (RutaDeVueloDto ruta : resultadoRutas) {
			if (ruta.getEstado() == EstadoRuta.CONFIRMADA) {
				LupaDto lupaDto = new LupaDto();
				lupaDto.setNombre(ruta.getNombre());
				lupaDto.setTipo("Ruta");
				lupaDto.setEntidad(ruta);
				lupaDto.setFechaAlta(ruta.getFechaAlta());
				resultadoJuntos.add(lupaDto);
			}
		}
	}
	
}
