package uy.edu.fing.volandouy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Exception;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import uy.edu.fing.volandouy.webservices.*;


/*
import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.dto.PasajeDto;
import uy.edu.fing.volandouy.dto.ReservaDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;
import uy.edu.fing.volandouy.dto.VueloDto;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;
import uy.edu.fing.volandouy.fabricas.ManagerFactory;*/


@SuppressWarnings("serial")
@WebServlet("/reservarServlet")
public class reservarServlet extends HttpServlet {
	//IVueloController ivuelo;
	//IUsuarioController iusuario;

	UsuarioControllerService serviceImplementacion = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
       
    public reservarServlet() {
        super();
        //ivuelo = ManagerFactory.getInstance().getVueloController();
        //iusuario = ManagerFactory.getInstance().getUsuarioController();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = (String)request.getSession().getAttribute("tipo");
		if (tipo != null && ("cliente".equals(tipo))) {
			VueloDto dataVuelo = (VueloDto)request.getSession().getAttribute("datosVuelo");
			String nickname = (String) request.getSession().getAttribute("usuario");
			ClienteDto usuariodto = null;
			for(UsuarioDto usuarioDto : serviceImplementacion.listarUsuario().getListaUsuarios()) {
				if (usuarioDto.getNickName().equals(nickname)) {
					usuariodto = (ClienteDto)usuarioDto;
				}
			}
			
	
			request.setAttribute("paquetesReserva", usuariodto.getComprasPaquete());
			request.setAttribute("nombreCliente", usuariodto.getNombre());
			request.setAttribute("apellidoCliente", usuariodto.getApellido());
			
			
			if (!dataVuelo.getClientes().contains(nickname)) {
				request.getRequestDispatcher("/WEB-INF/views/reservar.jsp").forward(request, response);
			}else {
				response.sendRedirect("detallesReservaServlet");
			}
		}else {
			request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
			request.getRequestDispatcher("loginServlet").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		realizarReserva(request, response);
		request.setAttribute("datosVuelo", request.getParameter("datosVuelo"));
		
		if (request.getAttribute("errorMessage") == null) {
			request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
		}else {
			doGet(request, response);
		}
	}

	private void realizarReserva(HttpServletRequest request, HttpServletResponse response) {
		//ReservaDto res; //= new ReservaDto(fechaReserva, tipoAsiento, uniEquipajeExtra, costo, pasajeros, cliente, aerolinea, ruta, vuelo, paquete)
		Date fechaReserva = new Date();
		TipoAsiento tipoAsiento = TipoAsiento.valueOf(request.getParameter("tipoAsientoReserva"));
		int uniEquipajeExtra = Integer.parseInt(request.getParameter("equipajeExtraReserva"));
		float costo = 0;
		List<PasajeDto> pasajeros = obtenerPasajeros(request);
		String cliente = (String)request.getSession().getAttribute("usuario");
		String aerolinea = (String)request.getParameter("aerolineaReserva");
		String ruta = (String)request.getParameter("rutaReserva");
		String vuelo = (String)request.getParameter("vueloReserva");
		String paquete = null;
		String metodoPago = (String) request.getParameter("metodoPagoReserva");
		if (!metodoPago.equals("general")) {
			paquete = metodoPago;
		}
		
		//res = new ReservaDto(fechaReserva, tipoAsiento, uniEquipajeExtra, costo, pasajeros, cliente, aerolinea, ruta, vuelo, paquete, null);
		ObjectFactory fabrica = new ObjectFactory();
		ReservaDto res = fabrica.createReservaDto();
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(fechaReserva);
        XMLGregorianCalendar xmlGregorianCalendar;
		try {
			xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
			res.setFechaReserva(xmlGregorianCalendar);
			//usuarioDto.setFechaAlta(xmlGregorianCalendar);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		res.setTipoAsiento(tipoAsiento);
		res.setUniEquipajeExtra(uniEquipajeExtra);
		res.setCosto(costo);
		res.setCliente(cliente);
		res.setAerolinea(aerolinea);
		res.setRuta(ruta);
		res.setVuelo(vuelo);
		res.setPaquete(paquete);
		
		
		List<PasajeDto> pasajerosAdd = res.getPasajeros();
		
		
		for(PasajeDto pasajero:pasajeros) {
			pasajerosAdd.add(pasajero);
		}
		
		VueloControllerService vueloserviceImplementacion = WebServiceAdapter.getService(VueloControllerService.class, "VueloControllerService");
		
		
		if (request.getAttribute("errorMessage") == null) {
		
			try {
				//ivuelo.reservarVuelo(res);
				vueloserviceImplementacion.reservarVuelo(res);
				request.removeAttribute("errorMessage");
			} catch (Exception e) {
				request.setAttribute("errorMessage", e.getMessage());
			}
		}
		
	}
	
	private List<PasajeDto> obtenerPasajeros(HttpServletRequest request) {	
		String pasajerosFijosJson = request.getParameter("pasajerosFijosJson");
	    List<PasajeDto> pasajerosFijos = new ArrayList<>();

	    if (pasajerosFijosJson != null && !pasajerosFijosJson.isEmpty()) {
	        // Expresión regular para extraer índice, nombre y apellido
	        String regex = "\"(\\d+)\":\\{\"nombre\":\"([^\"]+)\",\"apellido\":\"([^\"]+)\"\\}";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(pasajerosFijosJson);

	        while (matcher.find()) {
	            String nombre = matcher.group(2);
	            String apellido = matcher.group(3);
	            //PasajeDto pasajeroNuevo = new PasajeDto(nombre, apellido, "");
	            PasajeDto pasajeroNuevo = new PasajeDto();
	            pasajeroNuevo.setNombre(nombre);
	            pasajeroNuevo.setApellido(apellido);
	            pasajeroNuevo.setNumeroAsiento("vacio");
	            int index = Integer.parseInt(matcher.group(1)) - 1;
	            if (index >= pasajerosFijos.size()) {
	                pasajerosFijos.add(pasajeroNuevo);
	            } else {
	                pasajerosFijos.add(index, pasajeroNuevo);
	            }
	        }
	    }

	    Integer cantidadPasajeros = Integer.parseInt(request.getParameter("cantidadPasajesReserva"));
	    if (pasajerosFijos.size() != cantidadPasajeros) {
	        request.setAttribute("errorMessage", "Falta ingresar los datos de algún pasajero");
	    }

	    return pasajerosFijos;
		
		
		
		
	}

	
}
