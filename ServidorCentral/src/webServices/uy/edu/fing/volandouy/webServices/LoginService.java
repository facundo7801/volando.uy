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
import uy.edu.fing.volandouy.modelo.Cliente;
import uy.edu.fing.volandouy.modelo.Usuario;

@WebService(targetNamespace = "http://volandouy.fing.edu.uy/webServices/")
@XmlSeeAlso({ AerolineaDto.class, CategoriaDto.class, CiudadDto.class, ClienteDto.class, ComercializaDto.class,
	CompraPaqueteDto.class, PaqueteDto.class, PasajeDto.class, ReservaDto.class, RutaDeVueloDto.class,
	UsuarioDto.class, VueloDto.class, TipoDocumento.class, TipoAsiento.class, EstadoRuta.class, LupaDto.class })
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class LoginService{
	private Endpoint endpoint = null;
	
	public LoginService() {}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
	@WebMethod
	public UsuarioDto getSession(String id, String password) throws Exception{
		UserManager manejadorUsuario = UserManager.getInstance();
		Usuario usuario = manejadorUsuario.findUserByEmail(id);

		if (usuario == null) {
			usuario = manejadorUsuario.findUserByNickname(id);
		}
		
		if (usuario != null && usuario.getContrasenia().equals(password)) {
			return usuario.toDto();
		}
		
		throw new Exception("Usuario o contraseña incorrectos");
	}
	
	@WebMethod
	public ClienteDto getSessionMobile(String id, String password) throws Exception{
		UserManager manejadorUsuario = UserManager.getInstance();
		Usuario usuario = manejadorUsuario.findUserByEmail(id);

		if (usuario == null) {
			usuario = manejadorUsuario.findUserByNickname(id);
		}
		
		if (usuario != null && usuario.getContrasenia().equals(password)) {
			if (usuario instanceof Cliente) {
				Cliente clienteMobile = (Cliente)usuario;
				return clienteMobile.toDto();
			}
			
			throw new Exception("Solo los clientes pueden acceder desde el servicio movil");
		}
		
		throw new Exception("Usuario o contraseña incorrectos");
	}
	
}
