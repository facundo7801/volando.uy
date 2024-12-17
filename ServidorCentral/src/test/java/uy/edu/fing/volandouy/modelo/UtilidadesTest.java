package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import uy.edu.fing.volandouy.controllers.Utilidades;

public class UtilidadesTest {

	@Test
	public void testVerificarLetras() {
		assertTrue(Utilidades.verificarLetras("abc"));
		assertFalse(Utilidades.verificarLetras("abc123"));
		assertFalse(Utilidades.verificarLetras(""));
		assertFalse(Utilidades.verificarLetras(" "));
	}

	@Test
	public void testVerificarLetrasEspacio() {
		assertTrue(Utilidades.verificarLetrasEspacio("abc def"));
		assertTrue(Utilidades.verificarLetrasEspacio("abc"));
		assertFalse(Utilidades.verificarLetrasEspacio("abc123"));
		assertFalse(Utilidades.verificarLetrasEspacio(""));
		assertFalse(Utilidades.verificarLetrasEspacio("   "));
	}

	@Test
	public void testVerificarNumeros() {
		assertTrue(Utilidades.verificarNumeros("123"));
		assertFalse(Utilidades.verificarNumeros("0123"));
		assertFalse(Utilidades.verificarNumeros("123a"));
		assertFalse(Utilidades.verificarNumeros(""));
	}

	@Test
	public void testVerificarTodoCeros() {
		assertTrue(Utilidades.verificarTodoCeros("000"));
		assertFalse(Utilidades.verificarTodoCeros("123"));
		assertFalse(Utilidades.verificarTodoCeros("0123"));
		assertFalse(Utilidades.verificarTodoCeros(""));
	}

	@Test
	public void testVerificarCerosPrincipio() {
		assertTrue(Utilidades.verificarCerosPrincipio("00123"));
		assertFalse(Utilidades.verificarCerosPrincipio("123"));
		assertTrue(Utilidades.verificarCerosPrincipio("0123"));
		assertFalse(Utilidades.verificarCerosPrincipio(""));
		assertFalse(Utilidades.verificarCerosPrincipio("000"));
	}

	@Test
	public void testVerificarEmail() {
		assertTrue(Utilidades.verificarEmail("test@example.com"));
		assertFalse(Utilidades.verificarEmail("test@.com"));
		assertFalse(Utilidades.verificarEmail("test@example"));
		assertTrue(Utilidades.verificarEmail("test@exam_ple.com"));
		assertFalse(Utilidades.verificarEmail(""));
	}

	@Test
	public void testEliminarCerosPrincipio() {
		assertEquals("123", Utilidades.eliminarCerosPrincipio("00123"));
		assertEquals("123", Utilidades.eliminarCerosPrincipio("123"));
		assertEquals("", Utilidades.eliminarCerosPrincipio("000"));
		assertEquals("", Utilidades.eliminarCerosPrincipio("0"));
	}

	@Test
	public void testVerificarDuracion() {
		assertTrue(Utilidades.verificarDuracion("12:34"));
		assertFalse(Utilidades.verificarDuracion("00:00"));
		assertFalse(Utilidades.verificarDuracion("25:00"));
		assertFalse(Utilidades.verificarDuracion("12:60"));
		assertFalse(Utilidades.verificarDuracion("1234"));
	}

	@Test
	public void testStringToTime() {
		Time time = Utilidades.stringToTime("12:34");
		assertNotNull(time);
		assertEquals(12, time.getHours());
		assertEquals(34, time.getMinutes());

		time = Utilidades.stringToTime("00:00");
		assertNotNull(time);

		time = Utilidades.stringToTime("25:00");
		assertNull(time);

		time = Utilidades.stringToTime("12:60");
		assertNull(time);

		time = Utilidades.stringToTime("1234");
		assertNull(time);
	}

	@Test
	public void testSumarDiasFecha() {
		// Crea una fecha base (ejemplo: 1 de enero de 2021)
		Calendar calendar = Calendar.getInstance();
		calendar.set(2021, Calendar.JULY, 31); // 31 de julio de 2021
		Date fechaBase = calendar.getTime();

		// Suma 8 días
		Date fechaNueva = Utilidades.sumarDiasFecha(8, fechaBase);

		// Configura la fecha esperada (ejemplo: 8 de agosto de 2021)
		calendar.set(2021, Calendar.AUGUST, 8); // 8 de agosto de 2021
		Date fechaEsperada = calendar.getTime();

		// Verifica el resultado
		assertEquals(fechaEsperada.getMonth(), fechaNueva.getMonth()); // Mes de agosto (0 basado)
		assertEquals(fechaEsperada.getYear(), fechaNueva.getYear()); // Año 2021
	}

