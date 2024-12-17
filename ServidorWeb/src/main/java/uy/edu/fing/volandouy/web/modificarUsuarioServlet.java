package uy.edu.fing.volandouy.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import uy.edu.fing.volandouy.webservices.AerolineaDto;
import uy.edu.fing.volandouy.webservices.ClienteDto;
import uy.edu.fing.volandouy.webservices.ObjectFactory;
import uy.edu.fing.volandouy.webservices.TipoDocumento;
import uy.edu.fing.volandouy.webservices.UsuarioControllerService;
import uy.edu.fing.volandouy.webservices.UsuarioDto;

@SuppressWarnings("serial")
@WebServlet("/modificarUsuarioServlet")
@MultipartConfig // Para manejar archivos (imagen)
public class modificarUsuarioServlet extends HttpServlet {

    public modificarUsuarioServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String tipo = (String)request.getSession().getAttribute("tipo");
		if (tipo != null && ("cliente".equals(tipo) || "aerolinea".equals(tipo))) {
	        String nickname = request.getParameter("nickname");
	        HttpSession session = request.getSession();
	        session.setAttribute("pagina", "ConsultaUsr");
	        
			UsuarioControllerService serviceImplementacion = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
	        
			List<UsuarioDto> listaUsuarios = serviceImplementacion.listarUsuario().getListaUsuarios();
	        request.getSession().setAttribute("usuarios", listaUsuarios);
	        @SuppressWarnings("unchecked")
	        List<UsuarioDto> usuarios = (List<UsuarioDto>) session.getAttribute("usuarios");
	
	        if (usuarios == null || usuarios.isEmpty()) {
	            System.out.println("No hay usuarios en la sesión.");
	            response.getWriter().write("No hay usuarios disponibles en la sesión.");
	            return;
	        }
	
	        UsuarioDto usuarioEncontrado = usuarios.stream()
	                .filter(u -> u.getNickName().equalsIgnoreCase(nickname))
	                .findFirst()
	                .orElse(null);
	
	        if (usuarioEncontrado != null) {
	            request.setAttribute("findUser", usuarioEncontrado);
	            request.getRequestDispatcher("WEB-INF/views/modificarUsuario.jsp").forward(request, response);
	        } else {
	            response.getWriter().write("Usuario no encontrado.");
	        }
		}else {
			request.setAttribute("errorMessage", "¡No se puede acceder de esa forma!");
			request.getRequestDispatcher("loginServlet").forward(request, response);
		}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nickname = request.getParameter("nickname");

        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<UsuarioDto> usuarios = (List<UsuarioDto>) session.getAttribute("usuarios");

        if (usuarios == null || usuarios.isEmpty()) {
            response.getWriter().write("No hay usuarios disponibles en la sesión.");
            return;
        }

        UsuarioDto usuario = usuarios.stream()
                .filter(u -> u.getNickName().equalsIgnoreCase(nickname))
                .findFirst()
                .orElse(null);

        if (usuario != null) {
            UsuarioDto usuarioActualizado = null;

            if (usuario instanceof ClienteDto) {
                usuarioActualizado = crearNuevoCliente((ClienteDto) usuario, request);
            } else if (usuario instanceof AerolineaDto) {
                usuarioActualizado = crearNuevaAerolinea((AerolineaDto) usuario, request);
            }
            
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

            UsuarioControllerService serviceImplementacion = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
            
            if (usuarioActualizado != null) {
            	try {
					//ManagerFactory.getInstance().getUsuarioController().actualizarUsuario(usuarioActualizado);
            		serviceImplementacion.actualizarUsuario(usuarioActualizado, imagenNombre, imagenBytes);
				} catch (Exception e) {
					request.setAttribute("errorMessage", e.getMessage());
				}
            	
            	if (request.getSession().getAttribute("errorMessage") != null) {
            		request.getRequestDispatcher("WEB-INF/views/modificarUsuario.jsp").forward(request, response);
            	}
            	//usuarios = ManagerFactory.getInstance().getUsuarioController().listarUsuario();
            	usuarios = serviceImplementacion.listarUsuario().getListaUsuarios();
                session.setAttribute("usuarios", usuarios);
                response.sendRedirect("detallesUsuarioServlet?nickname=" + nickname);
            }
        } else {
            System.out.println("Error: usuario no encontrado para el nickname: " + nickname);
            response.getWriter().write("Error: usuario no encontrado.");
        }
    }

    private ClienteDto crearNuevoCliente(ClienteDto cliente, HttpServletRequest request)
            throws IOException, ServletException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nacionalidad = request.getParameter("nacionalidad");
        Date fechaNacimiento = obtenerFecha(request.getParameter("fechaNacimiento"));
        TipoDocumento tipoDocumento = obtenerTipoDocumento(request.getParameter("tipoDocumento"));
        String numeroDocumento = request.getParameter("numeroDocumento");

        ObjectFactory fabrica = new ObjectFactory();
        ClienteDto clienteDto = fabrica.createClienteDto();
        
        clienteDto.setApellido(apellido);
        clienteDto.setContrasenia(cliente.getContrasenia());
        clienteDto.setEmail(cliente.getEmail());
        clienteDto.setFechaAlta(cliente.getFechaAlta());
        
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(fechaNacimiento);
        XMLGregorianCalendar xmlGregorianCalendar;
		try {
			xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
			clienteDto.setFechaNacimiento(xmlGregorianCalendar);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        clienteDto.setNacionalidad(nacionalidad);
        clienteDto.setNickName(cliente.getNickName());
        clienteDto.setNombre(nombre);
        clienteDto.setNumeroDocumento(numeroDocumento);
        clienteDto.setTipoDocumento(tipoDocumento);
        clienteDto.getReservas();
        clienteDto.getComprasPaquete();
        clienteDto.getSeguidos();
        
        return clienteDto;
        
        
        /*return new ClienteDto(
                cliente.getNickName(), nombre, cliente.getEmail(), apellido, fechaNacimiento,
                tipoDocumento, numeroDocumento, nacionalidad, cliente.getReservas(),
                cliente.getComprasPaquete(), cliente.getFechaAlta(), cliente.getContrasenia(), imagen, cliente.getSeguidos()
        );*/
    }

    private AerolineaDto crearNuevaAerolinea(AerolineaDto aerolinea, HttpServletRequest request)
            throws IOException, ServletException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String website = request.getParameter("website");
        
        ObjectFactory fabrica = new ObjectFactory();
        AerolineaDto aerolineaDto = fabrica.createAerolineaDto();
        
        aerolineaDto.setContrasenia(aerolinea.getContrasenia());
        aerolineaDto.setDescripcion(descripcion);
        aerolineaDto.setEmail(aerolinea.getEmail());
        aerolineaDto.setFechaAlta(aerolinea.getFechaAlta());
        aerolineaDto.setNickName(aerolinea.getNickName());
        aerolineaDto.setNombre(nombre);
        aerolineaDto.setWebsite(website);
        
        return aerolineaDto;
        
        /*return new AerolineaDto(
                aerolinea.getNickName(), nombre, aerolinea.getEmail(), descripcion, website,
                aerolinea.getRutasDeVuelo(), aerolinea.getFechaAlta(), aerolinea.getContrasenia(), imagen, aerolinea.getSeguidos()
        );*/
    }


    

    private Date obtenerFecha(String fechaStr) {
        try {
            return fechaStr != null && !fechaStr.isEmpty() ? Date.valueOf(fechaStr) : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private TipoDocumento obtenerTipoDocumento(String tipoStr) {
        try {
            return tipoStr != null ? TipoDocumento.valueOf(tipoStr) : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
