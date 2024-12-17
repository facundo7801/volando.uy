<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="uy.edu.fing.volandouy.webservices.UsuarioDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.AerolineaDto"%>
<%@ page import="uy.edu.fing.volandouy.webservices.ClienteDto"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
<meta charset="UTF-8">
<title>Detalles del Usuario</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet" href="./media/detalleUsuario.css" />
</head>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/template/header.jsp" />
		</div>

		<div class="container">
			<h1>Detalles del Usuario</h1>
			<%
			UsuarioDto usuario = (UsuarioDto) request.getAttribute("findUser");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (usuario != null) {
				if (usuario instanceof ClienteDto) {
					ClienteDto cliente = (ClienteDto) usuario;
			%>
			<div class="user-details">
				<img src="/ServidorWeb/Imagenes?nombreImagen=<%= cliente.getImagen() != null ? cliente.getImagen() : "noimg.jpg" %>" alt="Imagen de cliente">

				<div class="details-content">
					<ul>
						<li><i class="fas fa-user"></i> Nickname: <strong><%=cliente.getNickName()%></strong></li>
						<li><i class="fas fa-id-badge"></i> Nombre: <strong><%=cliente.getNombre()%></strong></li>
						<li><i class="fas fa-user-tag"></i> Apellido: <strong><%=cliente.getApellido()%></strong></li>
						<li><i class="fas fa-envelope"></i> Email: <strong><%=cliente.getEmail()%></strong></li>
						<li><i class="fas fa-calendar-alt"></i> Fecha de Alta: <strong><%=dateFormat.format(cliente.getFechaAlta().toGregorianCalendar().getTime())%></strong></li>
						<li><i class="fas fa-birthday-cake"></i> Fecha de Nacimiento:
							<strong><%=dateFormat.format(cliente.getFechaNacimiento().toGregorianCalendar().getTime())%></strong></li>
						<li><i class="fas fa-flag"></i> Nacionalidad: <strong><%=cliente.getNacionalidad()%></strong></li>
						<li><i class="fas fa-id-card"></i> Tipo de Documento: <strong><%=cliente.getTipoDocumento() != null ? cliente.getTipoDocumento().name() : "No disponible"%></strong></li>
						<li><i class="fas fa-hashtag"></i> Número de Documento: <strong><%=cliente.getNumeroDocumento()%></strong></li>
					</ul>

					<div class="action-buttons">
						<%
						String nicknameLogeado = (String) request.getSession().getAttribute("usuario");
						String seguir = (String) request.getAttribute("seguirEnabled");
						String dejarSeguir = (String) request.getAttribute("dejarSeguirEnabled");
						if (cliente.getNickName().equals(nicknameLogeado)) {
						%>
						<a href="ListarReservasServlet?clienteConsulta=consultaDirecta"
							class="btn btn-success">Consulta Reservas</a>
						<a href="listaPaqueteServlet?clienteConsulta=clienteConsulta"
							class="btn btn-success">Consulta Paquetes</a>
						<%
						}else if (seguir != null) {
						%>
							<a href="detallesUsuarioServlet?nickname=<%=cliente.getNickName()%>&seguir=si"
								class="btn btn-success">Seguir</a>
						<%
						} else if (dejarSeguir != null){
						%>
							<a href="detallesUsuarioServlet?nickname=<%=cliente.getNickName()%>&dejarSeguir=si"
								class="btn btn-success">Dejar de Seguir</a>
						<%
						}
						%>
						<a href="ListaUsuariosServlet?seguidos=si&nicknameUsuarioSeguidores=<%=cliente.getNickName() %>&seguidosL=Si"
							class="btn btn-success">Listar Seguidos</a>
						<a href="ListaUsuariosServlet?seguidores=si&nicknameUsuarioSeguidores=<%=cliente.getNickName() %>&seguidoresL=Si"
							class="btn btn-success">Listar Seguidores</a>
					</div>
				</div>
			</div>
			<%
			} else if (usuario instanceof AerolineaDto) {
			AerolineaDto aerolinea = (AerolineaDto) usuario;
			%>
			<div class="user-details">
				<img src="/ServidorWeb/Imagenes?nombreImagen=<%= aerolinea.getImagen() != null ? aerolinea.getImagen() : "noimg.jpg" %>" alt="Imagen de aerolinea">

				<div class="details-content">
					<ul>
						<li><i class="fas fa-user"></i> Nickname: <strong><%=aerolinea.getNickName()%></strong></li>
						<li><i class="fas fa-id-badge"></i> Nombre: <strong><%=aerolinea.getNombre()%></strong></li>
						<li><i class="fas fa-envelope"></i> Email: <strong><%=aerolinea.getEmail()%></strong></li>
						<li><i class="fas fa-calendar-alt"></i> Fecha de Alta: <strong><%=dateFormat.format(aerolinea.getFechaAlta().toGregorianCalendar().getTime())%></strong></li>
						<li><i class="fas fa-info-circle"></i> Descripción: <strong><%=aerolinea.getDescripcion()%></strong></li>
						<li><i class="fas fa-globe"></i> Sitio Web: <strong><%=aerolinea.getWebsite()%></strong></li>
					</ul>
					<div class="action-buttons">
						<a
							href="ListarRutasServlet?aerolineaListar=<%=aerolinea.getNickName()%>"
							class="btn btn-success">Listar Rutas</a>
						<%
						String nicknameLogeado = (String) request.getSession().getAttribute("usuario");
						String seguir = (String) request.getAttribute("seguirEnabled");
						String dejarSeguir = (String) request.getAttribute("dejarSeguirEnabled");
						%>
						<%
						if (seguir != null) {
						%>
							<a href="detallesUsuarioServlet?nickname=<%=aerolinea.getNickName()%>&seguir=si&seguidosL=Si"
								class="btn btn-success">Seguir</a>
						<%
						} else if (dejarSeguir != null){
						%>
							<a href="detallesUsuarioServlet?nickname=<%=aerolinea.getNickName()%>&dejarSeguir=si&seguidoresL=Si"
								class="btn btn-success">Dejar de Seguir</a>
						<%
						}
						%>
						<a href="ListaUsuariosServlet?seguidos=si&nicknameUsuarioSeguidores=<%=aerolinea.getNickName() %>&seguidosL=Si"
								class="btn btn-success">Listar Seguidos</a>
						<a href="ListaUsuariosServlet?seguidores=si&nicknameUsuarioSeguidores=<%=aerolinea.getNickName() %>&seguidoresL=Si"
								class="btn btn-success">Listar Seguidores</a>
					</div>
				</div>
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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>