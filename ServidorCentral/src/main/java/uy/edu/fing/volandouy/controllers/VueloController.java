package uy.edu.fing.volandouy.controllers;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import uy.edu.fing.volandouy.dto.CategoriaDto;
import uy.edu.fing.volandouy.dto.CiudadDto;
import uy.edu.fing.volandouy.dto.PasajeDto;
import uy.edu.fing.volandouy.dto.ReservaDto;
import uy.edu.fing.volandouy.dto.VueloDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;
import uy.edu.fing.volandouy.managers.UserManager;
import uy.edu.fing.volandouy.managers.VueloManager;
import uy.edu.fing.volandouy.modelo.Aerolinea;
import uy.edu.fing.volandouy.modelo.Categoria;
import uy.edu.fing.volandouy.modelo.Ciudad;
import uy.edu.fing.volandouy.modelo.Cliente;
import uy.edu.fing.volandouy.modelo.Pasaje;
import uy.edu.fing.volandouy.modelo.Reserva;
import uy.edu.fing.volandouy.modelo.RutaDeVuelo;
import uy.edu.fing.volandouy.modelo.Usuario;
import uy.edu.fing.volandouy.modelo.Vuelo;
import uy.edu.fing.volandouy.util.DtoUtil;

public class VueloController implements IVueloController {

	private VueloManager manejadorVuelo = VueloManager.getInstance();
	private UserManager manejadorUsuario = UserManager.getInstance();

	private void validarAerolineaRuta(String aerolinea, String ruta) throws Exception {

		Aerolinea aerolineaSistema = (Aerolinea) manejadorUsuario.findUserByNickname(aerolinea);

		if (aerolineaSistema == null || aerolinea.isBlank()) {
			throw new Exception("La aerolinea ingresada no existe en el sistema");
		}

		// Si no se agrega este if entonces hay que hacer un catch del
		// NullPointerException
		if (ruta == null || ruta.isBlank()) {
			throw new Exception("No se ingreso el nombre de la ruta de vuelo");
		}

		RutaDeVuelo rutaSistema = aerolineaSistema.getRutasDeVuelo().get(ruta);

		if (rutaSistema == null) {
			throw new Exception("La ruta de vuelo ingresada no pertenece a la aerolinea ingresada");
		}
		
		if (rutaSistema.getEstado() == EstadoRuta.Finalizada) {
			throw new Exception("El estado de la ruta de vuelo ingresada es \"Finalizada\"");
		}
		
		if (rutaSistema.getEstado() != EstadoRuta.Confirmada) {
			throw new Exception("El estado de la ruta de vuelo ingresada es distinto de \"Confirmada\"");
		}

	}

	@SuppressWarnings("deprecation")
	private void validarVuelo(VueloDto vuelodto) throws Exception {

		Aerolinea aerolinea = (Aerolinea) manejadorUsuario.findUserByNickname(vuelodto.getAerolinea());
		RutaDeVuelo ruta = aerolinea.getRutasDeVuelo().get(vuelodto.getRuta());
		Vuelo vuelo = null;
		List<Usuario> usuarios = manejadorUsuario.getUsuarios();

		for (Usuario usuario : usuarios) {
			if (usuario instanceof Aerolinea) {
				Aerolinea aerolineaSistema = (Aerolinea) usuario;
				for (RutaDeVuelo rutaSistema : aerolineaSistema.getRutasDeVuelo().values()) {
					for (Vuelo vueloRuta : rutaSistema.getVuelos()) {
						if (vueloRuta.getNombre().equals(vuelodto.getNombre())) {
							vuelo = vueloRuta;
						}
					}
				}
			}
		}

		if (vuelodto.getNombre().isBlank()) {
			throw new Exception("El nombre del vuelo es requerido");
		}

		if (vuelo != null) {
			throw new Exception("El nombre del vuelo ingresado ya esta registrado en el sistema");
		}

		if (vuelodto.getFechaAlta().before(ruta.getFechaAlta())) {
			throw new Exception(
					"La fecha en la que se realiza el vuelo es anterior a la fecha de alta de la ruta del vuelo");
		}

		Time horaCero = new Time(0, 0, 0);
		if (vuelodto.getDuracion() == null || vuelodto.getDuracion().equals(horaCero)) {
			throw new Exception("La duracion ingresada es igual a 0");
		}

		if (vuelodto.getCantMaxAsEjecutivo() == 0 && vuelodto.getCantMaxAsTurista() == 0) {
			throw new Exception("La cantidad de asientos totales del avion es igual a 0");
		}

		if (vuelodto.getCantMaxAsEjecutivo() < 0) {
			throw new Exception("La cantidad de asientos del tipo ejecutivo es menor a 0");
		}

		if (vuelodto.getCantMaxAsTurista() < 0) {
			throw new Exception("La cantidad de asientos del tipo turista es menor a 0");
		}

		if (vuelodto.getFecha().before(vuelodto.getFechaAlta())) {
			throw new Exception("La fecha en la que se realiza el vuelo es anterior a la fecha de alta del vuelo");
		}

	}

