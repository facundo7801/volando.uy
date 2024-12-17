package uy.edu.fing.volandouy.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import uy.edu.fing.volandouy.dto.ComercializaDto;
import uy.edu.fing.volandouy.dto.CompraPaqueteDto;
import uy.edu.fing.volandouy.dto.PaqueteDto;
import uy.edu.fing.volandouy.dto.ReservaDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;
import uy.edu.fing.volandouy.managers.PaqueteManager;
import uy.edu.fing.volandouy.managers.UserManager;
import uy.edu.fing.volandouy.modelo.Aerolinea;
import uy.edu.fing.volandouy.modelo.Cliente;
import uy.edu.fing.volandouy.modelo.Comercializa;
import uy.edu.fing.volandouy.modelo.CompraPaquete;
import uy.edu.fing.volandouy.modelo.Paquete;
import uy.edu.fing.volandouy.modelo.Pasaje;
import uy.edu.fing.volandouy.modelo.Reserva;
import uy.edu.fing.volandouy.modelo.RutaDeVuelo;
import uy.edu.fing.volandouy.modelo.Usuario;
import uy.edu.fing.volandouy.modelo.Vuelo;

public class PaqueteController implements IPaqueteController {

	private void validarPaquete(PaqueteDto paquete) throws Exception {
		PaqueteManager manejadorPaquete = PaqueteManager.getInstance();

		Paquete paqueteExistente = manejadorPaquete.findPaqueteByName(paquete.getNombre());
		if (paqueteExistente != null) {
			throw new Exception("Ya existe un paquete con el mismo nombre");
		}

		if (paquete.getNombre().isBlank()) {
			throw new Exception("El nombre del paquete es requerido");
		}

		if (paquete.getDescripcion().isBlank()) {
			throw new Exception("La descripción del paquete es requerida");
		}

		if (paquete.getPeriodoValidez() == null) {
			throw new Exception("El período de validez es requerido");
		}

		if (paquete.getPeriodoValidez() <= 0) {
			throw new Exception("El período de validez debe ser positivo");
		}

		if (paquete.getDescuento() == null) {
			throw new Exception("El descuento es requerido");
		}

		if (paquete.getDescuento() <= 0) {
			throw new Exception("El descuento debe ser positivo");
		}

		if (paquete.getDescuento() > 100) {
			throw new Exception("El descuento debe ser menor o igual a 100");
		}

		if (paquete.getFechaAlta() == null) {
			throw new Exception("La fecha de alta es requerida");
		}

	}

	private void validarComercializa(ComercializaDto comercializaDto) throws Exception {
		if (comercializaDto.getRuta().isBlank()) {
			throw new Exception("El nombre de la ruta es requerido");
		}

		if (comercializaDto.getCantRutasDeVuelo() <= 0) {
			throw new Exception("La cantidad de rutas de vuelo debe ser mayor que cero");
		}

		UserManager manejadorUsuario = UserManager.getInstance();
		List<Usuario> usuarios = manejadorUsuario.getUsuarios();
		RutaDeVuelo rutaDeVuelo = this.getRutaDeVuelo(usuarios, comercializaDto.getRuta());

		if (rutaDeVuelo == null) {
			throw new Exception("No existe una ruta de vuelo con el nombre " + comercializaDto.getRuta()
					+ " registrada en el sistema");
		}

		PaqueteManager manejadorPaquete = PaqueteManager.getInstance();
		Paquete paquete = manejadorPaquete.findPaqueteByName(comercializaDto.getPaquete());

		if (paquete == null) {
			throw new Exception(
					"No existe un paquete con el nombre " + comercializaDto.getPaquete() + " registrado en el sistema");
		}

		// Valida que la ruta no se haya ingresado previamente al paquete
		for (Comercializa comercializaRuta : paquete.getListComercializa()) {
			if (comercializaRuta.getRutaDeVuelo().getNombre().equals(comercializaDto.getRuta())) {
				throw new Exception("La ruta de vuelo ingresada ya se había ingresado al paquete previamente");
			}
		}

		if (paquete.getCompradores().size() > 0) {
			throw new Exception(
					"No se pueden agregar rutas al paquete porque ya fue comprado por un cliente previamente");
		}
		
		if (rutaDeVuelo.getEstado() != EstadoRuta.Confirmada) {
			throw new Exception("El estado de la ruta ingresada es distinto de \"Confirmada\"");
		}

	}

