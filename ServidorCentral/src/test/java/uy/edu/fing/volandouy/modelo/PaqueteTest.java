package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import uy.edu.fing.volandouy.dto.PaqueteDto;

public class PaqueteTest {

	@Test
	public void testPaqueteConstructorAndGetters() {
		Date fechaAlta = new Date();
		PaqueteDto paquetedto = new PaqueteDto("Paquete1", "Descripción del paquete", 30, 10, fechaAlta, 1000.0f, null, null, null);
		Paquete paquete = new Paquete(paquetedto);

		assertEquals("Paquete1", paquete.getNombre());
		assertEquals("Descripción del paquete", paquete.getDescripcion());
		assertEquals((int)30, (int)paquete.getPeriodoValidez());
		assertEquals((int)10, (int)paquete.getDescuento());
		assertEquals(fechaAlta, paquete.getFechaAlta());
		assertEquals(0, paquete.getCostoAsociado(), 0.01);
	}

	@Test
	public void testPaqueteSetters() {
		Date fechaAlta = new Date();
		PaqueteDto paquetedto = new PaqueteDto("Paquete1", "Descripción del paquete", 30, 10, fechaAlta, 1000.0f, null, null, null);
		Paquete paquete = new Paquete(paquetedto);

		paquete.setNombre("Paquete2");
		paquete.setDescripcion("Nueva descripción");
		paquete.setPeriodoValidez(60);
		paquete.setDescuento(20);
		Date nuevaFechaAlta = new Date(fechaAlta.getTime() + 10000);
		paquete.setFechaAlta(nuevaFechaAlta);
		paquete.setCostoAsociado(2000.0f);
		paquete.getImagen();
		paquete.setImagen(null);
		paquete.setListComercializa(new ArrayList<>());
		
		assertEquals("Paquete2", paquete.getNombre());
		assertEquals("Nueva descripción", paquete.getDescripcion());
		assertEquals((int)60, (int) paquete.getPeriodoValidez());
		assertEquals((int)20, (int)paquete.getDescuento());
		assertEquals(nuevaFechaAlta, paquete.getFechaAlta());
		assertEquals(2000.0f, paquete.getCostoAsociado(), 0.01);
	}

	@Test
	public void testPaqueteToDto() {
		Date fechaAlta = new Date();
		PaqueteDto paquetedto = new PaqueteDto("Paquete1", "Descripción del paquete", 30, 10, fechaAlta, 1000.0f, null, null, null);
		Paquete paquete = new Paquete(paquetedto);
		PaqueteDto paqueteDto = paquete.toDto();

		assertEquals("Paquete1", paqueteDto.getNombre());
		assertEquals("Descripción del paquete", paqueteDto.getDescripcion());
		assertEquals((int)30, (int)paqueteDto.getPeriodoValidez());
		assertEquals((int)10, (int)paqueteDto.getDescuento());
		assertEquals(fechaAlta, paqueteDto.getFechaAlta());
		assertEquals(0, paqueteDto.getCostoAsociado(), 0.01);
	}
}
