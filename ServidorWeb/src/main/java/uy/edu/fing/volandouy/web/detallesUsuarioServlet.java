package uy.edu.fing.volandouy.web;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import uy.edu.fing.volandouy.webservices.UsuarioDto;
import uy.edu.fing.volandouy.webservices.ClienteDto;
import uy.edu.fing.volandouy.webservices.ObjectFactory;
import uy.edu.fing.volandouy.webservices.AerolineaDto;
import uy.edu.fing.volandouy.webservices.TipoDocumento;
import uy.edu.fing.volandouy.webservices.UsuarioControllerService;
import uy.edu.fing.volandouy.webservices.DetallesUsuarioService;


@SuppressWarnings("serial")
@WebServlet("/detallesUsuarioServlet")
@MultipartConfig // Para manejar archivos (imagen)
public class detallesUsuarioServlet extends HttpServlet {

    public detallesUsuarioServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        HttpSession session = request.getSession();

        /*@SuppressWarnings("unchecked")
        List<UsuarioDto> usuarios = (List<UsuarioDto>) session.getAttribute("usuarios");*/
        
    	UsuarioControllerService serviceImpLista = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
        
        List<UsuarioDto> usuarios = serviceImpLista.listarUsuario().getListaUsuarios();
        request.getSession().setAttribute("usuarios", usuarios);

        if (usuarios == null || usuarios.isEmpty()) {
            response.getWriter().write("No hay usuarios disponibles en la sesión.");
            return;
        }

        UsuarioDto usuarioEncontrado = usuarios.stream()
                .filter(u -> u.getNickName().equalsIgnoreCase(nickname))
                .findFirst()
                .orElse(null);
        
        if (usuarioEncontrado != null) {
            request.setAttribute("findUser", usuarioEncontrado);
            
            /* Nuevo */
            String tipo = (String)request.getSession().getAttribute("tipo");
            //Si no es visitante
            if (!tipo.equals("visitante")) {
            	
	            String nicknameLogeado = (String)request.getSession().getAttribute("usuario");
	            //Si el usuario que consulta no es el mismo
	            if (!nickname.equals(nicknameLogeado)) {
	            	
	            	//Si no lo sigue verifico si lo queria seguir y al reves
	            	String seguir = (String) request.getParameter("seguir");
	            	String dejarSeguir = (String) request.getParameter("dejarSeguir");
	            	
	            	DetallesUsuarioService detallesService = WebServiceAdapter.getService(DetallesUsuarioService.class, "DetallesUsuarioService");
	            	
		            if (seguir != null) {
		            	detallesService.seguirUsuario(nicknameLogeado, nickname);
		            }else if (dejarSeguir != null) {
		            	detallesService.dejarSeguirUsuario(nicknameLogeado, nickname);
		            }
	            	
	            	//Actualizo la lista de usuarios
	            	UsuarioControllerService usuarioController = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
	            	List<UsuarioDto> listaUsuariosActualizada = usuarioController.listarUsuario().getListaUsuarios();
	            	request.getSession().setAttribute("usuarios", listaUsuariosActualizada);
	            	
	            	//Busco el usuario logeado
	            	UsuarioDto usuarioLogeado = null;
	            	for (UsuarioDto user : listaUsuariosActualizada) {
	            		if (user.getNickName().equals(nicknameLogeado)) {
	            			usuarioLogeado = user;
	            		}
	            	}
	            	//usuarioLogeado no es null nunca
	            	
	            	//Seteo atributos para mostrar los botones segun si lo sigue o no
	            	boolean loSigue = false;
	            	for(String nombreSeguidos : usuarioLogeado.getSeguidos()) {
	            		if (nombreSeguidos.equals(nickname)) {
	            			loSigue = true;
	            		}
	            	}
	            	
	            	if (!loSigue) {
	            		request.setAttribute("seguirEnabled", "si");
	            	}else {
	            		request.setAttribute("dejarSeguirEnabled", "si");
	            	}
	            	
	            }
	            
            }
            /* Nuevo */
            
            request.getRequestDispatcher("WEB-INF/views/detallesUsuario.jsp").forward(request, response);
        } else {
            response.getWriter().write("Usuario no encontrado.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<UsuarioDto> usuarios = (List<UsuarioDto>) session.getAttribute("usuarios");

        UsuarioDto usuario = usuarios.stream()
                .filter(u -> u.getNickName().equalsIgnoreCase(nickname))
                .findFirst()
                .orElse(null);

        if (usuario != null) {
            UsuarioDto usuarioActualizado = null;

            // Verificar el tipo de usuario y crear una nueva instancia actualizada
            if (usuario instanceof ClienteDto) {
                usuarioActualizado = crearNuevoCliente((ClienteDto) usuario, request);
            } else if (usuario instanceof AerolineaDto) {
                usuarioActualizado = crearNuevaAerolinea((AerolineaDto) usuario, request);
            }

            if (usuarioActualizado != null) {
                // Reemplazar el usuario en la lista
                usuarios.remove(usuario);
                usuarios.add(usuarioActualizado);

                session.setAttribute("usuarios", usuarios); // Guardar cambios en la sesión
                response.sendRedirect("detallesUsuarioServlet?nickname=" + nickname); // Redirigir para mostrar los cambios
            }
        } else {
            response.getWriter().write("Error: usuario no encontrado.");
        }
    }

    private ClienteDto crearNuevoCliente(ClienteDto cliente, HttpServletRequest request) throws IOException, ServletException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nacionalidad = request.getParameter("nacionalidad");
        Date fechaNacimiento = obtenerFecha(request.getParameter("fechaNacimiento"));
        TipoDocumento tipoDocumento = obtenerTipoDocumento(request.getParameter("tipoDocumento"));
        String numeroDocumento = request.getParameter("numeroDocumento");

        // Manejar la imagen (opcional)
     // Manejar la imagen (opcional)
        String imagen = cliente.getImagen();
        Part imagenPart = request.getPart("imagen");
        if (imagenPart != null && imagenPart.getSize() > 0) {
            String imagePath = "uploads/" + imagenPart.getSubmittedFileName(); // Cambiado a "uploads"
            imagenPart.write(getServletContext().getRealPath("/") + imagePath);
            imagen = imagePath;
        }

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
        
        clienteDto.setImagen(imagen);
        clienteDto.setNacionalidad(nacionalidad);
        clienteDto.setNickName(cliente.getNickName());
        clienteDto.setNombre(nombre);
        clienteDto.setNumeroDocumento(numeroDocumento);
        clienteDto.setTipoDocumento(tipoDocumento);
        clienteDto.getReservas();
        clienteDto.getComprasPaquete();
        clienteDto.getSeguidos();
        
        return clienteDto;
        
    }