	private RutaDeVuelo getRutaDeVuelo(List<Usuario> usuarios, String ruta) {
		RutaDeVuelo rutaDeVuelo = null;
		for (Usuario usuario : usuarios) {
			if (usuario instanceof Aerolinea && rutaDeVuelo == null) {
				Aerolinea aerolinea = (Aerolinea) usuario;
				Map<String, RutaDeVuelo> rutasDeVuelo = aerolinea.getRutasDeVuelo();
				rutaDeVuelo = rutasDeVuelo.get(ruta);
			}
		}
		return rutaDeVuelo;
	}

	public void crearPaqueteDeRutaDeVuelo(PaqueteDto paqueteDto) throws Exception {
		this.validarPaquete(paqueteDto);
		PaqueteManager manejadorPaquete = PaqueteManager.getInstance();
		Paquete nuevoPaquete = new Paquete(paqueteDto);
		manejadorPaquete.addPaquete(nuevoPaquete);
	}

	public List<PaqueteDto> listarPaquetes() {
		PaqueteManager manejadorPaquete = PaqueteManager.getInstance();
		List<Paquete> paquetes = manejadorPaquete.getPaquetes();
		List<PaqueteDto> paquetesDto = new ArrayList<>();
		for (Paquete paquete : paquetes) {
			paquetesDto.add(paquete.toDto());
		}
		return paquetesDto;
	}

	public void agregarRutaDeVueloAPaquete(ComercializaDto comercializaDto) throws Exception {
		this.validarComercializa(comercializaDto);
		PaqueteManager manejadorPaquete = PaqueteManager.getInstance();
		Paquete paquete = manejadorPaquete.findPaqueteByName(comercializaDto.getPaquete());

		UserManager manejadorUsuario = UserManager.getInstance();
		List<Usuario> usuarios = manejadorUsuario.getUsuarios();

		RutaDeVuelo rutaDeVuelo = this.getRutaDeVuelo(usuarios, comercializaDto.getRuta());

		Comercializa comercializa = new Comercializa(comercializaDto.getCantRutasDeVuelo(),
				comercializaDto.getTipoDeAsiento(), rutaDeVuelo, paquete);

		// Lo agrega en paquete
		List<Comercializa> listPaqueteComercializa = paquete.getListComercializa();
		listPaqueteComercializa.add(comercializa);

		if (comercializaDto.getTipoDeAsiento() == TipoAsiento.Turista) {
			//float costoAsociado = rutaDeVuelo.getCostoTurista() * comercializaDto.getCantRutasDeVuelo()
					//* ((float) paquete.getDescuento() / 100);
			//costoAsociado += paquete.getCostoAsociado();
			
			float costoNuevaRutaNormal = rutaDeVuelo.getCostoTurista() * comercializaDto.getCantRutasDeVuelo();
			float costoNuevaRutaDescuento = costoNuevaRutaNormal * ((float) paquete.getDescuento() / 100);
			float costoAsociado = costoNuevaRutaNormal - costoNuevaRutaDescuento + paquete.getCostoAsociado();
			paquete.setCostoAsociado(costoAsociado);
		} else {
			//float costoAsociado = rutaDeVuelo.getCostoEjecutivo() * comercializaDto.getCantRutasDeVuelo()
					//* ((float) paquete.getDescuento() / 100);

			//costoAsociado += paquete.getCostoAsociado();
			
			float costoNuevaRutaNormal = rutaDeVuelo.getCostoEjecutivo() * comercializaDto.getCantRutasDeVuelo();
			float costoNuevaRutaDescuento = costoNuevaRutaNormal * ((float) paquete.getDescuento() / 100);
			float costoAsociado = costoNuevaRutaNormal - costoNuevaRutaDescuento + paquete.getCostoAsociado();
			paquete.setCostoAsociado(costoAsociado);

		}

		// Lo agrega en la ruta
		List<Comercializa> listRutaDeVueloComercializa = rutaDeVuelo.getListComercializa();
		listRutaDeVueloComercializa.add(comercializa);
	}

