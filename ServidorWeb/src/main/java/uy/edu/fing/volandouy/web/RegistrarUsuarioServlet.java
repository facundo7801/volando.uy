package uy.edu.fing.volandouy.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import uy.edu.fing.volandouy.webservices.AerolineaDto;
import uy.edu.fing.volandouy.webservices.ClienteDto;
import uy.edu.fing.volandouy.webservices.UsuarioControllerService;
import uy.edu.fing.volandouy.webservices.UsuarioDto;
import uy.edu.fing.volandouy.webservices.UsuariosList;
import uy.edu.fing.volandouy.webservices.TipoDocumento;
import uy.edu.fing.volandouy.webservices.ObjectFactory;

@SuppressWarnings("serial")
@WebServlet("/RegistrarUsuarioServlet")
@MultipartConfig
public class RegistrarUsuarioServlet extends HttpServlet {
	
	public RegistrarUsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/RegistrarUsuario.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String tipoUsuario = request.getParameter("tipoUsuario");
		String apellido = request.getParameter("apellido");
		String fechaNacimiento = request.getParameter("fechaNacimiento");
		String nacionalidad = request.getParameter("nacionalidad");
		String tipoDocumento = request.getParameter("tipoDocumento");
		String numeroDocumento = request.getParameter("numeroDocumento");
		String descripcion = request.getParameter("descripcion");
		String sitioWeb = request.getParameter("sitioWeb");
		
		//validarDatos(request, response);
		
		// Manejo de la imagen
		/*Part imagenPart = request.getPart("imagen");
		String imagenFileName = null;

		if (imagenPart != null && imagenPart.getSize() > 0) {
			// Obtiene el nombre del archivo
			imagenFileName = imagenPart.getSubmittedFileName();
			// Define la ruta donde quieres guardar la imagen
			String uploads = getServletContext().getRealPath("") + "media\\images";
			//System.out.println("Ruta donde se guarda la imagen: " + uploads);
			File uploadDir = new File(uploads);
			if (!uploadDir.exists())
				uploadDir.mkdir(); // Crea el directorio si no existe
			File file = new File(uploadDir, imagenFileName);
			// Guarda la imagen en el servidor
			imagenPart.write(file.getAbsolutePath());
			//System.out.println("Ruta donde se guarda la imagen: " + uploads);

		}*/
		
		Part imagenPart = request.getPart("imagen");
		String imagenNombre = new String();
		byte[] imagenBytes = new ByteArrayOutputStream().toByteArray();

		if (imagenPart != null && imagenPart.getSize() > 0) {
			
            imagenNombre = imagenPart.getSubmittedFileName();

            InputStream inputStream = imagenPart.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            
            imagenBytes = byteArrayOutputStream.toByteArray();
            
		}

		ObjectFactory fabrica = new ObjectFactory();
		UsuarioDto usuarioDto;
		
		if ("cliente".equals(tipoUsuario)) {
			Date fechaNac = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				fechaNac = dateFormat.parse(fechaNacimiento);
				//System.out.println("La fecha de nacimiento es: " + fechaNac.toString());
			} catch (ParseException e) {
				request.setAttribute("errorMessage", "La fecha de nacimiento no es válida");
				request.getRequestDispatcher("/WEB-INF/views/RegistrarUsuario.jsp").forward(request, response);
			}

			TipoDocumento tipoDoc = TipoDocumento.valueOf(tipoDocumento);
			String contrasenia = password;
			Date fechaAltaUser = new Date();

			usuarioDto = fabrica.createClienteDto();
			usuarioDto.setNickName(nickname);
			usuarioDto.setNombre(nombre);
			usuarioDto.setEmail(email);
			usuarioDto.setContrasenia(contrasenia);
			//usuarioDto.setImagen(imagenFileName);
			usuarioDto.getSeguidos();
			
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(fechaAltaUser);
	        XMLGregorianCalendar xmlGregorianCalendar;
			try {
				xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
				usuarioDto.setFechaAlta(xmlGregorianCalendar);
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ClienteDto clienteDto = (ClienteDto)usuarioDto;
			clienteDto.setApellido(apellido);
			
			GregorianCalendar gregorianCalendarNacimiento = new GregorianCalendar();
            gregorianCalendarNacimiento.setTime(fechaNac);
	        XMLGregorianCalendar xmlGregorianCalendarNacimiento;
			try {
				xmlGregorianCalendarNacimiento = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendarNacimiento);
				clienteDto.setFechaNacimiento(xmlGregorianCalendarNacimiento);
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			clienteDto.setNacionalidad(nacionalidad);
			clienteDto.setTipoDocumento(tipoDoc);
			clienteDto.setNumeroDocumento(numeroDocumento);
			clienteDto.getReservas();
			clienteDto.getComprasPaquete();
			
		} else { // aerolinea
			String contrasenia = password;
			Date fechaAlta = new Date();
			usuarioDto = fabrica.createAerolineaDto();
			
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(fechaAlta);
	        try {
				XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
				usuarioDto.setFechaAlta(xmlGregorianCalendar);
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			usuarioDto.setNickName(nickname);
			usuarioDto.setNombre(nombre);
			usuarioDto.setEmail(email);
			usuarioDto.setContrasenia(contrasenia);
			//usuarioDto.setImagen(imagenFileName);
			usuarioDto.getSeguidos();
	        
			AerolineaDto aerolineaDto = (AerolineaDto)usuarioDto;
			
			aerolineaDto.setDescripcion(descripcion);
			aerolineaDto.setWebsite(sitioWeb);
			aerolineaDto.getRutasDeVuelo();
		}
		
		UsuarioControllerService serviceImplementacion = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
		
		try {
			serviceImplementacion.agregarUsuario(usuarioDto, imagenNombre, imagenBytes);
			UsuariosList listaUsuariosContainer = serviceImplementacion.listarUsuario();
			List<UsuarioDto> usuariosRegistrados = listaUsuariosContainer.getListaUsuarios();
			
			request.getSession().setAttribute("usuarios", usuariosRegistrados);
			response.sendRedirect("loginServlet");
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/RegistrarUsuario.jsp").forward(request, response);
		}
	}
	
	private void validarDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String nombre = request.getParameter("nombre");
		//String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		//String tipoUsuario = request.getParameter("tipoUsuario");
		//String apellido = request.getParameter("apellido");
		String fechaNacimiento = request.getParameter("fechaNacimiento");
		//String nacionalidad = request.getParameter("nacionalidad");
		//String tipoDocumento = request.getParameter("tipoDocumento");
		//String numeroDocumento = request.getParameter("numeroDocumento");
		//String descripcion = request.getParameter("descripcion");
		
		if (!password.equals(confirmPassword)) {
			request.setAttribute("errorMessage", "Las contraseñas no coinciden");
			request.getRequestDispatcher("/WEB-INF/views/RegistrarUsuario.jsp").forward(request, response);
		}
		
		Date fechaActual = new Date();
		
		if (fechaNacimiento == null || fechaNacimiento.isBlank()) {
			request.setAttribute("errorMessage", "La fecha de nacimiento es requerida");
			request.getRequestDispatcher("/WEB-INF/views/RegistrarUsuario.jsp").forward(request, response);
			return;
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaNac = null;
		try {
			fechaNac = dateFormat.parse(fechaNacimiento);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (fechaNac == null || fechaNac.after(fechaActual)) {
			request.setAttribute("errorMessage", "La fecha de nacimiento ingresada es posterior a la fecha actual");
			request.getRequestDispatcher("/WEB-INF/views/RegistrarUsuario.jsp").forward(request, response);
			return;
		}
		
	}
	
}
