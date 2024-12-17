package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import uy.edu.fing.volandouy.controllers.UsuarioController;
import uy.edu.fing.volandouy.controllers.Utilidades;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;
import uy.edu.fing.volandouy.managers.UserManager;

public class UsuarioControllerTest {
	@SuppressWarnings("deprecation")
	@Test
	public void testAgregarUsuario() {
		UserManager usrmg = UserManager.getInstance();
		UsuarioController controlusr = new UsuarioController();
		Integer anio = 2024;
		String mes = "Agosto";
		Integer dia = 7;
		String nick = "facu";
		String name = "Facundo";
		String eMail = "facu@cualquiercosa.com";
		String apellido = "L";
		TipoDocumento tipoDoc = TipoDocumento.PASAPORTE;
		String numDoc = "12345678";
		String Nacionalidad = "Uruguay";

		List<String> reservas = null;
		List<String> comprasPaquete = null;
		@SuppressWarnings("deprecation")
		Date fechaUsuario = new Date(anio, Utilidades.MesStringToInt(mes), dia);
		ClienteDto newCliente = new ClienteDto(nick, name, eMail, apellido, fechaUsuario, tipoDoc, numDoc, Nacionalidad,
				reservas, comprasPaquete, new Date(1924-1900, 0, 1), "Contra", null, null, null);

		String nickAero1 = "Aero1";
		String nameAero1 = "Aerolinea 1";
		String emailAero1 = "Aero1@gmail.com";
		String descAero1 = "descripcion aero 1";
		String webAero1 = "Aero1.com";
		List<String> rutasAero1 = null;

		AerolineaDto Aerolinea1 = new AerolineaDto(nickAero1, nameAero1, emailAero1, descAero1, webAero1, rutasAero1,
				new Date(1924-1900, 0, 1), "Contra", null, null, null);

		try {
			controlusr.agregarUsuario(newCliente);
			Cliente cliente = (Cliente) usrmg.findUserByNickname(nick);
			assertEquals(numDoc, cliente.getNumeroDocumento());

			controlusr.agregarUsuario(Aerolinea1);
			Aerolinea aero1 = (Aerolinea) usrmg.findUserByNickname(nickAero1);
			assertEquals(emailAero1, aero1.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testListarUsuarios() {
		UserManager usrmg = UserManager.getInstance();
		UsuarioController controlusr = new UsuarioController();

		List<UsuarioDto> users = new ArrayList<>();
		Integer diaUser1 = 7;
		String mesUser1 = "Octubre";
		Integer anioUser1 = 2000;
		Date fechauser1 = new Date(anioUser1, Utilidades.MesStringToInt(mesUser1), diaUser1);
		List<String> reservasUser1 = null;
		List<String> comprasPaqueteUser1 = null;
		UsuarioDto user1 = new ClienteDto("juan21", "Juan", "jp@gmail.com", "Pérez", fechauser1,
				TipoDocumento.PASAPORTE, "87654321", "Argentina", reservasUser1, comprasPaqueteUser1, new Date(1924-1900, 0, 1), "Contra", null, null, null);

		String nickAeroLista1 = "Aero1Lista";
		String nameAeroLista1 = "Aerolinea 1";
		String emailAeroLista1 = "Aero1Lista@gmail.com";
		String descAeroLista1 = "descripcion aero 1";
		String webAeroLista1 = "Aero1.com";
		List<String> rutasAeroLista1 = null;

		AerolineaDto AerolineaLista1 = new AerolineaDto(nickAeroLista1, nameAeroLista1, emailAeroLista1, descAeroLista1,
				webAeroLista1, rutasAeroLista1, new Date(1924-1900, 0, 1), "Contra", null, null, null);

		try {
			controlusr.agregarUsuario(user1);
			controlusr.agregarUsuario(AerolineaLista1);
			List<UsuarioDto> lst1 = controlusr.listarUsuario();
			assertTrue(check(user1, lst1));
			assertTrue(check(AerolineaLista1, lst1));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private boolean check(UsuarioDto usrdto, List<UsuarioDto> lst) {
		boolean res = false;
		UsuarioDto user = null;
		for (UsuarioDto usr : lst) {
			if (usrdto.getNickName().equals(usr.getNickName())) {
				user = usr;
			}
		}
		boolean nombre1 = usrdto.getNombre().equals(user.getNombre());
		boolean nick1 = usrdto.getNickName().equals(user.getNickName());
		boolean email1 = usrdto.getEmail().equals(user.getEmail());
		if (user instanceof ClienteDto) {
			ClienteDto cliendtoaux1 = (ClienteDto) usrdto;
			boolean tipodoc1 = cliendtoaux1.getTipoDocumento() == ((ClienteDto) user).getTipoDocumento();
			boolean numdoc1 = cliendtoaux1.getNumeroDocumento().equals(((ClienteDto) user).getNumeroDocumento());
			boolean nacion1 = cliendtoaux1.getNacionalidad().equals(((ClienteDto) user).getNacionalidad());

			res = (nombre1 && nick1 && email1 && tipodoc1 && numdoc1 && nacion1);

		} else if (user instanceof AerolineaDto) {
			AerolineaDto aerodtoaux1 = (AerolineaDto) usrdto;
			boolean booldescAeroLista1 = aerodtoaux1.getDescripcion().equals(((AerolineaDto) user).getDescripcion());
			boolean boolwebAeroLista1 = aerodtoaux1.getWebsite().equals(((AerolineaDto) user).getWebsite());

			res = (booldescAeroLista1 && boolwebAeroLista1);
		}
		return res;
	}

	@SuppressWarnings("deprecation")
	@Test

	public void testActualizarUsuario() {

		UserManager usrmg = UserManager.getInstance();
		UsuarioController controlusr = new UsuarioController();
		Integer anio = 2024;
		String mes = "Agosto";
		Integer dia = 7;
		String nick = "carlos";
		String name = "Facundo";
		String eMail = "carlos@cualquiercosa.com";
		String apellido = "L";
		TipoDocumento tipoDoc = TipoDocumento.PASAPORTE;
		String numDoc = "12345678";
		String Nacionalidad = "Uruguay";

		List<String> reservas = null;
		List<String> comprasPaquete = null;
		@SuppressWarnings("deprecation")
		Date fechaUsuario = new Date(anio, Utilidades.MesStringToInt(mes), dia);
		ClienteDto newCliente = new ClienteDto(nick, name, eMail, apellido, fechaUsuario, tipoDoc, numDoc, Nacionalidad,
				reservas, comprasPaquete, new Date(1924-1900, 0, 1), "Contra", null, null, null);

		String nickAero1 = "Aero123";
		String nameAero1 = "Aerolinea 1";
		String emailAero1 = "Aero123@gmail.com";
		String descAero1 = "descripcion aero 1";
		String webAero1 = "Aero1.com";
		List<String> rutasAero1 = null;

		AerolineaDto Aerolinea1 = new AerolineaDto(nickAero1, nameAero1, emailAero1, descAero1, webAero1, rutasAero1,
				new Date(1924-1900, 0, 1), "Contra", null, null, null);

		try {
			controlusr.agregarUsuario(newCliente);
			Cliente cliente = (Cliente) usrmg.findUserByNickname(nick);
			assertEquals(numDoc, cliente.getNumeroDocumento());

			controlusr.agregarUsuario(Aerolinea1);
			Aerolinea aero1 = (Aerolinea) usrmg.findUserByNickname(nickAero1);
			assertEquals(emailAero1, aero1.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Integer anioActualizar = 2005;
		String mesActualizar = "Agosto";
		Integer diaActualizar = 7;
		String nameActualizar = "Rodrigo";
		String apellidoActualizar = "Gómez";
		TipoDocumento tipoDocActualizar = TipoDocumento.PASAPORTE;
		String numDocActualizar = "45678982";
		String NacionalidadActualizar = "Italiano";

		List<String> reservasActualizar = null;
		List<String> comprasPaqueteActualizar = null;
		@SuppressWarnings("deprecation")
		Date fechaUsuarioActualizar = new Date(anioActualizar, Utilidades.MesStringToInt(mesActualizar), diaActualizar);
		ClienteDto newClienteActualizar = new ClienteDto(nick, nameActualizar, eMail, apellidoActualizar,
				fechaUsuarioActualizar, tipoDocActualizar, numDocActualizar, NacionalidadActualizar, reservasActualizar,
				comprasPaqueteActualizar, new Date(1924-1900, 0, 1), "Contra", null, null, null);

		String nameAero1Actualizar = "Aerolinea 1";

		String descAero1Actualizar = "descripcion aero 1";
		String webAero1Actualizar = "Aero1.com";
		List<String> rutasAero1Actualizar = null;

		AerolineaDto Aerolinea1Actualizar = new AerolineaDto(nickAero1, nameAero1Actualizar, emailAero1,
				descAero1Actualizar, webAero1Actualizar, rutasAero1Actualizar, new Date(1924-1900, 0, 1), "Contra", null, null, null);

		try {
			controlusr.actualizarUsuario(newClienteActualizar);
			check(newClienteActualizar, controlusr.listarUsuario());
			// Cliente cliente = (Cliente)usrmg.findUserByNickname(nick);
			// assertEquals(numDoc,cliente.getNumeroDocumento());

			controlusr.actualizarUsuario(Aerolinea1Actualizar);
			check(Aerolinea1Actualizar, controlusr.listarUsuario());
			// Aerolinea aero1 = (Aerolinea)usrmg.findUserByNickname(nickAero1);
			// assertEquals(emailAero1, aero1.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