	private void validarPaqueteReserva(ReservaDto reservadto, Cliente cliente, RutaDeVuelo ruta) throws Exception {
		String paqueteNombre = reservadto.getPaquete();
		PaqueteManager manejadorPaquete = PaqueteManager.getInstance();

		if (paqueteNombre == null) {
			throw new Exception("Ingrese el nombre del paquete");
		}

		Paquete paquete = manejadorPaquete.findPaqueteByName(paqueteNombre);

		if (paquete == null) {
			throw new Exception("El paquete ingresado no existe en el sistema");
		}

		if (cliente.getComprasPaquete().get(paqueteNombre) == null) {
			throw new Exception("El cliente no compro el paquete seleccionado");
		}
		
		if (cliente.getComprasPaquete().get(paqueteNombre).getVencimiento().before(reservadto.getFechaReserva())) {
			throw new Exception("El paquete que va a utilizar para la reserva vence antes de la fecha de reserva ingresada");
		}

		boolean pertenece = false;
		int iRuta = 0;
		Comercializa rutaPaquete = null;
		while (iRuta < paquete.getListComercializa().size() && !pertenece) {
			pertenece = paquete.getListComercializa().get(iRuta).getRutaDeVuelo() == ruta;
			if (!pertenece) {
				iRuta++;
			}else {
				rutaPaquete = paquete.getListComercializa().get(iRuta);
			}
		}

		//rutaPaquete != null si no entra en el if
		if (!pertenece) {
			throw new Exception("La ruta seleccionada no forma parte del paquete");
		}
		
		if (rutaPaquete.getTipoDeAsiento() != reservadto.getTipoAsiento()) {
			throw new Exception("El tipo de asiento seleccionado no esta incluido en la ruta del paquete");
		}

		// El chequeo de la fecha de alta con la fecha de reserva se hace
		// implicitamente, ya que la fecha de compra del paquete es posterior a la fecha
		// de alta del paquete
		// Esto se tiene que haber hecho en el caso de uso de compra paquete
		if (reservadto.getFechaReserva().before(cliente.getComprasPaquete().get(paqueteNombre).getFechaCompra())) {
			throw new Exception(
					"La fecha de la reserva tiene que ser posterior a la fecha en la que se compro el paquete");
		}

		// Controlar los usos del paquete con la cantidad disponible
		int pasajesUsadosRuta = 0;
		for (Reserva reserva : cliente.getReservas().values()) {
			if (reserva.getCompraPaquete() != null && reserva.getCompraPaquete().getPaquete() == paquete) {
				pasajesUsadosRuta = pasajesUsadosRuta + reserva.getPasajeros().size();
			}
		}

		int cantidadTotalPasajesRuta = paquete.getListComercializa().get(iRuta).getCantRutasDeVuelo();

		if (pasajesUsadosRuta + reservadto.getPasajeros().size() > cantidadTotalPasajesRuta) {
			throw new Exception(
					"La cantidad de pasajes de la reserva exceden el limite de pasajes disponibles en el paquete para utilizar con esta ruta, quedan disponibles "
							+ (cantidadTotalPasajesRuta - pasajesUsadosRuta));
		}

	}

	private float calcularCostoReserva(ReservaDto reservadto, RutaDeVuelo rutaSistema, Integer descuento) {
		int cantidadPasajes = reservadto.getPasajeros().size();
		float costoEquipaje = rutaSistema.getCostoEquipajeExtra() * reservadto.getUniEquipajeExtra();
		// Se asume que el descuento pertenece al intervalo (0, 100)
		float descuentoAplicado = descuento / 100.0f;

		if (reservadto.getTipoAsiento() == TipoAsiento.Turista) {
			//return rutaSistema.getCostoTurista() * cantidadPasajes * descuentoAplicado + costoEquipaje;
			return (rutaSistema.getCostoTurista() * cantidadPasajes) * (1 - descuentoAplicado) + costoEquipaje;
			//return (rutaSistema.getCostoTurista() * cantidadPasajes + costoEquipaje) * (1 - descuentoAplicado);
		} else {
			//return rutaSistema.getCostoEjecutivo() * cantidadPasajes * descuentoAplicado + costoEquipaje;
			return (rutaSistema.getCostoEjecutivo() * cantidadPasajes) * (1 - descuentoAplicado) + costoEquipaje;
			//return (rutaSistema.getCostoEjecutivo() * cantidadPasajes + costoEquipaje) * (1 - descuentoAplicado);
		}
	}
	
