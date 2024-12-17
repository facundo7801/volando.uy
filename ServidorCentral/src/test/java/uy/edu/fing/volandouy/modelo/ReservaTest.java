package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.CategoriaDto;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.dto.PaqueteDto;
import uy.edu.fing.volandouy.dto.PasajeDto;
import uy.edu.fing.volandouy.dto.ReservaDto;
import uy.edu.fing.volandouy.dto.VueloDto;
import uy.edu.fing.volandouy.dto.CompraPaqueteDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;

public class ReservaTest {

	boolean pasajeroEnReservaDto(ReservaDto reservadtoPrueba, List<PasajeDto> pasajeros) {
		boolean[] visitados = new boolean[reservadtoPrueba.getPasajeros().size()];
		for (boolean visita : visitados) {
			visita = false;
		}

		int iReserva = 0;
		int iPasajeros = 0;
		List<PasajeDto> pasajesReserva = reservadtoPrueba.getPasajeros();

		while (iPasajeros <= pasajeros.size() - 1 && iReserva <= pasajesReserva.size() - 1) {
			PasajeDto pasajeReserva = pasajesReserva.get(iReserva);
			PasajeDto pasajeDto = pasajeros.get(iPasajeros);
			if (pasajeReserva.getNombre() == pasajeDto.getNombre()
					&& pasajeReserva.getApellido() == pasajeDto.getApellido() && !visitados[iReserva]) {
				visitados[iReserva] = true;
				iReserva = 0;
				iPasajeros++;
			} else {
				iReserva++;
			}
		}

		return iPasajeros >= pasajeros.size();

	}