	@Override
	public void agregarVuelo(VueloDto vuelodto) throws Exception{
		agregarVuelo(vuelodto, true);
	}
	
	public void agregarVuelo(VueloDto vuelodto, boolean validar) throws Exception {
		if (validar) {
			validarAerolineaRuta(vuelodto.getAerolinea(), vuelodto.getRuta());
			validarVuelo(vuelodto);
		}
		
		Aerolinea aerolinea = (Aerolinea) manejadorUsuario.findUserByNickname(vuelodto.getAerolinea());
		RutaDeVuelo ruta = aerolinea.getRutasDeVuelo().get(vuelodto.getRuta());

		Vuelo vueloNuevo = new Vuelo(vuelodto, ruta);
		ruta.getVuelos().add(vueloNuevo);
		manejadorVuelo.agregarVuelos(vueloNuevo);
	}

	@Override
	public List<VueloDto> listarVuelo(String aerolinea, String ruta) throws Exception {
		try {
		validarAerolineaRuta(aerolinea, ruta);
		} catch (Exception e) {
			if (e.getMessage().equals("El estado de la ruta de vuelo ingresada es distinto de \"Confirmada\"")) {
				return new ArrayList<>();
			}else if (e.getMessage().equals("El estado de la ruta de vuelo ingresada es \"Finalizada\"")) {
				/* Codigo de recuperar los vuelos de la ruta finalizada */
				Aerolinea aerolineaSistema = (Aerolinea) manejadorUsuario.findUserByNickname(aerolinea);
				RutaDeVuelo rutaSistema = aerolineaSistema.getRutasDeVuelo().get(ruta);

				List<VueloDto> datosVuelos = new ArrayList<>();
				for (Vuelo vuelo : rutaSistema.getVuelos()) {
					datosVuelos.add(vuelo.toDto());
				}

				return datosVuelos;
			}else {
				throw new Exception(e.getMessage());
			}
		}

		Aerolinea aerolineaSistema = (Aerolinea) manejadorUsuario.findUserByNickname(aerolinea);
		RutaDeVuelo rutaSistema = aerolineaSistema.getRutasDeVuelo().get(ruta);

		List<VueloDto> datosVuelos = new ArrayList<>();
		for (Vuelo vuelo : rutaSistema.getVuelos()) {
			datosVuelos.add(vuelo.toDto());
		}

		return datosVuelos;

	}

	private void validarCliente(String cliente) throws Exception {

		Cliente clienteSistema = (Cliente) manejadorUsuario.findUserByNickname(cliente);
		if (clienteSistema == null || cliente.isBlank()) {
			throw new Exception("El cliente ingresado no se encuentra registrado en el sistema");
		}
	}

	private int calcularAsientosOcupados(Vuelo vuelo, TipoAsiento tipoasiento) {
		int asientosOcupados = 0;
		for (Reserva reserva : vuelo.getClientes().values()) {
			if (reserva.getTipoAsiento() == tipoasiento) {
				asientosOcupados = asientosOcupados + reserva.getPasajeros().size();
			}
		}
		return asientosOcupados;
	}

	private boolean maxAsientosOcupados(Vuelo vuelo, ReservaDto reservadto) {
		int asientosOcupados = calcularAsientosOcupados(vuelo, reservadto.getTipoAsiento());
		boolean ocupados;
		if (reservadto.getTipoAsiento() == TipoAsiento.Turista) {
			ocupados = asientosOcupados + reservadto.getPasajeros().size() > vuelo.getCantMaxAsTurista();
		} else {
			ocupados = asientosOcupados + reservadto.getPasajeros().size() > vuelo.getCantMaxAsEjecutivo();
		}
		return ocupados;
	}

