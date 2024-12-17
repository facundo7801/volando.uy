package uy.edu.fing.volandouy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import uy.edu.fing.volandouy.webservices.UsuarioDto;
import uy.edu.fing.volandouy.webservices.UsuarioControllerService;

@SuppressWarnings("serial")
@WebServlet("/ListaUsuariosServlet")
public class ListaUsuariosServlet extends HttpServlet {
    public ListaUsuariosServlet() {
    	super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		UsuarioControllerService serviceImplementacion = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService");
		String seguidores = (String)request.getParameter("seguidores");
		String seguidos = (String)request.getParameter("seguidos");
		String nickname = (String)request.getParameter("nicknameUsuarioSeguidores");
		List<UsuarioDto> listaUsuarios = serviceImplementacion.listarUsuario().getListaUsuarios();;
		
		/* Nuevo por seguidores */
		if (seguidos != null) {
			UsuarioDto usuarioLogeado = buscarUsuario(listaUsuarios, nickname);
			List<UsuarioDto> listaUsuariosSeguidos = new ArrayList<>();
			
			for (String nicknameSeguido : usuarioLogeado.getSeguidos()) {
				UsuarioDto usuarioSeguido = buscarUsuario(listaUsuarios, nicknameSeguido);
				listaUsuariosSeguidos.add(usuarioSeguido);
			}
			
			listaUsuarios = listaUsuariosSeguidos;
		} else if (seguidores != null) {
			UsuarioDto usuarioLogeado = buscarUsuario(listaUsuarios, nickname);
			List<UsuarioDto> listaUsuariosSeguidores = new ArrayList<>();
			
			/*for (UsuarioDto usuario : listaUsuarios) {
				if (!usuario.getNickName().equals(nickname)) {
					for (String nombreSeguido : usuario.getSeguidos()) {
						if (nombreSeguido.equals(nickname)) {
							listaUsuariosSeguidores.add(usuario);
						}	
					}	
				}
			}*/
			
			for (String nicknameSeguidor : usuarioLogeado.getSeguidores()) {
				UsuarioDto usuarioSeguidor = buscarUsuario(listaUsuarios, nicknameSeguidor);
				listaUsuariosSeguidores.add(usuarioSeguidor);
			}
			
			listaUsuarios = listaUsuariosSeguidores;
		}
		
		String seguidosL = request.getParameter("seguidosL");
		String seguidoresL = request.getParameter("seguidoresL");
		request.setAttribute("seguidosL", seguidosL);
		request.setAttribute("seguidoresL", seguidoresL);
		
		/* Nuevo por seguidores */
		
        request.getSession().setAttribute("usuarios", listaUsuarios);
        request.getSession().setAttribute("pagina", "consultaUsuarios");
        request.getRequestDispatcher("/WEB-INF/views/listaUsuarios.jsp").forward(request, response);
    }
    
    private UsuarioDto buscarUsuario(List<UsuarioDto> usuarios, String nickname) {
    	UsuarioDto usuarioLogeado = null;
    	for (UsuarioDto usuario : usuarios) {
    		if (usuario.getNickName().equals(nickname)) {
    			usuarioLogeado = usuario;
    		}
    	}
    	
    	return usuarioLogeado;
    }
    
}
