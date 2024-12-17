<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="uy.edu.fing.volandouy.webservices.UsuarioDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.AerolineaDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.ClienteDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.TipoDocumento"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<meta charset="UTF-8">
<title>Modificar Usuario</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap"
	rel="stylesheet">
<style>
body {
	font-family: 'Roboto', sans-serif;
	background-color: #f8f9fa;
	color: #343a40;
}

.container {
	margin-top: 30px;
}

h1 {
	text-align: center;
	margin-bottom: 20px;
	color: #007bff;
}

.user-details {
	background: white;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	margin-bottom: 20px;
}

.user-details img {
	max-width: 150px; /* Cambia el tamaño según tus necesidades */
	height: auto;
	border-radius: 8px;
	margin-bottom: 15px; /* Espacio debajo de la imagen */
}

.user-details ul {
	list-style-type: none;
	padding: 0;
}

.user-details li {
	margin-bottom: 10px; /* Espacio entre elementos de la lista */
}

.btn-actualizar {
	display: block;
	margin: 20px auto 0; /* Centra el botón y añade margen */
}

#error_message {
	color: #D8000C;
	background-color: #FFD2D2;
	border: 1px solid #D8000C;
	padding: 10px 20px;
	border-radius: 5px;
	font-family: Arial, sans-serif;
	font-size: 14px;
	text-align: center;
	width: 100%;
	margin-top: 10px;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>

		<div class="container">
			<h1>Modificar Usuario</h1>
			<%
			UsuarioDto usuario = (UsuarioDto) request.getAttribute("findUser");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (usuario != null) {
				if (usuario instanceof ClienteDto) {
					ClienteDto cliente = (ClienteDto) usuario;
			%>
			<div class="user-details">
				<img src="/ServidorWeb/Imagenes?nombreImagen=<%= cliente.getImagen() != null ? cliente.getImagen() : "noimg.jpg" %>" alt="Imagen de cliente">
				<form action="modificarUsuarioServlet" method="post"
					enctype="multipart/form-data" onsubmit="return validateForm();">
					
					<c:if test="${errorMessage != null}">
						<div id="error_message">${errorMessage}</div>
						<!-- Mensaje de error -->
					</c:if>
					
					<input type="hidden" name="nickname"
						value="<%=cliente.getNickName()%>" /> <input type="hidden"
						name="email" value="<%=cliente.getEmail()%>" />

					<div class="form-group">
						<label for="nombre">Nombre:</label> <input type="text"
							class="form-control" id="nombre" name="nombre"
							value="<%=cliente.getNombre()%>" required>
					</div>

					<div class="form-group">
						<label for="apellido">Apellido:</label> <input type="text"
							class="form-control" id="apellido" name="apellido"
							value="<%=cliente.getApellido()%>" required>
					</div>

					<div class="form-group">
						<label for="fechaNacimiento">Fecha de Nacimiento:</label> <input
							type="date" class="form-control" id="fechaNacimiento"
							name="fechaNacimiento"
							value="<%=dateFormat.format(cliente.getFechaNacimiento().toGregorianCalendar().getTime())%>"
							required>
					</div>

					<div class="form-group">
						<label for="nacionalidad">Nacionalidad:</label> <input type="text"
							class="form-control" id="nacionalidad" name="nacionalidad"
							value="<%=cliente.getNacionalidad()%>" required>
					</div>

					<div class="form-group">
						<label for="tipoDocumento">Tipo de Documento:</label> <select
							class="form-control" id="tipoDocumento" name="tipoDocumento"
							required>
							<option value="CI"
								<%if (cliente.getTipoDocumento() == TipoDocumento.CI) {%>
								selected <%}%>>Cédula de Identidad (CI)</option>
							<option value="PASAPORTE"
								<%if (cliente.getTipoDocumento() == TipoDocumento.PASAPORTE) {%>
								selected <%}%>>Pasaporte</option>
							<option value="OTRO"
								<%if (cliente.getTipoDocumento() == TipoDocumento.OTRO) {%>
								selected <%}%>>Otro</option>
						</select>
					</div>

					<div class="form-group">
						<label for="numeroDocumento">Número de Documento:</label> <input
							type="text" class="form-control" id="numeroDocumento"
							name="numeroDocumento" value="<%=cliente.getNumeroDocumento()%>"
							required>
					</div>

					<div class="form-group">
						<label for="imagen">Imagen:</label> <input type="file"
							class="form-control" id="imagen" name="imagen">
					</div>

					<div class="form-group">
						<a id="reservasC"
							href="ListarReservasServlet?clienteConsulta=consultaDirecta"
							class="btn btn-success">Consulta Reservas</a>
					</div>

					<div class="form-group">
						<a id="paquetesC"
							href="listaPaqueteServlet?clienteConsulta=clienteConsulta" class="btn btn-success">Consulta Paquetes</a>
					</div>
					
					<div class="form-group">
						<a id="seguidosC"
							href="ListaUsuariosServlet?seguidos=si&nicknameUsuarioSeguidores=<%=cliente.getNickName() %>&seguidosL=Si"
							class="btn btn-success">Listar Seguidos</a>
					</div>
					
					<div class="form-group">
						<a id="seguidoresC"
							href="ListaUsuariosServlet?seguidores=si&nicknameUsuarioSeguidores=<%=cliente.getNickName() %>&seguidoresL=Si"
							class="btn btn-success">Listar Seguidores</a>
					</div>

					<button type="submit" class="btn btn-primary btn-actualizar">Actualizar
						Usuario</button>
				</form>
			</div>
			<%
			} else if (usuario instanceof AerolineaDto) {
			AerolineaDto aerolinea = (AerolineaDto) usuario;
			%>
			<div class="user-details">
				<img src="/ServidorWeb/Imagenes?nombreImagen=<%= aerolinea.getImagen() != null ? aerolinea.getImagen() : "noimg.jpg" %>" alt="Imagen de aerolinea">
				<form action="modificarUsuarioServlet" method="post"
					enctype="multipart/form-data" onsubmit="return validateForm();">
					<input type="hidden" name="nickname"
						value="<%=aerolinea.getNickName()%>" /> <input type="hidden"
						name="email" value="<%=aerolinea.getEmail()%>" />

					<div class="form-group">
						<label for="nombre">Nombre:</label> <input type="text"
							class="form-control" id="nombre" name="nombre"
							value="<%=aerolinea.getNombre()%>" required>
					</div>

					<div class="form-group">
						<label for="descripcion">Descripción:</label> <input type="text"
							class="form-control" id="descripcion" name="descripcion"
							value="<%=aerolinea.getDescripcion()%>" required>
					</div>

					<div class="form-group">
						<label for="website">Sitio Web:</label> <input type="text"
							class="form-control" id="website" name="website"
							value="<%=aerolinea.getWebsite()%>">
					</div>

					<div class="form-group">
						<label for="imagen">Imagen:</label> <input type="file"
							class="form-control" id="imagen" name="imagen">
					</div>

					<div class="form-group">
						<a id="rutasA"
							href="ListarRutasServlet?aerolineaListar=<%=aerolinea.getNickName()%>"
							class="btn btn-success">Listar Rutas</a>
					</div>

					<div class="form-group">
						<a id="seguidosA"
							href="ListaUsuariosServlet?seguidos=si&nicknameUsuarioSeguidores=<%=aerolinea.getNickName() %>&seguidosL=Si"
							class="btn btn-success">Listar Seguidos</a>
					</div>
					
					<div class="form-group">
						<a id="seguidoresA"
							href="ListaUsuariosServlet?seguidores=si&nicknameUsuarioSeguidores=<%=aerolinea.getNickName() %>&seguidoresL=Si"
							class="btn btn-success">Listar Seguidores</a>
					</div>

					<button type="submit" class="btn btn-primary btn-actualizar">Actualizar
						Aerolínea</button>

					<c:if test="${errorMessage != null}">
						<div id="errorMessageAerolinea" class="error-message">${errorMessage}</div>
						<!-- Mensaje de error -->
					</c:if>
				</form>
			</div>
			<%
			}
			} else {
			%>
			<p>No se encontraron detalles del usuario.</p>
			<%
			}
			%>
		</div>
	</div>

	<script>
		function validateForm() {
			const fechaNacimiento = document.getElementById('fechaNacimiento').value;
			const errorMessage = document.getElementById('errorMessage');

			// Validación de fecha de nacimiento (no puede ser futura)
			const today = new Date();
			const fechaNacimientoDate = new Date(fechaNacimiento);

			if (fechaNacimiento && fechaNacimientoDate > today) {
				errorMessage.textContent = 'La fecha de nacimiento no puede ser una fecha futura.';
				errorMessage.style.color = 'red';
				return false; // Evita el envío del formulario
			}

			errorMessage.textContent = ''; // Limpia el mensaje de error
			return true; // Permite el envío del formulario
		}
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>