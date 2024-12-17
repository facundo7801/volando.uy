package uy.edu.fing.volandouy.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uy.edu.fing.volandouy.webservices.UsuarioControllerService;
import uy.edu.fing.volandouy.webservices.RutaDeVueloDto;
import uy.edu.fing.volandouy.webservices.EstadoRuta;


@SuppressWarnings("serial")
@WebServlet("/ListarRutasServlet")
public class ListarRutasServlet extends HttpServlet {
	public ListarRutasServlet() {
		super();
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String aerolineaNombre = request.getParameter("aerolineaListar");
    	List <RutaDeVueloDto> rutasAerolinea = obtenerRutas(request, aerolineaNombre);
    	
    	request.getSession().setAttribute("rutas", rutasAerolinea);
        //request.getSession().setAttribute("pagina", "ListarRutas");
        request.getRequestDispatcher("/WEB-INF/views/listarRutas.jsp").forward(request, response);
    }
    
    private List<RutaDeVueloDto> obtenerRutas(HttpServletRequest request , String aerolineaNombre){
    	List<RutaDeVueloDto> rutasMostrar = new ArrayList<>();
    	try {
    		UsuarioControllerService serviceImpl = WebServiceAdapter.getService(UsuarioControllerService.class, "UsuarioControllerService"); 		
			List<RutaDeVueloDto> rutasAerolinea = serviceImpl.listarRutas(aerolineaNombre).getListaRutas();
			
			 
			String aerolineaLogeada = null;
			if (!"visitante".equals((String)request.getSession().getAttribute("tipo"))) {
				aerolineaLogeada = (String)request.getSession().getAttribute("usuario");
			}
			
			if (aerolineaNombre.equals(aerolineaLogeada)) {
				for (RutaDeVueloDto ruta : rutasAerolinea) {
					rutasMostrar.add(ruta);
				}
			}else {
				for (RutaDeVueloDto ruta : rutasAerolinea) {
					if (ruta.getEstado() == EstadoRuta.CONFIRMADA) {
						rutasMostrar.add(ruta);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return rutasMostrar;
    	
    }
    
}
