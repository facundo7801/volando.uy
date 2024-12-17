package uy.edu.fing.volandouy.webServices;

import java.sql.Time;
import java.util.Date;

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
import uy.edu.fing.volandouy.managers.VueloManager;
import uy.edu.fing.volandouy.modelo.Vuelo;
import uy.edu.fing.volandouy.modelo.Cliente;
import uy.edu.fing.volandouy.modelo.Reserva;

@WebService(targetNamespace = "http://volandouy.fing.edu.uy/webServices/")
@XmlSeeAlso({ AerolineaDto.class, CategoriaDto.class, CiudadDto.class, ClienteDto.class, ComercializaDto.class,
	CompraPaqueteDto.class, PaqueteDto.class, PasajeDto.class, ReservaDto.class, RutaDeVueloDto.class,
	UsuarioDto.class, VueloDto.class, TipoDocumento.class, TipoAsiento.class, EstadoRuta.class, LupaDto.class })
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class detallesReservaService {
private Endpoint endpoint = null;
	
	public detallesReservaService() {}
	
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
	
	@WebMethod
	public ReservaDto reservaCliente(String nombreCliente, String nombreVuelo) {
		Vuelo vueloConsultado = VueloManager.getInstance().buscarVuelosPorNombre(nombreVuelo);
		
		ReservaDto reservaConsultada = null;
		for(Reserva reservas : vueloConsultado.getClientes().values()) {
			if (reservas.getCliente().getNickName().equals(nombreCliente)) {
				reservaConsultada = reservas.toDto();
			}
		}
		
		return reservaConsultada;
		
	}
	
	@SuppressWarnings("deprecation")
	@WebMethod
	public void realizarCheckIn(String nombreCliente, String nombreVuelo) {
		Date fechaActual = new Date();
		Time horaCheckIn = new Time(fechaActual.getHours(), fechaActual.getMinutes(), fechaActual.getSeconds());
		Cliente clienteSistema = (Cliente)UserManager.getInstance().findUserByNickname(nombreCliente);
		
		for(Reserva reserva : clienteSistema.getReservas().values()){
			if (reserva.getVuelo().getNombre().equals(nombreVuelo)) {
				reserva.setEmbarqueUrl("Realizado");
				reserva.setFechaCheckIn(fechaActual);
				reserva.setEmbarqueHora(horaCheckIn);
			}
		}
	}
	
}
