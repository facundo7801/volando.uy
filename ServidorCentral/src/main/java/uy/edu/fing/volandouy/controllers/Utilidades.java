package uy.edu.fing.volandouy.controllers;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Utilidades {
	public static String[] Month = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
			"Setiembre", "Octubre", "Noviembre", "Diciembre" };

	// El parametro mes es exacto y el año es exacto
	public static Integer[] getDias(Integer mes, Integer anio) {
		LocalDate primerDiaMes = LocalDate.of(anio, mes, 1);
		Integer tamanioDias = primerDiaMes.lengthOfMonth();

		Integer[] dias = new Integer[tamanioDias];
		for (int i = 0; i < tamanioDias; i++) {
			dias[i] = i + 1;
		}

		return dias;

	}

	public static Integer[] getAnios() {
		Integer[] anios = new Integer[101];
		for (int i = 0; i < 101; i++) {
			anios[i] = 1924 + i;
		}

		return anios;

	}

	// Devuelve true si el string solo esta formado por letras y no es vacio
	public static boolean verificarLetras(String texto) {
		return texto.matches("[a-zA-Z]+");
	}

	// Devuelve true si el string solo esta formado por letras, espacios y no es
	// vacio ni solo formado por espacios
	public static boolean verificarLetrasEspacio(String texto) {
		return texto.matches("[a-zA-Z]+(\\s[a-zA-Z]+)*");
	}

	// Devuelve true si el string es un numero valido, sin ceros no significativos
	public static boolean verificarNumeros(String texto) {
		return texto.matches("(?!0\\d)\\d+");

	}

	public static boolean verificarDecimales(String texto) {
		return texto.matches("([0-9]*[.])?[0-9]+");

	}

	// Devuelve true si el texto esta formado por todo ceros
	public static boolean verificarTodoCeros(String texto) {
		return texto.matches("0+");
	}

	// Devuelve true si hay un numero valido con ceros no significativos adelante
	public static boolean verificarCerosPrincipio(String texto) {
		return texto.matches("0+\\d+") && !verificarTodoCeros(texto);
	}

	// Devuelve true si texto es un email valido
	public static boolean verificarEmail(String texto) {
		return texto.matches("[\\w\\.!#$%&'*+/=?^_`{|}~-]+@[\\w.-]+\\.[a-zA-Z]{2,6}");
	}

	// Si es todo ceros devuelve el string vacio
	public static String eliminarCerosPrincipio(String texto) {
		return texto.replaceFirst("^0+", "");
	}

	// Devuelve true si la duracion es una hora valida distinta de 00:00
	public static boolean verificarDuracion(String texto) {
		return texto.matches("([01]\\d|2[0-3]):[0-5]\\d") && !texto.equals("00:00");
	}

	// Si el formato es hh:mm y hh:mm != 00:00 entonces retorna un new Time(hh, mm,
	// 0) sino retorna null
	@SuppressWarnings("deprecation")
	public static Time stringToTime(String texto) {
		if (verificarDuracion(texto)) {
			return new Time(Integer.parseInt(texto.substring(0, 2)), Integer.parseInt(texto.substring(3, 5)), 0);
		} else if (texto.equals("00:00")) {
			return new Time(0, 0, 0);
		}
		return null;
	}

	// retorna fecha + dias
	@SuppressWarnings("deprecation")
	public static Date sumarDiasFecha(int dias, Date fecha) {
		Date fechaNueva = new Date(fecha.getYear(), fecha.getMonth(), fecha.getDate());
		int diasTotal = dias + fechaNueva.getDate();
		LocalDate primerDiaMes = LocalDate.of(fechaNueva.getYear() + 1900, fechaNueva.getMonth() + 1, 1);
		Integer tamanioDias = primerDiaMes.lengthOfMonth();
		while (diasTotal > tamanioDias) {
			diasTotal = diasTotal - tamanioDias;
			// Mes de [0, 11] por eso el + 1
			if (fechaNueva.getMonth() + 1 < 12) {
				fechaNueva.setMonth(fechaNueva.getMonth() + 1);
			} else {
				fechaNueva.setMonth(0);
				// El parametro es el year - 1900 pero getYear ya te lo da asi
				fechaNueva.setYear(fechaNueva.getYear() + 1);
			}
		}
		fechaNueva.setDate(diasTotal);
		return fechaNueva;
	}

	//Acutaliza la fechaAlta con la hora, minutos y segundos actuales si la fechaAlta coincide con la actual
	//Si no es la actual se asume que lo hace a las 00:00, por lo tanto no seteo nada
	@SuppressWarnings("deprecation")
	public static void actualizarFechaAlta(Date fechaAlta) {
		Date fechaAltaActual = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fechaAltaString = dateFormat.format(fechaAlta);
		String fechaAltaActualString = dateFormat.format(fechaAltaActual);
		if (fechaAltaString.equals(fechaAltaActualString)) {
			fechaAlta.setHours(fechaAltaActual.getHours());
			fechaAlta.setMinutes(fechaAltaActual.getMinutes());
			fechaAlta.setSeconds(fechaAltaActual.getSeconds());
		}		
	}
	
	public static int MesStringToInt(String mes) {
		int meses = 0;
		switch (mes) {
		case "Enero":
			meses = 1;
			break;
		case "Febrero":
			meses = 2;
			break;
		case "Marzo":
			meses = 3;
			break;
		case "Abril":
			meses = 4;
			break;
		case "Mayo":
			meses = 5;
			break;
		case "Junio":
			meses = 6;
			break;
		case "Julio":
			meses = 7;
			break;
		case "Agosto":
			meses = 8;
			break;
		case "Setiembre":
			meses = 9;
			break;
		case "Octubre":
			meses = 10;
			break;
		case "Noviembre":
			meses = 11;
			break;
		case "Diciembre":
			meses = 12;
			break;
		default:
		}
		return meses;
	}
}
