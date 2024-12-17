package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.controllers.Utilidades;
import uy.edu.fing.volandouy.controllers.VueloController;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.CategoriaDto;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.dto.PaqueteDto;
import uy.edu.fing.volandouy.dto.PasajeDto;
import uy.edu.fing.volandouy.dto.ReservaDto;
import uy.edu.fing.volandouy.dto.VueloDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;
import uy.edu.fing.volandouy.managers.PaqueteManager;
import uy.edu.fing.volandouy.managers.UserManager;

public class VueloControllerTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAgregarVuelo() {
		List<PasajeDto> pasajeros = new ArrayList<>();
		PasajeDto pasajero1dto = new PasajeDto("John", "Doe", null);
		PasajeDto pasajero2dto = new PasajeDto("Jane", "Doe", null);
		pasajeros.add(pasajero1dto);
		pasajeros.add(pasajero2dto);

		Date fecha = new Date();
		Date fechaAlta = new Date();
		Time hora2 = new Time(12, 43, 0);

		AerolineaDto aerolineadto = new AerolineaDto("AeroVC1", "nombreA", "emailVC1", "descripcionA", "websiteA", null,
				new Date(1924 - 1900, 0, 1), "Contra", null, null, null);
		Aerolinea aerolinea = new Aerolinea(aerolineadto);
		UserManager manejadorUsuario = UserManager.getInstance();
		manejadorUsuario.agregarUsuario(aerolinea);

		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("RutaVC1", "Descripción de la ruta", 100.0f, 200.0f, 50.0f, fechaAlta,
				aerolinea, new ArrayList<>(), hora2, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);

		Date fechaAltaRuta2 = new Date(2012 - 1900, 5, 1);
		RutaDeVuelo rutaDeVuelo2 = new RutaDeVuelo("RutaVC2", "Descripción de la ruta", 100.0f, 200.0f, 50.0f,
				fechaAltaRuta2, aerolinea, new ArrayList<>(), hora2, null, null, EstadoRuta.Confirmada, "Resumen",
				null, null, null);

		List<RutaDeVuelo> rutas = new ArrayList<>();
		rutas.add(rutaDeVuelo);
		rutas.add(rutaDeVuelo2);
		aerolinea.getRutasDeVuelo().put(rutaDeVuelo.getNombre(), rutaDeVuelo);
		aerolinea.getRutasDeVuelo().put(rutaDeVuelo2.getNombre(), rutaDeVuelo2);

		CategoriaDto categoriaDto = new CategoriaDto("CategoriaVC1");
		Categoria categoria = new Categoria(categoriaDto);
		categoria.setRutasDeVuelo(rutas);
		rutaDeVuelo.getCategorias().add(categoria);

		Time hora = new Time(12, 15, 0);