	private void validarVueloReserva(ReservaDto reservadto) throws Exception {
		String aerolinea = reservadto.getAerolinea();
		String ruta = reservadto.getRuta();
		Aerolinea aerolineaSistema = (Aerolinea) manejadorUsuario.findUserByNickname(aerolinea);
		RutaDeVuelo rutaSistema = aerolineaSistema.getRutasDeVuelo().get(ruta);
		Vuelo vueloSistema = null;

		for (Vuelo vueloClase : rutaSistema.getVuelos()) {
			if (vueloClase.getNombre().equals(reservadto.getVuelo())) {
				vueloSistema = vueloClase;
			}
		}

		if (vueloSistema == null || reservadto.getVuelo().isBlank()) {
			throw new Exception("El vuelo ingresado no se encuentra registrado en el sistema");
		}

		if (vueloSistema.getClientes().get(reservadto.getCliente()) != null) {
			throw new Exception("El cliente seleccionado ya realizo una reserva para este vuelo previamente");
		}

		if (reservadto.getPasajeros() == null || reservadto.getPasajeros().isEmpty()) {
			throw new Exception("No se ingreso ningun pasajero para la reserva del vuelo");
		}

		for (PasajeDto pasajero : reservadto.getPasajeros()) {
			if (pasajero.getNombre() == null || pasajero.getNombre().isBlank()) {
				throw new Exception("Falta ingresar el nombre de uno o mas pasajeros");
			}
			/*
			 * if (!Utilidades.verificarLetrasEspacio(pasajero.getNombre())) { throw new
			 * Exception(
			 * "El nombre de algún pasajero contiene carácteres inválidos, solo ingrese letras mayúsculas y minúsculas"
			 * ); }
			 */
			if (pasajero.getApellido() == null || pasajero.getApellido().isBlank()) {
				throw new Exception("Falta ingresar el apellido de uno o mas pasajeros");
			}
			/*
			 * if (!Utilidades.verificarLetrasEspacio(pasajero.getApellido())) { throw new
			 * Exception(
			 * "El apellido de algún pasajero contiene carácteres inválidos, solo ingrese letras mayúsculas y minúsculas"
			 * ); }
			 */
		}

		if (maxAsientosOcupados(vueloSistema, reservadto)) {
			throw new Exception("No queda lugar disponible en el avion para la cantidad de pasajes ingresada");
		}

		if (reservadto.getFechaReserva().after(vueloSistema.getFecha())) {
			throw new Exception("La fecha de la reserva ingresada en posterior a la fecha programada del vuelo");
		}
		
		if (reservadto.getFechaReserva().before(vueloSistema.getFechaAlta())) {
			throw new Exception("La fecha de la reserva ingresada es anterior a la fecha de alta del vuelo en el sistema");
		}
		
		Cliente clienteSistema = (Cliente) manejadorUsuario.findUserByNickname(reservadto.getCliente());
		
		if (clienteSistema != null && reservadto.getFechaReserva().before(clienteSistema.getFechaAlta())) {
			throw new Exception("la fecha de la reserva es anterior a la fecha de alta del cliente en el sistema");
		}
		
	}

	private float calcularCosto(ReservaDto reservadto, RutaDeVuelo ruta) {
		int cantidadPasajes = reservadto.getPasajeros().size();
		float costoEquipaje = ruta.getCostoEquipajeExtra() * reservadto.getUniEquipajeExtra();

		if (reservadto.getTipoAsiento() == TipoAsiento.Turista) {
			return ruta.getCostoTurista() * cantidadPasajes + costoEquipaje;
		} else {
			return ruta.getCostoEjecutivo() * cantidadPasajes + costoEquipaje;
		}

	}

	public void reservarVuelo(ReservaDto reservadto) throws Exception {
		reservarVuelo(reservadto, true);
	}

