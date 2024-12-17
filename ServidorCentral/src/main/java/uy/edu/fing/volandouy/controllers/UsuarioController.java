package uy.edu.fing.volandouy.controllers;

import java.util.ArrayList;
import java.util.List;

import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.CategoriaDto;
import uy.edu.fing.volandouy.dto.CiudadDto;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.dto.RutaDeVueloDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;
import uy.edu.fing.volandouy.managers.UserManager;
import uy.edu.fing.volandouy.managers.VueloManager;
import uy.edu.fing.volandouy.modelo.Aerolinea;
import uy.edu.fing.volandouy.modelo.Categoria;
import uy.edu.fing.volandouy.modelo.Ciudad;
import uy.edu.fing.volandouy.modelo.Cliente;
import uy.edu.fing.volandouy.modelo.RutaDeVuelo;
import uy.edu.fing.volandouy.modelo.Usuario;

public class UsuarioController implements IUsuarioController {

	private void validarUsuario(UsuarioDto usuario) throws Exception {
		UserManager manejadorUsuario = UserManager.getInstance();

		Usuario usuarioExistente = manejadorUsuario.findUserByEmail(usuario.getEmail());
		if (usuarioExistente != null) {
			throw new Exception("El usuario ya se encuentra registrado con ese email");
		}

		usuarioExistente = manejadorUsuario.findUserByNickname(usuario.getNickName());
		if (usuarioExistente != null) {
			throw new Exception("El usuario ya se encuentra registrado con ese nickName");
		}

		if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
			throw new Exception("El email del usuario es requerido");
		}

		if (usuario.getNickName() == null || usuario.getNickName().isBlank()) {
			throw new Exception("El nickName es requerido");
		}

