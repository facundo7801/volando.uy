package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import uy.edu.fing.volandouy.util.DataLoader;

public class DataLaderTest {

	@Test
	public void testDatareaderSwing() throws Exception {
		DataLoader dataLoader = new DataLoader(false, null);
		dataLoader.loadAllFiles();
		dataLoader.processUsuarios();
		dataLoader.processCategorias();
		dataLoader.processCiudades();
		dataLoader.processRutasDeVuelo();
		dataLoader.processVuelos();
		dataLoader.processReservasYPasajes();
		dataLoader.processPaquetes();
		dataLoader.processPaquetesRutas();
		dataLoader.processComprasPaquetes();
		dataLoader.processSeguidos();

		assertNotNull(true);
	}

}
