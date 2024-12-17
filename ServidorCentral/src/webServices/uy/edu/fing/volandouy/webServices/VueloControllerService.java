package uy.edu.fing.volandouy.webServices;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Endpoint;
import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.dataAux.LupaDto;
import uy.edu.fing.volandouy.dataContainers.CategoriasList;
import uy.edu.fing.volandouy.dataContainers.CiudadesList;
import uy.edu.fing.volandouy.dataContainers.VuelosList;
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
import uy.edu.fing.volandouy.managers.VueloManager;
import uy.edu.fing.volandouy.modelo.Aerolinea;
import uy.edu.fing.volandouy.modelo.RutaDeVuelo;
import uy.edu.fing.volandouy.modelo.Usuario;
import uy.edu.fing.volandouy.modelo.Vuelo;

@WebService(targetNamespace = "http://volandouy.fing.edu.uy/webServices/")
@XmlSeeAlso({ AerolineaDto.class, CategoriaDto.class, CiudadDto.class, ClienteDto.class, ComercializaDto.class,
		CompraPaqueteDto.class, PaqueteDto.class, PasajeDto.class, ReservaDto.class, RutaDeVueloDto.class,
		UsuarioDto.class, VueloDto.class, TipoDocumento.class, TipoAsiento.class, EstadoRuta.class, LupaDto.class })
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class VueloControllerService {
	private Endpoint endpoint = null;

	public VueloControllerService() {
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}

	@SuppressWarnings("deprecation")
	@WebMethod
	public void agregarVuelo(VueloDto vuelodto, String duracionVuelo, String imagenNombre, byte[] imagenBytes) throws Exception {
		IVueloController ivuelo = ManagerFactory.getInstance().getVueloController();
		
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
		
		vuelodto.setImagen(imagenNombre);
		
		Time duracion = Time.valueOf(duracionVuelo + ":00");
		vuelodto.setDuracion(duracion);
		
		RutaDeVueloDto ruta = ManagerFactory.getInstance().getUsuarioController().obtenerRutaDeVueloPorNombre(vuelodto.getRuta());
		if (ruta != null) {
			Date fechaVueloActualizada = (Date) vuelodto.getFecha().clone();
			fechaVueloActualizada.setHours(ruta.getHora().getHours());
			fechaVueloActualizada.setMinutes(ruta.getHora().getMinutes());
			fechaVueloActualizada.setSeconds(ruta.getHora().getSeconds());
			vuelodto.setFecha(fechaVueloActualizada);
		}
		
		ivuelo.agregarVuelo(vuelodto);
	}

	@WebMethod
	public VuelosList listarVuelo(String aerolinea, String ruta) throws Exception {
		IVueloController ivuelo = ManagerFactory.getInstance().getVueloController();
		VuelosList listaVuelos = new VuelosList();
		listaVuelos.setListaVuelos(ivuelo.listarVuelo(aerolinea, ruta));
		return listaVuelos;
	}

	@WebMethod
	public void reservarVuelo(ReservaDto reservadto) throws Exception {
		IVueloController ivuelo = ManagerFactory.getInstance().getVueloController();
		ivuelo.reservarVuelo(reservadto);
	}

	@WebMethod
	public void agregarCiudad(CiudadDto ciudadDto) throws Exception {
		IVueloController ivuelo = ManagerFactory.getInstance().getVueloController();
		ivuelo.agregarCiudad(ciudadDto);
	}

	@WebMethod
	public CiudadesList listarCiudades() {
		IVueloController ivuelo = ManagerFactory.getInstance().getVueloController();
		CiudadesList listaCiudades = new CiudadesList();
		listaCiudades.setListaCiudades(ivuelo.listarCiudades());
		return listaCiudades;
	}

	@WebMethod
	public void altaCategoria(CategoriaDto categoriaDto) throws Exception {
		IVueloController ivuelo = ManagerFactory.getInstance().getVueloController();
		ivuelo.altaCategoria(categoriaDto);
	}

	@WebMethod
	public CategoriasList listarCategorias() {
		IVueloController ivuelo = ManagerFactory.getInstance().getVueloController();
		CategoriasList listaCategorias = new CategoriasList();
		listaCategorias.setListaCategorias(ivuelo.listarCategorias());
		return listaCategorias;
	}

	@WebMethod
	public CiudadDto buscarCiudadPorNombre(String nombre, String pais) {
		IVueloController ivuelo = ManagerFactory.getInstance().getVueloController();
		return ivuelo.buscarCiudadPorNombre(nombre, pais);
	}

	@WebMethod
	public CategoriaDto buscarCategoriaPorNombre(String nombre) {
		IVueloController ivuelo = ManagerFactory.getInstance().getVueloController();
		return ivuelo.buscarCategoriaPorNombre(nombre);
	}

	@WebMethod
	public VueloDto buscarVueloPornombre(String nombre) {
		IVueloController ivuelo = ManagerFactory.getInstance().getVueloController();
		return ivuelo.buscarVueloPornombre(nombre);
	}
	
	public boolean sePuedeFinalizarRuta(String ruta) {
		RutaDeVuelo rutaSistema = null;
		
		for (Usuario usuario : UserManager.getInstance().getUsuarios()) {
			if (usuario instanceof Aerolinea) {
				Aerolinea aerolinea = (Aerolinea) usuario;
				for (RutaDeVuelo rutaAero : aerolinea.getRutasDeVuelo().values()) {
					if (rutaAero.getNombre().equals(ruta)) {
						rutaSistema = rutaAero;
					}
				}
			}
		}
		
		/*for (Vuelo vueloSistema : VueloManager.getInstance().getVuelos()) {
			if (ruta.equals(vueloSistema.getRutaDeVuelo().getNombre())) {
				rutaSistema = vueloSistema.getRutaDeVuelo();
			}
		}*/
		
		Date fechaActual = new Date();
		List<Vuelo> vuelosRuta = rutaSistema.getVuelos();
		boolean sePuedeFinalizar = rutaSistema.getListComercializa().size() == 0;
		
		for (int i = 0; sePuedeFinalizar && i < vuelosRuta.size(); i++) {
			sePuedeFinalizar = vuelosRuta.get(i).getFecha().before(fechaActual);
		}
		
		return sePuedeFinalizar;
	}
}
