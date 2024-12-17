package uy.edu.fing.volandouy.webServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.jws.WebParam;
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

@WebService(targetNamespace = "http://volandouy.fing.edu.uy/webServices/")
@XmlSeeAlso({ AerolineaDto.class, CategoriaDto.class, CiudadDto.class, ClienteDto.class, ComercializaDto.class,
	CompraPaqueteDto.class, PaqueteDto.class, PasajeDto.class, ReservaDto.class, RutaDeVueloDto.class,
	UsuarioDto.class, VueloDto.class, TipoDocumento.class, TipoAsiento.class, EstadoRuta.class, LupaDto.class })
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ImagenesService {
private Endpoint endpoint = null;
	
	public ImagenesService() {}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
	@WebMethod
	public byte[] getFile(String name) {
		byte[] byteArray = null;
		try {
		        File f = new File("imagenes/" + name);
		        FileInputStream streamer = new FileInputStream(f);
		        byteArray = new byte[streamer.available()];
		        streamer.read(byteArray);
		        
		} catch (IOException e) {
		        e.printStackTrace();
		}
		
		return byteArray;
		
		} 
}
