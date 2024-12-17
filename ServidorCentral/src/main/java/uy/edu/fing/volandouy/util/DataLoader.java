package uy.edu.fing.volandouy.util;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uy.edu.fing.volandouy.controllers.PaqueteController;
import uy.edu.fing.volandouy.controllers.UsuarioController;
import uy.edu.fing.volandouy.controllers.VueloController;
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
import uy.edu.fing.volandouy.dto.VueloDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;
import uy.edu.fing.volandouy.managers.UserManager;
import uy.edu.fing.volandouy.modelo.Cliente;
import uy.edu.fing.volandouy.modelo.Reserva;
import uy.edu.fing.volandouy.modelo.Usuario;

public class DataLoader {
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private boolean isWeb;
	private String basePath;
	private UsuarioController iusuario = new UsuarioController();
	private VueloController ivuelo = new VueloController();
	private PaqueteController ipaquete = new PaqueteController();

	private Map<String, Map<String, String>> categoriasMap = new HashMap<>();
	private Map<String, Map<String, String>> usuariosMap = new HashMap<>();
	private Map<String, Map<String, String>> aerolineasMap = new HashMap<>();
	private Map<String, Map<String, String>> clientesMap = new HashMap<>();
	private Map<String, Map<String, String>> vuelosMap = new HashMap<>();
	private Map<String, Map<String, String>> ciudadesMap = new HashMap<>();
	private Map<String, Map<String, String>> rutasMap = new HashMap<>();
	private Map<String, Map<String, String>> reservasMap = new HashMap<>();
	private Map<String, Map<String, String>> pasajesMap = new HashMap<>();
	private Map<String, Map<String, String>> paquetesMap = new HashMap<>();
	private Map<String, Map<String, String>> paquetesRutasMap = new HashMap<>();
	private Map<String, Map<String, String>> comprasPaquetesMap = new HashMap<>();
	private Map<String, Map<String, String>> checkInMap = new HashMap<>();
	private Map<String, Map<String, String>> seguidosMap = new HashMap<>();
	private Map<String, String> imagesMap = new HashMap<>();

	public DataLoader(boolean isWeb, String basePath) {
		this.isWeb = isWeb;
		this.basePath = basePath;
	}