		VueloDto vuelodto = new VueloDto("VueloVC1", fecha, hora, 150, 50, fechaAlta, aerolinea.getNickName(),
				rutaDeVuelo.getNombre(), null, null);
		VueloDto vuelodtoNombreRepetido = new VueloDto("VueloVC1", fecha, hora, 150, 50, fechaAlta,
				aerolinea.getNickName(), rutaDeVuelo.getNombre(), null, null);
		VueloDto vuelodtoAerolineaInexistente = new VueloDto("VueloVCF2", fecha, hora, 150, 50, fechaAlta, null,
				rutaDeVuelo.getNombre(), null, null);
		VueloDto vuelodtoRutaInexistente = new VueloDto("VueloVCF3", fecha, hora, 150, 50, fechaAlta,
				aerolinea.getNickName(), null, null, null);
		Date fecha2 = new Date(2012 - 1900, 11, 2);
		Date fechaAlta2 = new Date(2011 - 1900, 11, 2);
		VueloDto vuelodtoFechaAnterior = new VueloDto("VueloVCF4", fecha2, hora, 150, 50, fechaAlta2,
				aerolinea.getNickName(), rutaDeVuelo2.getNombre(), null, null);
		VueloDto vuelodtoDuracionErronea = new VueloDto("VueloVCF5", fecha, new Time(0, 0, 0), 150, 50, fechaAlta,
				aerolinea.getNickName(), rutaDeVuelo.getNombre(), null, null);
		VueloDto vuelodtoCantEjecutivoTuristaMal = new VueloDto("VueloVCF6", fecha, hora, 0, 0, fechaAlta,
				aerolinea.getNickName(), rutaDeVuelo.getNombre(), null, null);
		VueloDto vuelodtoCantEjecutivoMal = new VueloDto("VueloVCF7", fecha, hora, 12, -2, fechaAlta,
				aerolinea.getNickName(), rutaDeVuelo.getNombre(), null, null);
		VueloDto vuelodtoCantTuristaMal = new VueloDto("VueloVCF8", fecha, hora, -4, 50, fechaAlta,
				aerolinea.getNickName(), rutaDeVuelo.getNombre(), null, null);
		Date fecha3 = new Date(2011 - 1900, 7, 2);
		Date fechaAlta3 = new Date(2012 - 1900, 11, 2);
		VueloDto vuelodtoFechaFechaAltaMal = new VueloDto("VueloVCF9", fecha3, hora, 150, 50, fechaAlta3,
				aerolinea.getNickName(), rutaDeVuelo2.getNombre(), null, null);
		VueloDto vuelodtoReservaNoPerteneceAerolinea = new VueloDto("VueloVCF10", fecha, hora, 150, 50, fechaAlta,
				aerolinea.getNickName(), "RutaInexistenteEnElSistema", null, null);

		IVueloController ivuelos = new VueloController();

		try {
			// Agregar un vuelo al sistema
			ivuelos.agregarVuelo(vuelodto);
			assertEquals(((rutaDeVuelo.getVuelos()).get(0)).getNombre(), "VueloVC1");
			// Agregar vuelo repetido, test de falla
			ivuelos.agregarVuelo(vuelodtoNombreRepetido);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El nombre del vuelo ingresado ya esta registrado en el sistema");
		}

