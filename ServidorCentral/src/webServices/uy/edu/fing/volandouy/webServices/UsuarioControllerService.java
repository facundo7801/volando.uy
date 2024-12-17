package uy.edu.fing.volandouy.webServices;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Time;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Endpoint;
import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.dataAux.LupaDto;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.CategoriaDto;
import uy.edu.fing.volandouy.dto.CiudadDto;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.dto.ComercializaDto;
import uy.edu.fing.volandouy.dto.CompraPaqueteDto;
import uy.edu.fing.volandouy.dto.PaqueteDto;
import uy.edu.fing.volandouy.dto.PasajeDto;
import uy.edu.fing.volandouy.dto.ReservaDto;
import uy.edu.fing.volandouy.dto.RutaDeVueloDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;
import uy.edu.fing.volandouy.dto.VueloDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;
import uy.edu.fing.volandouy.fabricas.ManagerFactory;
import uy.edu.fing.volandouy.managers.UserManager;
import uy.edu.fing.volandouy.modelo.Aerolinea;
import uy.edu.fing.volandouy.modelo.RutaDeVuelo;
import uy.edu.fing.volandouy.modelo.Usuario;
import uy.edu.fing.volandouy.dataContainers.RutasList;
import uy.edu.fing.volandouy.dataContainers.UsuariosList;

