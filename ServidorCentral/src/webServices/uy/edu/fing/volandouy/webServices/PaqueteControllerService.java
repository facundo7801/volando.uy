package uy.edu.fing.volandouy.webServices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Endpoint;
import uy.edu.fing.volandouy.controllers.IPaqueteController;
import uy.edu.fing.volandouy.dataAux.LupaDto;
import uy.edu.fing.volandouy.dataContainers.PaqueteList;
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

@WebService(targetNamespace = "http://volandouy.fing.edu.uy/webServices/")
@XmlSeeAlso({ AerolineaDto.class, CategoriaDto.class, CiudadDto.class, ClienteDto.class, ComercializaDto.class,
	CompraPaqueteDto.class, PaqueteDto.class, PasajeDto.class, ReservaDto.class, RutaDeVueloDto.class,
	UsuarioDto.class, VueloDto.class, TipoDocumento.class, TipoAsiento.class, EstadoRuta.class, LupaDto.class })
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PaqueteControllerService {
private Endpoint endpoint = null;
	
	public PaqueteControllerService(){}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
	@WebMethod
	public void crearPaqueteDeRutaDeVuelo(PaqueteDto paqueteDto) throws Exception{
		IPaqueteController ipaquete = ManagerFactory.getInstance().getPaqueteController();
		ipaquete.crearPaqueteDeRutaDeVuelo(paqueteDto);
	}

	@WebMethod
	public void agregarRutaDeVueloAPaquete(ComercializaDto comercializaDto) throws Exception{
		IPaqueteController ipaquete = ManagerFactory.getInstance().getPaqueteController();
		ipaquete.agregarRutaDeVueloAPaquete(comercializaDto);
	}

	@WebMethod
	public PaqueteList listarPaquetes(){
		IPaqueteController ipaquete = ManagerFactory.getInstance().getPaqueteController();
		PaqueteList listaPaquetes = new PaqueteList();
		listaPaquetes.setListaPaquetes(ipaquete.listarPaquetes());
		return listaPaquetes;
	}

	@WebMethod
	public void comprarPaquete(CompraPaqueteDto compraPaquetedto) throws Exception{
		IPaqueteController ipaquete = ManagerFactory.getInstance().getPaqueteController();
		ipaquete.comprarPaquete(compraPaquetedto);
	}

	@WebMethod
	public PaqueteDto buscarPaquetePorNombre(String nombre) {
		IPaqueteController ipaquete = ManagerFactory.getInstance().getPaqueteController();
		return ipaquete.buscarPaquetePorNombre(nombre);
	}
	
}