		try {
			// Agregar vuelo en una aerolinea inexistente
			ivuelos.agregarVuelo(vuelodtoAerolineaInexistente);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "La aerolinea ingresada no existe en el sistema");
		}

		try {
			// Agregar un vuelo en una ruta inexistente
			ivuelos.agregarVuelo(vuelodtoRutaInexistente);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "No se ingreso el nombre de la ruta de vuelo");
		}

		try {
			// Agregar vuelo con fecha anterior a fechaAlta, test de falla
			ivuelos.agregarVuelo(vuelodtoFechaAnterior);
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"La fecha en la que se realiza el vuelo es anterior a la fecha de alta de la ruta del vuelo");
		}

		try {
			// Agregar un vuelo con una duracion de 0 o menor, test de falla
			ivuelos.agregarVuelo(vuelodtoDuracionErronea);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "La duracion ingresada es igual a 0");
		}

		try {
			// Agregar un vuelo con cantidad de asientos de ambos tipos igual a 0, test de
			// falla
			ivuelos.agregarVuelo(vuelodtoCantEjecutivoTuristaMal);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "La cantidad de asientos totales del avion es igual a 0");
		}

		try {
			// Agregar un vuelo con cantidad de asientos de ambos tipos igual a 0, test de
			// falla
			ivuelos.agregarVuelo(vuelodtoCantEjecutivoMal);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "La cantidad de asientos del tipo ejecutivo es menor a 0");
		}

		try {
			// Agregar un vuelo con cantidad de asientos de ambos tipos igual a 0, test de
			// falla
			ivuelos.agregarVuelo(vuelodtoCantTuristaMal);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "La cantidad de asientos del tipo turista es menor a 0");
		}

		try {
			// Agregar un vuelo con cantidad de asientos de ambos tipos igual a 0, test de
			// falla
			ivuelos.agregarVuelo(vuelodtoFechaFechaAltaMal);
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"La fecha en la que se realiza el vuelo es anterior a la fecha de alta del vuelo");
		}

		try {
			ivuelos.agregarVuelo(vuelodtoReservaNoPerteneceAerolinea);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "La ruta de vuelo ingresada no pertenece a la aerolinea ingresada");
		}

	}

	// Solo comparo los nombres porque los dtos ya estan verificados en VueloTest
	// que son iguales
	public boolean vuelosIguales(VueloDto vuelo1, VueloDto vuelo2) {
		return vuelo1.getNombre() == vuelo2.getNombre();
	}

	public boolean vuelosDtoContenidos(List<VueloDto> listPrincipal, List<VueloDto> listContenida) {
		int iPrincipal = 0;
		int iContenida = 0;

		while (iPrincipal <= listPrincipal.size() - 1 && iContenida <= listContenida.size() - 1) {
			VueloDto vueloPrincipal = listPrincipal.get(iPrincipal);
			VueloDto vueloContenido = listContenida.get(iContenida);
			if (vuelosIguales(vueloPrincipal, vueloContenido)) {
				iPrincipal = 0;
				iContenida++;
			} else {
				iPrincipal++;
			}
		}
		return iContenida >= listContenida.size();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testListarVuelo() {
		List<PasajeDto> pasajeros = new ArrayList<>();
		PasajeDto pasajero1dto = new PasajeDto("John", "Doe", null);
		PasajeDto pasajero2dto = new PasajeDto("Jane", "Doe", null);
		pasajeros.add(pasajero1dto);
		pasajeros.add(pasajero2dto);

		Date fecha = new Date();
		Date fechaAlta = new Date();
		Time hora2 = new Time(12, 30, 0);

		AerolineaDto aerolineadto = new AerolineaDto("AeroVC2", "nombreA", "emailVC2", "descripcionA", "websiteA", null,
				new Date(1924 - 1900, 0, 1), "Contra", null, null, null);
		Aerolinea aerolinea = new Aerolinea(aerolineadto);
		UserManager manejadorUsuario = UserManager.getInstance();
		manejadorUsuario.agregarUsuario(aerolinea);

		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("RutaVC3", "Descripción de la ruta", 100.0f, 200.0f, 50.0f, fechaAlta,
				aerolinea, new ArrayList<>(), hora2, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);

		Date fechaAltaRuta2 = new Date(2012 - 1900, 5, 1);
		RutaDeVuelo rutaDeVuelo2 = new RutaDeVuelo("RutaVC4", "Descripción de la ruta", 100.0f, 200.0f, 50.0f,
				fechaAltaRuta2, aerolinea, new ArrayList<>(), hora2, null, null, EstadoRuta.Confirmada, "Resumen",
				null, null, null);

		List<RutaDeVuelo> rutas = new ArrayList<>();
		rutas.add(rutaDeVuelo);
		rutas.add(rutaDeVuelo2);
		aerolinea.getRutasDeVuelo().put(rutaDeVuelo.getNombre(), rutaDeVuelo);
		aerolinea.getRutasDeVuelo().put(rutaDeVuelo2.getNombre(), rutaDeVuelo2);

		CategoriaDto categoriaDto = new CategoriaDto("CategoriaVC1");
		Categoria categoria = new Categoria(categoriaDto);
		categoria.setRutasDeVuelo(rutas);
		rutaDeVuelo.getCategorias().add(categoria);

		Time hora = new Time(12, 15, 0);

		// VueloDto vuelodto100 = new VueloDto("VueloVC100", fecha, 120, 150, 50,
		// fechaAlta, aerolinea.getNickName(), rutaDeVuelo.getNombre(), null);
		VueloDto vuelodto2222 = new VueloDto("VueloVC3000000", fecha, hora, 150, 50, fechaAlta, aerolinea.getNickName(),
				rutaDeVuelo.getNombre(), null, null);
		VueloDto vuelodto3333 = new VueloDto("VueloVC4000000000", fecha, hora, 150, 50, fechaAlta,
				aerolinea.getNickName(), rutaDeVuelo.getNombre(), null, null);
		VueloDto vuelodto4444 = new VueloDto("VueloVC50000000000", fecha, hora, 150, 50, fechaAlta,
				aerolinea.getNickName(), rutaDeVuelo.getNombre(), null, null);

		List<VueloDto> dataVuelos = new ArrayList<>();
		// dataVuelos.add(vuelodto100);
		dataVuelos.add(vuelodto2222);
		dataVuelos.add(vuelodto3333);
		dataVuelos.add(vuelodto4444);

		IVueloController ivuelos = new VueloController();
		try {
			// ivuelos.agregarVuelo(vuelodto100);
			ivuelos.agregarVuelo(vuelodto2222);
			ivuelos.agregarVuelo(vuelodto3333);
			ivuelos.agregarVuelo(vuelodto4444);
			assertTrue(vuelosDtoContenidos(ivuelos.listarVuelo(aerolinea.getNickName(), rutaDeVuelo.getNombre()),
					dataVuelos));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testReservarVuelo() {
		List<PasajeDto> pasajeros = new ArrayList<>();
		PasajeDto pasajero1dto = new PasajeDto("John", "Doe", null);
		PasajeDto pasajero2dto = new PasajeDto("Jane", "Doe", null);
		pasajeros.add(pasajero1dto);
		pasajeros.add(pasajero2dto);

		Date fechaAlta = new Date(2018 - 1900, 10, 3);
		Time hora2 = new Time(10, 2, 0);

		AerolineaDto aerolineadto = new AerolineaDto("AeroVCR1", "nombreA", "emailVCR1", "descripcionA", "websiteA",
				null, new Date(1924 - 1900, 0, 1), "Contra", null, null, null);
		Aerolinea aerolinea = new Aerolinea(aerolineadto);

		ClienteDto clientedto1 = new ClienteDto("ClienteVCR1", "nombreC", "ClienteEmailVCR1", "apellidoC", new Date(),
				TipoDocumento.CI, "5342342-2", "NacionalidadC", null, null, new Date(1924 - 1900, 0, 1), "Contra",
				null, null, null);
		Cliente cliente1 = new Cliente(clientedto1);

		ClienteDto clientedto2 = new ClienteDto("ClienteVCR2", "nombreC", "ClienteEmailVCR2", "apellidoC", new Date(),
				TipoDocumento.CI, "5342342-2", "NacionalidadC", null, null, new Date(1924 - 1900, 0, 1), "Contra",
				null, null, null);
		Cliente cliente2 = new Cliente(clientedto2);

		UserManager manejadorUsuario = UserManager.getInstance();
		manejadorUsuario.agregarUsuario(aerolinea);
		manejadorUsuario.agregarUsuario(cliente1);
		manejadorUsuario.agregarUsuario(cliente2);

		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("RutaVCR1", "Descripción de la ruta", 100.0f, 200.0f, 50.0f,
				fechaAlta, aerolinea, new ArrayList<>(), hora2, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);

		RutaDeVuelo rutaDeVuelo2 = new RutaDeVuelo("RutaVCR2", "Descripción de la ruta", 100.0f, 200.0f, 50.0f,
				fechaAlta, aerolinea, new ArrayList<>(), hora2, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);

		List<RutaDeVuelo> rutas = new ArrayList<>();
		rutas.add(rutaDeVuelo);
		rutas.add(rutaDeVuelo2);
		aerolinea.getRutasDeVuelo().put(rutaDeVuelo.getNombre(), rutaDeVuelo);
		aerolinea.getRutasDeVuelo().put(rutaDeVuelo2.getNombre(), rutaDeVuelo2);

		CategoriaDto categoriaDto = new CategoriaDto("CategoriaVCR1");
		Categoria categoria = new Categoria(categoriaDto);
		categoria.setRutasDeVuelo(rutas);
		rutaDeVuelo.getCategorias().add(categoria);

		Date fecha = new Date(2018 - 1900, 10, 5);
		Date fechaAltaVuelo = new Date(2018 - 1900, 10, 4);

		Time hora = new Time(12, 15, 0);

		VueloDto vuelodto = new VueloDto("VueloVCR1", fecha, hora, 150, 50, fechaAltaVuelo, aerolinea.getNickName(),
				rutaDeVuelo.getNombre(), null, null);
		VueloDto vuelodto2 = new VueloDto("VueloVCR2", fecha, hora, 150, 50, fechaAltaVuelo, aerolinea.getNickName(),
				rutaDeVuelo.getNombre(), null, null);
		VueloDto vuelodto3 = new VueloDto("VueloVCR3", fecha, hora, 1, 0, fechaAltaVuelo, aerolinea.getNickName(),
				rutaDeVuelo2.getNombre(), null, null);
		VueloDto vuelodto4 = new VueloDto("VueloVCR4", fecha, hora, 2, 10, fechaAltaVuelo, aerolinea.getNickName(),
				rutaDeVuelo2.getNombre(), null, null);

		IVueloController ivuelos = new VueloController();

		Date fechaReserva = new Date(2018 - 1900, 10, 5);

		// El vuelo no existe en el sistema
		try {
			ReservaDto reservadto = new ReservaDto(fechaReserva, TipoAsiento.Turista, 0, 0, pasajeros,
					clientedto1.getNickName(), aerolinea.getNickName(), rutaDeVuelo.getNombre(), vuelodto.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El vuelo ingresado no se encuentra registrado en el sistema");
		}

		// Cliente no registrado en el sistema
		try {
			ivuelos.agregarVuelo(vuelodto2);
			ReservaDto reservadto = new ReservaDto(fechaReserva, TipoAsiento.Ejecutivo, 0, 0, pasajeros,
					"ClienteInexistente1", aerolinea.getNickName(), rutaDeVuelo.getNombre(), vuelodto2.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El cliente ingresado no se encuentra registrado en el sistema");
		}

		// Reserva sin paquete exitosa
		try {
			ivuelos.agregarVuelo(vuelodto);

			ReservaDto reservadto = new ReservaDto(fechaReserva, TipoAsiento.Turista, 0, 0, pasajeros,
					clientedto1.getNickName(), aerolinea.getNickName(), rutaDeVuelo.getNombre(), vuelodto.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto);

			Vuelo vuelo1Ruta = null;
			for (Vuelo vuelos : rutaDeVuelo.getVuelos()) {
				if (vuelos.getNombre() == vuelodto.getNombre()) {
					vuelo1Ruta = vuelos;
				}
			}

			assertTrue(cliente1.getReservas().get(vuelodto.getNombre()) != null);
			assertEquals(cliente1, vuelo1Ruta.getClientes().get(cliente1.getNickName()).getCliente());
			assertEquals(cliente1.getReservas().get(vuelodto.getNombre()).getVuelo(), vuelo1Ruta);

			ReservaDto reservadto2 = new ReservaDto(fechaReserva, TipoAsiento.Ejecutivo, 0, 0, pasajeros,
					clientedto2.getNickName(), aerolinea.getNickName(), rutaDeVuelo.getNombre(), vuelodto.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Cliente ya habia hecho reserva
		try {
			ReservaDto reservadto = new ReservaDto(fechaReserva, TipoAsiento.Turista, 0, 0, pasajeros,
					clientedto1.getNickName(), aerolinea.getNickName(), rutaDeVuelo.getNombre(), vuelodto.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El cliente seleccionado ya realizo una reserva para este vuelo previamente");
		}

		// No hay asientos ejecutivos disponibles
		try {
			ivuelos.agregarVuelo(vuelodto3);
			ReservaDto reservadto2 = new ReservaDto(fechaReserva, TipoAsiento.Ejecutivo, 0, 0, pasajeros,
					clientedto2.getNickName(), aerolinea.getNickName(), rutaDeVuelo2.getNombre(), vuelodto3.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto2);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "No queda lugar disponible en el avion para la cantidad de pasajes ingresada");
		}

		// No hay asientos turistas disponibles porque ya estan ocupados
		try {
			ivuelos.agregarVuelo(vuelodto4);
			ReservaDto reservadto = new ReservaDto(fechaReserva, TipoAsiento.Turista, 0, 0, pasajeros,
					clientedto1.getNickName(), aerolinea.getNickName(), rutaDeVuelo2.getNombre(), vuelodto4.getNombre(),
					null, null, null, null);
			ReservaDto reservadto2 = new ReservaDto(fechaReserva, TipoAsiento.Turista, 0, 0, pasajeros,
					clientedto2.getNickName(), aerolinea.getNickName(), rutaDeVuelo2.getNombre(), vuelodto4.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto);
			ivuelos.reservarVuelo(reservadto2);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "No queda lugar disponible en el avion para la cantidad de pasajes ingresada");
		}

		// No se ingreso ningun pasajero (null)
		try {
			ReservaDto reservadto2 = new ReservaDto(fechaReserva, TipoAsiento.Turista, 0, 0, null,
					clientedto2.getNickName(), aerolinea.getNickName(), rutaDeVuelo2.getNombre(), vuelodto4.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto2);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "No se ingreso ningun pasajero para la reserva del vuelo");
		}

		// No se ingreso ningun pasajero (List vacia)
		try {
			ReservaDto reservadto2 = new ReservaDto(fechaReserva, TipoAsiento.Turista, 0, 0, new ArrayList<>(),
					clientedto2.getNickName(), aerolinea.getNickName(), rutaDeVuelo2.getNombre(), vuelodto4.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto2);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "No se ingreso ningun pasajero para la reserva del vuelo");
		}

		try {
			// Retorna el anio - 1900, por eso no va el -1900
			Date fechaReservaPosterior = new Date(vuelodto4.getFecha().getYear() + 1, 11, 2);
			ReservaDto reservadto2 = new ReservaDto(fechaReservaPosterior, TipoAsiento.Ejecutivo, 0, 0, pasajeros,
					clientedto2.getNickName(), aerolinea.getNickName(), rutaDeVuelo2.getNombre(), vuelodto4.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto2);
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"La fecha de la reserva ingresada en posterior a la fecha programada del vuelo");
		}

		VueloDto vuelodto5 = new VueloDto("VueloVCR5", fecha, hora, 2, 50, fechaAltaVuelo, aerolinea.getNickName(),
				rutaDeVuelo2.getNombre(), null, null);
		List<PasajeDto> pasajerosVueloDto5 = new ArrayList<>();
		PasajeDto pasajero1Vuelo5 = new PasajeDto("", "Doe", null);
		PasajeDto pasajero2Vuelo5 = new PasajeDto("Jane", "", null);
		pasajerosVueloDto5.add(pasajero1Vuelo5);
		pasajerosVueloDto5.add(pasajero2Vuelo5);

		// Nombre invalido en pasajero 1 y apellido invalido en pasajero 2
		try {
			ivuelos.agregarVuelo(vuelodto5);

			ReservaDto reservadto5 = new ReservaDto(fechaReserva, TipoAsiento.Turista, 0, 0, pasajerosVueloDto5,
					clientedto1.getNickName(), aerolinea.getNickName(), rutaDeVuelo2.getNombre(), vuelodto5.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto5);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Falta ingresar el nombre de uno o mas pasajeros");
		}

		pasajerosVueloDto5.get(0).setNombre("Arthur");

		// Apellido invalido en pasajero 1
		try {
			ReservaDto reservadto5 = new ReservaDto(fechaReserva, TipoAsiento.Turista, 0, 0, pasajerosVueloDto5,
					clientedto1.getNickName(), aerolinea.getNickName(), rutaDeVuelo2.getNombre(), vuelodto5.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto5);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Falta ingresar el apellido de uno o mas pasajeros");
		}

		pasajerosVueloDto5.get(0).setNombre(null);

		// Nombre vacio en pasajero 1 y apellido invalido en pasajero 2
		try {
			ReservaDto reservadto5 = new ReservaDto(fechaReserva, TipoAsiento.Turista, 0, 0, pasajerosVueloDto5,
					clientedto1.getNickName(), aerolinea.getNickName(), rutaDeVuelo2.getNombre(), vuelodto5.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto5);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Falta ingresar el nombre de uno o mas pasajeros");
		}

		pasajerosVueloDto5.get(0).setNombre("Arthur");
		pasajerosVueloDto5.get(1).setApellido(null);

		// Apellido vacio en pasajero 2
		try {
			ReservaDto reservadto5 = new ReservaDto(fechaReserva, TipoAsiento.Turista, 0, 0, pasajerosVueloDto5,
					clientedto1.getNickName(), aerolinea.getNickName(), rutaDeVuelo2.getNombre(), vuelodto5.getNombre(),
					null, null, null, null);
			ivuelos.reservarVuelo(reservadto5);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Falta ingresar el apellido de uno o mas pasajeros");
		}

		pasajerosVueloDto5.get(1).setApellido("Smith");
		PaqueteDto paqueteDtoReservaExitosa = new PaqueteDto("PaqueteReservaExitosa", "Desc", 4, 20, fechaAlta, 200, null, null, null);
		Paquete paqueteVuelo5= new Paquete(paqueteDtoReservaExitosa);
		PaqueteManager manejadorPaquete = PaqueteManager.getInstance();
		manejadorPaquete.addPaquete(paqueteVuelo5);

		Comercializa paqueteReservaExitosaRuta2 = new Comercializa(2, TipoAsiento.Turista, rutaDeVuelo2, paqueteVuelo5);
		paqueteVuelo5.getListComercializa().add(paqueteReservaExitosaRuta2);
		rutaDeVuelo2.getListComercializa().add(paqueteReservaExitosaRuta2);

		// la fecha tiene que coincidir con la de la compraPaqueteExitoso
		Date fechaVencimientoPaqueteExitoso = Utilidades.sumarDiasFecha(paqueteVuelo5.getPeriodoValidez(), fechaAlta);
		CompraPaquete compraPaqueteExitoso = new CompraPaquete(fechaAlta, fechaVencimientoPaqueteExitoso, cliente1,
				paqueteVuelo5);
		cliente1.getComprasPaquete().put(paqueteVuelo5.getNombre(), compraPaqueteExitoso);

		try {
			ReservaDto reservadto5 = new ReservaDto(fechaReserva, TipoAsiento.Turista, 0, 0, pasajerosVueloDto5,
					clientedto1.getNickName(), aerolinea.getNickName(), rutaDeVuelo2.getNombre(), vuelodto5.getNombre(),
					paqueteVuelo5.getNombre(), null, null, null);
			ivuelos.reservarVuelo(reservadto5);
			assertEquals(compraPaqueteExitoso, cliente1.getReservas().get(vuelodto5.getNombre()).getCompraPaquete());
			assertEquals(cliente1.getReservas().get(vuelodto5.getNombre()),
					compraPaqueteExitoso.getReservas().get(vuelodto5.getNombre()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * try {
		 * 
		 * }catch (Exception e) { assertEquals(); }
		 */

	}

}