@WebService(targetNamespace = "http://volandouy.fing.edu.uy/webServices/")
@XmlSeeAlso({ AerolineaDto.class, CategoriaDto.class, CiudadDto.class, ClienteDto.class, ComercializaDto.class,
	CompraPaqueteDto.class, PaqueteDto.class, PasajeDto.class, ReservaDto.class, RutaDeVueloDto.class,
	UsuarioDto.class, VueloDto.class, TipoDocumento.class, TipoAsiento.class, EstadoRuta.class, LupaDto.class })
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class UsuarioControllerService {
	private Endpoint endpoint = null;
	
	public UsuarioControllerService() {}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
	@WebMethod
	public void agregarUsuario(UsuarioDto usuariodto, String imagenNombre, byte[] imagenBytes) throws Exception{
		IUsuarioController iusuario = ManagerFactory.getInstance().getUsuarioController();
		
		if (!imagenNombre.equals(new String())) {
			try {
				String uploads = System.getProperty("user.dir") + "/imagenes";
				System.out.println("Ruta donde se guarda la imagen: " + uploads);
	            File uploadDir = new File(uploads);
	            if (!uploadDir.exists())
	                uploadDir.mkdir();
	            File file = new File(uploadDir, imagenNombre);
	
	            // Guarda los bytes de la imagen en el servidor
	            try (FileOutputStream fos = new FileOutputStream(file)) {
	                fos.write(imagenBytes);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}else {
			imagenNombre = null;
		}
		
		usuariodto.setImagen(imagenNombre);
		iusuario.agregarUsuario(usuariodto);
	}
	
	@WebMethod
	public void actualizarUsuario(UsuarioDto usuarioDto, String imagenNombre, byte[] imagenBytes) throws Exception{
		IUsuarioController iusuario = ManagerFactory.getInstance().getUsuarioController();
		
		if (!imagenNombre.equals(new String())) {
			try {
				String uploads = System.getProperty("user.dir") + "/imagenes";
				System.out.println("Ruta donde se guarda la imagen: " + uploads);
	            File uploadDir = new File(uploads);
	            if (!uploadDir.exists())
	                uploadDir.mkdir();
	            File file = new File(uploadDir, imagenNombre);
	
	            // Guarda los bytes de la imagen en el servidor
	            try (FileOutputStream fos = new FileOutputStream(file)) {
	                fos.write(imagenBytes);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}else {
			imagenNombre = null;
		}
		usuarioDto.setImagen(imagenNombre);
		
		iusuario.actualizarUsuario(usuarioDto);
	}
	
	@WebMethod
	public UsuariosList listarUsuario(){
		IUsuarioController iusuario = ManagerFactory.getInstance().getUsuarioController();
		UsuariosList lista = new UsuariosList();
		lista.setListaUsuarios(iusuario.listarUsuario());
		return lista;
	}
	
	@WebMethod
	public UsuarioDto obtenerUsuarioPorCorreo(String correo) {
		IUsuarioController iusuario = ManagerFactory.getInstance().getUsuarioController();
		return iusuario.obtenerUsuarioPorCorreo(correo);
	}

	@SuppressWarnings("deprecation")
	@WebMethod
	public void agregarRuta(RutaDeVueloDto rutaDto, String horaRuta, String imagenNombre, byte[] imagenBytes) throws Exception{
		IUsuarioController iusuario = ManagerFactory.getInstance().getUsuarioController();
		
		if (!imagenNombre.equals(new String())) {
			try {
				String uploads = System.getProperty("user.dir") + "/imagenes";
				System.out.println("Ruta donde se guarda la imagen: " + uploads);
	            File uploadDir = new File(uploads);
	            if (!uploadDir.exists())
	                uploadDir.mkdir();
	            File file = new File(uploadDir, imagenNombre);
	
	            // Guarda los bytes de la imagen en el servidor
	            try (FileOutputStream fos = new FileOutputStream(file)) {
	                fos.write(imagenBytes);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}else {
			imagenNombre = null;
		}
		
		rutaDto.setImagen(imagenNombre);
		
		String[]  horaParts = horaRuta.split(":");
        int horas = Integer.parseInt(horaParts[0]);
        int minutos = Integer.parseInt(horaParts[1]);
		
		Time hora = new Time(horas, minutos, 0);
		rutaDto.setHora(hora);
		iusuario.agregarRuta(rutaDto);
	}

	@WebMethod
	public RutasList listarRutas(String aerolinea) throws Exception{
		IUsuarioController iusuario = ManagerFactory.getInstance().getUsuarioController();
		RutasList listaRutas = new RutasList();
		listaRutas.setListaRutas(iusuario.listarRutas(aerolinea));
		return listaRutas;
	}

	@WebMethod
	public void cambiarEstadoRuta(String aerolinea, String ruta, EstadoRuta estado) throws Exception{
		IUsuarioController iusuario = ManagerFactory.getInstance().getUsuarioController();
		iusuario.cambiarEstadoRuta(aerolinea, ruta, estado);
	}
	
	@WebMethod
	public RutaDeVueloDto obtenerRutaDeVueloPorNombre(String nombre) {
		IUsuarioController iusuario = ManagerFactory.getInstance().getUsuarioController();
		return iusuario.obtenerRutaDeVueloPorNombre(nombre);
	}
	
	@WebMethod
	public UsuarioDto obtenerUsuarioPorNickName(String nickName) {
		IUsuarioController iusuario = ManagerFactory.getInstance().getUsuarioController();
		return iusuario.obtenerUsuarioPorNickName(nickName);
	}
	
	@WebMethod
	public void actualizarVisitasDeRuta(String nombreRuta) {
		UserManager manejadorUsuario = UserManager.getInstance();
		RutaDeVuelo  ruta = manejadorUsuario.findRutaDeVueloByNombre(nombreRuta);
		if (ruta.getEstado() == EstadoRuta.Confirmada) {
			ruta.setVisitas(ruta.getVisitas() + 1);
		}
	}
	
	@WebMethod
	public void finalizarRuta(String aerolinea, String ruta) {
		UserManager manejadorUsuario = UserManager.getInstance();
		Usuario usuarioAerolinea = manejadorUsuario.findUserByNickname(aerolinea);
		
		Aerolinea aerolineaSistema = (Aerolinea) usuarioAerolinea;
		RutaDeVuelo rutaSistema = null;
		
		for (RutaDeVuelo rutaAerolinea : aerolineaSistema.getRutasDeVuelo().values()) {
			if (rutaAerolinea.getNombre().equals(ruta)) {
				rutaSistema = rutaAerolinea;
			}
		}
		
		rutaSistema.setEstado(EstadoRuta.Finalizada);
	}
	
}