	/*
	 * Se llama esta funcion desde VuelosController para reservar usando un Paquete
	 * El cliente y la ruta de vuelo ya estan validados al igual que todo lo de
	 * reserva con excepcion del paquete Todo lo relacionado al paquete se valida en
	 * este controlador y luego se crea la reserva. Que no se haya realizado una
	 * reserva usando el paquete tambien ya esta validado
	 */
	// Por el momento no se considera que el paquete precisa saber quien lo compro,
	// solo los clientes saben que compraron
	// No es necesario que la ruta conozca al paquete (ahora esta hecho de forma que
	// si lo conozca)
	public void reservarVueloPaquete(ReservaDto reservadto, Cliente clienteSistema, RutaDeVuelo rutaSistema,
			Vuelo vueloSistema) throws Exception {
		validarPaqueteReserva(reservadto, clienteSistema, rutaSistema);
		CompraPaquete compraPaquete = clienteSistema.getComprasPaquete().get(reservadto.getPaquete());

		float costoReserva = calcularCostoReserva(reservadto, rutaSistema, compraPaquete.getPaquete().getDescuento());
		reservadto.setCosto(costoReserva);
		
		Reserva nuevaReserva = new Reserva(reservadto, clienteSistema, vueloSistema, compraPaquete, null);
		clienteSistema.getReservas().put(reservadto.getVuelo(), nuevaReserva);
		vueloSistema.getClientes().put(reservadto.getCliente(), nuevaReserva);
		// La key de las reservas en compraPaquete es el nombre del vuelo
		compraPaquete.getReservas().put(reservadto.getVuelo(), nuevaReserva);
		
		for (Pasaje pasajero : nuevaReserva.getPasajeros()) {
			vueloSistema.agregarAsiento(pasajero);
		}
		
	}

	private void validarCompraPaqueteDto(CompraPaqueteDto compraPaquetedto) throws Exception {
		PaqueteManager manejadorPaquete = PaqueteManager.getInstance();
		Paquete paqueteSistema = manejadorPaquete.findPaqueteByName(compraPaquetedto.getPaquete());
		UserManager manejadorUsuario = UserManager.getInstance();
		Usuario usuarioSistema = manejadorUsuario.findUserByNickname(compraPaquetedto.getComprador());
		
		if (usuarioSistema instanceof Aerolinea) {
			throw new Exception("El comprador ingresado es una aerolinea en lugar de un cliente");
		}
		
		Cliente clienteSistema = (Cliente) manejadorUsuario.findUserByNickname(compraPaquetedto.getComprador());

		if (paqueteSistema == null) {
			throw new Exception("El nombre ingresado no pertenece a ningún paquete registrado en el sistema");
		}

		if (clienteSistema == null) {
			throw new Exception("El nickname ingresado no pertenece a ningún cliente registrado en el sistema");
		}

		if (clienteSistema.getComprasPaquete().containsKey(compraPaquetedto.getPaquete())) {
			throw new Exception("El cliente ingresado ya había comprado el paquete");
		}

		if (paqueteSistema.getCompradores().containsKey(compraPaquetedto.getComprador())) {
			throw new Exception("El paquete ingresado ya había registrado la compra del cliente");
		}

		if (paqueteSistema.getListComercializa().size() == 0) {
			throw new Exception("El paquete seleccionado no posee rutas de vuelo");
		}
		
		if (compraPaquetedto.getFechaCompra() == null) {
			throw new Exception("La fecha de compra es requerida");
		}

		if (compraPaquetedto.getFechaCompra().before(paqueteSistema.getFechaAlta())) {
			throw new Exception(
					"La fecha de compra ingresada es anterior a la fecha de alta del paquete en el sistema");
		}
		
		if (compraPaquetedto.getFechaCompra().before(clienteSistema.getFechaAlta())) {
			throw new Exception("La fecha de compra del paquete es anterior a la fecha de alta del cliente en el sistema");
		}

	}

	public void comprarPaquete(CompraPaqueteDto compraPaquetedto) throws Exception {
		validarCompraPaqueteDto(compraPaquetedto);

		PaqueteManager manejadorPaquete = PaqueteManager.getInstance();
		UserManager manejadorUsuario = UserManager.getInstance();
		Paquete paqueteSistema = manejadorPaquete.findPaqueteByName(compraPaquetedto.getPaquete());
		Cliente clienteSistema = (Cliente) manejadorUsuario.findUserByNickname(compraPaquetedto.getComprador());

		Date vencimiento = Utilidades.sumarDiasFecha(paqueteSistema.getPeriodoValidez(),
				compraPaquetedto.getFechaCompra());
		CompraPaquete compraPaqueteNuevo = new CompraPaquete(compraPaquetedto.getFechaCompra(), vencimiento,
				clienteSistema, paqueteSistema);
		paqueteSistema.getCompradores().put(compraPaquetedto.getComprador(), compraPaqueteNuevo);
		clienteSistema.getComprasPaquete().put(compraPaquetedto.getPaquete(), compraPaqueteNuevo);

	}

	@Override
	public PaqueteDto buscarPaquetePorNombre(String nombre) {
		PaqueteManager manejadorPaquete = PaqueteManager.getInstance();
		return manejadorPaquete.buscarPaquetePorNombre(nombre).toDto();
	}

}
