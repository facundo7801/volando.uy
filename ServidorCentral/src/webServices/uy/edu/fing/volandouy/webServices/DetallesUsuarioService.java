package uy.edu.fing.volandouy.webServices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Endpoint;
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
import uy.edu.fing.volandouy.managers.UserManager;
import uy.edu.fing.volandouy.modelo.Usuario;

@WebService(targetNamespace = "http://volandouy.fing.edu.uy/webServices/")
@XmlSeeAlso({ AerolineaDto.class, CategoriaDto.class, CiudadDto.class, ClienteDto.class, ComercializaDto.class,
	CompraPaqueteDto.class, PaqueteDto.class, PasajeDto.class, ReservaDto.class, RutaDeVueloDto.class,
	UsuarioDto.class, VueloDto.class, TipoDocumento.class, TipoAsiento.class, EstadoRuta.class, LupaDto.class })
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class DetallesUsuarioService {
	private Endpoint endpoint = null;
	
	public DetallesUsuarioService() {}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
	@WebMethod
	public void seguirUsuario(String usuarioDeseaSeguir, String usuario){
		UserManager manejador = UserManager.getInstance();
		Usuario usuarioQueSigue = manejador.findUserByNickname(usuarioDeseaSeguir);
		Usuario usuarioTarget = manejador.findUserByNickname(usuario);
		//No se hace chequeos porque sino no hubiese llegado hasta aca
		usuarioQueSigue.getSeguidos().put(usuario, usuarioTarget);
		usuarioTarget.getSeguidores().put(usuarioDeseaSeguir, usuarioQueSigue);
	}
	
	@WebMethod
	public void dejarSeguirUsuario(String usuarioDeseaDejar, String usuario){
		UserManager manejador = UserManager.getInstance();
		Usuario usuarioQueDeja = manejador.findUserByNickname(usuarioDeseaDejar);
		Usuario usuarioPierdeSeguidor = manejador.findUserByNickname(usuario);
		//No se hace chequeos porque sino no hubiese llegado hasta aca
		usuarioQueDeja.getSeguidos().remove(usuario);
		usuarioPierdeSeguidor.getSeguidores().remove(usuarioDeseaDejar);
	}
	
}