	public void reservarVuelo(ReservaDto reservadto, boolean realizarValidaciones) throws Exception {
		String aerolinea = reservadto.getAerolinea();
		String ruta = reservadto.getRuta();
		if (realizarValidaciones) {
			validarAerolineaRuta(reservadto.getAerolinea(), reservadto.getRuta());
			validarVueloReserva(reservadto);
			validarCliente(reservadto.getCliente());
		}
		Cliente clienteSistema = (Cliente) manejadorUsuario.findUserByNickname(reservadto.getCliente());
		Aerolinea aerolineaSistema = (Aerolinea) manejadorUsuario.findUserByNickname(aerolinea);
		RutaDeVuelo rutaSistema = aerolineaSistema.getRutasDeVuelo().get(ruta);
		Vuelo vueloSistema = null;

		for (Vuelo vueloClase : rutaSistema.getVuelos()) {
			if (vueloClase.getNombre().equals(reservadto.getVuelo())) {
				vueloSistema = vueloClase;
			}
		}

		if (reservadto.getPaquete() == null) {
			float costoReserva = calcularCosto(reservadto, rutaSistema);
			reservadto.setCosto(costoReserva);
			
			Reserva nuevaReserva = new Reserva(reservadto, clienteSistema, vueloSistema, null, null);
			clienteSistema.getReservas().put(reservadto.getVuelo(), nuevaReserva);
			vueloSistema.getClientes().put(reservadto.getCliente(), nuevaReserva);
			
			for (Pasaje pasajero : nuevaReserva.getPasajeros()) {
				vueloSistema.agregarAsiento(pasajero);
			}
			
		} else {
			// Reserva con paquete
			// Falta validar las cosas relacionadas con el paquete,
			// por ejemplo que el cliente haya comprado el paquete seleccionado,
			// Que no haya superado los usos del paquete, fechas de vencimiento del paquete,
			// etc.

			PaqueteController controladorPaquete = new PaqueteController();
			controladorPaquete.reservarVueloPaquete(reservadto, clienteSistema, rutaSistema, vueloSistema);
		}

	}

	private void validarCiudad(CiudadDto ciudadDto, VueloManager manejadorVuelo) throws Exception {
		if (ciudadDto.getNombre() == null || ciudadDto.getNombre().isBlank()) {
			throw new Exception("El nombre de la ciudad es obligatorio");
		}
		if (manejadorVuelo.buscarCiudadPorNombre(ciudadDto.getNombre(), ciudadDto.getPais()) != null) {
			throw new Exception("El par nombre - pais ya existe");
		}
		if (ciudadDto.getDescripcion() == null || ciudadDto.getDescripcion().isBlank()) {
			throw new Exception("La descripcion es obligatoria");
		}
		if (ciudadDto.getNombreAeropuerto() == null || ciudadDto.getNombreAeropuerto().isBlank()) {
			throw new Exception("El nombre del aeropuerto es obligatorio");
		}
		if (ciudadDto.getPais() == null || ciudadDto.getPais().isBlank()) {
			throw new Exception("El país es obligatorio");
		}
		if (ciudadDto.getWebsite() == null || ciudadDto.getWebsite().isBlank()) {
			throw new Exception("El website es obligatorio");
		}

	}

	public void agregarCiudad(CiudadDto ciudadDto) throws Exception {
		this.agregarCiudad(ciudadDto, true);
	}

	public void agregarCiudad(CiudadDto ciudadDto, boolean validar) throws Exception {
		if (validar) {
			this.validarCiudad(ciudadDto, manejadorVuelo);
		}
		Ciudad nevaCiudad = new Ciudad(ciudadDto);
		manejadorVuelo.agregarCiudad(nevaCiudad);
	}

	@Override
	public List<CiudadDto> listarCiudades() {

		return DtoUtil.convertirLista(manejadorVuelo.getCiudades(), Ciudad::toDto);
	}

	@Override
	public void altaCategoria(CategoriaDto categoriaDto) throws Exception {
		if (categoriaDto.getNombre() == null || categoriaDto.getNombre().isBlank()) {
			throw new Exception("El nombre de la categoría no puede estar vacío");
		}

		if (manejadorVuelo.buscarCategoriaPorNombre(categoriaDto.getNombre()) != null) {
			throw new Exception("El nombre de la categoría ya se encuentra registrado en el sistema");
		}

		Categoria nuevaCategoria = new Categoria(categoriaDto);
		manejadorVuelo.agregarCategoria(nuevaCategoria);
	}

	@Override
	public List<CategoriaDto> listarCategorias() {
		return DtoUtil.convertirLista(manejadorVuelo.getCategorias(), Categoria::toDto);
	}

	@Override
	public CiudadDto buscarCiudadPorNombre(String nombre, String pais) {
		return manejadorVuelo.buscarCiudadPorNombre(nombre, pais).toDto();
	}

	@Override
	public CategoriaDto buscarCategoriaPorNombre(String nombre) {
		return manejadorVuelo.buscarCategoriaPorNombre(nombre).toDto();
	}

	@Override
	public VueloDto buscarVueloPornombre(String nombre) {
		return manejadorVuelo.buscarVuelosPorNombre(nombre).toDto();
	}
}
