package uy.edu.fing.volandouy.webServices;

import java.util.ArrayList;
import java.util.List;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Endpoint;
import uy.edu.fing.volandouy.dataAux.LupaDto;
import uy.edu.fing.volandouy.dataContainers.ReservasList;
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
import uy.edu.fing.volandouy.modelo.Cliente;
import uy.edu.fing.volandouy.modelo.Reserva;
import uy.edu.fing.volandouy.modelo.Vuelo;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;
import uy.edu.fing.volandouy.managers.UserManager;
import uy.edu.fing.volandouy.managers.VueloManager;


@WebService(targetNamespace = "http://volandouy.fing.edu.uy/webServices/")
@XmlSeeAlso({ AerolineaDto.class, CategoriaDto.class, CiudadDto.class, ClienteDto.class, ComercializaDto.class,
	CompraPaqueteDto.class, PaqueteDto.class, PasajeDto.class, ReservaDto.class, RutaDeVueloDto.class,
	UsuarioDto.class, VueloDto.class, TipoDocumento.class, TipoAsiento.class, EstadoRuta.class, LupaDto.class })
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)

public class ListarReservasService {
	private Endpoint endpoint = null;
	
	public ListarReservasService() {}
	
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
	@WebMethod
	public ReservasList obtenerReservasCliente(String nombreCliente) {
		Cliente clienteSistema = (Cliente)UserManager.getInstance().findUserByNickname(nombreCliente);
		
		ReservasList reservasLista = new ReservasList();
		
		List<ReservaDto> reservas = new ArrayList<>();
		
		for (Reserva reserva : clienteSistema.getReservas().values()) {
			reservas.add(reserva.toDto());
		}
		
		reservasLista.setListaReserva(reservas);
		
		
		return reservasLista;
	}
	
	
	@WebMethod
	public ReservasList obtenerReservasVuelo(String nombreVuelo, String tipoUsuario, String nicknameCliente) {
		Vuelo vuelosistema = VueloManager.getInstance().buscarVuelosPorNombre(nombreVuelo);
		List<ReservaDto> reservas = new ArrayList<>();
		if (tipoUsuario.equals("aerolinea")) {
			for (Reserva reserva : vuelosistema.getClientes().values()) {
				reservas.add(reserva.toDto());
			}
		}else if (tipoUsuario.equals("cliente")) {
			for (Reserva reserva : vuelosistema.getClientes().values()) {
				if (reserva.getCliente().getNickName().equals(nicknameCliente)) {
					reservas.add(reserva.toDto());
				}
			}
		}
		ReservasList reservasLista = new ReservasList();
		reservasLista.setListaReserva(reservas);
		
		return reservasLista;
	}
	
}


















