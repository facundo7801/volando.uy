package uy.edu.fing.volandouy.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import uy.edu.fing.volandouy.webservices.UsuarioDto;
import uy.edu.fing.volandouy.webservices.UsuarioControllerService;


@WebServlet("/VerificarUsuarioAJAX")
public class VerificarUsuarioAJAX extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public VerificarUsuarioAJAX() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipoVerificacion = request.getParameter("tipoVerificacion");
        boolean disponible = false;

        UsuarioControllerService iusuario = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
        List<UsuarioDto> usuarios = iusuario.listarUsuario().getListaUsuarios();
        
        if ("nicknameVerificacion".equals(tipoVerificacion)) {
        	String nickname = request.getParameter("valor");
            disponible = verificarNickname(usuarios, nickname);
        } else if ("emailVerificacion".equals(tipoVerificacion)) {
        	String email = request.getParameter("valor");
            disponible = verificarEmail(usuarios, email);
        }
        
        response.setContentType("application/json");
        //response.getWriter().write("{\"disponible\":" + disponible + "}");
        // Establecer el tipo de respuesta a JSON
        PrintWriter out = response.getWriter();
        
        // Construir la respuesta JSON
        String jsonResponse = "{\"disponible\": " + disponible + "}";
        out.print(jsonResponse);
        out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean verificarNickname(List<UsuarioDto> usuarios, String nickname) {
		for (UsuarioDto usuario : usuarios) {
			if (usuario.getNickName().equals(nickname)) {
				return false;
			}
		}
		
		if (nickname.isBlank()) {
			return false;
		}
		
        return true;
    }

    private boolean verificarEmail(List<UsuarioDto> usuarios, String email) {
    	for (UsuarioDto usuario : usuarios) {
			if (usuario.getEmail().equals(email)) {
				return false;
			}
		}
    	
    	if (email.isBlank() || !email.matches(".+@.+")) {
    		return false;
    	}
    	
        return true;
    }

}
