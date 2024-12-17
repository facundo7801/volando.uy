package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uy.edu.fing.volandouy.controllers.IPaqueteController;
import uy.edu.fing.volandouy.controllers.PaqueteController;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.CiudadDto;
import uy.edu.fing.volandouy.dto.ComercializaDto;
import uy.edu.fing.volandouy.dto.PaqueteDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;
import uy.edu.fing.volandouy.managers.PaqueteManager;
import uy.edu.fing.volandouy.managers.UserManager;
import uy.edu.fing.volandouy.managers.VueloManager;

public class PaqueteControllerTest {

	private Ciudad ciudadO;
	private Ciudad ciudadD;
	private Time hora;

	@SuppressWarnings("deprecation")
	@Before
	public void setDatos() {
		Date fechaAltaC = new Date();
		CiudadDto ciudadDtoO = new CiudadDto("Montevideo", "Uruguay", "Aeropuerto Internacional de Carrasco",
				"Capital del país", "www.montevideo.gub.uy", fechaAltaC);

		Ciudad ciudadO = new Ciudad(ciudadDtoO);

		CiudadDto ciudadDtoD = new CiudadDto("Montevideo", "Uruguay", "Aeropuerto Internacional de Carrasco",
				"Capital del país", "www.montevideo.gub.uy", fechaAltaC);

		Ciudad ciudadD = new Ciudad(ciudadDtoD);

		Time hora = new Time(2, 3, 0);

		VueloManager manejadorVuelo = VueloManager.getInstance();
		try {
			manejadorVuelo.agregarCiudad(ciudadD);
			manejadorVuelo.agregarCiudad(ciudadO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.ciudadO = ciudadO;
		this.ciudadD = ciudadD;
		this.hora = hora;

	}

	private boolean paqueteEnList(List<Paquete> list, String paqueteNombre) {
		for (Paquete paquete : list) {
			if (paquete.getNombre().equals(paqueteNombre)) {
				return true;
			}
		}

		return false;

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCrearPaquete() {
		Date fecha1 = new Date(2012 - 1900, 11, 2);
		PaqueteDto paqueteDto = new PaqueteDto("PaquetePrueba1", "Descripcion de Paquete de Prueba 1", 5, 20, fecha1,
				200, null, null, null);
		PaqueteDto paqueteDtoNombreRepetido = new PaqueteDto("PaquetePrueba1",
				"Descripcion de Paquete con nombre repetido 1", 10, 20, fecha1, 200, null, null, null);
		PaqueteDto paqueteDtoSinNombre = new PaqueteDto("", "Descripcion de Paquete sin nombre 1", 10, 20, fecha1, 200, null, null, null);
		PaqueteDto paqueteDtoSinDescripcion = new PaqueteDto("PaquetePruebaSinDescripcion", "", 10, 20, fecha1, 200, null, null, null);
		PaqueteDto paqueteDtoSinPeriodoDeValidez = new PaqueteDto("PaquetePruebaSinPeriodoDeValidez",
				"Descripcion de Paquete sin periodo de validez 1", null, 20, fecha1, 200, null, null, null);
		PaqueteDto paqueteDtoConPeriodoDeValidezNegativo = new PaqueteDto("PaquetePruebaConPeriodoDeValidezNegativo",
				"Descripcion de Paquete con periodo de validez negativo 1", -10, 20, fecha1, 200, null, null, null);
		PaqueteDto paqueteDtoSinDescuento = new PaqueteDto("PaquetePruebaSinDescuento",
				"Descripcion de Paquete sin descuento 1", 10, null, fecha1, 200, null, null, null);
		PaqueteDto paqueteDtoConDescuentoNegativo = new PaqueteDto("PaquetePruebaConDescuentooNegativo",
				"Descripcion de Paquete con descuento negativo 1", 10, -20, fecha1, 200, null, null, null);
		PaqueteDto paqueteDtoConDescuentoMayor = new PaqueteDto("PaquetePruebaConDescuentooMayor",
				"Descripcion de Paquete con descuento mayor 1", 10, 150, fecha1, 200, null, null, null);
		PaqueteDto paqueteDtoSinFecha = new PaqueteDto("PaquetePruebaSinFecha", "Descripcion de Paquete sin fecha 1",
				10, 20, null, 200, null, null, null);

		PaqueteManager manejadorPaquete = PaqueteManager.getInstance();
		IPaqueteController paqueteCtrl = new PaqueteController();

		try {
			// Agregar un paquete al sistema
			paqueteCtrl.crearPaqueteDeRutaDeVuelo(paqueteDto);
			List<Paquete> paquetes = manejadorPaquete.getPaquetes();
			assertTrue(paqueteEnList(paquetes, paqueteDto.getNombre()));
			// assertEquals(paquetes.get(0).getNombre(), "PaquetePrueba1");
			// Agregar paquete repetido, test de falla
			paqueteCtrl.crearPaqueteDeRutaDeVuelo(paqueteDtoNombreRepetido);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Ya existe un paquete con el mismo nombre");
		}

		try {
			// Agregar un paquete sin nombre
			paqueteCtrl.crearPaqueteDeRutaDeVuelo(paqueteDtoSinNombre);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El nombre del paquete es requerido");
		}

		try {
			// Agregar un paquete sin descripcion
			paqueteCtrl.crearPaqueteDeRutaDeVuelo(paqueteDtoSinDescripcion);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "La descripción del paquete es requerida");
		}

		try {
			// Agregar un paquete sin periodo de validez
			paqueteCtrl.crearPaqueteDeRutaDeVuelo(paqueteDtoSinPeriodoDeValidez);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El período de validez es requerido");
		}
		try {
			// Agregar un paquete con periodo de validez negativo
			paqueteCtrl.crearPaqueteDeRutaDeVuelo(paqueteDtoConPeriodoDeValidezNegativo);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El período de validez debe ser positivo");
		}
		try {
			// Agregar un paquete sin descuento
			paqueteCtrl.crearPaqueteDeRutaDeVuelo(paqueteDtoSinDescuento);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El descuento es requerido");
		}
		try {
			// Agregar un paquete con descuento negativo
			paqueteCtrl.crearPaqueteDeRutaDeVuelo(paqueteDtoConDescuentoNegativo);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El descuento debe ser positivo");
		}
		try {
			// Agregar un paquete con descuento mayor a 100
			paqueteCtrl.crearPaqueteDeRutaDeVuelo(paqueteDtoConDescuentoMayor);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El descuento debe ser menor o igual a 100");
		}
		try {
			// Agregar un paquete sin fecha
			paqueteCtrl.crearPaqueteDeRutaDeVuelo(paqueteDtoSinFecha);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "La fecha de alta es requerida");
		}
	}

	// Solo comparo los nombres porque los dtos ya estan verificados en VueloTest
	// que son iguales
	public boolean paquetesIguales(PaqueteDto paquete1, PaqueteDto paquete2) {
		return paquete1.getNombre() == paquete2.getNombre();
	}

	public boolean paqueteDtoContenidos(List<PaqueteDto> listPrincipal, List<PaqueteDto> listContenida) {
		int iPrincipal = 0;
		int iContenida = 0;

		while (iPrincipal <= listPrincipal.size() - 1 && iContenida <= listContenida.size() - 1) {
			PaqueteDto paquetePrincipal = listPrincipal.get(iPrincipal);
			PaqueteDto paqueteContenido = listContenida.get(iContenida);
			if (paquetesIguales(paquetePrincipal, paqueteContenido)) {
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
		Date fecha = new Date(2020 - 1900, 11, 2);
		PaqueteDto paqueteDto = new PaqueteDto("Paquete1", "Descripcion Paquete 1", 11, 50, fecha, 120, null, null, null);
		PaqueteDto paqueteDto2 = new PaqueteDto("Paquete2", "Descripcion Paquete 2", 11, 50, fecha, 120, null, null, null);
		PaqueteDto paqueteDto3 = new PaqueteDto("Paquete3", "Descripcion Paquete 3", 11, 50, fecha, 120, null, null, null);
		PaqueteDto paqueteDto4 = new PaqueteDto("Paquete4", "Descripcion Paquete 4", 11, 50, fecha, 120, null, null, null);

		List<PaqueteDto> dataPaquetes = new ArrayList<>();
		dataPaquetes.add(paqueteDto);
		dataPaquetes.add(paqueteDto2);
		dataPaquetes.add(paqueteDto3);
		dataPaquetes.add(paqueteDto4);

		IPaqueteController iPaqueteController = new PaqueteController();
		try {
			iPaqueteController.crearPaqueteDeRutaDeVuelo(paqueteDto);
			iPaqueteController.crearPaqueteDeRutaDeVuelo(paqueteDto2);
			iPaqueteController.crearPaqueteDeRutaDeVuelo(paqueteDto3);
			iPaqueteController.crearPaqueteDeRutaDeVuelo(paqueteDto4);
			assertTrue(paqueteDtoContenidos(iPaqueteController.listarPaquetes(), dataPaquetes));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void agregarRutaDeVueloAPaquete_calculoCostoEjecutivo() {
		// Crear objetos con valores conocidos
		Date fecha = new Date(2022 - 1900, 11, 2);
		PaqueteDto paqueteDto = new PaqueteDto("Paquete6", "Descripcion Paquete 5", 11, 50, fecha, 120, null, null, null);
		Date fechaAlta = new Date(2022 - 1900, 15, 3);
		AerolineaDto aerolineaDto = new AerolineaDto("Aerolinea5", "Aerolinea", "ab@mail.com",
				"Descripción aerolinea 1", "aerolinea.com", new ArrayList<String>(), new Date(1924 - 1900, 0, 1),
				"Contra", null, null, null);
		Aerolinea aerolinea = new Aerolinea(aerolineaDto);
		UserManager manejadorUsuario = UserManager.getInstance();
		manejadorUsuario.agregarUsuario(aerolinea);
		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("RutaDeVuelo5", "Descripción Ruta de vuelo 1", 10, 20, 5, fechaAlta,
				aerolinea, new ArrayList<Categoria>(), hora, ciudadO, ciudadD, EstadoRuta.Confirmada, "Resumen", null, null, null);

		aerolinea.getRutasDeVuelo().put(rutaDeVuelo.getNombre(), rutaDeVuelo);
		ComercializaDto comercializaDto = new ComercializaDto(2, TipoAsiento.Ejecutivo, "RutaDeVuelo5", "Paquete6");
		// Llamar al método
		IPaqueteController iPaqueteController = new PaqueteController();
		PaqueteManager manejadorPaquete = PaqueteManager.getInstance();
		try {
			iPaqueteController.crearPaqueteDeRutaDeVuelo(paqueteDto);
			Paquete paquete = manejadorPaquete.findPaqueteByName(paqueteDto.getNombre());
			iPaqueteController.agregarRutaDeVueloAPaquete(comercializaDto);
			// Verificar el cálculo del costo asociado
			assertEquals(20.0, paquete.getCostoAsociado(), 0.01);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void agregarRutaDeVueloAPaquete_calculoCostoTurista() {
		// Crear objetos con valores conocidos
		Date fecha = new Date(2022 - 1900, 11, 2);
		PaqueteDto paqueteDto = new PaqueteDto("Paquete7", "Descripcion Paquete 7", 15, 20, fecha, 0, null, null, null);
		Date fechaAlta = new Date(2022 - 1900, 15, 3);
		AerolineaDto aerolineaDto = new AerolineaDto("Aerolinea6", "Aerolinea6", "a6@mail.com",
				"Descripción aerolinea 6", "aerolinea6.com", new ArrayList<String>(), new Date(1924 - 1900, 0, 1),
				"Contra", null, null, null);
		Aerolinea aerolinea = new Aerolinea(aerolineaDto);
		UserManager manejadorUsuario = UserManager.getInstance();
		manejadorUsuario.agregarUsuario(aerolinea);
		RutaDeVuelo rutaDeVuelo = new RutaDeVuelo("RutaDeVuelo6", "Descripción Ruta de vuelo 6", 15, 25, 10, fechaAlta,
				aerolinea, new ArrayList<Categoria>(), hora, ciudadO, ciudadD, EstadoRuta.Confirmada, "Resumen", null, null, null);

		aerolinea.getRutasDeVuelo().put(rutaDeVuelo.getNombre(), rutaDeVuelo);
		ComercializaDto comercializaDto = new ComercializaDto(3, TipoAsiento.Turista, "RutaDeVuelo6", "Paquete7");
		// Llamar al método
		IPaqueteController iPaqueteController = new PaqueteController();
		PaqueteManager manejadorPaquete = PaqueteManager.getInstance();
		try {
			iPaqueteController.crearPaqueteDeRutaDeVuelo(paqueteDto);
			Paquete paquete = manejadorPaquete.findPaqueteByName(paqueteDto.getNombre());
			iPaqueteController.agregarRutaDeVueloAPaquete(comercializaDto);
			// Verificar el cálculo del costo asociado
			assertEquals(36.0, paquete.getCostoAsociado(), 0.01);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void agregarRutaDeVueloAPaquete_CantidadDeRutasMayorQueCero() {
		ComercializaDto comercializaDto = new ComercializaDto(-1, TipoAsiento.Ejecutivo, "RutaDeVuelo5", "Paquete6");
		// Llamar al método
		IPaqueteController iPaqueteController = new PaqueteController();
		try {
			iPaqueteController.agregarRutaDeVueloAPaquete(comercializaDto);
			// Verificar el cálculo del costo asociado
		} catch (Exception e) {
			assertEquals(e.getMessage(), "La cantidad de rutas de vuelo debe ser mayor que cero");
		}
	}
}
