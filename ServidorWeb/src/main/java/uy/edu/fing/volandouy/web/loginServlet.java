package uy.edu.fing.volandouy.web;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uy.edu.fing.volandouy.webservices.LoginService;
import uy.edu.fing.volandouy.webservices.UsuarioDto;
import uy.edu.fing.volandouy.webservices.AerolineaDto;
import uy.edu.fing.volandouy.webservices.Exception_Exception;

/*
 * Si es visitante solo tiene el atributo de tipo,
 * sino tiene el atributo tipo, usuario y email.
 * tipo: indica si es "visitante", "aerolinea, "cliente"
 * usuario: guarda el nickname del usuario que se logeo con exito
 * email: es el email del usuario que se logeo con exito
 */

@SuppressWarnings("serial")
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	
	public loginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if ("cerrarSesion".equals((String)request.getSession().getAttribute("cerrar"))) {
			
			HttpSession sesion = request.getSession();
			Enumeration<String> attributeNames = sesion.getAttributeNames();

			
			while (attributeNames.hasMoreElements()) {
			    String attributeName = attributeNames.nextElement();
			    sesion.removeAttribute(attributeName);  // Eliminar el atributo de la sesión
			}
		}
		
		
		
		request.getSession().setAttribute("tipo", "visitante");
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		
		try {
			//Se logro logear con exito
			verificarUsuario(userName, userPassword, request);
			request.getSession().setAttribute("cerrar", "cerrarSesion");;
			request.getRequestDispatcher("homeServlet").forward(request, response);
		} catch (Exception_Exception e) {
			//Datos incorrectos en el login
			request.setAttribute("errorMessage", "Usuario o contraseña incorrectos");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}

	}

	private void verificarUsuario(String userName, String userPassword, HttpServletRequest request) throws Exception_Exception {
		LoginService serviceImplementacion = WebServiceAdapter.getService(LoginService.class, "LoginService");
		UsuarioDto usuarioLogeado = serviceImplementacion.getSession(userName, userPassword);
		
		if (usuarioLogeado != null) {
			if (usuarioLogeado.getNickName().equals(userName)) {
				request.getSession().setAttribute("tipoLogin", "nickname");
			}else {
				request.getSession().setAttribute("tipoLogin", "email");
			}
			
			request.getSession().setAttribute("usuario", usuarioLogeado.getNickName());
			request.getSession().setAttribute("email", usuarioLogeado.getEmail());
			if (usuarioLogeado instanceof AerolineaDto) {
				request.getSession().setAttribute("tipo", "aerolinea");
			} else {
				request.getSession().setAttribute("tipo", "cliente");
			}
			
		}

	}

}