	boolean pasajeroEnReserva(Reserva reserva, List<PasajeDto> pasajeros) {
		boolean[] visitados = new boolean[reserva.getPasajeros().size()];
		for (boolean visita : visitados) {
			visita = false;
		}

		int iReserva = 0;
		int iPasajeros = 0;
		List<Pasaje> pasajesReserva = reserva.getPasajeros();

		while (iPasajeros <= pasajeros.size() - 1 && iReserva <= pasajesReserva.size() - 1) {
			Pasaje pasajeReserva = pasajesReserva.get(iReserva);
			PasajeDto pasajeDto = pasajeros.get(iPasajeros);
			if (pasajeReserva.getNombre() == pasajeDto.getNombre()
					&& pasajeReserva.getApellido() == pasajeDto.getApellido() && !visitados[iReserva]) {
				visitados[iReserva] = true;
				iReserva = 0;
				iPasajeros++;
			} else {
				iReserva++;
			}
		}

		return iPasajeros >= pasajeros.size();

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testReservaToDto() {
		Time hora = new Time(0, 0, 0);
		List<PasajeDto> pasajeros = new ArrayList<>();
		PasajeDto pasajero1dto = new PasajeDto("John", "Doe", null);
		PasajeDto pasajero2dto = new PasajeDto("Jane", "Doe", null);
		pasajeros.add(pasajero1dto);
		pasajeros.add(pasajero2dto);

		Date fecha = new Date();
		Date fechaAlta = new Date();

		AerolineaDto aerolineadto = new AerolineaDto("Aero1", "nombreA", "emailA", "descripcionA", "websiteA", null,
				new Date(1924 - 1900, 0, 1), "Contra", null, null, null);
		Aerolinea aerolinea = new Aerolinea(aerolineadto);

		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("Ruta1", "Descripción de la ruta", 100.0f, 200.0f, 50.0f, fechaAlta,
				aerolinea, new ArrayList<>(), hora, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);
		List<RutaDeVuelo> rutas = new ArrayList<>();
		rutas.add(rutaDeVuelo);
		aerolinea.getRutasDeVuelo().put(rutaDeVuelo.getNombre(), rutaDeVuelo);

		CategoriaDto categoriaDto = new CategoriaDto("Categoria1");
		Categoria categoria = new Categoria(categoriaDto);
		categoria.setRutasDeVuelo(rutas);
		rutaDeVuelo.getCategorias().add(categoria);

		VueloDto vuelodto = new VueloDto("Vuelo1", fecha, new Time(12, 15, 0), 150, 50, fechaAlta,
				aerolinea.getNickName(), rutaDeVuelo.getNombre(), null, null);
		Vuelo vuelo = new Vuelo(vuelodto, rutaDeVuelo);
		rutaDeVuelo.getVuelos().add(vuelo);

		ClienteDto clientedto = new ClienteDto("nick", "nombre", "email", "apellido", new Date(), TipoDocumento.CI,
				"1234", "nacionalidad", null, null, new Date(1924 - 1900, 0, 1), "Contra", null, null, null);
		Cliente cliente = new Cliente(clientedto);

		PaqueteDto paquetedto = new PaqueteDto("paquete1", "descripcion", 2, 20, new Date(), 150.0f, null, null, null);
		Paquete paquete = new Paquete(paquetedto);
		CompraPaquete compra = new CompraPaquete(new Date(), new Date(), cliente, paquete);
		cliente.getComprasPaquete().put(paquete.getNombre(), compra);

		ReservaDto reservadto = new ReservaDto(new Date(), TipoAsiento.Turista, 2, 150.0f, pasajeros,
				cliente.getNickName(), aerolinea.getNickName(), rutaDeVuelo.getNombre(), vuelo.getNombre(), null, null, null, null);
		Reserva reserva = new Reserva(reservadto, cliente, vuelo, compra, null);
		// Falta vincular la CompraPaquete con la Reserva
		vuelo.getClientes().put(cliente.getNickName(), reserva);

		ReservaDto reservadtoPrueba = reserva.toDto();

		assertEquals(reserva.getFechaReserva(), reservadtoPrueba.getFechaReserva());
		assertEquals(reserva.getTipoAsiento(), reservadtoPrueba.getTipoAsiento());
		assertEquals(reserva.getUniEquipajeExtra(), reservadtoPrueba.getUniEquipajeExtra());
		assertEquals(reserva.getCosto(), reservadtoPrueba.getCosto(), 0.01);
		assertEquals(reserva.getPasajeros().size(), reservadtoPrueba.getPasajeros().size());
		assertEquals(reserva.getCliente().getNickName(), reservadtoPrueba.getCliente());
		assertEquals(reserva.getVuelo().getNombre(), reservadtoPrueba.getVuelo());
		assertEquals(reserva.getCompraPaquete().getPaquete().getNombre(), reservadtoPrueba.getPaquete());
		assertEquals(aerolinea.getNickName(), reservadtoPrueba.getAerolinea());
		assertEquals(vuelo.getRutaDeVuelo().getNombre(), reservadtoPrueba.getRuta());
		reservadtoPrueba.setCosto(198);
		assertNotEquals(reserva.getCosto(), reservadtoPrueba.getCosto());
		assertEquals(reserva.getPasajeros().size(), pasajeros.size());
		assertTrue(pasajeroEnReservaDto(reservadtoPrueba, pasajeros));
		pasajeros.add(pasajero2dto);
		PasajeDto pasajero3dto = new PasajeDto("Jane", "Rich", null);
		pasajeros.add(pasajero3dto);
		assertFalse(pasajeroEnReservaDto(reservadtoPrueba, pasajeros));
		
		Cliente comprador = compra.getComprador();
		CompraPaqueteDto dataCompra = compra.toDto();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testReservaNombrePasajeros() {
		Time hora = new Time(0, 0, 0);
		List<PasajeDto> pasajeros = new ArrayList<>();
		PasajeDto pasajero1dto = new PasajeDto("John", "Doe", null);
		PasajeDto pasajero2dto = new PasajeDto("Jane", "Doe", null);
		pasajeros.add(pasajero1dto);
		pasajeros.add(pasajero2dto);

		Date fecha = new Date();
		Date fechaAlta = new Date();

		AerolineaDto aerolineadto = new AerolineaDto("Aero1", "nombreA", "emailA", "descripcionA", "websiteA", null,
				new Date(1924 - 1900, 0, 1), "Contra", null, null, null);
		Aerolinea aerolinea = new Aerolinea(aerolineadto);

		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("Ruta1", "Descripción de la ruta", 100.0f, 200.0f, 50.0f, fechaAlta,
				aerolinea, new ArrayList<>(), hora, null, null, EstadoRuta.Confirmada, "Resumen", null, null, null);
		List<RutaDeVuelo> rutas = new ArrayList<>();
		rutas.add(rutaDeVuelo);
		aerolinea.getRutasDeVuelo().put(rutaDeVuelo.getNombre(), rutaDeVuelo);

		CategoriaDto categoriaDto = new CategoriaDto("Categoria1");
		Categoria categoria = new Categoria(categoriaDto);
		categoria.setRutasDeVuelo(rutas);
		rutaDeVuelo.getCategorias().add(categoria);

		VueloDto vuelodto = new VueloDto("Vuelo1", fecha, new Time(12, 15, 0), 150, 50, fechaAlta,
				aerolinea.getNickName(), rutaDeVuelo.getNombre(), null, null);
		Vuelo vuelo = new Vuelo(vuelodto, rutaDeVuelo);
		rutaDeVuelo.getVuelos().add(vuelo);

		ClienteDto clientedto = new ClienteDto("nick", "nombre", "email", "apellido", new Date(), TipoDocumento.CI,
				"1234", "nacionalidad", null, null, new Date(1924 - 1900, 0, 1), "Contra", null, null, null);
		Cliente cliente = new Cliente(clientedto);

		PaqueteDto paquetedto = new PaqueteDto("paquete1", "descripcion", 2, 20, new Date(), 150.0f, null, null, null);
		Paquete paquete = new Paquete(paquetedto);
		CompraPaquete compra = new CompraPaquete(new Date(), new Date(), cliente, paquete);
		cliente.getComprasPaquete().put(paquete.getNombre(), compra);

		Date fechaReserva = new Date();

		ReservaDto reservadto = new ReservaDto(fechaReserva, TipoAsiento.Turista, 2, 150.0f, pasajeros,
				cliente.getNickName(), aerolinea.getNickName(), rutaDeVuelo.getNombre(), vuelo.getNombre(), null, null, null, null);
		Reserva reserva = new Reserva(reservadto, cliente, vuelo, compra, null);
		// Falta vincular la CompraPaquete con la Reserva
		vuelo.getClientes().put(cliente.getNickName(), reserva);
		compra.getReservas().put(vuelo.getNombre(), reserva);
		
		CompraPaqueteDto compraDto = compra.toDto();

		assertEquals(reserva.getFechaReserva(), fechaReserva);
		assertEquals(reserva.getTipoAsiento(), TipoAsiento.Turista);
		assertEquals(reserva.getUniEquipajeExtra(), 2);
		assertEquals(reserva.getCosto(), 150.0f, 0.01);
		assertEquals(reserva.getPasajeros().size(), pasajeros.size());
		assertEquals(reserva.getCliente().getNickName(), cliente.getNickName());
		assertEquals(reserva.getVuelo().getNombre(), vuelo.getNombre());
		assertEquals(reserva.getCompraPaquete(), compra);
		assertEquals(reserva.getPasajeros().size(), pasajeros.size());
		assertTrue(pasajeroEnReserva(reserva, pasajeros));
		pasajeros.add(pasajero2dto);
		PasajeDto pasajero3dto = new PasajeDto("Jane", "Rich", null);
		pasajeros.add(pasajero3dto);
		assertFalse(pasajeroEnReserva(reserva, pasajeros));
		
		reserva.getEmbarqueUrl();
		reserva.setEmbarqueUrl(null);
		reserva.getFechaCheckIn();
		reserva.setFechaCheckIn(null);
	}
}