	@Test
	public void testMesStringToInt() {
		assertEquals(1, Utilidades.MesStringToInt("Enero"));
        assertEquals(2, Utilidades.MesStringToInt("Febrero"));
        assertEquals(3, Utilidades.MesStringToInt("Marzo"));
        assertEquals(4, Utilidades.MesStringToInt("Abril"));
        assertEquals(5, Utilidades.MesStringToInt("Mayo"));
        assertEquals(6, Utilidades.MesStringToInt("Junio"));
        assertEquals(7, Utilidades.MesStringToInt("Julio"));
        assertEquals(8, Utilidades.MesStringToInt("Agosto"));
        assertEquals(9, Utilidades.MesStringToInt("Setiembre"));
        assertEquals(10, Utilidades.MesStringToInt("Octubre"));
        assertEquals(11, Utilidades.MesStringToInt("Noviembre"));
        assertEquals(12, Utilidades.MesStringToInt("Diciembre"));
		assertEquals(0, Utilidades.MesStringToInt("InvalidMonth")); // Valor por defecto para entradas inválidas
	}
	
	
	@Test
    public void testVerificarDecimales() {
        // Casos válidos
        assertTrue(Utilidades.verificarDecimales("123"));
        assertTrue(Utilidades.verificarDecimales("123.45"));
        assertTrue(Utilidades.verificarDecimales("0.1"));
        assertTrue(Utilidades.verificarDecimales(".45"));
        assertTrue(Utilidades.verificarDecimales("0"));

        // Casos inválidos
        assertFalse(Utilidades.verificarDecimales("123..45"));
        assertFalse(Utilidades.verificarDecimales("123."));
        assertFalse(Utilidades.verificarDecimales("abc"));
        assertFalse(Utilidades.verificarDecimales("123a"));
        assertFalse(Utilidades.verificarDecimales(""));
    }
	
	 @Test
	    public void testGetDias() {
	        // Prueba de diferentes meses y años
	        Integer[] eneroDias = Utilidades.getDias(1, 2024);
	        assertEquals(31, eneroDias.length); // Enero tiene 31 días
	        assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 }, eneroDias);

	        Integer[] febreroDiasBisiesto = Utilidades.getDias(2, 2024);
	        assertEquals(29, febreroDiasBisiesto.length); // 2024 es un año bisiesto
	        assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29 }, febreroDiasBisiesto);

	        Integer[] febreroDiasNoBisiesto = Utilidades.getDias(2, 2023);
	        assertEquals(28, febreroDiasNoBisiesto.length); // 2023 no es bisiesto
	    }

	    @Test
	    public void testGetAnios() {
	        // Verificar los años generados
	        Integer[] anios = Utilidades.getAnios();
	        assertEquals(101, anios.length); // Se generan 101 años
	        assertEquals(Integer.valueOf(1924), anios[0]); // El primer año es 1924
	        assertEquals(Integer.valueOf(2024), anios[100]); // El último año es 2024
	    }
	    
	 
	    @Test
	    public void testActualizarFechaAlta() {
	        // Caso 1: fechaAlta es la misma que la fecha actual
	        Date fechaAlta = new Date(); // Usa la fecha y hora actual
	        Date fechaOriginal = (Date) fechaAlta.clone(); // Clonamos la fecha original para asegurar que no cambie
	        Utilidades.actualizarFechaAlta(fechaAlta); 

	        // Verificamos que la fecha no haya cambiado si la fechaAlta es la misma que la fecha actual
	        assertEquals(fechaOriginal.getYear(), fechaAlta.getYear());
	        assertEquals(fechaOriginal.getMonth(), fechaAlta.getMonth());
	        assertEquals(fechaOriginal.getDate(), fechaAlta.getDate());
	        assertEquals(fechaOriginal.getHours(), fechaAlta.getHours());
	        assertEquals(fechaOriginal.getMinutes(), fechaAlta.getMinutes());
	        assertEquals(fechaOriginal.getSeconds(), fechaAlta.getSeconds());

	        // Caso 2: fechaAlta es diferente a la fecha actual
	        Date fechaAltaDiferente = new Date(2023, 10, 1); // Fecha de ejemplo distinta de la actual
	        int originalHours = fechaAltaDiferente.getHours();
	        int originalMinutes = fechaAltaDiferente.getMinutes();
	        int originalSeconds = fechaAltaDiferente.getSeconds();
	        
	        Utilidades.actualizarFechaAlta(fechaAltaDiferente);
	        
	        assertEquals(2023, fechaAltaDiferente.getYear());
	        assertEquals(10, fechaAltaDiferente.getMonth());
	        assertEquals(1, fechaAltaDiferente.getDate());
	    }
}