    private AerolineaDto crearNuevaAerolinea(AerolineaDto aerolinea, HttpServletRequest request) throws IOException, ServletException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String website = request.getParameter("website");

     // Manejar la imagen (opcional)
        String imagen = aerolinea.getImagen();
        Part imagenPart = request.getPart("imagen");
        if (imagenPart != null && imagenPart.getSize() > 0) {
            String imagePath = "uploads/" + imagenPart.getSubmittedFileName(); // Cambiado a "uploads"
            imagenPart.write(getServletContext().getRealPath("/") + imagePath);
            imagen = imagePath;
        }

        ObjectFactory fabrica = new ObjectFactory();
        AerolineaDto aerolineaDto = fabrica.createAerolineaDto();
        
        aerolineaDto.setContrasenia(aerolinea.getContrasenia());
        aerolineaDto.setDescripcion(descripcion);
        aerolineaDto.setEmail(aerolinea.getEmail());
        aerolineaDto.setFechaAlta(aerolinea.getFechaAlta());
        aerolineaDto.setImagen(imagen);
        aerolineaDto.setNickName(aerolinea.getNickName());
        aerolineaDto.setNombre(nombre);
        aerolineaDto.setWebsite(website);
        
        return aerolineaDto;
        
    }

    private Date obtenerFecha(String fechaStr) {
        try {
            return fechaStr != null && !fechaStr.isEmpty() ? Date.valueOf(fechaStr) : null;
        } catch (IllegalArgumentException e) {
            return null; // Manejo de error de formato
        }
    }

    private TipoDocumento obtenerTipoDocumento(String tipoStr) {
        try {
            return tipoStr != null ? TipoDocumento.valueOf(tipoStr) : null;
        } catch (IllegalArgumentException e) {
            return null; // Manejo de error de valor inválido
        }
    }
}