		if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
			throw new Exception("El nombre es requerido");
		}

		if (usuario instanceof ClienteDto) {
			this.validarCliente(usuario);
		}

		if (usuario instanceof AerolineaDto) {
			this.validarAerolinea(usuario);
		}
		
		if (usuario.getContrasenia() == null || usuario.getContrasenia().isBlank()) {
			throw new Exception("La contraseña ingresada es vacía");
		}
		
		//La imagen es opcional

	}

	private void validarAerolinea(UsuarioDto usuario) throws Exception {
		AerolineaDto aerolinea = (AerolineaDto) usuario;

		if (aerolinea.getDescripcion() == null || aerolinea.getDescripcion().isBlank()) {
			throw new Exception("La descripcion de la aerolinea es requerida");
		}
	}

	private void validarCliente(UsuarioDto usuario) throws Exception {

		ClienteDto cliente = (ClienteDto) usuario;

		if (cliente.getApellido().isBlank()) {
			throw new Exception("El apellido del cliente es requerido");
		}

		if (cliente.getFechaNacimiento() == null) {
			throw new Exception("La fecha de nacimiento del cliente es requerido");
		}

		if (cliente.getTipoDocumento() == null) {
			throw new Exception("El tipo de documento del cliente es requerido");
		}

		if (cliente.getNumeroDocumento().isBlank()) {
			throw new Exception("El numero de documento del cliente es requerido");
		}

		if (cliente.getNacionalidad().isBlank()) {
			throw new Exception("La nacionalidad del cliente es requerida");
		}

	}

	private Usuario conmvertirDtoAEntidad(UsuarioDto usuarioDto) {
		if (usuarioDto instanceof ClienteDto) {
			return new Cliente((ClienteDto) usuarioDto);
		} else {
			return new Aerolinea((AerolineaDto) usuarioDto);
		}
	}

	@Override
	public void agregarUsuario(UsuarioDto usuarioDto) throws Exception {
		this.validarUsuario(usuarioDto);
		UserManager manejadorUsuario = UserManager.getInstance();
		Usuario nuevoUsuario = this.conmvertirDtoAEntidad(usuarioDto);
		manejadorUsuario.agregarUsuario(nuevoUsuario);
	}

	private void validarActualizarUsuario(UsuarioDto usuarioDto) throws Exception {
		UserManager manejadorUsuario = UserManager.getInstance();

		Usuario usuarioExistente = manejadorUsuario.findUserByEmail(usuarioDto.getEmail());
		if (usuarioExistente == null) {
			throw new Exception("El usuario no se encuentra registrado con ese email");
		}

		usuarioExistente = manejadorUsuario.findUserByNickname(usuarioDto.getNickName());
		if (usuarioExistente == null) {
			throw new Exception("El usuario no se encuentra registrado con ese nickName");
		}

		if (usuarioDto.getEmail() == null || usuarioDto.getEmail().isBlank()) {
			throw new Exception("El email del usuario es requerido");
		}

		if (usuarioDto.getNickName() == null || usuarioDto.getNickName().isBlank()) {
			throw new Exception("El nickName es requerido");
		}

		if (usuarioDto.getNombre() == null || usuarioDto.getNombre().isBlank()) {
			throw new Exception("El nombre es requerido");
		}

		if (usuarioDto instanceof ClienteDto) {
			this.validarCliente(usuarioDto);
		}

		if (usuarioDto instanceof AerolineaDto) {
			this.validarAerolinea(usuarioDto);
		}
	}

	@Override
	public void actualizarUsuario(UsuarioDto usuarioDto) throws Exception {
		validarActualizarUsuario(usuarioDto);
		UserManager manejadorUsuario = UserManager.getInstance();
		Usuario usuario = manejadorUsuario.findUserByNickname(usuarioDto.getNickName());
		usuario.setNombre(usuarioDto.getNombre());
		//Hay que validar el formato de la imagen si no es null?
		usuario.setImagen(usuarioDto.getImagen());

		if (usuario instanceof Cliente) {
			ClienteDto clientedto = (ClienteDto) usuarioDto;
			Cliente cliente = (Cliente) usuario;

			cliente.setApellido(clientedto.getApellido());
			cliente.setFechaNacimiento(clientedto.getFechaNacimiento());
			cliente.setNacionalidad(clientedto.getNacionalidad());
			cliente.setNumeroDocumento(clientedto.getNumeroDocumento());
			cliente.setTipoDocumento(clientedto.getTipoDocumento());
		} else {
			AerolineaDto aerolineadto = (AerolineaDto) usuarioDto;
			Aerolinea aerolinea = (Aerolinea) usuario;

			aerolinea.setDescripcion(aerolineadto.getDescripcion());
			aerolinea.setWebsite(aerolineadto.getWebsite());
		}
	}

	@Override
	public List<UsuarioDto> listarUsuario() {
		UserManager manejadorUsuario = UserManager.getInstance();
		List<Usuario> usuarios = manejadorUsuario.getUsuarios();
		List<UsuarioDto> usuariosDto = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			usuariosDto.add(usuario.toDto());
		}
		return usuariosDto;
	}

	private void validarRuta(RutaDeVueloDto rutaDto) throws Exception {
		UserManager manejadorUsuario = UserManager.getInstance();
		VueloManager manejadorVuelo = VueloManager.getInstance();

		if (rutaDto.getAerolinea() == null) {
			throw new Exception("El nombre de la aerolinea es requerido");
		}

		for (Usuario usuario : manejadorUsuario.getUsuarios()) {
			if (usuario instanceof Aerolinea) {
				Aerolinea aerolinea = (Aerolinea) usuario;
				for (RutaDeVuelo ruta : aerolinea.getRutasDeVuelo().values()) {
					if (ruta.getNombre().equals(rutaDto.getNombre())) {
						throw new Exception("El nombre de la ruta de vuelo ya se encuntra registrado en el sistema");
					}
				}
			}
		}

		if (rutaDto.getNombre().isBlank()) {
			throw new Exception("El nombre de la ruta es requerido");
		}

		if (rutaDto.getCategorias().size() == 0) {
			throw new Exception("Se debe ingresar al menos una categoría");
		}

		for (CategoriaDto nombreCategoria : rutaDto.getCategorias()) {
			Categoria categoria = manejadorVuelo.buscarCategoriaPorNombre(nombreCategoria.getNombre());
			if (categoria == null) {
				throw new Exception("Al menos una de las categorias ingresadas no existe en el sistema");
			}
		}

		if (rutaDto.getCiudadOrigen() == null) {
			throw new Exception("La ciudad de origen es requerida");
		}

		if (rutaDto.getCiudadDestino() == null) {
			throw new Exception("La ciudad de destino es requerida");
		}
		CiudadDto ciudadOrigenDto = rutaDto.getCiudadOrigen();
		CiudadDto ciudadDestinoDto = rutaDto.getCiudadDestino();
		Ciudad ciudadOrigen = manejadorVuelo.buscarCiudadPorNombre(ciudadOrigenDto.getNombre(),
				ciudadOrigenDto.getPais());
		Ciudad ciudadDestino = manejadorVuelo.buscarCiudadPorNombre(ciudadDestinoDto.getNombre(),
				ciudadDestinoDto.getPais());

		if (ciudadOrigen == null) {
			throw new Exception("La ciudad de origen ingresada no existe en el sistema");
		}

		if (ciudadDestino == null) {
			throw new Exception("La ciudad de destino ingresada no existe en el sistema");
		}

		if (ciudadOrigenDto.getNombre().equals(ciudadDestinoDto.getNombre())
				&& ciudadOrigenDto.getPais().equals(ciudadDestinoDto.getPais())) {
			throw new Exception("Las ciudades seleccionadas tienen que ser distintas");
		}

		if (rutaDto.getCostoEquipajeExtra() < 0) {
			throw new Exception("El costo del equipaje extra es negativo");
		}

		if (rutaDto.getCostoEjecutivo() < 0) {
			throw new Exception("El costo ejecutivo es negativo");
		}

		if (rutaDto.getCostoTurista() < 0) {
			throw new Exception("El costo turista es negativo");
		}

		if (rutaDto.getCostoEjecutivo() == 0 && rutaDto.getCostoTurista() == 0) {
			throw new Exception("El costo turista y ejecutivo son 0 a la vez");
		}

		if (rutaDto.getCostoTurista() >= rutaDto.getCostoEjecutivo()) {
			throw new Exception("El costo turista es mayor o igual al costo ejecutivo");
		}

		if (rutaDto.getDescripcion().isBlank()) {
			throw new Exception("La descripción es requerida");
		}
		
		if (rutaDto.getResumen().isBlank()) {
			throw new Exception("La descripción corta es requerida");
		}

		if (rutaDto.getHora() == null) {
			throw new Exception("La hora es requerida");
		}

		if (ciudadOrigen.getFechaAlta().after(rutaDto.getFechaAlta())) {
			throw new Exception(
					"La fecha de alta ingresada es anterior a la fecha de alta de la ciudad de origen en el sistema");
		}

		if (ciudadDestino.getFechaAlta().after(rutaDto.getFechaAlta())) {
			throw new Exception(
					"La fecha de alta ingresada es anterior a la fecha de alta de la ciudad de destino en el sistema");
		}
		
		if (rutaDto.getResumen() == null && rutaDto.getResumen().isBlank()) {
			throw new Exception("El resumen es requerido");
		}
		
		Aerolinea aerolineaSistema = (Aerolinea) manejadorUsuario.findUserByNickname(rutaDto.getAerolinea().getNickName());
		
		if (rutaDto.getFechaAlta().before(aerolineaSistema.getFechaAlta())) {
			throw new Exception("La fecha de alta de la ruta de vuelo es anterior a la fecha de alta de la aerolinea en el sistema");
		}

	}

	public void agregarRuta(RutaDeVueloDto rutaDto) throws Exception {
		// Llama al método con el parámetro booleano, manteniendo la validación
		agregarRuta(rutaDto, true); // Por defecto, las validaciones están habilitadas
	}

	public void agregarRuta(RutaDeVueloDto rutaDto, boolean validar) throws Exception {
		if (validar) {
			validarRuta(rutaDto);
		}
		UserManager manejadorUsuario = UserManager.getInstance();
		VueloManager manejadorVuelo = VueloManager.getInstance();
		CiudadDto ciudadOrigenDto = rutaDto.getCiudadOrigen();
		CiudadDto ciudadDestinoDto = rutaDto.getCiudadDestino();
		Ciudad ciudadOrigen = manejadorVuelo.buscarCiudadPorNombre(ciudadOrigenDto.getNombre(),
				ciudadOrigenDto.getPais());
		Ciudad ciudadDestino = manejadorVuelo.buscarCiudadPorNombre(ciudadDestinoDto.getNombre(),
				ciudadDestinoDto.getPais());
		List<Categoria> categorias = new ArrayList<>();
		for (CategoriaDto categoriaDto : rutaDto.getCategorias()) {
			Categoria categoria = manejadorVuelo.buscarCategoriaPorNombre(categoriaDto.getNombre());
			categorias.add(categoria);
		}
		Aerolinea aerolinea = (Aerolinea) manejadorUsuario.findUserByNickname(rutaDto.getAerolinea().getNickName());

		// Ruta
		RutaDeVuelo nuevaRuta = new RutaDeVuelo(rutaDto.getNombre(), rutaDto.getDescripcion(),
				rutaDto.getCostoTurista(), rutaDto.getCostoEjecutivo(), rutaDto.getCostoEquipajeExtra(),
				rutaDto.getFechaAlta(), aerolinea, categorias, rutaDto.getHora(), ciudadOrigen, ciudadDestino, rutaDto.getEstado(), rutaDto.getResumen(), rutaDto.getImagen(), rutaDto.getVideo(), rutaDto.getFechaFinalizada());

		nuevaRuta.setVisitas(rutaDto.getVisitas());
		// La agrego en la aerolinea
		aerolinea.getRutasDeVuelo().put(nuevaRuta.getNombre(), nuevaRuta);
		// La agrego en las categorias
		for (Categoria categoria : categorias) {
			categoria.getRutasDeVuelo().add(nuevaRuta);
		}
		manejadorUsuario.agregarRutaDeVuelo(nuevaRuta);
	}

	public List<RutaDeVueloDto> listarRutas(String aerolinea) throws Exception {
		UserManager manejadorUsuario = UserManager.getInstance();
		Usuario usuarioAerolinea = manejadorUsuario.findUserByNickname(aerolinea);

		if (usuarioAerolinea == null || usuarioAerolinea instanceof Cliente) {
			throw new Exception("La aerolinea ingresada no existe en el sistema");
		}

		Aerolinea aerolineaSistema = (Aerolinea) usuarioAerolinea;
		List<RutaDeVueloDto> rutasDto = new ArrayList<>();
		for (RutaDeVuelo ruta : aerolineaSistema.getRutasDeVuelo().values()) {
			rutasDto.add(ruta.toDto());
		}

		return rutasDto;
	}
	
	private void validarCambiarEstadoRuta(String aerolinea, String ruta, EstadoRuta estado) throws Exception {
		UserManager manejadorUsuario = UserManager.getInstance();
		Usuario usuarioAerolinea = manejadorUsuario.findUserByNickname(aerolinea);
		
		if (usuarioAerolinea == null || usuarioAerolinea instanceof Cliente) {
			throw new Exception("La aerolinea ingresada no existe en el sistema");
		}
		
		Aerolinea aerolineaSistema = (Aerolinea) usuarioAerolinea;
		RutaDeVuelo rutaSistema = null;
		for (RutaDeVuelo rutaAerolinea : aerolineaSistema.getRutasDeVuelo().values()) {
			if (rutaAerolinea.getNombre().equals(ruta)) {
				rutaSistema = rutaAerolinea;
			}
		}
		
		if (rutaSistema == null) {
			throw new Exception("La ruta ingresada no pertenece a la aerolinea ingresada o no existe en el sistema");
		}
		
		if (rutaSistema.getEstado() != EstadoRuta.Ingresada) {
			throw new Exception("La ruta ingresada ya tenía un estado distinto de \"Ingresada\"");
		}
		
		if (estado == EstadoRuta.Ingresada) {
			throw new Exception("El estado ingresado tiene que ser distinto de \"Ingresada\"");
		}
		
	}
	
	public void cambiarEstadoRuta(String aerolinea, String ruta, EstadoRuta estado) throws Exception{
		validarCambiarEstadoRuta(aerolinea, ruta, estado);
		UserManager manejadorUsuario = UserManager.getInstance();
		Usuario usuarioAerolinea = manejadorUsuario.findUserByNickname(aerolinea);
		
		Aerolinea aerolineaSistema = (Aerolinea) usuarioAerolinea;
		RutaDeVuelo rutaSistema = null;
		
		for (RutaDeVuelo rutaAerolinea : aerolineaSistema.getRutasDeVuelo().values()) {
			if (rutaAerolinea.getNombre().equals(ruta)) {
				rutaSistema = rutaAerolinea;
			}
		}
		
		rutaSistema.setEstado(estado);
		
	}

	@Override
	public UsuarioDto obtenerUsuarioPorCorreo(String correo) {
		UserManager manejadorUsuario = UserManager.getInstance();
		return manejadorUsuario.findUserByEmail(correo).toDto();
	}

	@Override
	public RutaDeVueloDto obtenerRutaDeVueloPorNombre(String nombre) {
		UserManager manejadorUsuario = UserManager.getInstance();
		return manejadorUsuario.findRutaDeVueloByNombre(nombre).toDto();
	}
	
	public UsuarioDto obtenerUsuarioPorNickName(String nickName) {
		UserManager manejadorUsuario = UserManager.getInstance();
		return manejadorUsuario.findUserByNickname(nickName).toDto();
	}
}