	private Date parseDate(String dateString) {
		if (dateString == null || dateString.isEmpty()) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Time parseTime(String timeString) {
		if (timeString == null || timeString.trim().isEmpty()) {
			return null;
		}

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

			long ms = sdf.parse(timeString).getTime();

			return new Time(ms);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Map<String, Map<String, String>> loadCSV(String filePath) throws IOException {
		List<Map<String, String>> records = CSVReader.readCSV(filePath);
		Map<String, Map<String, String>> dataMap = new HashMap<>();

		for (Map<String, String> record : records) {
			String ref = record.get("Ref");
			dataMap.put(ref, record);
		}
		return dataMap;
	}

	private String getFilePath(String fileName) {
		return "src/data/java/uy/edu/fing/volandouy/datos/" + fileName;
	}

	private void loadImages() {
		File imagesDir = new File(basePath + "/imagenes");
		if (imagesDir.exists() && imagesDir.isDirectory()) {
			File[] files = imagesDir.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isFile()) {
						String fileName = file.getName();
						String key = fileName.split("\\.")[0];
						imagesMap.put(key, file.getName());
					}
				}
			}
		} else {
			System.err.println("Directorio de imágenes no encontrado: " + imagesDir.getAbsolutePath());
		}
	}

	public void loadAllFiles() throws IOException {
		if (this.isWeb) {
			// Cargar imágenes
			loadImages();
		}

		// Cargar usuarios
		File usuariosFile = new File(getFilePath("2024Usuarios.csv"));
		usuariosMap = loadCSV(usuariosFile.getAbsolutePath());

		// Cargar aerolíneas
		File aerolineasFile = new File(getFilePath("2024Usuarios-Aerolineas.csv"));
		aerolineasMap = loadCSV(aerolineasFile.getAbsolutePath());

		// Cargar clientes
		File clientesFile = new File(getFilePath("2024Usuarios-Clientes.csv"));
		clientesMap = loadCSV(clientesFile.getAbsolutePath());

		// Cargar categorías
		File categoriaFile = new File(getFilePath("2024Categorias.csv"));
		categoriasMap = loadCSV(categoriaFile.getAbsolutePath());

		// Cargar ciudades
		File ciudadesFile = new File(getFilePath("2024Ciudades.csv"));
		ciudadesMap = loadCSV(ciudadesFile.getAbsolutePath());

		// Cargar rutas de vuelo
		File rutasFile = new File(getFilePath("2024RutasVuelos.csv"));
		rutasMap = loadCSV(rutasFile.getAbsolutePath());

		// Cargar vuelos
		File vuelosFile = new File(getFilePath("2024Vuelos.csv"));
		vuelosMap = loadCSV(vuelosFile.getAbsolutePath());

		// Cargar reservas
		File reservasFile = new File(getFilePath("2024Reservas.csv"));
		reservasMap = loadCSV(reservasFile.getAbsolutePath());

		// Cargar Pasajes
		File pasajesFile = new File(getFilePath("2024Pasajes.csv"));
		pasajesMap = loadCSV(pasajesFile.getAbsolutePath());

		// Cargar paquetes
		File paquetesFile = new File(getFilePath("2024Paquetes.csv"));
		paquetesMap = loadCSV(paquetesFile.getAbsolutePath());

		// Cargar paquetesRutas
		File paquetesRutasFile = new File(getFilePath("2024PaquetesRutas.csv"));
		paquetesRutasMap = loadCSV(paquetesRutasFile.getAbsolutePath());

		// Cargar compras de paquetes
		File comprasPaquetesFile = new File(getFilePath("2024ComprasPaquetes.csv"));
		comprasPaquetesMap = loadCSV(comprasPaquetesFile.getAbsolutePath());

		File checkInFile = new File(getFilePath("2024Checkin.csv"));
		checkInMap = loadCSV(checkInFile.getAbsolutePath());
		
		File seguidosFile = new File(getFilePath("2024Seguidos.csv"));
		seguidosMap = loadCSV(seguidosFile.getAbsolutePath());
	}

	public void processUsuarios() throws Exception {
		for (Map.Entry<String, Map<String, String>> entry : usuariosMap.entrySet()) {
			String usuarioRef = entry.getKey();
			Map<String, String> usuarioData = entry.getValue();

			String tipoUsuario = usuarioData.get("Tipo");

			if ("C".equalsIgnoreCase(tipoUsuario)) {
				processCliente(usuarioRef, usuarioData);
			} else if ("A".equalsIgnoreCase(tipoUsuario)) {
				processAerolinea(usuarioRef, usuarioData);
			} else {
				System.err.println("Tipo de usuario desconocido para la referencia: " + usuarioRef);
			}
		}
	}

	private void processCliente(String usuarioRef, Map<String, String> usuarioData) throws Exception {
		Map<String, String> clienteData = clientesMap.get(usuarioRef);
		if (clienteData == null) {
			System.err.println("Cliente no encontrado para la referencia: " + usuarioRef);
			return;
		}
		Date fechaAlta = parseDate(usuarioData.get("FechaAlta"));

		String imagenRuta = imagesMap.get(usuarioRef);
		ClienteDto cliente = new ClienteDto(usuarioData.get("Nickname"), usuarioData.get("Nombre"),
				usuarioData.get("Correo"), clienteData.get("Apellido"), parseDate(clienteData.get("FechaNacimiento")),
				TipoDocumento.valueOf(clienteData.get("TipoDocumento").toUpperCase()),
				clienteData.get("NumeroDocumento"), clienteData.get("Nacionalidad"), null, null, fechaAlta,
				usuarioData.get("Contraseña"), imagenRuta, null, null);

		iusuario.agregarUsuario(cliente);
	}

	private void processAerolinea(String usuarioRef, Map<String, String> usuarioData) throws Exception {
		Map<String, String> aerolineaData = aerolineasMap.get(usuarioRef);
		if (aerolineaData == null) {
			System.err.println("Aerolínea no encontrada para la referencia: " + usuarioRef);
			return;
		}

		Date fechaAlta = parseDate(usuarioData.get("FechaAlta"));

		String imagenRuta = imagesMap.get(usuarioRef);
		AerolineaDto aerolinea = new AerolineaDto(usuarioData.get("Nickname"), usuarioData.get("Nombre"),
				usuarioData.get("Correo"), aerolineaData.get("Descripcion"), aerolineaData.get("SitioWeb"), null,
				fechaAlta, usuarioData.get("Contraseña"), imagenRuta, null, null);

		iusuario.agregarUsuario(aerolinea);
	}

	public void processCategorias() throws Exception {
		for (Map.Entry<String, Map<String, String>> entry : categoriasMap.entrySet()) {
			String categoriaRef = entry.getKey();
			Map<String, String> categoriaData = entry.getValue();

			String nombreCategoria = categoriaData.get("Nombre");

			if (nombreCategoria == null || nombreCategoria.trim().isEmpty()) {
				System.err.println(
						"Advertencia: El nombre de la categoría está vacío para la referencia: " + categoriaRef);
				continue;
			}

			CategoriaDto categoria = new CategoriaDto(nombreCategoria);
			ivuelo.altaCategoria(categoria);
		}
	}

	public void processCiudades() throws Exception {
		for (Map.Entry<String, Map<String, String>> entry : ciudadesMap.entrySet()) {
			String ciudadRef = entry.getKey();
			Map<String, String> ciudadData = entry.getValue();

			String nombre = ciudadData.get("Nombre");
			String pais = ciudadData.get("Pais");
			String nombreAeropuerto = ciudadData.get("Aeropuerto");
			String descripcion = ciudadData.get("Descripcion");
			String website = ciudadData.get("SitioWeb");
			Date fechaAlta = parseDate(ciudadData.get("FechaAlta"));

			System.out.println("fechaAlta: " + fechaAlta);

			if (nombre == null || nombre.trim().isEmpty()) {
				System.err.println("Advertencia: El nombre de la ciudad está vacío para la referencia: " + ciudadRef);
				continue;
			}

			CiudadDto ciudad = new CiudadDto(nombre, pais, nombreAeropuerto, descripcion, website, fechaAlta);
			ivuelo.agregarCiudad(ciudad, false);
		}
	}

	public void processRutasDeVuelo() throws Exception {
		for (Map.Entry<String, Map<String, String>> entry : rutasMap.entrySet()) {
			String rutaRef = entry.getKey();
			Map<String, String> rutaData = entry.getValue();

			String nombre = rutaData.get("Nombre");
			String descripcion = rutaData.get("Descripcion");
			Date fechaAlta = parseDate(rutaData.get("FechaAlta"));
			float costoTurista = Float.parseFloat(rutaData.get("CostoTurista"));
			float costoEjecutivo = Float.parseFloat(rutaData.get("CostoEjecutivo"));
			float costoEquipajeExtra = Float.parseFloat(rutaData.get("CostoEquipaje"));
			Time hora = parseTime(rutaData.get("Hora"));
			String estado = rutaData.get("Estado");
			EstadoRuta enumEstado = EstadoRuta.valueOf(estado);
			String resumen = rutaData.get("DescripcionCorta");

			String aerolineaRef = rutaData.get("Aerolinea");
			String categoriasRef = rutaData.get("Categoria");
			String ciudadOrigenRef = rutaData.get("Origen");
			String ciudadDestinoRef = rutaData.get("Destino");

			Date fechaFinalizada = null;
			if (!rutaData.get("FechaFinalizada").equals("vacio")) {
				fechaFinalizada = parseDate(rutaData.get("FechaFinalizada"));
			}

			String imagenRuta = imagesMap.get(rutaRef);

			if (nombre == null || nombre.trim().isEmpty()) {
				System.err.println(
						"Advertencia: El nombre de la ruta de vuelo está vacío para la referencia: " + rutaRef);
				continue;
			}

			Map<String, String> aerolineaData = aerolineasMap.get(aerolineaRef);
			if (aerolineaData == null) {
				System.err.println("Error: No se encontró la aerolínea para la referencia: " + aerolineaRef);
				continue;
			}
			Map<String, String> userAerolineaData = usuariosMap.get(aerolineaData.get("Ref"));
			AerolineaDto aerolinea = (AerolineaDto) iusuario.obtenerUsuarioPorCorreo(userAerolineaData.get("Correo"));

			Map<String, String> ciudadOrigenData = ciudadesMap.get(ciudadOrigenRef);
			if (ciudadOrigenData == null) {
				System.err.println("Error: No se encontró la ciudad de origen para la referencia: " + ciudadOrigenRef);
				continue;
			}
			CiudadDto ciudadOrigen = ivuelo.buscarCiudadPorNombre(ciudadOrigenData.get("Nombre"),
					ciudadOrigenData.get("Pais"));

			Map<String, String> ciudadDestinoData = ciudadesMap.get(ciudadDestinoRef);
			if (ciudadDestinoData == null) {
				System.err
						.println("Error: No se encontró la ciudad de destino para la referencia: " + ciudadDestinoRef);
				continue;
			}
			CiudadDto ciudadDestino = ivuelo.buscarCiudadPorNombre(ciudadDestinoData.get("Nombre"),
					ciudadDestinoData.get("Pais"));

			List<CategoriaDto> categorias = new ArrayList<>();
			String[] categoriasRefs = categoriasRef.split(",");
			for (String categoriaRef : categoriasRefs) {
				categoriaRef = categoriaRef.trim();
				Map<String, String> categoriaData = categoriasMap.get(categoriaRef);
				if (categoriaData == null) {
					System.err.println("Error: No se encontró la categoría para la referencia: " + categoriaRef);
					continue;
				}
				CategoriaDto categoria = ivuelo.buscarCategoriaPorNombre(categoriaData.get("Nombre"));
				categorias.add(categoria);
			}

			String video = rutaData.get("Video");
			
			if (video.equals("(Sin video)")) {
				video = null;
			}
			
			int visitas = Integer.parseInt(rutaData.get("Visitas"));
			
			RutaDeVueloDto rutaDeVuelo = new RutaDeVueloDto(nombre, descripcion, costoTurista, costoEjecutivo,
					costoEquipajeExtra, fechaAlta, aerolinea, categorias, null, hora, ciudadOrigen, ciudadDestino,
					enumEstado, resumen, imagenRuta, video, visitas, fechaFinalizada);
			iusuario.agregarRuta(rutaDeVuelo, false);
			
		}
	}

	public void processVuelos() throws Exception {
		for (Map.Entry<String, Map<String, String>> entry : vuelosMap.entrySet()) {
			String vueloRef = entry.getKey();
			Map<String, String> vueloData = entry.getValue();

			String nombre = vueloData.get("Nombre");
			Date fecha = parseDate(vueloData.get("Fecha"));
			Time duracion = parseTime(vueloData.get("Duracion"));
			int cantMaxAsTurista = Integer.parseInt(vueloData.get("MaxTuristas"));
			int cantMaxAsEjecutivo = Integer.parseInt(vueloData.get("MaxEjecutivo"));
			Date fechaAlta = parseDate(vueloData.get("FechaAlta"));

			String rutaRef = vueloData.get("Ruta");
			String aerolineaRef = vueloData.get("Aerolinea");

			String imagenVuelo = imagesMap.get(vueloRef);

			Map<String, String> rutaData = rutasMap.get(rutaRef);
			RutaDeVueloDto rutaDeVuelo = iusuario.obtenerRutaDeVueloPorNombre(rutaData.get("Nombre"));

			Map<String, String> aerolineaData = aerolineasMap.get(aerolineaRef);

			Map<String, String> userAerolineaData = usuariosMap.get(aerolineaData.get("Ref"));
			AerolineaDto aerolinea = (AerolineaDto) iusuario.obtenerUsuarioPorCorreo(userAerolineaData.get("Correo"));

			VueloDto vuelo = new VueloDto(nombre, fecha, duracion, cantMaxAsTurista, cantMaxAsEjecutivo, fechaAlta,
					aerolinea.getNickName(), rutaDeVuelo.getNombre(), null, imagenVuelo);
			ivuelo.agregarVuelo(vuelo, false);
		}
	}

	private List<PasajeDto> obtenerPasajerosReserva(String refReserva) {
		List<PasajeDto> pasajesAsociados = new ArrayList<>();
		for (Map.Entry<String, Map<String, String>> pasajeEntry : pasajesMap.entrySet()) {
			Map<String, String> pasajeData = pasajeEntry.getValue();
			String pasajeReservaRef = pasajeData.get("Reserva");
			if (refReserva.equals(pasajeReservaRef)) {
				String nombrePasajero = pasajeEntry.getValue().get("Nombre");
				String apellidoPasajero = pasajeEntry.getValue().get("Apellido");
				String numeroAsiento = pasajeEntry.getValue().get("Asiento");
				if (numeroAsiento.equals("vacio")) {
					numeroAsiento = null;
				}
				PasajeDto pasaje = new PasajeDto(nombrePasajero, apellidoPasajero, numeroAsiento);
				pasajesAsociados.add(pasaje);
			}
		}
		return pasajesAsociados;

	}

	public void processReservasYPasajes() throws Exception {
		for (Map.Entry<String, Map<String, String>> entry : reservasMap.entrySet()) {
			String reservaRef = entry.getKey();
			Map<String, String> reservaData = entry.getValue();

			Date fechaReserva = parseDate(reservaData.get("FechaReserva"));
			String tipoAsientoStr = reservaData.get("TipoAsiento");
			TipoAsiento tipoAsiento = TipoAsiento.valueOf(tipoAsientoStr);
			int uniEquipajeExtra = Integer.parseInt(reservaData.get("EquipajeExtra"));
			float costo = Float.parseFloat(reservaData.get("Costo"));

			String vueloRef = reservaData.get("Vuelo");
			String rutaRef = reservaData.get("Ruta");
			String aerolineaRef = reservaData.get("Aerolinea");
			String clienteRef = reservaData.get("Cliente");

			Map<String, String> aerolineaData = aerolineasMap.get(aerolineaRef);
			Map<String, String> userAerolineaData = usuariosMap.get(aerolineaData.get("Ref"));
			AerolineaDto aerolinea = (AerolineaDto) iusuario.obtenerUsuarioPorCorreo(userAerolineaData.get("Correo"));

			Map<String, String> clienteData = clientesMap.get(clienteRef);
			Map<String, String> userClienteData = usuariosMap.get(clienteData.get("Ref"));
			ClienteDto cliente = (ClienteDto) iusuario.obtenerUsuarioPorCorreo(userClienteData.get("Correo"));

			Map<String, String> vueloData = vuelosMap.get(vueloRef);
			VueloDto vuelo = ivuelo.buscarVueloPornombre(vueloData.get("Nombre"));

			Map<String, String> rutaData = rutasMap.get(rutaRef);
			RutaDeVueloDto rutaDeVuelo = iusuario.obtenerRutaDeVueloPorNombre(rutaData.get("Nombre"));

			List<PasajeDto> pasajeros = obtenerPasajerosReserva(reservaRef);

			ReservaDto reserva = new ReservaDto(fechaReserva, tipoAsiento, uniEquipajeExtra, costo, pasajeros,
					cliente.getNickName(), aerolinea.getNickName(), rutaDeVuelo.getNombre(), vuelo.getNombre(), null,
					null, null, null);
			processCheckIn(reserva, reservaRef);
		}
	}

	public void processPaquetes() throws Exception {
		for (Map.Entry<String, Map<String, String>> entry : paquetesMap.entrySet()) {
			String paqueteRef = entry.getKey();
			Map<String, String> paqueteData = entry.getValue();

			String nombre = paqueteData.get("Nombre");
			String descripcion = paqueteData.get("Descripción");
			Date fecha = parseDate(paqueteData.get("FechaAlta"));
			int periodoValidez = Integer.parseInt(paqueteData.get("Validez"));
			float costoAsociado = Float.parseFloat(paqueteData.get("Costo"));
			int descuento = Integer.parseInt(paqueteData.get("Descuento"));
			String imagenRuta = imagesMap.get(paqueteRef);

			PaqueteDto paquete = new PaqueteDto(nombre, descripcion, periodoValidez, descuento, fecha, costoAsociado,
					null, null, imagenRuta);
			ipaquete.crearPaqueteDeRutaDeVuelo(paquete);
		}
	}

	public void processPaquetesRutas() throws Exception {
		for (Map.Entry<String, Map<String, String>> entry : paquetesRutasMap.entrySet()) {
			Map<String, String> paqueteRutaData = entry.getValue();

			String paqueteRef = paqueteRutaData.get("Paquete");
			String rutaRef = paqueteRutaData.get("Ruta");
			int cantRutasDeVuelo = Integer.parseInt(paqueteRutaData.get("Cantidad"));
			String tipoAsientoStr = paqueteRutaData.get("TipoAsiento");
			TipoAsiento tipoDeAsiento = TipoAsiento.valueOf(tipoAsientoStr);

			Map<String, String> paqueteData = paquetesMap.get(paqueteRef);
			PaqueteDto paquete = ipaquete.buscarPaquetePorNombre(paqueteData.get("Nombre"));

			Map<String, String> rutaData = rutasMap.get(rutaRef);
			RutaDeVueloDto rutaDeVuelo = iusuario.obtenerRutaDeVueloPorNombre(rutaData.get("Nombre"));

			ComercializaDto comercializa = new ComercializaDto(cantRutasDeVuelo, tipoDeAsiento, rutaDeVuelo.getNombre(),
					paquete.getNombre());
			ipaquete.agregarRutaDeVueloAPaquete(comercializa);
		}
	}

	public void processComprasPaquetes() throws Exception {
		for (Map.Entry<String, Map<String, String>> entry : comprasPaquetesMap.entrySet()) {
			Map<String, String> compraData = entry.getValue();

			String paqueteRef = compraData.get("Paquete");
			String clienteRef = compraData.get("Cliente");
			Date fechaCompra = parseDate(compraData.get("FechaCompra"));
			Date fechaValidez = parseDate(compraData.get("Validez"));
			// int costo = Integer.parseInt(compraData.get("Costo"));

			Map<String, String> paqueteData = paquetesMap.get(paqueteRef);
			PaqueteDto paquete = ipaquete.buscarPaquetePorNombre(paqueteData.get("Nombre"));

			Map<String, String> clienteData = clientesMap.get(clienteRef);
			Map<String, String> userClienteData = usuariosMap.get(clienteData.get("Ref"));
			ClienteDto cliente = (ClienteDto) iusuario.obtenerUsuarioPorCorreo(userClienteData.get("Correo"));

			CompraPaqueteDto compra = new CompraPaqueteDto(fechaCompra, fechaValidez, cliente.getNickName(),
					paquete.getNombre(), null);
			ipaquete.comprarPaquete(compra);
		}
	}

	private void processCheckIn(ReservaDto reserva, String reservaRef) throws Exception {
		Map<String, String> checkInData = null;
		for (Map<String, String> dataCheckIn : checkInMap.values()) {
			if (dataCheckIn.get("Reserva").equals(reservaRef)) {
				checkInData = dataCheckIn;
			}
		}
		// for (Map.Entry<String, Map<String, String>> entry : checkInMap.entrySet()) {
		// Map<String, String> checkInData = entry.getValue();

		// String checkInRef = checkInData.get("Ref");
		if (checkInData != null) {
			//String checkInReserva = checkInData.get("Reserva");
			Time checkInhora = parseTime(checkInData.get("HoraEmbarque"));
			Date checkInFecha = parseDate(checkInData.get("FechaCheckin"));

			String URL = "Realizado";
			reserva.setEmbarqueHora(checkInhora);
			reserva.setFechaCheckIn(checkInFecha);
			reserva.setEmbarqueUrl(URL);

			ivuelo.reservarVuelo(reserva, false);
			Cliente clienteSistema = (Cliente) UserManager.getInstance().findUserByNickname(reserva.getCliente());

			for (Reserva reservaCliente : clienteSistema.getReservas().values()) {
				if (reservaCliente.getVuelo().getNombre().equals(reserva.getVuelo())) {
					reserva.setEmbarqueUrl("Realizado");
					reserva.setFechaCheckIn(checkInFecha);
					reserva.setEmbarqueHora(checkInhora);
				}
			}
		} else {
			ivuelo.reservarVuelo(reserva, false);
		}
		// }

	}
	
	public void processSeguidos() {
		for (Map<String, String> dataSeguidos : seguidosMap.values()) {
			String seguidorNickname = dataSeguidos.get("NickNameSeguidor");
			String seguidoNickname = dataSeguidos.get("NickNameSeguido");
			
			UserManager manejador = UserManager.getInstance();
			Usuario usuarioQueSigue = manejador.findUserByNickname(seguidorNickname);
			Usuario usuarioTarget = manejador.findUserByNickname(seguidoNickname);
			usuarioQueSigue.getSeguidos().put(seguidoNickname, usuarioTarget);
			usuarioTarget.getSeguidores().put(seguidorNickname, usuarioQueSigue);
		}
	}

}
